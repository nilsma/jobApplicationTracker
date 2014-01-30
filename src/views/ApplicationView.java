package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import utils.Utils;

/**
 * a view class that dictates the application mainframe
 * @author nilsma
 *
 */
@SuppressWarnings("serial")
public class ApplicationView extends JFrame {
	private int applicationFrameHeight;
	private int applicationFrameWidth;
	private String title = "Job Application Tracker - v2013.05.05";
	private JMenuItem closeapplication;
	private JMenuItem changeUser;
	
	/**
	 * Constructor that runs the frame method, which
	 * spawns the application view and sets its settings
	 */
	public ApplicationView(Component view) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		applicationFrameWidth = Utils.getApplicationFrameWidth();
		applicationFrameHeight = Utils.getApplicationFrameHeight();
		frame(view, applicationFrameHeight, applicationFrameWidth);
		
	}
	
	/**
	 * a method that spawns the application mainframe,
	 * the outer boundaries of the application view
	 */
	public void frame(Component view, int height, int width) {
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		setSize(width,height);
		setTitle(title);
		contentPane.add(view);
		contentPane.setBackground(new Color(0xcccccc));
		
		//create and set menubar
		JMenuBar menubar = new JMenuBar();
		this.setJMenuBar(menubar);
		
		//menubar entries
		JMenu filemenu = new JMenu("File");
		menubar.add(filemenu);
		
		//file itementries
		closeapplication = new JMenuItem("Exit");
		changeUser = new JMenuItem("Change User");
		filemenu.add(changeUser);
		filemenu.addSeparator();
		filemenu.add(closeapplication);
	}
	
	/**
	 * a method to set the screensize field
	 */
	public void setScreenSize() {
		Utils.getScreenSize();
	}

	/**
	 * @return the closeapplication
	 */
	public JMenuItem getCloseapplication() {
		return closeapplication;
	}

	/**
	 * @param closeapplication the closeapplication to set
	 */
	public void setCloseapplication(JMenuItem closeapplication) {
		this.closeapplication = closeapplication;
	}

	public void updateTitle(String addendum) {
		title = "";
		title += "Job Application Tracker - v2013.05.05";
		title += " : " + addendum;
		this.setTitle(title);
	}

	/**
	 * @return the applicationFrameHeight
	 */
	public int getApplicationFrameHeight() {
		return applicationFrameHeight;
	}

	/**
	 * @return the applicationFrameWidth
	 */
	public int getApplicationFrameWidth() {
		return applicationFrameWidth;
	}

	/**
	 * @return the changeUser
	 */
	public JMenuItem getChangeUser() {
		return changeUser;
	}

	/**
	 * @param changeUser the changeUser to set
	 */
	public void setChangeUser(JMenuItem changeUser) {
		this.changeUser = changeUser;
	}
	
}
