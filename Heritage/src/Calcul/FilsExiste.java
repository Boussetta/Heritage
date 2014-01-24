package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class FilsExiste {
	public static void ex�cuterTraitement(Membre D�c�d�){
		
		/*
		 * 		Le premier h�ritier primaire, l�gitime � une part de la succession si le fils existe
		 * 		est le conjoint. Dans le cas ou le d�c�d� est une femme et l'�poux existe alors 
		 * 		il aura une part �gale � 1/4 (dans le cas inverse elle aura une part �gale � 1/8)
		 */
		
		if(D�c�d�.conjoint != null){
			if( !(D�c�d�.conjoint.personne instanceof famille.D�c�d�) ){
				if(D�c�d�.conjoint.personne.sxe == sexe.male){
					D�c�d�.conjoint.personne.part = CalculPart.Biens / 4;
				}else if(D�c�d�.conjoint.personne.sxe == sexe.femelle){
					D�c�d�.conjoint.personne.part = CalculPart.Biens / 8;
				}
				CalculPart.Reste = CalculPart.Reste - D�c�d�.conjoint.personne.part;
			}
		}
		
		/*
		 * 		le second h�ritier l�gitime � une part s'il existe, est le p�re, dans notre cas
		 * 		il aura une part �gale � 1/6. Si le p�re n'existe pas alors on doit v�rifier 
		 * 		l'existance du grand p�re, si c'est le cas il aura la part d�crite pr�c�demment
		 */
		
		if(D�c�d�.p�re != null){
			if( !(D�c�d�.p�re.personne instanceof famille.D�c�d�)){
				D�c�d�.p�re.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - D�c�d�.p�re.personne.part;
			}else{
				if(D�c�d�.p�re.p�re != null){
					if(!(D�c�d�.p�re.p�re.personne instanceof famille.D�c�d�)){
						D�c�d�.p�re.p�re.personne.part = CalculPart.Biens / 6;
						CalculPart.Reste = CalculPart.Reste - D�c�d�.p�re.p�re.personne.part;
					}
				}
			}
		}
		
		/*
		 * 		Par la suite, nous devons v�rifier la pr�sence de la m�re du d�c�d�, si elle existe
		 * 		elle aura une part �gale � celle du p�re (1/6), sinon cette part est partag� entre 
		 * 		les grands - m�re selon les condition d�crite dans le diagramme. 
		 */
		
		if(D�c�d�.m�re != null){
			if( !(D�c�d�.m�re.personne instanceof famille.D�c�d�)){
				D�c�d�.m�re.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.personne.part;
			}else{
				if(D�c�d�.p�re != null){
					if(!(D�c�d�.p�re.personne instanceof famille.D�c�d�)){
						if(D�c�d�.m�re.m�re != null){
							if( !(D�c�d�.m�re.m�re.personne instanceof famille.D�c�d�)){
								D�c�d�.m�re.m�re.personne.part =  CalculPart.Biens / 6;
								CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.m�re.personne.part;
							}
						}
					}
				}
			}
		}
		
		
		if(D�c�d�.getNbreFilles() == 0){
			int nbreFils = D�c�d�.getNbreFils_Vivants();
			for(int i = 0;i < D�c�d�.fils.size();i++){
				if(D�c�d�.fils.get(i).personne.sxe == sexe.male){
					if( !(D�c�d�.fils.get(i).personne instanceof famille.D�c�d�)){
						D�c�d�.fils.get(i).personne.part = CalculPart.Reste / nbreFils;
					}
				}
			}
		}else{
			int nbreFilles = D�c�d�.getNbreFilles();
			int nbreFils = D�c�d�.getNbreFils_Vivants();
			float partFille = CalculPart.Reste / (nbreFilles + (2 * nbreFils));
			for(int i = 0;i < D�c�d�.fils.size();i++){
				if(D�c�d�.fils.get(i).personne.sxe == sexe.male){
					D�c�d�.fils.get(i).personne.part = 2 * partFille;
				}else if(D�c�d�.fils.get(i).personne.sxe == sexe.femelle){
					D�c�d�.fils.get(i).personne.part = partFille;
				}
				
			}
		}
	}
}
