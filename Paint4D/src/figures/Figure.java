package figures;

public abstract class Figure {

	protected Point centre;
	
	abstract public void Translater(double x,double y);
	
	abstract public void Afficher(String name);
	
	abstract public String ToString();
	
	public Point getCentre() 
	{
		return centre;
	}
}
