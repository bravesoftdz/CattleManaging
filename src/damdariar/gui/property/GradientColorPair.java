package damdariar.gui.property;

import java.awt.Color;

public class GradientColorPair {
	
	public  Color backGroundColorStart;
	public GradientColorPair(Color backGroundColorStart,
			Color backGroundColorEnd) {
		super();
		this.backGroundColorStart = backGroundColorStart;
		this.backGroundColorEnd = backGroundColorEnd;
	}
	public Color getBackGroundColorStart() {
		return backGroundColorStart;
	}
	public void setBackGroundColorStart(Color backGroundColorStart) {
		this.backGroundColorStart = backGroundColorStart;
	}
	public Color getBackGroundColorEnd() {
		return backGroundColorEnd;
	}
	public void setBackGroundColorEnd(Color backGroundColorEnd) {
		this.backGroundColorEnd = backGroundColorEnd;
	}
	public  Color backGroundColorEnd ;

}
