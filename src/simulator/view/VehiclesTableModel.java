package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver{

	private Controller c;
	private String [] columnas = {"ID","Estado","Itinerario","ClaseCO2","VelMax","VelActual","CO2Total","TotalDistancia"};
	private List<Vehicle> coches;
	
	public VehiclesTableModel(Controller ctlr)
	{
		c=ctlr;
		coches = new ArrayList<Vehicle>(c.getTrafficSimulator().getMapaCarreteras().getVehicles());
		ctlr.addObserver(this);
	}
	@Override
	public int getRowCount() {
		return coches.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj = null;
		if(rowIndex>=coches.size())
			obj= null;
		else
		{
			Vehicle e = coches.get(rowIndex);
			if(columnIndex==0)
				obj = e.getId();
			else if(columnIndex==1)
				{
					if(e.getEstado()==VehicleStatus.PENDING)
						obj = "Pending";
					else if(e.getEstado()==VehicleStatus.ARRIVED)
						obj = "Arrived";
					else if(e.getEstado()==VehicleStatus.TRAVELING)
						{
							String s =  "Travelling " + e.getRoad() +":"+ e.getLocalizacion();
							obj = s;
						}
					else if(e.getEstado()==VehicleStatus.WAITING)
					{
						String s =  "Waiting j:"+ e.getCruceActual();
						obj = s;
					}
				}
			else if(columnIndex==2)
				obj = e.getItinerario();
			else if(columnIndex==3)
				obj = e.getGradoContaminacion();
			else if(columnIndex==4)
				obj = e.getVelMax();
			else if(columnIndex==5)
				obj = e.getVelActual();
			else if(columnIndex==6)
				obj = e.getContaminacionTotal();
			else if(columnIndex==7)
				obj = e.getDistTotal();
			else
				obj = null;
		}
		
		return obj;
	}
	public String getColumnName(int col) {
		return columnas[col];
	}
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> evento, int time) {
		coches=map.getVehicles();
		this.fireTableDataChanged();		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> evento, Event e, int time) {
		coches=map.getVehicles();
		this.fireTableDataChanged();		
	}

	@Override
	public void onReset(RoadMap map, List<Event> evento, int time) {
		for(int i=0;i<this.coches.size();i++)
			this.coches.remove(i);
		this.fireTableDataChanged();		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> evento, int time) {
		coches=map.getVehicles();
		this.fireTableDataChanged();		
	}

	@Override
	public void onError(String err) {
		System.out.println(err);
	}

}
