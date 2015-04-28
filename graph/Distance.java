package graph;

import NicksGraph.*;

public interface Distance<A> {
	public int d(Node<A> start, Node<A> end);
}
