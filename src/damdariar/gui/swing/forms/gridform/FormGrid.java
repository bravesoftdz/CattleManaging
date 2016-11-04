package damdariar.gui.swing.forms.gridform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.PatternSyntaxException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.hibernate.Query;

import damdariar.beans.AbstractBean;
import damdariar.gui.swing.ConfirmDialog;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.DTable;
import damdariar.gui.swing.DTextField;
import damdariar.gui.swing.forms.FormTree;
import damdariar.gui.swing.forms.listeners.AdvanceSearchListener;
import damdariar.gui.swing.forms.listeners.BeanActionListener;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.forms.listeners.TreeRowSelectionListener;
import damdariar.gui.swing.model.ApplicationDataModel;
import damdariar.gui.swing.model.DTableModel;
import damdariar.gui.swing.renderers.DTableCellRenderer;
import damdariar.images.ImageUtil;
import damdariar.util.AdvanceKeyListener;
import damdariar.util.Util;


public class FormGrid extends DPanel implements AdvanceSearchListener,Printable,DataModelStatusListener, TableColumnModelListener, BeanActionListener, TreeRowSelectionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  DTable table;
	public  DPanel searchPanel;
	private TableRowSorter<TableModel> sorter;
	private FounderRecordsLabel label;
	private DScrollPane scrollPane;
	private DTextField searchField = new DTextField();
	private DTableModel model;
	private DStatusPanel statusPanel;
	JPopupMenu popupMenu;
	
	
	 static String deleteMultiRecords = " \u0622\u06cc\u0627 \u0645\u06cc \u062e\u0648\u0627\u0647\u064a\u062f \u0631\u06a9\u0648\u0631\u062f\u0647\u0627\u06cc \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u0631\u0627 \u067e\u0627\u06a9 \u0646\u0645\u0627\u064a\u064a\u062f\u061f";
	 static String deleteSingleRecord = " \u0622\u06cc\u0627 \u0645\u06cc \u062e\u0648\u0627\u0647\u064a\u062f \u0631\u06a9\u0648\u0631\u062f \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u0631\u0627 \u067e\u0627\u06a9 \u0646\u0645\u0627\u064a\u064a\u062f\u061f";
	 static String deleteMenuName = " \u0631\u06a9\u0648\u0631\u062f\u0647\u0627\u06cc \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u067e\u0627\u06a9 \u0634\u0648\u062f";
	 static String deleteDialogTitle = " \u062a\u0627\u064a\u064a\u062f \u0639\u0645\u0644 \u062d\u0630\u0641 \u0631\u06a9\u0648\u0631\u062f";
	
	
	private String simpleSearchTitle = " \u062c\u0633\u062a\u062c\u0648 \u0628\u0631 \u0631\u0648\u06cc \u062f\u0627\u062f\u0647 \u0647\u0627";
	List<GridRowSelectionListener> gridRowSelectionListeners;
	private Class<?> classType;
	private String linkedProperty;
	private FormTree formTree;
	
	
	public FormGrid(Class<?> classType,List<DataModelStatusListener> dataModelStatusListeners){
		init(classType,dataModelStatusListeners,null);
	}
	
	
	public FormGrid(Class<?> classType){
		init(classType,null,null);
		
	}
	
	
	public FormGrid(Class<?> classType,
			List<DataModelStatusListener> dataModelStatusListeners,
			String criteria) {
		init(classType,dataModelStatusListeners,criteria);
	}
	
	public FormGrid(Class<?> classType,
			List<DataModelStatusListener> dataModelStatusListeners,
			String criteria,String linkedProperty) {
		this.linkedProperty = linkedProperty;
		init(classType,dataModelStatusListeners,criteria);
		
	}


	public  void init(Class<?> classType,List<DataModelStatusListener> dataModelStatusListeners,String criteria){
		
		this.classType = classType;
		/*searchField.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent fe){
				if(searchField.getInputContext() != null )
					searchField.getInputContext().selectInputMethod(new Locale("fa","IR"));		
			}
		});*/
		
		searchField.setBorder(BorderFactory.createTitledBorder(simpleSearchTitle));
		new AdvanceKeyListener(searchField);
		searchField.addVetoableChangeListener(new VetoableChangeListener(){

			@Override
			public void vetoableChange(PropertyChangeEvent e)
					throws PropertyVetoException {
				String text = ((JTextField)e.getSource()).getText ().trim ();
				
				
				if ( text.length () == 0 )
				{
					sorter.setRowFilter ( null );
				}
				else
				{
					try
					{
						sorter.setRowFilter ( RowFilter.regexFilter ( text ) );
					}
					catch ( PatternSyntaxException pse )
					{
						System.err.println ( "Bad regex pattern" );
					}
				}
				label.setText ( String.valueOf ( sorter.getViewRowCount () ) );
				
			}
			
		});

	label = new FounderRecordsLabel();
	JPanel southPanel = getSouthPanel();
	
	if(dataModelStatusListeners == null)
		dataModelStatusListeners = new ArrayList<DataModelStatusListener>();
	dataModelStatusListeners.add(this);
	
	model = new DTableModel(classType,dataModelStatusListeners,criteria,linkedProperty);
	table = new DTable ( model);
	scrollPane = new DScrollPane ();
	

	sorter = new TableRowSorter<TableModel> ( model );
	List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey> ();
	sortKeys.add ( new RowSorter.SortKey ( 1 , SortOrder.ASCENDING ) );
	sortKeys.add ( new RowSorter.SortKey ( 0 , SortOrder.DESCENDING ) );
	sorter.setSortKeys ( sortKeys );
	table.setRowSorter ( sorter );

	label.setText ( String.valueOf ( sorter.getViewRowCount () ) );

	table.setDragEnabled ( false );
	table.setOpaque ( false/*true */);
	table.setShowHorizontalLines ( false );
	table.setDefaultRenderer ( Object.class , new DTableCellRenderer () );
	table.getTableHeader ().setReorderingAllowed ( false );
	try{
		    Class<?> configCls = Class.forName("damdariar.gui.swing.forms.gridform."+classType.getSimpleName()+"GridConfig");
		    GridConfig gridConfig = (GridConfig) configCls.newInstance();
    	    table.setAutoResizeMode(DTable.AUTO_RESIZE_OFF);
    		String[] propertyNames = model.getPropertyNames();
    		for(int i = 0 ; i < propertyNames.length ; i++ )
    		   table.getColumnModel().getColumn(i).setPreferredWidth(gridConfig.getColumnWidth(propertyNames[i]));
	}catch(Exception e){
		
	}	
    	  		
	table.getTableHeader ().setReorderingAllowed(true);
	table.getColumnModel().addColumnModelListener(this);
	scrollPane.setViewportView ( table );
	setLayout(new BorderLayout());
	table.setBackground(Color.blue);
	scrollPane.setBackground(Color.blue);
	
	add(searchField,BorderLayout.NORTH);
	add(scrollPane,BorderLayout.CENTER);
	add(southPanel,BorderLayout.SOUTH);
	
	scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	searchField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	
	table.addMouseListener(new TableMouseAdapter(this));
	table.addKeyListener(new TableKeyAdapter(this));
	table.addFocusListener(new TableFocusAdapter(this));
	}
	
	
	public AbstractBean   getSelectedRowBean(){
		try{
			int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
			if(rowIndex >= 0)
				return model.getBeanByRow(rowIndex);
		}catch(Exception e){
			
		}
		return null;
		
	}

	public void addGridRowSelectionListener(
			GridRowSelectionListener gridRowSelectionListener) {
		if(gridRowSelectionListeners == null)
			gridRowSelectionListeners = new ArrayList<GridRowSelectionListener>();
		gridRowSelectionListeners.add(gridRowSelectionListener);
	}

	public void delete(AbstractBean bean) {
		model.remove(bean);
		label.setText ( String.valueOf ( sorter.getViewRowCount () ) );
	}

	public void deleteSelectedRows(){
		int[] viewIndex  = table.getSelectedRows();
		if(viewIndex.length == 0)
			return;
		int yesOrNo = ConfirmDialog.showYesNoDialog(this,
				viewIndex.length > 1 ? deleteMultiRecords : deleteSingleRecord ,
				deleteDialogTitle,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.WARNING_MESSAGE,
			    null); 
		if(yesOrNo == 1 || yesOrNo == -1)
			return;
		List<AbstractBean> deletedBeans =  new ArrayList<AbstractBean>();
		for(int i = 0 ; i < viewIndex.length ; i++){
			AbstractBean bean = model.getBeanByRow(table.convertRowIndexToModel(viewIndex[i]));
			deletedBeans.add(bean);
			if(formTree != null)
				formTree.delete(bean);
		}	
		model.removeRows(deletedBeans);
		if(deletedBeans != null)
			fireRowsDeleted(deletedBeans);
	}
	
	public void fireRowsDeleted(List<AbstractBean> deletedBeans){
		if(gridRowSelectionListeners != null)
			for(int i = 0; i < gridRowSelectionListeners.size() ; i++)
				gridRowSelectionListeners.get(i).girdRowsDeleted(deletedBeans);
		
	}
	
	public void  fireGridRowSelectionChanged(AbstractBean selectedBean){
		if(gridRowSelectionListeners != null)
			for(int i = 0; i < gridRowSelectionListeners.size() ; i++)
				gridRowSelectionListeners.get(i).gridRowSelectionChanged(selectedBean);
		
		
	}
	
	
	public void save(AbstractBean bean) {
	    model.add(bean);	
	    label.setText ( String.valueOf ( sorter.getViewRowCount () ) );
	}

	

	
	JPopupMenu  getRightClickPopup(){
		if(popupMenu !=null)
			return popupMenu;
		popupMenu = new JPopupMenu();
		JMenuItem deleteItem = new JMenuItem(FormGrid.deleteMenuName,ImageUtil.getImageIcon("delete16.gif"));
		deleteItem.setHorizontalTextPosition(SwingConstants.RIGHT);
		popupMenu.add(deleteItem);
		popupMenu.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		deleteItem.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		deleteItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelectedRows();
			}});
		
		deleteItem.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseExited(MouseEvent e) {
				popupMenu.setVisible(false);
			}});
		
		return popupMenu;
		
	}
	
	public ApplicationDataModel getApplicationDataModel(){
		
		return new ApplicationDataModel(model.getModelLoader());
	}



	public void treeRowSelectionChanged(AbstractBean beanObj) {
	/*	if(!model.isAllData())
			return;
		Integer index = table.convertRowIndexToView(model.getModelRow(beanObj));
		table.setRowSelectionInterval(index,index);*/
	}


	@Override
	public void searchDone(Query query) {
		model.changeModel(query,this);
		
	}


	
	 public int print(Graphics g, PageFormat pageFormat, 
		        int pageIndex) throws PrinterException {
		 
		 		 
		     	Graphics2D  g2 = (Graphics2D) g;
		     	ImageIcon ico =  ImageUtil.getImageIcon("logo.png");
		     	Image img = ico.getImage();
		     	int imageX = 250;
			 	int imageY = (ico.getIconHeight() /2)+25;
			 		
		     	g2.setColor(Color.black);
		     	int fontHeight=g2.getFontMetrics().getHeight();
		     	int fontDesent=g2.getFontMetrics().getDescent();

		     	//leave room for page number
		     	double pageHeight = 
		     	  pageFormat.getImageableHeight()-fontHeight;
		     	double pageWidth = 
		     	  pageFormat.getImageableWidth();
		     	double tableWidth = (double) 
		          table.getColumnModel(
		          ).getTotalColumnWidth();
		     	double scale = 1; 
		     	if (tableWidth >= pageWidth) {
				scale =  pageWidth / tableWidth;
			}

		     	double headerHeightOnPage=
		                 (table.getTableHeader(
		                 ).getHeight()*scale)+(ico.getIconHeight());
		     	double tableWidthOnPage=tableWidth*scale;

		     	double oneRowHeight=(table.getRowHeight()+
		                      table.getRowMargin())*scale;
		     	int numRowsOnAPage=
		              (int)((pageHeight-headerHeightOnPage)/
		                                  oneRowHeight);
		     	double pageHeightForTable=oneRowHeight*
		     	                            numRowsOnAPage;
		     	int totalNumPages= 
		     	      (int)Math.ceil((
		                (double)table.getRowCount())/
		                                    numRowsOnAPage);
		     	if(pageIndex>=totalNumPages) {
		                      return NO_SUCH_PAGE;
		     	}

		     	g2.drawImage(img,imageX,imageY,ico.getIconWidth(),ico.getIconHeight(),ico.getImageObserver());
		     	g2.translate(pageFormat.getImageableX(), 
		                       pageFormat.getImageableY());
		//bottom center
		     	g2.drawString(" \u0635\u0641\u062d\u0647  "+":"+Util.getUnicodeNumer((pageIndex+1)+""),
		     	    (int)pageWidth/2-35, (int)(pageHeight
		     	    +fontHeight-fontDesent));

		     	g2.translate(0f,headerHeightOnPage);
		     	g2.translate(0f,-pageIndex*pageHeightForTable);

		     	//If this piece of the table is smaller 
		     	//than the size available,
		     	//clip to the appropriate bounds.
		     	if (pageIndex + 1 == totalNumPages) {
		           int lastRowPrinted = 
		                 numRowsOnAPage * pageIndex;
		           int numRowsLeft = 
		                 table.getRowCount() 
		                 - lastRowPrinted;
		           g2.setClip(0, 
		             (int)(pageHeightForTable * pageIndex),
		             (int) Math.ceil(tableWidthOnPage),
		             (int) Math.ceil(oneRowHeight * 
		                               numRowsLeft));
		     	}
		     	//else clip to the entire area available.
		     	else{    
		             g2.setClip(0, 
		             (int)(pageHeightForTable*pageIndex), 
		             (int) Math.ceil(tableWidthOnPage),
		             (int) Math.ceil(pageHeightForTable));        
		     	}

		     	g2.scale(scale,scale);
		     	table.paint(g2);
		     	
//		     	headerHeightOnPage = headerHeightOnPage - ico.getIconHeight();
		     	
		     	g2.scale(1/scale,1/scale);
		     	g2.translate(0f,pageIndex*pageHeightForTable);
		     	g2.translate(0f, -(headerHeightOnPage-(ico.getIconHeight())));
		     	
		     	g2.setClip(0, 0,
		     	  (int) Math.ceil(tableWidthOnPage), 
		          (int)Math.ceil(headerHeightOnPage));
		     	g2.scale(scale,scale);
		     	table.getTableHeader().paint(g2);
		     	//paint header at top

		     	return Printable.PAGE_EXISTS;
		   }

	
	public void printAction() {
		  PrinterJob pj=PrinterJob.getPrinterJob();
          pj.setPrintable(FormGrid.this);
          pj.printDialog();
          try{ 
            pj.print();
          }catch (Exception PrintException) {}
          

		
		
	}


	@Override
	public void statusChanged(int statusType, List<Object> listOfObjs) {
		
		if(statusType == DataModelStatusListener.MODEL_LOADING_FINISHED){
			statusPanel.clear();
			label.setText ( String.valueOf (listOfObjs != null ?  listOfObjs.size() :  0 ) );
		}
		
		else if(statusType == DataModelStatusListener.MODEL_LOADING_STARTED){
			statusPanel.setStatusMessage(" \u062f\u0631 \u062d\u0627\u0644 \u0628\u0627\u0631\u06af\u0630\u0627\u0631\u06cc \u062f\u0627\u062f\u0647 \u0647\u0627 \u060c \u0644\u0637\u0641\u0627 \u0635\u0628\u0631 \u0646\u0645\u0627\u06cc\u06cc\u062f", 
					ImageUtil.getImageIcon("loading.gif"));
		}
		
	}

	
	public JPanel getSouthPanel(){
		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.setOpaque(false);
		southPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JPanel  statusNfoundPanel = new JPanel(new GridLayout(1,2));
		statusNfoundPanel.setOpaque(false);
		statusNfoundPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		
		
		
		
		JButton button= new JButton(ImageUtil.getImageIcon("table_refresh.png"));
		button.setOpaque(true);
		button.setRolloverEnabled(true);
		button.setRolloverIcon(ImageUtil.getImageIcon("table_refresh_over.png"));
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setActionCommand("refresh");
		button.setToolTipText(" \u0628\u0627\u0631\u06af\u0630\u0627\u0631\u06cc \u0645\u062c\u062f\u062f \u062a\u0645\u0627\u0645 \u0631\u06a9\u0648\u0631\u062f\u0647\u0627");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				model.restoreOriginalModel();
			}});
		
		statusPanel = new DStatusPanel();
		
		statusNfoundPanel.add(label);
		statusNfoundPanel.add(statusPanel);
		southPanel.add(statusNfoundPanel,BorderLayout.EAST);
		southPanel.add(button,BorderLayout.WEST);
		
		return southPanel;
		
	}


	@Override
	public void columnAdded(TableColumnModelEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void columnMarginChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void columnMoved(TableColumnModelEvent e) {
		int  f = e.getFromIndex();
		int  t = e.getToIndex();
		if(f != t){
		  model.realIndex[f] = t;
		  model.realIndex[t] = f;
		}
		
	}


	@Override
	public void columnRemoved(TableColumnModelEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void columnSelectionChanged(ListSelectionEvent e) {
		
	}


	public void setTreeView(FormTree rightComponent) {
		this.formTree = rightComponent;
	}

}

class TableFocusAdapter extends FocusAdapter{
	
	private FormGrid formGrid;
	public TableFocusAdapter(FormGrid formGrid) {
		this.formGrid = formGrid;
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(formGrid.popupMenu !=null && formGrid.popupMenu.isVisible())
			formGrid.popupMenu.setVisible(false);
			
	}
	
	
}
class TableMouseAdapter extends MouseAdapter{
	private FormGrid formGrid;
	public TableMouseAdapter(FormGrid formGrid) {
		this.formGrid = formGrid;
	}

	public void mouseReleased(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON3 ){
			formGrid.getRightClickPopup().setLocation(e.getXOnScreen()-(formGrid.getRightClickPopup().getWidth()/2), e.getYOnScreen()-(formGrid.getRightClickPopup().getHeight()/2));
			formGrid.getRightClickPopup().setVisible(true);
			return;
		}
		formGrid.fireGridRowSelectionChanged(formGrid.getSelectedRowBean());
		if(formGrid.popupMenu != null && formGrid.popupMenu.isVisible())
			formGrid.popupMenu.setVisible(false);
	}
	
}

class TableKeyAdapter extends KeyAdapter{

	private FormGrid formGrid;
	public TableKeyAdapter(FormGrid formGrid) {
		this.formGrid = formGrid;
	}

	public void keyReleased(KeyEvent e) {
		formGrid.fireGridRowSelectionChanged(formGrid.getSelectedRowBean());
	}

}


class FounderRecordsLabel  extends DLabel{

	private static final long serialVersionUID = 1L;
	private String foundedRecords = " \u062a\u0639\u062f\u0627\u062f \u0631\u06a9\u0648\u0631\u062f \u06cc\u0627\u0641\u062a \u0634\u062f\u0647";
	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		super.setText(foundedRecords + " : " +text);
	}
	
}

