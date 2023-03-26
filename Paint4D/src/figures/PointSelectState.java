package figures;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class PointSelectState extends State {

	
	private Point selectedPoint = null;
	private Figure selectedFig = null;
	
	public PointSelectState(Editeur editeur) {
		super(editeur);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void Enter() 
	{
		super.Enter();
	}
	
	@Override 
	public void Exit() 
	{
		super.Exit();
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(false);
		}
		selectedPoint = null;
		editeur.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(false);
			selectedPoint = null;
		}
		
		int i = 0;
		Point mousePos = new Point(e.getX(),e.getY());
		while(selectedPoint == null && i < editeur.figures.size()) 
		{
			selectedPoint = editeur.figures.get(i++).CloseTo(mousePos);
			editeur.repaint();
		}
		
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(true);
			selectedFig = editeur.figures.get(--i);
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
		if(selectedPoint != null) 
		{
			Point mousePos = new Point(e.getX(),e.getY());
			selectedPoint.setX(mousePos.getX());
			selectedPoint.setY(mousePos.getY());
			editeur.repaint();
		}
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
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(false);
		}
		selectedPoint = null;
		editeur.repaint();
	}

	@Override
	public void backspaceTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedFig != null && selectedPoint != null) 
		{
			if(selectedFig instanceof Polygone) 
			{
				Polygone poly = (Polygone) selectedFig;
				poly.getSommets().remove(selectedPoint);
			}else if(selectedFig instanceof Segment) 
			{
				Segment s = (Segment) selectedFig;
				if(!s.getP1().equals(selectedPoint)) 
				{
					editeur.figures.add(s.getP1().clone());
				}else 
				{
					editeur.figures.add(s.getP2().clone());
				}
				editeur.figures.remove(selectedFig);
			}else
			{
				editeur.figures.remove(selectedFig);
			}
			selectedPoint = null;
			editeur.repaint();
		}
	}

}
