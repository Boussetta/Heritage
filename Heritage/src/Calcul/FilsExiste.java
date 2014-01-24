package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class FilsExiste {
	public static void exécuterTraitement(Membre Décédé){
		
		/*
		 * 		Le premier héritier primaire, légitime à une part de la succession si le fils existe
		 * 		est le conjoint. Dans le cas ou le décédé est une femme et l'époux existe alors 
		 * 		il aura une part égale à 1/4 (dans le cas inverse elle aura une part égale à 1/8)
		 */
		
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
		
		/*
		 * 		le second héritier légitime à une part s'il existe, est le père, dans notre cas
		 * 		il aura une part égale à 1/6. Si le père n'existe pas alors on doit vérifier 
		 * 		l'existance du grand père, si c'est le cas il aura la part décrite précédemment
		 */
		
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
		
		/*
		 * 		Par la suite, nous devons vérifier la présence de la mère du décédé, si elle existe
		 * 		elle aura une part égale à celle du père (1/6), sinon cette part est partagé entre 
		 * 		les grands - mère selon les condition décrite dans le diagramme. 
		 */
		
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
		
		
		if(Décédé.getNbreFilles() == 0){
			int nbreFils = Décédé.getNbreFils_Vivants();
			for(int i = 0;i < Décédé.fils.size();i++){
				if(Décédé.fils.get(i).personne.sxe == sexe.male){
					if( !(Décédé.fils.get(i).personne instanceof famille.Décédé)){
						Décédé.fils.get(i).personne.part = CalculPart.Reste / nbreFils;
					}
				}
			}
		}else{
			int nbreFilles = Décédé.getNbreFilles();
			int nbreFils = Décédé.getNbreFils_Vivants();
			float partFille = CalculPart.Reste / (nbreFilles + (2 * nbreFils));
			for(int i = 0;i < Décédé.fils.size();i++){
				if(Décédé.fils.get(i).personne.sxe == sexe.male){
					Décédé.fils.get(i).personne.part = 2 * partFille;
				}else if(Décédé.fils.get(i).personne.sxe == sexe.femelle){
					Décédé.fils.get(i).personne.part = partFille;
				}
				
			}
		}
	}
}
