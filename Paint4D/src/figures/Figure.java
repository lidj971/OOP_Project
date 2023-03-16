package figures;

import java.awt.Graphics;

public abstract class Figure implements Cloneable {
	
    abstract public void Translater(double x,double y);
	
	abstract public void Afficher();
	
	abstract public String ToString();
	
	abstract public Point getCentre();
	
	abstract public void Paint(Graphics gc);
	
	public Figure clone() 
	{
		Figure f = null;
		try { f = (Figure)super.clone();}
		catch(CloneNotSupportedException e){}
		return f;
	}
}
