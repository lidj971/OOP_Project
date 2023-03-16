package td3;

import java.util.Scanner;

public class MDP {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{
			SaisirMDP();
		}catch(Exception e) 
		{
			main(new String[1]);
		}
		
		System.out.println("MDP Valide");
	}
	
	public static String SaisirMDP() throws MDPVide,MDPTaille,MDPForce
	{
		System.out.println("Veuillez saisir un mot de passe");
		String mdp = new Scanner(System.in).next();
		
		if(mdp.length() == 0) 
		{
			throw new MDPVide();
		}
		
		if(mdp.length() < 8 || mdp.length() > 30) 
		{
			throw new MDPTaille(mdp);
		}
		
		if(mdp.matches("[a-zA-Z]*")) 
		{
			throw new MDPForce();
		}
		
		return mdp;
	}
}
