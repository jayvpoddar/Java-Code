package bloughstuff;

import java.util.*;

public class GraphSearch {
    
    public class Pair<A, B> {

	public final A a;
	public final B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		return true;
	}
}

	
	public static <T> int dsp(T start, Map<T, List<Pair<T, Integer>>> adjList, T goal) {
		Set<T> set = adjList.keySet();	
		HashMap<T, Integer> map = new HashMap();
		LinkedList<T> visited = new LinkedList<T>();
		int count = 0;
		T[] arr = (T[])set.toArray();
		for(int i= 0; i<arr.length; i++)
		{
			if(arr[i].equals(start))
			{
				map.put(arr[i], 0);
			}
			else
				map.put(arr[i], (int) Double.POSITIVE_INFINITY);
		}
		
		
		T node = start;
		while(!visited.contains(goal) && count <= set.size() )
		{
			if(!visited.contains(node)){
				visited.add(node);
				List<Pair<T, Integer>> temp = adjList.get(node);
				for(int i=0; i<temp.size(); i++)
				{
					if(!visited.contains(temp.get(i).a) && (temp.get(i).b + map.get(node) < map.get(temp.get(i).a)))
					{
						map.put(temp.get(i).a, temp.get(i).b + map.get(node));
					}
				}

				node = findMin(map, visited, arr,node);	
			
		}
			count++;
		}
		if(count>=adjList.size()+1)
			return -1;
		return map.get(goal);
		

	}
	
	private static <T> T findMin(HashMap<T, Integer> map, LinkedList<T> visited, T[] arr ,T node)
	{
		int min = (int)Double.POSITIVE_INFINITY;
		T temp = node;
		for(int i= 0; i<arr.length; i++){
			
			if(map.get(arr[i]) < min && !visited.contains(arr[i])){
				min = map.get(arr[i]);
				temp = arr[i];
			}
		}
		
		return temp;
	}
}


