package Calcul;

import famille.Membre;

public class Existe {
	public static boolean fils(Membre D�c�d�){		
		boolean result = false;		
		if(D�c�d�.getNbreFils_Vivants() > 0){
			result = true;
		}		
		return result;
	}
	public static boolean fils_de_fils(Membre D�c�d�){
		boolean result = false;
		int nbreFils = D�c�d�.getNbreFils();
		if( (nbreFils > 0) && (!Existe.fils(D�c�d�)) ){
			int i = 0;
			while( (i < nbreFils) && (result == false) ){
				if(D�c�d�.fils.get(i).getNbreFils_Vivants() > 0){
					result = true;
				}
				i++;
			}
		}
		return result;
	}
	public static boolean filles(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.getNbreFilles_vivantes() > 0){
			result = true;
		}
		return result;
	}
	public static boolean filles_de_fils(Membre D�c�d�){
		boolean result = false;
		int nbreFils = D�c�d�.fils.size();
		if( nbreFils > 0 ){
			int i = 0;
			while( (i < nbreFils) && (result == false) ){
				if(D�c�d�.fils.get(i).getNbreFilles_vivantes() > 0){
					result = true;
				}
				i ++;
			}
		}
		return result;
	}
	
	public static boolean p�re(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.p�re != null){
			if(!(D�c�d�.p�re.personne instanceof famille.D�c�d�)){
				result = true;
			}
		}
		return result;
	}
	public static boolean grandP�re(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.p�re != null){
			if((D�c�d�.p�re.personne instanceof famille.D�c�d�)){
				if(D�c�d�.p�re.p�re != null){
					if( !(D�c�d�.p�re.p�re.personne instanceof famille.D�c�d�) ){
						result = true;
					}
				}
			}
		}
		return result;
	}
	public static boolean epoux(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.conjoint != null){
			if( !(D�c�d�.conjoint.personne instanceof famille.D�c�d�)){
				result = true;
			}
		}
		return result;
	}
	public static boolean m�re(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.m�re != null){
			if( !(D�c�d�.m�re.personne instanceof famille.D�c�d�)){
				result = true;
			}
		}
		return result;
	}
	public static boolean grandM�re_M(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.m�re != null){
			if(D�c�d�.m�re.m�re != null){
				if( !(D�c�d�.m�re.m�re.personne instanceof famille.D�c�d�)){
					result = true;
				}
			}
		}		
		return result;
	}
	public static boolean grandM�re_P(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.p�re != null){
			if(D�c�d�.p�re.m�re != null){
				if( !(D�c�d�.p�re.m�re.personne instanceof famille.D�c�d�)){
					result = true;
				}
			}
		}		
		return result;
	}
	public static boolean fr�re(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.p�re != null){
			if(D�c�d�.p�re.getNbreFils_Vivants() > 0){
				result = true;
			}
		}else if(D�c�d�.m�re != null){
			if(D�c�d�.m�re.getNbreFils_Vivants() > 0){
				result = true;
			}
		}
		return result;
	}
	public static boolean soeur(Membre D�c�d�){
		boolean result = false;
		if(D�c�d�.p�re != null){
			if(D�c�d�.p�re.getNbreFilles_vivantes() > 0){
				result = true;
			}
		}else if(D�c�d�.m�re != null){
			if(D�c�d�.m�re.getNbreFilles_vivantes() > 0){
				result = true;
			}
		}
		return result;
	}
	
}
