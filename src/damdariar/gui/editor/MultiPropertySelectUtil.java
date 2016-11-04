package damdariar.gui.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JWindow;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.forms.listeners.PropertyDisplaySelectionListener;
import damdariar.images.ImageUtil;

public class MultiPropertySelectUtil {
	
	Component parent;
	JWindow win = new JWindow();
	private String[] displayPropertis;
	private String[] propertyTranlations;
	int     selectedProperty = 0;
	int     oldSelectedProperty = 0;
	
	static int  xStep  = 10;
	static int  yStep  = 10;
	static int  width  = 150;
	static int  height = 150;
	
	private PropertyDisplaySelectionListener displaySelectionListener;
	MultiPropertySelectUtil(Component parent,PropertyDisplaySelectionListener displaySelectionListener,String[] displayPropertis ,	String[] propertyTranlations){
	 this.displayPropertis = displayPropertis;
	 this.propertyTranlations = propertyTranlations;
	 this.displaySelectionListener = displaySelectionListener;
	 this.parent = parent;
	 config();
		
	}
	
	public void config(){
		
		DPanel confirmPanel = new DPanel();
		JButton okButton = new JButton( "\u062a\u0627\u06cc\u06cc\u062f", new ImageIcon(ImageUtil.class.getResource("ok16.gif")));
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if(oldSelectedProperty != selectedProperty){
					displaySelectionListener.propertySelectionChanged(displayPropertis[selectedProperty]);
					oldSelectedProperty = selectedProperty;
				}
				win.setVisible(false);
			}});
		
		JButton cancelButton = new JButton( " \u0627\u0646\u0635\u0631\u0627\u0641", new ImageIcon(ImageUtil.class.getResource("cancel16.gif")));
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				win.setVisible(false);
			}
});
		
		confirmPanel.add(okButton);
		confirmPanel.add(cancelButton);
		confirmPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		

			
		ButtonGroup  buttonGroup = new ButtonGroup();
		DPanel  radioGroupPanel = new DPanel(new GridLayout(propertyTranlations.length,1));
		win.setLayout(new BorderLayout());
		win.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	
		for(int i = 0 ; i < propertyTranlations.length ; i++){
			JRadioButton button = new JRadioButton(propertyTranlations[i]);
			button.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            if( i == selectedProperty)
            	button.setSelected(true);
            button.setActionCommand(i+"");
            button.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					selectedProperty = Integer.parseInt(e.getActionCommand());
					
				}});
            buttonGroup.add(button);
            radioGroupPanel.add(button);
            
		}
		win.add(radioGroupPanel,BorderLayout.CENTER);
		win.add(confirmPanel,BorderLayout.SOUTH);
		radioGroupPanel.setOpaque(true);
		radioGroupPanel.setBackground(Color.white);
		
	}
	
	JWindow  showPropertySelectionWindow(int x,int y){
		win.setSize(width,0);
		win.setLocation(x < width ? width-x : x-width , y+15);
		win.setVisible(true);	
		(new Timer(this)).start();
		return win;
	}

	public void dispose() {
		win.dispose();
		win=null;
	}
	

}

class Timer extends Thread {

	private MultiPropertySelectUtil propertyUtil;
 	Timer(MultiPropertySelectUtil propertyUtil){
		
		this.propertyUtil = propertyUtil;
	}
	public void run(){
		try{
			while(propertyUtil.win.getHeight() < MultiPropertySelectUtil.height){
			propertyUtil.win.setSize(MultiPropertySelectUtil.width  ,
									 propertyUtil.win.getSize().height + MultiPropertySelectUtil.yStep);
			 sleep(10);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}



