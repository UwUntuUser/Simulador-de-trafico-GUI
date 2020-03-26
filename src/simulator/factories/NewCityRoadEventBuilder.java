package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;


public class NewCityRoadEventBuilder extends NewRoadEventBuilder{

	public NewCityRoadEventBuilder() {
		super("new_city_road");
	}


	@Override
	public Event createTheRoad(int time, String id, String src, String dst,int length,int co2, int vel, Weather w) {
		
		return new NewCityRoadEvent(time, id, src,dst,length,co2,vel,w);
	}


}
