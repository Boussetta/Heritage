package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class frèreExiste {
	/*
	 * 		Dans un tel cas les bénéficiaires de résidu sont les frères et les soeurs
	 */
	public static void exécuterTraitement(Membre Décédé){
		if(Existe.filles(Décédé)){
			int nbre_filles = Décédé.getNbreFilles_vivantes();
			float part_filles = 0;
			if(nbre_filles >= 2){
				part_filles = (2 * CalculPart.Biens) / 3;
			}else{
				part_filles = CalculPart.Biens / 2;
				if(Existe.filles_de_fils(Décédé)){
					float part_Filles_de_Fils = CalculPart.Biens / 6;
					for(int i = 0;i < Décédé.fils.size();i ++){
						for(int j = 0;j < Décédé.fils.get(i).fils.size();j ++){
							if( (Décédé.fils.get(i).fils.get(j).personne.sxe == sexe.femelle) && !(Décédé.fils.get(i).fils.get(j).personne instanceof famille.Décédé) ){
								Décédé.fils.get(i).fils.get(j).personne.part = part_Filles_de_Fils / getNbre_filles_de_fils(Décédé);
							}
						}
					}
					CalculPart.Reste = CalculPart.Reste - part_Filles_de_Fils;	
				}
			}
			for(int i = 0;i < Décédé.fils.size();i ++){
				if( (Décédé.fils.get(i).personne.sxe == sexe.femelle) && !(Décédé.fils.get(i).personne instanceof famille.Décédé) ){
					Décédé.fils.get(i).personne.part = part_filles / nbre_filles;
				}
			}
			CalculPart.Reste = CalculPart.Reste - part_filles;			
		}else{
			if(Existe.filles_de_fils(Décédé)){
				float part_filles_de_fils = 0;
				if(Existe.epoux(Décédé)){
					float part_conjoint = 0;
					if(Décédé.conjoint.personne.sxe == sexe.male){
						part_conjoint = CalculPart.Biens / 4;
					}else{
						part_conjoint = CalculPart.Biens / 8;
					}
					Décédé.conjoint.personne.part = part_conjoint;
					CalculPart.Reste = CalculPart.Reste - part_conjoint;
				}
				if(getNbre_filles_de_fils(Décédé) >= 2){
					part_filles_de_fils = (2 * CalculPart.Biens) / 3;
				}else{
					part_filles_de_fils = CalculPart.Biens / 2;
				}
				for(int i = 0;i < Décédé.fils.size();i ++){
					for(int j = 0;j < Décédé.fils.get(i).fils.size();j++){
						if( (Décédé.fils.get(i).fils.get(j).personne.sxe == sexe.femelle) && !(Décédé.fils.get(i).fils.get(j).personne instanceof famille.Décédé) ){
							Décédé.fils.get(i).fils.get(j).personne.part = part_filles_de_fils / getNbre_filles_de_fils(Décédé);
						}
					}
				}
				CalculPart.Reste = CalculPart.Reste - part_filles_de_fils;
			}else{
				if(Existe.epoux(Décédé)){
					Décédé.conjoint.personne.part = CalculPart.Biens / 2;
				}
			}
		}
		if(Existe.mère(Décédé)){
			Décédé.mère.personne.part = CalculPart.Biens / 6;
			CalculPart.Reste = CalculPart.Reste - Décédé.mère.personne.part;
		}else{
			if(Existe.grandMère_M(Décédé)){
				if(Existe.grandMère_P(Décédé)){
					Décédé.mère.mère.personne.part = CalculPart.Biens /12;
					Décédé.père.mère.personne.part = CalculPart.Biens /12;
					CalculPart.Reste = CalculPart.Reste - (Décédé.mère.mère.personne.part 
																+ Décédé.père.mère.personne.part);
				}else{
					Décédé.mère.mère.personne.part = CalculPart.Biens / 6;
					CalculPart.Reste = CalculPart.Reste - Décédé.mère.mère.personne.part;
				}
			}else{
				if(Existe.grandMère_P(Décédé)){
					Décédé.père.mère.personne.part = CalculPart.Biens / 6;
					CalculPart.Reste = CalculPart.Reste - Décédé.père.mère.personne.part;
				}
			}
		}
		if(Existe.soeur(Décédé)){
			Membre tampo = null;
			if(Décédé.père != null){
				tampo = Décédé.père;
			}else if(Décédé.mère != null){
				tampo = Décédé.mère;
			}
			int nbre_soeur = tampo.getNbreFilles_vivantes();
			int nbre_frère = tampo.getNbreFils_Vivants();
			if(nbre_soeur > 0){
				float part_soeur = CalculPart.Reste / (nbre_soeur + 2 * nbre_frère);
				for(int i = 0;i < tampo.fils.size();i ++){
					if( !(tampo.fils.get(i).personne instanceof famille.Décédé)){
						if(tampo.fils.get(i).personne.sxe == sexe.femelle){
							tampo.fils.get(i).personne.part = part_soeur;
						}else if(tampo.fils.get(i).personne.sxe == sexe.male){
							tampo.fils.get(i).personne.part = 2 * part_soeur;
						}
					}
				}
			}else{
				for(int i = 0;i < tampo.fils.size();i ++){
					if( !(tampo.fils.get(i).personne instanceof famille.Décédé)){
						tampo.fils.get(i).personne.part = CalculPart.Reste / nbre_frère;
					}
				}
			}
		}
		
		
	}
	public static int getNbre_filles_de_fils(Membre Décédé){
		int nbre = 0;
		for(int i = 0;i < Décédé.fils.size();i ++){
			nbre = nbre + Décédé.fils.get(i).getNbreFilles_vivantes();
		}
		return nbre;
	}
}
