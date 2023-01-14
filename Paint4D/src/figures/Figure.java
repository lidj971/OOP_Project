package figures;

public abstract class Figure {

	protected String name;
	
	public Figure(String name) 
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract void Translater(double x,double y);
	
	public abstract void Afficher();
	
	public abstract String ToString();
	
	public abstract Point GetCenter();
}
