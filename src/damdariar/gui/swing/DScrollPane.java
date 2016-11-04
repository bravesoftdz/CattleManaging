package damdariar.gui.swing;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class DScrollPane extends JScrollPane {


	boolean  isAdjusted = false;
	public DScrollPane() {
		super();
		config();
	}

	public DScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
		config();
	}

	public DScrollPane(Component view) {
		super(view);
		config();
	}

	public DScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
		config();
	}

	
	public void config(){
		
		setOpaque(false);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JScrollBar bar = getHorizontalScrollBar();
		if(bar != null){
		 bar.addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(!isAdjusted){ 
					getHorizontalScrollBar().setValue(getHorizontalScrollBar().getMinimum());
					isAdjusted = true;
				}
				
			}});
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
