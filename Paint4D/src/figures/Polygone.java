package figures;
import java.util.ArrayList;
import java.awt.Graphics;

public class Polygone extends Figure{

	private ArrayList<Point> sommets;
	
	public Polygone() 
	{
		sommets = new ArrayList<Point>();;
	}
	
	public ArrayList<Point> getSommets() 
	{
		return sommets;
	}
	
	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		for(Point p:getSommets()) 
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
		for(Point p:getSommets()) 
		{
			res += p.ToString() + " ";
		}
		res += "}";
		return res;
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		if(getSommets().size() == 0) return null;
		
		double x = 0,y = 0;
		for(Point p:getSommets()) 
		{
			x+=p.getX();
			y+=p.getY();
		}
		int size = getSommets().size();
		return new Point(x/size,y/size);
	}
	
	public Polygone clone() 
	{
		Polygone pClone = (Polygone)super.clone();
		for(int i = 0;i < getSommets().size();i++) 
		{
		    pClone.getSommets().set(i,getSommets().get(i).clone());
		}
		return pClone;
	}
	
	public boolean add(Point p) 
	{
		return this.sommets.add(p);
	}
	
	public boolean Equals(Polygone poly) 
	{
		int n = this.getSommets().size();
		if(n != poly.getSommets().size()) 
		{
			return false;
		}
		Point p1 = this.getSommets().get(0);
		Point p2 = poly.getSommets().get(0);
		int i2 = 0;
		while(!(i2 < n && p1.Equals(p2))) 
		{
			p2 = poly.getSommets().get(++i2);
		}
		if(i2 == n)return false;
		int i1 = 0;
		p1 = this.getSommets().get(++i1);
		p2 = this.getSommets().get((i2+1)%n);
		if(p1.Equals(p2)) 
		{
			while(i1 < n) 
			{
				p1 = this.getSommets().get((i1+1)%n);
				p2 = poly.getSommets().get((i2+1)%n);
				if(!p1.Equals(p2))return false;
			}
			return true;
		}else 
		{
			p2 = poly.getSommets().get((n+i2-1)%n);
			if(!p1.Equals(p2)) 
			{
				return false;
			}
			while(i1 < n)
			{
				p1 = this.getSommets().get((i1++)%n);
				p2 = poly.getSommets().get((i2-- + n)%n);
				if(!p1.equals(p2)) 
				{
					return false;
				}
				
			}
		}
		return true;
	}
	
	public void Paint(Graphics gc) 
	{
		int n = sommets.size();
		Point p2 = sommets.get(0);
		for(int i = 0;i < sommets.size();i++) 
		{
			Point p1 = p2;
			p2 = sommets.get((i+1)%n);
			gc.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
		}
	}
}
