package graph;

import NicksGraph.*;

public interface Heuristic<A> {
	public int h(Node<A> start, Node<A> end);
}
