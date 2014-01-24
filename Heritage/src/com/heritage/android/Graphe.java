package com.heritage.android;
import Calcul.CalculPart;
import DialogBox.MembreDialog;
import DialogBox.MortDialog;
import DialogBox.RelationDialog;
import DialogBox.SaveDialog;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import famille.ArbreGN;
import famille.Personne.sexe;
import geometrie.FormesGéométriques.Ligne;
import geometrie.FormesGéométriques.Point;
import geometrie.OpérationGeometriques;

public class Graphe extends Activity implements OnTouchListener{
	public static RenderView rndr_view;
	public Vibrator Levibreur;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		rndr_view = new RenderView(this);
		rndr_view.setOnTouchListener(this);
		setContentView(rndr_view);
		Temp.initJPG(Graphe.this);
		//Toast.makeText(this, R.string.familytree,Toast.LENGTH_SHORT).show();
	}
	public class RenderView extends View{
		private MotionEvent event=null ;
		public RenderView(Context context) {
			super(context);
		}
		public void setEvent(MotionEvent event) {
			this.event = event;			
		}
		protected void onDraw(Canvas canvas) {
			/***************** I - Chargement de la Zone de Dessin*****************/
//			canvas.drawRGB(255,255, 255);
			canvas.drawBitmap(Temp.grille, 0,0, null);
	        canvas.drawBitmap(Temp.Trash, 10,10, null);
	        canvas.drawBitmap(Temp.male, canvas.getWidth() - 50,10, null);
	        canvas.drawBitmap(Temp.femelle, canvas.getWidth() - 50,70, null);	        
	        
	        if(!Temp.arbre.Membreslist.isEmpty()){
	        	Temp.arbre.afficherMembres(canvas, Graphe.this);
	        }
	        
	        /***************** II - Actions suites aux Touchée à l'écran *****************/
	        if(event != null){
	        	int action = event.getAction() & MotionEvent.ACTION_MASK;
	        	/***************** II - 1 - Appui sur l'écran *****************/
	        	if(action == MotionEvent.ACTION_DOWN){
	        		/***************** II - 1 - 1 - Création d'un nouveau membre de la famille *****************/
	        		if(  (event.getX() >= (canvas.getWidth() - 50)) && (event.getY() >= 10) && (event.getY() <= 60)
	        				&& Temp.SelectedSexe == null){
		        		Levibreur  = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		        		Levibreur.vibrate(50);
	        			Temp.SelectedSexe = sexe.male;
		        		Temp.tampon = Bitmap.createScaledBitmap(Temp.male, 60, 60, true);
		        		canvas.drawBitmap(Temp.tampon, canvas.getWidth() - 65,5, null);
	        		}else if((event.getX() >= (canvas.getWidth() - 50)) && (event.getY() >= 70) && (event.getY() <= 120)  
	        				&& Temp.SelectedSexe == null){
		        		Levibreur  = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		        		Levibreur.vibrate(50);
		        		Temp.SelectedSexe = sexe.femelle;
		        		Temp.tampon = Bitmap.createScaledBitmap(Temp.femelle, 60, 60, true);
		        		canvas.drawBitmap(Temp.tampon, canvas.getWidth() - 65,65, null);
		        	/***************** II - 1 - 2 - Création d'un lien parental *****************/
		        	}else if(Temp.SelectedSexe == null && Temp.tampoSexe == null){
		        		Temp.Membre = Temp.arbre.trouverMembre(event.getX() - Temp.TranslationX - Temp.TrX, 
		        													event.getY() - Temp.TranslationY - Temp.TrY,
		        															Temp.arbre.Membreslist);
		        		if(Temp.Membre != null){
		        			Temp.Décédé_éditable = true;
		        			Temp.départ = new Point(event.getX() - 5 - Temp.TranslationX - Temp.TrX,
		        										event.getY() - 5 - Temp.TranslationY - Temp.TrY);		        			
		        	/***************** II - 1 - 3 - Déplacement de l'écran *****************/
		        		}else{
		        			if(!Temp.arbre.Membreslist.isEmpty()){
			        			Temp.Translation = true;
			        			Temp.InitX = event.getX();
			        			Temp.InitY = event.getY();		        				
		        			}
		        		}
		        	}
	        	/***************** II - 2 - Mouvement du doigt sur l'écran *****************/
	        	}else if(action == MotionEvent.ACTION_MOVE){
	        		Temp.Décédé_éditable = false;
	        		/***************** II - 2 - 1 - Création d'un nouveau membre de la famille *****************/
	        		if(Temp.SelectedSexe != null){
	        			Temp.X = event.getX() - 70;
	        			Temp.Y = event.getY() - 70;
        				canvas.drawBitmap(Temp.tampon, Temp.X,Temp.Y, null);
        			/***************** II - 2 - 2 - Création d'un lien parental *****************/
	        		}else if(Temp.départ != null){
	        			Temp.ligne = new Ligne(Temp.départ, new Point(event.getX() - 5 - Temp.TranslationX - Temp.TrX,
	        															event.getY() - 5 - Temp.TranslationY - Temp.TrY));
	        			OpérationGeometriques.TracerLigne(canvas,Temp.ligne);
	        		/***************** II - 2 - 3 - Déplacement de l'écran *****************/
	        		}else if(Temp.Translation == true){
	        			Temp.TrX = event.getX() - Temp.InitX;
	        			Temp.TrY = event.getY() - Temp.InitY;
	        		}
	        	/***************** II - 3 - Relachement du doigt de l'écran *****************/
	        	}else if(action == MotionEvent.ACTION_UP){
	        		/***************** II - 3 - 1 - Création d'un nouveau membre de la famille *****************/
	        		if(Temp.SelectedSexe != null){
	        			Temp.tampoSexe = Temp.SelectedSexe;
	        			if(!((Temp.X  >= (canvas.getWidth() - 110)) && (Temp.Y + 10 <= 120)) ){
	        				MembreDialog Dialog = new MembreDialog(Graphe.this);
	        				Dialog.show();
	        			}
	        		/***************** II - 3 - 2 - Création d'un lien parental *****************/
	        		}else if(Temp.ligne != null && Temp.arbre.trouverMembre(Temp.ligne.arrivée.X, Temp.ligne.arrivée.Y,Temp.arbre.Membreslist) != null){
	        			Temp.M1 = Temp.Membre;
        				Temp.M2 = Temp.arbre.trouverMembre(Temp.ligne.arrivée.X,
        														Temp.ligne.arrivée.Y,
        															Temp.arbre.Membreslist);
	        			if(!Temp.arbre.existeLien(Temp.M1,Temp.M2)){
	        				Temp.tampoLigne = Temp.ligne;
	        				if(Temp.M1 != Temp.M2){	        					
	        					RelationDialog Dialog = new RelationDialog(Graphe.this);
		        				Dialog.show();
	        				}
	        			}
        			}else if (Temp.Membre != null && (Temp.Décédé_éditable == true) && Temp.ligne == null){
        				Temp.Décédé = Temp.Membre;
        				MortDialog Dialog = new MortDialog(Graphe.this);
        				Dialog.show();
        			}
	        		/***************** II - 3 - 3 - Déplacement de l'écran *****************/
	        		else if(Temp.Translation == true){
        				Temp.InitX = 0;
	        			Temp.InitY = 0;
        				Temp.TranslationX += Temp.TrX;
        				Temp.TranslationY += Temp.TrY;
        				Temp.TrX = 0;
        				Temp.TrX = 0;
        				Temp.Translation = false;
        			}
					Temp.SelectedSexe = null;
	        		Temp.départ = null;
    				Temp.ligne = null;
    				Temp.Décédé_éditable = false;
	        	}
	        }
		}		
	}
	public boolean onTouch(View v, MotionEvent event) {
		rndr_view.setEvent (event);
    	v.invalidate();
        return true;
	}

	/***************** III - Gestion des Menus *****************/
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option, menu);
		return true;    
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.clean:
			Temp.viderMémoire();
			rndr_view.invalidate();
			return true;
		case R.id.save:
			SaveDialog Dialog = new SaveDialog(Graphe.this);
			Dialog.show();
			return true;
		case R.id.CalculerParts:
			CalculPart.exécuter(this,ArbreGN.trouverDécédé(Temp.arbre.Membreslist));
			//rndr_view.invalidate();
			return true;
		case R.id.QuiterGraphe:
			Temp.arbre.Membreslist.clear();
			Graphe.this.finish();
			return true;
		}
		return false;
	}	
}