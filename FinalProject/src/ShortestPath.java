// to find the shortest path between two stops

/*Cost associated with edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes from
 *transfers.txt with transfer type 0 (which is immediate transfer possible), and for transfer type 2 the
 *cost is the minimum transfer time divided by 100.
 * 
 */

import java.util.Scanner;

public class ShortestPath {
	
	
	private double matrix [][];
	private String stopTimes;
	private String transfers;
	
	public void readStops(String filename) {
		// read the stop file
		try {
			Scanner scanner = new Scanner(filename);
			scanner.hasNextLine(); // this is the line of words
			
			while(scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String [] stopInformation = line.split(",");
				String parentStation = ""; // there are like no parent stations in stops.txt
				int stopId = -1;
				int stopCode = -1;
				int locationType = -1;
				double stopLatitude = -1;
				double stopLongitude = -1;
				String stopName = "";
				
				if(stopInformation.length == 10 && !stopInformation[9].equals("") && !stopInformation[9].equals(" "))
                    parentStation = stopInformation[9];
				
                if(!stopInformation[0].equals("") && !stopInformation[0].equals(" ")) // if there is a value for stopid which is first value in file
                    stopId = Integer.parseInt(stopInformation[0]);
                
                if(!stopInformation[1].equals("") && !stopInformation[1].equals(" "))
                    stopCode = Integer.parseInt(stopInformation[1]);
                
                if(!stopInformation[4].equals("") && !stopInformation[4].equals(" "))
                    stopLatitude = Double.parseDouble(stopInformation[4]);
                
                if(!stopInformation[5].equals("") && !stopInformation[5].equals(" "))
                    stopLongitude = Double.parseDouble(stopInformation[5]);
                
                if(!stopInformation[8].equals("") && !stopInformation[8].equals(" "))
                    locationType = Integer.parseInt(stopInformation[8]);
                
                addStop(new BusStop(stopId, stopCode, stopName, stopInfo[3], stopLatitude, stopLongitude, stopInfo[6], stopInfo[7], locationType, parentStation));
                
                // if we need to know stop name add in here
			}
			
		}catch(Exception e){
			
		}
	}
	
	public void addStop(Stop stop) {
		
	}
	
	public void readStopTime(String filename) {
		Scanner scanner = new Scanner(filename);
		scanner.nextLine(); // skips the first line with words on it
		
		while(scanner.hasNext()) {
			int tripId = -1;
			int stopId =-1;
			double [][] stopSequenceDistance = new double[41][41] ; // there are 41 stops in each route
			
		}
	}
	
	

}
