package figures;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Segment extends Figure implements Cloneable,Serializable{
	
	private Point p1,p2;
	public Segment(Point p1,Point p2) {
		// TODO Auto-generated constructor stub
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		getP1().Translater(x,y);
		getP2().Translater(x,y);
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(ToString());
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "[" + getP1().ToString() + ", " + getP2().ToString() + "]";
	}
	
	@Override
	public Point getCentre() 
	{
		return new Point((getP1().getX() + getP2().getX()) / 2,(getP1().getY() + getP2().getY()) / 2);
	}
	
	@Override
	public void Paint(Graphics gc) 
	{
		this.p1.Paint(gc);
		this.p2.Paint(gc);
		Color currentColor = gc.getColor();
		if(isSelected()) 
		{
			gc.setColor(new Color(0, 102, 255));
		}
		gc.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
		gc.setColor(currentColor);
	}
	
	public double getLongueur() 
	{
		return getP1().Distance(getP2());
	}
	
	
	public boolean Equals(Segment segment) 
	{
		return (segment.getP1().Equals(getP1()) && segment.getP2().Equals(getP2())) || (segment.getP1().Equals(getP2()) && segment.getP2().Equals(getP1()));
	}
	
	@Override
	public Segment clone() 
	{
		Segment s = (Segment)super.clone();
		s.setP1(getP1().clone());
		s.setP2(getP2().clone());
		System.out.println("segClone");
		return s;
	}

	
	public boolean isTouching(Point p3) 
	{
		Segment s2 = new Segment(this.getP1(),p3);
		double x = (p3.getX() - this.getP1().getX()) / (this.getP2().getX() - this.getP1().getX());
		double y = (p3.getY() - this.getP1().getY()) / (this.getP2().getY() - this.getP1().getY());
		return (x == y && x >= 0 && x <= 1);
	}
		


	@Override
	public Point CloseTo(Point p) {
		// TODO Auto-generated method stub
		Point closestPoint = p1.CloseTo(p);
		if(closestPoint != null) 
		{
			return closestPoint;
		}
		closestPoint = p2.CloseTo(p);
		return closestPoint;

	}

	@Override
	public boolean IsInside(Rectangle rect) {
		// TODO Auto-generated method stub
		return p1.IsInside(rect) && p2.IsInside(rect);
	}
	
	@Override
	public void setSelected(boolean selected) 
	{
		super.setSelected(selected);
		p1.setSelected(selected);
		p2.setSelected(selected);
	}

}
