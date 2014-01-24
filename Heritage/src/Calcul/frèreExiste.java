package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class fr�reExiste {
	/*
	 * 		Dans un tel cas les b�n�ficiaires de r�sidu sont les fr�res et les soeurs
	 */
	public static void ex�cuterTraitement(Membre D�c�d�){
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
				float part_filles_de_fils = 0;
				if(Existe.epoux(D�c�d�)){
					float part_conjoint = 0;
					if(D�c�d�.conjoint.personne.sxe == sexe.male){
						part_conjoint = CalculPart.Biens / 4;
					}else{
						part_conjoint = CalculPart.Biens / 8;
					}
					D�c�d�.conjoint.personne.part = part_conjoint;
					CalculPart.Reste = CalculPart.Reste - part_conjoint;
				}
				if(getNbre_filles_de_fils(D�c�d�) >= 2){
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
			}else{
				if(Existe.epoux(D�c�d�)){
					D�c�d�.conjoint.personne.part = CalculPart.Biens / 2;
				}
			}
		}
		if(Existe.m�re(D�c�d�)){
			D�c�d�.m�re.personne.part = CalculPart.Biens / 6;
			CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.personne.part;
		}else{
			if(Existe.grandM�re_M(D�c�d�)){
				if(Existe.grandM�re_P(D�c�d�)){
					D�c�d�.m�re.m�re.personne.part = CalculPart.Biens /12;
					D�c�d�.p�re.m�re.personne.part = CalculPart.Biens /12;
					CalculPart.Reste = CalculPart.Reste - (D�c�d�.m�re.m�re.personne.part 
																+ D�c�d�.p�re.m�re.personne.part);
				}else{
					D�c�d�.m�re.m�re.personne.part = CalculPart.Biens / 6;
					CalculPart.Reste = CalculPart.Reste - D�c�d�.m�re.m�re.personne.part;
				}
			}else{
				if(Existe.grandM�re_P(D�c�d�)){
					D�c�d�.p�re.m�re.personne.part = CalculPart.Biens / 6;
					CalculPart.Reste = CalculPart.Reste - D�c�d�.p�re.m�re.personne.part;
				}
			}
		}
		if(Existe.soeur(D�c�d�)){
			Membre tampo = null;
			if(D�c�d�.p�re != null){
				tampo = D�c�d�.p�re;
			}else if(D�c�d�.m�re != null){
				tampo = D�c�d�.m�re;
			}
			int nbre_soeur = tampo.getNbreFilles_vivantes();
			int nbre_fr�re = tampo.getNbreFils_Vivants();
			if(nbre_soeur > 0){
				float part_soeur = CalculPart.Reste / (nbre_soeur + 2 * nbre_fr�re);
				for(int i = 0;i < tampo.fils.size();i ++){
					if( !(tampo.fils.get(i).personne instanceof famille.D�c�d�)){
						if(tampo.fils.get(i).personne.sxe == sexe.femelle){
							tampo.fils.get(i).personne.part = part_soeur;
						}else if(tampo.fils.get(i).personne.sxe == sexe.male){
							tampo.fils.get(i).personne.part = 2 * part_soeur;
						}
					}
				}
			}else{
				for(int i = 0;i < tampo.fils.size();i ++){
					if( !(tampo.fils.get(i).personne instanceof famille.D�c�d�)){
						tampo.fils.get(i).personne.part = CalculPart.Reste / nbre_fr�re;
					}
				}
			}
		}
		
		
	}
	public static int getNbre_filles_de_fils(Membre D�c�d�){
		int nbre = 0;
		for(int i = 0;i < D�c�d�.fils.size();i ++){
			nbre = nbre + D�c�d�.fils.get(i).getNbreFilles_vivantes();
		}
		return nbre;
	}
}
