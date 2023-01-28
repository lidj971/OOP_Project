package figures;

public class SegmentNomme extends Segment implements Nomme{

	private String name;
	
	public SegmentNomme(Point p1, Point p2,String name) {
		super(p1, p2);
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
