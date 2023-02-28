package figures;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testPolygone();
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
		printEquals(p2.ToString(),p3.ToString(),p2.Equals(p3));
		printEquals(p1.ToString(),p2.ToString(),p1.Equals(p2));
		Point p1Bis = p1.clone();
		if(p1Bis == null) 
		{
			System.out.println("Cloning Error");
			return;
		}
		p1.Afficher();
		System.out.print("Clone = ");
		p1Bis.Afficher();
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
		Segment s2 = new Segment(new Point(-5,-1),new Point(-2,2));
		Segment s3 = new Segment(s2.getP2(),s1.getP1());
		printEquals(s1.ToString(),s2.ToString(),s1.Equals(s2));
		printEquals(s1.ToString(),s3.ToString(),s1.Equals(s3));
		Segment s4 = new Segment(new Point(0,0),new Point(3,3));
		printEquals(s1.ToString(),s4.ToString(),s1.Equals(s4));
		Segment s1Bis = s1.clone();
		if(s1Bis == null) 
		{
			System.out.println("Cloning Error");
			return;
		}
		s1Bis.Translater(1, 1);
		printEquals(s1.ToString(), s1Bis.ToString(), s1.Equals(s1Bis));
	}
	
	public static void testCercle() 
	{
		Cercle c1 = new Cercle(new Point(0,0), 5);
		c1.Afficher();
		c1.Translater(2, 2);
		c1.Afficher();
		Cercle c2 = new Cercle(new Point(-2,2),new Point(5,-6));
		c2.Afficher();
		try {
			c2.setRayon(2);
		} catch (NegRadiusException e) {
			// TODO Auto-generated catch block
			System.out.println("Le rayon choisie est de : " + e.radius);
			System.out.println(e.radius  + " <= 0 par consequent le rayon est invalide");
		}
		c2.Afficher();
		try {
			c2.setRayon(-2);
		} catch (NegRadiusException e) {
			// TODO Auto-generated catch block
			System.out.println("Le rayon choisie est de : " + e.radius);
			System.out.println(e.radius  + " <= 0 par consequent le rayon est invalide");
		}
		c2.Afficher();
		Cercle c3 = new Cercle(new Point(2,2), 5);
		printEquals(c1.ToString(),c2.ToString(),c1.Equals(c2));
		printEquals(c1.ToString(),c3.ToString(),c1.Equals(c3));
		Cercle c1Bis = c1.clone();
		if(c1Bis == null) 
		{
			System.out.println("Cloning Error");
			return;
		}
		c1Bis.Translater(1, 1);
		printEquals(c1.ToString(),c1Bis.ToString(),c1.Equals(c1Bis));
		
	}
	
	public static void testPolygone() 
	{
		Polygone poly1 = new Polygone();
		poly1.add(new Point(0,0));
		poly1.add(new Point(1,3));
		poly1.add(new Point(3,5));
		poly1.add(new Point(4,1));
		
		Polygone poly2 = new Polygone();
		poly2.add(new Point(1,3));
		poly2.add(new Point(3,5));
		poly2.add(new Point(0,0));
		poly2.add(new Point(4,1));
		
		
		printEquals(poly1.ToString(),poly2.ToString(),poly1.Equals(poly2));
		
		poly1.add(new Point (6,3));
		
		printEquals(poly1.ToString(),poly2.ToString(),poly1.Equals(poly2));
		
		Polygone polyClone = poly1.clone();
		if(polyClone == null) 
		{
			System.out.println("Cloning Error");
			return;
		}
		
		printEquals(poly1.ToString(),polyClone.ToString(),poly1.Equals(polyClone));
		
		Polygone poly3 = new Polygone();
		poly3.add(poly1.getSommets().get(2));
		poly3.add(poly1.getSommets().get(1));
		poly3.add(poly1.getSommets().get(0));
		poly3.add(poly1.getSommets().get(4));
		poly3.add(poly1.getSommets().get(3));
		
		printEquals(poly1.ToString(),poly3.ToString(),poly1.Equals(poly3));
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
	
	
	public static void testPolymorphisme() 
	{
		PointNomme p1 = new PointNomme(0,0,"A");
		PointNomme p2 = new PointNomme(1,1,"B");
		Segment s = new Segment(p1,p2);
		s.Afficher();
	}
	
	public static void testSerialization() 
	{
		Point p = new Point(2,2);
		Segment s = new Segment(new Point(2,2),new Point (0,0));
		Cercle c = new Cercle(s.getP1(),s.getP2());
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream("SerializationTest"));
			out.writeObject(p);
			out.writeObject(s);
			out.writeObject(c);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Point p1 = null;
		Segment s1 = null;
		Cercle c1 = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("SerializationTest"));
			p1 = (Point)in.readObject();
			s1 = (Segment)in.readObject();
			c1 = (Cercle)in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p1.Afficher();
		s1.Afficher();
		c1.Afficher();
	}
	
	public static void testStructures() 
	{
		ArrayList<Figure> figTab = new ArrayList<Figure>();
		figTab.add(new Point(0,0));
		figTab.add(new PointNomme(1,1,"NOMME"));
		figTab.add(new Segment(new Point(0,0),new Point(3,3)));
		figTab.add(new Cercle(new Point(0,0),new Point(3,3)));
		for (Figure fig: figTab){
			fig.Afficher();
			fig.Translater(2,2);
			fig.Afficher();
			fig.getCentre().Afficher();
		}
	}
	
	public static void printEquals(String f1str,String f2str,boolean equal) 
	{
		System.out.println(f1str + " == " + f2str + " : " + equal);
	}
}
