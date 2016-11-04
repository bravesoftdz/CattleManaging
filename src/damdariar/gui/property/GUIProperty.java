package damdariar.gui.property;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import damdariar.beans.CGuiProperty;
import damdariar.gui.swing.forms.listeners.GuiPropertyChangeListener;
import damdariar.hibernate.HibernateUtil;

public class GUIProperty {
	
    public static CGuiProperty defaultProperty ;
	public static Color startGradientColor;
	public static Color endGradientColor;
	public static Color fontColor;
	public static Font  font;
	public static GradientColorPair colorPair;
	
	public static String SYSTEM_NAME = "DAMYAR_SOFTWARE_GUI_SETTINGS";
	private GuiPropertyChangeListener formColorChangeListener;
//	private GuiPropertyChangeListener formFontChangeListener;
//	private GuiPropertyChangeListener formFontColorChangeListener;

	public void addFormColorChangeListener(GuiPropertyChangeListener changeListener){
		
		this.formColorChangeListener = changeListener;
	}
	

	
	public static Map<String,CGuiProperty> colorMap = new HashMap<String, CGuiProperty>();;
	public static GUIProperty INSTANCE ;
	static{
		if(INSTANCE == null)
			INSTANCE = new GUIProperty();
	}
	
	static GUIProperty  getInstance(){
		return INSTANCE;
		
	}
	
	public static void setFormColor(String  className,Color startColor,Color endColor){
		
	   if(colorMap.containsKey(className)){
		   CGuiProperty property =   colorMap.get(className);
		   property.setColorName(convertColorToString(startColor,endColor));
		   HibernateUtil.saveOrUpdate(property);
	   }	
	   else{
		   CGuiProperty property = new CGuiProperty();
		   property.setClassName(className);
		   property.setColorName(convertColorToString(startColor,endColor));
		   HibernateUtil.saveOrUpdate(property);
		   colorMap.put(className, property);
		
		   
	   }
		
	}
	
	public static void setFontColor(String className,Color formColor){
		  if( colorMap.containsKey(className)){
			   CGuiProperty property =   colorMap.get(className);
			   property.setFontColor(convertSingleColorToString(formColor));
			   HibernateUtil.saveOrUpdate(property);
		   }	
		   else{
			   CGuiProperty property = new CGuiProperty();
			   property.setClassName(className);
			   property.setFontColor(convertSingleColorToString(formColor));
			   HibernateUtil.saveOrUpdate(property);
			   colorMap.put(className, property);
			   
		   }
		
	}
	
	public static void setFormFont(String className,Font font){
		  if( colorMap.containsKey(className)){
			   CGuiProperty property =   colorMap.get(className);
			   property.setFontName(convertFontToString(font));
			   HibernateUtil.saveOrUpdate(property);
		   }	
		   else{
			   CGuiProperty property = new CGuiProperty();
			   property.setClassName(className);
			   property.setFontName(convertFontToString(font));
			   HibernateUtil.saveOrUpdate(property);
			   colorMap.put(className, property);
			   
		   }
		
	}
	
	public static void setDefaultFont(Font font){
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		GUIProperty.font = font;
		String fontName = convertFontToString(font);
		property.setFontName(fontName);
		HibernateUtil.saveOrUpdate(property);
	}
	

	public static void setDefaultFontColor(Color fontColor){
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		GUIProperty.fontColor = fontColor;
		String color = convertSingleColorToString(fontColor);
		property.setFontColor(color);
		HibernateUtil.saveOrUpdate(property);
	}
	
	public static void setDefaultFormColor(Color startColor,Color endColor){
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		GUIProperty.startGradientColor = startColor;
		GUIProperty.endGradientColor = endColor;
		GUIProperty.colorPair = new GradientColorPair(startColor,endColor);
		String color = convertColorToString(startColor,endColor);
		property.setColorName(color);
		HibernateUtil.saveOrUpdate(property);
		if(INSTANCE.formColorChangeListener != null)
			INSTANCE.formColorChangeListener.fireGuiPropertyChanged();
	}
	
	public static void setPassword(String str){
		
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		property.setPassword(str);
		HibernateUtil.saveOrUpdate(property);

	}
	
	
	public static String getPassword(){
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		if(property != null)
			return property.getPassword();
		return null;
		
	}
	public static void setDefaultGUISettings(Color startColor,Color endColor,Color fontColor,Font font){
		CGuiProperty property =colorMap.get(SYSTEM_NAME);
		
		GUIProperty.font = font;
		GUIProperty.fontColor = fontColor;
		GUIProperty.startGradientColor = startColor;
		GUIProperty.endGradientColor = endColor;
		GUIProperty.colorPair = new GradientColorPair(startColor,endColor);
		
		String fontName = convertFontToString(font);
		String colorFont = convertSingleColorToString(fontColor);
		String colorForm = convertColorToString(startColor,endColor);
		property.setFontName(fontName);
		property.setFontColor(colorFont);
		property.setColorName(colorForm);
		HibernateUtil.saveOrUpdate(property);
	}
	
	public static void loadGuiProperty(){
		List<CGuiProperty> guiProperties =   (List<CGuiProperty>) HibernateUtil.getListOfObjects(CGuiProperty.class);
		for(CGuiProperty property:guiProperties){
			colorMap.put(property.getClassName(),property);	
		}
		if(!colorMap.containsKey(SYSTEM_NAME)){
			
			 CGuiProperty property = new CGuiProperty();
			 property.setClassName(SYSTEM_NAME);
			 property.setColorName(convertColorToString(Color.BLUE, Color.LIGHT_GRAY)); 
			 property.setFontName(convertFontToString(new Font("Dialog",Font.BOLD,12)));
			 property.setFontColor(convertSingleColorToString(Color.black));
			 HibernateUtil.saveOrUpdate(property);
			 colorMap.put(SYSTEM_NAME, property);
			
		}
			
			defaultProperty = colorMap.get(SYSTEM_NAME);
			colorPair          = convertStringToColor(defaultProperty.getColorName());
			startGradientColor = colorPair.getBackGroundColorStart();
			endGradientColor   = colorPair.getBackGroundColorEnd();
			font               = convertStringToFont(defaultProperty.getFontName());
			fontColor          = convertStringToSingleColor(defaultProperty.getFontColor());
			if(defaultProperty.getPassword() == null || defaultProperty.getPassword().trim().equals(""))	
			{
				 InfoDialog info = new InfoDialog();
				 info.askInfo();	
			}
		
		
	}
	
	public  GradientColorPair getBackGroundColor(String className){
		CGuiProperty property = colorMap.get(className);
		if(property == null || property.getColorName() == null || "".equals(property.getColorName().trim()))
			return colorPair;
     	return  convertStringToColor(property.getColorName());
		
	}
	
	public Font      getFont(String className){
		CGuiProperty property = colorMap.get(className);
		if(property == null || property.getFontName() == null || "".equals(property.getFontName().trim()))
			return font;
		return convertStringToFont(property.getFontName());
		
	}
	
	public Color    getColroFont(String className){
		CGuiProperty property = colorMap.get(className);
		if(property == null || property.getFontColor() == null || "".equals(property.getFontColor().trim()))
			return fontColor;
		return convertStringToSingleColor(property.getFontColor());
		
	}
	
	public static String convertFontToString(Font ft){
		
		String name = ft.getName();
		int    style = ft.getStyle();
		int    size = ft.getSize();
		
		return name +"@STYLE@" + style + "@SIZE@"+ size;
		
	}
	
    public static Font convertStringToFont(String font){
		int styleIndex = font.indexOf("@STYLE@");
		int sizeIndex = font.indexOf("@SIZE@");
    	String name = font.substring(0,styleIndex);
    	int style =   Integer.parseInt(font.substring(styleIndex+"@STYLE@".length(),sizeIndex));
    	int size =    Integer.parseInt(font.substring(sizeIndex+"@SIZE@".length()));
		return new Font(name,style,size);
	}
	
	
	public static String convertColorToString(Color colorStart,Color colorEnd){
		
		
  	    String redS =   colorStart.getRed()+"RES";
		String greenS = colorStart.getGreen()+"GES";
		String blueS =  colorStart.getBlue()+"BES";
		
		String redE =   colorEnd.getRed()+"REE";
		String greenE = colorEnd.getGreen()+"GEE";
		String blueE =  colorEnd.getBlue()+"";
		
		return redS+greenS+blueS+redE+greenE+blueE;
		
	}
	
	public static String convertSingleColorToString(Color color){
		
  	    String red =   color.getRed()+"RE";
		String green = color.getGreen()+"GE";
		String blue =  color.getBlue()+"";
		
		return red+green+blue;
	}
	
	public static Color convertStringToSingleColor(String color){
		
		int redEnd= color.indexOf("RE");
		int greenEnd = color.indexOf("GE");
		
		int red =   Integer.parseInt(color.substring(0,redEnd));
		int green = Integer.parseInt(color.substring(redEnd+2,greenEnd));
		int blue =  Integer.parseInt(color.substring(greenEnd+2));
		
		return new Color(red,green,blue);
		
	}

	
    public static GradientColorPair convertStringToColor(String color){
		int redSEnd= color.indexOf("RES");
		int greenSEnd = color.indexOf("GES");
		int blueSEnd = color.indexOf("BES");
		
		int redEEnd= color.indexOf("REE");
		int greenEEnd = color.indexOf("GEE");
		
		int redS =   Integer.parseInt(color.substring(0,redSEnd));
		int greenS = Integer.parseInt(color.substring(redSEnd+3,greenSEnd));
		int blueS = Integer.parseInt(color.substring(greenSEnd+3,blueSEnd));
		
		int redE =   Integer.parseInt(color.substring(blueSEnd+3,redEEnd));
		int greenE = Integer.parseInt(color.substring(redEEnd+3,greenEEnd));
		int blueE = Integer.parseInt(color.substring(greenEEnd+3));
		
		return new  GradientColorPair(new Color(redS,greenS,blueS),new Color(redE,greenE,blueE));
		
	}
	
	public GUIProperty(){
		
	}

}
