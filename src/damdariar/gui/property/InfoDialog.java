package damdariar.gui.property;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InfoDialog extends JDialog{
	JTextField  key;
	InfoDialog(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	

	public void askInfo(){
		setLayout(new BorderLayout());
		applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		
		JPanel keyPanel = new JPanel();
		keyPanel.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		JLabel label2 = new JLabel("\u06a9\u0644\u06cc\u062f \u0628\u0631\u0646\u0627\u0645\u0647 \u062f\u0627\u062f\u0647 \u0634\u062f\u0647 \u0631\u0627 \u0648\u0627\u0631\u062f \u0646\u0645\u0627\u064a\u064a\u062f");
		keyPanel.add(label2,BorderLayout.NORTH);
		key = new JTextField(35);
		keyPanel.add(key,BorderLayout.CENTER);
		add(keyPanel,BorderLayout.CENTER);
		JButton can = GUIUtil.getCancelButton();
		can.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}});
		JButton ok = GUIUtil.getOKButton();
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					GUIProperty.setPassword(key.getText());
					setVisible(false);
					dispose();
				
			}});
		JPanel confirmPanel = new JPanel();
		confirmPanel.add(can);
		confirmPanel.add(ok);
		add(confirmPanel,BorderLayout.SOUTH);
		setSize(200,150);
		GUIUtil.showCenterScreen(this);
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
	}

}
