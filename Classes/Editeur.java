package figures;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;


public class Editeur extends JPanel implements MouseListener,MouseMotionListener{

	public FigureList figures;
	public ArrayList<FigureList> figuresList;
	
	public int currentFiguresList;
	
	public StateMachine stateMachine;
	
	public CreateState createState;
	public PointSelectState pointSelectState;
	public ZoneSelectState zoneSelectState;
	
	public JPanel component;
	
	public JMenuBar menuBar;
	
	
	
	public Editeur() 
	{
		super();
		
		repaint();
		
		figures = new FigureList();
		
		figuresList = new ArrayList<FigureList>();
		
		currentFiguresList = 0;
		
		figuresList.add(figures);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESCAPE);
	    this.getActionMap().put(ESCAPE, escape);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), BACKSPACE);
	    this.getActionMap().put(BACKSPACE, backspace);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK), CTRL_C);
	    this.getActionMap().put(CTRL_C, ctrl_c);
	
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK), CTRL_V);
	    this.getActionMap().put(CTRL_V, ctrl_v);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK), CTRL_Z);
	    this.getActionMap().put(CTRL_Z, ctrl_z);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK), CTRL_Y);
	    this.getActionMap().put(CTRL_Y, ctrl_y);
	    
		createState = new CreateState(this);
		pointSelectState = new PointSelectState(this);
		zoneSelectState = new ZoneSelectState(this);
		
		stateMachine = new StateMachine();
		
		menuBar = CreateMenuBar();
	}
	
	public Editeur(FigureList figures) 
	{
		super();
		
		repaint();
		
		this.figures = figures;
		
		figuresList = new ArrayList<FigureList>();
		
		currentFiguresList = 0;
		
		figuresList.add(figures);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), ESCAPE);
	    this.getActionMap().put(ESCAPE, escape);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), BACKSPACE);
	    this.getActionMap().put(BACKSPACE, backspace);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK), CTRL_C);
	    this.getActionMap().put(CTRL_C, ctrl_c);
	
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK), CTRL_V);
	    this.getActionMap().put(CTRL_V, ctrl_v);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK), CTRL_Z);
	    this.getActionMap().put(CTRL_Z, ctrl_z);
	    
	    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK), CTRL_Y);
	    this.getActionMap().put(CTRL_Y, ctrl_y);
	    
		createState = new CreateState(this);
		pointSelectState = new PointSelectState(this);
		zoneSelectState = new ZoneSelectState(this);
		
		stateMachine = new StateMachine();
		
		menuBar = CreateMenuBar();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Frame
		JFrame frame = new JFrame();
		frame.setSize(480, 480);
		frame.setTitle("Paint4D");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenu = frame.getContentPane();

		//Zone de dessin
		Editeur editeur = null;
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("SaveFile"));
			editeur = new Editeur((FigureList)in.readObject());
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			editeur = new Editeur();
			System.out.print(false);
		} 
		
		editeur.setBorder(BorderFactory.createTitledBorder("Plan"));
		editeur.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));

		JButton clearFigures = new JButton("C");
		clearFigures.addActionListener(editeur.new ViderListe());
		editeur.add(clearFigures);
		
		//Menu
		frame.setJMenuBar(editeur.menuBar);
		
		//Component
		JPanel component = new JPanel();
		component.setBorder(BorderFactory.createTitledBorder("Component"));
		component.setPreferredSize(new Dimension(200,480));
		component.setLayout(new FlowLayout(FlowLayout.LEADING,3,3));
		
		contenu.add(component,BorderLayout.WEST);
		editeur.component = component;
		contenu.add(editeur);
		
		frame.setVisible(true);
		
		frame.addWindowListener(editeur.new SaveFigListAdapter(editeur.figures));
		
		editeur.stateMachine.Initialize(editeur.pointSelectState);
	}
	
	public void addFigureList(FigureList figureList,int pos) 
	{
		figuresList.add(pos,figureList);
		if(figuresList.size() <= 10) 
		{
			currentFiguresList++;
		}else 
		{
			figuresList.remove(0);
		}
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
	
	
	public JMenuBar CreateMenuBar() 
	{
		JMenuBar menu = new JMenuBar();
		
		JMenu createMenu = new JMenu("Creer");	
		
		JMenuItem createPoint = new JMenuItem("Point");
		createPoint.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			createState.setCurrentFig("Point");
			stateMachine.ChangeState(createState);
			} });
		createMenu.add(createPoint);
		
		JMenuItem createSegment = new JMenuItem("Segment");
		createSegment.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			createState.setCurrentFig("Segment");
			stateMachine.ChangeState(createState);
			} });
		createMenu.add(createSegment);
		
		JMenuItem createCercle = new JMenuItem("Cercle");
		createCercle.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			createState.setCurrentFig("Cercle");
			stateMachine.ChangeState(createState);
			} });
		createMenu.add(createCercle);
		
		JMenuItem createPolygone = new JMenuItem("Polygone");
		createPolygone.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			createState.setCurrentFig("Polygone");
			stateMachine.ChangeState(createState);
			} });
		createMenu.add(createPolygone);
		menu.add(createMenu);
		
		JMenu select = new JMenu("Selectionner");
		menu.add(select);
		
		JMenuItem selectPoint = new JMenuItem("Point");
		selectPoint.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			stateMachine.ChangeState(pointSelectState);} });
		select.add(selectPoint);
		
		JMenuItem selectZone = new JMenuItem("Zone");
		selectZone.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
			stateMachine.ChangeState(zoneSelectState);} });
		select.add(selectZone);
		
		return menu;
	}
	

	public class ViderListe implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			stateMachine.getCurrentState().Exit();
			stateMachine.getCurrentState().Enter();
			figures.clear();
			repaint();
		}
	}
	
	public class SaveFigListAdapter extends WindowAdapter{
		FigureList figures;
		
		public SaveFigListAdapter(FigureList figures) 
		{
			super();
			this.figures = figures;
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new FileOutputStream("SaveFile"));
				out.writeObject(figures);
				out.flush();
				out.close();
			} catch (Exception exc) {
				// TODO Auto-generated catch block
				exc.printStackTrace();
			}
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		stateMachine.getCurrentState().mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		stateMachine.getCurrentState().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		stateMachine.getCurrentState().mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		stateMachine.getCurrentState().mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		stateMachine.getCurrentState().mouseExited(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		stateMachine.getCurrentState().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		stateMachine.getCurrentState().mouseMoved(e);
	}
	
	private static final String ESCAPE = "Escape";
    private Action escape = new AbstractAction(ESCAPE) {
        @Override
        public void actionPerformed(ActionEvent e) {
            stateMachine.getCurrentState().escapeTyped(e);
        }
    };
    
    private static final String BACKSPACE = "Backspace";
    private Action backspace = new AbstractAction(BACKSPACE) {
        @Override
        public void actionPerformed(ActionEvent e) {
            stateMachine.getCurrentState().backspaceTyped(e);
        }
    };
    
    private static final String CTRL_C = "Ctrl_C";
    private Action ctrl_c = new AbstractAction(CTRL_C) {
        @Override
        public void actionPerformed(ActionEvent e) {
        	stateMachine.getCurrentState().ctrl_cTyped(e);
        }
    };
    
    private static final String CTRL_V = "Ctrl_V";
    private Action ctrl_v = new AbstractAction(CTRL_V) {
        @Override
        public void actionPerformed(ActionEvent e) {
        	stateMachine.getCurrentState().ctrl_vTyped(e);
        }
    };
    
    private static final String CTRL_Z = "Ctrl_Z";
    private Action ctrl_z = new AbstractAction(CTRL_Z) {
        @Override
        public void actionPerformed(ActionEvent e) {
        	stateMachine.getCurrentState().ctrl_zTyped(e);
        }
    };
    
    private static final String CTRL_Y = "Ctrl_Y";
    private Action ctrl_y = new AbstractAction(CTRL_Y) {
        @Override
        public void actionPerformed(ActionEvent e) {
        	stateMachine.getCurrentState().ctrl_yTyped(e);
        }
    };
}	


	