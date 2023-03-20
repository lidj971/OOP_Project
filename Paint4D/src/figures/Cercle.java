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
		Color currentColor = gc.getColor();
		if(isSelected()) 
		{
			gc.setColor(new Color(0, 102, 255));
		}
		gc.drawOval((int)(this.getCentre().getX() - (this.rayon/2)),(int)(this.getCentre().getY() - (this.rayon/2)),(int)this.getRayon(),(int)this.getRayon());
		gc.setColor(currentColor);
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

	public void setRayon(double rayon) throws NegRadiusException {
		if(rayon < 0) 
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

	@Override
	public Point CloseTo(Point p) {
		// TODO Auto-generated method stub
		if(p.Distance(this.getCentre()) <= threshold) 
		{
			return getCentre();
		}
		return null;
	}

	@Override
	public boolean IsInside(Rectangle rect) {
		// TODO Auto-generated method stub
		return centre.IsInside(rect);
	}
	
	@Override
	public void setSelected(boolean selected) 
	{
		super.setSelected(selected);
		centre.setSelected(selected);
	}
	
}
