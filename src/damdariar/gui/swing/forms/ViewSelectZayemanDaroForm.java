package damdariar.gui.swing.forms;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.JComponent;

import damdariar.beans.ViewSelectZayemanDaro;
import damdariar.gui.swing.DLabel;
import damdariar.hibernate.HibernateUtil;

public class ViewSelectZayemanDaroForm extends AbstractForm{
	static int i = 1;
	public ViewSelectZayemanDaroForm() {
		super(ViewSelectZayemanDaro.class);		
	}
	
	public void  befroeLayoutComponents(){
		editorsMap.remove("CZayemanId");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	
	@Override
	protected void afterSave() {
        HibernateUtil.flushCurrentSession();
//		super.afterSave();
	}

	@Override
	protected boolean beforeSave() {
		if(((ViewSelectZayemanDaro)($Form_Bean)).getCDaroTajvizId() == null){
		String str = "Select max(CDaroTajvizId)From CDaroTajviz ";
		List<Integer> list =HibernateUtil.getSession().createQuery(str).list();
		if(list != null && list.size() > 0){
			Integer max = list.get(0);
			((ViewSelectZayemanDaro)($Form_Bean)).setCDaroTajvizId((max != null ? max : 0) + i);
			i++;
		}
		}
		return true;
	}




	public void layOutComponents(){
		
		layoutManager.getConstriant().gridx = 0;
		layoutManager.getConstriant().gridy = 0;
		layoutManager.add(new DLabel(editorsMap.get("CDaroId").getEditorProperty().getPropertyTranslation())); 
		layoutManager.getConstriant().gridx = 1;
		layoutManager.getConstriant().gridy = 0;
		FormUtility.setPreferredSize(editorsMap.get("CDaroId"), FormUtility.FORM_MIN_FIELD_TYPE);
		layoutManager.add((JComponent) editorsMap.get("CDaroId"));
		
		layoutManager.getConstriant().gridx = 0;
		layoutManager.getConstriant().gridy = 1;
		layoutManager.add(new DLabel(editorsMap.get("dateTajviz").getEditorProperty().getPropertyTranslation()));
		layoutManager.getConstriant().gridx = 1;
		layoutManager.getConstriant().gridy = 1;
		FormUtility.setPreferredSize(editorsMap.get("dateTajviz"), FormUtility.FORM_MIN_FIELD_TYPE);
		layoutManager.add((JComponent) editorsMap.get("dateTajviz"));
		
		
		layoutManager.getConstriant().gridx = 0;
		layoutManager.getConstriant().gridy = 2;
		layoutManager.add(new DLabel(editorsMap.get("cuontDaro").getEditorProperty().getPropertyTranslation()));		
		layoutManager.getConstriant().gridx = 1;
		layoutManager.getConstriant().gridy = 2;
		FormUtility.setPreferredSize(editorsMap.get("cuontDaro"), FormUtility.FORM_MIN_FIELD_TYPE);
		layoutManager.add((JComponent) editorsMap.get("cuontDaro"));
		
		layoutManager.getConstriant().gridx = 2;
		layoutManager.getConstriant().gridy = 0;
		layoutManager.getConstriant().gridheight = 3 ;
		layoutManager.getConstriant().gridwidth = 3 ;
		((JComponent)(editorsMap.get("dateConsume"))).setPreferredSize(new Dimension(160,80));	
		layoutManager.getConstriant().fill = GridBagConstraints.BOTH;
		layoutManager.add((JComponent) editorsMap.get("dateConsume"));
		
		
		
	}
 }
