package geometrie;

import android.content.Context;
import android.widget.Toast;
public class FormesGéométriques {
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
		public Point départ;
		public Point arrivée;
		public Ligne(){
			this.départ = new Point();
			this.arrivée = new Point();
		}
		public Ligne(Point point){
			this.départ = new Point();
			this.arrivée = point;
		}
		public Ligne(Point départ,Point arrivée){
			this.départ = départ;
			this.arrivée = arrivée;
		}
	}
}
