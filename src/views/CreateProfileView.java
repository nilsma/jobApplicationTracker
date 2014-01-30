package views;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateProfileView extends JFrame {
	private JFrame frame;
	private String title = "Create New Profile";
	private JLabel nameLabel;
	private JLabel pathLabel;
	private JPanel buttonPanel;
	private JButton confirmButton;
	private JButton cancelButton;
	private JTextField nameField;
	private JTextField pathField;
	private Container namePanel;
	private JPanel pathPanel;
	private int frameHeight = 150;
	private int frameWidth = 300;

	public CreateProfileView() {
		initializeCreateProfileView();
	}
	
	public void initializeCreateProfileView() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setTitle(title);
		
		Container container = getContentPane();
		BoxLayout containerLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(containerLayout);
		
		namePanel = new JPanel();
		BoxLayout namePanelLayout = new BoxLayout(namePanel, BoxLayout.X_AXIS);
		namePanel.setLayout(namePanelLayout);
		namePanel.setMaximumSize(new Dimension(frameWidth - 50, frameHeight));
		
		nameLabel = new JLabel("Name: ");
		nameField = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		
		pathPanel = new JPanel();
		BoxLayout fieldPanelLayout = new BoxLayout(pathPanel, BoxLayout.X_AXIS);
		pathPanel.setLayout(fieldPanelLayout);
		pathPanel.setMaximumSize(new Dimension(frameWidth - 50, frameHeight));
		
		pathLabel = new JLabel("Path: ");
		pathField = new JTextField(20);
		pathPanel.add(pathLabel);
		pathPanel.add(pathField);
		
		buttonPanel = new JPanel();
		BoxLayout buttonPanelLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(buttonPanelLayout);
		
		confirmButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		
		buttonPanel.add(confirmButton);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(cancelButton);
		
		container.add(Box.createVerticalStrut(10));
		container.add(namePanel);
//		container.add(Box.createVerticalStrut(10));
//		container.add(pathPanel);
		container.add(Box.createVerticalStrut(10));
		container.add(buttonPanel);
		container.add(Box.createVerticalStrut(10));
		
		frame.add(container);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(frameWidth,frameHeight);
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @return the confirmButton
	 */
	public JButton getConfirmButton() {
		return confirmButton;
	}

	/**
	 * @return the cancelButton
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * @return the nameField
	 */
	public JTextField getNameField() {
		return nameField;
	}

	/**
	 * @param nameField the nameField to set
	 */
	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	/**
	 * @return the pathField
	 */
	public JTextField getPathField() {
		return pathField;
	}

	/**
	 * @param pathField the pathField to set
	 */
	public void setPathField(JTextField pathField) {
		this.pathField = pathField;
	}

}
