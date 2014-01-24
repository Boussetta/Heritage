package geometrie;

import android.content.Context;
import android.widget.Toast;
public class FormesG�om�triques {
	public static class Point{
		public float X;
		public float Y;
		public Point(){
			this.X = 0;
			this.Y = 0;
		}
		public Point(float X,float Y){
			this.X = X;
			this.Y = Y;
		}
		public void afficher(Context Graphe){
			Toast.makeText(Graphe, "(" + this.X + "," + this.Y + ")" ,Toast.LENGTH_SHORT).show();
		}
	}
	public static class Ligne{
		public Point d�part;
		public Point arriv�e;
		public Ligne(){
			this.d�part = new Point();
			this.arriv�e = new Point();
		}
		public Ligne(Point point){
			this.d�part = new Point();
			this.arriv�e = point;
		}
		public Ligne(Point d�part,Point arriv�e){
			this.d�part = d�part;
			this.arriv�e = arriv�e;
		}
	}
}
