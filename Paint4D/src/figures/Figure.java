package figures;

    public abstract class Figure implements Cloneable {
	
    abstract public void Translater(double x,double y);
	
	abstract public void Afficher();
	
	abstract public String ToString();
	
	abstract public Point getCentre();
	
	public Figure clone() 
	{
		Figure f = null;
		try { f = (Figure)super.clone();}
		catch(CloneNotSupportedException e){}
		return f;
	}
}
