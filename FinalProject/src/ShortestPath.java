/*1. Finding shortest paths between 2 bus stops (as input by the user), returning the list of stops
en route as well as the associated “cost”.

Stops are listed in stops.txt and connections (edges) between them come from stop_times.txt and
transfers.txt files.
All lines in transfers.txt are edges (directed), while in stop_times.txt an edge
should be added only between 2 consecutive stops with the same trip_id.

eg first 3 entries in stop_times.txt are
9017927, 5:25:00, 5:25:00,646,1,,0,0,
9017927, 5:25:50, 5:25:50,378,2,,0,0,0.3300
9017927, 5:26:28, 5:26:28,379,3,,0,0,0.5780
This should add a directed edge from 646 to 378, and a directed edge from 378 to 379 (as they’re on
the same trip id 9017927).

Cost associated with edges should be as follows: 1 if it comes from stop_times.txt, 2 if it comes from
transfers.txt with transfer type 0 (which is immediate transfer possible), and for transfer type 2 the
cost is the minimum transfer time divided by 100.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShortestPath {
	
	static int weight;
	
	
	static List<StopTime> listOfStopTimes = new ArrayList<StopTime>();
	static List<BusStop> listOfStops = new ArrayList<BusStop>();
	static List<Transfers> listOfStopTransfers = new ArrayList<Transfers>();
	static List<DirectedEdge> edges = new ArrayList<DirectedEdge>();
	
	public static void readFile() throws IOException {
		
		
		// read the stop file
		try {
			BufferedReader bufReader = new BufferedReader(new FileReader("stops.txt"));
	        //ignore first line
	        bufReader.readLine();
	        
	        String thisLine;
	        
			while((thisLine = bufReader.readLine()) != null) {
				
				String [] stopInformation = thisLine.split(",");
				String parentStation = ""; // there are no parent stations in stops.txt
				int stopId = -1;
				int stopCode = -1;
				int locationType = -1;
				double stopLatitude = -1;
				double stopLongitude = -1;
				String stopName = "";
				
				if(stopInformation.length == 10 && !stopInformation[9].equals("") && !stopInformation[9].equals(" "))
                    parentStation = stopInformation[9];
				
                if(!stopInformation[0].equals("") && !stopInformation[0].equals(" ")) // if there is a value for stop id which is first value in file
                    stopId = Integer.parseInt(stopInformation[0]);
                
                if(!stopInformation[1].equals("") && !stopInformation[1].equals(" "))
                    stopCode = Integer.parseInt(stopInformation[1]);
                
                if(!stopInformation[4].equals("") && !stopInformation[4].equals(" "))
                    stopLatitude = Double.parseDouble(stopInformation[4]);
                
                if(!stopInformation[5].equals("") && !stopInformation[5].equals(" "))
                    stopLongitude = Double.parseDouble(stopInformation[5]);
                
                if(!stopInformation[8].equals("") && !stopInformation[8].equals(" "))
                    locationType = Integer.parseInt(stopInformation[8]);
                
                String stop_url = (stopInformation[7].equals("")) ? "null" : stopInformation[7];
                
                stopName = stopInformation[2];
                String stop_desc = stopInformation[3];
                
                String zoneId = stopInformation[6];
                String stopURL = stopInformation[7];
                 
                
                BusStop busStop = new BusStop(stopId, stopCode, stopName, stop_desc, stopLatitude, stopLongitude, zoneId, stopURL, locationType, parentStation);
                listOfStops.add(busStop);
                
                
			}
			
		}catch(Exception e){
			System.out.println("Unable to read stops file");
		}
		
		
		
		
		
		listOfStopTimes = SearchTrip.getListOfValidTrips();
		
//		try {
			BufferedReader bufReader2 = new BufferedReader(new FileReader("transfers.txt"));
	        //ignore first line
	        bufReader2.readLine();
	        
	        String thisLine2;
			
			while((thisLine2 = bufReader2.readLine()) != null) {
				
				String [] variables = thisLine2.split(",");
				
				int from_stop_id = -1;
				int to_stop_id = -1;
				int transfer_type = -1;
				int min_transfer_time = -1;
				
				
				if(variables.length == 4 && !variables[3].equals("") && !variables[3].equals(" "))
                    min_transfer_time = Integer.parseInt(variables[3]);
				
                if(!variables[0].equals("") && !variables[0].equals(" ")) 
                    from_stop_id = Integer.parseInt(variables[0]);
                
                if(!variables[1].equals("") && !variables[1].equals(" "))
                    to_stop_id = Integer.parseInt(variables[1]);
                
                if(!variables[2].equals("") && !variables[2].equals(" "))
                    transfer_type = Integer.parseInt(variables[2]);
                
                
                 
                
                Transfers transfer = new Transfers(from_stop_id, to_stop_id, transfer_type, min_transfer_time);
                listOfStopTransfers.add(transfer);
                
                
			}
			
//		}catch(Exception e){
//			System.out.println("Unable to read transfers file");
//		}
	}
	
	
	
	public static void findShortestPath(int start, int destination) throws IOException {
		readFile();
		
		for(int i = 0; i < listOfStopTimes.size() -1; i++) {
			if(listOfStopTimes.get(i).trip_id == listOfStopTimes.get(i + 1).trip_id) {
				weight = 1;
				DirectedEdge directedEdge = new DirectedEdge(listOfStopTimes.get(i).stop_id, listOfStopTimes.get(i + 1).stop_id, weight);
				edges.add(directedEdge);
			}
		}
		for( int i = 0; i < listOfStopTransfers.size(); i++) {
			
			
			if( listOfStopTransfers.get(i).transfer_type == 0) {
				weight = 2;//2 if it comes from transfers.txt with transfer type 0 (which is immediate transfer possible)
			}
			else {
				weight = listOfStopTransfers.get(i).min_transfer_time / 100;//for transfer type 2 the cost is the minimum transfer time divided by 100.

			}
			DirectedEdge directedEdge = new DirectedEdge(listOfStopTransfers.get(i).from_stop_id, listOfStopTransfers.get(i).to_stop_id, weight);
			edges.add(directedEdge);
		}
		
		EdgeWeightedDigraph edgeWeightedDigraph = new EdgeWeightedDigraph(edges.size());
		
		for(int i=0; i < edges.size(); i++) {
			edgeWeightedDigraph.addEdge(edges.get(i));
		}
		
		DijkstraSP shortestPath = new DijkstraSP(edgeWeightedDigraph, start);
		
		if(shortestPath.hasPathTo(destination)) {
			System.out.println("The shortest path from " + start + " to "+ destination + " is " + shortestPath.distTo(destination) );
		}
		else {
			System.out.println( "There is no path between " + start + " and " + destination);
		}
	}
	
	

}
