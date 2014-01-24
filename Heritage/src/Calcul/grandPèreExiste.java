package Calcul;

import famille.Membre;
import famille.Personne.sexe;

public class grandPèreExiste {
	public static void exécuterTraitement(Membre Décédé){
		if(Décédé.mère != null){
			if( !(Décédé.mère.personne instanceof famille.Décédé) ){
				Décédé.mère.personne.part =  CalculPart.Biens / 6;
				CalculPart.Reste = CalculPart.Reste - Décédé.mère.personne.part;
			}else{
				if(Décédé.mère.mère != null){
					if( !(Décédé.mère.mère.personne instanceof famille.Décédé)){
						if(Décédé.père.mère != null){
							if( !(Décédé.père.mère.personne instanceof famille.Décédé)){
								Décédé.père.mère.personne.part = CalculPart.Biens / 12;
								CalculPart.Reste = CalculPart.Reste - Décédé.père.mère.personne.part;
								Décédé.mère.mère.personne.part = CalculPart.Biens / 12;
								CalculPart.Reste = CalculPart.Reste - Décédé.mère.mère.personne.part;
							}
						}
						Décédé.mère.mère.personne.part = CalculPart.Biens / 6;
						CalculPart.Reste = CalculPart.Reste - Décédé.mère.mère.personne.part;
					}
				}else{
					if(Décédé.père.mère != null){
						if( !(Décédé.père.mère.personne instanceof famille.Décédé)){
							Décédé.père.mère.personne.part = CalculPart.Biens / 6;
							CalculPart.Reste = CalculPart.Reste - Décédé.père.mère.personne.part;
						}
					}
				}
			}
		}
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
				int nbre_filles_de_fils = getNbre_filles_de_fils(Décédé);
				float part_filles_de_fils = 0;
				if(nbre_filles_de_fils >= 2){
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
			}
		}
		Décédé.père.père.personne.part = CalculPart.Reste;
	}
	public static int getNbre_filles_de_fils(Membre Décédé){
		int nbre = 0;
		for(int i = 0;i < Décédé.fils.size();i ++){
			nbre = nbre + Décédé.fils.get(i).getNbreFilles_vivantes();
		}
		return nbre;
	}
}
