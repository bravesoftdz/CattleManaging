package damdariar.gui.editor;

import java.lang.reflect.Method;

import javax.swing.JComponent;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import damdariar.beans.AbstractBean;
import damdariar.beans.PropertyMetaData;
import damdariar.gui.swing.forms.RightClickDialog;
import damdariar.gui.swing.model.DComboBoxModel;

public class EditorFactory {
	
	
	public static JComponent getEditor(Class<Object>  cls){
		
		if(cls.equals(boolean.class))
			return new CheckBoxEditor();
		if(cls.equals(String.class))
			return new SimpleTextEditor();
	
		return new SimpleTextEditor();
		
	}
	public  static boolean isListBox(String preferredEditor){
		if(preferredEditor.indexOf('-') != -1)
			return true;
		return false;
	}
	
	public  static boolean isMultiComboBox(String preferredEditor){
		if(preferredEditor != null && !isListBox(preferredEditor))
			return true;
		return false;
	}
	
	public static String[] getListModel(String preferredEditor){
		try{
		String className = preferredEditor.substring(0,preferredEditor.indexOf('-'));
		String methodName = preferredEditor.substring(preferredEditor.indexOf('-')+1);
		Class<?> cls = Class.forName(className);
		Method modelMethod = cls.getMethod(methodName, null);
		return (String[]) modelMethod.invoke(null, null);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
		
	}
	
	public static EditorI getEditor(Class<?> cls, String propertyName,
			String propertyTranslation, Type type, boolean isMandatory,String preferredEditor,int editorSequence) {
		
		PropertyMetaData metaData = new PropertyMetaData(propertyName,propertyTranslation,isMandatory,cls,type);
		EditorI editor= null;
		if(preferredEditor != null ){
			if(isListBox(preferredEditor))
				editor = new ComboBoxEditor(new DComboBoxModel(getListModel(preferredEditor)));
			else
				{
				try{
					editor =  (EditorI) Class.forName(preferredEditor).newInstance();
				   }catch(Exception e){
					   e.printStackTrace();
				   }
				       
				}
		}
		else 
		if(AbstractBean.isIdentifier(cls,propertyName))
		{
	    	editor = new DMultiDisplayCombo(cls,AbstractBean.getIdentifierClass(cls,propertyName),metaData);
		}	
		else
		if(type.equals(Hibernate.STRING))
		{
			editor = new SimpleTextEditor();
		}
		else 
		if(type.equals(Hibernate.INTEGER))
		{
			editor = new NumberEditor();
		}
		else 
			if(type.equals(Hibernate.FLOAT))
			{
			   editor = new NumberFloatEditor();
			}
		else 
		if(type.equals(Hibernate.TIMESTAMP))
		{
		   editor = new DateEditor();
		}
		else
		if(type.equals(Hibernate.BOOLEAN))
		{
		   editor = new CheckBoxEditor();
		}
		else
		if(type.equals(Hibernate.BLOB)){
			editor = new DImageEditor();
			
		}	

		if(editor != null){	
			 editor.setEditorProperty(metaData);
			 editor.setEditorSequence(editorSequence);
		}
		
		new RightClickDialog(editor);
		return editor;
	}



}
