package simulator.factories;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder{

	public NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
	}

	public Event createTheRoad(int time, String id, String src, String dst,int length,int co2, int vel, Weather w) {
		
		return new NewInterCityRoadEvent(time, id, src,dst,length,co2,vel,w);
	}

}
