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
	 */
	public ApplicationTableModel(List<JobApplication> applications) {
		this.applications = applications;
	}
	
	public void updateAppTable(List<JobApplication> applications) {
		this.applications = applications;
		this.fireTableDataChanged();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		if(applications == null) {
			return 0;
		} else {
			return applications.size();
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

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
