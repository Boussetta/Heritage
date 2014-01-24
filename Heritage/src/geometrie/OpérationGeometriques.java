package geometrie;
import famille.Membre;
import geometrie.FormesG�om�triques.Ligne;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.heritage.android.Temp;
public class Op�rationGeometriques {
	public static void TracerLigne(Canvas canvas,Ligne ligne){
		Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth (5);
        canvas.drawLine(ligne.d�part.X + Temp.TranslationX + Temp.TrX,ligne.d�part.Y + Temp.TranslationY + Temp.TrY,
        				ligne.arriv�e.X+ Temp.TranslationX + Temp.TrX,ligne.arriv�e.Y + Temp.TranslationY + Temp.TrY, paint);
	}
	public static void TracerCercle(Canvas canvas,Membre membre){
		Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth (5);
        canvas.drawCircle(membre.X+ 24 + Temp.TranslationX + Temp.TrX,
        				 	membre.Y + 27 + Temp.TranslationY + Temp.TrY,
        				 		30,paint);
	}
	
	public static void alignerHorizontale(Membre m1,Membre m2,Ligne ligne){
		/***************** Distance de garde entre les deux membres *****************/
		if(Math.abs(m1.X - m2.X) < (48 + 40)){
			if(m1.X < m2.X){
				m2.X = m1.X + 48 + 40;
			}else if(m2.X < m1.X){
				m1.X = m2.X + 48 + 40;
			}			
		}
		if((m1.admetLien() && !m2.admetLien()) || (!m1.admetLien() && !m2.admetLien())){
			m2.Y = m1.Y;
			ligne.d�part.Y = m1.Y + 40;
			ligne.arriv�e.Y = m2.Y + 40;		
		}else if(!m1.admetLien() && m2.admetLien()){
			m1.Y = m2.Y;
			ligne.d�part.Y = m1.Y + 40;
			ligne.arriv�e.Y = m2.Y + 40;
		}
		if(m1.X < m2.X){
			ligne.d�part.X = m1.X + 53;
			ligne.arriv�e.X = m2.X - 5;
		}else{
			ligne.d�part.X = m1.X - 5;
			ligne.arriv�e.X = m2.X + 53;	
		}
	}
	public static void alignerHorizontale(Membre m1, Membre m2){
		if(Math.abs(m1.X - m2.X) < (48 + 50)){
			if(m1.X < m2.X){
				m2.X = m1.X + 48 + 50;
			}else if(m2.X < m1.X){
				m1.X = m2.X + 48 + 50;
			}			
		}else if(Math.abs(m1.X - m2.X) > (48 + 100)){
			if(m1.X < m2.X){
				m2.X = m1.X + 48 + 50;
			}else if(m2.X < m1.X){
				m1.X = m2.X + 48 + 50;
			}
		}
		if((m1.admetLien() && !m2.admetLien()) || (!m1.admetLien() && !m2.admetLien())){
			m2.Y = m1.Y;		
		}else if(!m1.admetLien() && m2.admetLien()){
			m1.Y = m2.Y;
		}
	}
	public static void alignerVerticale(Membre m1,Membre m2,Ligne ligne){
		/***************** Distance de garde entre les deux membres *****************/
		if(Math.abs(m1.Y - m2.Y) < (48 + 30)){
			if(m1.Y < m2.Y){
				m2.Y = m1.Y + 48 + 30;
			}else if(m2.Y < m1.Y){
				m1.Y = m2.Y + 48 + 30;
			}			
		}
		if((m1.admetLien() && !m2.admetLien()) || (!m1.admetLien() && !m2.admetLien())){
			m2.X = m1.X;
			ligne.d�part.X = m1.X + 24;
			ligne.arriv�e.X = m2.X + 24;		
		}else if(!m1.admetLien() && m2.admetLien()){
			m1.X = m2.X;
			ligne.d�part.X = m1.X + 24;
			ligne.arriv�e.X = m2.X + 24;
		}
		if(m1.Y < m2.Y){
			ligne.d�part.Y = m1.Y + 53;
			ligne.arriv�e.Y = m2.Y - 5;
		}else{
			ligne.d�part.Y = m1.Y - 5;
			ligne.arriv�e.Y = m2.Y + 53;	
		}
	}
}