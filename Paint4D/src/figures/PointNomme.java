package figures;

public class PointNomme extends Point implements Nomme{

	private String name;
	
	public PointNomme(double x, double y,String name) {
		super(x, y);
		this.name = name;
	}
	
	public PointNomme(Point p,String name) 
	{
		super(p);
		this.name = name;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void Afficher() 
	{
		System.out.println(name + ToString());
	}
	
	@Override
	public void setNom(String name) 
	{
		this.name = name;
	}

}
