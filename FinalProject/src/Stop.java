
public class Stop {
	String stop_id;
	String stop_code;
	String stop_name;
	String stop_desc;
	String stop_lat;
	String stop_long;
	String zone_id;
	String stop_url;
	String location_type;
	String parent_station;
	
	public Stop(String stop_id, String stop_code, String stop_name,String stop_desc, String stop_lat, 
			String stop_long, String zone_id, String stop_url, String location_type ) {
		this.stop_id = stop_id;
		this.stop_code =stop_code;
		this.stop_name = stop_name;
		this.location_type = location_type;
		this.stop_lat = stop_lat;
		this.stop_long = stop_long;
		//this.parent_station = parent_station;
	}

}
