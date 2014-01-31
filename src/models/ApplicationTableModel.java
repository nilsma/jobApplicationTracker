package models;

import java.util.List;

import javax.swing.table.DefaultTableModel;

/**
 * A model class for table, extending AbstractTableModel
 * @author nilsma
 *
 */
@SuppressWarnings("serial")
public class ApplicationTableModel extends DefaultTableModel {
	private String[] columnNames = {"Company", "Position", "Applied", "Duedate", "Followup", "Status", "Notes"};
	private List<JobApplication> applications;
	
	/**
	 * Constructor for the ApplicationTableModel
	 * @param applications the list of applications for the table
	 */
	public ApplicationTableModel(List<JobApplication> applications) {
		this.applications = applications;
	}
	
	/**
	 * A method to update the table containing the job applications
	 * @param applications the new list of applications to which the table should be updated with - both previous and new applications 
	 */
	public void updateAppTable(List<JobApplication> applications) {
		this.applications = applications;
		this.fireTableDataChanged();
	}
	
	/**
	 * A method to distinguish whether a given cell is editable
	 * @param row the number of the row for the cell to check
	 * @param column the number of the column for the cell to check
	 * @return false regardless as no cells in the table should be editable
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	/**
	 * A method to get the number of columns in the application table
	 * @return the number of columns
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * A method to the number of rows in the application table
	 * @return the number of rows
	 */
	@Override
	public int getRowCount() {
		if(applications == null) {
			return 0;
		} else {
			return applications.size();
		}
	}
	
	/**
	 * A method to get the header of a given column
	 * @param columnIndex the number of the column to get the title for
	 * @return the columns title
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	/**
	 * A method to get the value of a cell at the given row and column in the table of applications
	 * @param row the number of the row of which to get the value
	 * @param column the number of the column of which to get the value
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return applications.get(row).getColumnData(column);
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

}
