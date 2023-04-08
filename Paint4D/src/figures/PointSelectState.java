package figures;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

public class PointSelectState extends State {

	
	private Point selectedPoint = null;
	private Figure selectedFig = null;
	private FigureList cachedFigList;
	
	public PointSelectState(Editeur editeur) {
		super(editeur);
		// TODO Auto-generated constructor stub
	}
	
	@Override 
	public void Enter() 
	{
		super.Enter();
		
		JLabel selectingLabel = new JLabel("Point Selecting");
		selectingLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(selectingLabel);
		
		JLabel cursorLabel = new JLabel();
		cursorLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(cursorLabel);
		
		editeur.component.repaint();
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
		cachedFigList = null;
		editeur.repaint();
		
		editeur.component.removeAll();
		
		editeur.component.repaint();
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
			
			editeur.component.remove(2);
			editeur.component.repaint();
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
			selectedFig = editeur.figures.get(--i);
			cachedFigList = editeur.figures.clone();
			selectedPoint.setSelected(true);
			
			JLabel pointLabel = new JLabel("P" + mousePos.ToString());
			pointLabel.setPreferredSize(new Dimension(200,20));
			editeur.component.add(pointLabel);
			
			editeur.component.repaint();
		}
		editeur.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		editeur.addFigureList(cachedFigList, editeur.currentFiguresList);
		cachedFigList = null;
		
		editeur.repaint();
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
			
			if(editeur.component.getComponent(1) instanceof JLabel) 
			{
				JLabel label = (JLabel)editeur.component.getComponent(1);
				label.setText("Cursor" + mousePos.ToString());
				editeur.component.repaint();
			}
			
			if(editeur.component.getComponent(2) instanceof JLabel) 
			{
				JLabel label = (JLabel)editeur.component.getComponent(2);
				label.setText("P" + mousePos.ToString());
				editeur.component.repaint();
			}
			
			editeur.repaint();
		}		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(editeur.component.getComponent(1) instanceof JLabel) 
		{
			Point mousePos = new Point(e.getX(),e.getY());
			JLabel cursorlabel = (JLabel)editeur.component.getComponent(1);
			cursorlabel.setText("Cursor" + mousePos.ToString());
			editeur.component.repaint();
		}
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
			selectedPoint = null;
			
			editeur.component.remove(2);
			editeur.component.repaint();
		}
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
			
			editeur.component.remove(2);
			editeur.component.repaint();
		}
	}

	@Override
	public void ctrl_cTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ctrl_vTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ctrl_zTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		super.ctrl_zTyped(e);
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(false);
		}
		selectedPoint = null;
		editeur.repaint();
	}
	
	@Override
	public void ctrl_yTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		super.ctrl_yTyped(e);
		if(selectedPoint != null) 
		{
			selectedPoint.setSelected(false);
		}
		selectedPoint = null;
		editeur.repaint();
	}
}
