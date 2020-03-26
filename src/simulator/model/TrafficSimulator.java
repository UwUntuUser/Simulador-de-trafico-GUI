package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator {
	
	private RoadMap mapaCarreteras;
	private SortedArrayList<Event> listaEventos;
	private int tiempo;
	private int tiempoActual;
	
	public TrafficSimulator()
	{
		this.listaEventos = new SortedArrayList<Event>();
		this.mapaCarreteras= new RoadMap(new ArrayList<Junction>(),new ArrayList<Road>(),new ArrayList<Vehicle>(),
				new HashMap<String,Junction>(),new HashMap<String,Road>(),new HashMap<String,Vehicle>());
		this.tiempo= 0 ;
		this.tiempoActual = 0;
	}
	
	
	
	public void setTiempo(int t)
	{
		this.tiempo=t;
	}
	public void addEvent(Event e)
	{
		this.listaEventos.add(e);
	}
	public void advance()
	{
		this.tiempoActual++;
		List<Event> auxiliar = new ArrayList<Event>();
		for(int i=0;i<listaEventos.size();i++)
		{
			if(listaEventos.get(i).getTime()==this.tiempoActual)
			{
				listaEventos.get(i).execute(mapaCarreteras);
				auxiliar.add(listaEventos.get(i));
			}
		}
		
		// es necesario que se borren de esta forma, sin esto quedan eventos sin ejecutar
		for(int i =0;i<auxiliar.size();i++) 
			listaEventos.remove(auxiliar.get(i));
		
		List<Junction> cruces = mapaCarreteras.getJunctions();
		List<Road> carreteras = mapaCarreteras.getRoads();


		for(int i=0;i<cruces.size();i++)
			cruces.get(i).advance(tiempo);
		for(int i=0;i<carreteras.size();i++)
			carreteras.get(i).advance(tiempo);
	}
	public void reset()
	{
		this.mapaCarreteras.reset();
		this.listaEventos.clear();
		this.tiempo=0;
	}
	public JSONObject report()
	{
		JSONObject j= new JSONObject();
		JSONObject aux= new JSONObject();
		
		j.put("time", tiempoActual);
		
		j.put("state", this.mapaCarreteras.report());
		
		return j;
	}

}
