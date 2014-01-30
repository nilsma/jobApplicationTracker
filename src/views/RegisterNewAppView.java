package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

import utils.Utils;

@SuppressWarnings("serial")
public class RegisterNewAppView extends JFrame {
	private JFrame mainFrame;
	private JPanel topContainer;
	private JPanel middleContainer;
	private JPanel bottomContainer;
	private JPanel topLabels;
	private JPanel middleLabels;
	private JPanel bottomLabels;
	private JPanel topFields;
	private JPanel middleFields;
	private JPanel bottomFields;
	private JLabel id;
	private JLabel company;
	private JLabel position;
	private JLabel applied;
	private JLabel duedate;
	private JLabel followup;
	private JLabel status;
	private JLabel notes;
	private JLabel files;
	private JTextField idTF;
	private JTextField companyTF;
	private JTextField positionTF;
	private JTextField appliedTF;
	private JTextField duedateTF;
	private JTextField followupTF;
	private JTextArea notesArea;
	private JList filesList;
	private JPanel middleColumnOne;
	private JPanel middleColumnTwo;
	private JPanel middleColumnThree;
	private JPanel filesPanel;
	private String[] statuses = {"Application sent", "Duedate reached", "Follow-up!", "Advanced interviews", "Application dead"};
	private JComboBox statusList;
	private JPanel buttonContainer;
	private JButton addFileButton;
	private JButton removeFileButton;
	private JButton saveButton;
	private JButton clearButton;
	private JButton cancelButton;
	private Object[] data = {};
	private JPanel filesButtonPanel;
	private JPanel filesListPanel;
	private DefaultListModel listModel;
	private JLabel verifyLabel;
	private JPanel verifyPanel;
	
	public RegisterNewAppView() {
		initialize();
	}

	public void initialize() {
		//instantiating mainFrame
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//MAINCONTAINER
		//instantiating window mainContainer
		Container mainContainer = getContentPane();
		BoxLayout mainContainerLayout = new BoxLayout(mainContainer, BoxLayout.Y_AXIS);
		mainContainer.setLayout(mainContainerLayout);
		
		//TOPCONTAINER
		//generating topContainer as JPanel
		topContainer = new JPanel();
		BoxLayout topContainerLayout = new BoxLayout(topContainer, BoxLayout.X_AXIS);
		topContainer.setLayout(topContainerLayout);
		
		//generating panel topLabels for labels in topContainer
		topLabels = new JPanel();
		BoxLayout topLabelsLayout = new BoxLayout(topLabels, BoxLayout.Y_AXIS);
		topLabels.setLayout(topLabelsLayout);
		topLabels.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		id = new JLabel("ID: ");
		id.setAlignmentX(Component.RIGHT_ALIGNMENT);
		company = new JLabel("Company: ");
		company.setAlignmentX(Component.RIGHT_ALIGNMENT);
		position = new JLabel("Position: ");
		position.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		topLabels.add(id);
		topLabels.add(Box.createVerticalStrut(10));
		topLabels.add(company);
		topLabels.add(Box.createVerticalStrut(10));
		topLabels.add(position);
		
		//generating panel topFields for fields in topContainer
		topFields = new JPanel();
		BoxLayout topFieldsLayout = new BoxLayout(topFields, BoxLayout.Y_AXIS);
		topFields.setLayout(topFieldsLayout);
		topFields.setPreferredSize(new Dimension(200, 100));
		topFields.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		idTF = new JTextField();
		idTF.setMaximumSize(new Dimension(30, 20));
		idTF.setPreferredSize(new Dimension(30, 20));
		idTF.setText(Utils.getNextId());
		idTF.setEnabled(false);
		idTF.setAlignmentX(Component.LEFT_ALIGNMENT);
		companyTF = new JTextField(15);
		positionTF = new JTextField(15);
		
		companyTF.setMaximumSize(new Dimension(230, 20));
		positionTF.setMaximumSize(new Dimension(230, 20));
		
		topFields.add(idTF);
		topFields.add(Box.createVerticalStrut(10));
		topFields.add(companyTF);
		topFields.add(Box.createVerticalStrut(10));
		topFields.add(positionTF);
		topFields.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//adding labels and fields to topContainer
		topContainer.add(topLabels);
		topContainer.add(topFields);
		
		//MIDDLECONTAINER
		//generating middleContainer as JPanel
		middleContainer = new JPanel();
		BoxLayout middleContainerLayout = new BoxLayout(middleContainer, BoxLayout.X_AXIS);
		middleContainer.setLayout(middleContainerLayout);
		
		//generating columns for middleContainer to hold pairings of label and field on the Y_AXIS
		middleColumnOne = new JPanel();
		BoxLayout middleColumnOneLayout = new BoxLayout(middleColumnOne, BoxLayout.Y_AXIS);
		middleColumnOne.setLayout(middleColumnOneLayout);
		
		middleColumnTwo = new JPanel();
		BoxLayout middleColumnTwoLayout = new BoxLayout(middleColumnTwo, BoxLayout.Y_AXIS);
		middleColumnTwo.setLayout(middleColumnTwoLayout);
		
		middleColumnThree = new JPanel();
		BoxLayout middleColumnThreeLayout = new BoxLayout(middleColumnThree, BoxLayout.Y_AXIS);
		middleColumnThree.setLayout(middleColumnThreeLayout);
		
		Dimension columnSize = new Dimension(100, 50);
		middleColumnOne.setMaximumSize(columnSize);
		middleColumnTwo.setMaximumSize(columnSize);
		middleColumnThree.setMaximumSize(columnSize);
		
		//generating labels and textfields for the middlecontainer
		applied = new JLabel("Applied: ");
		applied.setAlignmentX(Component.CENTER_ALIGNMENT);
		duedate = new JLabel("Duedate: ");
		duedate.setAlignmentX(Component.CENTER_ALIGNMENT);
		followup = new JLabel("Follow-up: ");
		followup.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		appliedTF = new JTextField(6);
		duedateTF = new JTextField(6);
		followupTF = new JTextField(6);
		
		//setting maximumsize for middlecontainer textfields
		Dimension maximumFieldSizeMiddle = new Dimension(80, 20);
		appliedTF.setMaximumSize(maximumFieldSizeMiddle);
		duedateTF.setMaximumSize(maximumFieldSizeMiddle);
		followupTF.setMaximumSize(maximumFieldSizeMiddle);
		
		middleColumnOne.add(applied);
		middleColumnOne.add(appliedTF);
		
		middleColumnTwo.add(duedate);
		middleColumnTwo.add(duedateTF);
		
		middleColumnThree.add(followup);
		middleColumnThree.add(followupTF);
		
		//adding labels and fields to middleContainer
		middleContainer.add(middleColumnOne);
		middleContainer.add(middleColumnTwo);
		middleContainer.add(middleColumnThree);
		
		//BOTTOMCONTAINER
		//generating bottomContainer as JPanel
		bottomContainer = new JPanel();
		BoxLayout bottomContainerLayout = new BoxLayout(bottomContainer, BoxLayout.Y_AXIS);
		bottomContainer.setLayout(bottomContainerLayout);
		
		//generating panel bottomLabels for labels in bottomContainer
		bottomLabels = new JPanel();
		BoxLayout bottomLabelsLayout = new BoxLayout(bottomLabels, BoxLayout.Y_AXIS);
		bottomLabels.setLayout(bottomLabelsLayout);
		
		JPanel statusPanel = new JPanel();
		BoxLayout statusListLayout = new BoxLayout(statusPanel, BoxLayout.X_AXIS);
		statusPanel.setLayout(statusListLayout);
		
		JPanel notesPanel = new JPanel();
		BoxLayout notesLayout = new BoxLayout(notesPanel, BoxLayout.X_AXIS);
		notesPanel.setLayout(notesLayout);
		
		status = new JLabel("Status: ");
		notes = new JLabel("Notes: ");
		files = new JLabel("Files: ");
		
		statusList = new JComboBox(statuses);
		statusList.setSelectedIndex(0);
		statusList.setMaximumSize(new Dimension(245, 24));
		statusList.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		notesArea = new JTextArea();
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		notesArea.setBorder(border);
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		
		JScrollPane areaScrollPane = new JScrollPane(notesArea);
		areaScrollPane.setMaximumSize(new Dimension(250, 150));
		areaScrollPane.setPreferredSize(new Dimension(250, 100));
		areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		listModel = new DefaultListModel();
		for(int i = 0; i < data.length; i++) {
			listModel.addElement(data[i]);
		}
		filesList = new JList(listModel);
		filesList.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane filesScrollPane = new JScrollPane(filesList);
		filesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addFileButton = new JButton("Add file");
		removeFileButton = new JButton("Remove file");
		
		filesPanel = new JPanel();
		BoxLayout filesPanelLayout = new BoxLayout(filesPanel, BoxLayout.Y_AXIS);
		filesPanel.setLayout(filesPanelLayout);
		
		filesListPanel = new JPanel();
		BoxLayout filesListPanelLayout = new BoxLayout(filesListPanel, BoxLayout.X_AXIS);
		filesListPanel.setLayout(filesListPanelLayout);
		filesListPanel.setMaximumSize(new Dimension(300,100));
		
		filesListPanel.add(files);
		filesListPanel.add(filesScrollPane);
	
		
		filesButtonPanel = new JPanel();
		BoxLayout filesButtonPanelLayout = new BoxLayout(filesButtonPanel, BoxLayout.X_AXIS);
		filesButtonPanel.setLayout(filesButtonPanelLayout);
		
		filesButtonPanel.add(addFileButton);
		filesButtonPanel.add(Box.createHorizontalStrut(5));
		filesButtonPanel.add(removeFileButton);
		
		statusPanel.add(status);
		statusPanel.add(statusList);
		notesPanel.add(notes);
		notesPanel.add(areaScrollPane);
		filesPanel.add(filesListPanel);
		filesPanel.add(Box.createVerticalStrut(10));
		filesPanel.add(filesButtonPanel);
		
		//adding labels and fields to bottomContainer
		bottomContainer.add(statusPanel);
		bottomContainer.add(Box.createVerticalStrut(15));
		bottomContainer.add(notesPanel);
		bottomContainer.add(Box.createVerticalStrut(15));
		bottomContainer.add(filesPanel);
		
		//generating panel for holding buttons
		buttonContainer = new JPanel();
		BoxLayout buttonContainerLayout = new BoxLayout(buttonContainer, BoxLayout.X_AXIS);
		buttonContainer.setLayout(buttonContainerLayout);
		buttonContainer.setPreferredSize(new Dimension(200, 40));
		
		saveButton = new JButton("Save");
		clearButton = new JButton("Clear");
		cancelButton = new JButton("Back");
		
		buttonContainer.add(saveButton);
		buttonContainer.add(Box.createHorizontalStrut(15));
		buttonContainer.add(clearButton);
		buttonContainer.add(Box.createHorizontalStrut(15));
		buttonContainer.add(cancelButton);
		
		verifyPanel = new JPanel();
		BoxLayout verifyPanelLayout = new BoxLayout(verifyPanel, BoxLayout.X_AXIS);
		verifyPanel.setLayout(verifyPanelLayout);
		verifyPanel.setPreferredSize(new Dimension(200, 40));
		
		verifyLabel = new JLabel("Saved!");
		verifyLabel.setVisible(false);
		verifyLabel.setForeground(new Color(1, 140, 13));
		verifyPanel.add(verifyLabel);
		
		//adding panels to mainContainer
		mainContainer.add(Box.createVerticalStrut(15));
		mainContainer.add(topContainer);
		mainContainer.add(Box.createVerticalStrut(10));
		mainContainer.add(middleContainer);
		mainContainer.add(Box.createVerticalStrut(15));
		mainContainer.add(bottomContainer);
		mainContainer.add(Box.createVerticalStrut(15));
		mainContainer.add(buttonContainer);
		mainContainer.add(verifyPanel);
		mainContainer.add(Box.createVerticalStrut(10));
		
		//setting mainFrame general display parameters
		mainFrame.add(mainContainer);
		mainFrame.setSize(400, 650);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setTitle("Register new application");
	}

	/**
	 * a method to set the background color of a JTextComponent,
	 * used for coloring a non-valid field through the checkForm() 
	 * method in RegisterNewAppController
	 * @param component the component to have its background colored red
	 */
	public void setErrorColor(JTextComponent component) {
		component.setBackground(Color.RED);
	}
	
	public void setBackgroundColor(JTextComponent component, Color color) {
		component.setBackground(color);
	}
	
	/**
	 * @return the mainFrame
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * @return the topContainer
	 */
	public JPanel getTopContainer() {
		return topContainer;
	}

	/**
	 * @param topContainer the topContainer to set
	 */
	public void setTopContainer(JPanel topContainer) {
		this.topContainer = topContainer;
	}

	/**
	 * @return the middleContainer
	 */
	public JPanel getMiddleContainer() {
		return middleContainer;
	}

	/**
	 * @param middleContainer the middleContainer to set
	 */
	public void setMiddleContainer(JPanel middleContainer) {
		this.middleContainer = middleContainer;
	}

	/**
	 * @return the bottomContainer
	 */
	public JPanel getBottomContainer() {
		return bottomContainer;
	}

	/**
	 * @param bottomContainer the bottomContainer to set
	 */
	public void setBottomContainer(JPanel bottomContainer) {
		this.bottomContainer = bottomContainer;
	}

	/**
	 * @return the topLabels
	 */
	public JPanel getTopLabels() {
		return topLabels;
	}

	/**
	 * @param topLabels the topLabels to set
	 */
	public void setTopLabels(JPanel topLabels) {
		this.topLabels = topLabels;
	}

	/**
	 * @return the middleLabels
	 */
	public JPanel getMiddleLabels() {
		return middleLabels;
	}

	/**
	 * @param middleLabels the middleLabels to set
	 */
	public void setMiddleLabels(JPanel middleLabels) {
		this.middleLabels = middleLabels;
	}

	/**
	 * @return the bottomLabels
	 */
	public JPanel getBottomLabels() {
		return bottomLabels;
	}

	/**
	 * @param bottomLabels the bottomLabels to set
	 */
	public void setBottomLabels(JPanel bottomLabels) {
		this.bottomLabels = bottomLabels;
	}

	/**
	 * @return the topFields
	 */
	public JPanel getTopFields() {
		return topFields;
	}

	/**
	 * @param topFields the topFields to set
	 */
	public void setTopFields(JPanel topFields) {
		this.topFields = topFields;
	}

	/**
	 * @return the middleFields
	 */
	public JPanel getMiddleFields() {
		return middleFields;
	}

	/**
	 * @param middleFields the middleFields to set
	 */
	public void setMiddleFields(JPanel middleFields) {
		this.middleFields = middleFields;
	}

	/**
	 * @return the bottomFields
	 */
	public JPanel getBottomFields() {
		return bottomFields;
	}

	/**
	 * @param bottomFields the bottomFields to set
	 */
	public void setBottomFields(JPanel bottomFields) {
		this.bottomFields = bottomFields;
	}

	/**
	 * @return the id
	 */
	public JLabel getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(JLabel id) {
		this.id = id;
	}

	/**
	 * @return the company
	 */
	public JLabel getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(JLabel company) {
		this.company = company;
	}

	/**
	 * @return the position
	 */
	public JLabel getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(JLabel position) {
		this.position = position;
	}

	/**
	 * @return the applied
	 */
	public JLabel getApplied() {
		return applied;
	}

	/**
	 * @param applied the applied to set
	 */
	public void setApplied(JLabel applied) {
		this.applied = applied;
	}

	/**
	 * @return the duedate
	 */
	public JLabel getDuedate() {
		return duedate;
	}

	/**
	 * @param duedate the duedate to set
	 */
	public void setDuedate(JLabel duedate) {
		this.duedate = duedate;
	}

	/**
	 * @return the followup
	 */
	public JLabel getFollowup() {
		return followup;
	}

	/**
	 * @param followup the followup to set
	 */
	public void setFollowup(JLabel followup) {
		this.followup = followup;
	}

	/**
	 * @return the status
	 */
	public JLabel getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(JLabel status) {
		this.status = status;
	}

	/**
	 * @return the notes
	 */
	public JLabel getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(JLabel notes) {
		this.notes = notes;
	}

	/**
	 * @return the files
	 */
	public JLabel getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(JLabel files) {
		this.files = files;
	}

	/**
	 * @return the idTF
	 */
	public JTextField getIdTF() {
		return idTF;
	}

	/**
	 * @param idTF the idTF to set
	 */
	public void setIdTF(JTextField idTF) {
		this.idTF = idTF;
	}

	/**
	 * @return the companyTF
	 */
	public JTextField getCompanyTF() {
		return companyTF;
	}

	/**
	 * @param companyTF the companyTF to set
	 */
	public void setCompanyTF(JTextField companyTF) {
		this.companyTF = companyTF;
	}

	/**
	 * @return the positionTF
	 */
	public JTextField getPositionTF() {
		return positionTF;
	}

	/**
	 * @param positionTF the positionTF to set
	 */
	public void setPositionTF(JTextField positionTF) {
		this.positionTF = positionTF;
	}

	/**
	 * @return the appliedTF
	 */
	public JTextField getAppliedTF() {
		return appliedTF;
	}

	/**
	 * @param appliedTF the appliedTF to set
	 */
	public void setAppliedTF(JTextField appliedTF) {
		this.appliedTF = appliedTF;
	}

	/**
	 * @return the duedateTF
	 */
	public JTextField getDuedateTF() {
		return duedateTF;
	}

	/**
	 * @param duedateTF the duedateTF to set
	 */
	public void setDuedateTF(JTextField duedateTF) {
		this.duedateTF = duedateTF;
	}

	/**
	 * @return the followupTF
	 */
	public JTextField getFollowupTF() {
		return followupTF;
	}

	/**
	 * @param followupTF the followupTF to set
	 */
	public void setFollowupTF(JTextField followupTF) {
		this.followupTF = followupTF;
	}

	/**
	 * @return the notesArea
	 */
	public JTextArea getNotesArea() {
		return notesArea;
	}

	/**
	 * @param notesArea the notesArea to set
	 */
	public void setNotesArea(JTextArea notesArea) {
		this.notesArea = notesArea;
	}

	/**
	 * @return the filesList
	 */
	public JList getFilesList() {
		return filesList;
	}

	/**
	 * @param filesList the filesList to set
	 */
	public void setFilesList(JList filesList) {
		this.filesList = filesList;
	}

	/**
	 * @return the middleColumnOne
	 */
	public JPanel getMiddleColumnOne() {
		return middleColumnOne;
	}

	/**
	 * @param middleColumnOne the middleColumnOne to set
	 */
	public void setMiddleColumnOne(JPanel middleColumnOne) {
		this.middleColumnOne = middleColumnOne;
	}

	/**
	 * @return the middleColumnTwo
	 */
	public JPanel getMiddleColumnTwo() {
		return middleColumnTwo;
	}

	/**
	 * @param middleColumnTwo the middleColumnTwo to set
	 */
	public void setMiddleColumnTwo(JPanel middleColumnTwo) {
		this.middleColumnTwo = middleColumnTwo;
	}

	/**
	 * @return the middleColumnThree
	 */
	public JPanel getMiddleColumnThree() {
		return middleColumnThree;
	}

	/**
	 * @param middleColumnThree the middleColumnThree to set
	 */
	public void setMiddleColumnThree(JPanel middleColumnThree) {
		this.middleColumnThree = middleColumnThree;
	}

	/**
	 * @return the filesPanel
	 */
	public JPanel getFilesPanel() {
		return filesPanel;
	}

	/**
	 * @param filesPanel the filesPanel to set
	 */
	public void setFilesPanel(JPanel filesPanel) {
		this.filesPanel = filesPanel;
	}

	/**
	 * @return the statuses
	 */
	public String[] getStatuses() {
		return statuses;
	}

	/**
	 * @param statuses the statuses to set
	 */
	public void setStatuses(String[] statuses) {
		this.statuses = statuses;
	}

	/**
	 * @return the statusList
	 */
	public JComboBox getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(JComboBox statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the buttonContainer
	 */
	public JPanel getButtonContainer() {
		return buttonContainer;
	}

	/**
	 * @param buttonContainer the buttonContainer to set
	 */
	public void setButtonContainer(JPanel buttonContainer) {
		this.buttonContainer = buttonContainer;
	}

	/**
	 * @return the saveButton
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton the saveButton to set
	 */
	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	/**
	 * @return the clearButton
	 */
	public JButton getClearButton() {
		return clearButton;
	}

	/**
	 * @param clearButton the clearButton to set
	 */
	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	/**
	 * @return the cancelButton
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * @param cancelButton the cancelButton to set
	 */
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	/**
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object[] data) {
		this.data = data;
	}

	/**
	 * @return the addFileButton
	 */
	public JButton getAddFileButton() {
		return addFileButton;
	}

	/**
	 * @param addFileButton the addFileButton to set
	 */
	public void setAddFileButton(JButton addFileButton) {
		this.addFileButton = addFileButton;
	}

	/**
	 * @return the removeFileButton
	 */
	public JButton getRemoveFileButton() {
		return removeFileButton;
	}

	/**
	 * @param removeFileButton the removeFileButton to set
	 */
	public void setRemoveFileButton(JButton removeFileButton) {
		this.removeFileButton = removeFileButton;
	}

	/**
	 * @return the listModel
	 */
	public DefaultListModel getListModel() {
		return listModel;
	}

	/**
	 * @param listModel the listModel to set
	 */
	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

	/**
	 * @return the verifyLabel
	 */
	public JLabel getVerifyLabel() {
		return verifyLabel;
	}

	/**
	 * @param verifyLabel the verifyLabel to set
	 */
	public void setVerifyLabel(JLabel verifyLabel) {
		this.verifyLabel = verifyLabel;
	}
	
}
