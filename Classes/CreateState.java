package figures;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JLabel;

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

		JLabel figLabel = new JLabel(currentFig);
		figLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(figLabel);
		
		JLabel infoLabel = new JLabel();
		infoLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(infoLabel);
		editeur.component.repaint();
	}
	
	@Override 
	public void Exit() 
	{
		super.Exit();
		editeur.component.removeAll();
		editeur.component.repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!currentFig.equals("Point") && !currentFig.equals("Polygone"))return;
		FigureList figListClone = editeur.figures.clone();
		Point mousePos = new Point(e.getX(),e.getY());
		if(currentFig.equals("Point")) 
		{
			Point p = mousePos.clone();
			editeur.figures.add(p);
		}else if(editeur.figures.get(editeur.figures.size() - 1) instanceof Polygone)
		{
			Polygone poly = (Polygone)editeur.figures.get(editeur.figures.size() - 1);
			poly.add(mousePos);
			
			JLabel infoLabel = new JLabel();
			infoLabel.setPreferredSize(new Dimension(200,20));
			editeur.component.add(infoLabel,BorderLayout.WEST);
			editeur.component.repaint();
		}
		editeur.addFigureList(figListClone, editeur.currentFiguresList);
		editeur.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!currentFig.equals("Segment") && !currentFig.equals("Cercle"))return;
		FigureList figListClone = editeur.figures.clone();
		Point mousePos = new Point(e.getX(),e.getY());
		if(currentFig.equals("Segment")) 
		{
			Segment s = new Segment(mousePos,mousePos);
			editeur.figures.add(s);
			JLabel segInfoLabel = new JLabel();
			segInfoLabel.setPreferredSize(new Dimension(200,20));
			editeur.component.add(segInfoLabel,BorderLayout.WEST);		
		}else 
		{
			Cercle c = new Cercle(mousePos,0);
			editeur.figures.add(c);	
		}
		
		JLabel lengthInfoLabel = new JLabel();
		lengthInfoLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(lengthInfoLabel,BorderLayout.WEST);	
		editeur.component.repaint();	
		
		editeur.addFigureList(figListClone, editeur.currentFiguresList);
		editeur.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!currentFig.equals("Segment") && !currentFig.equals("Cercle"))return;
		
		//Supprimer toutes les infos de la figure creer
		editeur.component.removeAll();
		JLabel figLabel = new JLabel(currentFig);
		figLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(figLabel);
		JLabel infoLabel = new JLabel();
		infoLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(infoLabel,BorderLayout.WEST);	
		editeur.component.repaint();
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
		if((!currentFig.equals("Segment") && !currentFig.equals("Cercle")) || !mouseInside)return;
		Point mousePos = new Point(e.getX(),e.getY());
		if(editeur.figures.get(editeur.figures.size() - 1) instanceof Segment) //Update la position du deuxime point du segment et ses infos
		{
			Segment s = (Segment)editeur.figures.get(editeur.figures.size() - 1);
			s.setP2(mousePos);
			editeur.repaint();
			
			JLabel label;
			if(editeur.component.getComponent(2) instanceof JLabel) 
			{
				label = (JLabel)editeur.component.getComponent(2);
				label.setText("P2" + mousePos.ToString());
			}
			
			if(editeur.component.getComponent(3) instanceof JLabel) 
			{
				label = (JLabel)editeur.component.getComponent(3);
				label.setText("Longueur = " + (int)s.getLongueur());
				
			}
			editeur.component.repaint();
			
		}else if(editeur.figures.get(editeur.figures.size() - 1) instanceof Cercle) //Update le rayon du cercle et ses infos
		{
			Cercle c = (Cercle)editeur.figures.get(editeur.figures.size() - 1);
			
			Point ulCorner = new Point((int)(c.getCentre().getX() - (c.getRayon()/2)),(int)(c.getCentre().getY() - (c.getRayon()/2)));
			
			if(ulCorner.getX() <= 0 || ulCorner.getY() <= 0)return; 
			
			
			try {
				c.setRayon(c.getCentre().Distance(mousePos)*2);
			} catch (NegRadiusException e1) {
				// TODO Auto-generated catch block
			}
			
			JLabel label;
			if(editeur.component.getComponent(2) instanceof JLabel) 
			{
				label = (JLabel)editeur.component.getComponent(2);
				label.setText("Rayon = " + (int)c.getRayon());
			}
		}
		editeur.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel label;
		Point mousePos = new Point(e.getX(),e.getY());
		
		
		
		if(currentFig.equals("Polygone") && (editeur.figures.get(editeur.figures.size() - 1) instanceof Polygone)) //Afficher la position de creation du prochain point de polygone
		{			
			Polygone poly = (Polygone)editeur.figures.get(editeur.figures.size() - 1);
			
			if(!(editeur.component.getComponent(poly.getSommets().size()) instanceof JLabel)) return;
			
			label = (JLabel)editeur.component.getComponent(poly.getSommets().size());
			label.setText("P" + (poly.getSommets().size() + 1) + mousePos.ToString());
			editeur.component.repaint();
		}else 
		{
			if(editeur.component.getComponent(1) instanceof JLabel) //Afficher la position de creation de la figure selon son type
			{
				String currentInfo = currentFig;
				if(currentFig.equals("Segment")) 
				{
					currentInfo = "P1";
				}else if (currentFig.equals("Cercle"))
				{
					currentInfo = "Centre";
				}
				
				label = (JLabel)editeur.component.getComponent(1);
				label.setText(currentInfo + mousePos.ToString());
				editeur.component.repaint();
			}
		}
	}

	@Override
	public void escapeTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(!currentFig.equals("Polygone")) return;
		
		
		//Arret de l'ajout de point au polygone courrant 
		Polygone poly = new Polygone();
		editeur.figures.add(poly);

		editeur.component.removeAll();
		JLabel figLabel = new JLabel(currentFig);
		figLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(figLabel);
		JLabel polyInfoLabel = new JLabel();
		polyInfoLabel.setPreferredSize(new Dimension(200,20));
		editeur.component.add(polyInfoLabel,BorderLayout.WEST);	
		editeur.component.repaint();
	}

	@Override
	public void backspaceTyped(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
	}
}
