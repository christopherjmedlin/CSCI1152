import java.util.ArrayList;
import java.util.List;

/**
 * Generic linked list.
 *
 * @author Christopher Medlin
 */
public class LinkedList<T> {

	private Node head;
	private Node tail;
	private int length;

	private class Node {
		public T data;
		public Node next;

		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	public class LinkedListIterator {
		Node current;
		
		public LinkedListIterator(Node current) {
			this.current = current;
		}

		public T next() {
			if (!this.hasNext()) {
				throw new IllegalStateException("No elements left.");
			}
			T data = current.data;
			current = current.next;
			return data;
		}

		public boolean hasNext() {
			return current != null;
		}
	}
	
	/**
	 * New empty linked list.
	 */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Checks if list is empty.
	 *
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return head==null;
	}
	
	/**
	 * Inserts at end of linked list.
	 */
	public void insert(T data) {
		Node newNode = new Node(data);
		
		if (isEmpty()) {
			this.head = newNode;
		} else {
			this.tail.next = newNode;
		}
		this.tail = newNode;

		length++;
	}
	
	/**
	 * Converts to an array list.
	 */
	public ArrayList<T> toArrayList() {
		Node current = this.head;
		ArrayList<T> arr = new ArrayList<T>();
		
		for (int i = 0; current != null; i++) {
			arr.add(current.data);
			current = current.next;
		}
		return arr;
	}

	/**
	 * Returns every item in the list that is equal to the given item.
	 *
	 * @param search an item that can be compared to the items in this list.
	 */
	public LinkedList<T> linearSearchAll(Comparable<T> search) {
		Node current = this.head;
		LinkedList<T> found = new LinkedList<T>();

		// for each node in list
		while (current != null) {
			if (search.compareTo(current.data) == 0)
				found.insert(current.data);
			current = current.next;
		}

		return found;
	}
	
	public LinkedListIterator iterator() {
		return new LinkedListIterator(this.head);
	}

	public int length() {
		return this.length;
	}
}
