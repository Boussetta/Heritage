package Calcul;

import famille.Membre;

public class Existe {
	public static boolean fils(Membre Décédé){		
		boolean result = false;		
		if(Décédé.getNbreFils_Vivants() > 0){
			result = true;
		}		
		return result;
	}
	public static boolean fils_de_fils(Membre Décédé){
		boolean result = false;
		int nbreFils = Décédé.getNbreFils();
		if( (nbreFils > 0) && (!Existe.fils(Décédé)) ){
			int i = 0;
			while( (i < nbreFils) && (result == false) ){
				if(Décédé.fils.get(i).getNbreFils_Vivants() > 0){
					result = true;
				}
				i++;
			}
		}
		return result;
	}
	public static boolean filles(Membre Décédé){
		boolean result = false;
		if(Décédé.getNbreFilles_vivantes() > 0){
			result = true;
		}
		return result;
	}
	public static boolean filles_de_fils(Membre Décédé){
		boolean result = false;
		int nbreFils = Décédé.fils.size();
		if( nbreFils > 0 ){
			int i = 0;
			while( (i < nbreFils) && (result == false) ){
				if(Décédé.fils.get(i).getNbreFilles_vivantes() > 0){
					result = true;
				}
				i ++;
			}
		}
		return result;
	}
	
	public static boolean père(Membre Décédé){
		boolean result = false;
		if(Décédé.père != null){
			if(!(Décédé.père.personne instanceof famille.Décédé)){
				result = true;
			}
		}
		return result;
	}
	public static boolean grandPère(Membre Décédé){
		boolean result = false;
		if(Décédé.père != null){
			if((Décédé.père.personne instanceof famille.Décédé)){
				if(Décédé.père.père != null){
					if( !(Décédé.père.père.personne instanceof famille.Décédé) ){
						result = true;
					}
				}
			}
		}
		return result;
	}
	public static boolean epoux(Membre Décédé){
		boolean result = false;
		if(Décédé.conjoint != null){
			if( !(Décédé.conjoint.personne instanceof famille.Décédé)){
				result = true;
			}
		}
		return result;
	}
	public static boolean mère(Membre Décédé){
		boolean result = false;
		if(Décédé.mère != null){
			if( !(Décédé.mère.personne instanceof famille.Décédé)){
				result = true;
			}
		}
		return result;
	}
	public static boolean grandMère_M(Membre Décédé){
		boolean result = false;
		if(Décédé.mère != null){
			if(Décédé.mère.mère != null){
				if( !(Décédé.mère.mère.personne instanceof famille.Décédé)){
					result = true;
				}
			}
		}		
		return result;
	}
	public static boolean grandMère_P(Membre Décédé){
		boolean result = false;
		if(Décédé.père != null){
			if(Décédé.père.mère != null){
				if( !(Décédé.père.mère.personne instanceof famille.Décédé)){
					result = true;
				}
			}
		}		
		return result;
	}
	public static boolean frère(Membre Décédé){
		boolean result = false;
		if(Décédé.père != null){
			if(Décédé.père.getNbreFils_Vivants() > 0){
				result = true;
			}
		}else if(Décédé.mère != null){
			if(Décédé.mère.getNbreFils_Vivants() > 0){
				result = true;
			}
		}
		return result;
	}
	public static boolean soeur(Membre Décédé){
		boolean result = false;
		if(Décédé.père != null){
			if(Décédé.père.getNbreFilles_vivantes() > 0){
				result = true;
			}
		}else if(Décédé.mère != null){
			if(Décédé.mère.getNbreFilles_vivantes() > 0){
				result = true;
			}
		}
		return result;
	}
	
}
