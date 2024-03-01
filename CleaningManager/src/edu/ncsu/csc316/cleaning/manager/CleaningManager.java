package edu.ncsu.csc316.cleaning.manager;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Comparator;

import edu.ncsu.csc316.cleaning.data.CleaningLogEntry;
import edu.ncsu.csc316.cleaning.data.RoomRecord;
import edu.ncsu.csc316.cleaning.dsa.Algorithm;
import edu.ncsu.csc316.cleaning.dsa.DSAFactory;
import edu.ncsu.csc316.cleaning.dsa.DataStructure;
import edu.ncsu.csc316.cleaning.io.InputReader;
import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.sorter.Sorter;


/**
 * This class is responsible for instantiating the CleaningManager object. This object will help
 * provide information about the cleaning's of each room to the user via the UI
 * 
 * @author Max Farthing
 */
public class CleaningManager {
	/** list of cleaning entry log objects from IO */
	private List<CleaningLogEntry> cleaningList;
	/** list of room record entry objects from IO */
	private List<RoomRecord> roomList;
	
	/**
	 * Constructor with changeable map data structure to allow for easily manipulation of programs
	 * run time
	 * @param pathToRoomFile String containing file path with room information
	 * @param pathToLogFile String containing file path with cleaning log information
	 * @param mapType type of map specified, allows to be changed easily
	 * @throws FileNotFoundException Exception thrown when a file cannot be found 
	 */
    public CleaningManager(String pathToRoomFile, String pathToLogFile, DataStructure mapType) throws FileNotFoundException {
        DSAFactory.setListType(DataStructure.ARRAYBASEDLIST);
        DSAFactory.setComparisonSorterType(Algorithm.MERGESORT);
        DSAFactory.setNonComparisonSorterType(Algorithm.COUNTING_SORT);
        DSAFactory.setMapType(mapType);
        
        cleaningList = InputReader.readLogFile(pathToLogFile);
        roomList = InputReader.readRoomFile(pathToRoomFile);
    }
    
    /**
     * Secondary constructor with an already chosen constructor for the map type
     * @param pathToRoomFile String containing file path with room information
     * @param pathToLogFile String containing file path with cleaning log information
     * @throws FileNotFoundException Exception thrown when a file cannot be found
     */
    public CleaningManager(String pathToRoomFile, String pathToLogFile) throws FileNotFoundException {
        this(pathToRoomFile, pathToLogFile, DataStructure.SEARCHTABLE);
    }

    /**
     * This method puts together out lists of cleaning log entries and their and their names and puts them into
     * a map in K,V format. Allows us to put all cleaning information all together inside of 1 map
     * @return map rooms and their cleaning log entries
     */
    public Map<String, List<CleaningLogEntry>> getEventsByRoom() {
    	Map<String, List<CleaningLogEntry>> map = DSAFactory.getMap(new RoomComparator());
    	
    	for (RoomRecord room : roomList) {
    		List<CleaningLogEntry> l = DSAFactory.getIndexedList();
            map.put(room.getRoomID(), l);
        }
    	
        for (CleaningLogEntry entry : cleaningList) {
            String roomID = entry.getRoomID();
            List<CleaningLogEntry> entries = map.get(roomID);
            if (entries != null) {
                entries.addLast(entry);
            }
        }
        
    	return map;
    }

    /**
     * This method is responsible for determining the amount of coverage by the vacuum since
     * specified time stamp
     * @param time specified time to be checked for
     * @return integer 
     */
    public int getCoverageSince(LocalDateTime time) {  
    	int total = 0;
    	
    	CleaningLogEntry[] cleanArr;
    	cleanArr = new CleaningLogEntry[cleaningList.size()]; //sorting cleaning log entries
    	sortCleaningList(cleaningList, cleanArr);
    	
    	RoomRecord[] roomArr;
    	roomArr = new RoomRecord[roomList.size()]; //sorting room records
    	sortRoomList(roomList, roomArr);
    	
    	for (int i = 0; i < cleanArr.length; i++) {
            if (cleanArr[i].getTimestamp().compareTo(time) > 0) {
                int index = binarySearch(roomArr, cleanArr[i].getRoomID());
                if (index >= 0 && index < roomArr.length && roomArr[index].getRoomID().equals(cleanArr[i].getRoomID())) {
                    int l = roomArr[index].getLength();
                    int w = roomArr[index].getWidth();
                    int sum = l * w * cleanArr[i].getPercentCompleted();
                    total += sum / 100;
                }
            }
        }
    	
    	return total;
    }
    
    /**
     * static helper method to help sort room lists
     * @param list list you want sorted
     * @param arr array to be updated
     */
    private static void sortRoomList(List<RoomRecord> list, RoomRecord[] arr) {
    	Sorter<RoomRecord> sort2 = DSAFactory.getComparisonSorter(new RoomListComparator());
    	for(int i = 0; i < list.size(); i++) {
    		arr[i] = list.get(i);
    	}
    	sort2.sort(arr);
    }
    
    /**
     * static helper method to help sort cleaning lists
     * @param list list to be sorted
     * @param arr updated sorted array
     */
    private static void sortCleaningList(List<CleaningLogEntry> list, CleaningLogEntry[] arr) {
    	Sorter<CleaningLogEntry> sorter = DSAFactory.getComparisonSorter(new NameComparator());
    	for(int i = 0; i < list.size(); i++) {
    		arr[i] = list.get(i);
    	}
    	sorter.sort(arr);
    }
    
    /**
     * private helper method to help with looks up and searching
     * @param arr array to search through
     * @param roomID the id of the room requested
     * @return return index
     */
    private int binarySearch(RoomRecord[] arr, String roomID) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = arr[mid].getRoomID().compareTo(roomID);

            if (comparison == 0) {
                return mid; // Room ID found
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Room ID not found
    }
    
    /**
     * Inner class for comparing Room names based on alphabetical order to be stored in map
     * 
     * @author Max Farthing
     */
    private static class RoomComparator implements Comparator<String> {

		@Override
		public int compare(String room1, String room2) {
			return room1.compareTo(room2);
		}
    	
    }
    
    /**
     * Inner class for comparing time stamps
     * 
     * @author Max Farthing
     */
    private static class NameComparator implements Comparator<CleaningLogEntry> {

		@Override
		public int compare(CleaningLogEntry c1, CleaningLogEntry c2) {
			return c1.getTimestamp().compareTo(c2.getTimestamp());
		}
    	
    }
    
    /**
     * Inner class for comparing room IDS
     * 
     * @author Max Farthing
     */
    private static class RoomListComparator implements Comparator<RoomRecord> {

		@Override
		public int compare(RoomRecord r1, RoomRecord r2) {
			return r1.getRoomID().compareTo(r2.getRoomID());
		}
    	
    }
    
}