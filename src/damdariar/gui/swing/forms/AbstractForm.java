package damdariar.gui.swing.forms;

import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.JTextArea;

import org.flexdock.docking.DockingConstants;
import org.flexdock.view.View;
import org.hibernate.type.Type;

import damdariar.beans.AbstractBean;
import damdariar.gui.editor.EditorI;
import damdariar.gui.property.GUIDefaultValues;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DScrollPane;
import damdariar.gui.swing.forms.listeners.BeanActionListener;
import damdariar.gui.swing.forms.listeners.GridRowSelectionListener;
import damdariar.gui.swing.forms.listeners.TreeRowSelectionListener;
import damdariar.gui.swing.layout.GridLayoutManager;
import damdariar.hibernate.HibernateUtil;

public class AbstractForm extends DScrollPane implements DockingConstants,GridRowSelectionListener,TreeRowSelectionListener,VetoableChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DPanel               contentPane= new DPanel();
	AbstractBean         $Form_Bean;
	Map<String,EditorI>   editorsMap;
	GridLayoutManager     layoutManager = new GridLayoutManager(contentPane);
	List<BeanActionListener> beanActionListenerList;
	boolean isDetailForm = false;
	String  linkedProperty = "";
	
	Class<?>                classType;
	private Integer parentId;
	protected  View view;

	AbstractForm(Class<?> classType){
	    this(classType,false,null,null);
		
	}
	
	AbstractForm(Class<?> classType,String[] editors){
	    this(classType,false,null,null,editors);
		
	}
	
	AbstractForm(Class<?> classType,boolean isDetailForm,String  linkedProperty ,Integer parentId){
		this(classType,isDetailForm,linkedProperty ,parentId,null);
		
	}
	
	AbstractForm(Class<?> classType,boolean isDetailForm,String  linkedProperty ,Integer parentId,String[] editors){
		this.isDetailForm = isDetailForm;
		this.linkedProperty = linkedProperty;
		this.parentId = parentId;
		applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		getViewport().add(contentPane);
		this.classType = classType;
		editorsMap = FormUtility.getEditors(classType,linkedProperty,this);
		if(editors != null)
		{
			for (int i = 0; i < editors.length; i++) {
			  editorsMap.remove(editors[i]);	
			}
		}	
		initializeFormBean();
		befroeLayoutComponents();
		layOutComponents();
		afterLayoutComponents();
		
	}
	
	
	
	public void  befroeLayoutComponents(){
		
	}
	
	public void  afterLayoutComponents(){
		
	}
	
	
	public DPanel getContentPanel(){
		return contentPane;
	}
	
	
	public void layOutComponents(){
		
	    int newLine = 2;
	    int editorCounter = 0;
		
		int SIZE_TYPE = FormUtility.FORM_MIN_FIELD_TYPE;
		if(editorsMap.size() <= 8 ){			
			 SIZE_TYPE = FormUtility.FORM_MIN_FIELD_TYPE;
			 newLine = 2;
		}
		if(editorsMap.size() > 8 /*&&  editorsMap.size() <= 16*/ ){
			 SIZE_TYPE = FormUtility.FORM_MEDIUM_FIELD_TYPE;
			 newLine = 3;
		}
/*		if(editorsMap.size()> 16 ){
			 SIZE_TYPE = FormUtility.FORM_LARGE_FIELD_TYPE;
			 newLine = 3;
		}*/
	    
		List<EditorI>  newLineNeededEditors =null;
	
		layoutManager.getConstriant().fill = GridBagConstraints.BOTH;
		
		EditorI[] editors = new EditorI[editorsMap.size()];
		Arrays.sort(editorsMap.values().toArray(editors),new Comparator<EditorI>(){

			public int compare(EditorI o1, EditorI o2) {
				if(o1.getEditorSequence() >= o2.getEditorSequence())
					return 1;
				if(o1.getEditorSequence() < o2.getEditorSequence())
					return -1;
				return 0;
			}});
	 	for(EditorI editor : editors){		
			 	
			 	if(!((JComponent)editor).isVisible())
			 		continue;
			 	
			 	FormUtility.initializeLocale((JComponent)editor);
			 	FormUtility.setPreferredSize(editor,SIZE_TYPE);
		
			 	if(isNeedNewLine(editor)){
			 		if(newLineNeededEditors == null)
			 			newLineNeededEditors = new ArrayList<EditorI>();
			 		newLineNeededEditors.add(editor);
			 		continue;
			 	}
			 		
			 	if(editorCounter % newLine == 0){
			 		layoutManager.gotoNewLine();
			 	}
				layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
			 	layoutManager.add(new DLabel(editor.getEditorProperty().getPropertyTranslation()));
				layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
			 	layoutManager.add((JComponent) editor);
			 	editorCounter++;
			}
		
		if(newLineNeededEditors != null)
			for(EditorI neededNewLineEditor : newLineNeededEditors){
				layoutManager.gotoNewLine();
				layoutManager.getConstriant().gridwidth = 1;
				layoutManager.getConstriant().gridheight = 1;
				layoutManager.getConstriant().anchor = GridBagConstraints.EAST;
			 	layoutManager.add(new DLabel(neededNewLineEditor.getEditorProperty().getPropertyTranslation()));
			 	layoutManager.getConstriant().gridwidth = 3;
				layoutManager.getConstriant().gridheight = 2;
				layoutManager.getConstriant().anchor = GridBagConstraints.CENTER;
				layoutManager.add((JComponent) neededNewLineEditor);
			}
			
		
		
	}
	
	/**
	 * @return the $Form_Bean
	 */
	public AbstractBean getForm_Bean() {
		return $Form_Bean;
	}

	/**
	 * @param form_Bean the $Form_Bean to set
	 */
	public void setForm_Bean(AbstractBean form_Bean) {
		$Form_Bean = form_Bean;
	}

	/**
	 * @return the editorsMap
	 */
	public Map<String, EditorI> getEditorsMap() {
		return editorsMap;
	}

	/**
	 * @param editorsMap the editorsMap to set
	 */
	public void setEditorsMap(Map<String, EditorI> editorsMap) {
		this.editorsMap = editorsMap;
	}

	/**
	 * @return the classType
	 */
	public Class<?> getClassType() {
		return classType;
	}

	/**
	 * @param classType the classType to set
	 */
	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}
	
	
	public void fillFormBean(){
		if(editorsMap == null)
			return;
		for(Entry<String, EditorI> entry : editorsMap.entrySet()){
			String propertyName = entry.getKey();
		 	EditorI editor  = entry.getValue(); 
		 	Object  editorValue = editor.getValue();
		 	setBeanValue(propertyName,editorValue,editor.getEditorProperty().getType());
		}
	}
	
	
	private void setBeanValue(String propertyName, Object value, Type type) {
		if($Form_Bean == null)
			return;

		try{
				Class<?> cls = $Form_Bean.getClass();
				String methodName = "set"+getCamelString(propertyName);
				Method setterMethod = cls.getMethod(methodName, FormUtility.getParameterType(type));
				setterMethod.invoke($Form_Bean, value);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getCamelString(String propertyName){
		
		return Character.toUpperCase(propertyName.charAt(0))+propertyName.substring(1);
	}
	
	
	public void saveAction(){
		if(!beforeSave())
			return;
		fillFormBean();
		HibernateUtil.saveOrUpdate($Form_Bean);
		fireSaveAction();
		afterSave();
		
	}
	
	
	public void fireSaveAction(){
		
		if(beanActionListenerList != null)
			for(int i = 0 ; i < beanActionListenerList.size() ; i++)
				beanActionListenerList.get(i).save($Form_Bean);
	}
	
	public void fireDeleteAction(){
		
		if(beanActionListenerList != null)
			for(int i = 0 ; i < beanActionListenerList.size() ; i++)
				beanActionListenerList.get(i).delete($Form_Bean);
	}
	
	protected boolean beforeSave() {
		return true;
	}
	
	protected void  afterSave(){
		
		
	};

	protected boolean beforeDelete() {
		return true;
	}
	
	

	public void deleteAction(){
		
		if($Form_Bean.getID() == null || $Form_Bean.getID() < 0)
			return ;
		if(!beforeDelete())
			return;
		HibernateUtil.delete($Form_Bean);
		fireDeleteAction();
		initializeFormBean();
	}
	
	public void newAction(){
		initializeFormBean();
	}


	
	public void resetEditors(){
		
		if(editorsMap == null)
			return;		
		for(Entry<String, EditorI> entry : editorsMap.entrySet()){
		 	EditorI editor  = entry.getValue();
//		 	editor.setValue(null);
		 	editor.setValue(GUIDefaultValues.get(editor.getEditorProperty().getBeanClass().getSimpleName(),
		 			editor.getEditorProperty().getPropertyName()));
		}
	}
	
	public  AbstractBean initializeFormBean(){
		resetEditors();
		try{
			Class<?>  bean = Class.forName(classType.getName());
			$Form_Bean  = (AbstractBean) bean.newInstance();
			if(isDetailForm)
				AbstractBean.setPropertyValue($Form_Bean, linkedProperty, parentId);
			return $Form_Bean;
		}catch(Exception e){
			
			
		}
		return null;
		
	}
	
	public double getResizedWeight(){
		
		return 0.5d;
	}
	
	public void fillEditorsFromBean(){
		
		for(Entry<String, EditorI> entry : editorsMap.entrySet()){
		 	EditorI editor  = entry.getValue();
		 	editor.setValue(AbstractBean.getPropertyValue($Form_Bean, entry.getKey()));
		}
		
	}

	public void gridRowSelectionChanged(AbstractBean beanObj) {
		initializeFormBean();
		$Form_Bean = beanObj;
		fillEditorsFromBean();
	}
	
	


	/**
	 * @return the beanActionListener
	 */
	public List<BeanActionListener> getBeanActionListener() {
		return beanActionListenerList;
	}


	/**
	 * @param beanActionListener the beanActionListener to set
	 */
	public void addBeanActionListener(BeanActionListener beanActionListener) {
		if(beanActionListenerList == null)
			beanActionListenerList = new ArrayList<BeanActionListener>();
		beanActionListenerList.add(beanActionListener);
	}
	
	
	public  boolean isNeedNewLine(EditorI editor){
		if(editor instanceof JTextArea)
			return true;
		return false;
		
	}

	@Override
	public void girdRowsDeleted(List<AbstractBean> deletedBeans) {
		if($Form_Bean != null && deletedBeans.contains($Form_Bean))
			initializeFormBean();
	}

	@Override
	public void treeRowSelectionChanged(AbstractBean beanObj) {
		gridRowSelectionChanged(beanObj);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		
	}
	
	public void setDetailFormInfo(boolean isDetailForm2,
			String linkedProperty2, Integer parentId2) {
		this.isDetailForm = isDetailForm2;
		this.linkedProperty = linkedProperty2;
		this.parentId = parentId2;
		if(isDetailForm && $Form_Bean != null)
			AbstractBean.setPropertyValue($Form_Bean, linkedProperty, parentId);
	}

	public void setView(View view){
		this.view = view;
	}


}
