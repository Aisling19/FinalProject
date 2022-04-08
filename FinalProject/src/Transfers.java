
public class Transfers {
	int from_stop_id;
	int to_stop_id;
	int transfer_type;
	int min_transfer_time;
	
	Transfers(int from_stop_id, int to_stop_id, int transfer_type, int min_transfer_time){
		this.from_stop_id = from_stop_id;
		this.to_stop_id = to_stop_id;
		this.transfer_type = transfer_type;
		this.min_transfer_time = min_transfer_time;
	}

}
