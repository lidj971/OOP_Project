package td3;

public class MDPTaille extends Exception {
	public int taille;
	public MDPTaille(String mdp) 
	{
		this.taille = mdp.length();
		System.out.println("Votre mot de passe fait " + taille + " caracteres");
		System.out.println("Votre mot de passe doit faire entre 8 et 30 caracters");
	}
}
