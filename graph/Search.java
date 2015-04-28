package graph;

import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import NicksGraph.Node;

public class Search<A> {

	private LinkedList<Node<A>> q;
	private Stack<Node<A>> s;
	private Boolean queue;
	
	public Search(boolean queue) {
		q = new LinkedList<Node<A>>();
		s = new Stack<Node<A>>();
		this.queue = queue;
	}
	
	/**
	 * Pushes an item onto either the end of a queue, if the Boolean queue is true,
	 * or onto the top of the stack if queue is false.
	 * @param x The item to be added to the data structure.
	 */
	public void push(Node<A> x) {
		if(queue) {
			q.add(x);
		} else {
			s.push(x);
		}
	}
	
	/**
	 * Takes an item off the front of the queue, if the Boolean queue is true,
	 * or takes the top item off of the stack if queue is false.
	 * @return Returns the item at the front of the data structure.
	 */
	public Node<A> pop() {
		if(queue) {
			return q.poll();
		} else {
			return s.pop();
		}
	}
	
	/**
	 * Returns a Boolean indicating if the chosen data structure is empty or not. 
	 * @return Returns true if there are no items in the data structure.
	 */
	public Boolean isEmpty() {
		if(queue) {
			return q.isEmpty();
		} else {
			return s.isEmpty();
		}
	}
	
	/**
	 * Adds all of the items in a given set to the queue or stack.
	 * @param x The set of items to be added to the data structure.
	 */
	public void addAll(Set<Node<A>> x) {
		if(queue) {
			q.addAll(x);
		} else {
			s.addAll(x);
		}
	}
	
	/**
	 * Returns the Boolean queue.
	 * @return Returns the value of the Boolean queue.
	 */
	public Boolean getQueue() {
		return queue;
	}
}
