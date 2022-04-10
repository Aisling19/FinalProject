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
		Title.printHeader();
		
		Title.printSelection();
		
		//We can take in the number and then call on the class or function that performs that operation
		Scanner scanner = new Scanner(System.in);
		
		boolean dontQuit = true;
		
		while(dontQuit) {
			System.out.println("/ / / / / / / / / / / / / / / / / / / / / / / / / ");
			System.out.println("\nEnter the number of the function you would like to run:");
			
			
			if(scanner.hasNextInt()) {
				int inputFunction = scanner.nextInt();
				//scanner.nextLine();
				
				if(inputFunction == 1) {
					boolean runFunction1 = true;
					while(runFunction1) {
						System.out.println("\nThis function will find the shortest path between two bus stops");
						System.out.println("\nPlease enter the bus stop's ID you are departing from:");
						
						if(scanner.hasNextInt()) {
							int start = scanner.nextInt();
							//scanner.nextLine();
							
							
							System.out.println("Please enter your trips destination bus stop's ID:");
							if(scanner.hasNextInt());{
								int destination = scanner.nextInt();
								//scanner.nextLine();
								ShortestPath.findShortestPath(start, destination);
							
							}
							
						}
						else 
						{
							System.out.println("The starting or destination bus stop ID is invalid. ");
							String input = scanner.next();
							
						}
						
						
						
						
						
						System.out.println("");
						System.out.println("---------------------------------------------------------------------------");
						System.out.println("\nIf you would like to exit this function type exit, if not type continue:");
						//scanner.next();
						String answer = scanner.next();
						if(answer.equalsIgnoreCase("exit")) {
							runFunction1 = false;
						}
						
					}
					
				}
				
				else if(inputFunction == 2) {
					boolean runFunction2 = true;
					while(runFunction2) {
						System.out.println("Please enter the stop you want to search for:");
						String searchInput = scanner.next();
		                searchInput += scanner.nextLine();
		                searchInput = searchInput.toUpperCase();
		                StringSearch.searchString(searchInput);
		                System.out.println("");
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("\nIf you would like to exit this function type exit, if not type continue:");
						
						String answer = scanner.next();
						if(answer.equalsIgnoreCase("exit")) {
							runFunction2 = false;
						}
					}
					
					
					
	                
					
				}
				
				else if(inputFunction == 3) {
					boolean runFunction3 = true;
					while(runFunction3 ) {
						System.out.println("Please enter the arrival time you would like to search for in the format hh:mm:ss:");
						String arrivalTime = scanner.next();
						if(validInputArrivalTime(arrivalTime) == true) {
							SearchTrip.searchForTrips(arrivalTime);
						}
						else {
							System.out.print("Input is invalid. Must be of the format hh:mm:ss");
						}
						System.out.println("");
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("\nIf you would like to exit this function type exit, if not type continue:");
						String input = scanner.next();
						if(input.equalsIgnoreCase("exit")) {
							runFunction3 = false;
						}
					}
				}
				else if(inputFunction == 4) {
					System.out.println("You have quit the Vancouver bus Management service, enjoy your day:)");
					dontQuit = false;
				}
				else {
					System.out.println("You have inputed an incorrect function. Please try again.");
					
					
				}
				
			}
			
			else {
				System.out.println("You have inputed an incorrect function. Please try again.");
				
				scanner.next();
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
                else {
                	return false;
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
