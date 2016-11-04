package damdariar.gui.swing.search;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import damdariar.gui.swing.layout.GridLayoutManager;

public class SearchEntryLayoutManager extends GridLayoutManager{

	public  SearchEntryLayoutManager(Container container) {
		super(container);
		setAnchor(GridBagConstraints.EAST);
		getConstriant().gridx = 0;
		getConstriant().gridy = 0;
		getConstriant().insets   = new Insets(0,0,0,0);
		getConstriant().fill = GridBagConstraints.BOTH;
		getConstriant().weighty = 0;
		getConstriant().weightx = 5;
		// TODO Auto-generated constructor stub
	}

}
