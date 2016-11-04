package damdariar.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.flexdock.dockbar.Dockbar;
import org.flexdock.dockbar.DockbarManager;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.props.DockablePropertySet;
import org.flexdock.view.View;

import com.jgoodies.looks.LookUtils;
import com.jgoodies.looks.Options;
import com.jgoodies.looks.demo.Settings;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;

import damdariar.gui.property.GUIDefaultValues;
import damdariar.gui.property.GUIProperty;
import damdariar.gui.property.GUISettingsDialog;
import damdariar.gui.property.GUIUtil;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.forms.AbstractFormContentPane;
import damdariar.gui.swing.forms.CPropertyForm;
import damdariar.gui.swing.forms.listeners.FormActionListener;
import damdariar.gui.swing.forms.listeners.MenuItemActionListener;
import damdariar.hibernate.HibernateUtil;
import damdariar.images.ImageUtil;
import damdariar.resource.ResourceUtil;

public class Main extends JFrame implements MenuItemActionListener,ActionListener,WindowListener,ComponentListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final Dimension PREFERRED_SIZE =
        LookUtils.IS_LOW_RESOLUTION
            ? new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height-25/*650+300, 510+150+20*/)
            : new Dimension(730+300, 560+150+20);


    private static final String COPYRIGHT =
        "\u00a9 2001-2009 JGoodies Karsten Lentzsch. All Rights Reserved.";


	public static Main instance;


	public  static  View advanceSearchView = new View("AdvanceSearch", " \u062c\u0633\u062a\u062c\u0648\u06cc \u067e\u06cc\u0634\u0631\u0641\u062a\u0647");
	public  static  View exitView = new View("ExitForm", " \u062b\u0628\u062a \u062e\u0631\u0648\u062c \u062f\u0627\u0645 F5");
	public  static  View zayemanView = new View("ZayemanForm"," \u062b\u0628\u062a \u0632\u0627\u06cc\u0645\u0627\u0646 F2");
	public  static  View talghihView = new View("TalghihForm"," \u062b\u0628\u062a \u062a\u0644\u0642\u06cc\u062d \u0648 \u062a\u0633\u062a F3");
	public  static  View bimariView = new View("BimariForm", " \u062b\u0628\u062a \u0628\u06cc\u0645\u0627\u0631\u06cc F4");
	
	
    /** Describes optional settings of the JGoodies Looks. */
    private final Settings settings;
    
    
    private ContentPanel contentPane ;
    private Component currentContentPane;
    private Component home;

    /**
     * Constructs a <code>DemoFrame</code>, configures the UI,
     * and builds the content.
     */
    protected Main(Settings settings) {
        this.settings = settings;      
        configureUI();
        build();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public static JWindow  getSplash(){
    	
    	JWindow window = new JWindow();
    	JProgressBar  bar = new JProgressBar();
    	bar.setString("          \u062f\u0631 \u062d\u0627\u0644 \u0628\u0627\u0631 \u06af\u0630\u0627\u0631\u06cc \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u0645\u06cc\u0627\u0631  \u060c \u0644\u0637\u0641\u0627 \u0635\u0628\u0631 \u0646\u0645\u0627\u06cc\u06cc\u062f       ");
    	bar.setStringPainted(true);
    	bar.setIndeterminate(true);
    	window.add(bar,BorderLayout.CENTER);
//    	window.add(new JLabel(ImageUtil.getImageIcon("loading.gif")),BorderLayout.WEST);
    	window.setSize(250,50);
    	window.setAlwaysOnTop(true);
    	GUIUtil.showCenterScreen(window);
    	return window;
    }
    
    public DockbarManager manager;
    public Dockbar        leftBar;
    
    public   void initializeDockPanelSize(){
    	
        manager = DockbarManager.getInstance(Main.instance);
    	leftBar = manager.getLeftBar();
    	
      	DockablePropertySet props = advanceSearchView.getDockingProperties();
		props.setPreviewSize(.48f);
		
		DockablePropertySet propsExit = exitView.getDockingProperties();
		propsExit.setPreviewSize(.44f);
		
		DockablePropertySet propsZayeman =  zayemanView.getDockingProperties();
		propsZayeman.setPreviewSize(.7f);
		
		
		DockablePropertySet propsBimari = bimariView.getDockingProperties();
		propsBimari.setPreviewSize(.5f);
		
		DockablePropertySet propsTalghih = talghihView.getDockingProperties();
		propsTalghih.setPreviewSize(.6f);
		
		
    }
    public static void main(String[] args) {
    	JWindow win = getSplash();
        Settings settings = createDefaultSettings();
        if (args.length > 0) {
            String lafShortName = args[0];
            String lafClassName;
            if ("Windows".equalsIgnoreCase(lafShortName)) {
                lafClassName = Options.JGOODIES_WINDOWS_NAME;
            } else if ("Plastic".equalsIgnoreCase(lafShortName)) {
                lafClassName = Options.PLASTIC_NAME;
            } else if ("Plastic3D".equalsIgnoreCase(lafShortName)) {
                lafClassName = Options.PLASTIC3D_NAME;
            } else if ("PlasticXP".equalsIgnoreCase(lafShortName)) {
                lafClassName = Options.PLASTICXP_NAME;
            } else {
                lafClassName = lafShortName;
            }
            System.out.println("L&f chosen: " + lafClassName);
            settings.setSelectedLookAndFeel(lafClassName);
        }
        
        startUp.ubdateStatusCow();
    	GUIProperty.loadGuiProperty();
    	GUIDefaultValues.loadDefaultValues();
        instance = new Main(settings);
        instance.initializeDockPanelSize();
        win.setVisible(false);
    	win.dispose();
        instance.setSize(PREFERRED_SIZE);
        instance.addWindowListener(instance);
        instance.setLocation(0, 0);
//      instance.locateOnScreen(instance);
//        instance.setResizable(false);
        instance.setVisible(true);
    }
    

    public static Main getInstance() {
		return instance;
	}

	private static Settings createDefaultSettings() {
        Settings settings = Settings.createDefault();

        // Configure the settings here.

        return settings;
    }


    /**
     * Configures the user interface; requests Swing settings and
     * JGoodies Looks options from the launcher.
     */
    private void configureUI() {
        // UIManager.put("ToolTip.hideAccelerator", Boolean.FALSE);

        Options.setDefaultIconSize(new Dimension(18, 18));

        Options.setUseNarrowButtons(settings.isUseNarrowButtons());

        // Global options
        Options.setTabIconsEnabled(settings.isTabIconsEnabled());
        UIManager.put(Options.POPUP_DROP_SHADOW_ENABLED_KEY,
                settings.isPopupDropShadowEnabled());

        // Swing Settings
        LookAndFeel selectedLaf = settings.getSelectedLookAndFeel();
        if (selectedLaf instanceof PlasticLookAndFeel) {
            PlasticLookAndFeel.setPlasticTheme(settings.getSelectedTheme());
            PlasticLookAndFeel.setTabStyle(settings.getPlasticTabStyle());
            PlasticLookAndFeel.setHighContrastFocusColorsEnabled(
                settings.isPlasticHighContrastFocusEnabled());
        } else if (selectedLaf.getClass() == MetalLookAndFeel.class) {
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
        }

        // Work around caching in MetalRadioButtonUI
        JRadioButton radio = new JRadioButton();
        radio.getUI().uninstallUI(radio);
        JCheckBox checkBox = new JCheckBox();
        checkBox.getUI().uninstallUI(checkBox);

        try {
            UIManager.setLookAndFeel(selectedLaf);
        } catch (Exception e) {
            System.out.println("Can't change L&F: " + e);
        }

    }

    /**
     * Builds the <code>DemoFrame</code> using Options from the Launcher.
     */
    private void build() {
    	applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        setContentPane(buildContentPane());
        setTitle(getWindowTitle());
        setJMenuBar(
            createMenuBuilder(this).buildMenuBar(
                settings,
                createHelpActionListener(),
                createAboutActionListener()));
        setIconImage(readImageIcon("eye_16x16.gif").getImage());
    }


    /**
     * Creates and returns a builder that builds the menu.
     * This method is overriden by the full JGoodies Looks Demo to use
     * a more sophisticated menu builder that uses the JGoodies
     * UI Framework.
     * @param mainFrameUtil 
     *
     * @return the builder that builds the menu bar
     */
    protected MenuBarView createMenuBuilder(Main mainFrameUtil) {
        return new MenuBarView(mainFrameUtil);
    }

    /**
     * Builds and answers the content.
     */
    private JComponent buildContentPane() {
        contentPane = new ContentPanel(new BorderLayout());
        contentPane.add(buildToolBar(), BorderLayout.NORTH);
        contentPane.add(buildMainPanel(), BorderLayout.CENTER);
        return contentPane;
    }

    // Tool Bar *************************************************************

    /**
     * Builds, configures and returns the toolbar. Requests
     * HeaderStyle, look-specific BorderStyles, and Plastic 3D Hint
     * from Launcher.
     */
    private Component buildToolBar() {
        DToolBar toolBar = new DToolBar();
//        toolBar.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        toolBar.setFloatable(true);
        toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
        // Swing
        toolBar.putClientProperty(
            Options.HEADER_STYLE_KEY,
            settings.getToolBarHeaderStyle());
        toolBar.putClientProperty(
            PlasticLookAndFeel.BORDER_STYLE_KEY,
            settings.getToolBarPlasticBorderStyle());
        toolBar.putClientProperty(
            WindowsLookAndFeel.BORDER_STYLE_KEY,
            settings.getToolBarWindowsBorderStyle());
        toolBar.putClientProperty(
            PlasticLookAndFeel.IS_3D_KEY,
            settings.getToolBar3DHint());

        AbstractButton button;
/*
        toolBar.add(createToolBarButton("backward.gif", "Back"));
        button = createToolBarButton("forward.gif", "Next");
        button.setEnabled(false);
        toolBar.add(button);*/
        
        button = createToolBarButton("home.gif", ResourceUtil.getString("HOME"));
        toolBar.add(button);
        button.addActionListener(this);
        button.setActionCommand("home");
//        toolBar.addSeparator();
        
        button = createToolBarButton("preference16.gif", ResourceUtil.getString("PREFERENCES"));
        toolBar.add(button);
        button.setRolloverIcon(readImageIcon("preference16_over.gif"));
        button.addActionListener(this);
        button.setActionCommand("Preferences");
        toolBar.addSeparator();

        button = createToolBarButton("new16.gif", ResourceUtil.getString("NEW"));
        button.setActionCommand("new");
        button.addActionListener(this);
        toolBar.add(button);
        
        button = createToolBarButton("save_edit.gif", ResourceUtil.getString("SAVE"));
        button.setActionCommand("save");
        button.addActionListener(this);
        toolBar.add(button);
        
        button = createToolBarButton("delete16.gif", ResourceUtil.getString("DELETE"));
        button.setActionCommand("delete");
        button.addActionListener(this);
        toolBar.add(button);
        
        button = createToolBarButton("print.gif", ResourceUtil.getString("PRINT"));
        button.setActionCommand("print");
        button.addActionListener(this);
        toolBar.add(button);
/*        
        toolBar.add(createToolBarButton("print.gif", "Print"));
        toolBar.add(createToolBarButton("refresh.gif", "Update"));
        toolBar.addSeparator();

        ButtonGroup group = new ButtonGroup();
        button = createToolBarRadioButton("pie_mode.png", "Pie Chart");
        button.setSelectedIcon(readImageIcon("pie_mode_selected.gif"));
        group.add(button);
        button.setSelected(true);
        toolBar.add(button);

        button = createToolBarRadioButton("bar_mode.png", "Bar Chart");
        button.setSelectedIcon(readImageIcon("bar_mode_selected.gif"));
        group.add(button);
        toolBar.add(button);

        button = createToolBarRadioButton("table_mode.png", "Table");
        button.setSelectedIcon(readImageIcon("table_mode_selected.gif"));
        group.add(button);
        toolBar.add(button);
        toolBar.addSeparator();

        button = createToolBarButton("help.gif", "Open Help");
        button.addActionListener(createHelpActionListener());
        toolBar.add(button);*/

        return toolBar;
    }

    /**
     * Creates and returns a JButton configured for use in a JToolBar.<p>
     *
     * This is a simplified method that is overriden by the Looks Demo.
     * The full code uses the JGoodies UI framework's ToolBarButton
     * that better handles platform differences.
     */
    protected AbstractButton createToolBarButton(String iconName, String toolTipText) {
        JButton button = new JButton(readImageIcon(iconName));
        button.setToolTipText(toolTipText);
        button.setFocusable(false);
        return button;
    }
    

    private AbstractButton createToolBarButton(String iconName, String toolTipText, ActionListener action, KeyStroke keyStroke) {
        AbstractButton button = createToolBarButton(iconName, toolTipText);
        button.registerKeyboardAction(action, keyStroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        return button;
    }

    /**
     * Creates and returns a JToggleButton configured for use in a JToolBar.<p>
     *
     * This is a simplified method that is overriden by the Looks Demo.
     * The full code uses the JGoodies UI framework's ToolBarButton
     * that better handles platform differences.
     */
    protected AbstractButton createToolBarRadioButton(String iconName, String toolTipText) {
        JToggleButton button = new JToggleButton(readImageIcon(iconName));
        button.setToolTipText(toolTipText);
        button.setFocusable(false);
        return button;
    }

    // Tabbed Pane **********************************************************

    /**
     * Builds and answers the tabbed pane.
     */
    private Component buildMainPanel() {
    	DPanel mainPanel = new DPanel();
    	JLabel backGroundLabel = new JLabel(ImageUtil.getImageIcon("happycow.jpg"));
    	mainPanel.add(backGroundLabel);
    	setCurrentContentPane(mainPanel);
    	home = mainPanel;
        return home;
    }
    


    protected String getWindowTitle() {
        return  ResourceUtil.getString("SOFTWARE_TITLE");
    }


    // Helper Code **********************************************************************

    /**
     * Looks up and returns an icon for the specified filename suffix.
     */
    protected static ImageIcon readImageIcon(String filename) {
        URL url = Main.class.getResource("resources/images/" + filename);
        return new ImageIcon(url);
    }

    /**
     * Locates the given component on the screen's center.
     */
    protected void locateOnScreen(Component component) {
    		 Dimension paneSize = component.getSize();
    	        Dimension screenSize = component.getToolkit().getScreenSize();
    	        component.setLocation(
    	            (screenSize.width  - paneSize.width)  / 2,
    	            (screenSize.height - paneSize.height) / 2);
			// TODO: handle exception
       
    }

    /**
     * Creates and answers an ActionListener that opens the help viewer.
     */
    protected ActionListener createHelpActionListener() {
        return null;
    }

    /**
     * Creates and answers an ActionListener that opens the about dialog.
     */
    protected ActionListener createAboutActionListener() {
        return new AboutActionListener();
    }


    private final class AboutActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(
                Main.this,
                "The simple Looks Demo Application\n\n"
                    + COPYRIGHT + "\n\n");
        }
    }

    private final class OpenFileActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new JFileChooser().showOpenDialog(Main.this);
        }
    }
    
    
    public void registerContentPane(Component comp){
    	
    	disposeFormResources();
    	contentPane.remove(getCurrentContentPane());
    	contentPane.add(comp,BorderLayout.CENTER);
    	contentPane.validate();
    	setCurrentContentPane(comp);
    	repaint();
    	
    	
    }

	/**
	 * @return the currentPanel
	 */
	public Component getCurrentContentPane() {
		return currentContentPane;
	}
	
	public FormActionListener getCurrentFormActionListener(){
		
		JComponent comp = (JComponent) getCurrentContentPane();
		if(comp instanceof JTabbedPane)
		{
			FormActionListener actionListener = (FormActionListener)(((JTabbedPane)comp).getSelectedComponent());
			return actionListener;
		}
		return null;
	}

	/**
	 * @param currentPanel the currentPanel to set
	 */
	public void setCurrentContentPane(Component currentContentPane) {
		this.currentContentPane = currentContentPane;
	}

	public void disposeFormResources(){
		Component comp = getCurrentContentPane();
		if(comp != null && comp instanceof DTabbedPane)
		{
			((AbstractFormContentPane) (((DTabbedPane)comp).getSelectedComponent())).dispose();
		}

	}
	public void menuItemSelected(Component source,String actionCommand,Class<?>[] classes) {
		HibernateUtil.closeCurrentSession();
		try{
			DTabbedPane tabbedPane;
			if(classes != null){
			 tabbedPane = new DTabbedPane(JTabbedPane.TOP,classes);
			}
			else{
			  tabbedPane = new DTabbedPane(JTabbedPane.TOP);
			  Class<?> cls = Class.forName(actionCommand);
			  AbstractFormContentPane contentPane = new AbstractFormContentPane(cls);
			  String tabName = ResourceUtil.getTabName(cls.getSimpleName());
			  tabbedPane.add(tabName,contentPane);
			}
			
			registerContentPane(tabbedPane);
			 
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equalsIgnoreCase("home")){
			 DockingManager.close(DockingManager.getDockable("AdvanceSearch"));
			 AbstractFormContentPane.AppView = null;
			 registerContentPane(home);
		 }
		 else if(e.getActionCommand().equalsIgnoreCase("Preferences"))
		 {
			 GUISettingsDialog dialog = new GUISettingsDialog();
			 dialog.setSize(270,250);
			 GUIUtil.showCenterScreen(dialog);
			 dialog.setResizable(false);
			 dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 }
		 else if( getCurrentFormActionListener() != null){
			if(e.getActionCommand().equalsIgnoreCase("delete") )
				getCurrentFormActionListener().deleteAction();
			else if(e.getActionCommand().equalsIgnoreCase("save") )
				getCurrentFormActionListener().saveAction();
			else if(e.getActionCommand().equalsIgnoreCase("new") )
				getCurrentFormActionListener().newAction();
			else if(e.getActionCommand().equalsIgnoreCase("print"))
				getCurrentFormActionListener().printAction();
		}
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		disposeFormResources();
		HibernateUtil.close();
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setVisible(boolean bool){
		super.setVisible(bool);
		if(!bool){
			HibernateUtil.close();
			instance = null;
			System.exit(0);
		}
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
		
	}

   public static DPanel initialPanel = getTreeWaitingPanel();
   public static DPanel getTreeWaitingPanel(){
	   if(initialPanel != null)
		   return initialPanel;
		initialPanel = new DPanel(new BorderLayout());
		JLabel lab = new JLabel(" \u062f\u0631 \u062d\u0627\u0644 \u0633\u0627\u062e\u062a\u0646 \u062f\u0631\u062e\u062a \u0648\u0631\u0627\u062b\u062a\u06cc \u062f\u0627\u0645 ");
		lab.setOpaque(false);
		lab.setIcon(ImageUtil.getImageIcon("loading.gif"));
		lab.setVerticalTextPosition(SwingConstants.TOP);
		lab.setHorizontalTextPosition(SwingConstants.LEFT);
		Font fon = lab.getFont();
		Font newFon = new Font(fon.getFamily(),Font.BOLD|Font.ITALIC,10);
		lab.setFont(newFon);
		initialPanel.add(lab);
		return initialPanel;
	   
   }
}