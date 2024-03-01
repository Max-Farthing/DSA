package edu.ncsu.csc316.cleaning.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
/**
 * testing class for Cleaning Manager
 * 
 * @author Max Farthing
 */
public class CleaningManagerTest {

	/**
	 * testing get events by room method
	 */
	@Test
    public void testGetEventsByRoom() {
        try {
            String pathToRoomFile = "input/room-data.txt";
            String pathToLogFile = "input/cleaning-logs.txt";

            CleaningManager cleaningManager = new CleaningManager(pathToRoomFile, pathToLogFile);

            Map<String, List<CleaningLogEntry>> eventsByRoom = cleaningManager.getEventsByRoom();

            assertEquals(69, eventsByRoom.get("Office").get(0).getPercentCompleted());
            assertEquals(88, eventsByRoom.get("Kitchen").get(0).getPercentCompleted());
            assertEquals(50, eventsByRoom.get("Bedroom").get(0).getPercentCompleted());
            assertEquals(97, eventsByRoom.get("Living Room").get(0).getPercentCompleted());
            assertEquals(7, eventsByRoom.get("Bathroom").get(0).getPercentCompleted());

        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
    }

	/**
	 * testing get coverage since method
	 */
    @Test
    public void testGetCoverageSince() {
        try {
            String pathToRoomFile = "input/room-data.txt";
            String pathToLogFile = "input/cleaning-logs.txt";

            CleaningManager cleaningManager = new CleaningManager(pathToRoomFile, pathToLogFile);

            LocalDateTime timestamp = LocalDateTime.parse("2023-08-31T05:48:10");

            int coverage = cleaningManager.getCoverageSince(timestamp);

            assertEquals(1716, coverage);

        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        }
    }

}
