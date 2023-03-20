package figures;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;


public class Editeur extends JPanel implements MouseListener,MouseMotionListener,MouseWheelListener{

	public static ArrayList<Figure> figures;
	public static ArrayList<Figure> selectedFigures;
	public static String currentFig = "";
	public static String currentState = "";
	public static Point selectedPoint = null;
	public static Rectangle selectZone;
	private double zoomFactor = 1;
	private double prevZoomFactor = 1;
	private boolean zoomer;
	
	public Editeur() 
	{
		super();
		repaint();
		figures = new ArrayList<Figure>();
		selectedFigures = new ArrayList<Figure>();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Frame
		JFrame frame = new JFrame();
		frame.setSize(480, 480);
		frame.setTitle("Paint4D");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = frame.getContentPane();
		//Menu
		JMenuBar menu = CreateMenuBar();
		frame.setJMenuBar(menu);
		
		//Zone de dessin
		Editeur editeur = new Editeur();
		editeur.setBorder(BorderFactory.createTitledBorder("Plan"));
		//JButton initFigures = new JButton("Initialiser Figures");
		//initFigures.addActionListener(editeur.new InitialiserFigures(editeur));
		//editeur.add(initFigures);
		JButton clearFigures = new JButton("Clear");
		clearFigures.addActionListener(editeur.new ViderListe(editeur));
		editeur.add(clearFigures,BorderLayout.EAST);
		
		//Componenet
		JPanel component = new JPanel();
		component.setBorder(BorderFactory.createTitledBorder("Component"));
		component.setPreferredSize(new Dimension(200,480));
		
		
		contenu.add(component,BorderLayout.WEST);
		contenu.add(editeur);
		frame.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
			{
				int keyCode = e.getKeyCode();
				System.out.println(e.getKeyChar());
				if(keyCode == KeyEvent.VK_ESCAPE && currentFig.equals("Polygone")) 
				{
					currentFig = "";
				}
			}
		});
		frame.setVisible(true);
		
	}
	
	@Override
	public void paint(Graphics gc) 
	{
		super.paint(gc);
		
		/*Graphics2D gc2 = (Graphics2D) gc;
	    if (zoomer) {
	        AffineTransform at = new AffineTransform();
	        at.scale(zoomFactor, zoomFactor);
	        prevZoomFactor = zoomFactor;
	        gc2.transform(at);
	        zoomer = false;
	    }*/
		
	    for(Figure fig:figures) 
		{
			fig.Paint(gc);
		}
	}
	
	public static JMenuBar CreateMenuBar() 
	{
		JMenuBar menu = new JMenuBar();

		JMenu adminMenu = new JMenu("Administration");
		menu.add(adminMenu);
		
		JMenu createMenu = new JMenu("Creer");	
		
		JMenuItem createPoint = new JMenuItem("Point");
		createPoint.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentFig = "Point";
			currentState = "Creer";
			} });
		createMenu.add(createPoint);
		
		JMenuItem createSegment = new JMenuItem("Segment");
		createSegment.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentFig = "Segment"; 
			currentState = "Creer";
			} });
		createMenu.add(createSegment);
		
		JMenuItem createCercle = new JMenuItem("Cercle");
		createCercle.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentFig = "Cercle"; 
			currentState = "Creer"; 
			} });
		createMenu.add(createCercle);
		
		JMenuItem createPolygone = new JMenuItem("Polygone");
		createPolygone.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentFig = "Polygone";
			currentState = "Creer";
			Polygone poly = new Polygone();
			figures.add(poly);
			} });
		createMenu.add(createPolygone);
		menu.add(createMenu);
		
		JMenu select = new JMenu("Selectionner");
		menu.add(select);
		
		JMenuItem selectPoint = new JMenuItem("Point");
		selectPoint.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentState = "Point Selecting"; } });
		select.add(selectPoint);
		
		JMenuItem selectZone = new JMenuItem("Zone");
		selectZone.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			currentState = "Zone Selecting"; 
			selectedPoint.setSelected(false);
			selectedPoint = null;} });
		select.add(selectZone);
		
		JMenu calcMenu = new JMenu("Calculer");
		menu.add(calcMenu);
		
		return menu;
	}
	
	public void deSelectPoint() 
	{
		if(selectedPoint != null) 
		{
			currentState = "Point Selecting";
			selectedPoint.setSelected(false);
			selectedPoint = null;
			repaint();
		} 
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
			editeur.repaint();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentState.equals("Creer")) 
		{
			if(currentFig.equals("Point")) 
			{
				Point p = new Point(e.getX(),e.getY());
				figures.add(p);
				this.repaint();
			}else if(currentFig.equals("Polygone") && figures.get(figures.size() - 1) instanceof Polygone)
			{
				Point mousePos = new Point(e.getX(),e.getY());
				Polygone poly = (Polygone)figures.get(figures.size() - 1);
				poly.add(mousePos);
				this.repaint();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(currentState.equals("Creer")) 
		{
			if(currentFig.equals("Segment")) 
			{
				Point mousePos = new Point(e.getX(),e.getY());
				Segment s = new Segment(mousePos,mousePos);
				figures.add(s);
				this.repaint();
			}else if(currentFig.equals("Cercle")) 
			{
				Point mousePos = new Point(e.getX(),e.getY());
				Cercle c = new Cercle(mousePos,0);
				figures.add(c);
				this.repaint();
			}
		}
		
		
		
		if(currentState.equals("Point Selected")) 
		{
			deSelectPoint();
		}
		
		
		
		if(currentState.equals("Point Selecting")) 
		{
			int i = 0;
			Point mousePos = new Point(e.getX(),e.getY());
			while(selectedPoint == null && i < figures.size()) 
			{
				selectedPoint = figures.get(i++).CloseTo(mousePos);
				this.repaint();
			}
			if(selectedPoint != null) 
			{
				currentState = "Point Selected";
				selectedPoint.setSelected(true);
			}
			this.repaint();
		}
		
		if(currentState.equals("Zone Selecting")) 
		{
			selectedFigures.clear();
			Point mousePos = new Point(e.getX(),e.getY());
			selectZone = new Rectangle(mousePos);
			figures.add(selectZone);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(currentState.equals("Selecting")) 
		{
			for(Figure fig:figures) 
			{
				if(fig.IsInside(selectZone) && !(fig instanceof Rectangle)) 
				{
					selectedFigures.add(fig);
					fig.setSelected(true);
					
				}
			}
			figures.remove(selectZone);
			selectZone = null; 
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		deSelectPoint();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(figures.size() < 1)return;
		
		if(currentState.equals("Creer")) 
		{
			if(currentFig.equals("Segment") && figures.get(figures.size() - 1) instanceof Segment) 
			{
				Point mousePos = new Point(e.getX(),e.getY());
				Segment s = (Segment)figures.get(figures.size() - 1);
				s.setP2(mousePos);
				this.repaint();
			}else if(currentFig.equals("Cercle") && figures.get(figures.size() - 1) instanceof Cercle) 
			{
				Point mousePos = new Point(e.getX(),e.getY());
				Cercle c = (Cercle)figures.get(figures.size() - 1);
				try {
					c.setRayon(c.getCentre().Distance(mousePos)*2);
				} catch (NegRadiusException e1) {
					// TODO Auto-generated catch block
				}
				this.repaint();
			}
		}
		
		if(currentState.equals("Point Selected") && selectedPoint != null) 
		{
			Point mousePos = new Point(e.getX(),e.getY());
			selectedPoint.setX(mousePos.getX());
			selectedPoint.setY(mousePos.getY());
			this.repaint();
		}
		if(figures.get(figures.size() - 1) instanceof Rectangle) 
		{
			Point mousePos = new Point(e.getX(),e.getY());
			selectZone = (Rectangle)figures.get(figures.size() - 1);
			selectZone.Resize(mousePos);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		zoomer = true;
	    //Zoom in
	    if (e.getWheelRotation() < 0) {
			System.out.println("ld");
	        zoomFactor *= 1.1;
	        repaint();
	    }
	    //Zoom out
	    if (e.getWheelRotation() > 0) {
	        zoomFactor /= 1.1;
	        repaint();
	    }
	}
}
