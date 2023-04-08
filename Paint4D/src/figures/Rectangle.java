package figures;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;

public class Rectangle extends Figure implements Cloneable,Serializable{

	private Point A;
	private Point B;
	private Point C;
	private Point D;
	private boolean poser;
	
	public Rectangle(Point p) 
	{
		A = new Point(p);
		B = new Point(p);
		C = new Point(p);
		D = new Point(p);
	}
	
	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		A.Translater(x, y);
		B.Translater(x, y);
		C.Translater(x, y);
		D.Translater(x, y);
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(ToString());
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "A : " + A.ToString() + " B : "  + B.ToString() + " C : " + C.ToString() + " D : " + D.ToString();
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		return new Point(new Segment(A,B).getCentre().getX(),new Segment(A,C).getCentre().getY());
	}

	@Override
	public void Paint(Graphics gc) {
		// TODO Auto-generated method stub
		Color currentColor = gc.getColor();
		Color selectColor;
		if(poser) 
		{
			selectColor = new Color(7, 227, 40);
		}else 
		{
			selectColor = new Color(0, 102, 255);
		}
		gc.setColor(selectColor);
		gc.drawLine((int)A.getX(), (int)A.getY(), (int)B.getX(), (int)B.getY());
		gc.drawLine((int)A.getX(), (int)A.getY(), (int)D.getX(), (int)D.getY());
		gc.drawLine((int)C.getX(), (int)C.getY(), (int)B.getX(), (int)B.getY());
		gc.drawLine((int)C.getX(), (int)C.getY(), (int)D.getX(), (int)D.getY());
		gc.setColor(new Color(selectColor.getRed(),selectColor.getGreen(),selectColor.getBlue(),50));
		gc.fillRect((int)getUpLeftCorner().getX(), (int)getUpLeftCorner().getY(), (int)A.Distance(B), (int)A.Distance(D));
		gc.setColor(currentColor);
	}

	@Override
	public Point CloseTo(Point p) {
		// TODO Auto-generated method stub
		Point closestPoint = A.CloseTo(p);
		if(B.CloseTo(p) != null)
			closestPoint = B.CloseTo(p);
		if(C.CloseTo(p) != null)
			closestPoint = C.CloseTo(p);
		if(D.CloseTo(p) != null)
			closestPoint = D.CloseTo(p);
		return closestPoint;
	}

	@Override
	public Rectangle clone() 
	{
		Rectangle r = (Rectangle)super.clone();
		r.setA(getA().clone());
		r.setB(getB().clone());
		r.setC(getC().clone());
		r.setD(getD().clone());
		return r;
		
	}
	
	public void Resize(Point p) 
	{
		B.setX(p.getX());
		D.setY(p.getY());
		C.setX(p.getX());
		C.setY(p.getY());
	}
	
	public Point getUpLeftCorner() 
	{
		if(C.getY() < A.getY() && C.getX() < A.getX()) 
		{
			return C;
		}else if(D.getY() < A.getY()) 
		{
			return D;
		}else if(B.getX() < A.getX()) 
		{
			return B;
		}
		return A;
	}
	
	public Point getDownRightCorner() 
	{
		if(C.getY() < A.getY() && C.getX() < A.getX()) 
		{
			return A;
		}else if(D.getY() < A.getY()) 
		{
			return B;
		}else if(B.getX() < A.getX()) 
		{
			return D;
		}
		return C;
	}
	
	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}

	public Point getC() {
		return C;
	}

	public void setC(Point c) {
		C = c;
	}

	public Point getD() {
		return D;
	}

	public void setD(Point d) {
		D = d;
	}

	@Override
	public boolean IsInside(Rectangle rect) {
		// TODO Auto-generated method stub
		return A.IsInside(rect) && B.IsInside(rect) && C.IsInside(rect) && D.IsInside(rect);
	}

	public boolean isPoser() {
		return poser;
	}

	public void setPoser(boolean poser) {
		this.poser = poser;
	}
	
}
