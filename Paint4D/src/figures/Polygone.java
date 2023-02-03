package figures;
import java.util.ArrayList;

public class Polygone extends Figure{

	private ArrayList<Point> pList = new ArrayList<Point>();
	
	public Polygone(ArrayList<Point> pList) 
	{
		this.pList.addAll(pList);
	}
	
	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		for(Point p:pList) 
		{
			p.Translater(x, y);
		}
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.ToString());
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		String res = "{ ";
		for(Point p:pList) 
		{
			res.concat(p.ToString());
		}
		res.concat(" }");
		return res;
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Polygone clone() 
	{
		Polygone p = (Polygone)super.clone();
		return p;
	}
	
	public boolean add(Point p) 
	{
		pList.add(p);
		return true;
	}

}
