package figures;

import java.awt.Graphics;

public abstract class Figure implements Cloneable {
	
    protected static double threshold = 3;
    
    private boolean selected = false;
	
	abstract public void Translater(double x,double y);
	
	abstract public void Afficher();
	
	abstract public String ToString();
	
	abstract public Point getCentre();
	
	abstract public void Paint(Graphics gc);
	
	public abstract Point CloseTo(Point p);
	
	public abstract boolean IsInside(Rectangle rect);
	
	public Figure clone() 
	{
		Figure f = null;
		try { f = (Figure)super.clone();}
		catch(CloneNotSupportedException e){}
		return f;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
