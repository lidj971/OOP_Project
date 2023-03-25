package figures;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class State {
	protected Editeur editeur;
	protected boolean isActive;
	
	public State(Editeur editeur) 
	{
		this.editeur = editeur;
		isActive = false;
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

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mouseExited(MouseEvent e);

	public abstract void mouseDragged(MouseEvent e);
	
	public abstract void mouseMoved(MouseEvent e);

	public abstract void mouseWheelMoved(MouseWheelEvent e);
}
