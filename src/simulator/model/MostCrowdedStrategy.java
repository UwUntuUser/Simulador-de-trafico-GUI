package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) 
	{
		int a = 0;
		if(roads.isEmpty())
			a = -1;
		else if(currGreen == -1) // si todos estan en rojo
		{
			int maxSize=0;
			for(int i=0;i<qs.size();i++)
			{
				ArrayList<Vehicle> aux= (ArrayList<Vehicle>) qs.get(i);
				if(aux.size()>maxSize)
					maxSize=aux.size();
			}
			a = maxSize;
		}
		else if(currTime-lastSwitchingTime<timeSlot)
			a = currGreen;
		else 
		{
			int tamMax=-1;
			for(int i=0;i<qs.size();i++)
			{
				if(qs.get(i).size()>tamMax)
				{
					tamMax = qs.get(i).size();
					a = i;
				}
			}
		}
		return a;
	}

	public MostCrowdedStrategy(int ts)
	{
		this.timeSlot=ts;
	}
}
