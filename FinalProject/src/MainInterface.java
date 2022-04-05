import java.io.IOException;
import java.util.Scanner;

/*4. Provide front interface enabling selection between the above features or an option to exit
the programme, and enabling required user input. It does not matter if this is command-line
or graphical UI, as long as functionality/error checking is provided.
You are required to provide error checking and show appropriate messages in the case of erroneous
inputs – eg bus stop doesn’t exist, wrong format for time for bus stop (eg letters instead of
numbers), no route possible etc. 
*/
public class MainInterface {
	
	

	public static void main(String[] args) throws IOException
    {
		System.out.println("Welcome to the Bus Management System for the Vancouver bus routes."
				+ "\n The following interface has four functions:" 
				+ "\n    1. To find the shortest path between two bus stops"
				+ "\n    2. To search for a bus stop by full name or by the first few characters in the name"
				+ "\n    3. To search for all trips with a given arrival time"
				+ "\n    4. An optiom to exit the programme");
		
		//We can take in the number and then call on the class or function that performs that operation
		Scanner scanner = new Scanner(System.in);
		
		boolean dontQuit = true;
		
		while(dontQuit) {
			System.out.println("Enter the number of the function you would like to run:");
			String inputFunction = scanner.next();
			
			if(inputFunction == "1") {
				//do the functionality for function 1
			}
			
			else if(inputFunction == "2") {
				//do the functionality for function 2
			}
			
			else if(inputFunction == "3") {
				boolean runFunction3 = true;
				while(runFunction3) {
					System.out.println("You have selected function 3."
							+ "\n Please enter the arrival time you would like to search for in the format hh:mm:ss:");
					String arrivalTime = scanner.next();
					if(validInputArrivalTime(arrivalTime) == true) {
						SearchTrip.searchForTrips(arrivalTime);
					}
					else {
						System.out.print("Input is invalid. Must be of the format hh:mm:ss");
					}
				}
			}
			else if(inputFunction == "4") {
				System.out.println("You have quit the Vancouver bus Management service, enjoy your day:)");
			}
			else {
				System.out.println("You have not inputed a correct function. Please try again.");
			}
		}
		scanner.close();
		
    }
	
	static private boolean validInputArrivalTime(String arrivalTime)
    {
        //The input must be equal to hh:mm:ss
        if (arrivalTime == null || arrivalTime.length() != 8)
        {
            return false; // not valid input 
        }
        //Validate time format
        if (arrivalTime.charAt(2) == ':' && arrivalTime.charAt(5) == ':')
        {
            String hh = arrivalTime.substring(0, 2); //substring end index is exclusive and start index is inclusive
            String mm = arrivalTime.substring(3, 5);
            String ss = arrivalTime.substring(6, 8);
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
            //If there is an exception the input arrival time is not in the correct format
            catch (NumberFormatException nfe)
            {
                return false;
            }
        }
        return false;
    }


}
