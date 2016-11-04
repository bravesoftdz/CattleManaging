package damdariar.gui.property;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import damdariar.images.ImageUtil;

public class GUISettingsDialog extends JDialog implements ActionListener{

	
	String defaultSettings = " \u0627\u0646\u062a\u062e\u0627\u0628 \u062a\u0646\u0638\u06cc\u0645\u0627\u062a \u067e\u06cc\u0634 \u0641\u0631\u0636 \u0638\u0627\u0647\u0631\u06cc \u062a\u0645\u0627\u0645 \u0633\u06cc\u0633\u062a\u0645";
	String formSettings = " \u0627\u0646\u062a\u062e\u0627\u0628 \u062a\u0646\u0638\u06cc\u0645\u0627\u062a  \u0638\u0627\u0647\u0631\u06cc \u0647\u0631 \u0641\u0631\u0645";
	Color  startColor;
	Color  endColor;
	Color  fontColor;
	Font   font;
	
	JTabbedPane tabbedPane;
	public GUISettingsDialog(){
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		add(tabbedPane);
		
		JPanel  defaultSettingsPanel = new JPanel(new FlowLayout());
		defaultSettingsPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		GridLayout defaultLayout = new GridLayout();
		JPanel defaultGradientPanel = new JPanel();
		defaultGradientPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		defaultGradientPanel.setLayout(defaultLayout);
		defaultLayout.setColumns(4);
		defaultLayout.setHgap(5);
		defaultLayout.setRows(2);
		defaultLayout.setVgap(5);
		
	    JLabel defaultStartColor = new JLabel(" \u0631\u0646\u06af \u0627\u0635\u0644\u06cc \u067e\u06cc\u0634 \u0632\u0645\u06cc\u0646\u0647");	
		
		JButton defaultStartColorButton = new JButton();
		defaultStartColorButton.setBackground(GUIProperty.startGradientColor);
		defaultStartColorButton.setActionCommand("StartColor");
		defaultStartColorButton.addActionListener(this);
		
		JLabel defaultEndColor = new JLabel(" \u0631\u0646\u06af \u0641\u0631\u0639\u06cc \u067e\u06cc\u0634 \u0632\u0645\u06cc\u0646\u0647 ");	
		
		
		JButton defaultEndColorButton = new JButton();
		defaultEndColorButton.setBackground(GUIProperty.endGradientColor);
		defaultEndColorButton.setActionCommand("EndColor");
		defaultEndColorButton.addActionListener(this);
		
		defaultGradientPanel.add(defaultStartColor);
		defaultGradientPanel.add(defaultStartColorButton);
		defaultGradientPanel.add(defaultEndColor);
		defaultGradientPanel.add(defaultEndColorButton);
	
		
		Border gradientBorer = new TitledBorder(" \u0627\u0646\u062a\u062e\u0627\u0628 \u0631\u0646\u06af \u067e\u06cc\u0634 \u0632\u0645\u06cc\u0646\u0647 \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u0645\u06cc\u0627\u0631");
		defaultGradientPanel.setBorder(gradientBorer);
		defaultGradientPanel.setOpaque(false);
		
		
		GridLayout defaultFontLayout = new GridLayout();
		JPanel defaultFontPanel = new JPanel();
		defaultFontPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		defaultFontPanel.setLayout(defaultFontLayout);
		defaultFontLayout.setColumns(4);
		defaultFontLayout.setHgap(5);
		defaultFontLayout.setRows(2);
		defaultFontLayout.setVgap(5);
		
		
		JLabel defaultFont = new JLabel(" \u0627\u0646\u062a\u062e\u0627\u0628 \u0641\u0648\u0646\u062a \u0628\u0631\u0686\u0633\u0628 \u0647\u0627 ");	
		
		
		JButton defaultFontButton = new JButton();
		defaultFontButton.setText(GUIProperty.font.getName());
		defaultFontButton.setFont(GUIProperty.font);
		defaultFontButton.setActionCommand("Font");
		defaultFontButton.addActionListener(this);
		
	    JLabel defaultFontColor = new JLabel("  \u0627\u0646\u062a\u062e\u0627\u0628 \u0631\u0646\u06af \u0641\u0648\u0646\u062a \u0628\u0631\u0686\u0633\u0628 \u0647\u0627 ");	
		
		JButton defaultFontColorButton = new JButton();
		defaultFontColorButton.setBackground(GUIProperty.fontColor);
		defaultFontColorButton.setActionCommand("FontColor");
		defaultFontColorButton.addActionListener(this);
		
		
		
		defaultFontPanel.add(defaultFont);
		defaultFontPanel.add(defaultFontButton);
		defaultFontPanel.add(defaultFontColor);
		defaultFontPanel.add(defaultFontColorButton);
	
		
		Border fontBorer = new TitledBorder(" \u0627\u0646\u062a\u062e\u0627\u0628 \u0631\u0646\u06af \u0641\u0648\u0646\u062a \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u0645\u06cc\u0627\u0631");
		defaultFontPanel.setBorder(fontBorer);
		defaultFontPanel.setOpaque(false);
		
		
		
		defaultSettingsPanel.add(defaultGradientPanel);
		defaultSettingsPanel.add(defaultFontPanel);
		defaultSettingsPanel.add(getConfirmPanel());
		
		tabbedPane.add(defaultSettings, defaultSettingsPanel);
//		tabbedPane.add(formSettings, new JLabel(" \u062f\u0631 \u062f\u0633\u062a \u0633\u0627\u062e\u062a"));
		
	}
	
	public JPanel getConfirmPanel(){
		
		JPanel confirmPanel = new JPanel();
		confirmPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridLayout defaultConfirmLayout = new GridLayout();
		confirmPanel.setLayout(defaultConfirmLayout);
		defaultConfirmLayout.setColumns(2);
		defaultConfirmLayout.setHgap(35);
		defaultConfirmLayout.setRows(1);
		defaultConfirmLayout.setVgap(35);
		
		
		JButton okButton = new JButton( "\u062a\u0627\u06cc\u06cc\u062f", new ImageIcon(ImageUtil.class.getResource("ok16.gif")));
		okButton.setActionCommand("OK");
		okButton.addActionListener(this);
		
		JButton cancelButton = new JButton( " \u0627\u0646\u0635\u0631\u0627\u0641", new ImageIcon(ImageUtil.class.getResource("cancel16.gif")));
		cancelButton.setActionCommand("CANCEL");
		cancelButton.addActionListener(this);
		
		
		confirmPanel.add(cancelButton);
		confirmPanel.add(okButton);
		
		confirmPanel.setOpaque(false);
		
		return confirmPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(e.getActionCommand().equalsIgnoreCase("OK")){
			 
			 if(startColor != null && endColor != null)
			 {
				 GUIProperty.setDefaultFormColor(startColor, endColor);
			 }
			 else  if(startColor != null && endColor == null){
				 
				 GUIProperty.setDefaultFormColor(startColor, GUIProperty.endGradientColor);
			 }
			 else  if(startColor == null && endColor != null){
				 
				 GUIProperty.setDefaultFormColor(GUIProperty.startGradientColor, endColor);
			 }
			 
			 if(fontColor != null	)
				 GUIProperty.setDefaultFontColor(fontColor);
			 
			 if(font != null)
				 GUIProperty.setDefaultFont(font);
			 
			 setVisible(false);
			 dispose();
				 
		 }
		 if(e.getActionCommand().equalsIgnoreCase("CANCEL")){
			 setVisible(false);
			 dispose();
		 }
		 if(e.getActionCommand().equalsIgnoreCase("StartColor")){
		
			 startColor = JColorChooser.showDialog
				(this, " \u0631\u0646\u06af \u0627\u0635\u0644\u06cc \u067e\u06cc\u0634 \u0632\u0645\u06cc\u0646\u0647", ((JButton)(e.getSource())).getBackground());
			 if(startColor != null && !startColor.equals(GUIProperty.startGradientColor))
			   ((JButton)(e.getSource())).setBackground(startColor);
			 else
				 startColor = null;
				 
		 }
		 if(e.getActionCommand().equalsIgnoreCase("EndColor")){
			 endColor = JColorChooser.showDialog
				(this, " \u0631\u0646\u06af \u0641\u0631\u0639\u06cc \u067e\u06cc\u0634 \u0632\u0645\u06cc\u0646\u0647", ((JButton)(e.getSource())).getBackground());
		 
			 if(endColor != null && !endColor.equals(GUIProperty.endGradientColor)) 
			 ((JButton)(e.getSource())).setBackground(endColor);
		 	else
		 	   endColor = null;
		 }
		 if(e.getActionCommand().equalsIgnoreCase("Font")){
			font =  FontChooser.showDialog
						(this, " \u0627\u0646\u062a\u062e\u0627\u0628  \u0641\u0648\u0646\u062a \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u0645\u06cc\u0627\u0631", font == null ? GUIProperty.font : font);
			
			if(!font.equals(GUIProperty.font)){
			   ((JButton)(e.getSource())).setText(font.getName());
			   ((JButton)(e.getSource())).setFont(font);
			}
			else
				font = null;
		 }
		 if(e.getActionCommand().equalsIgnoreCase("FontColor")){
			 
			fontColor = JColorChooser.showDialog
						(this, " \u0627\u0646\u062a\u062e\u0627\u0628 \u0631\u0646\u06af \u0641\u0648\u0646\u062a \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u0645\u06cc\u0627\u0631", ((JButton)(e.getSource())).getBackground());
			if(fontColor != null && !fontColor.equals(GUIProperty.fontColor)) 
				 ((JButton)(e.getSource())).setBackground(fontColor);
			else
				fontColor = null;
				
		 }
		 
		 
			 
	}
	
	public static void main(String[] args){
		
		GUIProperty.loadGuiProperty();
		GUISettingsDialog dialog = new GUISettingsDialog();
		dialog.setSize(270,250);
		dialog.setVisible(true);
		dialog.setResizable(false);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
}
