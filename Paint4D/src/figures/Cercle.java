package figures;

public class Cercle extends Figure{
	
	private double rayon;
	
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
	public void Afficher(String name) {
		// TODO Auto-generated method stub
		System.out.println(name + " : " + ToString());
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon(double rayon) {
		if(rayon > 0) 
		{
			this.rayon = rayon;
			return;
		}
		System.out.println(ConsoleColors.RED + ConsoleColors.YELLOW_BACKGROUND +"Erreur : rayon <= 0" + ConsoleColors.RESET);
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return "Centre" + getCentre().ToString() + " r = " + rayon;
	}

}
