package figures;

import java.util.ArrayList;

public class FigureList extends ArrayList<Figure> implements Cloneable{
	public FigureList() 
	{
		super();
	}

	public FigureList clone() 
	{
		FigureList fl = (FigureList)super.clone();
		fl.clear();
		for(Figure fig:this) 
		{
			fl.add(fig.clone());
		}
		return fl;
	}
}
