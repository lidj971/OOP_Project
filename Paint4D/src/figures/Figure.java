package figures;

public abstract class Figure {

	//protected String name;
	
	/*public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}*/

	protected Point center;
	
	abstract public void Translater(double x,double y);
	
	abstract public void Afficher(String name);
	
	abstract public String ToString();
	
	public Point getCenter() 
	{
		return center;
	}
}
