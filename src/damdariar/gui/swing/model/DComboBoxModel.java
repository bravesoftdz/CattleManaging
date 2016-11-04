package damdariar.gui.swing.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;



/*	 * ComboBoxModel.java	 */
public class DComboBoxModel extends DefaultComboBoxModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**		 * The TreeSet which holds the combobox's data (ordered no duplicates)
	*/

	private TreeSet<Object> values = null;
	
	public DComboBoxModel(List<Object> items){
		
		this(items,false);
	}
	
	public DComboBoxModel(List<Object> items,boolean isMandatory)
	{
		
		super();
		if(!isMandatory)
			super.addElement(null);
		this.values = new TreeSet<Object>();
		int i, c;
	/*	for (i = 0, c = items.size(); i < c; i++)
			values.add(items.get(i).toString());*/
		Iterator<Object> it = items/*values*/.iterator();
		while (it.hasNext())
			super.addElement(it.next()/*.toString()*/);
	}
	public DComboBoxModel(final Object items[])
	{
		this(Arrays.asList(items),false);
	}
	
	public DComboBoxModel(final Object items[],boolean isMandatory)
	{
		this(Arrays.asList(items),isMandatory);
	}
}