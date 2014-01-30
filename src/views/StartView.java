package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import models.ApplicationTableModel;
import models.JobApplication;

import utils.Utils;

/**
 * a view class that dictates the frames inside the application mainframe
 * @author nilsma
 *
 */
@SuppressWarnings({"serial"})
public class StartView extends JPanel {
	private int width;
	private int height;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private String[] columnNames = {"Company", "Position", "Applied", "Duedate", "Followup", "Status", "Notes"};
	private JButton regApp;
	private JButton removeApp;
	private JButton appDet;
	private JButton quitBtn;
	private JCheckBox activeBox;
	private JCheckBox inactiveBox;
	private JComboBox sortCategories;
	private List<JobApplication> applications;
	private ApplicationTableModel appTableModel;
	
	/**
	 * Constructor calls a new instance of the StartView class, and
	 * also spawns the application panel
	 */
	public StartView(List<JobApplication> applications) {
		this.applications = applications;
		appTableModel = new ApplicationTableModel(null);
		setupStartView(applications);
	}
	
	/**
	 * A method to initialize the startupView
	 */
	public void setupStartView(List<JobApplication> applications) {
		//sets the variables height and width depending on the current screen dimensions through
		//the Utils class, and subtracts pixels specifically for this view with 
		//two parameters (adjustHeight, adjustWidth)
		setScreenDimensions(Utils.getApplicationFrameWidth(), Utils.getApplicationFrameHeight());
		
		//new layout for main frame in this view
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(boxLayout);
		
		// creating, and setting variables for, the left-hand and right-hand containers in the main frame in this view
		// leftContainer
		JPanel leftContainer = new JPanel();
		leftContainer.setPreferredSize(new Dimension((Utils.getApplicationFrameWidth())/2, Utils.getApplicationFrameHeight()));
		
		// rightContainer
		JPanel rightContainer = new JPanel();
		rightContainer.setLayout(new FlowLayout());
		rightContainer.setPreferredSize(new Dimension(Utils.getApplicationFrameWidth()/4, Utils.getApplicationFrameHeight()));
		
		//panel for holding check-buttons on top of leftContainer
		JPanel checkHolder = new JPanel();
		checkHolder.setLayout(new BoxLayout(checkHolder, BoxLayout.X_AXIS));
		JPanel checkButtons = new JPanel();
		checkButtons.setLayout(new BoxLayout(checkButtons, BoxLayout.Y_AXIS));
		JLabel chkPretext = new JLabel("Showing");
		JLabel chkPosttext = new JLabel("applications");
		activeBox = new JCheckBox("Active", true);
		inactiveBox = new JCheckBox("Inactive", true);
		checkHolder.add(chkPretext);
		checkHolder.add(Box.createHorizontalStrut(25));
		checkButtons.add(activeBox);
		checkButtons.add(inactiveBox);
		checkHolder.add(checkButtons);
		checkHolder.add(Box.createHorizontalStrut(20));
		checkHolder.add(chkPosttext);
		leftContainer.add(checkHolder);
		
		//panel for holding the sorting buttons
		//to be added to the checkHolder panel
		JPanel sortHolder = new JPanel();
		sortHolder.setLayout(new BoxLayout(sortHolder, BoxLayout.Y_AXIS));
		JLabel sortHeader = new JLabel("Sort by:");
		sortHolder.add(sortHeader);
		sortHolder.add(Box.createVerticalStrut(10));
		ArrayList<String> categories = new ArrayList<String>();
		//uses length - 1 to stop the user from sorting on the category "Notes" in the applications
		for(int i = 0; i < columnNames.length - 1; i++) {
			categories.add(columnNames[i]);
		}
		sortCategories = new JComboBox(categories.toArray());
		sortCategories.setSelectedIndex(0);
		sortHolder.add(sortCategories);
		checkHolder.add(Box.createHorizontalStrut(70));
		checkHolder.add(sortHolder);
		
		//creating and setting bounds for table within scrollPane
		//also adds a scrollpane for holding the table, and adding both to leftContainer
		//also calls adjustColumnWidths to set the width of the columns accordingly
		if(!(applications == null)) {
			appTableModel = new ApplicationTableModel(applications);
			table = new JTable(appTableModel);
			adjustColumnWidths();
			adjustColumnAlignment();
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension((width/2), 500));
			scrollPane.setViewportView(table);
			leftContainer.add(scrollPane);
		} else {
			table = new JTable(appTableModel);
			adjustColumnWidths();
			adjustColumnAlignment();
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension((width/2), 500));
			scrollPane.setViewportView(table);
			leftContainer.add(scrollPane);
		}
		
		//new panel for holding buttons in right-hand container
		JPanel rcButtons = new JPanel();
		rcButtons.setLayout(new BoxLayout(rcButtons, BoxLayout.Y_AXIS));
		rightContainer.add(rcButtons);
		
		//top spacing for button-holding panel
		rcButtons.add(Box.createVerticalStrut(90));
		
		//new button for registering new application
		regApp = new JButton("Register new application");
		regApp.setAlignmentX(Component.CENTER_ALIGNMENT);
		rcButtons.add(regApp);
		
		//spacing between register-application and register-company buttons
		rcButtons.add(Box.createVerticalStrut(40));
		
		//new button for registering new company
		removeApp = new JButton("Remove row");
		removeApp.setAlignmentX(Component.CENTER_ALIGNMENT);
		rcButtons.add(removeApp);
		
		//spacing between register-company and application-details buttons
		rcButtons.add(Box.createVerticalStrut(40));
		
		//new button for application details
		appDet = new JButton("Application details");
		appDet.setAlignmentX(Component.CENTER_ALIGNMENT);
		rcButtons.add(appDet);
		
		//spacing between application-details button and infoPanel JPanel
		rcButtons.add(Box.createVerticalStrut(40));
		
		//creating and adding quit button
		quitBtn = new JButton("Quit");
		quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		rcButtons.add(quitBtn);
		
		//new panel for info-panel in right-hand container
		//with boxlayout y axis for holding additionally nested boxlayouts of x axis
		//in the form of [label][label]
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		//spacing between top of infoPanel and infoPanelEntry1
		infoPanel.add(Box.createVerticalStrut(20));
		
		//new panel with boxlayout x axis in rightContainer 
		//for holding the first (from top) entry in infoPanel
		//holds two elements in the form of [label][label]
		JPanel infoPanelEntry1 = new JPanel();
		infoPanelEntry1.setLayout(new BoxLayout(infoPanelEntry1, BoxLayout.X_AXIS));
		JLabel noOfAppsL = new JLabel("Number of applications: ");
		noOfAppsL.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanelEntry1.add(noOfAppsL);
		JLabel noOfAppsTF = new JLabel("5");
		noOfAppsTF.setAlignmentX(Component.RIGHT_ALIGNMENT);
		infoPanelEntry1.add(noOfAppsTF);
		infoPanel.add(infoPanelEntry1);
		
		//spacing between top of infoPanelEntry1 and infoPanelEntry2
		infoPanel.add(Box.createVerticalStrut(20));
		
		//new panel with boxlayout x axis in rightContainer 
		//for holding the second (from top) entry in infoPanel
		//holds two elements in the form of [label][label]
		JPanel infoPanelEntry2 = new JPanel();
		infoPanelEntry2.setLayout(new BoxLayout(infoPanelEntry2, BoxLayout.X_AXIS));
		JLabel noOfFolUpsL = new JLabel("Number of follow-ups: ");
		noOfFolUpsL.setAlignmentX(Component.LEFT_ALIGNMENT);
		infoPanelEntry2.add(noOfFolUpsL);
		JLabel noOfFolUpsTF = new JLabel("20");
		noOfFolUpsTF.setAlignmentX(Component.RIGHT_ALIGNMENT);
		infoPanelEntry2.add(noOfFolUpsTF);
		infoPanel.add(infoPanelEntry2);
		
		//new panel for quit buttons at the bottom of rightContainer
		JPanel rcBottom = new JPanel();
		rcBottom.setLayout(new BoxLayout(rcBottom, BoxLayout.Y_AXIS));
		
		//set component colors
		setBackground(new Color(0xcccccc));
		leftContainer.setBackground(new Color(0xcccccc));
		rightContainer.setBackground(new Color(0xcccccc));
		rcButtons.setBackground(new Color(0xcccccc));
		rcBottom.setBackground(new Color(0xcccccc));
		infoPanel.setBackground(new Color(0xcccccc));
		infoPanelEntry1.setBackground(new Color(0xcccccc));
		infoPanelEntry2.setBackground(new Color(0xcccccc));
		checkHolder.setBackground(new Color(0xcccccc));
		checkButtons.setBackground(new Color(0xcccccc));
		sortHolder.setBackground(new Color(0xcccccc));
		activeBox.setBackground(new Color(0xcccccc));
		inactiveBox.setBackground(new Color(0xcccccc));
		
		// adding mainContainers to view boxlayout
		this.add(leftContainer);
		this.add(rightContainer);
			
	}
	
	/**
	 * a method to adjust the column widths
	 */
	public void adjustColumnWidths() {
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(105);
	}
	
	/**
	 * a method to adjust the column alignment
	 */
	public void adjustColumnAlignment() {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	}
	
	/**
	 * a method for setting the screen dimensions to the variables width and height through
	 * the Utils class, and adds or subtracts additional pixels specific for this view
	 * @param int adjHeight an int of pixels to subtract from the screensize height
	 * @param int adjWidth an int of pixels to subtract from the screensize width
	 */
	public void setScreenDimensions(int adjHeight, int adjWidth) {
		width = Utils.getApplicationFrameWidth();
		height = Utils.getApplicationFrameHeight();
	}

	/**
	 * @return the someButton
	 */
	public JButton getRegAppBtn() {
		return regApp;
	}

	/**
	 * @param regAppBtn the someButton to set
	 */
	public void setRegAppBtn(JButton regAppBtn) {
		this.regApp = regAppBtn;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	/**
	 * @return the columnNames
	 */
	public String[] getColumnNames() {
		return columnNames;
	}

	/**
	 * @param columnNames the columnNames to set
	 */
	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * @return the regApp
	 */
	public JButton getRegApp() {
		return regApp;
	}

	/**
	 * @param regApp the regApp to set
	 */
	public void setRegApp(JButton regApp) {
		this.regApp = regApp;
	}

	/**
	 * @return the removeApp
	 */
	public JButton getRemoveApp() {
		return removeApp;
	}

	/**
	 * @param regComp the regComp to set
	 */
	public void setRemoveApp(JButton removeApp) {
		this.removeApp = removeApp;
	}

	/**
	 * @return the appDet
	 */
	public JButton getAppDet() {
		return appDet;
	}

	/**
	 * @param appDet the appDet to set
	 */
	public void setAppDet(JButton appDet) {
		this.appDet = appDet;
	}

	/**
	 * @return the quitBtn
	 */
	public JButton getQuitBtn() {
		return quitBtn;
	}

	/**
	 * @param quitBtn the quitBtn to set
	 */
	public void setQuitBtn(JButton quitBtn) {
		this.quitBtn = quitBtn;
	}

	/**
	 * @return the activeBox
	 */
	public JCheckBox getActiveBox() {
		return activeBox;
	}

	/**
	 * @param activeBox the activeBox to set
	 */
	public void setActiveBox(JCheckBox activeBox) {
		this.activeBox = activeBox;
	}

	/**
	 * @return the inactiveBox
	 */
	public JCheckBox getInactiveBox() {
		return inactiveBox;
	}

	/**
	 * @param inactiveBox the inactiveBox to set
	 */
	public void setInactiveBox(JCheckBox inactiveBox) {
		this.inactiveBox = inactiveBox;
	}

	/**
	 * @return the sortCategories
	 */
	public JComboBox getSortCategories() {
		return sortCategories;
	}

	/**
	 * @param sortCategories the sortCategories to set
	 */
	public void setSortCategories(JComboBox sortCategories) {
		this.sortCategories = sortCategories;
	}

	/**
	 * @return the applications
	 */
	public List<JobApplication> getApplications() {
		return applications;
	}

	/**
	 * @param applications the applications to set
	 */
	public void setApplications(List<JobApplication> applications) {
		this.applications = applications;
	}

	/**
	 * @return the appTableModel
	 */
	public ApplicationTableModel getAppTableModel() {
		return appTableModel;
	}

	/**
	 * @param appTableModel the appTableModel to set
	 */
	public void setAppTableModel(ApplicationTableModel appTableModel) {
		this.appTableModel = appTableModel;
	}
	
}
