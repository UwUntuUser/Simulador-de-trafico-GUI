package simulator.model;

import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@SuppressWarnings("null")
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) 
	{		
		Vehicle v= q.get(0);
		List<Vehicle> aux = null;
		aux.add(v);
		return aux;
	}

}
