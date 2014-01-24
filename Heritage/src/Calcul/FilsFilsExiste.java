package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class FilsFilsExiste {
	/*
	 * 		Cette condition n'est ex�cuter qui si la premi�re n'est pas v�rifier,
	 * 		c'est � dire que si le fils de d�c�d� n'existe pas.
	 * 		Dans un tel cas le traitement est similaire au pr�c�dent sauf que 
	 * 		la/les filles deviennent parmi les h�ritier primaires,
	 * 		autrement elles ont une part fixe:
	 * 		si elle s'agit d'une fille elle aura la moitier de la succession,
	 * 		sinon elle auront tout le r�sidu
	 */
	
	public static void ex�cuterTraitement(Membre D�c�d�){		
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
		if(Existe.filles(D�c�d�)){
			float partFilles = 0;
			if(D�c�d�.getNbreFilles_vivantes() >= 2){
				partFilles = (2 * CalculPart.Biens) / 3;
			}else{
				partFilles = CalculPart.Biens / 2;
			}
			for(int i = 0;i < D�c�d�.fils.size();i++){
				if( (D�c�d�.fils.get(i).personne.sxe == sexe.femelle) && !(D�c�d�.fils.get(i).personne instanceof famille.D�c�d�) ){
					D�c�d�.fils.get(i).personne.part = partFilles / D�c�d�.getNbreFilles_vivantes();
				}
			}
			CalculPart.Reste = CalculPart.Reste - partFilles;
		}		
		for(int i = 0;i < D�c�d�.fils.size();i ++){
			for(int j = 0;j < D�c�d�.fils.get(i).fils.size();j ++){
				if( (D�c�d�.fils.get(i).fils.get(j).personne.sxe == sexe.male) && !(D�c�d�.fils.get(i).fils.get(j).personne instanceof famille.D�c�d�) ){
					D�c�d�.fils.get(i).fils.get(j).personne.part = CalculPart.Reste / getNbre_fils_de_fils(D�c�d�);
				}
			}
		}		
	}	
	public static int getNbre_fils_de_fils(Membre D�c�d�){
		int nbre = 0;
		for(int i = 0;i < D�c�d�.fils.size();i ++){
			nbre = nbre + D�c�d�.fils.get(i).getNbreFils_Vivants();
		}
		return nbre;
	}
}
