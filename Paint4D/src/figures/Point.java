package figures;

public class Point extends Figure{

	private double x;
	private double y;
	
	public Point(double x,double y,String name) {
		super(name);
		this.x = x;
		this.y = y;
	}

	public Point(Point p,String name) {
		super(name);
		this.x = p.getX();
		this.y = p.getY();
	}
		
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		this.x += x;
		this.y += y;
	}

	public void Afficher() {
		
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
		
		return name + " X : " + x + "Y : " + y;
	}
	
	public Point GetCenter() 
	{
		return this;
	}
	
	public double Distance(Point p) 
	{
		return Math.sqrt(Math.pow(x, p.getX()) + Math.pow(y, p.getY()));
	}
}
