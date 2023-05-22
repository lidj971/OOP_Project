package figures;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;

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
		
		JLabel selectingLabel = new JLabel("Zone Selecting");
		selectingLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(selectingLabel);
		
		JLabel cursorLabel = new JLabel();
		cursorLabel.setPreferredSize(new Dimension(200,80));
		editeur.component.add(cursorLabel);
		
		editeur.component.revalidate();
		editeur.component.repaint();
	}
	
	@Override 
	public void Exit() 
	{
		super.Exit();
		for(Figure fig:selectedFigures)
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		cachedFigList = null;
		editeur.repaint();
		
		editeur.component.removeAll();
		editeur.component.revalidate();
		editeur.component.repaint();
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
				
				//Mettre en cache la liste de figure
				cachedFigList = editeur.figures.clone();
				cachedFigList.remove(cachedFigList.size() - 1);
				//deselectionner les figures clonees
				for(Figure fig:cachedFigList)
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
				
				//supprimer les infos des figures selectionnes
				int i = editeur.component.getComponents().length - 1;
				
				while(i >= 2) 
				{
					editeur.component.remove(i);
					i--;
				}
				
				editeur.component.revalidate();
				editeur.component.repaint();
			}
		}

		if(selectZone == null)
		{
			//suprimer la zone de selection precedente
			selectedFigures.clear();
			Point mousePos = new Point(e.getX(),e.getY());
			selectZone = new Rectangle(mousePos);
			editeur.figures.add(selectZone);
			
			//Afficher les infos de la zone de seleciction
			if(editeur.component.getComponent(1) instanceof JLabel) 
			{
				JLabel selectZoneLabel = (JLabel)editeur.component.getComponent(1);
				selectZoneLabel.setText(selectZone.ToString());
				editeur.component.repaint();
			}
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
					//Selectionnes tt les figures dans la zone de selection
					selectedFigures.add(fig);
					fig.setSelected(true);
					
					
					//Afficher les infos de figures selectionnes
					if(fig instanceof Polygone) //si la figure un polygone on separe l'info en plusieurs labels
					{
						Polygone poly = (Polygone)fig;
						for(Point p:poly.getSommets()) 
						{
							JLabel pLabel = new JLabel(p.ToString());
							pLabel.setPreferredSize(new Dimension(200,20));
							editeur.component.add(pLabel);
						}
					}else 
					{
						JLabel figLabel = new JLabel(fig.ToString());
						figLabel.setPreferredSize(new Dimension(200,20));
						editeur.component.add(figLabel);
					}

					editeur.component.revalidate();
					editeur.component.repaint();
				}
			}
		}
		
		
		
		if(selectedFigures.size() > 0) 
		{
			//Poser la zone de selection
			selectZone.setPoser(true);
		}else 
		{
			//Supprimer la zone de selection
			editeur.figures.remove(selectZone);
			selectZone = null; 
		}
		
		if(cachedFigList != null && !cachedFigList.get(0).getCentre().equals(editeur.figures.get(0).getCentre())) 
		{
			//ajouter la liste en cache au flux
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
		
		if(!(editeur.figures.get(editeur.figures.size() - 1) instanceof Rectangle) || !mouseInside) return;
		
		Point mousePos = new Point(e.getX(),e.getY());
		
		if(selectedFigures.size() == 0) 
		{
			
			//Redimensionnez la zone de selection selon la souris
			selectZone = (Rectangle)editeur.figures.get(editeur.figures.size() - 1);
			selectZone.Resize(mousePos);
			
		}else 
		{
			//Deplacer les figures avc la zone de selecition
			selectZone.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
			
			//Affichers les infos des figures selecitonnes
			int i = 2;
			for(Figure fig:selectedFigures) 
			{
				fig.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
				
				if(!(editeur.component.getComponent(i) instanceof JLabel)) 
				{
					i++;
					continue;
				}
				
				//Si la figure est un Polygone on separe l'info en plusieus labels
				if(fig instanceof Polygone) 
				{
					Polygone poly = (Polygone)fig;
					for(Point p:poly.getSommets()) 
					{
						JLabel pLabel = (JLabel)editeur.component.getComponent(i);
						pLabel.setText(p.ToString());
						i++;
					}
				}else
				{

					JLabel figLabel = (JLabel)editeur.component.getComponent(i);
					figLabel.setText(fig.ToString());
					i++;
				}	
			}
			
			editeur.component.revalidate();
			editeur.component.repaint();
			cachedMousePos.Translater(mousePos.getX() - cachedMousePos.getX() ,mousePos.getY() - cachedMousePos.getY());
		}
		
		//Afficher les infos de la zone de selection
		if(editeur.component.getComponent(1) instanceof JLabel) 
		{
			JLabel selectZoneLabel = (JLabel)editeur.component.getComponent(1);
			selectZoneLabel.setText(selectZone.ToString());
			editeur.component.repaint();
		}
		
		editeur.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!(editeur.component.getComponent(1) instanceof JLabel) || selectZone != null) return;
		
		//Afficher la position du curseur
		Point mousePos = new Point(e.getX(),e.getY());
		JLabel cursorlabel = (JLabel)editeur.component.getComponent(1);
		cursorlabel.setText("Cursor" + mousePos.ToString());
		editeur.component.repaint();
	}


	@Override
	public void escapeTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		//deselectionnes les figures
		for(Figure fig:editeur.figures) 
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		editeur.repaint();
		
		//supprimer les infos sur les deselectionnes
		int i = editeur.component.getComponents().length - 1;
		
		while(i >= 2) 
		{
			editeur.component.remove(i);
			i--;
		}
		
		editeur.component.revalidate();
		editeur.component.repaint();
	}

	@Override
	public void backspaceTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedFigures.size() == 0)return;
		
		//Cree un clone de la liste de figures selectionnes
		FigureList figListClone = editeur.figures.clone();
		//supprimer les figures;
		editeur.figures.removeAll(selectedFigures);
		selectedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		
		//Rendre les elements de la liste clonee non selectionnes
		figListClone.remove(figListClone.size() - 1);
		for(Figure fig:figListClone)
		{
			fig.setSelected(false);
		}
		
		//ajouter la liste au chemin
		editeur.addFigureList(figListClone,editeur.currentFiguresList);
		
		editeur.repaint();
		
		//supprimer les infos sur les figures supprimees
		int i = editeur.component.getComponents().length - 1;
		
		while(i >= 2) 
		{
			editeur.component.remove(i);
			i--;
		}
		
		editeur.component.revalidate();
		editeur.component.repaint();
	}

	@Override
	public void ctrl_cTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		if(selectedFigures.size() == 0)return; 
		
		//Mettre les figures selectionnes en cache
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
		for(Figure fig:figListClone)
		{
			fig.setSelected(false);
		}
		
		editeur.addFigureList(figListClone,editeur.currentFiguresList);
		
		editeur.repaint();
	}

	@Override
	public void ctrl_zTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		//Annuler les selections
		for(Figure fig:selectedFigures)
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null;
		
		//Effectuer le retour
		super.ctrl_zTyped(e);
		
		//Supprimer les infos des selections
		int i = editeur.component.getComponents().length - 1;
		
		while(i >= 2) 
		{
			editeur.component.remove(i);
			i--;
		}
		
		editeur.component.revalidate();
		editeur.component.repaint();
	}
	
	@Override
	public void ctrl_yTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		//Annuler les selections
		for(Figure fig:selectedFigures)
		{
			fig.setSelected(false);
		}
		selectedFigures.clear();
		cachedFigures.clear();
		editeur.figures.remove(selectZone);
		selectZone = null; 
		//Effectuer l'avancer
		super.ctrl_yTyped(e);
		
		//Supprimer les infos des selections
		int i = editeur.component.getComponents().length - 1;
		
		while(i >= 2) 
		{
			editeur.component.remove(i);
			i--;
		}
		
		editeur.component.revalidate();
		editeur.component.repaint();
	}
}
