package famille;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;

public class ArbreGN {
	public Vector<Membre> Membreslist;

	public ArbreGN(){
		this.Membreslist = new Vector<Membre>();
	}
	
	public void afficherMembres(Canvas canvas, Context graphe) {
		for(int i = 0;i < Membreslist.size();i++){
			Membreslist.get(i).afficher(canvas, graphe);
			Membreslist.get(i).afficherSousMembre(canvas, graphe);
		}		
	}
	
	public boolean existeLien(Membre M1,Membre M2) {
		boolean existe = false;
		if( existe == false && (M1.conjoint == M2 /*|| M1.p�re == M2 || M1.m�re == M2*/) ){
			existe = true;
		}
		
		if( existe == false && (M2.conjoint == M1 /*|| M2.p�re == M1 || M2.m�re == M1*/) ){
			existe = true;
		}
		return existe;
	}

	public Membre trouverMembre(float X, float Y, Vector<Membre> membreslist) {
		Membre result = null;
		int i = 0;
		while( ( result == null ) && (i < membreslist.size()) ){
			result = trouverMembre(X,Y,membreslist.get(i));
			if(result == null){
				i++;
			}
		}
		return result;
	}
	
	public Membre trouverMembre(float X, float Y, Membre M){
		Membre result = null;
		if( ( X >= M.X ) && ( X <= M.X + 48 ) 
				&& ( Y >= M.Y ) && ( Y <= M.Y + 48 ) ){
			result = M;
		}		
		if( (result == null) && (M.conjoint != null) ){
			if((X >= M.conjoint.X) && (X <= M.conjoint.X + 48) 
					&& (Y >= M.conjoint.Y) && (Y <= M.conjoint.Y + 48)){
				result = M.conjoint;
			}
		}		
		if( (result == null) && (M.fils.size() > 0) ){
			result = trouverMembre(X,Y,M.fils);
		}		
		return result;
	}

	public static Membre trouverD�c�d�(Vector<Membre> membreslist){
		Membre result = null;
		int i = 0;
		while( ( result == null ) && (i < membreslist.size()) ){
			result = trouverD�c�d�(membreslist.get(i));
			if(result == null){
				i++;
			}
		}
		return result;
	}
	
	public static Membre trouverD�c�d�(Membre M){
		/*
		 * 		recherche du d�c�d� dans les cas triviaux
		 */
		Membre result = null;
		if( M.personne instanceof famille.D�c�d� ){
			if(((famille.D�c�d�)M.personne).Biens > 0){
				result = M;				
			}
		}		
		if( (result == null) && (M.conjoint != null) ){
			if( M.conjoint.personne instanceof famille.D�c�d� ){
				if(((famille.D�c�d�)M.conjoint.personne).Biens > 0){
					result = M.conjoint;					
				}
			}
		}
		
		/*
		 * 		recherche dans les sous membres
		 */
		if( (result == null) && (M.fils.size() > 0) ){
			result = trouverD�c�d�(M.fils);
		}		
		return result;
	}
}
