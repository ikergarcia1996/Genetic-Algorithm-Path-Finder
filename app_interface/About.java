/*
----==============----
IKER GARCÍA FERRERO
    03/01/2017
 hardw360@gmail.com   
----==============----

«(C)» Copyright 2017 Iker García "Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)" 

*/
package app_interface;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.SystemColor;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
	            // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException|ClassNotFoundException|InstantiationException|IllegalAccessException e) {
	    	System.out.println("Fallo al cambiar el look and feel, the toca usar el feo de java :S");
	    }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public About() {
		ImageIcon img = new ImageIcon("icon.PNG");
		this.setIconImage(img.getImage());
		this.setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 705, 335);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionBorder);
		contentPane.setToolTipText("erh");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnByIkerGarca = new JTextPane();
		txtpnByIkerGarca.setBackground(SystemColor.menu);
		txtpnByIkerGarca.setEditable(false);
		txtpnByIkerGarca.setText("By: Iker Garc\u00EDa Ferrero\r\nDate: 03/01/2017\r\n\r\n--Contact--\r\nMail: hardw360@gmail.com \r\n\r\nThe source code can be found here:\r\nhttps://github.com/ikergarcia1996/Simple-AI_Ikerg-app_INTELLIGENT_POINTS\r\n\r\nA demostration and explanation can be found here (Spanish):\r\nhttps://www.youtube.com/hardware360grados\r\n\r\nThis program uses Processing 3.2.3\r\n\r\nCopyright 2017 Iker Garc\u00EDa \"Attribution-NonCommercial-ShareAlike 4.0 International (CC BY-NC-SA 4.0)\" ");
		txtpnByIkerGarca.setToolTipText("");
		txtpnByIkerGarca.setBounds(12, 13, 664, 262);
		contentPane.add(txtpnByIkerGarca);
	}
}
