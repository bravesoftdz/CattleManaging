package damdariar.gui.swing.forms.listeners;

import java.util.List;

public interface DataModelStatusListener {

	public static final  int MODEL_LOADING_STARTED = 1;
	public static final  int MODEL_LOADING_FINISHED = 2;
	public void  statusChanged(int statusType, List<Object> listOfObjs); 
}
