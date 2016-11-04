package damdariar.gui.swing;

import java.awt.Component;
import java.awt.Graphics;

public class SearchPanelScrollPane extends DScrollPane{

	public SearchPanelScrollPane() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchPanelScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		// TODO Auto-generated constructor stub
	}

	public SearchPanelScrollPane(Component view) {
		super(view);
		// TODO Auto-generated constructor stub
	}

	public SearchPanelScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	

}
