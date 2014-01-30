package views;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProfilesView extends JFrame {
	private JFrame frame;
	private String[] profilesArray;
	private String title = "Choose a profile ...";
	private JComboBox comboBox;
	private JPanel topPanel;
	private Container bottomPanel;
	private JButton confirmButton;
	private JButton cancelButton;
	private int frameHeight = 150;
	private int frameWidth = 300;

	public ProfilesView(String[] profilesArray) {
		this.profilesArray = profilesArray;
		initializeProfilesView();
	}
	
	public void initializeProfilesView() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle(title);
		
		Container container = getContentPane();
		BoxLayout containerLayout = new BoxLayout(container, BoxLayout.Y_AXIS);
		container.setLayout(containerLayout);

		topPanel = new JPanel();
		BoxLayout topPanelLayout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
		topPanel.setLayout(topPanelLayout);
		comboBox = new JComboBox();
		DefaultComboBoxModel boxModel = new DefaultComboBoxModel(profilesArray);
		topPanel.setMaximumSize(new Dimension(frameWidth - 50, frameHeight));
		comboBox.setModel(boxModel);
		topPanel.add(comboBox);
//		topPanel.add(boxModel);
		
		bottomPanel = new JPanel();
		BoxLayout bottomPanelLayout = new BoxLayout(bottomPanel, BoxLayout.X_AXIS);
		bottomPanel.setLayout(bottomPanelLayout);
		bottomPanel.setPreferredSize(new Dimension(200, 100));
		confirmButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		bottomPanel.add(confirmButton);
		bottomPanel.add(Box.createHorizontalStrut(30));
		bottomPanel.add(cancelButton);
		
		container.add(Box.createVerticalStrut(20));
		container.add(topPanel);
		container.add(Box.createVerticalStrut(5));
		container.add(bottomPanel);
		
		frame.add(container);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(frameWidth, frameHeight);
	}

	/**
	 * @return the comboBox
	 */
	public JComboBox getComboBox() {
		return comboBox;
	}

	/**
	 * @param comboBox the comboBox to set
	 */
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	/**
	 * @return the confirmButton
	 */
	public JButton getConfirmButton() {
		return confirmButton;
	}

	/**
	 * @param confirmButton the confirmButton to set
	 */
	public void setConfirmButton(JButton confirmButton) {
		this.confirmButton = confirmButton;
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
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param profilesArray the profilesArray to set
	 */
	public void setProfilesArray(String[] profilesArray) {
		this.profilesArray = profilesArray;
	}	 

}
