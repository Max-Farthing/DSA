package edu.ncsu.csc316.cleaning.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.cleaning.dsa.DataStructure;

/**
 * testing class for report manager
 * 
 * @author Max Farthing
 */
public class ReportManagerTest {
    /** report manager for testing */
	private ReportManager reportManager;

	/**
	 * setup method for testing
	 */
	@Before
	public void setUp() {
		try {
            reportManager = new ReportManager("input/room-data.txt", "input/cleaning-logs.txt", DataStructure.SEARCHTABLE);
        } catch (FileNotFoundException e) {
        	System.out.println("oops");
        }
	}
	
	/**
	 * testing the vacuum bag report
	 */
	@Test
	public void testVacuumBagReport() {
		String timestamp = "08/31/2023 05:48:10";
        String expected = "Vacuum Bag Report (last replaced " + timestamp + ") [\n" +
                "   Bag is due for replacement in 3564 SQ FT\n" +
                "]\n";

        String result = reportManager.getVacuumBagReport(timestamp);

        assertEquals(expected, result);
	}

	/**
	 * testing frequency report
	 */
	@Test
    public void testFrequencyReport() {
        int number = 3; 
        String expected = "Frequency of Cleanings [\n" +
                "   Office has been cleaned 2 times\n" +
                "   Bathroom has been cleaned 1 times\n" +
                "   Bedroom has been cleaned 1 times\n" +
                "]\n";

        String result = reportManager.getFrequencyReport(number);

        assertEquals(expected, result);
    }
	
	/**
	 * testing room report method
	 */
	@Test
    public void testRoomReport() {
        String expected = "Room Report [\n" +
                "   Bathroom was cleaned on [\n" +
                "      09/26/2023 11:54:32\n" +
                "   ]\n" +
                "   Bedroom was cleaned on [\n" +
                "      09/25/2023 16:22:15\n" +
                "   ]\n" +
                "   Kitchen was cleaned on [\n" +
                "      09/23/2023 08:52:55\n" +
                "   ]\n" +
                "   Living Room was cleaned on [\n" +
                "      08/31/2023 05:48:11\n" +
                "   ]\n" +
                "   Office was cleaned on [\n" +
                "      09/28/2023 12:15:02\n" +
                "      09/27/2023 14:42:02\n" +
                "   ]\n" +
                "]\n";

        String result = reportManager.getRoomReport();

        assertEquals(expected, result);
    }
}