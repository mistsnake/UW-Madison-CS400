// --== CS400 File Header Information ==--
// Name: Anais Corona Perez
// Email: coronaperez@wisc.edu
// Team: DC
// TA: Daniel
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.platform.console.ConsoleLauncher;
import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String> graph;
    
    /**
     * Instantiate graph from last week's shortest path activity.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges from Week 11. Shortest Path Activity
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        assertTrue(graph.shortestPath("A", "D").toString().equals(
            "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        assertTrue(graph.shortestPath("A", "F").toString().equals(
            "[A, C, F]"
        ));
    }
    
    /**
     * Confirms that the distance computed by dijkstrasShortestPath
     * between two vertices is correct
     */
    @Test
    public void testDistanceDtoB() {
    	assertTrue(graph.dijkstrasShortestPath("D", "B").distance == 12);
    }
    
    /**
     * Confirm the sequence of nodes along the path from source
     * vertex to end vertex is correct
     */
    @Test
    public void testPathNodesDtoB() {
    	List<String> data = graph.dijkstrasShortestPath("D", "B").dataSequence;
    	String[] correctSeq = {"D", "E", "A", "C", "B"};
    	for(int i = 0; i < correctSeq.length; i++) {
    		assertTrue(data.get(i).equals(correctSeq[i]));
    	}
    }
    
    /**
     * Confirm path cost between two nodes is correct
     */
    @Test
    public void testPathCostEtoF() {
    	assertTrue(graph.dijkstrasShortestPath("E", "F").distance == 7);
    }
    
    /**
     * Confirms the predecessor to the ending vertex
     */
    @Test
    public void testEndPredecessorFtoB() {
    	int predecessorIndex = graph.dijkstrasShortestPath("F", "B").dataSequence.size() - 2;
    	assertTrue(graph.dijkstrasShortestPath("F", "B").dataSequence.get(predecessorIndex).equals("C"));
    }
    
    /**
     * Checks that NoSuchElementException is thrown when it has to be
     */
    @Test
    public void testNoSuchElementException() {
    	
    	//test dijkStrasShortest path with invalid values
    	boolean exceptionCaught = false;
    	
    	try {
    		graph.dijkstrasShortestPath("G", "A");    		
    	}catch(NoSuchElementException invalidStart) {
    		assertTrue(true);
    		exceptionCaught = true;
    	}
    	if(exceptionCaught == false) 
    		assertTrue(false);
    	
    	//reset exceptionCaught
    	exceptionCaught = false;
    	
    	try {
    		graph.dijkstrasShortestPath("A", "G");
    	}catch(NoSuchElementException invalidEnd) {
    		assertTrue(true);
    		exceptionCaught = true;
    	}
    	if(exceptionCaught == false) 
    		assertTrue(false);
    	
    	
    	//reset exceptionCaught
    	exceptionCaught = false;
    	
    	//test dijkStrasShortestPath with two valid entries
    	try {
    		graph.dijkstrasShortestPath("A", "D");
    	}catch(NoSuchElementException e) {
    		assertTrue(false);
    		exceptionCaught = true;
    	}
    	
    	if(exceptionCaught == false)
    		assertTrue(true);
    	
    	//reset exceptionCaught
    	exceptionCaught = false;
    	
    	
    	//create a new graph to test dijkstrasShortestPath when
    	//a path between the two nodes cannot be found
    	graph = new CS400Graph<>();
    	graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
       
        //only create a connection between A and B; C is in the graph
        //but isn't connected anywhere
        graph.insertEdge("A","B",6);
        
        try {
        	graph.dijkstrasShortestPath("A", "C");
        }catch(NoSuchElementException pathNotFound) {
        	assertTrue(true);
        	exceptionCaught = true;
        }
        if(exceptionCaught == false)
        	assertTrue(false);
    }

    
    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        String classPath = System.getProperty("java.class.path").replace(" ", "\\ ");
        String[] arguments = new String[] {
          "-cp",
          classPath,
          "--include-classname=.*",
          "--select-class=" + className };
        ConsoleLauncher.main(arguments);
      }

}