package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class grandP�reExiste {
	public static void ex�cuterTraitement(Membre D�c�d�){
		if(D�c�d�.m�re != null){
			if( !(D�c�d�.m�re.personne instanceof famille.D�c�d�) ){
				D�c�d�.m�re.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.personne.part;
			}else{
				if(D�c�d�.m�re.m�re != null){
					if( !(D�c�d�.m�re.m�re.personne instanceof famille.D�c�d�)){
						if(D�c�d�.p�re.m�re != null){
							if( !(D�c�d�.p�re.m�re.personne instanceof famille.D�c�d�)){
								D�c�d�.p�re.m�re.personne.part = CalculPart.Biens / 12;
								CalculPart.Reste = CalculPart.Reste - D�c�d�.p�re.m�re.personne.part;
								D�c�d�.m�re.m�re.personne.part = CalculPart.Biens / 12;
								CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.m�re.personne.part;
							}
						}
						D�c�d�.m�re.m�re.personne.part = CalculPart.Biens / 6;
						CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.m�re.personne.part;
					}
				}else{
					if(D�c�d�.p�re.m�re != null){
						if( !(D�c�d�.p�re.m�re.personne instanceof famille.D�c�d�)){
							D�c�d�.p�re.m�re.personne.part = CalculPart.Biens / 6;
							CalculPart.Reste = CalculPart.Reste - D�c�d�.p�re.m�re.personne.part;
						}
					}
				}
			}
		}
		if(Existe.filles(D�c�d�)){
			int nbre_filles = D�c�d�.getNbreFilles_vivantes();
			float part_filles = 0;
			if(nbre_filles >= 2){
				part_filles = (2 * CalculPart.Biens) / 3;
			}else{
				part_filles = CalculPart.Biens / 2;
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
					D�c�d�.fils.get(i).personne.part = part_filles / nbre_filles;
				}
			}
			CalculPart.Reste = CalculPart.Reste - part_filles;			
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
		D�c�d�.p�re.p�re.personne.part = CalculPart.Reste;
	}
	public static int getNbre_filles_de_fils(Membre D�c�d�){
		int nbre = 0;
		for(int i = 0;i < D�c�d�.fils.size();i ++){
			nbre = nbre + D�c�d�.fils.get(i).getNbreFilles_vivantes();
		}
		return nbre;
	}
}
