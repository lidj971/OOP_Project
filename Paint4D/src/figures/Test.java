package figures;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testSegmentNomme();
	}
	
	public static void testPoint() 
	{
		Point p1 = new Point(0,0);
		p1.Afficher();
		p1.Translater(3,1);
		p1.Afficher();
		Point p2 = new Point(-2,-6);
		System.out.println("Distance(p1,p2) = " + p1.Distance(p2));
		Point p3 = new Point(p2);
		p2.Afficher();
		p3.Afficher();
		System.out.println("Distance(p2,p3) = " + p2.Distance(p3));
	}
	
	public static void testSegment() 
	{
		Segment s1 = new Segment(new Point(0,0),new Point(3,3));
		s1.Afficher();
		System.out.println("Longueur : " + s1.getLongueur());
		s1.getCentre().Afficher();
		s1.Translater(-5, -1);
		s1.Afficher();
		System.out.println("Longueur : " + s1.getLongueur());
		s1.getCentre().Afficher();
	}
	
	public static void testCercle() 
	{
		Cercle c1 = new Cercle(new Point(0,0), 5);
		c1.Afficher();
		c1.Translater(2, 2);
		c1.Afficher();
		Cercle c2 = new Cercle(new Point(-2,2),new Point(5,-6));
		c2.Afficher();
		c2.setRayon(2);
		c2.Afficher();
		c2.setRayon(-2);
		c2.Afficher();
	}
	
	public static void testPointNomme() 
	{
		PointNomme a = new PointNomme(1,1,"A");
		a.Afficher();
		a.setNom("B");
		a.Afficher();
	}
	
	public static void testSegmentNomme() 
	{
		SegmentNomme s = new SegmentNomme(new Point(0,0),new Point(2,1),"S");
		s.Afficher();
		s.setNom("T");
		s.Afficher();
	}
	
}
