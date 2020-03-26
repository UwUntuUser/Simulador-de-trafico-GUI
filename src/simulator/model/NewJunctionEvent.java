package simulator.model;

public class NewJunctionEvent extends Event {

	
	private Junction cruce;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor)  {
		super(time);
		cruce = new Junction(id, lsStrategy, dqStrategy, yCoor, yCoor);
	}

	@Override
	void execute(RoadMap map) {
		
		map.addJunction(cruce);
		
	}
}
