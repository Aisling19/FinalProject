
public class Stop {
	int stopId;
	int stopCode;
	String stopName;
	int locationType;
	double stopLatitude;
	double stopLongitude;
	String parentStation;
	
	public Stop(double distance, int edge) {
		this.distance = distance;
		this.edge =edge;
	}

}
