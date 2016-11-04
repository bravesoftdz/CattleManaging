package damdariar.gui.swing.forms;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.flexdock.dockbar.DockLabelListener;
import org.flexdock.dockbar.Dockbar;
import org.flexdock.dockbar.DockbarManager;
import org.flexdock.docking.DockingManager;
import org.flexdock.view.View;
import org.hibernate.Hibernate;

import damdariar.beans.AbstractBean;
import damdariar.beans.CExit;
import damdariar.beans.CIll;
import damdariar.beans.CProperty;
import damdariar.beans.CTalghih;
import damdariar.beans.CTest;
import damdariar.beans.CZayeman;
import damdariar.gui.Main;
import damdariar.gui.editor.DMultiDisplayCombo;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.forms.gridform.EditableGrid;
import damdariar.gui.swing.forms.listeners.EditablGridAcionListener;
import damdariar.hibernate.HibernateUtil;

public class CPropertyForm extends AbstractForm{
	
	
	public static View exitView = Main.exitView; 
	public static View zayemanView = Main.zayemanView; 
	public static View talghihView = Main.talghihView; 
	public static View bimariView = Main.bimariView;
	
	@Override
	protected boolean beforeDelete() {
		JOptionPane.showMessageDialog(null," \u0645\u062c\u0627\u0632 \u0628\u0647  \u062d\u0630\u0641 \u062f\u0627\u0645 \u0646\u0645\u06cc \u0628\u0627\u0634\u06cc\u062f "," \u062f\u0627\u0645\u06cc\u0627\u0631",JOptionPane.CANCEL_OPTION);
		return false;

	}
	
	@Override
	protected boolean beforeSave() {
		if($Form_Bean != null && $Form_Bean.getID() != null){
			if($Form_Bean.getID() == 0)
				return false;
			DMultiDisplayCombo combo = ((DMultiDisplayCombo)editorsMap.get("fatherId"));
			Integer fatherId = (Integer) combo.getValue();
			if(fatherId != null && fatherId.equals($Form_Bean.getID()))
				return false;
			
		}
		if($Form_Bean.getID() == null){
		String  obj =(String) editorsMap.get("numberBody").getValue();	
		java.util.List objs = HibernateUtil.getSession().createSQLQuery("select number_body from c_property where c_property.active = 1").list();
		
		if(objs.contains(obj)){
			JOptionPane.showMessageDialog(null," \u0634\u0645\u0627\u0631\u0647 \u0628\u062f\u0646 \u062a\u06a9\u0631\u0627\u0631\u06cc \u0645\u06cc \u0628\u0627\u0634\u062f"," \u062f\u0627\u0645\u06cc\u0627\u0631",JOptionPane.CANCEL_OPTION);
			return false; 
		}else{
		return true;
		}
		}
		return true;
	}
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CPropertyForm(){
		
		super(CProperty.class);
	}

	@Override
	protected void afterSave() {
		
		String cowSex = ((CProperty)$Form_Bean).getCowSex();
		boolean isActive = ((CProperty)$Form_Bean).getActive();
		if(cowSex != null && cowSex.trim().equalsIgnoreCase("\u0645\u0627\u062f\u0647") && isActive){
			DMultiDisplayCombo combo = ((DMultiDisplayCombo)editorsMap.get("fatherId"));
			combo.addNewElement($Form_Bean);
		}
		HibernateUtil.flushCurrentSession();
		
	}

	@Override
	public void befroeLayoutComponents() {
		super.befroeLayoutComponents();
		editorsMap.remove("dateShakhsozi");
		editorsMap.remove("dateMagnet");
	}
	

	
	public void setView(View view){
		 super.setView(view);
		view.dock(exitView, EAST_REGION, .1f);
		view.dock(zayemanView, EAST_REGION, .1f);
		view.dock(bimariView, EAST_REGION, .1f);
		view.dock(talghihView, EAST_REGION, .1f);
		
		
		DockingManager.setMinimized(exitView, true,Main.instance,LEFT);
		DockingManager.setMinimized(zayemanView, true,Main.instance,LEFT);
		DockingManager.setMinimized(bimariView, true,Main.instance,LEFT);
		DockingManager.setMinimized(talghihView, true,Main.instance,LEFT);

	
		(new ExitWorker(this,Main.instance.leftBar)).start();
		(new ZayemanWorker(this,Main.instance.leftBar)).start();
		(new BimariWorker(this,Main.instance.leftBar)).start();
		(new TalghihTestWorker(this,Main.instance.leftBar)).start();

	}
	

	public void dispose() {
		DockingManager.close(DockingManager.getDockable("cow.hierarchy"));
		DockingManager.close(DockingManager.getDockable("ExitForm"));
		DockingManager.close(DockingManager.getDockable("ZayemanForm"));
		DockingManager.close(DockingManager.getDockable("BimariForm"));
		DockingManager.close(DockingManager.getDockable("TalghihForm"));
		(new disposeActionMap(this)).start();
		
	}
	
	
	

}

class ExitWorker extends Thread implements DockLabelListener{
	
	CPropertyForm cpropertyForm;
	simpleFormView simepleView;
	private Dockbar leftBar;
	
	ExitWorker(CPropertyForm cpropertyForm,Dockbar leftBar){
		this.cpropertyForm = cpropertyForm;
		this.leftBar = leftBar;
	}
	
	public void run(){
		
		CPropertyForm.exitView.setContentPane(new DPanel());
		CPropertyAction.registerAction("Exit", cpropertyForm,this);
	    leftBar.getLabel(CPropertyForm.exitView).addDockLabelListener(this);
	}
	
	
	public ActionListener getSaveAction(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CProperty cpropp = (CProperty) cpropertyForm.$Form_Bean;
				if(cpropp.getID() == null || cpropp.getID() == 0){
					cpropertyForm.saveAction();
				}
				CExitForm cexit = (CExitForm)simepleView.topComponent;
			   ((CExit)(cexit.$Form_Bean)).setCPropertyId(cpropp.getCPropertyId());
				cexit.saveAction();	
			}
		};
	}

	@Override
	public void mouseEntered() {
		if(CPropertyForm.exitView.getContentPane() instanceof simpleFormView)
			return;
		simepleView = new simpleFormView(CExit.class,new String[]{"CPropertyId"},
				new String[]{" \u062e\u0631\u0648\u062c \u062c\u062f\u06cc\u062f",
							 " \u062b\u0628\u062a \u062e\u0631\u0648\u062c",
							 " \u062d\u0630\u0641 \u062e\u0631\u0648\u062c"},
							 "CPropertyId");
		
		simepleView.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				Integer cpropId = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				if (e.getComponent().getSize().width < 0){
					if(cpropId != simepleView.grid.getLinkValue()){
						simepleView.topComponent.newAction();
						simepleView.grid.changeTable(cpropId);
					}
				}
			}
			
		});
		
		simepleView.save.addActionListener(getSaveAction());
		CPropertyForm.exitView.addAction(AbstractForm.CLOSE_ACTION);
		CPropertyForm.exitView.setContentPane(simepleView);
		
	}
		
	}



class ZayemanWorker extends Thread  implements DockLabelListener{
	
	CPropertyForm cpropertyForm;
	simpleFormView simepleView;
	private Dockbar leftBar;
	
	ZayemanWorker(CPropertyForm cpropertyForm,Dockbar leftBar){
		this.cpropertyForm = cpropertyForm;
		this.leftBar = leftBar;
	}
	
	public void run(){
		
		
		CPropertyForm.zayemanView.setContentPane(new DPanel());
		CPropertyAction.registerAction("Zayeman", cpropertyForm,this);
		leftBar.getLabel(CPropertyForm.zayemanView).addDockLabelListener(this);
		
	}
	
	public ActionListener getSaveAction(){
		
		return new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CProperty cpropp = (CProperty) cpropertyForm.$Form_Bean;
				if(cpropp.getID() == null || cpropp.getID() == 0){
					cpropertyForm.saveAction();
				}
				CZayemanForm czayeman = (CZayemanForm)simepleView.topComponent;
				((CZayeman)(czayeman.$Form_Bean)).setCPropertyId(cpropp.getCPropertyId());
			 	 czayeman.saveAction();	
				
			}
			
		};
	}

	@Override
	public void mouseEntered() {
		if(CPropertyForm.zayemanView.getContentPane() instanceof simpleFormView)
			return;
		simepleView = new simpleFormView(CZayeman.class,new String[]{"CPropertyId"},
				new String[]{" \u0632\u0627\u06cc\u0645\u0627\u0646 \u062c\u062f\u06cc\u062f",
							 " \u062b\u0628\u062a \u0632\u0627\u06cc\u0645\u0627\u0646 ",
							 " \u062d\u0630\u0641 \u0632\u0627\u06cc\u0645\u0627\u0646 "},
							 "CPropertyId");
		
		simepleView.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				Integer cpropId = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				if (e.getComponent().getSize().width < 0){
					if(cpropId != simepleView.grid.getLinkValue()){
						simepleView.topComponent.newAction();
						simepleView.grid.changeTable(cpropId);
					}
				}
			}
			
		});
		
		
		simepleView.save.addActionListener(getSaveAction());
		CPropertyForm.zayemanView.addAction(AbstractForm.CLOSE_ACTION);
		CPropertyForm.zayemanView.setContentPane(simepleView);
	}
}


class BimariWorker extends Thread  implements DockLabelListener{
	
	CPropertyForm cpropertyForm;
	simpleFormView simepleView;
	private Dockbar leftBar;
	
	BimariWorker(CPropertyForm cpropertyForm,Dockbar leftBar){
		this.cpropertyForm = cpropertyForm;
		this.leftBar = leftBar;
	}
	
	public void run(){
		
		
		CPropertyForm.bimariView.setContentPane(new DPanel());
		CPropertyAction.registerAction("Bimari", cpropertyForm,this);
		leftBar.getLabel(CPropertyForm.bimariView).addDockLabelListener(this);
		
	}
	
	public ActionListener getSaveAction(){
		return new ActionListener(){
			public void actionPerformed(ActionEvent e){
				CProperty cpropp = (CProperty) cpropertyForm.$Form_Bean;
				if(cpropp.getID() == null || cpropp.getID() == 0){
					cpropertyForm.saveAction();
				}
				AbstractForm cbimari = (AbstractForm)simepleView.topComponent;
				((CIll)(cbimari.$Form_Bean)).setCPropertyId(cpropp.getCPropertyId());
			 	cbimari.saveAction();	
			}
		};
	}

	@Override
	public void mouseEntered() {
		if(CPropertyForm.bimariView.getContentPane() instanceof simpleFormView)
			return;
		simepleView = new simpleFormView(CIll.class,new String[]{"CPropertyId"},
				new String[]{" \u0628\u06cc\u0645\u0627\u0631\u06cc \u062c\u062f\u06cc\u062f",
							 " \u062b\u0628\u062a \u0628\u06cc\u0645\u0627\u0631\u06cc",
							 " \u062d\u0630\u0641 \u0628\u06cc\u0645\u0627\u0631\u06cc"},
							 "CPropertyId");
		
		simepleView.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				Integer cpropId = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				if (e.getComponent().getSize().width < 0){
					if(cpropId != simepleView.grid.getLinkValue()){
						simepleView.topComponent.newAction();
						simepleView.grid.changeTable(cpropId);
					}
				}
			}
			
		});

		simepleView.save.addActionListener(getSaveAction());
		CPropertyForm.bimariView.addAction(AbstractForm.CLOSE_ACTION);
		CPropertyForm.bimariView.setContentPane(simepleView);
		
	}
}



class TalghihTestWorker extends Thread  implements DockLabelListener{
	
	CPropertyForm cpropertyForm;
	EditableGrid ctalghih;
	EditableGrid ctest;
	private Dockbar leftBar;
	
	TalghihTestWorker(CPropertyForm cpropertyForm,Dockbar leftBar){
		this.cpropertyForm = cpropertyForm;
		this.leftBar = leftBar;
	}
	
	public void run(){
		
		CPropertyForm.talghihView.setContentPane(new JPanel());
		CPropertyAction.registerAction("Talghih", cpropertyForm,this);
		leftBar.getLabel(CPropertyForm.talghihView).addDockLabelListener(this);
	}

	@Override
	public void mouseEntered() {
		if(CPropertyForm.talghihView.getContentPane() instanceof DPanel)
			return;
		DPanel   contentPane = new DPanel(new GridLayout(2,1));
		contentPane.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ctalghih = new EditableGrid(CTalghih.class,"CPropertyId",new String[]{"CpropertyId"});// new CTalghihForm(new String[]{"CPropertyId"});//new AbstractForm(CTalghih.class,new String[]{"CPropertyId"});

		ctest    =  new EditableGrid(CTest.class,"CTalghihId",new String[]{"CPropertyId","CTalghihId"});
		ctalghih.setBorder(BorderFactory.createTitledBorder(" \u062b\u0628\u062a \u062a\u0644\u0642\u06cc\u062d "));
		ctest.setBorder(BorderFactory.createTitledBorder(" \u062b\u0628\u062a \u062a\u0633\u062a \u0647\u0627\u06cc \u0627\u0646\u062c\u0627\u0645 \u0634\u062f\u0647 \u0628\u0631\u0627\u06cc \u062a\u0644\u0642\u06cc\u062d \u0648\u0627\u0631\u062f \u0634\u062f\u0647"));
		contentPane.add(ctalghih);
		contentPane.add(ctest);
		contentPane.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e) {
				Integer cpropId = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				if(cpropId == null) 
					cpropId = new Integer(-1);
				if (e.getComponent().getSize().width < 0){
					if(cpropId != ctalghih.getLinkValue()){
						ctalghih.changeTable(cpropId);
						ctest.changeTable(-1);
					}
					else
						ctalghih.save();
				}
			}
			
		});
		
		ctalghih.addGridRowSelectionListener(ctest);
		ctalghih.addEditablGridAcionListener(new EditablGridAcionListener(){

			@Override
			public void addAction(AbstractBean bean) {
				Integer id = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				if(id == null || id == 0)
				{	cpropertyForm.saveAction();
					id = ((CProperty) cpropertyForm.$Form_Bean).getCPropertyId();
				}
				List<Integer> list = HibernateUtil.getSession().createSQLQuery("select CP.dore FROM C_Property CP where CP.C_Property_Id = " + id).addScalar("dore",Hibernate.INTEGER).list();
					if(list != null && list.size() != 0){
						Integer dore = list.get(0);
						if(dore != null)
							AbstractBean.setPropertyValue(bean, "dore", dore);
						else
							AbstractBean.setPropertyValue(bean, "dore", null);
					}
			}

			@Override
			public void deleteAction(AbstractBean bean) {
				
			}});
		
		CPropertyForm.talghihView.addAction(AbstractForm.CLOSE_ACTION);
		CPropertyForm.talghihView.setContentPane(contentPane);
		
	}
	


}


