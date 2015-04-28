package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import maybe.Just;
import maybe.Maybe;
import maybe.Nothing;
import NicksGraph.Node;

public class Graph<A> {

	private Stack<Node<A>> stack;
	private LinkedList<Node<A>> queue;
	private HashSet<Node<A>> visited;
	private Map<A,Node<A>> nodes;
	private Map<Node<A>, Node<A>> map;
	private PriorityQueue<Node<A>> priority;
	private HashMap<Node<A>, Node<A>> predecessor;
	private HashMap<Node<A>, Integer> D;
	private HashMap<Node<A>, Integer> f;
	private Comparator<Node<A>> comp;
	
	public Graph() {
		this.stack = new Stack<Node<A>>();
		this.queue = new LinkedList<Node<A>>();
		this.visited = new HashSet<Node<A>>();
	    this.nodes = new LinkedHashMap<A,Node<A>>();
	    this.map = new LinkedHashMap<Node<A>, Node<A>>();
	    this.priority = new PriorityQueue<Node<A>>();
	    this.predecessor = new HashMap<Node<A>, Node<A>>();
	    this.D = new HashMap<Node<A>, Integer>();
	    this.f = new HashMap<Node<A>, Integer>();
	    this.comp = new Comparator<Node<A>>() {

			@Override
			public int compare(Node<A> n1, Node<A> n2) {
				int i1 = f.get(n1);
				int i2 = f.get(n2);
				if(i1 < i2) {
					return -1;
				} else if(i1 > i2) {
					return 1;
				} else {
					return 0;
				}
			}
	    	
	    };
	}

	public Node<A> nodeWith(A c) { 
	  Node<A> node;
	  if (nodes().containsKey(c)) {
	    node = nodes().get(c);
	  } else {
	    node = new Node<A>(c);
	    nodes().put(c,node);
	  }
	  return node;
	}
	
	public Node<A> getNode(A c){
		Node<A> node = nodes().get(c);
		return node;
	}

	public Map<A,Node<A>> nodes() {
	  return nodes;
	}

	public Maybe<Node<A>> nodeDepthFirst(Node<A> start, Node<A> end){
		this.stack = new Stack<Node<A>>();
		stack.push(start);
		this.visited = new HashSet<Node<A>>();
		
		while(!stack.isEmpty()){
			Node<A> x = stack.pop();
			
			if(!visited.contains(x)){
				if(x.equals(end)){
					return new Just<Node<A>>(x);
				}
				
				visited.add(x);
				stack.addAll(x.successors());
			}
		}
		return new Nothing<Node<A>>();
	}
	
	public Maybe<Node<A>> nodeBreadthFirst(Node<A> start, Node<A> end){
		this.queue = new LinkedList<Node<A>>();
		queue.push(start);
		this.visited = new HashSet<Node<A>>();
		
		while(!queue.isEmpty()){
			Node<A> x = queue.pop();
			
			if(!visited.contains(x)){
				if(x.equals(end)){
					return new Just<Node<A>>(x);
				}
				
				visited.add(x);
				queue.addAll(x.successors());
			}
		}
		
		return new Nothing<Node<A>>();
	}
	
	/**
	 * Returns the given node end iff there is a valid path between the two given nodes.
	 * @param start The node from which the algorithm starts.
	 * @param end The node which the algorithm is looking for a path too.
	 * @param h The heuristic distance from a given node to another given node using Euclidean distance.
	 * @param d The Manhattan distance between the current node to another given node.
	 * @return Returns the node that the algorithm is looking for.
	 */
	public Maybe<Node<A>> nodeAStar(Node<A> start, Node<A> end, Heuristic<A> h, Distance<A> d) {
		visited = new HashSet<Node<A>>();
		priority = new PriorityQueue<Node<A>>(11, comp);
		priority.add(start);
		D = new HashMap<Node<A>, Integer>();
		D.put(start, 0);
		f = new HashMap<Node<A>, Integer>();
		f.put(start, h.h(start, end));
		
		while(!priority.isEmpty()){
			Node<A> x = priority.poll();
			
			if(x.equals(end)) {
				return new Just<Node<A>>(x);
			}
			
			visited.add(x);
			
			for(Node<A> s : x.successors()) {
				int cost = D.get(x) + d.d(x, s);
				
				if(!priority.contains(s) || cost < D.get(s)) {
					D.put(s, cost);
					f.put(s, (D.get(s) + h.h(s, end)));
					priority.add(s);
				}
			}
			
			
		}
		
		return new Nothing<Node<A>>();
	}
	
	public Maybe<Stack<Node<A>>> pathDepthFirst(Node<A> start, Node<A> end){
		this.stack = new Stack<Node<A>>();
		stack.push(start);
		this.visited = new HashSet<Node<A>>();
		this.map = new LinkedHashMap<Node<A>, Node<A>>();
		Node<A> y = end;
		
		while(!stack.isEmpty()){
			Node<A> x = stack.pop();
			
			
			if(map.containsKey(x)){
				y = map.get(x);
				map.put(x, y);
			} else if(x.successors().contains(y)) {
				map.put(x, y);
			} else {
				for(Node<A> suc : x.successors()){
					if(map.containsKey(suc)) {
						y = suc;
						map.put(x, y);
					}
				}
			}
			
			if(!visited.contains(x)){
				
				if(x.equals(end)){
					this.stack = new Stack<Node<A>>();
					
					while(!x.equals(start)){
						stack.push(x);
						x = map.get(x);
					}
					stack.push(x);
					return new Just<Stack<Node<A>>>(stack);
				}
				visited.add(x);
				stack.addAll(x.successors());
			}
			y = x;
		}
		return new Nothing<Stack<Node<A>>>();
	}
	
	public Maybe<Stack<Node<A>>> pathBreadthFirst(Node<A> start, Node<A> end){
		this.queue = new LinkedList<Node<A>>();
		queue.push(start);
		this.visited = new HashSet<Node<A>>();
		this.map = new LinkedHashMap<Node<A>, Node<A>>();
		this.stack = new Stack<Node<A>>();
		Node<A> y = end;
		
		while(!queue.isEmpty()){
			Node<A> x = queue.pop();
			
			if(!visited.contains(x)){
				map.put(x, y);
				if(x.equals(end)){
					while(!x.equals(start)){
						stack.push(x);
						x = map.get(x);
					}
					stack.push(x);
					return new Just<Stack<Node<A>>>(stack);
				}
				
				visited.add(x);
			 	queue.addAll(x.successors());
			 	y = x;
			}
		}
		
		return new Nothing<Stack<Node<A>>>();
	}
	
	/**
	 * Searches for the shortest path between two nodes. It doesn't always return the shortest path,
	 * however it is quicker to execute than Dijkstra's Algorithm.
	 * @param start The node from which the algorithm starts.
	 * @param end The node which the algorithm is looking for a path too.
	 * @param h The heuristic distance from a given node to another given node using Euclidean distance.
	 * @param d The Manhattan distance between the current node to another given node.
	 * @return Returns a path between the two given nodes.
	 */
	public Maybe<Stack<Node<A>>> pathAStar(Node<A> start, Node<A> end, Heuristic<A> h, Distance<A> d) {
		visited = new HashSet<Node<A>>();
		priority = new PriorityQueue<Node<A>>(11, comp);
		priority.add(start);
		predecessor = new HashMap<Node<A>, Node<A>>();
		predecessor.put(start, end);
		stack = new Stack<Node<A>>();
		D = new HashMap<Node<A>, Integer>();
		D.put(start, 0);
		f = new HashMap<Node<A>, Integer>();
		f.put(start, h.h(start, end));
		
		while(!priority.isEmpty()){
			Node<A> x = priority.poll();
			
			if(x.equals(end)) {
				while(!x.equals(start)) {
					stack.push(x);
					x = predecessor.get(x);
				}
				stack.push(x);
				return new Just<Stack<Node<A>>>(stack);
			}
			
			visited.add(x);
			
			for(Node<A> s : x.successors()) {
				int cost = D.get(x) + d.d(x, s);
				
				if(!priority.contains(s) || cost < D.get(s)) {
					if(!visited.contains(s)) {
						predecessor.put(s, x);
					}
					D.put(s, cost);
					f.put(s, (D.get(s) + h.h(s, end)));
					if(!priority.contains(s)) {
						priority.add(s);
					}
				}
			}
			
			
		}
		
		return new Nothing<Stack<Node<A>>>();
	}
	
	/**
	 * Searches through a graph for a path between the two given nodes.
	 * The search can either be done using Depth First Search or Breadth First Search,
	 * depending on the Search object that has been passed. It then returns a path between
	 * the two given nodes.
	 * @param start The node from which the search algorithm starts.
	 * @param end The node which the search is looking for a path to.
	 * @param s Determines the search algorithm which is used.
	 * @return Returns a path between the two given nodes.
	 */
	public Maybe<Stack<Node<A>>> path(Node<A> start, Node<A> end, Search<A> s){
		this.visited = new HashSet<Node<A>>();
		this.map = new LinkedHashMap<Node<A>, Node<A>>();
		Node<A> y = end;
		map.put(start, end);
		s.push(start);
		
		while(!s.isEmpty()){
			Node<A> x = s.pop();
			
			if(map.containsKey(x)){
				y = map.get(x);
				map.put(x, y);
			} else if(x.successors().contains(y)) {
				map.put(x, y);
			} else {
				for(Node<A> suc : x.successors()){
					if(map.containsKey(suc)) {
						y = suc;
						map.put(x, y);
					}
				}
			}
			
			if(!visited.contains(x)){
				
				if(x.equals(end)){
					this.stack = new Stack<Node<A>>();
					while(x != null && !x.equals(start)){
						stack.push(x);
						x = map.get(x);
					}
					stack.push(x);
					return new Just<Stack<Node<A>>>(stack);
				}
				visited.add(x);
				s.addAll(x.successors());
			}
			y = x;
		}
		return new Nothing<Stack<Node<A>>>();
	}
}