import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 3. Searching for all trips with a given arrival time, returning full details of all trips matching the
criteria (zero, one or more), sorted by trip id
Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you
will need to remove all invalid times, e.g., there are times in the file that start at 27/28 hours, so are
clearly invalid. Maximum time allowed is 23:59:59. 
*/
public class SearchTrip {
	static List<StopTime> listOfStopTimes = new ArrayList<StopTime>();
	
	
	
	public static void searchForTrips(String arrivalTime) throws IOException {
		
		List<StopTime> matchedStopTimes = new ArrayList<StopTime>();
		
		listOfStopTimes = getListOfValidTrips();
		matchedStopTimes = matchArrivalTime(arrivalTime);
		
		
		//If there are no matching trips
        if (matchedStopTimes == null) // this is not running for some reason
        {
            System.out.println("Unfortunately there are no trips with the given arrival time of " + arrivalTime);
            return;
        }
        
        StopTime[]  arrayStopTime = new StopTime[matchedStopTimes.size()];
        
        matchedStopTimes.toArray(arrayStopTime);
        
        // now sort the array according to their trip id's, using the merge sort from assignment 1
        StopTime [] sortedStopTimes = new StopTime[arrayStopTime.length];
        sortedStopTimes = mergeSortRecursive(arrayStopTime);
        
        // convert the sorted array back to an array list of objects
        matchedStopTimes = Arrays.asList(sortedStopTimes);
        
        if(matchedStopTimes.size() > 0) {
        	System.out.println("The trips that match with the inputed arrival time of " + arrivalTime + " are as follows:");
            for(int i =0; i < matchedStopTimes.size(); i++) {
            	System.out.println("   Trip id: " + matchedStopTimes.get(i).trip_id
            			+ "\n   Arrival Time: " + matchedStopTimes.get(i).arrival_time
            			+ "\n   Departure Time: " + matchedStopTimes.get(i).departure_time
            			+ "\n   Stop id: " + matchedStopTimes.get(i).stop_id
            			+ "\n   Stop Sequence: " + matchedStopTimes.get(i).stop_sequence
            			+ "\n   Stop Headsign: " + matchedStopTimes.get(i).stop_headsign
            			+ "\n   Pickup Type: " + matchedStopTimes.get(i).pickup_type
            			+ "\n   Dropoff Type: " + matchedStopTimes.get(i).drop_off_type
            			+ "\n   Shape of Distance Travelled: " + matchedStopTimes.get(i).shape_dist_travelled);
            	System.out.println("-----------------");
            }
        	
        }
        else {
        	System.out.println("There are no trips that match the inputed arrival time. Sorry");
        }
        
        
        
		
	}
	
	public static List<StopTime> matchArrivalTime(String arrivalTime) {
		List<StopTime> matchedStopTimes = new ArrayList<StopTime>();
		for(int i =0; i< listOfStopTimes.size(); i++) {
			if(arrivalTime.equals(listOfStopTimes.get(i).arrival_time) ) {
				matchedStopTimes.add(listOfStopTimes.get(i));
			}
		}
		return matchedStopTimes;
	}
	
	
	// this function creates an array list of all the valid entries
	public static List<StopTime> getListOfValidTrips() throws IOException {
		
		
		
		
		//Buffered reader reads each line from file and stop_times
		BufferedReader bufReader = new BufferedReader(new FileReader("stop_times.txt"));
        //ignore first line
        bufReader.readLine();
     
        String thisLine;
        
        while ((thisLine = bufReader.readLine()) != null)
        {
        	
        	
            //separate out each different variable of the stop time
            String[] variables = thisLine.split(",");
            
            
            int trip_id = Integer.parseInt(variables[0]);
            
            
            String arrival_time = variables[1].replaceAll("\\s+", "0"); // to work in other functions we add a 0 to the start of the time if it is in the format h:mm:ss
            
            
            
            String departure_time = variables[2].replaceAll("\\s+", "0");

            
            // check if times are valid and if not break out of the iteration of the loop with continue and then go on to next line of input file
            try
            {
                
                LocalTime.parse(arrival_time, DateTimeFormatter.ofPattern("H:mm:ss"));
                LocalTime.parse(departure_time, DateTimeFormatter.ofPattern("H:mm:ss"));
            }
            catch (Exception e) 
            {
                
               
                continue;
            }
            

            
            
            int stop_id = Integer.parseInt(variables[3]);
            
            
            int stop_sequence = Integer.parseInt(variables[4]);
            
            
            
            String stop_headsign = (variables[5].equals("")) ? "null" : variables[5]; // if there is noting in the 6th part of the array then return null : using a conditional statement
            
            
           
            int pickup_type = Integer.parseInt(variables[6]);
            
            
            int drop_off_type = Integer.parseInt(variables[7]);

            //if there are only 8 elements in the array then the shape_dist_travelled does not exist for that line in the stop_times file
            String shape_dist_travelled = (variables.length == 8) ? null : variables[8];

            

            //add all the variables to the stop time
            StopTime stopTime = new StopTime(trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_travelled);

            //add the stopTime objects to the array list
            listOfStopTimes.add(stopTime);

            

            
            
             

            
        }
        bufReader.close();
        
        return listOfStopTimes;
	}
	
	
		
	
	
	
	
	
	
	
	
	static void merge (StopTime a[], StopTime [] aux, int lo, int mid, int hi) {
    	for(int k = lo; k <= hi; k++) {
    		aux[k] = a[k];
    	}
    	int i = lo, j = mid + 1;
    	for ( int k = lo; k <= hi; k++ ) {
    		if(i>mid) {
    			a[k] = aux[j++];
    		}
    		else if(j > hi) {
    			a[k] = aux[i++];
    		}
    		else if(aux[j].trip_id < aux[i].trip_id) {
    			a[k] = aux[j++];
    		}
    		else {
    			a[k] = aux[i++];
    		}
    	}
    }
    	
    static StopTime [] mergeSortRecursive(StopTime [] a) {
    	if(isEmpty(a)) {
    		return null;
    	}
    	StopTime [] aux = new StopTime [a.length];
    	sort(a, aux,0, a.length -1);
    	return a;
    	
    }
    
    
    public static void sort(StopTime[] a, StopTime [] aux, int lo, int hi)
    {
    	if ( hi <= lo) {
    		return;
    	}
    	int mid = lo + (hi - lo)/2;
    	sort(a, aux, lo, mid);
    	sort(a, aux, mid + 1, hi);
    	merge(a, aux, lo, mid, hi);
    }
    
    
    
    static boolean isEmpty(StopTime a[])
	{
		if(a == null) {
			return true;
		}
		else return false;
	}

}
