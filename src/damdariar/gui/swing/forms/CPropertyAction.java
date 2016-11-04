package damdariar.gui.swing.forms;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import org.flexdock.dockbar.DockLabelListener;
import org.flexdock.dockbar.DockbarManager;
import org.flexdock.docking.Dockable;

import damdariar.gui.Main;
import damdariar.gui.swing.forms.gridform.FormGrid;
import damdariar.gui.swing.forms.listeners.BeanActionListener;

public class CPropertyAction {
	
	public static AbstractAction zayemanAction ;
	public static AbstractAction talghihAction ;
	public static AbstractAction bimariAction ;
	public static AbstractAction exitAction ;
	
	
	public static void registerAction(String name,AbstractForm cpropertyForm,DockLabelListener dockLabelListener){
		AbstractAction action = null;
		KeyStroke stroke = null;
		
		if(name.equalsIgnoreCase("Zayeman")){
			 zayemanAction = action = getZayemanAction(dockLabelListener);
			 stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
		}
		else if (name.equalsIgnoreCase("Talghih")){
			talghihAction = action = getTalghihAction(dockLabelListener);
			 stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
		} 
		else if (name.equalsIgnoreCase("Bimari")){
			 bimariAction = action = getBimariAction(dockLabelListener);
			 stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
		} 
		else if (name.equalsIgnoreCase("Exit")){
			 exitAction = action = getExitAction(dockLabelListener);
			 stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
		}

			Main.instance.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, name);
			Main.instance.getRootPane().getActionMap().put(name,action);
		
			List<BeanActionListener> list = cpropertyForm.getBeanActionListener();
			for(BeanActionListener beanListener:list){
			
				if(beanListener instanceof FormGrid){
					FormGrid grid = (FormGrid)beanListener;
					grid.table.getInputMap(JComponent.WHEN_FOCUSED).put(stroke, name);
					grid.table.getActionMap().put(name,action);
				}
				
				else if(beanListener instanceof FormTree){
					FormTree tree = (FormTree)beanListener;
					tree.tree.getInputMap(JComponent.WHEN_FOCUSED).put(stroke, name);
					tree.tree.getActionMap().put(name,action);
				}
			}
			
		
		
		
	}
	
	
	public static AbstractAction getZayemanAction(DockLabelListener dockLabelListener){
		if(zayemanAction != null)
			return zayemanAction;
		 return  new CAction(dockLabelListener,"ZayemanForm",CPropertyForm.zayemanView);
		}

	
	
	public static AbstractAction getTalghihAction(DockLabelListener dockLabelListener){
		if(talghihAction != null)
			return talghihAction;
		return new CAction(dockLabelListener,"TalghihForm",CPropertyForm.talghihView);
					
	}
	
	
	public static AbstractAction getBimariAction(DockLabelListener dockLabelListener){
		if(bimariAction != null)
			return bimariAction;
		return new CAction(dockLabelListener,"BimariForm",CPropertyForm.bimariView);

	}
	
	public static AbstractAction getExitAction(DockLabelListener dockLabelListener){
		if(exitAction != null)
			return exitAction;
		return new CAction(dockLabelListener,"ExitForm",CPropertyForm.exitView);

	}
}

class  disposeActionMap extends Thread{
	AbstractForm  cpropertyForm;
	disposeActionMap(AbstractForm cpropertyForm){
		this.cpropertyForm = cpropertyForm;
	}
	
	public void run(){
		
		String[] actionNames = new String[]{"Zayeman","Talghih","Bimari","Exit"};
		KeyStroke[] strokes = new KeyStroke[]{
	 		KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
			KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),
			KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),
			KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0)
		};
		for(int i = 0 ; i < 4 ; i ++){
			
			Main.instance.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).remove(strokes[i]);
			Main.instance.getRootPane().getActionMap().remove(actionNames[i]);
		
			List<BeanActionListener> list = cpropertyForm.getBeanActionListener();
			for(BeanActionListener beanListener:list){
			
				if(beanListener instanceof FormGrid){
					FormGrid grid = (FormGrid)beanListener;
					grid.table.getInputMap(JComponent.WHEN_FOCUSED).remove(strokes[i]);
					grid.table.getActionMap().remove(actionNames[i]);
				}
				
				else if(beanListener instanceof FormTree){
					FormTree tree = (FormTree)beanListener;
					tree.tree.getInputMap(JComponent.WHEN_FOCUSED).remove(strokes[i]);
					tree.tree.getActionMap().remove(actionNames[i]);
				}
			}
		}
		
	}
}

class CAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DockLabelListener dockLabelListener;
	private String dockableId;
	private Dockable dockable;
	CAction(DockLabelListener dockLabelListener,String dockableId,Dockable dockable){
		this.dockLabelListener = dockLabelListener;
		this.dockableId = dockableId;
		this.dockable = dockable;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(dockLabelListener != null)
			dockLabelListener.mouseEntered();
		DockbarManager manager = DockbarManager.getInstance(Main.instance);
		Dockable dockable = manager.getActiveDockable();
		if(dockable != null){
			if(dockable.equals(this.dockable))
				manager.deactivate(dockableId);	
			else
			{
				manager.deactivate(dockable.getPersistentId());
				manager.setActiveDockable(dockableId);
			}
		}
		else 
			manager.setActiveDockable(dockableId);
	}
	
	
}