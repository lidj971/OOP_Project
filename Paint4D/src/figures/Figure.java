package figures;


public abstract class Figure {
	abstract public void Translater(double x,double y);
	
	abstract public void Afficher();
	
	abstract public String ToString();
	
	abstract public Point getCentre();
}
