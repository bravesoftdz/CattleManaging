package damdariar.gui.swing.forms;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.forms.gridform.SimpleGrid;
import damdariar.images.ImageUtil;

public class simpleFormView extends DPanel implements ActionListener{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractForm topComponent;
	SimpleGrid   grid;
	private String[] toolbarNames;
	private JToolBar toolbar;
	JButton save;
	@SuppressWarnings("unchecked")
	public simpleFormView(Class<?> classType,String[] editors , String[] toolbarNames , String linkName){
		this.toolbarNames = toolbarNames;
		try{
        	String formName = "damdariar.gui.swing.forms."+classType.getSimpleName()+"Form";
        	Class<?> formClass = Class.forName(formName);
        	Constructor construct = formClass.getConstructor(new Class<?>[]{(new String[]{}).getClass()});
        	topComponent = (AbstractForm)construct.newInstance(new Object[]{editors});
        }catch(Exception e){
        	topComponent = new AbstractForm(classType,editors);
        }
        
        
        
		DPanel topPanel = new DPanel(new BorderLayout());
		toolbar = createToolbar();
		topPanel.add(toolbar,BorderLayout.NORTH);
		topPanel.add(topComponent,BorderLayout.CENTER);
    	grid = new SimpleGrid(classType,null,null,linkName);
        setLayout(new GridLayout(2,1));
        
    	grid.addGridRowSelectionListener(topComponent);
    	topComponent.addBeanActionListener(grid);
        
    	add(topPanel);
		add(grid);
	}
	
	
	public JToolBar createToolbar(){
		
		JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
		toolbar.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		toolbar.setOpaque(false);
		Icon deleteIco = ImageUtil.getImageIcon("row_delete.png");
		JButton add    = new JButton(ImageUtil.getImageIcon("row_add.png"));
		add.setPreferredSize(new Dimension(24,24));
		add.setText(toolbarNames[0]);
		JButton delete = new JButton(deleteIco);
		delete.setPreferredSize(new Dimension(24,24));
		delete.setText(toolbarNames[2]);
		delete.setActionCommand("delete");
		delete.addActionListener(this);
		add.addActionListener(this);
		add.setActionCommand("add");
		Icon saveIco = ImageUtil.getImageIcon("save24.gif");
		save  = new JButton(saveIco);
		save.setText(toolbarNames[1]);
		save.setPreferredSize(new Dimension(24,24));
		toolbar.add(add);
		toolbar.add(save);
		toolbar.add(delete);
		
		return toolbar;
		
	}


	public SimpleGrid getGrid() {
		return grid;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("add"))
			topComponent.newAction();
		else if(e.getActionCommand().equalsIgnoreCase("delete"))
			topComponent.deleteAction();
	}
}
