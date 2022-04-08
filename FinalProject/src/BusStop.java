
public class BusStop {
	int stop_id;
	int stop_code;
	String stop_name;
	String stop_desc;
	Double stop_lat;
	Double stop_long;
	String zone_id;
	String stop_url;
	int location_type;
	String parent_station;
	
	public BusStop(int stop_id, int stop_code, String stop_name,String stop_desc, Double stop_lat, 
			Double stop_long, String zone_id, String stop_url, int location_type, String parent_station ) {
		this.stop_id = stop_id;
		this.stop_code =stop_code;
		this.stop_name = stop_name;
		this.location_type = location_type;
		this.stop_lat = stop_lat;
		this.stop_long = stop_long;
		this.parent_station = parent_station;
	}

}
