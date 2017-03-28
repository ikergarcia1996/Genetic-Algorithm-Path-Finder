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

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import variables.Variables;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;



public class App_control_interface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner itfast;
	private JRadioButton rdbtnYesob;
	private JRadioButton rdbtnNoob;
	private JRadioButton rdbtnYes_acel;
	private JRadioButton rdbtnNo_acel;

	/**
	 * Launch the application.
	 */
	public static void main(Variables var,int x, int y) {
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
					App_control_interface frame = new App_control_interface(var,x,y);
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
	public App_control_interface(Variables var, int x, int y) {
		ImageIcon img = new ImageIcon("icon.PNG");
		this.setIconImage(img.getImage());
		this.setTitle("Control panel");
		this.setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFastMode = new JLabel("Do");
		lblFastMode.setBounds(12, 36, 24, 16);
		contentPane.add(lblFastMode);
		
		itfast = new JSpinner();
		itfast.setModel(new SpinnerNumberModel(new Integer(1000), new Integer(1), null, new Integer(100)));
		itfast.setBounds(36, 33, 88, 22);
		contentPane.add(itfast);
		
		JLabel lblIterations = new JLabel("Iterations ");
		lblIterations.setBounds(136, 36, 71, 16);
		contentPane.add(lblIterations);
		
		JLabel lblFastMode_1 = new JLabel("FAST MODE");
		lblFastMode_1.setBounds(12, 13, 88, 16);
		contentPane.add(lblFastMode_1);
		
		JButton btnfastmodestart = new JButton("START");
		btnfastmodestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				var.loops_fastMode=(int) itfast.getValue();
				var.fastmode=true;
				
			}
		});
		btnfastmodestart.setBounds(199, 13, 82, 44);
		contentPane.add(btnfastmodestart);
		
		JLabel lblFastModeUntil = new JLabel("FAST MODE UNTIL SOLVED");
		lblFastModeUntil.setBounds(393, 13, 166, 16);
		contentPane.add(lblFastModeUntil);
		
		JButton btnStartus = new JButton("START");
		btnStartus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.fast_ultil_solved=true;
			}
		});
		btnStartus.setBounds(382, 32, 82, 25);
		contentPane.add(btnStartus);
		
		JButton btnStopfastmode = new JButton("STOP");
		btnStopfastmode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				var.fastmode=false;
			}
		});
		btnStopfastmode.setBounds(286, 13, 84, 44);
		contentPane.add(btnStopfastmode);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 65, 560, -1);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(12, 65, 560, 2);
		contentPane.add(separator_1);
		
		JLabel lblMap = new JLabel("MAPMAKING:");
		lblMap.setBounds(12, 68, 82, 16);
		contentPane.add(lblMap);
		
		JButton btnMoveOb = new JButton("Move objective");
		btnMoveOb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.mode=1;
			}
		});
		btnMoveOb.setBounds(12, 94, 140, 25);
		contentPane.add(btnMoveOb);
		
		JButton btnCreateWalls = new JButton("Create walls");
		btnCreateWalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.mode=3;
			}
		});
		btnCreateWalls.setBounds(221, 94, 140, 25);
		contentPane.add(btnCreateWalls);
		
		JButton btnMoveSpawn = new JButton("Move spawn");
		btnMoveSpawn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.mode=2;
			}
		});
		btnMoveSpawn.setBounds(432, 94, 140, 25);
		contentPane.add(btnMoveSpawn);
		
		JLabel lblFrammerate = new JLabel("Frammerate:");
		lblFrammerate.setBounds(12, 147, 88, 16);
		contentPane.add(lblFrammerate);
		
		JLabel lblMaxSpeed = new JLabel("Max. speed:");
		lblMaxSpeed.setBounds(12, 168, 88, 16);
		contentPane.add(lblMaxSpeed);
		
		JSpinner spinner_fps = new JSpinner();
		spinner_fps.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				var.frammerate=(int) spinner_fps.getValue();
			}
		});
		spinner_fps.setModel(new SpinnerNumberModel(new Integer(200), new Integer(1), null, new Integer(5)));
		spinner_fps.setBounds(96, 147, 106, 16);
		contentPane.add(spinner_fps);
		
		JSpinner spinner_speed = new JSpinner();
		spinner_speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.max_speed=(float) spinner_speed.getValue();
			}
		});
		spinner_speed.setModel(new SpinnerNumberModel(new Float(5), new Float(1), new Float(15), new Float(1)));
		spinner_speed.setBounds(96, 168, 106, 16);
		contentPane.add(spinner_speed);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(12, 132, 560, 2);
		contentPane.add(separator_2);
		
		JLabel lblShowOnlyThe = new JLabel("Show only the best:");
		lblShowOnlyThe.setBounds(254, 147, 134, 16);
		contentPane.add(lblShowOnlyThe);
		
		rdbtnYesob = new JRadioButton("Yes");
		rdbtnYesob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNoob.setSelected(false);
				var.onlybest=1;
			}
		});
		rdbtnYesob.setBounds(401, 143, 53, 25);
		contentPane.add(rdbtnYesob);
		
		rdbtnNoob = new JRadioButton("No");
		rdbtnNoob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnYesob.setSelected(false);
				var.onlybest=0;
			}
		});
		rdbtnNoob.setSelected(true);
		rdbtnNoob.setBounds(470, 143, 53, 25);
		contentPane.add(rdbtnNoob);
		
		JLabel lblMaxAce = new JLabel("Max. accel:");
		lblMaxAce.setBounds(12, 191, 71, 16);
		contentPane.add(lblMaxAce);
		
		JSpinner spinner_acel = new JSpinner();
		spinner_acel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.max_acel=(float) spinner_acel.getValue();
			}
		});
		spinner_acel.setModel(new SpinnerNumberModel(new Float(2), new Float(1), new Float(5), new Float(1)));
		spinner_acel.setBounds(96, 188, 106, 16);
		contentPane.add(spinner_acel);
		
		JLabel lblSize = new JLabel("SIZE *This does not affect collisions");
		lblSize.setBounds(296, 176, 214, 16);
		contentPane.add(lblSize);
		
		JSlider slider = new JSlider();
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setMinimum(5);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.factor_tamaño=((float)slider.getValue()/10);
			}
		});
		slider.setValue(10);
		slider.setMaximum(30);
		slider.setBounds(291, 189, 200, 26);
		contentPane.add(slider);
		
		JLabel lblX = new JLabel("x3");
		lblX.setBounds(503, 185, 56, 30);
		contentPane.add(lblX);
		
		JLabel lblX_1 = new JLabel("x0.5");
		lblX_1.setBounds(254, 184, 31, 30);
		contentPane.add(lblX_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(12, 242, 560, 2);
		contentPane.add(separator_3);
		
		JButton btnStopus = new JButton("STOP");
		btnStopus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				var.fast_ultil_solved=false;
			}
		});
		btnStopus.setBounds(486, 32, 73, 25);
		contentPane.add(btnStopus);
		
		JLabel lblPopulation = new JLabel("Population: *");
		lblPopulation.setBounds(12, 257, 88, 16);
		contentPane.add(lblPopulation);
		
		JSpinner spinner_pop = new JSpinner();
		spinner_pop.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.pop=(int) spinner_pop.getValue();
			}
		});
		spinner_pop.setModel(new SpinnerNumberModel(new Integer(25), new Integer(1), null, new Integer(5)));
		spinner_pop.setBounds(120, 254, 82, 22);
		contentPane.add(spinner_pop);
		
		JLabel lblMuationRate = new JLabel("Muation Rate %:");
		lblMuationRate.setBounds(12, 286, 100, 16);
		contentPane.add(lblMuationRate);
		
		JSpinner spinner_mutr = new JSpinner();
		spinner_mutr.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.mutation_rate= ((float)spinner_mutr.getValue() / 100);
			}
		});
		spinner_mutr.setModel(new SpinnerNumberModel(new Float(1), new Float(0), new Float(100), new Float(1)));
		spinner_mutr.setBounds(120, 283, 82, 22);
		contentPane.add(spinner_mutr);
		
		JLabel lblLifeTime = new JLabel("Life time: *");
		lblLifeTime.setBounds(12, 343, 71, 16);
		contentPane.add(lblLifeTime);
		
		JSpinner spinner_lift = new JSpinner();
		spinner_lift.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.max_lifetime=(int) spinner_lift.getValue();
			}
		});
		spinner_lift.setModel(new SpinnerNumberModel(new Integer(900), new Integer(10), null, new Integer(5)));
		spinner_lift.setBounds(120, 340, 82, 22);
		contentPane.add(spinner_lift);
		
		JLabel lblMutationsFromBest = new JLabel("Mutations from the best in the next generation:");
		lblMutationsFromBest.setBounds(221, 257, 302, 16);
		contentPane.add(lblMutationsFromBest);
		
		JLabel lblNoteThisHelps = new JLabel("Note: this may help to optimize the solution and sometimes");
		lblNoteThisHelps.setBounds(229, 273, 343, 16);
		contentPane.add(lblNoteThisHelps);
		
		JLabel lblToFindIt = new JLabel("to find it. Must be lower or equal to population-1");
		lblToFindIt.setBounds(231, 286, 317, 16);
		contentPane.add(lblToFindIt);
		
		JLabel lblANumberNear = new JLabel("A number near the population isn\u00B4t recommended");
		lblANumberNear.setBounds(230, 302, 302, 16);
		contentPane.add(lblANumberNear);
		
		JSpinner spinner_bmut = new JSpinner();
		spinner_bmut.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				var.best_mutations=(int) spinner_bmut.getValue();
			}
		});
		spinner_bmut.setModel(new SpinnerNumberModel(new Integer(2), new Integer(0), null, new Integer(1)));
		spinner_bmut.setBounds(302, 340, 140, 22);
		contentPane.add(spinner_bmut);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(12, 407, 560, 2);
		contentPane.add(separator_4);
		
		JLabel lblCalculeAcceleration = new JLabel("CALCULATE ACCELERATION:");
		lblCalculeAcceleration.setBounds(50, 433, 176, 16);
		contentPane.add(lblCalculeAcceleration);
		
		rdbtnYes_acel = new JRadioButton("YES");
		rdbtnYes_acel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNo_acel.setSelected(false);
				var.calcule_acel=true;
			}
		});
		rdbtnYes_acel.setSelected(true);
		rdbtnYes_acel.setBounds(227, 418, 64, 25);
		contentPane.add(rdbtnYes_acel);
		
		JLabel lblSpeedSpeed = new JLabel("Speed = Speed + acceleration");
		lblSpeedSpeed.setBounds(291, 422, 200, 16);
		contentPane.add(lblSpeedSpeed);
		
		rdbtnNo_acel = new JRadioButton("NO");
		rdbtnNo_acel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnYes_acel.setSelected(false);
				var.calcule_acel=false;
			}
		});
		rdbtnNo_acel.setBounds(227, 444, 64, 25);
		contentPane.add(rdbtnNo_acel);
		
		JLabel lblSpeedAcceleration = new JLabel("Speed = acceleration");
		lblSpeedAcceleration.setBounds(291, 448, 200, 16);
		contentPane.add(lblSpeedAcceleration);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(12, 474, 560, 2);
		contentPane.add(separator_5);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.reset=true;
			}
		});
		btnReset.setBounds(12, 489, 119, 84);
		contentPane.add(btnReset);
		
		JButton btnResetOnlyWalls = new JButton("RESET WALLS");
		btnResetOnlyWalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				var.resetWalls=true;
			}
		});
		btnResetOnlyWalls.setBounds(149, 489, 119, 84);
		contentPane.add(btnResetOnlyWalls);
		
		JLabel lblAbout = new JLabel("About:");
		lblAbout.setBounds(314, 489, 56, 16);
		contentPane.add(lblAbout);
		
		JLabel lblByIkerGarca = new JLabel("By: Iker Garc\u00EDa Ferrero");
		lblByIkerGarca.setBounds(314, 518, 140, 16);
		contentPane.add(lblByIkerGarca);
		
		JLabel lblHardwgmailcom = new JLabel("hardw360@gmail.com");
		lblHardwgmailcom.setBounds(314, 547, 128, 16);
		contentPane.add(lblHardwgmailcom);
		
		JButton btnMore = new JButton("MORE");
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About.main(null);
			}
		});
		btnMore.setBounds(462, 489, 97, 74);
		contentPane.add(btnMore);
		
		JLabel lblClicOnThe = new JLabel("Clic on a point to move the objective or the spawn. Clic on 2 points to create a wall");
		lblClicOnThe.setBounds(95, 68, 477, 16);
		contentPane.add(lblClicOnThe);
		
		JLabel lblwillChangeIn = new JLabel("*Will change in the next generation");
		lblwillChangeIn.setBounds(12, 378, 214, 16);
		contentPane.add(lblwillChangeIn);
		
		JLabel lblNoteTheScr = new JLabel("Note: The map window is resizeable.");
		lblNoteTheScr.setBounds(12, 220, 226, 16);
		contentPane.add(lblNoteTheScr);
		
		JSpinner spinner_crossr = new JSpinner();
		spinner_crossr.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				var.crossover_rate=(float) spinner_crossr.getValue()/100;
			}
		});
		spinner_crossr.setModel(new SpinnerNumberModel(new Float(90), new Float(0), new Float(100), new Float(1)));
		spinner_crossr.setBounds(120, 311, 82, 22);
		contentPane.add(spinner_crossr);
		
		JLabel lblCrossoverRate = new JLabel("Crossover rate %:");
		lblCrossoverRate.setBounds(12, 314, 112, 16);
		contentPane.add(lblCrossoverRate);
	}
}
