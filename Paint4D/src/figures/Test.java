package figures;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testCercle();
	}
	
	public static void testPoint() 
	{
		Point p1 = new Point(0,0);
		p1.Afficher("p1");
		p1.Translater(3,1);
		p1.Afficher("p1");
		Point p2 = new Point(-2,-6);
		System.out.println("Distance(p1,p2) = " + p1.Distance(p2));
		Point p3 = new Point(p2);
		p2.Afficher("p2");
		p3.Afficher("p3");
		System.out.println("Distance(p2,p3) = " + p2.Distance(p3));
	}
	
	public static void testSegment() 
	{
		Segment s1 = new Segment(new Point(0,0),new Point(3,3));
		s1.Afficher("s1");
		System.out.println("Longueur : " + s1.getLongueur());
		s1.getCentre().Afficher("Centre");
		s1.Translater(-5, -1);
		s1.Afficher("s1");
		System.out.println("Longueur : " + s1.getLongueur());
		s1.getCentre().Afficher("Centre");
	}
	
	public static void testCercle() 
	{
		Cercle c1 = new Cercle(new Point(0,0), 5);
		c1.Afficher("C1");
		c1.Translater(2, 2);
		c1.Afficher("C1");
		Cercle c2 = new Cercle(new Point(-2,2),new Point(5,-6));
		c2.Afficher("C2");
		c2.setRayon(2);
		c2.Afficher("C2");
		c2.setRayon(-2);
		c2.Afficher("C2");
	}
}
