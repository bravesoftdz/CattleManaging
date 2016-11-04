

package damdariar.gui.swing.forms.gridform;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
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
import damdariar.gui.property.GUIDefaultValues;
import damdariar.gui.swing.ConfirmDialog;
import damdariar.gui.swing.DEditableTable;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.editors.DTableCellEditor;
import damdariar.gui.swing.forms.listeners.AdvanceSearchListener;
import damdariar.gui.swing.forms.listeners.DataModelStatusListener;
import damdariar.gui.swing.forms.listeners.EditablGridAcionListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.model.ApplicationDataModel;
import damdariar.gui.swing.model.DEditableTableModel;
import damdariar.gui.swing.renderers.DTableCellRenderer;
import damdariar.images.ImageUtil;


	public class EditableGrid extends DPanel implements ActionListener,AdvanceSearchListener,DataModelStatusListener, TableColumnModelListener ,VetoableChangeListener,GridRowSelectionListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public  DEditableTable table;
		public  DPanel searchPanel;
		private TableRowSorter<TableModel> sorter;
		private DScrollPane scrollPane;
		private DEditableTableModel model;
		JPopupMenu popupMenu;
		
		
		JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
		
		 static String deleteMultiRecords = " \u0622\u06cc\u0627 \u0645\u06cc \u062e\u0648\u0627\u0647\u064a\u062f \u0631\u06a9\u0648\u0631\u062f\u0647\u0627\u06cc \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u0631\u0627 \u067e\u0627\u06a9 \u0646\u0645\u0627\u064a\u064a\u062f\u061f";
		 static String deleteSingleRecord = " \u0622\u06cc\u0627 \u0645\u06cc \u062e\u0648\u0627\u0647\u064a\u062f \u0631\u06a9\u0648\u0631\u062f \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u0631\u0627 \u067e\u0627\u06a9 \u0646\u0645\u0627\u064a\u064a\u062f\u061f";
		 static String deleteMenuName = " \u0631\u06a9\u0648\u0631\u062f\u0647\u0627\u06cc \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u062f\u0647 \u067e\u0627\u06a9 \u0634\u0648\u062f";
		 static String deleteDialogTitle = " \u062a\u0627\u064a\u064a\u062f \u0639\u0645\u0644 \u062d\u0630\u0641 \u0631\u06a9\u0648\u0631\u062f";
		
		
		List<GridRowSelectionListener> gridRowSelectionListeners;
		private Class<?> classType;
		private String[] columnsNotTobeShow;
		private String linkName;
		private Integer linkValue;
		private EditablGridAcionListener editablGridAcionListener;

		
		public EditableGrid(Class<?> classType) {
			init(classType,null,null);
		}

		public EditableGrid(Class<?> classType,String linkName,String[] columnsNotTobeShow) {
			init(classType,linkName,columnsNotTobeShow);
		}

		public EditableGrid(Class<?> classType,String linkName) {
			init(classType,null,null);
		}

		public  void init(Class<?> classType,String linkName,String[] columnsNotToBeShown){
			
			this.classType = classType;
			this.columnsNotTobeShow = columnsNotToBeShown; 
			this.linkName = linkName;
		
			List<DataModelStatusListener> dataModelStatusListeners
			 = new ArrayList<DataModelStatusListener>();
			dataModelStatusListeners.add(this);
			
			model = new DEditableTableModel(classType,null,columnsNotTobeShow,dataModelStatusListeners);
			table = new DEditableTable ( model);
			scrollPane = new DScrollPane ();
		

		sorter = new TableRowSorter<TableModel> ( model );
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey> ();
		sortKeys.add ( new RowSorter.SortKey ( 1 , SortOrder.ASCENDING ) );
		sortKeys.add ( new RowSorter.SortKey ( 0 , SortOrder.DESCENDING ) );
		sorter.setSortKeys ( sortKeys );
		table.setRowSorter ( sorter );


		table.setDragEnabled ( false );
		table.setOpaque ( false);
		table.setShowHorizontalLines ( false );
		table.setDefaultRenderer ( Object.class , new DTableCellRenderer () );
		table.setDefaultEditor(Object.class, new DTableCellEditor(model,this));
		table.getTableHeader ().setReorderingAllowed ( false );
		try{
			    Class<?> configCls = Class.forName("damdariar.gui.swing.forms.gridform."+classType.getSimpleName()+"GridConfig");
			    GridConfig gridConfig = (GridConfig) configCls.newInstance();
	    	    table.setAutoResizeMode(DEditableTable.AUTO_RESIZE_OFF);
	    		String[] propertyNames = model.getPropertyNames();
	    		for(int i = 0 ; i < propertyNames.length ; i++ )
	    		   table.getColumnModel().getColumn(i).setPreferredWidth(gridConfig.getColumnWidth(propertyNames[i]));
		}catch(Exception e){
			
		}	
	    	
		Icon deleteIco = ImageUtil.getImageIcon("row_delete.png");
		JButton add    = new JButton(ImageUtil.getImageIcon("row_add.png"));
		add.setPreferredSize(new Dimension(24,24));
		add.setSize(24,24);
		JButton delete = new JButton(deleteIco);
		delete.setPreferredSize(new Dimension(24,24));
		delete.setSize(24,24);
		delete.setActionCommand("delete");
		add.setActionCommand("add");
		add.addActionListener(this);
		delete.addActionListener(this);
		toolbar.add(add);
		toolbar.add(delete);
		toolbar.setOpaque(false);
		
		table.getTableHeader ().setReorderingAllowed(true);
		table.getColumnModel().addColumnModelListener(this);
		scrollPane.setViewportView ( table );
		setLayout(new BorderLayout());
		table.setBackground(Color.blue);
		scrollPane.setBackground(Color.blue);
		
		add(scrollPane,BorderLayout.CENTER);
		add(toolbar,BorderLayout.EAST);
		
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		table.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		table.addMouseListener(new EditableTableMouseAdapter(this));
		table.addKeyListener(new EditableTableKeyAdapter(this));
		table.addFocusListener(new EditableTableFocusAdapter(this));
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
			for(int i = 0 ; i < viewIndex.length ; i++)
				deletedBeans.add(model.getBeanByRow(table.convertRowIndexToModel(viewIndex[i])));
			model.removeRows(deletedBeans);
			if(deletedBeans != null)
				fireRowsDeleted(deletedBeans);
		}
		
		
		public void changeTable(Integer linkValue){
			this.linkValue = linkValue;
			model.changeModel(" "+ linkName + " = " + linkValue+ " ");
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
		
		
		public void save() {
		    model.save();	
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


		
		 

		@Override
		public void statusChanged(int statusType, List<Object> listOfObjs) {
/*			if(listOfObjs != null && listOfObjs.size() != 0){
				AbstractBean bean = (AbstractBean)listOfObjs.get(table.convertRowIndexToView(0));
				 table.addRowSelectionInterval(0, 0);
				 fireGridRowSelectionChanged(bean);
				 
			}*/
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void vetoableChange(PropertyChangeEvent evt)
				throws PropertyVetoException {
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equalsIgnoreCase("add")){
				try{
					Class<?>  bean = Class.forName(classType.getName());
					AbstractBean beanObj  = (AbstractBean) bean.newInstance();
					AbstractBean.setPropertyValue(beanObj, linkName, linkValue);
					for(String propertyName:model.getPropertyNames()){
					 	AbstractBean.setPropertyValue(beanObj, propertyName, 
					 			GUIDefaultValues.get(classType.getSimpleName(),
							 		propertyName));						
					}
					if(editablGridAcionListener != null)
						editablGridAcionListener.addAction(beanObj);
					model.add(beanObj);
				}catch(Exception e){
					
					
				}
			}
			else if(arg0.getActionCommand().equalsIgnoreCase("delete")){
				int rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
				if(rowIndex >= 0)
					model.remove(model.getBeanByRow(rowIndex));
			}
		}

		
		public void addEditablGridAcionListener(EditablGridAcionListener editablGridAcionListener){
			
			this.editablGridAcionListener = editablGridAcionListener;
		}
		public Integer getLinkValue() {
			return linkValue;
		}

		@Override
		public void girdRowsDeleted(List<AbstractBean> deletedBeans) {
			
		}

		@Override
		public void gridRowSelectionChanged(AbstractBean beanObj) {
			if((linkValue == null && beanObj != null) || (linkValue != beanObj.getID())){
					model.save();
					changeTable(beanObj.getID());
			}
		}

	}

	class EditableTableFocusAdapter extends FocusAdapter{
		
		private EditableGrid formGrid;
		public EditableTableFocusAdapter(EditableGrid formGrid) {
			this.formGrid = formGrid;
		}
		@Override
		public void focusLost(FocusEvent e) {
			if(formGrid.popupMenu !=null && formGrid.popupMenu.isVisible())
				formGrid.popupMenu.setVisible(false);
				
		}
		
		
	}
	class EditableTableMouseAdapter extends MouseAdapter{
		private EditableGrid formGrid;
		public EditableTableMouseAdapter(EditableGrid formGrid) {
			this.formGrid = formGrid;
		}

		public void mouseReleased(MouseEvent e) {
			
			if(e.getButton() == MouseEvent.BUTTON3 ){
//				formGrid.getRightClickPopup().setLocation(e.getXOnScreen(), e.getYOnScreen());
				formGrid.getRightClickPopup().setLocation(e.getXOnScreen()-(formGrid.getRightClickPopup().getWidth()/2), e.getYOnScreen()-(formGrid.getRightClickPopup().getHeight()/2));
				formGrid.getRightClickPopup().setVisible(true);
				return;
			}
			formGrid.fireGridRowSelectionChanged(formGrid.getSelectedRowBean());
			if(formGrid.popupMenu != null && formGrid.popupMenu.isVisible())
				formGrid.popupMenu.setVisible(false);
		}
		
	}

	class EditableTableKeyAdapter extends KeyAdapter{

		private EditableGrid formGrid;
		public EditableTableKeyAdapter(EditableGrid grid) {
			this.formGrid = grid;
		}

		public void keyReleased(KeyEvent e) {
			formGrid.fireGridRowSelectionChanged(formGrid.getSelectedRowBean());
		}

	}



