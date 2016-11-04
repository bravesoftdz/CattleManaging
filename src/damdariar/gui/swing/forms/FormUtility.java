package damdariar.gui.swing.forms;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import java.sql.Blob;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.hibernate.Hibernate;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import damdariar.gui.editor.EditorFactory;
import damdariar.gui.editor.EditorI;
import damdariar.gui.swing.forms.editorvisibility.EditorVisibility;
import damdariar.gui.swing.forms.editorvisibility.GridEditorVisibility;
import damdariar.hibernate.HibernateUtil;
import damdariar.resource.ResourceUtil;

public class FormUtility {
	
	public static int  FORM_MIN_FIELD_TYPE = 1; 
	public static int  FORM_MEDIUM_FIELD_TYPE = 2;
	public static int  FORM_LARGE_FIELD_TYPE = 3;
	

	
	public static Map<String,EditorI> getEditors(Class<?> cls,String linkedProperty,VetoableChangeListener listener){
		EditorVisibility visibilotyClass = getEditorVisibilityClass(cls);
		Map<String,EditorI>  editorsMap = new HashMap<String, EditorI>();
		ClassMetadata metaData = HibernateUtil.getMetaData(cls);
		String propertyNames[] = metaData.getPropertyNames();
		boolean[] nullabalities = metaData.getPropertyNullability();
		String    propertyTranlations[] = new String[propertyNames.length];
		Type[] types = metaData.getPropertyTypes();
		for (int i = 0; i <  propertyTranlations.length; i++) {
			if(!visibilotyClass.isVisible(propertyNames[i]) || propertyNames[i].equalsIgnoreCase(linkedProperty))
				continue;
			propertyTranlations[i]  = ResourceUtil.getPropertyTranslation(cls.getSimpleName(), propertyNames[i]);
			String   preferredEditor = ResourceUtil.getPropertyEditorPreference(cls.getSimpleName(), propertyNames[i]);
			int      editorSequence  =  ResourceUtil.getEditorSequence(cls.getSimpleName(), propertyNames[i]);
			EditorI editor = EditorFactory.getEditor(cls,propertyNames[i],propertyTranlations[i],types[i],!nullabalities[i],preferredEditor,editorSequence);
			if(listener != null)
				((JComponent)editor).addVetoableChangeListener(listener);
			editorsMap.put(propertyNames[i],editor);
		}
		
	    return editorsMap;		
			
		
	}
	public static Map<String,EditorI> getEditors(Class<?> cls){
		return getEditors(cls,"",null);
	}
	

	public static EditorI   getEditor(Class<?> cls,ClassMetadata metaData ,String propertyName){
		String propertyTranlation  = ResourceUtil.getPropertyTranslation(cls.getSimpleName(), propertyName);
		String   preferredEditor = ResourceUtil.getPropertyEditorPreference(cls.getSimpleName(), propertyName);
		int      editorSequence  =  ResourceUtil.getEditorSequence(cls.getSimpleName(), propertyName);
		EditorI editor = EditorFactory.getEditor(cls,propertyName,propertyTranlation,metaData.getPropertyType(propertyName),false,preferredEditor,editorSequence);
		return editor;
	}
	
	public static Class<?>[] getParameterType(Type type){
		
		Class<?>[]  parameterType=null; 
		if(type.equals(Hibernate.STRING))
		{
			parameterType = new Class[]{String.class}; 
		}
		else 
		if(type.equals(Hibernate.INTEGER))
		{
			parameterType = new Class[]{Integer.class}; 
		}
		else
		if(type.equals(Hibernate.FLOAT))
		{
			parameterType = new Class[]{Float.class}; 
		}
		else 
		if(type.equals(Hibernate.TIMESTAMP))
		{
			parameterType = new Class[]{Date.class}; 
		}
		else
		if(type.equals(Hibernate.BOOLEAN))
		{
			parameterType = new Class[]{Boolean.class}; 
		}
		else
		if(type.equals(Hibernate.BLOB))
		{
				parameterType = new Class[]{Blob.class}; 
		}

		return parameterType;
		
	}
	
	
	public static EditorI  setPreferredSize(EditorI editor , int FORM_FIELD_SIZE_TYPE){
		
		Dimension defaultDimension = null;
		if(FORM_FIELD_SIZE_TYPE == FORM_MIN_FIELD_TYPE)
		    defaultDimension = new Dimension(140,25);
		else if(FORM_FIELD_SIZE_TYPE == FORM_MEDIUM_FIELD_TYPE)
			defaultDimension = new Dimension(140,25);
		else
			defaultDimension = new Dimension(80,25);
		
		((JComponent)editor).setPreferredSize(defaultDimension);
		if(editor instanceof JTextArea)
			((JComponent)editor).setPreferredSize(new Dimension((int)defaultDimension.getWidth() * 2,(int)defaultDimension.getHeight()* 2));
		
		 return editor;
		
	}
	
	
	
	
	public static void initializeLocale(Component comp){
		
//		new ComponentLocaleHandler(comp);
	}
	
	public static EditorVisibility getEditorVisibilityClass(Class<?> classType){
		
		try{
        	String formName = "damdariar.gui.swing.forms.editorvisibility."+classType.getSimpleName()+"EditorVisibility";
        	Class<?> formClass = Class.forName(formName);
        	return (EditorVisibility) formClass.newInstance();
        }catch(Exception e){
        	return  new EditorVisibility();
        }
		
	}
	
	public static GridEditorVisibility getGridEditorVisibilityClass(Class<?> classType){
		
		try{
        	String formName = "damdariar.gui.swing.forms.editorvisibility."+classType.getSimpleName()+"GridEditorVisibility";
        	Class<?> formClass = Class.forName(formName);
        	return (GridEditorVisibility) formClass.newInstance();
        }catch(Exception e){
        	return  new GridEditorVisibility();
        }
		
	}
	
	
	public static boolean isVisible(Class<?> classType,String propertyName){
	   EditorVisibility  editorVisibiltyObj=null; 
		try{
        	String formName = "damdariar.gui.swing.forms.editorvisibility."+classType.getSimpleName()+"EditorVisibility";
        	Class<?> formClass = Class.forName(formName);
        	editorVisibiltyObj =  (EditorVisibility) formClass.newInstance();
        }catch(Exception e){
        	editorVisibiltyObj =  new EditorVisibility();
        }
        
        return editorVisibiltyObj.isVisible(propertyName);
		
	}
/*	public static AbstractForm getTopComponent(Component cmp){
		return (AbstractForm)((AbstractFormContentPane)(((JTabbedPane)cmp.getParent().getParent().getParent()).getComponentAt(1))).topComponent;
	}*/
	
}

class ComponentLocaleHandler extends FocusAdapter{
	Component comp;
	Locale farsiLocal = new Locale("fa","IR");
	boolean isLocaleInitialized = false;
	public ComponentLocaleHandler(Component comp){
		this.comp = comp;
		this.comp.addFocusListener(this);
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		if(!isLocaleInitialized && comp != null && comp.getInputContext() != null){
			comp.getInputContext().selectInputMethod(new Locale("fa","IR"));
			isLocaleInitialized = true;
		}
	}
	
	
	


	
	
	
}
