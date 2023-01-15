package figures;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testPoint();
	}
	
	public static void testPoint() 
	{
		Point p1 = new Point(0,0);
		p1.Afficher("p1");
		p1.getCenter().Afficher("p1 center");
		p1.Translater(3,1);
		p1.Afficher("p1");
		p1.getCenter().Afficher("p1 center");
		Point p2 = new Point(-2,-6);
		System.out.println("Distance(p1,p2) = " + p1.Distance(p2));
		Point p3 = new Point(p2);
		p2.Afficher("p2");
		p3.Afficher("p3");
		System.out.println("Distance(p2,p3) = " + p2.Distance(p3));
	}
}
