package figures;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class State {
	protected Editeur editeur;
	protected boolean isActive;
	protected boolean mouseInside;
	
	public State(Editeur editeur) 
	{
		this.editeur = editeur;
		isActive = false;
		mouseInside = false;
	}
	
	public void Enter() 
	{
		isActive = true;
	}
	
	public void Exit() 
	{
		isActive = false;
	}
	
	public abstract void mouseClicked(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseReleased(MouseEvent e);

	public void mouseEntered(MouseEvent e) 
	{
		mouseInside = true;
	}

	public void mouseExited(MouseEvent e) 
	{
		mouseInside = false;
	}

	public abstract void mouseDragged(MouseEvent e);
	
	public abstract void mouseMoved(MouseEvent e);

	public abstract void mouseWheelMoved(MouseWheelEvent e);
	
	public abstract void escapeTyped(ActionEvent e);
	
	public abstract void backspaceTyped(ActionEvent e);
	
	public abstract void ctrl_cTyped(ActionEvent e);
	
	public abstract void ctrl_vTyped(ActionEvent e);
}
