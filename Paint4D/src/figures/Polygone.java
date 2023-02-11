package figures;
import java.util.ArrayList;

public class Polygone extends Figure{

	private ArrayList<Point> pList = new ArrayList<Point>();
	
	public Polygone(ArrayList<Point> pList) 
	{
		this.pList.addAll(pList);
	}
	
	public ArrayList<Point> getPList() 
	{
		return pList;
	}
	
	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		for(Point p:getPList()) 
		{
			p.Translater(x, y);
		}
		Afficher();
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(this.ToString());
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		String res = "{";
		for(Point p:getPList()) 
		{
			res = res.concat(p.ToString() + " ");
		}
		res = res.concat("}");
		return res;
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		float x = 0,y = 0;
		for(Point p:getPList()) 
		{
			x+=p.getX();
			y+=p.getY();
		}
		return new Point(x/getPList().size(),y/getPList().size());
	}
	
	public Polygone clone() 
	{
		Polygone pClone = (Polygone)super.clone();
		for(Point p:pClone.getPList()) 
		{
		    //do something
		}
		return pClone;
	}
	
	public boolean add(Point p) 
	{
		pList.add(p);
		return true;
	}
	
	public boolean Equals(Polygone poly) 
	{
		if(getPList().size() != poly.getPList().size()) 
		{
			return false;
		}
		ArrayList<Point> targetPList = new ArrayList<Point>();
		targetPList.addAll(poly.getPList());
		int i = targetPList.size();
		for(Point p:getPList()) 
		{
			for(Point p2:targetPList) 
			{
				if(p.Equals(p2)) 
				{
					targetPList.remove(p2);
					break;
				}
			}
			if(targetPList.size() == i) 
			{
				return false;
			}
			i--;
		}
		return true;
	}
}
