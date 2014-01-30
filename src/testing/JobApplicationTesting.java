package testing;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import models.JobApplication;
import utils.Utils;

public class JobApplicationTesting {
	
	private JobApplication applicationNonEmpty;
	private JobApplication applicationEmpty;
	
	@Before
	public void setUp() throws Exception {

		applicationNonEmpty = new JobApplication(
				Utils.getNextId(),
				"company", "position", "010613", "010713", "010813",
				"Application sent", "some notes ...", null
				);
		applicationEmpty = new JobApplication();
	}
	
	@Test
	public void testCompanyNonEmpty() {
		assertEquals("company", applicationNonEmpty.getCompany());
		assertFalse(applicationNonEmpty.getCompany().equals("asdf"));
	}
	
	@Test
	public void testPositionNonEmpty() {
		assertEquals("position", applicationNonEmpty.getPosition());
		assertFalse(applicationNonEmpty.getPosition().equals("asdf"));
	}
	
	@Test
	public void testAppliedNonEmpty() {
		assertEquals("010613", Utils.dateToString(applicationNonEmpty.getApplied()));
		assertFalse(Utils.dateToString(applicationNonEmpty.getApplied()).equals("010170"));
	}
	
	@Test
	public void testDuedateNonEmpty() {
		assertEquals("010713", Utils.dateToString(applicationNonEmpty.getDuedate()));
		assertFalse(Utils.dateToString(applicationNonEmpty.getDuedate()).equals("010170"));
	}
	
	@Test
	public void testFollowupNonEmpty() {
		assertEquals("010813", Utils.dateToString(applicationNonEmpty.getFollowup()));
		assertFalse(Utils.dateToString(applicationNonEmpty.getFollowup()).equals("010170"));
	}
	
	@Test
	public void testStatusNonEmpty() {
		assertEquals("Application sent", applicationNonEmpty.getStatus());
		assertFalse(applicationNonEmpty.getStatus().equals("asdf"));
	}
	
	@Test
	public void testNotesNonEmpty() {
		assertEquals("some notes ...", applicationNonEmpty.getNotes());
		assertFalse(applicationNonEmpty.getNotes().equals("asdf"));
	}
	
	@Test
	public void testActiveNonEmpty() {
		assertTrue(applicationNonEmpty.isActive());
	}
	
	@Test
	public void testCompanyEmpty() {
		assertEquals("", applicationEmpty.getCompany());
	}
	
	@Test
	public void testPositionEmpty() {
		assertEquals("", applicationEmpty.getPosition());
	}
	
	@Test
	public void testAppliedEmpty() {
		assertNull(applicationEmpty.getApplied());
	}
	
	@Test
	public void testDuedateempty() {
		assertNull(applicationEmpty.getDuedate());
	}
	
	@Test
	public void testFollowupEmpty() {
		assertNull(applicationEmpty.getFollowup());
	}
	
	@Test
	public void testStatusEmpty() {
		assertEquals("", applicationEmpty.getStatus());
	}
	
	@Test
	public void testNotesEmpty() {
		assertEquals("", applicationEmpty.getNotes());
	}
	
	@Test
	public void testSetDateTrue() {
		String string = "010613";
		Date date = null;
		try {
			date = new SimpleDateFormat("ddMMyy").parse(string);
		} catch (ParseException e) {
			System.out.println("Testing JobApplication setDate (testSetDateTrue()) failed.");
			e.printStackTrace();
		}
		assertEquals(date, applicationNonEmpty.setDate("010613"));
	}
	
	@Test
	public void testSetDateFail() {
		String string = "010513";
		Date date = null;
		try {
			date = new SimpleDateFormat("ddMMyy").parse(string);
		} catch (ParseException e) {
			System.out.println("Testing JobApplication setDate (testSetDateFail()) failed.");
			e.printStackTrace();
		}
		assertFalse(date == applicationNonEmpty.setDate("010613"));
	}
	
	@Test
	public void testGetStatusTrue() {
		assertEquals("", applicationEmpty.getStatus());
	}
	
	@Test
	public void testGetStatusFail() {
		assertFalse("asdf".equals(applicationEmpty.getStatus()));
	}
	
	@Test
	public void testSetStatus() {
		applicationEmpty.setStatus("Application dead");
		assertEquals("Application dead", applicationEmpty.getStatus());
	}
	
	@Test
	public void testDefineActiveTrue() {
		assertTrue(applicationEmpty.isActive());
	}
	
	@Test
	public void testDefineActiveFalse() {
		assertFalse(!applicationEmpty.isActive());
	}
	
	@Test
	public void testSetActive() {
		applicationEmpty.setActive(false);
		assertFalse(applicationEmpty.isActive());
	}
	
	@Test
	public void testGetNotes() {
		assertEquals("", applicationEmpty.getNotes());
	}
	
	@Test
	public void testSetNotes() {
		applicationEmpty.setNotes("asdf");
		assertEquals("asdf", applicationEmpty.getNotes());
	}
}
