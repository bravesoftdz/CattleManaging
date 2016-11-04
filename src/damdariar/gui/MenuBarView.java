
package damdariar.gui;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ReportTotal.ReportDriver;
import ReportTotal.ReportParameter;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.demo.Settings;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import damdariar.beans.CBaseSperm;
import damdariar.beans.CCowLegStatus;
import damdariar.beans.CDaro;
import damdariar.beans.CDaroConsume;
import damdariar.beans.CDaroStor;
import damdariar.beans.CExit;
import damdariar.beans.CExitReason;
import damdariar.beans.CFalh;
import damdariar.beans.CFoodBase;
import damdariar.beans.CFoodExit;
import damdariar.beans.CIll;
import damdariar.beans.CJire;
import damdariar.beans.CJireDaescription;
import damdariar.beans.CLegStatus;
import damdariar.beans.CLocationBase;
import damdariar.beans.CLocationCow;
import damdariar.beans.CLocationJire;
import damdariar.beans.CMofodiStore;
import damdariar.beans.CMoveJireReason;
import damdariar.beans.CMoveLocationReason;
import damdariar.beans.CPersonal;
import damdariar.beans.CProperty;
import damdariar.beans.CPropertySine;
import damdariar.beans.CSonografi;
import damdariar.beans.CSonografiReport;
import damdariar.beans.CSperm;
import damdariar.beans.CStatusBase;
import damdariar.beans.CStatusCow;
import damdariar.beans.CStatusSine;
import damdariar.beans.CStore;
import damdariar.beans.CTalghih;
import damdariar.beans.CTest;
import damdariar.beans.CZayeman;
import damdariar.beans.IllName;
import damdariar.beans.RaftarFahli;
import damdariar.beans.ReasonZayemanStatus;
import damdariar.beans.StatusChild;
import damdariar.beans.StatusJoft;
import damdariar.beans.TestResult;
import damdariar.beans.ViewSelectIllDaro;
import damdariar.beans.ViewSelectLegDaro;
import damdariar.beans.ViewSelectSineDaro;
import damdariar.beans.ViewSelectSonografiDaro;
import damdariar.beans.ViewSelectTestDaro;
import damdariar.beans.ViewSelectZayemanDaro;
import damdariar.beans.ZayemanStatus;
import damdariar.beans.sematBase;
import damdariar.gui.swing.forms.listeners.MenuItemActionListener;


public class MenuBarView  implements ActionListener{

    private static final String HTML_TEXT =
        "<html><b>Bold</b>, <i>Italics</i>, <tt>Typewriter</tt></html>";
    MenuItemActionListener  menuBarListener;

	
   public  MenuBarView(MenuItemActionListener  menuBarListener){
    	this.menuBarListener =   menuBarListener;
    	
    }
    /**
	 * Builds, configures, and returns the menubar. Requests HeaderStyle,
	 * look-specific BorderStyles, and Plastic 3D hint from Launcher.
	 */
	public JMenuBar buildMenuBar(
        Settings settings,
        ActionListener helpActionListener,
        ActionListener aboutActionListener) {

		DMenuBar bar = new DMenuBar();
		bar.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		bar.putClientProperty(Options.HEADER_STYLE_KEY,
							  settings.getMenuBarHeaderStyle());
		bar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY,
							  settings.getMenuBarPlasticBorderStyle());
		bar.putClientProperty(WindowsLookAndFeel.BORDER_STYLE_KEY,
							  settings.getMenuBarWindowsBorderStyle());
		bar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY,
							  settings.getMenuBar3DHint());

		bar.add(buildDamMenu());
		bar.add(buildTalghiMenu());
		bar.add(buildLocationMenu());
        bar.add(buildDaroMenu());
        bar.add(buildInfoMenu());
        bar.add(buildReportTalghihZayeman());
        bar.add(buildReportJaygah());
        bar.add(buildReportAmari());
        bar.add(buildReportStor());
		bar.add(buildReportMenu());
		return bar;
	}


	/**
	 * Builds and returns the file menu.
	 */
	private JMenu buildDamMenu() {
		DMenuItem item;

		//dam
		JMenu menu = createMenu("\u062f\u0627\u0645", 'D');
    	
		item = createMenuItem(" \u062b\u0628\u062a \u062f\u0627\u0645 \u062e\u0631\u06cc\u062f\u0627\u0631\u06cc \u0634\u062f\u0647 \u06cc\u0627 \u0645\u062a\u0648\u0644\u062f \u0634\u062f\u0647", 'C', KeyStroke.getKeyStroke("alt C"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CProperty");
		item.setClasses(new Class<?>[]{CProperty.class});
		item.addActionListener(this);

		item = createMenuItem(" \u062b\u0628\u062a \u062f\u0644\u06cc\u0644 \u062e\u0631\u0648\u062c \u06cc\u06a9 \u062f\u0627\u0645 \u0627\u0632 \u0633\u06cc\u0633\u062a\u0645", 'S', KeyStroke.getKeyStroke("alt E"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CExit");
		item.setClasses(new Class<?>[]{CExit.class,CExitReason.class});
		item.addActionListener(this);
				
		item = createMenuItem(" \u062b\u0628\u062a \u0648\u0632\u0646 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt W"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CWeight");
		item.addActionListener(this);
		
		item = createMenuItem(" \u062b\u0628\u062a \u0648\u0636\u0639\u06cc\u062a \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CStatusCow");
		item.setClasses(new Class<?>[]{CStatusCow.class,CStatusBase.class});
		item.addActionListener(this);
				
		item = createMenuItem(" \u062a\u063a\u06cc\u06cc\u0631 \u0634\u0645\u0627\u0631\u0647 \u0641\u0644\u0632\u06cc \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt N"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CNumber");
		item.addActionListener(this);
		
		item = createMenuItem(" \u062b\u0628\u062a \u0631\u06a9\u0648\u0631\u062f \u0634\u06cc\u0631 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt M"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CMilk");
		item.addActionListener(this);
			
		item = createMenuItem(" \u0639\u06a9\u0633 \u0647\u0627\u06cc \u062f\u0627\u0645 ", 'S', KeyStroke.getKeyStroke("alt P"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CPic");
		item.addActionListener(this);
		
		item = createMenuItem(" \u062b\u0628\u062a \u067e\u06cc\u0634\u0646\u0647\u0627\u062f \u062d\u0630\u0641 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt D"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CDelete");
		item.addActionListener(this);		
		
		item = createMenuItem(" \u062b\u0628\u062a \u0632\u0627\u06cc\u0645\u0627\u0646", 'S', KeyStroke.getKeyStroke("alt Z"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CZayeman");
		item.setClasses(new Class<?>[]{CZayeman.class , ViewSelectZayemanDaro.class,ZayemanStatus.class,StatusChild.class,ReasonZayemanStatus.class,StatusJoft.class});
		item.addActionListener(this);
		
		
		item = createMenuItem(" \u062c\u0627\u06cc\u06af\u0627\u0647 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CLocationCow");
		item.setClasses(new Class<?>[]{CLocationCow.class,CMoveLocationReason.class});
		item.addActionListener(this);
		
		/*item = createMenuItem(" \u0627\u0646\u0648\u0627\u0639 \u062f\u0644\u0627\u06cc\u0644 \u062a\u063a\u06cc\u06cc\u0631 \u062c\u0627\u06cc\u06af\u0627\u0647 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CMoveLocationReason");
		item.addActionListener(this);*/
		
		item = createMenuItem(" \u062b\u0628\u062a \u0648\u0636\u0639\u06cc\u062a \u0633\u0645 \u0686\u06cc\u0646\u06cc \u067e\u0627", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CCowLeg");
		item.addActionListener(this);
		
		item = createMenuItem(" \u0648\u0636\u0639\u06cc\u062a \u0628\u062f\u0646 \u0648 \u0633\u06cc\u0646\u0647 ", 'S', KeyStroke.getKeyStroke("alt B"));
		menu.add(item);
		item.setActionCommand("damdariar.beans.CPropertySine");
		item.setClasses(new Class<?>[]{CPropertySine.class,ViewSelectSineDaro.class,CStatusSine.class});
		item.addActionListener(this);
		
		return menu;
		
		
	}


	/**
	 * Builds and returns a menu with different JRadioButtonMenuItems.
	 */
	private JMenu buildTalghiMenu() {
	
		JMenu menu = createMenu(" \u0627\u0633\u067e\u0631\u0645 \u0648 \u062a\u0644\u0642\u06cc\u062d", 'R');
		DMenuItem itemTalghi;
		

		itemTalghi = createMenuItem(" \u062a\u0644\u0642\u06cc\u062d \u0648 \u062a\u0633\u062a \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt T"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CTalghih");
		itemTalghi.setClasses(new Class<?>[]{CTalghih.class,CTest.class, ViewSelectTestDaro.class,TestResult.class });
		itemTalghi.addActionListener(this);
		

		
		/*itemTalghi = createMenuItem(" \u0645\u0634\u062e\u0635\u0627\u062a \u0627\u0633\u067e\u0631\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CBaseSperm");
		itemTalghi.addActionListener(this);
		*/
		
		/*itemTalghi = createMenuItem(" \u062d\u0627\u0644\u062a \u0647\u0627\u06cc \u067e\u0627\u06cc\u0647 \u0632\u0627\u06cc\u0645\u0627\u0646 ", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.ZayemanStatus");
		itemTalghi.addActionListener(this);
		*/
		
		
		/*
		 * itemTalghi = createMenuItem(" \u062b\u0628\u062a \u062f\u0644\u0627\u06cc\u0644 \u0627\u06cc\u062c\u0627\u062f \u06cc\u06a9 \u062d\u0627\u0644\u062a \u062f\u0631 \u0632\u0627\u06cc\u0645\u0627\u0646", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.ReasonZayemanStatus");
		itemTalghi.addActionListener(this);
		
		itemTalghi = createMenuItem(" \u0648\u0636\u0639\u06cc\u062a \u0647\u0627\u06cc \u067e\u0627\u06cc\u0647 \u0641\u0631\u0632\u0646\u062f \u062f\u0631 \u0647\u0646\u06af\u0627\u0645 \u0632\u0627\u06cc\u0645\u0627\u0646", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.StatusChild");
		itemTalghi.addActionListener(this);
		*/
		/*itemTalghi = createMenuItem(" \u0648\u0636\u0639\u06cc\u062a \u0647\u0627\u06cc \u067e\u0627\u06cc\u0647 \u062c\u0641\u062a \u062f\u0631 \u0647\u0646\u06af\u0627\u0645 \u0632\u0627\u06cc\u0645\u0627\u0646", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.StatusJoft");
		itemTalghi.addActionListener(this);*/
		
		
		/*itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0632\u0627\u06cc\u0645\u0627\u0646", 'S', KeyStroke.getKeyStroke("alt Z"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CZayeman");
		itemTalghi.setClasses(new Class<?>[]{ZayemanStatus.class,StatusChild.class,ReasonZayemanStatus.class,StatusJoft.class, CZayeman.class});
		itemTalghi.addActionListener(this);*/
			
		itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u0641\u062d\u0644 \u0622\u0645\u062f\u0646 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt F"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CFalh");
		itemTalghi.setClasses(new Class<?>[]{ CFalh.class,RaftarFahli.class});
		itemTalghi.addActionListener(this);
		
		itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0627\u0633\u067e\u0631\u0645 \u0647\u0627\u06cc \u062e\u0631\u06cc\u062f\u0627\u0631\u06cc \u0634\u062f\u0647", 'S', KeyStroke.getKeyStroke("alt R"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CSperm");
		itemTalghi.setClasses(new Class<?>[]{ CSperm.class,CBaseSperm.class});
		itemTalghi.addActionListener(this);
		
		itemTalghi = createMenuItem(" \u067e\u06cc\u0634\u0646\u0647\u0627\u062f \u0627\u0633\u067e\u0631\u0645 \u0628\u0631\u0627\u06cc \u062f\u0627\u0645 ", 'S', KeyStroke.getKeyStroke("alt Q"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CSpermCowS");
		itemTalghi.addActionListener(this);
		
		/*
		 * itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u062a\u0633\u062a ", 'S', KeyStroke.getKeyStroke("alt U"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.CTest");
		itemTalghi.setClasses(new Class<?>[]{CTest.class,TestResult.class });
		itemTalghi.addActionListener(this);
		*/
	/*	itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0627\u0646\u0648\u0627\u0639 \u0631\u0641\u062a\u0627\u0631 \u0641\u062d\u0644\u06cc", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.RaftarFahli");
		itemTalghi.addActionListener(this);
		*/
		/*itemTalghi = createMenuItem(" \u062b\u0628\u062a \u0627\u0646\u0648\u0627\u0639 \u062d\u0627\u0644\u062a \u0647\u0627\u06cc \u0646\u062a\u06cc\u062c\u0647 \u062a\u0633\u062a ", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemTalghi);
		itemTalghi.setActionCommand("damdariar.beans.TestResult");
		itemTalghi.addActionListener(this);*/
		
		
		// Default icon
		/*ButtonGroup group1 = new ButtonGroup();
		item = createRadioItem(true, false);
		group1.add(item);
		menu.add(item);
		item = createRadioItem(true, true);
		group1.add(item);
		menu.add(item);

		menu.addSeparator();

		item = createRadioItem(false, false);
		menu.add(item);
		item = createRadioItem(false, true);
		menu.add(item);

		menu.addSeparator();

		// Custom icon
		ButtonGroup group2 = new ButtonGroup();
		item = createRadioItem(true, false);
		item.setIcon(readImageIcon("pie_mode.png"));
		group2.add(item);
		menu.add(item);
		item = createRadioItem(true, true);
		item.setIcon(readImageIcon("bar_mode.png"));
		group2.add(item);
		menu.add(item);

		menu.addSeparator();

		item = createRadioItem(false, false);
		item.setIcon(readImageIcon("alphab_sort.png"));
		//item.setDisabledIcon(Utils.getIcon("alphab_sort_gray.png"));
		menu.add(item);
		item = createRadioItem(false, true);
		item.setIcon(readImageIcon("size_sort.png"));
		//item.setDisabledIcon(readImageIcon("size_sort_gray.png"));
		menu.add(item);*/

		return menu;
	}


	/**
	 * Builds and returns a menu with different JCheckBoxMenuItems.
	 */
	private JMenu buildLocationMenu() {
//		JCheckBoxMenuItem item;

		JMenu menu = createMenu(" \u062c\u0627\u06cc\u06af\u0627\u0647 \u0648 \u062c\u06cc\u0631\u0647 \u062f\u0627\u0645", 'C');
		DMenuItem itemLocation;
		/*itemLocation = createMenuItem(" \u062b\u0628\u062a \u0627\u0646\u0648\u0627\u0639 \u063a\u0630\u0627", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CFoodBase");
		itemLocation.addActionListener(this);*/
		
		itemLocation = createMenuItem(" \u062b\u0628\u062a  \u062c\u06cc\u0631\u0647", 'S', KeyStroke.getKeyStroke("alt J"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CJire");
		itemLocation.setClasses(new Class<?>[]{CJire.class, CJireDaescription.class});
		itemLocation.addActionListener(this);
		
		/*itemLocation = createMenuItem(" \u062b\u0628\u062a \u062c\u0632\u0626\u06cc\u0627\u062a \u06cc\u06a9 \u062c\u06cc\u0631\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CJireDaescription");
		itemLocation.addActionListener(this);*/
		
		/*itemLocation = createMenuItem(" \u062b\u0628\u062a \u0639\u0644\u062a \u062e\u0631\u0627\u0628\u06cc \u063a\u0630\u0627", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CFoodExit");
		itemLocation.addActionListener(this);
		
*/
		itemLocation = createMenuItem(" \u062c\u0627\u06cc\u06af\u0627\u0647", 'S', KeyStroke.getKeyStroke("alt L"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CLocationBase");
		itemLocation.setClasses(new Class<?>[]{CLocationJire.class,CLocationBase.class,CMoveJireReason.class});
		itemLocation.addActionListener(this);
		
		/*itemLocation = createMenuItem(" \u062c\u0627\u06cc\u06af\u0627\u0647 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CLocationCow");
		itemLocation.addActionListener(this);
		
		itemLocation = createMenuItem(" \u062c\u06cc\u0631\u0647 \u063a\u0630\u0627\u06cc\u06cc \u062c\u0627\u06cc\u06af\u0627\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CLocationJire");
		itemLocation.addActionListener(this);*/
		
		/*itemLocation = createMenuItem(" \u062b\u0628\u062a \u062e\u0631\u0627\u0628 \u0634\u062f\u0646 \u063a\u0630\u0627", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CMofodiStore");
		itemLocation.addActionListener(this);*/
		
		/*itemLocation = createMenuItem(" \u062b\u0628\u062a \u0627\u0646\u0648\u0627\u0639 \u062f\u0644\u0627\u06cc\u0644 \u062a\u063a\u06cc\u06cc\u0631 \u062c\u06cc\u0631\u0647 \u062c\u0627\u06cc\u06af\u0627\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CMoveJireReason");
		itemLocation.addActionListener(this);
		
		itemLocation = createMenuItem(" \u0627\u0646\u0648\u0627\u0639 \u062f\u0644\u0627\u06cc\u0644 \u062a\u063a\u06cc\u06cc\u0631 \u062c\u0627\u06cc\u06af\u0627\u0647 \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CMoveLocationReason");
		itemLocation.addActionListener(this);*/
		
		itemLocation = createMenuItem(" \u0627\u0646\u0628\u0627\u0631 \u062f\u0627\u0631\u06cc \u063a\u0630\u0627", 'S', KeyStroke.getKeyStroke("alt G"));
		menu.add(itemLocation);
		itemLocation.setActionCommand("damdariar.beans.CStore");
		itemLocation.setClasses(new Class<?>[]{CStore.class,CMofodiStore.class,CFoodBase.class,CFoodExit.class});
		itemLocation.addActionListener(this);
		// Default icon
		/*menu.add(createCheckItem(true, false));
		menu.add(createCheckItem(true, true));
		menu.addSeparator();
		menu.add(createCheckItem(false, false));
		menu.add(createCheckItem(false, true));


		menu.addSeparator();

		// Custom icon
		item = createCheckItem(true, false);
		item.setIcon(readImageIcon("check.gif"));
		item.setSelectedIcon(readImageIcon("check_selected.gif"));
		menu.add(item);
		item = createCheckItem(true, true);
		item.setIcon(readImageIcon("check.gif"));
		item.setSelectedIcon(readImageIcon("check_selected.gif"));
		menu.add(item);

		menu.addSeparator();

		item = createCheckItem(false, false);
		item.setIcon(readImageIcon("check.gif"));
		item.setSelectedIcon(readImageIcon("check_selected.gif"));
		menu.add(item);
		item = createCheckItem(false, true);
		item.setIcon(readImageIcon("check.gif"));
		item.setSelectedIcon(readImageIcon("check_selected.gif"));
		item.setDisabledSelectedIcon(readImageIcon("check_disabled_selected.gif"));
		menu.add(item);
*/
		return menu;
	}


    /**
     * Builds and returns a menu with items that use a HTML text.
     */
    private JMenu buildDaroMenu() {
    	
    	
        JMenu menu = createMenu(" \u0628\u06cc\u0645\u0627\u0631\u06cc  \u0648 \u062f\u0627\u0631\u0648", 'S');
        DMenuItem itemDaro;
        itemDaro = createMenuItem(" \u067e\u0627", 'S', KeyStroke.getKeyStroke("alt L"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CCowLegStatus");
		itemDaro.setClasses(new Class<?>[]{CCowLegStatus.class,ViewSelectLegDaro.class,CLegStatus.class});
		itemDaro.addActionListener(this);
		
			/*itemDaro = createMenuItem(" \u062b\u0628\u062a \u0648\u0636\u0639\u06cc\u062a \u0633\u0645 \u0686\u06cc\u0646\u06cc \u067e\u0627", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CCowLeg");
		itemDaro.addActionListener(this);*/
		
		itemDaro = createMenuItem(" \u062f\u0627\u0631\u0648", 'S', KeyStroke.getKeyStroke("alt A"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CDaro");
		itemDaro.setClasses(new Class<?>[]{CDaroStor.class,CDaroConsume.class,CDaro.class});
		itemDaro.addActionListener(this);
		
		/*itemDaro = createMenuItem(" \u062b\u0628\u062a \u062f\u0627\u0631\u0648 \u0647\u0627\u06cc \u0645\u0635\u0631\u0641 \u0634\u062f\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CDaroConsume");
		itemDaro.addActionListener(this);
		
		itemDaro = createMenuItem(" \u062f\u0627\u0631\u0648 \u0647\u0627\u06cc \u062e\u0631\u06cc\u062f\u0627\u0631\u06cc \u0634\u062f\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CDaroStor");
		itemDaro.addActionListener(this);
		
		itemDaro = createMenuItem(" \u062f\u0627\u0631\u0648 \u0647\u0627\u06cc \u062a\u062c\u0648\u06cc\u0632 \u0634\u062f\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CDaroTajviz");
		itemDaro.addActionListener(this);*/
		
		itemDaro = createMenuItem(" \u0648\u0627\u0631\u062f \u06a9\u0631\u062f\u0646 \u0628\u06cc\u0645\u0627\u0631\u06cc \u0647\u0627", 'S', KeyStroke.getKeyStroke("alt I"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CIll");
		itemDaro.setClasses(new Class<?>[]{CIll.class,ViewSelectIllDaro.class,IllName.class});
		itemDaro.addActionListener(this);
		
		/*itemDaro = createMenuItem(" \u062d\u0627\u0644\u062a \u0647\u0627\u06cc \u067e\u0627\u06cc \u062f\u0627\u0645", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CLegStatus");
		itemDaro.addActionListener(this);*/
		
		/*itemDaro = createMenuItem(" \u0648\u0636\u0639\u06cc\u062a \u0628\u062f\u0646 \u0648 \u0633\u06cc\u0646\u0647 ", 'S', KeyStroke.getKeyStroke("alt B"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CPropertySine");
		itemDaro.setClasses(new Class<?>[]{CStatusSine.class,CPropertySine.class});
		itemDaro.addActionListener(this);*/
		
		
		itemDaro = createMenuItem(" \u0633\u0648\u0646\u0648\u06af\u0631\u0627\u0641\u06cc", 'S', KeyStroke.getKeyStroke("alt Z"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CSonografi");
		itemDaro.setClasses(new Class<?>[]{CSonografi.class,CSonografiReport.class, ViewSelectSonografiDaro.class});
		itemDaro.addActionListener(this);
		
		/*itemDaro = createMenuItem(" \u0633\u0648\u0646\u0648\u06af\u0631\u0627\u0641\u06cc", 'S', KeyStroke.getKeyStroke("alt Z"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CSonografi");
		itemDaro.setClasses(new Class<?>[]{CSonografi.class,CSonografiReport.class,DaroTajvizSonografi.class});
		itemDaro.addActionListener(this);*/
		/*itemDaro = createMenuItem(" \u06af\u0632\u0627\u0631\u0634 \u0633\u0648\u0646\u0648\u06af\u0631\u0627\u0641\u06cc", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CSonografiReport");
		itemDaro.addActionListener(this);*/
		
		/*itemDaro = createMenuItem(" \u0648\u0627\u0631\u062f \u06a9\u0631\u062f\u0646 \u062d\u0627\u0644\u062a \u0647\u0627\u06cc \u0633\u06cc\u0646\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.CStatusSine");
		itemDaro.addActionListener(this);*/
		
		/*itemDaro = createMenuItem(" \u0627\u0646\u0648\u0627\u0639 \u0628\u06cc\u0645\u0627\u0631\u06cc", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemDaro);
		itemDaro.setActionCommand("damdariar.beans.IllName");
		itemDaro.addActionListener(this);*/
		
        return menu;
    }


    /**
     * Builds and returns a menu with items that use a HTML text.
     */
    private JMenu buildInfoMenu() {
        JMenu menu = createMenu(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u0648\u06cc\u0698\u0647 \u062f\u0627\u0645\u062f\u0627\u0631\u06cc", 'A');
        DMenuItem itemInfo;
        
        
        itemInfo = createMenuItem(" \u062a\u0639\u0631\u06cc\u0641 \u0627\u0641\u0631\u0627\u062f \u0645\u0648\u062c\u0648\u062f \u062f\u0631 \u0633\u06cc\u0633\u062a\u0645", 'P', KeyStroke.getKeyStroke("alt P"));
		menu.add(itemInfo);
		itemInfo.setActionCommand("damdariar.beans.CPersonal");
		itemInfo.setClasses(new Class<?>[]{CPersonal.class,sematBase.class,});
		itemInfo.addActionListener(this);
		
		
        itemInfo = createMenuItem(" \u0645\u0634\u062e\u0635\u0627\u062a \u062f\u0627\u0645\u062f\u0627\u0631\u06cc", 'S', KeyStroke.getKeyStroke("alt D"));
		menu.add(itemInfo);
		itemInfo.setActionCommand("damdariar.beans.CDamdary");
		itemInfo.addActionListener(this);
		
		
		
		
		itemInfo = createMenuItem(" \u0634\u06cc\u0631 \u0645\u0635\u0631\u0641\u06cc \u062f\u0627\u0645\u062f\u0627\u0631\u06cc", 'S', KeyStroke.getKeyStroke("alt M"));
		menu.add(itemInfo);
		itemInfo.setActionCommand("damdariar.beans.CMilkCunsumer");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u06a9\u0644 \u0634\u06cc\u0631 \u0631\u0648\u0632\u0627\u0646\u0647 \u062f\u0627\u0645\u062f\u0627\u0631\u06cc", 'S', KeyStroke.getKeyStroke("alt T"));
		menu.add(itemInfo);
		itemInfo.setActionCommand("damdariar.beans.CMilkTotal");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u067e\u06cc\u06a9\u0631 \u0628\u0646\u062f\u06cc \u0628\u0631\u0627\u06cc \u067e\u06cc\u0627\u0645 \u06a9\u0648\u062a\u0627\u0647", 'S', KeyStroke.getKeyStroke("alt S"));
		menu.add(itemInfo);
		itemInfo.setActionCommand("damdariar.beans.CSmsMail");
		itemInfo.addActionListener(this);
        
        return menu;
    }


	/**
	 * Builds and and returns the help menu.
	 */
	private JMenu buildHelpMenu(
        ActionListener helpActionListener,
        ActionListener aboutActionListener) {

		JMenu menu = createMenu("Help", 'H');

		DMenuItem item;
        item = createMenuItem("Help Contents", readImageIcon("help.gif"), 'H');
        if (helpActionListener != null) {
    		item.addActionListener(helpActionListener);
        }
        menu.add(item);
        if (!isAboutInOSMenu()) {
            menu.addSeparator();
            item = createMenuItem("About", 'a');
            item.addActionListener(aboutActionListener);
            menu.add(item);
        }

		return menu;
	}

	private JMenu buildReportTalghihZayeman(){
		JMenu menu = createMenu(" \u06af\u0632\u0627\u0631\u0634\u0627\u062a \u062a\u0644\u0642\u06cc\u062d \u0648 \u0632\u0627\u06cc\u0645\u0627\u0646", 'A');
		 JMenuItem itemInfo;
		 
		    itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc\u06cc \u06a9\u0647 \u0628\u0639\u062f \u0627\u0632 \u062a\u0644\u0642\u06cc\u062d \u062a\u0633\u062a \u0646\u0634\u062f\u0647 \u0627\u0646\u062f", 'S');
			menu.add(itemInfo);
			itemInfo.setActionCommand("ReportCowForTest.jrxml");
			itemInfo.addActionListener(this);
			
			itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc \u06a9\u0647 \u0628\u0627\u06cc\u062f \u062a\u0633\u062a \u062f\u0648\u0645 \u0634\u0648\u0646\u062f", 'S');
			menu.add(itemInfo);
			itemInfo.setActionCommand("ReportCowForTest2.jrxml");
			itemInfo.addActionListener(this);
			
			itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc \u06a9\u0647 \u0628\u0627\u06cc\u062f \u062e\u0634\u06a9 \u0634\u062f\u0647 \u0628\u0627\u0634\u0646\u062f", 'S');
			menu.add(itemInfo);
			itemInfo.setActionCommand("ReportDamkhoshk.jrxml");
			itemInfo.addActionListener(this);
			
			itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc\u06cc \u06a9\u0647 \u0632\u0627\u06cc\u0634 \u0622\u0646\u0647\u0627 \u0646\u0632\u062f\u06cc\u06a9 \u0645\u06cc \u0628\u0627\u0634\u062f", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("ReportCowForZayesh.jrxml");
				itemInfo.addActionListener(this);

				itemInfo = createMenuItem(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u0632\u0627\u06cc\u0645\u0627\u0646 \u062f\u0631 \u06cc\u06a9 \u0628\u0627\u0632\u0647 \u062e\u0627\u0635", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("ReportZayeshForOneRangOfTime.jrxml");
				itemInfo.addActionListener(this);
				
				itemInfo = createMenuItem(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u062a\u0644\u0642\u06cc\u062d \u062f\u0631 \u06cc\u06a9 \u0628\u0627\u0632\u0647 \u062e\u0627\u0635", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("TalgihForRengOfTime.jrxml");
				itemInfo.addActionListener(this);
				
				itemInfo = createMenuItem(" \u062f\u0631\u0635\u062f \u0622\u0628\u0633\u062a\u0646\u06cc \u062f\u0627\u0645 \u0647\u0627 \u062f\u0631 \u062f\u0648\u0631\u0647 \u0647\u0627\u06cc \u0645\u062e\u062a\u0644\u0641", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("ReportPercentAbestani.jrxml");
				itemInfo.addActionListener(this);
				
				itemInfo = createMenuItem(" \u06af\u0627\u0648\u0647\u0627\u06cc\u06cc \u06a9\u0647 \u0628\u0639\u062f \u0627\u0632 \u0632\u0627\u06cc\u0645\u0627\u0646 \u062a\u0644\u0642\u06cc\u062d \u0646\u0634\u062f\u0647 \u0627\u0646\u062f", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("CowAfterZayemanNotTalghih.jrxml");
				itemInfo.addActionListener(this);
				
				itemInfo = createMenuItem(" \u06af\u0627\u0648\u0647\u0627\u06cc \u062a\u0644\u06cc\u0633\u0647 \u0627\u06cc \u06a9\u0647 \u062a\u0644\u0642\u06cc\u062d \u0646\u0634\u062f\u0647 \u0627\u0646\u062f", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("ReportTeliseForTalghih.jrxml");
				itemInfo.addActionListener(this);
				
				itemInfo = createMenuItem(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u0622\u0628\u0633\u062a\u0646\u06cc \u062f\u0631 \u06cc\u06a9 \u0628\u0627\u0632\u0647 \u062e\u0627\u0635", 'S');
				menu.add(itemInfo);
				itemInfo.setActionCommand("AbestanForRengOfTime.jrxml");
				itemInfo.addActionListener(this);
				
				
		 
		 return menu;
	}	

	private JMenu buildReportJaygah(){
		JMenu menu = createMenu( "\u06af\u0632\u0627\u0631\u0634\u0627\u062a \u062c\u0627\u06cc\u06af\u0627\u0647 \u0648 \u062c\u06cc\u0631\u0647", 'A');
		 JMenuItem itemInfo;
		 
		itemInfo = createMenuItem(" \u06af\u0632\u0627\u0631\u0634 \u062c\u0627\u06cc\u06af\u0627\u0647", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportJaygah.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u06af\u0632\u0627\u0631\u0634 \u062c\u06cc\u0631\u0647 \u062c\u0627\u06cc\u06af\u0627\u0647 \u0647\u0627", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportJireForJaygah.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u0647\u0632\u06cc\u0646\u0647 \u06cc\u06a9 \u0648\u0639\u062f\u0647 \u062c\u06cc\u0631\u0647", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportJireCost.jrxml");
		itemInfo.addActionListener(this);
		
		return menu;
	}
	
	private JMenu buildReportAmari(){
		JMenu menu = createMenu(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u0622\u0645\u0627\u0631\u06cc \u062f\u0627\u0645\u062f\u0627\u0631\u06cc", 'A');
		JMenuItem itemInfo;
		
		itemInfo = createMenuItem(" \u062f\u0631\u0635\u062f \u0622\u0628\u0633\u062a\u0646\u06cc \u062a\u0644\u0642\u06cc\u062d\u0627\u062a \u06cc\u06a9 \u0634\u062e\u0635", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("PercntPersonTalghihgarin6mount.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u062f\u0631\u0635\u062f \u0622\u0628\u0633\u062a\u0646\u06cc \u06af\u0644\u0647", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("PercentGale6mount.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u062f\u0631\u0635\u062f \u0628\u0647\u0631\u0647 \u0648\u0631\u06cc \u0627\u0633\u067e\u0631\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("PercnSperm6mount.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem("  \u06af\u0632\u0627\u0631\u0634 \u0627\u0633\u067e\u0631\u0645 \u0627\u0633\u062a\u0641\u0627\u062f\u0647 \u0634\u062f\u0647 \u062f\u0631 \u06cc\u06a9 \u0628\u0627\u0632\u0647", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportSpermUsesforoneRangOFTime.jrxml");
		itemInfo.addActionListener(this);
		
		return menu;
	}
	
	private JMenu buildReportMenu() {
    	
        JMenu menu = createMenu(" \u06af\u0632\u0627\u0631\u0634\u0627\u062a \u0645\u062e\u062a\u0644\u0641", 'A');
        JMenuItem itemInfo;
        
		itemInfo = createMenuItem(" \u06af\u0632\u0627\u0631\u0634 \u0631\u06a9\u0648\u0631\u062f \u06af\u06cc\u0631\u06cc \u0634\u06cc\u0631", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportMilkRcord.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u06af\u0632\u0627\u0631\u0634 \u0631\u0648\u0632 \u0647\u0627\u06cc \u0622\u0632\u0627\u062f \u062f\u0627\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportOPDam.jrxml");
		itemInfo.addActionListener(this);									
		
		itemInfo = createMenuItem(" \u0634\u0646\u0627\u0633\u0646\u0627\u0645\u0647 \u062f\u0627\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("total_information.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc\u06cc \u06a9\u0647 \u0645\u06af\u0646\u062a \u06af\u0630\u0627\u0631\u06cc \u0646\u0634\u062f\u0647 \u0627\u0646\u062f", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportCowForMagnet.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u062f\u0627\u0645 \u0647\u0627\u06cc\u06cc \u0634\u0627\u062e \u0633\u0648\u0632\u06cc \u0646\u0634\u062f\u0647 \u0627\u0646\u062f", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportCowForShakhSozi.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u0646\u0645\u0627\u06cc\u0634 \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u067e\u0627\u06cc\u0647 \u062f\u0627\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ViewInformationCawReport.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u0627\u0637\u0644\u0627\u0639\u0627\u062a \u06a9\u0644 \u062f\u0648\u0631\u0647 \u0647\u0627\u06cc \u062f\u0627\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("totalInformationCow.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u06af\u0648\u0633\u0627\u0644\u0647 \u0647\u0627\u06cc\u06cc \u06a9\u0647 \u0628\u0627\u06cc\u062f \u0627\u0632\u0634\u06cc\u0631 \u06af\u0631\u0641\u062a\u0647 \u0634\u0648\u0646\u062f", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportGosaleForMilk.jrxml");
		itemInfo.addActionListener(this);
		
		itemInfo = createMenuItem(" \u0645\u0648\u062c\u0648\u062f\u06cc \u062f\u0627\u0645", 'S');
		menu.add(itemInfo);
		itemInfo.setActionCommand("ReportMojodiCow.jrxml");
		itemInfo.addActionListener(this);
		
		return menu ;
    }

   private JMenu buildReportStor(){
	   JMenu menu = createMenu(" \u06af\u0632\u0627\u0631\u0634\u0627\u062a \u0627\u0646\u0628\u0627\u0631 \u062f\u0627\u0631\u06cc", 'A');
       JMenuItem itemInfo;
       return menu;
   }
    // Factory Methods ********************************************************

    protected JMenu createMenu(String text, char mnemonic) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(mnemonic);
        return menu;
    }


    protected DMenuItem createMenuItem(String text) {
        return new DMenuItem(text);
    }


    protected DMenuItem createMenuItem(String text, char mnemonic) {
        return new DMenuItem(text, mnemonic);
    }


    protected DMenuItem createMenuItem(String text, char mnemonic, KeyStroke key) {
        DMenuItem menuItem = new DMenuItem(text, mnemonic);
        menuItem.setAccelerator(key);
        return menuItem;
    }


    protected DMenuItem createMenuItem(String text, Icon icon) {
        return new DMenuItem(text, icon);
    }


    protected DMenuItem createMenuItem(String text, Icon icon, char mnemonic) {
        DMenuItem menuItem = new DMenuItem(text, icon);
        menuItem.setMnemonic(mnemonic);
        return menuItem;
    }


    protected DMenuItem createMenuItem(String text, Icon icon, char mnemonic, KeyStroke key) {
        DMenuItem menuItem = createMenuItem(text, icon, mnemonic);
        menuItem.setAccelerator(key);
        return menuItem;
    }


    protected JRadioButtonMenuItem createRadioButtonMenuItem(String text, boolean selected) {
        return new JRadioButtonMenuItem(text, selected);
    }


    protected JCheckBoxMenuItem createCheckBoxMenuItem(String text, boolean selected) {
        return new JCheckBoxMenuItem(text, selected);
    }


    // Subclass will override the following methods ***************************

    /**
     * Checks and answers whether the quit action has been moved to an
     * operating system specific menu, e.g. the OS X application menu.
     *
     * @return true if the quit action is in an OS-specific menu
     */
    protected boolean isQuitInOSMenu() {
        return false;
    }


    /**
     * Checks and answers whether the about action has been moved to an
     * operating system specific menu, e.g. the OS X application menu.
     *
     * @return true if the about action is in an OS-specific menu
     */
    protected boolean isAboutInOSMenu() {
        return false;
    }


    // Higher Level Factory Methods *****************************************

	/**
	 * Creates and returns a JRadioButtonMenuItem
	 * with the given enablement and selection state.
	 */
	private JRadioButtonMenuItem createRadioItem(boolean enabled, boolean selected) {
		JRadioButtonMenuItem item = createRadioButtonMenuItem(
			getToggleLabel(enabled, selected),
			selected);
		item.setEnabled(enabled);
		item.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JRadioButtonMenuItem source = (JRadioButtonMenuItem) e.getSource();
				source.setText(getToggleLabel(source.isEnabled(), source.isSelected()));
			}
		});
		return item;
	}


	/**
	 * Creates and returns a JCheckBoxMenuItem
	 * with the given enablement and selection state.
	 */
	private JCheckBoxMenuItem createCheckItem(boolean enabled, boolean selected) {
		JCheckBoxMenuItem item = createCheckBoxMenuItem(
			getToggleLabel(enabled, selected),
			selected);
		item.setEnabled(enabled);
		item.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JCheckBoxMenuItem source = (JCheckBoxMenuItem) e.getSource();
				source.setText(getToggleLabel(source.isEnabled(), source.isSelected()));
			}
		});
		return item;
	}


	/**
	 *  Returns an appropriate label for the given enablement and selection state.
	 */
	protected String getToggleLabel(boolean enabled, boolean selected) {
		String prefix = enabled  ? "Enabled" : "Disabled";
		String suffix = selected ? "Selected" : "Deselected";
		return prefix + " and " + suffix;
	}


    // Helper Code ************************************************************

    /**
     * Looks up and returns an icon for the specified filename suffix.
     */
    private ImageIcon readImageIcon(String filename) {
        URL url = getClass().getResource("resources/images/" + filename);
        return new ImageIcon(url);
    }


    /**
     * Creates and returns a submenu labeled with the given text.
     */
    private JMenu createSubmenu(String text) {
        JMenu submenu = new JMenu(text);
        submenu.add(new DMenuItem("Item 1"));
        submenu.add(new DMenuItem("Item 2"));
        submenu.add(new DMenuItem("Item 3"));
        return submenu;
    }


    public void fillReport(Object para){
    	
    	
    }
    
    public Map<String, Object> getReportMap(){
    	return new HashMap<String, Object> ();
    	
    }
    
	public void actionPerformed(ActionEvent e) {
		String path = ReportDriver.class.getResource("ReportCowForZayesh.jrxml").getFile();
		path = path.substring(5,path.indexOf("lib")+"lib".length())+"/";
		path.replace('\\', '/');
		DMenuItem menuItem = (DMenuItem) e.getSource();
		if(e.getActionCommand().equals("ReportCowForZayesh.jrxml") ){
			Map<String, Object> parameterNumberDay =getReportMap();
			Integer minDay = new Integer(0) ;
			Integer maxDay = new Integer(0) ;
			parameterNumberDay = ReportParameter.parameterNumberDayMaxMin( maxDay, minDay);
			/*parameterNumberDay.put("minDay", minDay);
			parameterNumberDay.put("maxDay", maxDay);*/
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportCowForZayesh.jrxml",parameterNumberDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportCowForZayesh.jrxml").getFile(),parameterNumberDay);
			}
		else if (e.getActionCommand().equals("ReportCowForTest.jrxml")){
			Map<String, Object> parameterNumberDay = getReportMap();
			Integer parNumber = ReportParameter.parameterNumberDate();
			if(parNumber.equals(new Integer(0))){
				
			}else{
			parameterNumberDay.put("numberDay", parNumber);
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportCowForTest.jrxml",parameterNumberDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportCowForTest.jrxml").getFile(),parameterNumberDay);
			}
		}else if(e.getActionCommand().equals("ReportCowForTest2.jrxml")){
			Map<String, Object> parameterNumberDay = getReportMap();
			Integer minDay = new Integer(0);
			Integer maxDay = new Integer(0);
			parameterNumberDay = ReportParameter.parameterNumberDayMaxMin(maxDay, minDay);	
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportCowForTest2.jrxml",parameterNumberDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportCowForTest2.jrxml").getFile(),parameterNumberDay);
		}else if(e.getActionCommand().equals("ReportDamkhoshk.jrxml")){
			fillReport("ReportDamkhoshk.jrxml");
			Map<String, Object> parameterNumberDay = getReportMap();
			Integer minDay = new Integer(0);
			Integer maxDay = new Integer(0);
			parameterNumberDay = ReportParameter.parameterNumberDayMaxMin(maxDay, minDay);
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportDamkhoshk.jrxml",parameterNumberDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportDamkhoshk.jrxml").getFile(),parameterNumberDay);
		}else if(e.getActionCommand().equals("ReportJaygah.jrxml")){
			Map<String, Object> parameterJaygah =getReportMap();
			Integer parJaygah = ReportParameter.ParameterJaygah();
			if( parJaygah!= null){
			parameterJaygah.put("numberJaygah",parJaygah);
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportJaygah.jrxml",parameterJaygah);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportJaygah.jrxml").getFile(),parameterJaygah);
			}else{
				
			}
		}else if(e.getActionCommand().equals("ReportJireForJaygah.jrxml")){
			fillReport("ReportJireForJaygah.jrxml");
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportJireForJaygah.jrxml");
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportJireForJaygah.jrxml").getFile());
		}else if(e.getActionCommand().equals("ReportOPDam.jrxml")){
			fillReport("ReportOPDam.jrxml");		
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportOPDam.jrxml");
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportOPDam.jrxml").getFile());
		}else if (e.getActionCommand().equals("PercnSperm6mount.jrxml")){
			Map<String, Object> parameterSperm = getReportMap();
            parameterSperm = ReportParameter.paraleterPersentSperm(); 		
           // ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("PercnSperm6mount.jrxml").getFile(),parameterSperm);
			if(parameterSperm != null ){
				// ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"PercnSperm6mount.jrxml",parameterSperm);
				 ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("PercnSperm6mount.jrxml").getFile(),parameterSperm);
			}else{
					
			}
			
		}else if(e.getActionCommand().equals("AbestanForRengOfTime.jrxml")){
			Map<String, Object> parameterDate = getReportMap();
			parameterDate = ReportParameter.parameterDateSE();
			if( parameterDate != null){
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"AbestanForRengOfTime.jrxml",parameterDate);
            ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("AbestanForRengOfTime.jrxml").getFile(),parameterDate);
			}else{
				
			}
		}
		else if(e.getActionCommand().equals("ReportZayeshForOneRangOfTime.jrxml")){
			Map<String, Object> parameterDate = getReportMap();
			parameterDate = ReportParameter.parameterDateSE();
			if( parameterDate != null){
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportZayeshForOneRangOfTime.jrxml",parameterDate);
            ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportZayeshForOneRangOfTime.jrxml").getFile(),parameterDate);
			}else{
				
			}
		}else if(e.getActionCommand().equals("TalgihForRengOfTime.jrxml")){
			Map<String, Object> parameterDate = getReportMap();
			parameterDate = ReportParameter.parameterDateSE();
			if( parameterDate != null){
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"TalgihForRengOfTime.jrxml",parameterDate);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("TalgihForRengOfTime.jrxml").getFile(),parameterDate);
			}else{
				
			}
		}else if(e.getActionCommand().equals("ReportSpermUsesforoneRangOFTime.jrxml")){
			Map<String, Object> parameterSperm = getReportMap();
			Integer parSperm = ReportParameter.ParameterSperm();
			if( parSperm != null){
			parameterSperm.put("SpermNumber",parSperm);
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportSpermUsesforoneRangOFTime.jrxml",parameterSperm);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportSpermUsesforoneRangOFTime.jrxml").getFile(),parameterSperm);
			}else{
				
			}
		}else if(e.getActionCommand().equals("total_information.jrxml")){
			Map<String, Object> parameterCowNumber = getReportMap();
			String parSperm = ReportParameter.ParameterCowNumer();
			if( parSperm != null){
			parameterCowNumber.put("BodyNumder",parSperm);
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"total_information.jrxml",parameterCowNumber);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("total_information.jrxml").getFile(),parameterCowNumber);
			}else{
				
			}
		}else if(e.getActionCommand().equals("totalInformationCow.jrxml")){
			Map<String, Object> parameterCowNumber = getReportMap();
			String parSperm = ReportParameter.ParameterCowNumer();
			if( parSperm != null){
			parameterCowNumber.put("BodyNumder",parSperm);
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"totalInformationCow.jrxml",parameterCowNumber);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("totalInformationCow.jrxml").getFile(),parameterCowNumber);
			}else{
				
			}
		}
		else if(e.getActionCommand().equals("ReportMilkRcord.jrxml")){
			Map<String, Object> parameterDateRecord = getReportMap();
			Date parDate = ReportParameter.parameterDate();
			if(parDate != null){
				parameterDateRecord.put("dateRecord", parDate);
		//		ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportMilkRcord.jrxml",parameterDateRecord);
				ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportMilkRcord.jrxml").getFile(),parameterDateRecord);
			}else{
				
			}
		}else if(e.getActionCommand().equals("PercntPersonTalghihgarin6mount.jrxml")){
			Map<String, Object> parameter =getReportMap();
			parameter = ReportParameter.paraleterPersent();
			if(parameter != null){
		//		ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"PercntPersonTalghihgarin6mount.jrxml",parameter);
				ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("PercntPersonTalghihgarin6mount.jrxml").getFile(),parameter);
			}else{
				
			}
			
		}else if(e.getActionCommand().equals("PercentGale6mount.jrxml")){
           Map<String,Object> parameterjaygah = getReportMap();
           Integer numberJaygah = ReportParameter.ParameterJaygah();
           if(numberJaygah!= null){
        	   parameterjaygah.put("NumberJaygah", numberJaygah);
        	//   ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"PercentGale6mount.jrxml",parameterjaygah);
        	   ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("PercentGale6mount.jrxml").getFile(),parameterjaygah);
           }else{
        	   
           }
		}else if(e.getActionCommand().equals("ReportCowForMagnet.jrxml")){
			 fillReport("ReportCowForMagnet.jrxml");
		//	 ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportCowForMagnet.jrxml");
			   ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportCowForMagnet.jrxml").getFile());
		}else if(e.getActionCommand().equals("ReportCowForShakhSozi.jrxml")){
			fillReport("ReportCowForShakhSozi.jrxml");
		//	ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportCowForShakhSozi.jrxml");
			 ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportCowForShakhSozi.jrxml").getFile());
		}else if(e.getActionCommand().equals("ViewInformationCawReport.jrxml")){

			Map<String, Object> parameterCowNumber =  getReportMap();
			String parCowBody = ReportParameter.ParameterCowNumer();
			if( parCowBody != null){
			parameterCowNumber.put("numberBady",parCowBody);
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ViewInformationCawReport.jrxml",parameterCowNumber);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ViewInformationCawReport.jrxml").getFile(),parameterCowNumber);
			}else{
				
			}
		}else if(e.getActionCommand().equals("ReportPercentAbestani.jrxml")){

			Map<String, Object> parameterDate =  getReportMap();
			parameterDate = ReportParameter.parameterDateSE();
			if( parameterDate != null){
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportPercentAbestani.jrxml",parameterDate);
		    ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportPercentAbestani.jrxml").getFile(),parameterDate);
			}else{
			//	ReportGosaleForMilk.jrxml
			}
		}else if(e.getActionCommand().equals("ReportGosaleForMilk.jrxml")){

			Map<String, Object> parameternumberDay =  getReportMap();
			parameternumberDay.put("numberDay", ReportParameter.parameterNumberDateG());
			if( parameternumberDay != null){
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportGosaleForMilk.jrxml",parameternumberDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportGosaleForMilk.jrxml").getFile(),parameternumberDay);
			}else{
			//	ReportMojodiCow.jrxml
			}
		}else if(e.getActionCommand().equals("ReportJireCost.jrxml")){

			Map<String, Object> parameterjireName =  getReportMap();
			String jireName = ReportParameter.ParameterJireName();
			if( jireName != null){
				parameterjireName.put("jireName", jireName);
			// ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportJireCost.jrxml",parameterjireName);
		   ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportJireCost.jrxml").getFile(),parameterjireName);
			}else{
				
			}
		}else if(e.getActionCommand().equals("CowAfterZayemanNotTalghih.jrxml")){
			fillReport("ReportOPDam.jrxml");		
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"CowAfterZayemanNotTalghih.jrxml");
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("CowAfterZayemanNotTalghih.jrxml").getFile());
			//ReportTeliseForTalghih.jrxml
		}else if(e.getActionCommand().equals("ReportTeliseForTalghih.jrxml")){
			fillReport("ReportOPDam.jrxml");		
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportTeliseForTalghih.jrxml");
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportTeliseForTalghih.jrxml").getFile());
			//ReportTeliseForTalghih.jrxml
		}else if(e.getActionCommand().equals("ReportMojodiCow.jrxml")){

			Map<String, Object> parameterDay =  getReportMap();
			parameterDay.put("startDay", ReportParameter.parameterDate());
			if( parameterDay != null){
			//ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",path+"ReportMojodiCow.jrxml",parameterDay);
			ReportDriver.runReport("jdbc:oracle:thin:@127.0.0.1:1521:XE","damdary","k123456",ReportDriver.class.getResource("ReportMojodiCow.jrxml").getFile(),parameterDay);
			}else{
			//	ReportMojodiCow.jrxml
			}
		}
		else if(menuBarListener != null)
			menuBarListener.menuItemSelected((Component) e.getSource()
					,e.getActionCommand(),menuItem.getClasses());
	}
	
	
}