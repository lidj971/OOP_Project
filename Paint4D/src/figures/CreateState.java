package figures;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CreateState extends State{
	public CreateState(Editeur editeur) {
		super(editeur);
		// TODO Auto-generated constructor stub
	}

	private String currentFig = "";
	
	public String getCurrentFig() 
	{
		return currentFig;
	}
	
	public void setCurrentFig(String currentFig) 
	{
		this.currentFig = currentFig;
	}
	
	@Override 
	public void Enter() 
	{
		super.Enter();
		if(currentFig.equals("Polygone")) 
		{
			Polygone poly = new Polygone();
			editeur.figures.add(poly);
		}
	}
	
	@Override 
	public void Exit() 
	{
		super.Exit();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Point mousePos = new Point(e.getX(),e.getY());
		if(currentFig.equals("Point")) 
		{
			Point p = mousePos.clone();
			editeur.figures.add(p);
		}else if(currentFig.equals("Polygone") && editeur.figures.get(editeur.figures.size() - 1) instanceof Polygone)
		{
			Polygone poly = (Polygone)editeur.figures.get(editeur.figures.size() - 1);
			poly.add(mousePos);
		}
		editeur.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Point mousePos = new Point(e.getX(),e.getY());
		if(currentFig.equals("Segment")) 
		{
			Segment s = new Segment(mousePos,mousePos);
			editeur.figures.add(s);
			
		}else if(currentFig.equals("Cercle")) 
		{
			Cercle c = new Cercle(mousePos,0);
			editeur.figures.add(c);
		}
		editeur.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point mousePos = new Point(e.getX(),e.getY());
		if(currentFig.equals("Segment") && editeur.figures.get(editeur.figures.size() - 1) instanceof Segment) 
		{
			Segment s = (Segment)editeur.figures.get(editeur.figures.size() - 1);
			s.setP2(mousePos);
			editeur.repaint();
		}else if(currentFig.equals("Cercle") && editeur.figures.get(editeur.figures.size() - 1) instanceof Cercle) 
		{
			Cercle c = (Cercle)editeur.figures.get(editeur.figures.size() - 1);
			try {
				c.setRayon(c.getCentre().Distance(mousePos)*2);
			} catch (NegRadiusException e1) {
				// TODO Auto-generated catch block
			}
		}
		editeur.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escapeTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentFig.equals("Polygone")) 
		{
			Polygone poly = new Polygone();
			editeur.figures.add(poly);
		}
	}

	@Override
	public void backspaceTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
