package figures;

public class Point extends Figure{

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
	
	public void Afficher(String name) {
		System.out.println(name + ToString());
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
	
	@Override
	public Point getCenter() 
	{
		center = this;
		return center;
	}
	
	public String ToString() {
		
		return "(" + x + ", " + y + ")";
	}
	
	public double Distance(Point p) 
	{
		return Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
	}  
}
