package figures;

public class Segment extends Figure{
	
	private Point p1,p2;
	public Segment(Point p1,Point p2) {
		// TODO Auto-generated constructor stub
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getP1() {
		return p1;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	
	public Point getP2() {
		return p2;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	@Override
	public void Translater(double x, double y) {
		// TODO Auto-generated method stub
		getP1().Translater(x,y);
		getP2().Translater(x,y);
	}

	@Override
	public void Afficher() {
		// TODO Auto-generated method stub
		System.out.println(ToString());
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "[" + getP1().ToString() + ", " + getP2().ToString() + "]";
	}
	
	@Override
	public Point getCentre() 
	{
		return new Point((getP1().getX() + getP2().getX()) / 2,(getP1().getY() + getP2().getY()) / 2);
	}
	
	public double getLongueur() 
	{
		return getP1().Distance(getP2());
	}
	
	public boolean Equals(Segment segment) 
	{
		return (segment.getP1().equals(getP1()) && segment.getP2().equals(getP2())) || (segment.getP1().equals(getP2()) && segment.getP2().equals(getP1()));
	}
}
