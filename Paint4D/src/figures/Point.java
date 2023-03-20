package figures;

import java.awt.Color;
import java.awt.Graphics;
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
	
	@Override
	public void Paint(Graphics gc) 
	{
		int taille = 7;
		
		//Option Croix
		//gc.drawLine((int)getX() - taille, (int)getY(), (int)getX() + taille, (int)getY());
		//gc.drawLine((int)getX(), (int)getY() - taille, (int)getX(), (int)getY() + taille);
		
		//Option Cercle
		Color currentColor = gc.getColor();
		if(isSelected()) 
		{
			gc.setColor(new Color(0, 102, 255));
		}
		gc.fillOval((int)(this.getX() - (taille/2)),(int)(this.getY() - (taille/2)),taille,taille);
		gc.setColor(currentColor);
	}

	public boolean Equals(Point point) {
		// TODO Auto-generated method stub
		return (point.getX() == this.getX() && point.getY() == this.getY());
	} 
	
	public Point clone() 
	{
		Point p = (Point)super.clone();
		return p;
	}

	@Override
	public Point CloseTo(Point p) {
		// TODO Auto-generated method stub
		if(this.Distance(p) < threshold) 
		{
			this.setX(p.getX());
			this.setY(p.getY());
			return this;
		}
		return null;
	}
	
	
	@Override
	public boolean IsInside(Rectangle rect) {
		// TODO Auto-generated method stub
		Point UL = rect.getUpLeftCorner();
		Point DR = rect.getDownRightCorner();
		return getX() >= UL.getX() && getY() >= UL.getY() && getX() <= DR.getX() && getY() <= DR.getY();
	}
}

