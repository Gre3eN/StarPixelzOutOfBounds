package FatPack;

public class MidOval extends Oval{
	
	public MidOval(int y) {
		this.y = y;
		transparency = Values.SECOND_OVAL_START_TRANSPERENCY;
	}
	
	public void expand() {
		size += Values.SECOND_OVAL_EXPAND_RATE;
		x -= Values.SECOND_OVAL_EXPAND_RATE / 2;
		y -= Values.SECOND_OVAL_EXPAND_RATE / 2;
		if(transparency > 0){
			transparency -= Values.INNER_OVAL_TRANSPERENCY_LOSS;
		}
	}
}
