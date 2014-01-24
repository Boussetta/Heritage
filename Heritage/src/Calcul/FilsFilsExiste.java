package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class FilsFilsExiste {
	/*
	 * 		Cette condition n'est exécuter qui si la première n'est pas vérifier,
	 * 		c'est à dire que si le fils de décédé n'existe pas.
	 * 		Dans un tel cas le traitement est similaire au précédent sauf que 
	 * 		la/les filles deviennent parmi les héritier primaires,
	 * 		autrement elles ont une part fixe:
	 * 		si elle s'agit d'une fille elle aura la moitier de la succession,
	 * 		sinon elle auront tout le résidu
	 */
	
	public static void exécuterTraitement(Membre Décédé){		
		if(Décédé.conjoint != null){
			if( !(Décédé.conjoint.personne instanceof famille.Décédé) ){
				if(Décédé.conjoint.personne.sxe == sexe.male){
					Décédé.conjoint.personne.part = CalculPart.Biens / 4;
				}else if(Décédé.conjoint.personne.sxe == sexe.femelle){
					Décédé.conjoint.personne.part = CalculPart.Biens / 8;
				}
				CalculPart.Reste = CalculPart.Reste - Décédé.conjoint.personne.part;
			}
		}		
		if(Décédé.père != null){
			if( !(Décédé.père.personne instanceof famille.Décédé)){
				Décédé.père.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - Décédé.père.personne.part;
			}else{
				if(Décédé.père.père != null){
					if(!(Décédé.père.père.personne instanceof famille.Décédé)){
						Décédé.père.père.personne.part = CalculPart.Biens / 6;
						CalculPart.Reste = CalculPart.Reste - Décédé.père.père.personne.part;
					}
				}
			}
		}		
		if(Décédé.mère != null){
			if( !(Décédé.mère.personne instanceof famille.Décédé)){
				Décédé.mère.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - Décédé.mère.personne.part;
			}else{
				if(Décédé.père != null){
					if(!(Décédé.père.personne instanceof famille.Décédé)){
						if(Décédé.mère.mère != null){
							if( !(Décédé.mère.mère.personne instanceof famille.Décédé)){
								Décédé.mère.mère.personne.part =  CalculPart.Biens / 6;
								CalculPart.Reste = CalculPart.Reste - Décédé.mère.mère.personne.part;
							}
						}
					}
				}
			}
		}		
		if(Existe.filles(Décédé)){
			float partFilles = 0;
			if(Décédé.getNbreFilles_vivantes() >= 2){
				partFilles = (2 * CalculPart.Biens) / 3;
			}else{
				partFilles = CalculPart.Biens / 2;
			}
			for(int i = 0;i < Décédé.fils.size();i++){
				if( (Décédé.fils.get(i).personne.sxe == sexe.femelle) && !(Décédé.fils.get(i).personne instanceof famille.Décédé) ){
					Décédé.fils.get(i).personne.part = partFilles / Décédé.getNbreFilles_vivantes();
				}
			}
			CalculPart.Reste = CalculPart.Reste - partFilles;
		}		
		for(int i = 0;i < Décédé.fils.size();i ++){
			for(int j = 0;j < Décédé.fils.get(i).fils.size();j ++){
				if( (Décédé.fils.get(i).fils.get(j).personne.sxe == sexe.male) && !(Décédé.fils.get(i).fils.get(j).personne instanceof famille.Décédé) ){
					Décédé.fils.get(i).fils.get(j).personne.part = CalculPart.Reste / getNbre_fils_de_fils(Décédé);
				}
			}
		}		
	}	
	public static int getNbre_fils_de_fils(Membre Décédé){
		int nbre = 0;
		for(int i = 0;i < Décédé.fils.size();i ++){
			nbre = nbre + Décédé.fils.get(i).getNbreFils_Vivants();
		}
		return nbre;
	}
}
