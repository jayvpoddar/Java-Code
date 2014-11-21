import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MST {

    

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning tree
     * If no MST exists, return null
     * 
     * @param g 
     * 				The graph to be processed.  Will never be null
     * @param start 
     * 				The ID of the start node.  Will always exist in the graph
     * @return the MST of the graph, null if no valid MST exists
     */
    public static Collection<Edge> prims(Graph g, int start){
    	Vertex startVertex = new Vertex(start);
    	Vertex currVertex = startVertex;
    	Collection<Edge> finalEdges = new ArrayList();
    	Collection<Edge> edgeList = g.getEdgeList();
    	ArrayList<Vertex> visited = new ArrayList();
    	visited.add(startVertex);
    	PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    	Map<Vertex, Integer> map = g.getAdjacencies(startVertex);
    	Set<Vertex> set = map.keySet();
    	for(Vertex v: set)
    	{
    		pq.add(new Edge(startVertex, v, map.get(v)));
    	}
    	while(visited.size()!= g.getVertices().size() && !pq.isEmpty())
    	{
    		Edge min = pq.poll();
    		if(!visited.contains(min.getU()) || !visited.contains(min.getV()))
    		{
    			finalEdges.add(min);
    			if(!visited.contains(min.getU()))
    				currVertex = min.getU();
    			else if(!visited.contains(min.getV()))
    				currVertex = min.getV();
    			map.putAll(g.getAdjacencies(currVertex));
    			visited.add(currVertex);
    			set = map.keySet();
    			for(Vertex v:set)
                {
    				if(edgeList.contains(new Edge(currVertex,v, map.get(v))))
    				{
    					pq.add(new Edge(currVertex,v, map.get(v)));
    				}
                }
    		}
    	}
    	if(pq.isEmpty())
    		return null;
    	return finalEdges;
    }
    
}
    	
