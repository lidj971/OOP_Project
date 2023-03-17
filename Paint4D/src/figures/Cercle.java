package figures;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;


public class Cercle extends Figure implements Cloneable,Serializable{
	private double rayon;
	private Point centre;
	
	public Cercle(Point centre,double rayon) {
		this.rayon = rayon;
		this.centre = centre;
	}

	public Cercle(Point p1,Point p2) {
		Segment s = new Segment(p1,p2);
		this.centre = s.getCentre();
		this.rayon = s.getLongueur() / 2;
	}
	
	@Override
	public void Translater(double x, double y) {
		centre.setX(centre.getX() + x);
		centre.setY(centre.getY() + y);
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(ToString());
	}


	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "Centre" + getCentre().ToString() + " r = " + rayon;
	}

	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		return centre;
	}
	
	@Override
	public void Paint(Graphics gc) 
	{
		this.getCentre().Paint(gc);
		gc.drawOval((int)this.getCentre().getX(),(int)this.getCentre().getY(),(int)this.getRayon(),(int)this.getRayon());
	}
	
	public boolean Equals(Cercle cercle) 
	{
		return getRayon() == cercle.getRayon() && cercle.getCentre().Equals(getCentre());
	}
	
	public Cercle clone() 
	{
		Cercle c = (Cercle)super.clone();
		c.setCentre(getCentre().clone());
		return c;
	}

	
	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon)throws NegRadiusException {
		if(rayon <= 0) 
		{
			throw new NegRadiusException(rayon);
		}
		this.rayon = rayon;
		return;
	}
	
	public void setCentre(Point p) 
	{
		centre = p;
	}
}
