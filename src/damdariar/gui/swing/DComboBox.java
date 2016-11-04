package damdariar.gui.swing;

import java.awt.ComponentOrientation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.jgoodies.looks.plastic.PlasticComboBoxUI;

import damdariar.gui.swing.forms.RightClickDialog;
import damdariar.gui.swing.renderers.DListCellRenderer;

public class DComboBox extends JComboBox{

	@Override
	public  void addMouseListener(MouseListener l) {
		if(l instanceof RightClickDialog)
		{ 
		  PlasticComboBoxUI ui = ((PlasticComboBoxUI)getUI());
		  ui.getArrowButton().addMouseListener(l);
		}else
		super.addMouseListener(l);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DComboBox() {
		super();
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setRenderer(new DListCellRenderer());
	}

	public DComboBox(ComboBoxModel model) {
		super(model);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setRenderer(new DListCellRenderer());
	}

	public DComboBox(Object[] items) {
		super(items);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setRenderer(new DListCellRenderer());
	}

	public DComboBox(Vector<?> items) {
		super(items);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setRenderer(new DListCellRenderer());
	}
	
	@Override
	public void fireVetoableChange(String propertyName, Object oldValue,
			Object newValue) throws PropertyVetoException {
		super.fireVetoableChange(propertyName, oldValue, newValue);
	}



}
