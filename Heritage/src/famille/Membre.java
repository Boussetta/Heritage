package famille;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.heritage.android.Temp;

import famille.Personne.sexe;
import geometrie.FormesGéométriques.Ligne;
import geometrie.FormesGéométriques.Point;
import geometrie.OpérationGeometriques;

public class Membre {
	public Personne personne;
	public float X;
	public float Y;
	public Membre père = null;
	public Membre mère = null;
	public Membre conjoint = null;	
	public Vector<Membre> fils = null;
	public Membre(Personne personne,float X,float Y){
		this.personne = personne;
		this.X = X;
		this.Y = Y;
		this.fils = new Vector<Membre>();
	}
	public void afficher(Canvas canvas,Context context){
		Bitmap bitmap = null;
		InputStream inputStream = null;
        try {
            AssetManager assetManager = context.getAssets();
            if(this.personne instanceof Décédé){
            	inputStream = assetManager.open("mort.png");
	            bitmap = BitmapFactory.decodeStream(inputStream);
            }else{
            	if(this.personne.sxe == sexe.male){
	            	inputStream = assetManager.open("male.png");
	                bitmap = BitmapFactory.decodeStream(inputStream);
	            }else if(this.personne.sxe == sexe.femelle){
	            	inputStream = assetManager.open("femelle.png");
	                bitmap = BitmapFactory.decodeStream(inputStream);
	            }
            }
        }catch (IOException e) {
        	e.printStackTrace();
        }finally {
        	try {
        		if (inputStream != null)
        			inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        canvas.drawBitmap(bitmap, this.X + Temp.TranslationX + Temp.TrX,
        							this.Y + Temp.TranslationY + Temp.TrY, null);	        
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        canvas.drawText(this.personne.prenom, this.X + 50 + Temp.TranslationX + Temp.TrX,
        										this.Y + 10 + Temp.TranslationY + Temp.TrY , paint);
        canvas.drawText(this.personne.nom, this.X + 50 + Temp.TranslationX + Temp.TrX,
        										this.Y + 25 + Temp.TranslationY + Temp.TrY, paint);
        
        if(this.personne instanceof Décédé){
        	if(((famille.Décédé)this.personne).Biens > 0){
        		paint.setColor(Color.BLUE);
            	paint.setTextSize(16);
            	canvas.drawText(String.valueOf(((famille.Décédé)this.personne).Biens), this.X + 50 + Temp.TranslationX + Temp.TrX,
    					this.Y + 40 + Temp.TranslationY + Temp.TrY, paint);
        	}
        }else{
        	if(this.personne.part > 0){
            	paint.setColor(Color.RED);
            	paint.setTextSize(16);
            	canvas.drawText(String.valueOf((double)((int)(this.personne.part*100))/100), this.X + 50 + Temp.TranslationX + Temp.TrX,
    					this.Y + 40 + Temp.TranslationY + Temp.TrY, paint);
            }
        }
        
        
	}
	public boolean admetLien() {
		boolean admetLien = false;
		if(this.conjoint != null ||
			this.père != null ||
				this.mère != null){
			admetLien = true;
		}
		return admetLien;
	}

	public void afficherSousMembre(Canvas canvas, Context graphe) {		
		if(!this.fils.isEmpty()){
			Point p1;
			Point p2;
			if(this.conjoint == null){
				p1 = new Point(this.X + 24,this.Y + 53);
				p2 = new Point(this.X + 24,this.Y + 73);
			}else{
				p1 = new Point( (this.conjoint.X + 48 + this.X) / 2,this.Y + 40);
				p2 = new Point( (this.conjoint.X + 48 + this.X) / 2,this.Y + 73);
			}
			OpérationGeometriques.TracerLigne(canvas, new Ligne(p1,p2));			
			if(this.fils.size() > 0){
				Point p3 = new Point(p2.X - (24 + ( (this.fils.size() - 2) * 48) + 24),p2.Y);
				Point p4 = new Point(p2.X + (24 + ( (this.fils.size() - 2) * 48) + 24),p2.Y);
				OpérationGeometriques.TracerLigne(canvas, new Ligne(p3,p4));
				this.fils.get(0).X = p3.X - 24;
				this.fils.get(0).Y = this.Y + 78;
				this.fils.get(0).afficher(canvas, graphe);
				this.fils.get(0).afficherSousMembre(canvas, graphe);
				for(int i = 1;i<this.fils.size();i++){
					this.fils.get(i).X = this.fils.get(i - 1).X + (2 * 48);
					this.fils.get(i).Y = this.fils.get(i - 1).Y;
					this.fils.get(i).afficher(canvas, graphe);
					this.fils.get(i).afficherSousMembre(canvas, graphe);
				}
			}
		}		
		if(this.conjoint != null){
			OpérationGeometriques.TracerLigne(canvas, new Ligne(new Point(this.X + 53,this.Y + 40),
																	new Point(this.X + 83,this.Y + 40)));
			this.conjoint.X = this.X + 88;
			this.conjoint.Y = this.Y;
			this.conjoint.afficher(canvas, graphe);
		}
	}
	public int getNbreFils(){
		int NbreFils = 0;
		for(int i = 0;i < this.fils.size();i++){
			if(this.fils.get(i).personne.sxe == sexe.male){
				NbreFils ++;
			}
		}
		return NbreFils;
	}
	
	public int getNbreFils_Vivants(){
		int NbreFils = 0;
		for(int i = 0;i < this.fils.size();i++){
			if( (this.fils.get(i).personne.sxe == sexe.male) && !(this.fils.get(i).personne instanceof famille.Décédé)){
				NbreFils ++;
			}
		}
		return NbreFils;
	}
	public int getNbreFilles(){
		int NbreFilles = 0;
		for(int i = 0;i < this.fils.size();i++){
			if(this.fils.get(i).personne.sxe == sexe.femelle){
				NbreFilles ++;
			}
		}
		return NbreFilles;
	}
	public int getNbreFilles_vivantes(){
		int NbreFilles = 0;
		for(int i = 0;i < this.fils.size();i++){
			if( (this.fils.get(i).personne.sxe == sexe.femelle) && !(this.fils.get(i).personne instanceof famille.Décédé) ){
				NbreFilles ++;
			}
		}
		return NbreFilles;
	}
}