import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/*
2. Searching for a bus stop by full name or by the first few characters in the name, using a
ternary search tree (TST), returning the full stop information for each stop matching the
search criteria (which can be zero, one or more stops)
In order for this to provide meaningful search functionality please move keywords flagstop, wb, nb,
sb, eb from start of the names to the end of the names of the stops when reading the file into a TST
(eg “WB HASTINGS ST FS HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”) 
*/
public class StringSearch {
	
	static TST<Stop> TST = new TST<Stop>(); // as the value of the key is an integer
	static List<Stop> listOfStops = new ArrayList<Stop>();
	
	TST tst;
	

	
	public static String moveWords(String word) {
		String flagstop = word.substring(0,8);
		if(flagstop.equalsIgnoreCase("flagstop")) {
			String add = " " + flagstop;
			word = word.substring(9);
			word = word + add;
		}
		
		String shortWord = word.substring(0,2);
		if(shortWord.equalsIgnoreCase("wb") || shortWord.equalsIgnoreCase("nb") || shortWord.equalsIgnoreCase("sb")
				|| shortWord.equalsIgnoreCase("eb")) {
			String add2 = " " + shortWord;
			word = word.substring(3);
			word = word + add2;
		}
		
		return word;
	}
	
	public static void createStopList(String searchInput) throws IOException {
		
		
		try {
			
			// read file in as an array list
			BufferedReader buffReader = new BufferedReader(new FileReader("stops.txt"));
			
			buffReader.readLine();
			
			String thisLine;
			while ((thisLine = buffReader.readLine()) != null) {
				String[] variables = thisLine.split(",");
				
				String stop_name = moveWords(variables[2]);
				String stop_id = (variables[0]);
				String stop_code = (variables[1].equals("")) ? "null" : (variables[1]);
				String stop_desc = variables[3];
				String stop_lat = (variables[4]);
				String stop_long = (variables[5]);
				String zone_id = variables[6];
				String stop_url = (variables[7].equals("")) ? "null" : variables[7];
				String location_type= (variables[8]);
				//String parent_station = variables[9];
				
				Stop stop = new Stop(stop_id,  stop_code,  stop_name, stop_desc,  stop_lat, 
						 stop_long,  zone_id,  stop_url,  location_type);
				
				
				
				listOfStops.add(stop);
			}
			
			buffReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
		
		for (int i = 0; i < listOfStops.size(); i++) {
			TST.put(listOfStops.get(i).stop_name, listOfStops.get(i));
		}
		
		
		
		
		
	}
	
	public static void searchString(String searchInput) throws IOException {
		
		createStopList(searchInput);
		
		Stop searchedStop;
		
		
		List<String> matchList = new ArrayList<String>();
		
		matchList = TST.keysWithPrefix(searchInput);
		
		for (int i = 0; i < matchList.size(); i++) {
			searchedStop = TST.get(matchList.get(i));
			System.out.println("The following stops have the string " + searchInput + " in the stop Name");
			System.out.println("   Stop ID: " + searchedStop.stop_id
        			+ "\n   Stop Code: " + searchedStop.stop_code
        			+ "\n   Stop Name: " + searchedStop.stop_name
        			+ "\n   Stop Desc: " + searchedStop.stop_desc
        			+ "\n   Stop Latitude: " + searchedStop.stop_lat
        			+ "\n   Stop Longitude: " + searchedStop.stop_long
        			+ "\n   Zone ID: " + searchedStop.zone_id
        			+ "\n   Stop URL: " + searchedStop.stop_url
        			+ "\n   Location Type: " + searchedStop.location_type);
        			//+ "\n   Parent Station: " + searchedStop.parent_station);
        	System.out.println("-----------------");
			
		}
		
	
	}
	

}
