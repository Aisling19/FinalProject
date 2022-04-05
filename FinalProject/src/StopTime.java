import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class StopTime {
	//intialising all of the variables in the file stop_times.txt
    String arrival_time;
    String stop_headsign;
    String departure_time;
    String shape_dist_travelled;
    int trip_id;
    int stop_id;
    int pickup_type;
    int stop_sequence;
    int drop_off_type;
    
    //constructor to intialise a stopTime object
    public StopTime(int trip_id, String arrival_time, String departure_time, int stop_id, int stop_sequence, String stop_headsign, int pickup_type, int drop_off_type, String shape_dist_travelled)
    {
        this.trip_id = trip_id;
        this.arrival_time = arrival_time;
        this.departure_time = departure_time;
        this.stop_id = stop_id;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_travelled = shape_dist_travelled;
    }
    
   
    

}
