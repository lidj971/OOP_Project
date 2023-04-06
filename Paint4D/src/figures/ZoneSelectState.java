package figures;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collection;

public class ZoneSelectState extends State{

	private ArrayList<Figure> selectedFigures;
	private ArrayList<Figure> cachedFigures;
	private FigureList cachedFigList;
	public Rectangle selectZone;
	private Rectangle cachedSelectZone;
	private Point cachedMousePos;
	private Point currentMousePos;
	
	public ZoneSelectState(Editeur editeur) {
		super(editeur);
		selectedFigures = new ArrayList<Figure>();
		cachedFigures = new ArrayList<Figure>();
		selectZone = null;
		cachedSelectZone = null;
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
		for(Figure fig:editeur.figures) //Opti
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		cachedFigList = null;
		editeur.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(selectZone != null) 
		{
			Point mousePos = new Point(e.getX(),e.getY());
			if(mousePos.IsInside(selectZone)) 
			{
				cachedMousePos = new Point(mousePos);
				cachedFigList = editeur.figures.clone();
				cachedFigList.remove(cachedFigList.size() - 1);
				for(Figure fig:cachedFigList) //Opti
				{
					fig.setSelected(false);
				}
			}else 
			{
				editeur.figures.remove(selectZone);
				for(Figure fig:selectedFigures) 
				{
					fig.setSelected(false);
				}
				selectZone = null; 
				selectedFigures.clear();
				editeur.repaint();
			}
		}

		if(selectZone == null)
		{
			selectedFigures.clear();
			Point mousePos = new Point(e.getX(),e.getY());
			selectZone = new Rectangle(mousePos);
			editeur.figures.add(selectZone);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(selectedFigures.size() == 0 && selectZone != null) 
		{
			for(Figure fig:editeur.figures) 
			{
				if(fig.IsInside(selectZone) && !(fig instanceof Rectangle)) 
				{
					selectedFigures.add(fig);
					fig.setSelected(true);
					
				}
			}
		}
		
		if(selectedFigures.size() > 0) 
		{
			selectZone.setPoser(true);
		}else 
		{
			editeur.figures.remove(selectZone);
			selectZone = null; 
		}
		
		if(cachedFigList != null && !cachedFigList.get(0).getCentre().equals(editeur.figures.get(0).getCentre())) 
		{
			editeur.addFigureList(cachedFigList,editeur.currentFiguresList);
		}
		
		editeur.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!(editeur.figures.get(editeur.figures.size() - 1) instanceof Rectangle)) return;
		
		Point mousePos = new Point(e.getX(),e.getY());
		
		if(selectedFigures.size() == 0) 
		{
			
			selectZone = (Rectangle)editeur.figures.get(editeur.figures.size() - 1);
			selectZone.Resize(mousePos);
			
		}else 
		{
			selectZone.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
			for(Figure fig:selectedFigures) 
			{
				fig.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
			}
			cachedMousePos.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
		}
		editeur.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		currentMousePos = new Point(e.getX(),e.getY());
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escapeTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		for(Figure fig:editeur.figures) 
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		editeur.repaint();
	}

	@Override
	public void backspaceTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedFigures.size() == 0)return;
		FigureList figListClone = editeur.figures.clone();
		editeur.figures.removeAll(selectedFigures);
		selectedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		
		figListClone.remove(figListClone.size() - 1);
		for(Figure fig:figListClone) //Opti
		{
			fig.setSelected(false);
		}
		
		editeur.addFigureList(figListClone,editeur.currentFiguresList);
		
		editeur.repaint();
	}

	@Override
	public void ctrl_cTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedFigures.size() == 0)return; 
		
		cachedFigures.clear();
		for(Figure fig:selectedFigures) 
		{
			Figure figClone = fig.clone();
			figClone.setSelected(false);
			cachedFigures.add(figClone);
		}
		cachedSelectZone = selectZone.clone();
	}

	@Override
	public void ctrl_vTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(cachedFigures.size() == 0 || !mouseInside)return;
		
		FigureList figListClone = editeur.figures.clone();
		
		ArrayList<Figure> newCachedFigures = new ArrayList<Figure>();
		
		for(Figure fig:cachedFigures) 
		{
			fig.Translater(currentMousePos.getX() - cachedSelectZone.getCentre().getX() ,currentMousePos.getY() - cachedSelectZone.getCentre().getY());
			editeur.figures.add(0, fig);
			newCachedFigures.add(fig.clone());
		}
		
		cachedSelectZone.Translater(currentMousePos.getX() - cachedSelectZone.getCentre().getX() ,currentMousePos.getY() - cachedSelectZone.getCentre().getY());
		
		cachedFigures = newCachedFigures;
		
		figListClone.remove(figListClone.size() - 1);
		for(Figure fig:figListClone) //Opti
		{
			fig.setSelected(false);
		}
		
		editeur.addFigureList(figListClone,editeur.currentFiguresList);
		
		editeur.repaint();
	}

	@Override
	public void ctrl_zTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		for(Figure fig:editeur.figures) //Opti
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		editeur.repaint();
		super.ctrl_zTyped(e);
	}
	
	@Override
	public void ctrl_yTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		for(Figure fig:selectedFigures) //Opti
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		super.ctrl_yTyped(e);
		editeur.repaint();
	}
}
