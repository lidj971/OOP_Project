package figures;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Polygone extends Figure{

	private ArrayList<Point> sommets;
	
	public Polygone() 
	{
		this.sommets = new ArrayList<Point>();;
	}
	
	public ArrayList<Point> getSommets() 
	{
		return this.sommets;
	}
	
	public void setSommets(ArrayList<Point> sommets)
	{
		this.sommets.clear();
		this.sommets.addAll(sommets);
	}
	
	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		for(Point p:this.getSommets()) 
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
	
	@Override
	public void Paint(Graphics gc) 
	{
		if(getSommets().size() == 0) return;
		
		Color currentColor = gc.getColor();		
		int n = sommets.size();
		Point p2 = sommets.get(0);
		for(int i = 0;i < sommets.size();i++) 
		{
			sommets.get(i).Paint(gc);
			Point p1 = p2;
			p2 = sommets.get((i+1)%n);
			if(isSelected()) 
			{
				gc.setColor(new Color(0, 102, 255));
			}
			gc.drawLine((int)p1.getX(),(int)p1.getY(),(int)p2.getX(),(int)p2.getY());
		}
		
		gc.setColor(currentColor);
	}
	
	@Override
	public Polygone clone() 
	{
		Polygone pClone = (Polygone)super.clone();
		pClone.sommets = new ArrayList<Point>();
		for(Point p:this.getSommets()) 
		{
			pClone.add(p.clone());
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
		while(i2 < n && !p1.Equals(p2)) 
		{
			p2 = poly.getSommets().get(++i2);
		}
		if(i2 == n)return false;
		int i1 = 1;
		p1 = this.getSommets().get(i1);
		p2 = this.getSommets().get((++i2)%n);
		if(p1.Equals(p2)) 
		{
			
			while(i1 < n) 
			{
				p1 = this.getSommets().get((++i1)%n);
				p2 = poly.getSommets().get((++i2)%n);
				if(!p1.Equals(p2)) 
				{
					return false;
				}
			}
			
			return true;
		}else 
		{
			i2 -= 2;
			p2 = poly.getSommets().get((n + i2)%n);
			
			if(!p1.Equals(p2)) 
			{
				return false;
			}
			while(i1 < n)
			{
				p1 = this.getSommets().get((++i1)%n);
				p2 = poly.getSommets().get((--i2 + n)%n);
				if(!p1.equals(p2)) 
				{
					return false;
				}
				
			}
		}
		return true;
	}

	@Override
	public Point CloseTo(Point p) {
		// TODO Auto-generated method stub
		if(getSommets().size() == 0) return null;
		int i = 0;
		Point closestPoint = this.getSommets().get(i++).CloseTo(p);
		while(closestPoint == null && i < this.getSommets().size()) 
		{
			closestPoint = this.getSommets().get(i++).CloseTo(p);
		}
		return closestPoint;
	}

	@Override
	public boolean IsInside(Rectangle rect) {
		// TODO Auto-generated method stub
		for(Point p:sommets) 
		{
			if(!p.IsInside(rect)) 
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public void setSelected(boolean selected) 
	{
		super.setSelected(selected);
		for(Point p:sommets) 
		{
			p.setSelected(selected);
		}
	}
}
