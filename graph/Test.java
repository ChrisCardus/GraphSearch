package graph;

//import java.util.Map;

import NicksGraph.Coordinate;
import NicksGraph.Node;

public class Test {

	public static void main(String[] args) {
		/*int[][] nick = {
			      {0,0,1,0,0,1}, 
			      {0,1,0,0,1,1,0,2}, 
			      {0,2,0,3,0,1}, 
			      {0,3,0,2,0,4}, 
			      {0,4,0,3,0,5}, 
			      {0,5,0,6,1,5,0,4}, 
			      {0,6,1,6,0,5}, 
			      {1,0,0,0,1,1,2,0}, 
			      {1,1,1,2,2,1,1,0,0,1}, 
			      {1,2,2,2,1,1,1,3}, 
			      {1,3,1,2,1,4,2,3}, 
			      {1,4,2,4,1,5,1,3}, 
			      {1,5,1,4,2,5,1,6,0,5}, 
			      {1,6,0,6,1,5,2,6}, 
			      {2,0,3,0,2,1,1,0}, 
			      {2,1,2,2,1,1,2,0,3,1}, 
			      {2,2,1,2,2,1,2,3,3,2}, 
			      {2,3,2,2,2,4,3,3,1,3}, 
			      {2,4,1,4,2,5,2,3,3,4}, 
			      {2,5,2,4,1,5,2,6,3,5}, 
			      {2,6,3,6,2,5,1,6}, 
			      {3,0,2,0,3,1}, 
			      {3,1,3,0,4,1,2,1,3,2}, 
			      {3,2,2,2,4,2,3,1}, 
			      {3,3,2,3,3,4}, 
			      {3,4,2,4,3,3}, 
			      {3,5,3,6,2,5,4,5}, 
			      {3,6,2,6,3,5}, 
			      {4,0}, 
			      {4,1,4,2,5,1,3,1}, 
			      {4,2,4,1,5,2,3,2}, 
			      {4,3}, 
			      {4,4}, 
			      {4,5,5,5,3,5}, 
			      {4,6}, 
			      {5,0}, 
			      {5,1,4,1,5,2,6,1}, 
			      {5,2,4,2,5,1,6,2}, 
			      {5,3}, 
			      {5,4}, 
			      {5,5,4,5,6,5}, 
			      {5,6}, 
			      {6,0,7,0,6,1}, 
			      {6,1,6,0,5,1,6,2,7,1}, 
			      {6,2,5,2,6,1,7,2}, 
			      {6,3,7,3,6,4}, 
			      {6,4,6,3,7,4}, 
			      {6,5,5,5,6,6,7,5}, 
			      {6,6,7,6,6,5}, 
			      {7,0,6,0,7,1,8,0}, 
			      {7,1,8,1,7,0,6,1,7,2}, 
			      {7,2,7,3,8,2,6,2,7,1}, 
			      {7,3,6,3,7,2,7,4,8,3}, 
			      {7,4,7,3,8,4,6,4,7,5}, 
			      {7,5,8,5,7,6,7,4,6,5}, 
			      {7,6,6,6,7,5,8,6}, 
			      {8,0,8,1,7,0,9,0}, 
			      {8,1,8,2,9,1,7,1,8,0}, 
			      {8,2,8,1,7,2,8,3}, 
			      {8,3,8,2,7,3,8,4}, 
			      {8,4,8,5,8,3,7,4}, 
			      {8,5,9,5,8,4,7,5,8,6}, 
			      {8,6,8,5,7,6,9,6}, 
			      {9,0,9,1,8,0}, 
			      {9,1,8,1,9,2,9,0}, 
			      {9,2,9,1,9,3}, 
			      {9,3,9,2,9,4}, 
			      {9,4,9,5,9,3}, 
			      {9,5,8,5,9,4,9,6}, 
			      {9,6,9,5,8,6} 
			    };*/
		
		int[][] nick = {
				  {0,0,1,0,0,1}, 
			      {0,1,0,0,1,1,0,2}, 
			      {0,2,0,3,0,1}, 
			      {0,3,0,2,0,4}, 
			      {0,4,0,3,0,5}, 
			      {0,5,0,6,1,5,0,4}, 
			      {0,6,1,6,0,5}, 
			      {1,0,0,0,1,1,2,0}, 
			      {1,1,1,2,2,1,1,0,0,1}, 
			      {1,2,2,2,1,1,1,3}, 
			      {1,3,1,2,1,4,2,3}, 
			      {1,4,2,4,1,5,1,3}, 
			      {1,5,1,4,2,5,1,6,0,5}, 
			      {1,6,0,6,1,5,2,6}, 
			      {2,0,3,0,2,1,1,0}, 
			      {2,1,2,2,1,1,2,0}, 
			      {2,2,1,2,2,1,2,3}, 
			      {2,3,2,2,2,4,1,3}, 
			      {2,4,1,4,2,5,2,3}, 
			      {2,5,2,4,1,5,2,6}, 
			      {2,6,2,5,1,6}, 
			      };
		
		Graph<Coordinate> nicksGraph = new Graph<Coordinate>();
		
	    for (int i = 0; i < nick.length; i++) {
	      assert(nick[i].length >= 2);
	      assert(nick[i].length % 2 == 0);

	      int x = nick[i][0];
	      int y = nick[i][1];
	      Coordinate c = new Coordinate(x, y);

	      Node<Coordinate> node = nicksGraph.nodeWith(c);

	      for (int j = 2; j < nick[i].length; j=j+2) {
	        int sx = nick[i][j];   
	        int sy = nick[i][j+1]; 
	        Coordinate sc = new Coordinate(sx, sy);
	        Node<Coordinate> s = nicksGraph.nodeWith(sc);
	        node.addSuccessor(s);
	        
	      }
	    }
	    
	    //Path finding
	    for(int i = 0; i < 3; i++) {
	    	for(int j = 0; j < 3; j++){
	    		Coordinate c1 = new Coordinate(i, i);
	    	    Coordinate c2 = new Coordinate(j, j);
	    	    
	    	    System.out.println("Depth first search: " + nicksGraph.pathDepthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println("Breadth first search: " + nicksGraph.pathBreadthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println();
	    	}
	    }
	   
	    /*Heuristic<Coordinate> h = new Heuristic<Coordinate>(){

			@Override
			public int h(Node<Coordinate> start, Node<Coordinate> end) {
				int hx = Math.abs(end.contents().getX() - start.contents().getX());
				int hy = Math.abs(end.contents().getY() - start.contents().getY());
				return (int)Math.ceil(Math.sqrt(Math.pow(hx, 2) + Math.pow(hy, 2)));
			}
	    	
	    };
	    
	    Distance<Coordinate> d = new Distance<Coordinate>(){

			@Override
			public int d(Node<Coordinate> start, Node<Coordinate> end) {
				int hx = Math.abs(end.contents().getX() - start.contents().getX());
				int hy = Math.abs(end.contents().getY() - start.contents().getY());
				return hx + hy;
			}
	    	
	    };*/
	    
	    //A* Search
	    /*for(int i = 0; i < 3; i++) {
	    	for(int j = 0; j < 7; j++){
	    		Coordinate c1 = new Coordinate(i, j);
	    	    Coordinate c2 = new Coordinate(2, i);
	    	    System.out.println("i: " + i + " j: " + j);
	    	    System.out.println("Desired: " + nicksGraph.nodeDepthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println("Actual: " + nicksGraph.nodeAStar(nicksGraph.getNode(c1), nicksGraph.getNode(c2), h, d));
	    	    System.out.println();
	    	    System.out.println("i: " + i + " j: " + j);
	    	    System.out.println("Desired: " + nicksGraph.pathDepthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println("Actual: " + nicksGraph.pathAStar(nicksGraph.getNode(c1), nicksGraph.getNode(c2), h, d));
	    	    System.out.println();
	    	}
    	}*/
	    
	    /*Search<Coordinate> s1 = new Search<Coordinate>(true);
	    Search<Coordinate> s2 = new Search<Coordinate>(false);
	    
	    for(int i = 0; i < 3; i++) {
	    	for(int j = 0; j < 7; j++){
	    		Coordinate c1 = new Coordinate(i, j);
	    	    Coordinate c2 = new Coordinate(2, i);
	    	    System.out.println("i: " + i + " j: " + j);
	    	    System.out.println("Desired: " + nicksGraph.pathDepthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println("Actual: " + nicksGraph.path(nicksGraph.getNode(c1), nicksGraph.getNode(c2), s2));
	    	    System.out.println();
	    	    System.out.println("i: " + i + " j: " + j);
	    	    System.out.println("Desired: " + nicksGraph.pathBreadthFirst(nicksGraph.getNode(c1), nicksGraph.getNode(c2)));
	    	    System.out.println("Actual: " + nicksGraph.path(nicksGraph.getNode(c1), nicksGraph.getNode(c2), s1));
	    	    System.out.println();
	    	}
		}*/
	}

}
