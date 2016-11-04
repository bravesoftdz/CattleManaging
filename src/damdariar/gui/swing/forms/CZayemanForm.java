package damdariar.gui.swing.forms;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.hibernate.Hibernate;

import damdariar.beans.CProperty;
import damdariar.beans.CZayeman;
import damdariar.gui.property.GUIUtil;
import damdariar.gui.swing.DButton;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.DTextField;
import damdariar.hibernate.HibernateUtil;

public class CZayemanForm extends AbstractForm implements ActionListener{

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		super.vetoableChange(evt);
		if(evt.getPropertyName().equalsIgnoreCase("CPropertyId")){
			Integer id = (Integer) evt.getNewValue();
			if(id != null){
				List<Integer> list = HibernateUtil.getSession().createSQLQuery("select CP.dore FROM C_Property CP where CP.C_Property_Id = " + id).addScalar("dore",Hibernate.INTEGER).list();
				if(list != null && list.size() != 0){
					Integer dore = list.get(0);
					if(dore != null)
						editorsMap.get("dore").setValue(dore);
					else
						editorsMap.get("dore").setValue(null);
				}
			}
			else {
				editorsMap.get("dore").setValue(null);
			}
		}
	}
	CZayemanForm() {
		super(CZayeman.class);
	}
	
	public CZayemanForm(String[] tobeRemovedEditors) {
		super(CZayeman.class,tobeRemovedEditors);
	}


	/**
	 * 
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	
	public void  afterLayoutComponents(){
		DButton button = new DButton(" \u062b\u0628\u062a \u062f\u0627\u0645");
		layoutManager.getConstriant().gridx = layoutManager.getConstriant().gridx+1;
		layoutManager.getConstriant().gridwidth = 1;
		layoutManager.getConstriant().gridheight = 1;
		layoutManager.getConstriant().fill = GridBagConstraints.BOTH;
//		button.setBackground(Color.blue);
		button.addActionListener(this);
		layoutManager.add(button);
	}
	JDialog numberPlastic = new JDialog();
	DTextField strCow = new DTextField(10);
	@Override
	public void actionPerformed(ActionEvent e)  {
		
		numberPlastic.setResizable(false);
		numberPlastic.setContentPane(new DPanel());
		numberPlastic.setTitle("\u062f\u0627\u0645\u06cc\u0627\u0631");
		numberPlastic.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		numberPlastic.setSize(250,150);		
		DLabel label = new DLabel(" \u0634\u0645\u0627\u0631\u0647 \u067e\u0644\u0627\u0633\u062a\u06cc\u06a9\u06cc \u0631\u0627 \u0648\u0627\u0631\u062f \u06a9\u0646\u06cc\u062f");
		FlowLayout layout = new FlowLayout();
		numberPlastic.setLayout(layout);
		numberPlastic.add(label);
		
		numberPlastic.add(strCow);
		JButton ok = GUIUtil.getOKButton();
		JButton cancel = GUIUtil.getCancelButton();
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CProperty Cow = new CProperty();
				Integer CowId = (Integer)(editorsMap.get("CPropertyId").getValue());
				Timestamp dateZayeman = (Timestamp)(editorsMap.get("zayamanDate").getValue());
				List<Integer> Father = HibernateUtil.getSession().createSQLQuery("select ct1.c_base_sperm_id from c_talghih ct1 where ct1.c_property_id = "+ CowId +"  and ct1.date_talghih = (select max(ct.date_talghih) from c_talghih ct  where ct.date_talghih < TIMESTAMP'"+ dateZayeman + "' and ct.c_base_sperm_id is not null and ct.c_property_id = "+ CowId +")").addScalar("c_base_sperm_id", Hibernate.INTEGER).list();
				if(Father != null && Father.size() != 0){
				Integer SpermId = Father.get(0);
				Cow.setSpermId(SpermId);
				}
				CZayemanForm.this.numberPlastic.setVisible(false);
				String numberPlastic = strCow.getText();
				Cow.setActive(true);
				Cow.setFatherId((Integer)editorsMap.get("CPropertyId").getValue());
				Cow.setNumberPelastic(numberPlastic);
				Cow.setDateBirthday((Date)editorsMap.get("zayamanDate").getValue());
				Cow.setDateInput((Date)editorsMap.get("zayamanDate").getValue());
				Cow.setCowSex((String)editorsMap.get("typeChild").getValue());
				Cow.setDore(1);
				HibernateUtil.saveOrUpdate(Cow);
				//HibernateUtil.flushCurrentSession();
			}
			
		});
		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				numberPlastic.setVisible(false);
			}
			
		});
		layout.setVgap(25);
		layout.setHgap(10);
		numberPlastic.add(ok);
		numberPlastic.add(cancel);
		GUIUtil.showCenterScreenUnpacked(numberPlastic);
		
	}
	@Override
	protected void afterSave() {
		
		super.afterSave();
	}

	
	
}
