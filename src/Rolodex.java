//
// Name: Ma, Jonathan
// Project: #3
// Due: 11/20/15
// Course: CS-245-01-f15
//
// Description:
// Tabbbed pane with tabs displaying contact info
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
public class Rolodex implements ActionListener{
	JFrame fr;
	JTabbedPane pane;
	Rolodex(String str) throws Exception{
		fr = new JFrame("Rolodex");
		fr.setIconImage(new ImageIcon("Rolodex.png").getImage());
		fr.setSize(500,300);
		fr.setLocationRelativeTo (null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar jbar = new JMenuBar();
		JMenu file = new JMenu("File");
		file.setMnemonic('F');
		JMenuItem open = createMenuItem("Open...", 'o');
		open.setEnabled(false);
		JMenuItem exit = createMenuItem("Exit...", 'x');
		file.add(open);
		file.addSeparator();
		file.add(exit);
		
		JMenu tabs = new JMenu("Tabs");
		tabs.setMnemonic('t');
		JMenu placement = new JMenu("Placement");
		placement.setMnemonic('P');
		JMenuItem top = createMenuItem("Top",'t');
		JMenuItem right = createMenuItem("Right", 'r');
		JMenuItem bottom = createMenuItem("Bottom", 'b');
		JMenuItem left = createMenuItem("Left", 'l');
		placement.add(top);
		placement.add(right);
		placement.add(bottom);
		placement.add(left);
		JMenu layout = new JMenu("Layout");
		layout.setMnemonic('L');
		JMenuItem scroll = createMenuItem("Scroll", 's');
		JMenuItem wrap = createMenuItem("Wrap", 'w');
		layout.add(scroll);
		layout.add(wrap);
		JMenuItem defaults = createMenuItem("Default", 'd');
		defaults.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		tabs.add(placement);
		tabs.add(layout);
		tabs.addSeparator();
		tabs.add(defaults);
		
		JMenu help = new JMenu("Help");
		help.setMnemonic('h');
		JMenuItem about = createMenuItem("About", 'a');
		help.add(about);
		
		pane = new JTabbedPane();
		BufferedReader br = new BufferedReader(new FileReader("contacts.txt"));
		String line;
		JPanel panel = new JPanel(new FlowLayout());;
		JPanel panel2 = new JPanel(new GridLayout(2,1));;
		JLabel nameLbl= new JLabel("Name: ",SwingConstants.RIGHT);
		JLabel emailLbl = new JLabel("Email: ",SwingConstants.RIGHT);
		JTextField nametxt = new JTextField("Ma, Jonathan");
		JTextField emailtxt = new JTextField("ma1@cpp.edu");
		panel.add(new JLabel(new ImageIcon("nopic.jpg")));
		panel2.add(nameLbl);
		panel2.add(nametxt);
		panel2.add(emailLbl);
	    panel2.add(emailtxt);
	    panel.add(panel2);
		pane.addTab("Ma, Jonathan", panel);
		ArrayList list = new ArrayList<String>();
		while((line = br.readLine()) != null){
			list.add(line);
		}
		Collections.sort(list);
		for(Object i:list){
			String [] contactInfo = ((String) i).split(":");
			panel = new JPanel(new FlowLayout());
			panel2 = new JPanel(new GridLayout(2,1));
			nametxt = new JTextField(contactInfo[0]);
			emailtxt = new JTextField(contactInfo[1]);
			nameLbl = new JLabel("Name: ", SwingConstants.RIGHT);
			emailLbl = new JLabel("Email: ", SwingConstants.RIGHT);
			nameLbl.setDisplayedMnemonic('n');
			emailLbl.setDisplayedMnemonic('e');
			nameLbl.setLabelFor(nametxt);
			emailLbl.setLabelFor(emailtxt);
			File picture = new File(contactInfo[2]);
			if(!picture.exists()){
				panel.add(new JLabel(new ImageIcon("nopic.jpg")));
			}
			else{
				panel.add(new JLabel(new ImageIcon(contactInfo[2])));
			}
			panel2.add(nameLbl);
			panel2.add(nametxt);
			panel2.add(emailLbl);
		    panel2.add(emailtxt);
		    panel.add(panel2);
		    pane.addTab(contactInfo[0],panel);
		}
		
		jbar.add(file);
		jbar.add(tabs);
		jbar.add(help);
		fr.setJMenuBar(jbar);
		fr.add(pane);
		fr.setVisible(true);
	}
	private JMenuItem createMenuItem(String name, char mnemonic) {
		JMenuItem item = new JMenuItem(name);
		item.setMnemonic(mnemonic);
		item.addActionListener(this);
		return item;
	}
	public void actionPerformed(ActionEvent ae) {
		String str = ae.getActionCommand();
		switch(str){
		   case "Exit...":
		      System.exit(0);
			  break;
		   case "Top":
		      pane.setTabPlacement(JTabbedPane.TOP);
			  break;
		   case "Right":
		      pane.setTabPlacement(JTabbedPane.RIGHT);
			  break;
		   case "Bottom":
		      pane.setTabPlacement(JTabbedPane.BOTTOM);
			  break;
		   case "Left":
			  pane.setTabPlacement(JTabbedPane.LEFT);
		      break;
		   case "Scroll":
			  pane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			  break;
		   case "Wrap":
			  pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			  break;
		   case "Default":
			  pane.setTabPlacement(JTabbedPane.TOP);
		      pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		      break;
		   case "About":
			   JOptionPane.showMessageDialog(fr, "<html><i>App</i> Rolodex version 0.1" + "\n" + 
					   	                         "<html><i>Icon</i> Copyright (c) 2015 <i>J. Ma</i>");
		}
	}
	public static void main(final String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
					try {
						new Rolodex("contacts.txt");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}
}