package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.Iterator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.dsa.Algorithm;
import edu.ncsu.csc316.cleaning.dsa.DSAFactory;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.Map.Entry;
import edu.ncsu.csc316.dsa.sorter.Sorter;

/**
 * This class is responsible for instantiating a ReportManager object which is used for interacting
 * with the UI of this program. This class allows information about rooms and the vacuum to be passed
 * from program to user
 * 
 * @author Max Farthing
 */
public class ReportManager {
	/** instance of cleaning manager to be used for output in UI */
	private CleaningManager manager;
	/** constant used for date formatting */
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    /**
     * Constructor for ReportManager that will allow us to change around our specified data structures easily
     * @param pathToRoomFile String containing room file information
     * @param pathToLogFile String containing log file information
     * @param mapType specified map type that will be changeable
     * @throws FileNotFoundException Exception where file cannot be found in program
     */
    public ReportManager(String pathToRoomFile, String pathToLogFile, DataStructure mapType) throws FileNotFoundException {
        manager = new CleaningManager(pathToRoomFile, pathToLogFile, mapType);
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.COUNTING_SORT);
        DSAFactory.setMapType(mapType);
        
    }
    
    /**
     * Constructor for ReportManager with a pre-selected map to be used
     * @param pathToRoomFile String containing room file information
     * @param pathToLogFile String containing log file information
     * @throws FileNotFoundException Exception where file cannot be found in program
     */
    public ReportManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
        this(pathToRoomFile, pathToLogFile, DataStructure.SEARCHTABLE);
    }

    /**
     * This method generates a report in the format of a String on when the vacuum bag will
     * need to changed
     * @param timestamp time of vacuum report requested
     * @return String based on vacuum report on when its bag needs changing
     */
    public String getVacuumBagReport(String timestamp) {
    	StringBuilder st = new StringBuilder();
    	LocalDateTime dateTime = null;
    	try {
    		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        	dateTime = LocalDateTime.parse(timestamp, format); //converting timestamp to localDateTime
    	} catch(DateTimeParseException e) {
    		return "Date & time must be in the format: MM/DD/YYYY HH:MM:SS";
    	}
    	
    	int dis = manager.getCoverageSince(dateTime); 
    	
    	st.append("Vacuum Bag Report (last replaced ");
    	st.append(timestamp);
    	st.append(") [\n");
    	if(dis > 5280) {
        	st.append("   Bag is overdue for replacement!\n");
    	} else {
    		st.append("   Bag is due for replacement in ");
        	st.append(5280 - dis); //amount of SQ FT
        	st.append(" SQ FT\n");
    	}
    	 
    	st.append("]\n");
    	
    	return st.toString();
    }

    /**
     * This method will tell how many times a specified number of rooms have been cleaned
     * @param number The requested number of rooms to be printed to output
     * @return String containing information about number of times each room requested has been cleaned
     */
	public String getFrequencyReport(int number) {
    	if(number <= 0) {
    		return "Number of rooms must be greater than 0.";
    	}
    	
    	Map<String, List<CleaningLogEntry>> map = manager.getEventsByRoom();
    	StringBuilder st = new StringBuilder();
    	st.append("Frequency of Cleanings [\n");
    	
    	Sorter<Entry<String, List<CleaningLogEntry>>> sorter = DSAFactory.getComparisonSorter(new SizeComparator());
    	Iterator<Entry<String, List<CleaningLogEntry>>> it = map.entrySet().iterator();
    	
    	Entry<String, List<CleaningLogEntry>>[] arr;
    	arr = new Entry[map.size()];
    	for(int i = 0; i < map.size(); i++) {
    		arr[i] = it.next();
    	}
    	sorter.sort(arr);
    	
    	int max = Math.min(number, map.size());
    	for(int i = 0; i < max; i++) { //outputs in descending order based on cleaning frequency
    		String s = arr[i].getKey(); //name to be printed for the room
    		List<CleaningLogEntry> l = arr[i].getValue(); //value in KV pair
    		
    		st.append("   "); //3 space indentation
    		st.append(s); //room name
    		st.append(" has been cleaned ");
    		st.append(l.size()); //# of times cleaned (size of list)
    		st.append(" times\n");
    		
    	}
    	
    	st.append("]\n");
    	
    	return st.toString();
    }
    
    /**
     * This method gives a detailed block of information about each room based upon its cleaning
     * history
     * @return String containing information about every room and its cleaning details
     */
	public String getRoomReport() {
    	
    	Map<String, List<CleaningLogEntry>> map = manager.getEventsByRoom();
    	if(map.isEmpty()) { //check if no rooms exist or if no cleaning log events exist
    		return "No rooms have been cleaned.";
    	}
    	Sorter<Entry<String, List<CleaningLogEntry>>> sort = DSAFactory.getComparisonSorter(new RoomComparator());
    	Entry<String, List<CleaningLogEntry>>[] arr;
    	Iterator<Entry<String, List<CleaningLogEntry>>> it = map.entrySet().iterator();

    	arr = new Entry[map.size()];
    	for(int i = 0; i < map.size(); i++) {
    		arr[i] = it.next();
    	}
    	sort.sort(arr);
    	Sorter<CleaningLogEntry> sorter = DSAFactory.getComparisonSorter(new DateComparator());
    
    	StringBuilder st = new StringBuilder(); //rooms output in ascending order, dates output in descending chronological
    	st.append("Room Report [\n");
    	
    	int count = 0;
    	for(int j = 0; j < arr.length; j++) {
    		Entry<String, List<CleaningLogEntry>> e = arr[j]; //capturing entry from iterator
    		String s = e.getKey(); //name to be printed for the room
    		List<CleaningLogEntry> l = e.getValue();

			st.append("   "); //indentation of 3 spaces
    		st.append(s); //room name
    		st.append(" was cleaned on [\n");
    		
    		if(e.getValue().size() == 0) { //if room has never been cleaned size == 0 of list
    			st.append("      (never cleaned)\n");
        		count++;
    		} else {
    			
    			CleaningLogEntry[] array = new CleaningLogEntry[l.size()];
    			listToArray(array, l);
    			sorter.sort(array);
    			
    			for(int i = 0; i < l.size(); i++) {
        			st.append("      "); //indentation of 6 spaces
        			LocalDateTime cleanedTime = array[i].getTimestamp();
        			st.append(cleanedTime.format(DATE_TIME_FORMAT)); //print timeStamp of cleaning
        			st.append("\n");
        		}
    		}
    		
    		st.append("   ]\n"); //end of reading information for 1 room
    	}
    	st.append("]\n"); //end of reading room report
    	if(count == map.size()) {
    		return "No rooms have been cleaned.";
    	}
    	return st.toString();
    }
    
    /**
     * Private helper method to convert a list to an array for sorting
     * @param arr array to be filled in with values
     * @param list list of values we want to sort
     */
    private static void listToArray(CleaningLogEntry[] arr, List<CleaningLogEntry> list) {
    	for(int i = 0; i < list.size(); i++) {
    		arr[i] = list.get(i);
		}
    }
    
    /**
     * private class to help sort entries based on date
     * 
     * @author Max Farthing
     */
    private static class DateComparator implements Comparator<CleaningLogEntry> {

		@Override
		public int compare(CleaningLogEntry c1, CleaningLogEntry c2) {
			return c2.getTimestamp().compareTo(c1.getTimestamp());
		}
    	
    }
    
    /**
     * private class to help sort entries based size of their list
     * 
     * @author Max Farthing
     */
    private static class SizeComparator implements Comparator<Entry<String, List<CleaningLogEntry>>> {

		@Override
		public int compare(Entry<String, List<CleaningLogEntry>> e1, Entry<String, List<CleaningLogEntry>> e2) {
			if(e1.getValue().size() > e2.getValue().size()) {
				return -1;
			} else if(e1.getValue().size() < e2.getValue().size()) {
				return 1;
			} else {
				return e1.getKey().compareTo(e2.getKey());
			}
		}
    	
    }
    
    /**
     * Inner class for comparing Room names based on alphabetical order to be stored in map
     * 
     * @author Max Farthing
     */
    private static class RoomComparator implements Comparator<Entry<String, List<CleaningLogEntry>>> {

		@Override
		public int compare(Entry<String, List<CleaningLogEntry>> e1, Entry<String, List<CleaningLogEntry>> e2) {
			return e1.getKey().compareTo(e2.getKey());
		}
    	
    }
}