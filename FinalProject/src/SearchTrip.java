/* 3. Searching for all trips with a given arrival time, returning full details of all trips matching the
criteria (zero, one or more), sorted by trip id
Arrival time should be provided by the user as hh:mm:ss. When reading in stop_times.txt file you
will need to remove all invalid times, e.g., there are times in the file that start at 27/28 hours, so are
clearly invalid. Maximum time allowed is 23:59:59. 
*/
public class SearchTrip {
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

}
