package famille;
public class Personne {
	public enum sexe {male,femelle}
	public String nom;
	public String prenom;
	public sexe sxe;
	public float part = 0;
	public Personne(sexe sexe){
		this.sxe = sexe;		
	}
	public Personne(sexe sexe,String nom,String prenom){
		this.sxe = sexe;
		this.nom = nom;
		this.prenom = prenom;
	}
	public Personne(Personne personne){
		this.sxe = personne.sxe;
		this.nom = personne.nom;
		this.prenom = personne.prenom;
	}
}
