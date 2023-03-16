package figures;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


public class Editeur extends JPanel {

	public ArrayList<Figure> figures;
	
	public Editeur() 
	{
		super();
		repaint();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setSize(480, 480);
		frame.setTitle("Paint4D");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = frame.getContentPane();
		Editeur editeur = new Editeur();
		editeur.setBackground(Color.white);
		JButton initFigures = new JButton("Initialiser Figures");
		initFigures.addActionListener(editeur.new InitialiserFigures(editeur));
		JButton clearFigures = new JButton("Clear");
		clearFigures.addActionListener(editeur.new ViderListe(editeur));
		editeur.add(initFigures);
		editeur.add(clearFigures);
		editeur.figures = new ArrayList<Figure>();
		editeur.figures.add(new Point(10,10));
		//editeur.figures.add(new Segment(new Point(40,40),new Point(80,80)));
		editeur.figures.add(new Cercle(new Point(100,100),50));
		Polygone poly = new Polygone();
		poly.add(new Point(10,10));
		poly.add(new Point(50,10));
		poly.add(new Point(10,50));
		poly.add(new Point(50,50));
		editeur.figures.add(poly);
		contenu.add(editeur);
		frame.setVisible(true);
	}
	
	@Override
	public void paint(Graphics gc) 
	{
		super.paint(gc);
		
		for(Figure fig:figures) 
		{
			fig.Paint(gc);
		}
	}
	
	public void CreateMenuBar() 
	{
		
	}
	
	public class InitialiserFigures implements ActionListener{

		private Editeur editeur;
		
		public InitialiserFigures(Editeur editeur) 
		{
			this.editeur = editeur;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			editeur.figures = new ArrayList<Figure>();
			System.out.println(editeur.figures);
		}
		
	}
	
	public class ViderListe implements ActionListener{

		private Editeur editeur;
		
		public ViderListe(Editeur editeur) 
		{
			this.editeur = editeur;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			editeur.figures.clear();
			System.out.println(editeur.figures);
		}
		
	}

}
