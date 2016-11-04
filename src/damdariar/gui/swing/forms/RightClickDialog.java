package damdariar.gui.swing.forms;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.hibernate.Hibernate;

import damdariar.beans.CDefaultValues;
import damdariar.gui.editor.DateEditor;
import damdariar.gui.editor.DateTimeEditor;
import damdariar.gui.editor.EditorI;
import damdariar.gui.property.GUIDefaultValues;
import damdariar.hibernate.HibernateUtil;

public class RightClickDialog extends JPopupMenu implements MouseListener,ActionListener{
	
	private EditorI editor;

	public RightClickDialog(EditorI editor){
		this.editor = editor;		
		((JComponent)editor).addMouseListener(this);
		JMenuItem menuItem = new JMenuItem(" \u0627\u064a\u0646 \u0645\u0642\u062f\u0627\u0631 \u0631\u0627 \u0628\u0647 \u0639\u0646\u0648\u0627\u0646 \u067e\u06cc\u0634 \u0641\u0631\u0636 \u0642\u0631\u0627\u0631 \u0628\u062f\u0647");
		menuItem.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuItem.setActionCommand("menuItem");
		menuItem.addActionListener(this);
		menuItem.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseExited(MouseEvent e) {
				setVisible(false);
			}});
		add(menuItem);
		if(editor instanceof DateEditor || editor instanceof DateTimeEditor){
			JMenuItem  dateItem = new JMenuItem(" \u062a\u0627\u0631\u06cc\u062e \u062c\u0627\u0631\u06cc \u0628\u0647 \u0639\u0646\u0648\u0627\u0646 \u0645\u0642\u062f\u0627\u0631 \u067e\u06cc\u0634 \u0641\u0631\u0636 \u0627\u0646\u062a\u062e\u0627\u0628 \u0634\u0648\u062f");
			dateItem.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			dateItem.setActionCommand("dateItem");
			dateItem.addActionListener(this);
			add(dateItem);
		}
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent ) {
		if((mouseEvent.getModifiers() & InputEvent.BUTTON3_MASK)
		== InputEvent.BUTTON3_MASK){
			setLocation(mouseEvent.getXOnScreen()-getWidth()/2, mouseEvent.getYOnScreen()-(getHeight()/2));
			setVisible(true);
		}

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			
			String className = editor.getEditorProperty().getBeanClass().getSimpleName();
			String editorName = editor.getEditorProperty().getPropertyName();
			CDefaultValues defaultValue = (CDefaultValues) HibernateUtil.getObject(CDefaultValues.class, " Class_Name = '" + className + "' and Editor_Name = '" + editorName +"'");
			
			if(defaultValue == null){
				defaultValue = new CDefaultValues();
				defaultValue.setClassName(className);
				defaultValue.setEditorName(editorName);
			}
			
			if(editor.getValue() == null && !e.getActionCommand().equalsIgnoreCase("dateItem"))
				defaultValue.setDefaultValue(null);
			else{	
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ObjectOutputStream oos = new ObjectOutputStream(baos);
		        oos.writeObject(e.getActionCommand().equalsIgnoreCase("dateItem") ? "sysdate" : editor.getValue());
		        oos.flush();
		        oos.close();
		        byte[] data = baos.toByteArray();
		        Blob blob = Hibernate.createBlob(data);
		        defaultValue.setDefaultValue(blob);
			}
			
			HibernateUtil.saveOrUpdate(defaultValue);
			GUIDefaultValues.put(className,editorName,e.getActionCommand().equalsIgnoreCase("dateItem") ? "sysdate" : editor.getValue());
	        setVisible(false);
	        
	        
		}catch(Exception exp){
			
		}
		
	}


}
