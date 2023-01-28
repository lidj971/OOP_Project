package figures;

import java.io.Serializable;

public class Point extends Figure implements Cloneable,Serializable{

	private double x;
	private double y;
	
	public Point(double x,double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this.x = p.getX();
		this.y = p.getY();
	}
		
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		this.x += x;
		this.y += y;
	}
	
	public void Afficher() {
		System.out.println(ToString());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public String ToString() {
		
		return "(" + x + ", " + y + ")";
	}
	
	public double Distance(Point p) 
	{
		return Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		return this;
	}

	public boolean Equals(Point point) {
		// TODO Auto-generated method stub
		return (point.getX() == this.getX() && point.getY() == this.getY());
	} 
	
	public Point clone() 
	{
		Point p = null;
		try { p = (Point)super.clone();}
		catch(CloneNotSupportedException e){}
		return p;
	}
}
