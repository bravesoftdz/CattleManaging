package ReportTotal;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.hibernate.Hibernate;

import damdariar.gui.editor.NumberEditor;
import damdariar.gui.property.GUIUtil;
import damdariar.gui.swing.DComboBox;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.hibernate.HibernateUtil;

public class ReportParameter {
	
	
	public static Integer ParameterJaygah(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select location_number from c_location_base").addScalar("Location_Number", Hibernate.STRING).list().toArray();
		DComboBox numberJaygah = new DComboBox(objs);
		numberJaygah.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ParametereDialog myDialo = new ParametereDialog();
		DLabel label = new DLabel(" \u0634\u0645\u0627\u0631\u0647 \u062c\u0627\u06cc\u06af\u0627\u0647");
		myDialo.addEditors(new JComponent[]{numberJaygah},new DLabel[]{label});
		myDialo.setModal(true);
		GUIUtil.showCenterScreen(myDialo);
//		myDialo.setVisible(true);
		
		Integer parameter = null;
		if(ParametereDialog.flag == true){
		    parameter = Integer.parseInt((String) numberJaygah.getSelectedItem());
		}else{
			parameter = null ;
		}
		
		return parameter;
		
	}
	public static String ParameterCowNumer(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select number_body from c_property").addScalar("Number_Body", Hibernate.STRING).list().toArray();
		DComboBox numberJaygah = new DComboBox(objs);
		numberJaygah.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ParametereDialog myDialo = new ParametereDialog();
		DLabel label = new DLabel(" \u0634\u0645\u0627\u0631\u0647 \u0628\u062f\u0646 \u062f\u0627\u0645");
		myDialo.addEditors(new JComponent[]{numberJaygah},new DLabel[]{label});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		String parameter = null;
		if(ParametereDialog.flag == true){
		    parameter = (String)numberJaygah.getSelectedItem();
		}else{
			parameter = null ;
		}
		return parameter;
		
	}
	
	public static Integer ParameterSperm(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select number_sperm from c_base_sperm").addScalar("Number_Sperm", Hibernate.INTEGER).list().toArray();
		DComboBox numberSperm = new DComboBox(objs);
		numberSperm.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ParametereDialog myDialo = new ParametereDialog();
		DLabel label = new DLabel(" \u0634\u0645\u0627\u0631\u0647 \u0627\u0633\u067e\u0631\u0645 \u0631\u0627 \u0648\u0627\u0631\u062f \u06a9\u0646\u06cc\u062f");
		myDialo.addEditors(new JComponent[]{numberSperm},new DLabel[]{label});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		Integer parameter = null;
		if(ParametereDialog.flag == true){
		    parameter = (Integer)numberSperm.getSelectedItem();
		}else{
			parameter = null ;
		}
		return parameter;
		
	}
	public static String ParameterJireName(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select cj.jire_name from c_jire cj").addScalar("jire_name", Hibernate.STRING).list().toArray();
		DComboBox jireName = new DComboBox(objs);
		jireName.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ParametereDialog myDialo = new ParametereDialog();
		DLabel label = new DLabel(" \u0646\u0627\u0645 \u062c\u06cc\u0631\u0647 \u0631\u0627 \u0627\u0646\u062a\u062e\u0627\u0628 \u06a9\u0646\u06cc\u062f");
		myDialo.addEditors(new JComponent[]{jireName},new DLabel[]{label});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		String parameter = null;
		if(ParametereDialog.flag == true){
		    parameter = (String)jireName.getSelectedItem();
		}else{
			parameter = null ;
		}
		return parameter;
		
	}

	public static Date parameterDate(){
		
		
		ParametereDialog myDialo = new ParametereDialog();
		DateEditor editor = new DateEditor(myDialo);
		DLabel lable = new DLabel(" \u062a\u0627\u0631\u06cc\u062e");
		myDialo.addEditors(new JComponent[]{editor},new DLabel[]{lable});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		if(ParametereDialog.flag == true ){
		    return (Date) editor.getValue();
		}else{
			return null;
		}
	}
	public static Map<String, Object> paraleterPersent(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select cp.last_name from c_personal cp where cp.end_date is null").addScalar("Last_Name", Hibernate.STRING).list().toArray();
		ParametereDialog myDialo = new ParametereDialog();
		DateEditor editorS = new DateEditor(myDialo);
		DateEditor editorE = new DateEditor(myDialo);
		DComboBox editorT = new DComboBox(objs);
		DLabel labelS = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u0634\u0631\u0648\u0639");
		DLabel labelE = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u067e\u0627\u06cc\u0627\u0646");
		DLabel labelT = new DLabel(" \u0646\u0627\u0645 \u0634\u062e\u0635");
		myDialo.addEditors(new JComponent[]{editorT,editorS,editorE}, new DLabel[]{labelT,labelS,labelE});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		 Map<String, Object> parameterper = new HashMap<String, Object>();
		if(ParametereDialog.flag == true){
			parameterper.put("FirstName", editorT.getSelectedItem());
			parameterper.put("startdate", editorS.getValue());
			parameterper.put("enddate", editorE.getValue());
			return parameterper;
		}else{
			return null;
		}
	}
	public static Map<String, Object> parameterDateSE(){
		ParametereDialog myDialo = new ParametereDialog();
		DateEditor editorS = new DateEditor(myDialo);
		DateEditor editorE = new DateEditor(myDialo);
		DLabel labelS = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u0634\u0631\u0648\u0639");
		DLabel labelE = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u067e\u0627\u06cc\u0627\u0646");
		myDialo.addEditors(new JComponent[]{editorS,editorE}, new DLabel[]{labelS,labelE});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		 Map<String, Object> parameterper = new HashMap<String, Object>();
		if(ParametereDialog.flag == true){
			parameterper.put("startDate", editorS.getValue());
			parameterper.put("endDate", editorE.getValue());
			return parameterper;
		}else{
			return null;
		}
	}
	public static Map<String, Object> paraleterPersentSperm(){
		Object[] objs = HibernateUtil.getSession().createSQLQuery("select cbs.number_sperm from c_base_sperm cbs ").addScalar("number_sperm",Hibernate.INTEGER).list().toArray();
		ParametereDialog myDialo = new ParametereDialog();
		DateEditor editorS = new DateEditor(myDialo);
		DateEditor editorE = new DateEditor(myDialo);
		DComboBox editorT = new DComboBox(objs);
		DLabel labelS = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u0634\u0631\u0648\u0639");
		DLabel labelE = new DLabel(" \u062a\u0627\u0631\u06cc\u062e \u067e\u0627\u06cc\u0627\u0646");
		DLabel labelT = new DLabel("  \u0634\u0645\u0627\u0631\u0647 \u0627\u0633\u067e\u0631\u0645");
		myDialo.addEditors(new JComponent[]{editorT,editorS,editorE}, new DLabel[]{labelT,labelS,labelE});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		 Map<String, Object> parameterper = new HashMap<String, Object>();
		if(ParametereDialog.flag == true){
			parameterper.put("Sperm_id", editorT.getSelectedItem());
			parameterper.put("startdate", editorS.getValue());
			parameterper.put("enddate", editorE.getValue());
			return parameterper;
		}else{
			return null;
		}
	}
	public static Integer parameterNumberDate(){
		ParametereDialog myDialo = new ParametereDialog();
		DLabel lable = new DLabel(" \u062a\u0639\u062f\u0627\u062f \u0631\u0648\u0632");
		NumberEditor editor = new NumberEditor();
		myDialo.addEditors(new JComponent[]{editor},new DLabel[]{lable});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		if(ParametereDialog.flag == true ){
		    return (Integer) editor.getValue();
		}else{
			return 0;
		}
	}
	public static Integer parameterNumberDateG(){
		ParametereDialog myDialo = new ParametereDialog();
		DLabel lable = new DLabel(" \u062d\u062f\u0627\u0642\u0644 \u062a\u0639\u062f\u0627\u062f \u0631\u0648\u0632");
		NumberEditor editor = new NumberEditor();
		myDialo.addEditors(new JComponent[]{editor},new DLabel[]{lable});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		if(ParametereDialog.flag == true ){
		    return (Integer) editor.getValue();
		}else{
			return 0;
		}
	}
	public static Map<String, Object> parameterNumberDayMaxMin(Integer maxDay,Integer minDay){
		Map<String, Object> parameterNumberDay = new HashMap<String, Object> ();
		ParametereDialog myDialo = new ParametereDialog();
		DLabel lableMin = new DLabel(" \u062d\u062f\u0627\u0642\u0644 \u062a\u0639\u062f\u0627\u062f \u0631\u0648\u0632 \u0647\u0627");
		DLabel labelMax = new DLabel(" \u062d\u062f\u0627\u06a9\u062b\u0631  \u062a\u0639\u062f\u0627\u062f \u0631\u0648\u0632 \u0647\u0627");
		NumberEditor editorMin = new NumberEditor();
		NumberEditor editorMax = new NumberEditor();
		myDialo.addEditors(new JComponent[]{editorMin,editorMax},new DLabel[]{lableMin,labelMax});
		myDialo.setModal(true);
//		myDialo.setVisible(true);
		GUIUtil.showCenterScreen(myDialo);
		if(ParametereDialog.flag == true ){
		    minDay = (Integer) editorMin.getValue();
		    maxDay = (Integer) editorMax.getValue();
		}else{
			minDay = new Integer(1);
			maxDay = new Integer(280);
		}
		parameterNumberDay.put("minDay",minDay);
		parameterNumberDay.put("maxDay",maxDay);
		return parameterNumberDay ;
	}
	
}

class ParametereDialog extends JDialog{
	static boolean flag = false;
	JPanel panel = new DPanel();
	void addEditors(JComponent[] editors,DLabel[] labels){
		GridLayout gl = new GridLayout(0,2);
		gl.setHgap(30);
		gl.setVgap(10);
	
		
		panel.setLayout(gl);
		for(int i = 0 ; i < editors.length ; i++){
			
			panel.add(labels[i]);			
			editors[i].setPreferredSize(new Dimension(120,30));
			panel.add(editors[i]);
			
		}
		
		flag = false ;
		JButton button = GUIUtil.getOKButton();
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				flag = true;
				ParametereDialog.this.setVisible(false);
				
			}		
		});
		JButton buttonc = GUIUtil.getCancelButton();
		buttonc.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = false;
				ParametereDialog.this.setVisible(false);
			}
			
		});
		panel.add(button);
		panel.add(buttonc);
		add(panel);
		setSize(150,editors.length * 30 + 40);
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
	}
	ParametereDialog(){
		FlowLayout lay = new FlowLayout();
		lay.setHgap(20);
		lay.setVgap(30);
		setLayout(lay);
		this.setTitle(" \u062f\u0627\u0645 \u06cc\u0627\u0631");
		setContentPane(new DPanel());
		
	
	}
	
	
}
