package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class P�reExiste {
	/*
	 * 		Si cette condition est v�rifier, alors les filles de fils vont avoir une part 
	 * 		�gale � 1/6 si le d�c�d� admet une seule fille, si elle sont deux ou plus
	 * 		alors les filles de fils sont exclues de la succession et le p�re devient b�n�ficiaire 
	 * 		de r�sidu de la succession
	 */
	public static void ex�cuterTraitement(Membre D�c�d�){
		if(D�c�d�.m�re != null){
			if( !(D�c�d�.m�re.personne instanceof famille.D�c�d�) ){
				D�c�d�.m�re.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.personne.part;
			}else{
				if(D�c�d�.m�re.m�re != null){
					if( !(D�c�d�.m�re.m�re.personne instanceof famille.D�c�d�)){
						D�c�d�.m�re.m�re.personne.part =  CalculPart.Biens / 6;
						CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.m�re.personne.part;
					}
				}
			}
		}		
		int nbreFilles = D�c�d�.getNbreFilles_vivantes();
		if(nbreFilles > 0){
			float partFilles = 0;
			if(nbreFilles >= 2){
				partFilles = (2 * CalculPart.Biens) / 3;
			}else{
				partFilles = CalculPart.Biens / 2;
				if(Existe.filles_de_fils(D�c�d�)){					
					float part_Filles_de_Fils = CalculPart.Biens / 6;
					for(int i = 0;i < D�c�d�.fils.size();i ++){
						for(int j = 0;j < D�c�d�.fils.get(i).fils.size();j ++){
							if( (D�c�d�.fils.get(i).fils.get(j).personne.sxe == sexe.femelle) && !(D�c�d�.fils.get(i).fils.get(j).personne instanceof famille.D�c�d�) ){
								D�c�d�.fils.get(i).fils.get(j).personne.part = part_Filles_de_Fils / getNbre_filles_de_fils(D�c�d�);
							}
						}
					}
					CalculPart.Reste = CalculPart.Reste - part_Filles_de_Fils;					
				}
			}
			for(int i = 0;i < D�c�d�.fils.size();i ++){
				if( (D�c�d�.fils.get(i).personne.sxe == sexe.femelle) && !(D�c�d�.fils.get(i).personne instanceof famille.D�c�d�) ){
					D�c�d�.fils.get(i).personne.part = partFilles / nbreFilles;
				}
			}
			CalculPart.Reste = CalculPart.Reste - partFilles;
		}else{
			if(Existe.filles_de_fils(D�c�d�)){
				int nbre_filles_de_fils = getNbre_filles_de_fils(D�c�d�);
				float part_filles_de_fils = 0;
				if(nbre_filles_de_fils >= 2){
					part_filles_de_fils = (2 * CalculPart.Biens) / 3;
				}else{
					part_filles_de_fils = CalculPart.Biens / 2;
				}
				for(int i = 0;i < D�c�d�.fils.size();i ++){
					for(int j = 0;j < D�c�d�.fils.get(i).fils.size();j++){
						if( (D�c�d�.fils.get(i).fils.get(j).personne.sxe == sexe.femelle) && !(D�c�d�.fils.get(i).fils.get(j).personne instanceof famille.D�c�d�) ){
							D�c�d�.fils.get(i).fils.get(j).personne.part = part_filles_de_fils / getNbre_filles_de_fils(D�c�d�);
						}
					}
				}
				CalculPart.Reste = CalculPart.Reste - part_filles_de_fils;
			}		
		}
		D�c�d�.p�re.personne.part = CalculPart.Reste;
	}
	public static int getNbre_filles_de_fils(Membre D�c�d�){
		int nbre = 0;
		for(int i = 0;i < D�c�d�.fils.size();i ++){
			nbre = nbre + D�c�d�.fils.get(i).getNbreFilles_vivantes();
		}
		return nbre;
	}
}
