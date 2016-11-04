package damdariar.gui.property;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *  Font Chooser Dialog
 *
 *  @author     douran
 *  @version    $Id: FontChooser.java,v 1.1 2005/09/06 18:53:50 bahramian Exp $
 */
public class FontChooser extends JDialog implements ActionListener
{
/**
	 * 
	 */
	private static final long serialVersionUID = -5321401733236332175L;
	//	add by ghodmanan 18 farvardin 86 
	private int winFontNum=0; 
//	private String path=ClassLoader.getSystemClassLoader().getResource("org/dpdouran/font").toString().substring(6);
	private ArrayList<ArrayList> localNameAndObjectFonts=new ArrayList<ArrayList>();
//	*********************************
	/**
	 *  Show Dialog with initial font and return selected font
	 *  @param owner Base window
	 *  @param title Chooser Title
	 *  @param initFont initial font
	 *  @return selected font
	 */
	public static Font showDialog (Dialog owner, String title, Font initFont)
	{
		Font retValue = initFont;
		FontChooser fc = new FontChooser(owner, title, initFont);
		retValue = fc.getFont();
		fc = null;
		return retValue;
	}   //  showDialog

	/*************************************************************************/

	/**
	 *  Constructor
	 *
	 *  @param owner Base window
	 *  @param title Chooser Title
	 *  @param initFont Initial Font
	 */
	public FontChooser(Dialog owner, String title, Font initFont)
	{
		super(owner, title, true);
		try
		{
			jbInit();
			dynInit();
			setFont(initFont);
			GUIUtil.showCenterScreen(this);
		}
		catch(Exception ex)
		{
			System.err.println ("FontChooser");
			ex.printStackTrace();
		}
	}   //  FontChooser

	/**
	 *  IDE Constructor
	 */
	public FontChooser()
	{
		this (null," \u0627\u0646\u062a\u062e\u0627\u0628 \u0641\u0648\u0646\u062a", null);
	}   //  FontChooser

//	static ResourceBundle   s_res = ResourceBundle.getBundle("org.dpdouran.plaf.PlafRes");

	/** Static list of Styles       */
	public static FontStyle[] s_list = {
		new FontStyle(" Plain", Font.PLAIN),
		new FontStyle(" Italic ", Font.ITALIC),
		new FontStyle(" Bold ", Font.BOLD),
		new FontStyle(" Italic Bold", Font.BOLD|Font.ITALIC)};

	private Font        m_font = super.getFont();
	private Font        m_retFont = null;

	private boolean     m_setting = false;

	private JPanel mainPanel = new JPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private JPanel selectPanel = new JPanel();
	private JLabel nameLabel = new JLabel();
	private JComboBox fontName = new JComboBox();
	private JLabel sizeLabel = new JLabel();
	private JLabel styleLabel = new JLabel();
	private JComboBox fontStyle = new JComboBox();
	private JComboBox fontSize = new JComboBox();
	private JTextArea fontTest = new JTextArea();
	private JTextArea fontInfo = new JTextArea();
	private GridBagLayout selectLayout = new GridBagLayout();
	private JPanel confirmPanel = new JPanel();
	private JButton bCancel = GUIUtil.getCancelButton();
	private JButton bOK = GUIUtil.getOKButton();
	private FlowLayout confirmLayout = new FlowLayout();

	/**
	 *  Static Layout
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		selectPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainPanel.setLayout(mainLayout);
		nameLabel.setText(" \u0646\u0627\u0645 \u0641\u0648\u0646\u062a");
		selectPanel.setLayout(selectLayout);
		sizeLabel.setText(" \u0633\u0627\u06cc\u0632 \u0641\u0648\u0646\u062a ");
		styleLabel.setText(" \u0627\u0633\u062a\u06cc\u0644 \u0641\u0648\u0646\u062a");
		fontTest.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		fontTest.setText(" \u062a\u0633\u062a \u062a\u0633\u062a");
		fontTest.setLineWrap(true);
		fontTest.setWrapStyleWord(true);
//		fontTest.setBackground(dpdouranPLAF.getFieldBackground_Inactive());
		fontTest.setBorder(BorderFactory.createLoweredBevelBorder());
		fontTest.setPreferredSize(new Dimension(220, 100));
		fontInfo.setText(" \u0641\u0648\u0646\u062a \u0641\u0648\u0646\u062a");
		fontInfo.setLineWrap(true);
		fontInfo.setWrapStyleWord(true);
//		fontInfo.setBackground(dpdouranPLAF.getFieldBackground_Inactive());
		fontInfo.setOpaque(false);
		fontInfo.setEditable(false);
		confirmPanel.setLayout(confirmLayout);
		confirmLayout.setAlignment(FlowLayout.RIGHT);
		confirmPanel.setOpaque(false);
		selectPanel.setOpaque(false);
		getContentPane().add(mainPanel);
		mainPanel.add(selectPanel, BorderLayout.CENTER);
		selectPanel.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(fontName,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(sizeLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(styleLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(fontStyle,  new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(fontSize,  new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		selectPanel.add(fontTest,  new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 5, 5, 5), 0, 0));
//		selectPanel.add(fontInfo, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0
//			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 5, 10, 5), 0, 0));
		//
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.add(bOK, null);
		confirmPanel.add(bCancel, null);
		bCancel.addActionListener(this);
		bOK.addActionListener(this);
	}   //  jbInit

	/**
	 *  Dynamic Init
	 */
	private void dynInit()
	{
		
		// comment by ghodmanan 18 farvardin 86
//		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//		Arrays.sort(names);
//		for (int i = 0; i < names.length; i++)
//			fontName.addItem(names[i]);

		
		// change the above comment file by ghodmanan 18 farvardin 86
		String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		ArrayList<String>  allNames=new ArrayList<String>();
//		Arrays.sort(names);
		for (int i = 0; i < names.length ; i++)
			if(		names[i].toLowerCase().equals("Arial".toLowerCase())
				||	names[i].toLowerCase().equals("Arial Unicode MS".toLowerCase())
				||	 names[i].toLowerCase().equals("Courier New".toLowerCase())
				||	 names[i].toLowerCase().equals("Microsoft Sans Serif".toLowerCase())
				||	 names[i].toLowerCase().equals("Tahoma".toLowerCase())
				||	 names[i].toLowerCase().equals("Dialog".toLowerCase())
				||	 names[i].toLowerCase().equals("Times New Roman".toLowerCase())
				){
//					fontName.addItem(names[i]);
				allNames.add(names[i]);
				winFontNum++;
			}
//		while(path.contains("%20"))
//			path=path.replaceFirst("%20"," ");
//		File fontDir=new File(path);
		ArrayList<String> fontFamily=new ArrayList<String>();
		ArrayList<Font> fontObject=new ArrayList<Font>();
		File[] fontFiles=null;
		ArrayList<InputStream> fontStreams=new ArrayList<InputStream>();
	/*	try {
			
			JarFile infraJar=null;((JarURLConnection)FontJar.class.getResource("FontJar.class").openConnection()).getJarFile();
			 Enumeration infraJarc=infraJar.entries();
//			 ArrayList<String> nameTheme=new ArrayList<String>();
			 int i=0;
			 while(infraJarc.hasMoreElements()){
				 String name=infraJarc.nextElement().toString();
				 if(name.startsWith("org/dpdouran/font/") && name.length()>18 && (name.toLowerCase().endsWith(".ttf") || name.toLowerCase().endsWith(".fon"))){
					 fontStreams.add(infraJar.getInputStream(infraJar.getEntry(name)));
					 i++;
				 }
			 }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
//			fontFiles=fontDir.listFiles();
		}*/
		Font f;
		int length=0;
		if(fontFiles!=null)
			length=fontFiles.length;
		else if(fontStreams!=null)
			length=fontStreams.size();
		for(int j=0;j<length;j++){
		try {
			if(fontFiles!=null && fontFiles[j].isFile()&& ( typeOfFile(fontFiles[j].getName()).equals("ttf") || typeOfFile(fontFiles[j].getName()).equals("fon")))
				f = Font.createFont(Font.TRUETYPE_FONT,fontFiles[j]);
			else
				f = Font.createFont(Font.TRUETYPE_FONT,fontStreams.get(j));
			allNames.add(f.getFamily());
			fontFamily.add(f.getFamily());
			fontObject.add(f);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		localNameAndObjectFonts.add(fontFamily);
		localNameAndObjectFonts.add(fontObject);
		Object[] disAllName=allNames.toArray();
		Arrays.sort(disAllName);
		for (int i = 0; i < disAllName.length; i++)
			fontName.addItem(disAllName[i].toString());
		fontName.addActionListener(this);
		//*************
		
		
		//
		for (int i = 6; i < 32; i++)
			fontSize.addItem(String.valueOf(i));
		fontSize.addActionListener(this);
		//
		for (int i = 0; i < s_list.length; i++)
			fontStyle.addItem(s_list[i]);
		fontStyle.addActionListener(this);
	}   //  dynInit

	private String typeOfFile(String name) {
		// TODO Auto-generated method stub
		int i=name.length()-1;
		for(;i>=0 && name.charAt(i)!='.';i--);
			
		if(i>=0)
			return name.substring(i+1).toLowerCase();
		return null;
	}

	/**
	 *  Set Font - sets font for chooser - not the component font
	 *  @param font
	 */
	public void setFont(Font font)
	{
		if (font == null)
			return;
	//	Log.trace("FontChooser.setFont - " + font.toString());
		if (m_retFont == null)
			m_retFont = font;
		//
		fontTest.setFont(font);
		fontInfo.setFont(font);
		fontInfo.setText(font.toString());
		//
		m_setting = true;
		fontName.setSelectedItem(font.getName());
		if (!fontName.getSelectedItem().equals(font.getName()))
			System.err.println("FontChooser.setFont" + fontName.getSelectedItem().toString() + " <> " + font.getName());
		//
		fontSize.setSelectedItem(String.valueOf(font.getSize()));
		if (!fontSize.getSelectedItem().equals(String.valueOf(font.getSize())))
			System.err.println("FontChooser.setFont" + fontSize.getSelectedItem() + " <> " + font.getSize());
		//  find style
		for (int i = 0; i < s_list.length; i++)
			if (s_list[i].getID() == font.getStyle())
				fontStyle.setSelectedItem(s_list[i]);
		if (((FontStyle)fontStyle.getSelectedItem()).getID() != font.getStyle())
			System.err.println("FontChooser.setFont" + ((FontStyle)fontStyle.getSelectedItem()).getID() + " <> " + font.getStyle());
		//
		m_font = font;
		this.pack();
		m_setting = false;
	}   //  setFont

	/**
	 *  Return selected font
	 *  @return font
	 */
	public Font getFont()
	{
		return m_retFont;
	}   //  getFont

	/**
	 *  ActionListener
	 *  @param e
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (m_setting)
			return;

		if (e.getSource() == bOK)
		{
			m_retFont = m_font;
			dispose();
		}

		else if (e.getSource() == bCancel)
			dispose();

		else if (e.getSource() == fontName)
		{
			String s = fontName.getSelectedItem().toString();
			if(((ArrayList)localNameAndObjectFonts.get(0)).contains(s)){
				Font sefont=(Font)((ArrayList)localNameAndObjectFonts.get(1)).get(((ArrayList)localNameAndObjectFonts.get(0)).indexOf(s));
				m_font =new Font(sefont.getName(), m_font.getStyle(), m_font.getSize());
			}else
			m_font = new Font(s, m_font.getStyle(), m_font.getSize());
		}
		else if (e.getSource() == fontSize)
		{
			String s = fontSize.getSelectedItem().toString();
			m_font = new Font(m_font.getName(), m_font.getStyle(), Integer.parseInt(s));
		}
		else if (e.getSource() == fontStyle)
		{
			FontStyle fs = (FontStyle)fontStyle.getSelectedItem();
			m_font = new Font(m_font.getName(), fs.getID(), m_font.getSize());
		}
	//	System.out.println("NewFont - " + m_font.toString());
		setFont(m_font);
	}   //  actionPerformed
	
//****************************************************************

}   //  FontChooser

/**
 *  Font Style Value Object
 */
class FontStyle
{
	/**
	 *  Create FontStyle
	 *  @param name
	 *  @param id
	 */
	public FontStyle(String name, int id)
	{
		m_name = name;
		m_id = id;
	}   //  FontStyle

	private String  m_name;
	private int     m_id;

	/**
	 *  Get Name
	 *  @return name
	 */
	public String toString()
	{
		return m_name;
	}   //  getName

	/**
	 *  Get int value of Font Style
	 *  @return id
	 */
	public int getID()
	{
		return m_id;
	}   //  getID
}   //  FontStyle
