package damdariar.gui.swing.layout;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;

public class GridLayoutManager extends GridBagLayout {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints gc;
	private Container container;
	private double    defaultRowWeight = 0;
	private double    defaultColumnWeight = 0;
	private int    defaultWidth =  1 ;
	private int    defaultHeight = 1;
	private int    x             = 0;
	private int    y             = 0;
	private Insets insets   = new Insets(5,5,5,5);
	private int    ipadx = 0;
	private int    ipady = 0;
	

	
	public  GridLayoutManager (Container container){
		
		this.container = container;
		gc = new GridBagConstraints(x,y,defaultWidth,defaultHeight,defaultRowWeight,defaultColumnWeight,GridBagConstraints.LINE_START,GridBagConstraints.BOTH,insets,ipadx,ipady);
		container.setLayout(this);
		
		
	}
	
	public void setRowWeight(double rowWeight){
		
		gc.weightx = rowWeight;
	}
	
	public void setColumnWeight(double columnWeight){
		
		gc.weighty = columnWeight;
	}
	
	public void setCellWeight(double weightx,double weighty){
		gc.weightx = weightx;
		gc.weighty = weighty;		
	}
	
	
	public void setIpadX(int ipadx){
		
		gc.ipadx = ipadx;
	}
	
	public void setIpadY(int ipady){
		
		gc.ipady = ipady;
	}
	
	public void setIpad(int ipadx,int ipady){
		
		gc.ipadx = ipadx;
		gc.ipady = ipady;
	}
	
	public void setAnchor(int anchor){
		gc.anchor = anchor;
	}
	
	public GridBagConstraints getConstriant(){
		return gc;
	}
	
	public void gotoNewLine(){
		
		gc.gridx = x;
		gc.gridy = gc.gridy + gc.gridheight ;
	}
	

	public void add(JComponent comp){
		container.add(comp,gc);
		gc.gridx = gc.gridx + gc.gridwidth;

		
	}
	
	public void add(JComponent comp,int x,int  y){
		gc.gridx = x;
		gc.gridy = y;
		container.add(comp,gc);
		
	}
	
	public void reset(){
		    gc.weightx = defaultRowWeight = 0;
		    gc.weighty = defaultColumnWeight = 0;
		    gc.gridwidth = defaultWidth =  1 ;
		    gc.gridheight = defaultHeight = 1;
		    gc.gridx = x = 1;
		    gc.gridy = y  = 1;
		    gc.insets =  insets   = new Insets(5,5,5,5); 
		
	}

}
