package damdariar.gui.swing.search;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import damdariar.gui.swing.DButton;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.listeners.AdvanceSearchListener;
import damdariar.resource.ResourceUtil;

public class AdvanceSearch extends JFrame{
	
	public static int  width =  500;
	public static int  height = 380;
	public static int  heightTop = 350;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DButton      searchButton = new DButton(ResourceUtil.getString("ADVANCESEARCH_BUTTON"));
	AdvancedSearchPanel searchPanel;
	private AdvanceSearchListener searchListener;
	private DPanel  contentPanel = new DPanel();
	
	public AdvanceSearch(final Class<?> classType){
		this(classType,null);
	}
	public AdvanceSearch(final Class<?> classType, String linkedProperty){
		setContentPane(contentPanel);
		setUndecorated(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);	
		searchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(searchListener != null)
					searchListener.searchDone(searchPanel.getSearchCriteria());
			}});
		searchPanel = new AdvancedSearchPanel(classType,linkedProperty);
		AdvancedSearchPanelLayoutManager manager = new AdvancedSearchPanelLayoutManager(contentPanel);
		manager.getConstriant().fill = GridBagConstraints.BOTH;
		manager.getConstriant().weighty = 0;
		manager.getConstriant().gridheight = 1;
		manager.getConstriant().ipady = height-100;
		DScrollPane  scrollPane = new DScrollPane(searchPanel);
		scrollPane.setOpaque(false);
		searchPanel.setPreferredSize(new Dimension(width-3, heightTop-60));
		scrollPane.setPreferredSize(new Dimension(width, heightTop));
		manager.add(scrollPane);
		
		manager.gotoNewLine();
		manager.getConstriant().gridheight = 1;
		manager.getConstriant().ipady = 0;
		manager.getConstriant().insets = new Insets(0,0,0,0);
		manager.getConstriant().weightx = 0;
		manager.getConstriant().weighty = 5;
		manager.getConstriant().fill = GridBagConstraints.CENTER;
		manager.getConstriant().anchor = GridBagConstraints.SOUTH;
		manager.add(searchButton);

		
		
		
	}
	
	public void addSearchListener(AdvanceSearchListener listener){
		
		this.searchListener = listener;
	}

	


	
	
}


