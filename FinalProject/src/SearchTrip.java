import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* 3. Searching for all trips with a given arrival time, returning full details of all trips matching the
criteria (zero, one or more), sorted by trip id
Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you
will need to remove all invalid times, e.g., there are times in the file that start at 27/28 hours, so are
clearly invalid. Maximum time allowed is 23:59:59. 
*/
public class SearchTrip {
	
	public ArrayList<StopTime> matchedTrips(String filename) throws IOException {
		int totalEntries = 0; // don't know if i will need this value
		int numberOfValidEntries = 0;
		ArrayList<StopTime> stopTimes = new ArrayList<StopTime>();
		
		//Buffered reader reads each line from file and stop_times
		BufferedReader reader = new BufferedReader(new FileReader(filename));
        //ignore first line
        reader.readLine();
     
        String thisLine;
        
        while ((thisLine = reader.readLine()) != null)
        {
        	
        	
            //separate out each different variable of the stop time
            String[] variables = thisLine.split(",");
            
            
            int trip_id = Integer.parseInt(variables[0]);
            
            
            String arrival_time = variables[1].replaceAll("\\s+", "0"); // to work in other functions we add a 0 to the start of the time if it is in the format h:mm:ss
            
            
            
            String departure_time = variables[2].replaceAll("\\s+", "0");

            if(validTimeFormat(arrival_time) == true && validTimeFormat(departure_time) == true) {
            	numberOfValidEntries ++;
            }
            else {
            	continue; // skip this iteration of loop as not a valid time
            }

            
            
            int stop_id = Integer.parseInt(variables[3]);
            
            
            int stop_sequence = Integer.parseInt(variables[4]);
            
            
            
            String stop_headsign = (variables[5].equals("")) ? "null" : variables[5]; // if there is noting in the 6th part of the array then return null : using a conditional statement
            
            
           
            int pickup_type = Integer.parseInt(variables[6]);
            
            
            int drop_off_type = Integer.parseInt(variables[7]);

            //if there are only 8 elements in the array then the shape_dist_travelled does not exist for that line in the stop_times file
            String shape_dist_travelled = (variables.length == 8) ? null : variables[8];

            //Get a list of all the stopTime objects in the HashMap with the new arrival time from the latest line.
            //If none exist previously, then make a new array of them that can be used by future lines.
            List<stopTime> newList = stopTimes.getOrDefault(arrival_time, new ArrayList<>());

            //Now we can create the stopTime object since we have all the necessary parameters and we have completed the error-checking for the time values.
            stopTime stopTimeToAdd = new stopTime(trip_id,arrival_time,departure_time,stop_id,stop_Sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_travelled);

            //Now we can add this new stopTime object to the list, of all the stopTime objects with the same arrival time.
            newList.add(stopTimeToAdd);

            //Now we can add the list of stopTime objects back into the HashMap, with the key as the arrival_time.
            //This allows us to carry out requests for individual arrival times later on, and get stopTime objects that match the query.
            stopTimes.put(arrival_time, newList);

            //Update variables for the progress bar
            counterForProgressBar++;
            numberOfValidEntries++;

            //We only want to update the progress bar for every 1% of progress, so the function remains speedy.
            //If we updated the progress bar after every line, the I/0 required would slow down the function.
            if (counterForProgressBar % ((int)totalNumberOfLinesInFile/100) == 0)
            {
                    progressBar.updateProgressBar(counterForProgressBar, totalNumberOfLinesInFile);
            }
        }
	}
	
	
	static private boolean validTimeFormat(String givenArrivalTime)
    {
        //The input must be equal to hh:mm:ss
        if (givenArrivalTime == null || givenArrivalTime.length() != 8)
        {
            return false; // not valid input 
        }
        //Validate time format
        if (givenArrivalTime.charAt(2) == ':' && givenArrivalTime.charAt(5) == ':')
        {
            String hh = givenArrivalTime.substring(0, 2); //substring end index is exclusive and start index is inclusive
            String mm = givenArrivalTime.substring(3, 5);
            String ss = givenArrivalTime.substring(6, 8);
            int hours, minutes, seconds;
            try
            {
                hours = Integer.parseInt(hh);
                minutes = Integer.parseInt(mm);
                seconds = Integer.parseInt(ss);
                if (hours > -1 && hours < 24 && // checking to see if hours and minutes form a valid time
                    minutes > -1 && minutes < 60 &&
                    seconds > -1 && seconds < 60)
                {
                    return true;
                }
            }
            //If an exception occurs when parsing the the strings as integers, the input is not in a valid time format.
            catch (NumberFormatException nfe)
            {
                return false;
            }
        }
        return false;
    }
	
	
	
	public void invalidTrips() {
		
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
