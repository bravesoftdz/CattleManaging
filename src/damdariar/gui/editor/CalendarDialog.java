package damdariar.gui.editor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ghasemkiani.util.icu.PersianCalendar;

import damdariar.gui.swing.CalendarLabel;
import damdariar.gui.swing.DLabel;
import damdariar.gui.swing.DPanel;
import damdariar.gui.swing.forms.listeners.DateDialogListener;
import damdariar.gui.swing.layout.GridLayoutManager;
import damdariar.images.ImageUtil;
import damdariar.images.date.DayOfMonth;

public class CalendarDialog extends JDialog implements ChangeListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int WIDTH = 250;
	int HEIGHT = 200;
	
	PersianCalendar pc;
	Date   initialCalendar;
	DPanel calendar ;
	DPanel headerPanel;
	DPanel daysPanel;
	DPanel confirmPanel;
	ButtonGroup group;
	
	JSpinner  yearSpinner = new JSpinner();
	JComboBox month = new JComboBox(monthNames);
	private DateDialogListener calendarListener;
	private boolean withTime=false;	
	public static String[] monthNames = new String[]{
		 "\u0641\u0631\u0648\u0631\u062f\u06cc\u0646",
		 " \u0627\u0631\u062f\u06cc\u0628\u0647\u0634\u062a",
		 " \u062e\u0631\u062f\u0627\u062f",
		 " \u062a\u06cc\u0631",
		 " \u0645\u0631\u062f\u0627\u062f",
		 " \u0634\u0647\u0631\u06cc\u0648\u0631",
		 " \u0645\u0647\u0631",
		 " \u0622\u0628\u0627\u0646",
		 " \u0622\u0630\u0631",
		 " \u062f\u06cc",
		 " \u0628\u0647\u0645\u0646",
		 " \u0627\u0633\u0641\u0646\u062f"	
	};
	
	public static String[] dayOfWeek = new String[]{
		
		 "\u0634\u0646\u0628\u0647",
		 "\u06F1\u0634\u0646\u0628\u0647",
		 "\u06F2\u0634\u0646\u0628\u0647",
		 "\u06F3\u0634\u0646\u0628\u0647",
		 "\u06F4\u0634\u0646\u0628\u0647",
		 "\u06F5\u0634\u0646\u0628\u0647",
		 "\u062c\u0645\u0639\u0647"
		 
			/* "\u06cc\u06a9\u0634\u0646\u0628\u0647"*/	
		 /*"\u062f\u0648\u0634\u0646\u0628\u0647",*/
/*		 "\u0633\u0647 \u0634\u0646\u0628\u0647",
		 "\u0686\u0647\u0627\u0631 \u0634\u0646\u0628\u0647",
		 "\u067e\u0646\u062c \u0634\u0646\u0628\u0647",*/
		 
		 
		 
		 
	};
	
	public void setVisible(boolean show){
		super.setVisible(show);
		if(!show)
			calendarListener.notifyOnClose();
	}

	public CalendarDialog(Component parent, Date initialDate,boolean withTime) throws IOException {
		this(null,parent,initialDate,withTime);
	}
	
	/**
	 * Create the dialog
	 * @param calendarButton 
	 * @throws IOException 
	 */
	public CalendarDialog(JDialog parentDialog,Component parent, Date initialDate,boolean withTime) throws IOException {
		super(parentDialog);
		setSize(WIDTH,withTime ? HEIGHT+50 : HEIGHT);
		setResizable(false);
		setLocationRelativeTo(parent);
		this.withTime = withTime;
		initializeCalendar(initialDate);
		int currentYear = getCurrentYear();
		yearSpinner.setModel(new SpinnerNumberModel(currentYear,currentYear-300,currentYear+300,1));


		yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner,"#"));
		yearSpinner.setValue(pc.get(Calendar.YEAR));
		month.setSelectedIndex(pc.get(Calendar.MONTH));
		yearSpinner.addChangeListener(this);
		month.addActionListener(this);
		calendar = new DPanel(new BorderLayout());
		calendar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		initHeader();
		initDays();
		calendar.add(headerPanel,BorderLayout.NORTH);
		calendar.add(daysPanel,BorderLayout.CENTER);
		if(withTime)
			calendar.add(getTimeComponent(),BorderLayout.SOUTH);
		confirmPanel = new DPanel();
		
		JButton okButton = new JButton( "\u062a\u0627\u06cc\u06cc\u062f", new ImageIcon(ImageUtil.class.getResource("ok16.gif")));
		okButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				pc.set(Calendar.DATE,new Integer(group.getSelection().getActionCommand()));
				CalendarDialog.this.setVisible(false);
				
			}});
		
		JButton cancelButton = new JButton( " \u0627\u0646\u0635\u0631\u0627\u0641", new ImageIcon(ImageUtil.class.getResource("cancel16.gif")));
		cancelButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				canceled();
				
			}
});
		
		
		confirmPanel.add(okButton);
		confirmPanel.add(cancelButton);
		confirmPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		confirmPanel.setBackgroundImage(ImageIO.read(ImageUtil.class.getResource("calendar-confirm.jpg")));
		
		
		
		
		DPanel    northPanel = new DPanel(new GridLayout(1,0));
		northPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		northPanel.add(yearSpinner);
		northPanel.add(month);
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(calendar, BorderLayout.CENTER);
		getContentPane().add(confirmPanel, BorderLayout.SOUTH);
		northPanel.setBackgroundImage(ImageIO.read(ImageUtil.class.getResource("calendar-body.jpg")));
		
		setIconImage(ImageIO.read(ImageUtil.class.getResource("calendar.png")));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				canceled();
			}
		});
		
	}
	
	

	private void canceled() {
		if(initialCalendar != null)
			pc.setTime(initialCalendar);
		else
			pc = null;
		setVisible(false);
	}
	
	private DPanel initHeader() throws IOException {
		// TODO Auto-generated method stub
		if(headerPanel == null){
			headerPanel = new DPanel(new GridLayout(0,7));
			headerPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		else
			headerPanel.removeAll();
		CalendarLabel[] dayLabel = new CalendarLabel[7];
		for(int i = 0 ; i < 7  ; i ++){
			dayLabel[i] = new CalendarLabel(dayOfWeek[i]);			
			dayLabel[i].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			dayLabel[i].setHorizontalAlignment(CalendarLabel.CENTER);
			dayLabel[i].setBackgroundImage(ImageIO.read(ImageUtil.class.getResource("calendar-label.png")));
			headerPanel.add(dayLabel[i]);
		}
		return headerPanel;
	}
	
	private DPanel  initDays(){
		if(daysPanel == null){
			daysPanel = new DPanel(new GridLayout(0,7));
			daysPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		else
			daysPanel.removeAll();
		
		PersianCalendar temp = new PersianCalendar(pc.getTime());
		
	  
	    temp.add(Calendar.MONTH, 1);
	    temp.set(Calendar.DATE, 1);
	    temp.add(Calendar.DATE, -1);
	
	    int maximumdate = temp.get(Calendar.DATE);
		int currentDay = pc.get(Calendar.DATE);
		
		temp.set(Calendar.DATE, 1);
		int firstDay = getDayofWeekNoinFarsi(temp.get(Calendar.DAY_OF_WEEK));
		
		for(int i = 1; i < firstDay;i++)
			daysPanel.add(new CalendarLabel(""));
		
		group = new ButtonGroup();
		JToggleButton[] weekButton = new JToggleButton[maximumdate];
		for(int i = 0; i < maximumdate ; i++){
			Icon ic = new ImageIcon(DayOfMonth.class.getResource("x-"+(i+1)+".png"));
			weekButton[i] = new JToggleButton();
			group.add(weekButton[i]);
			weekButton[i].setIcon(ic);
			weekButton[i].setName(""+(i+1));
			weekButton[i].setActionCommand(""+(i+1));
			weekButton[i].addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 2)
					{
						pc.set(Calendar.DATE,new Integer(((JToggleButton)e.getSource()).getName()));
						CalendarDialog.this.setVisible(false);
					}
				}
				
			
			});
			
			
			daysPanel.add(weekButton[i]);
			
		}
		
		weekButton[currentDay-1].setSelected(true);


		
		return daysPanel;
		
	}
	
	public int  getDayofWeekNoinFarsi(int dayofweekNo){
		if(dayofweekNo < 7)
			return dayofweekNo + 1;
		return 1;
	}

	public void  initializeCalendar(Date initialDate){
		initialCalendar = initialDate;
		if(pc == null)
			if(initialDate == null)
                setPersianCalendarDefaultValue();
			else
				pc = new PersianCalendar(initialDate);
		
	}
	public void  updateCalendar(){
		
	
	    pc.set(Calendar.YEAR, new Integer(yearSpinner.getValue().toString()));
	    pc.set(Calendar.MONTH, month.getSelectedIndex());
	
	    
	}
	
	int getCurrentYear(){
		
		return  pc.get(Calendar.YEAR);
	}
	
	int getCurrentMonth(){
		
		return pc.get(Calendar.MONTH);
		
		
	}
	
	int getCurrentDate(){
		
		return pc.get(Calendar.DATE);
		
		
	}

	public void stateChanged(ChangeEvent e) {

		updateCalendar();
		initDays();
		daysPanel.revalidate();
		repaint();
	}


	public void actionPerformed(ActionEvent e) {
		updateCalendar();
		initDays();
		daysPanel.revalidate();
		repaint();
		
	}
	
	public Date getCalendarDate(){
		return pc != null ?  pc.getTime() : null;
	}

	
	int getCurrentHour(){
		return pc.get(Calendar.HOUR_OF_DAY);			
	}
	
	int getCurrentMinute(){
		return pc.get(Calendar.MINUTE);			
	}
	
	public void addNotifierListener(DateDialogListener calendarListener) {
		// TODO Auto-generated method stub
		this.calendarListener = calendarListener; 
	}
	
	private void setPersianCalendarDefaultValue(){
		pc = new PersianCalendar(new Timestamp(System.currentTimeMillis()));
		pc.set(Calendar.MILLISECOND, 0);
		if(!withTime){
			pc.set(Calendar.HOUR_OF_DAY, 8);
			pc.set(Calendar.MINUTE, 0);
		}
	}
	
	public Component getTimeComponent(){
		DPanel timePanel = new DPanel();
		GridLayoutManager layoutMgr = new GridLayoutManager(timePanel);
		
		
		JSpinner hourSpinner = new JSpinner();
		hourSpinner.setModel(new SpinnerNumberModel(getCurrentHour(),0,23,1));
		hourSpinner.setEditor(new JSpinner.NumberEditor(hourSpinner,"#"));
		hourSpinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
					pc.set(Calendar.HOUR_OF_DAY, (Integer)(((JSpinner)e.getSource()).getValue()));
			}});
		
		JSpinner minuteSpinner = new JSpinner();
		minuteSpinner.setModel(new SpinnerNumberModel(getCurrentMinute(),0,59,1));
		minuteSpinner.setEditor(new JSpinner.NumberEditor(minuteSpinner,"#"));
		minuteSpinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
					pc.set(Calendar.MINUTE, (Integer)(((JSpinner)e.getSource()).getValue()));
			}});
		
		layoutMgr.getConstriant().insets = new Insets(10,5,0,5);
		layoutMgr.add(new DLabel("\u062f\u0642\u06cc\u0642\u0647"));
		layoutMgr.add(new DLabel(" "));
		layoutMgr.add(new DLabel("\u0633\u0627\u0639\u062a"));
		layoutMgr.gotoNewLine();		
		
		layoutMgr.getConstriant().insets = new Insets(0,0,0,0);
		layoutMgr.add(minuteSpinner);
		layoutMgr.getConstriant().insets = new Insets(5,5,5,5);
		layoutMgr.add(new DLabel(":"));
		layoutMgr.getConstriant().insets = new Insets(0,0,0,0);
		layoutMgr.add(hourSpinner);
		
		return timePanel;
		
		
	}


}
