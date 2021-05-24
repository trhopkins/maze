interface LinkedListInterface {
	public void insert(int key); // create a new Node
	public void delete(int key); // delete a Node
	public int numNodes(); // return the number of Nodes
	public boolean empty(); // return true if empty
	public boolean search(int key); // return true if Node exists
	public int[] getKeys(); // return all keys
}

public class LinkedList implements LinkedListInterface {
	private Node head;

	/* constructor */
	public void LinkedList() {
		head = null;
	}

	/* create a new Node */
	public void insert(int key) { // add case to prevent duplicate Nodes?
		Node newHead = new Node(key, head);
		head = newHead;
	}

	/* return the number of Nodes */
	public int numNodes() {
		int count = 0;
		Node tmp = head;
		while (tmp != null) { // from head to tail, increment
			count++;
			tmp = tmp.getNext();
		}
		return count;
	}

	/* return true if empty */
	public boolean empty() {
		return (head == null);
	}

	/* return true if Node */
	public boolean search(int key) {
		boolean result = false; // TODO: return error if 404 instead?
		Node tmp = head;
		while (tmp != null) { // for each while not done, head to tail
			if (tmp.getKey() == key) {
				result = true;
				return result;
			} else {
				tmp = tmp.getNext(); // try the next one
			}
		}
		return result;
	}

	/* delete a Node */
	public void delete(int key) {
		Node tmp = head;
		Node prev = null;
		if (tmp == null) { // if empty
			return; // exits
		}
		if (tmp != null && tmp.getKey() == key) { // if head matches key
			head = tmp.getNext();
			return; // exits
		}
		while (tmp != null && tmp.getKey() != key) {
			prev = tmp;
			tmp = tmp.getNext();
		}
		if (tmp == null) { // not found. TODO: Throw error?
			return; // exits
		}
		prev.setNext(tmp.getNext()); // update previous node's pointer
	}

	/* print all the Nodes in this LinkedList */
	public void traverse() {
		Node tmp = head;
		while (tmp != null) { // for each, head to tail (like Stack)
			System.out.print(tmp.getKey() + " ");
			tmp = tmp.getNext();
		}
	}

	/* return the number of Nodes */
	public int size() {
		int size = 0;
		Node tmp = head;
		while (tmp != null) {
			tmp = tmp.getNext();
			size++;
		}
		return size;
	}

	/* return all keys */
	public int[] getKeys() {
		int[] keys = new int[this.size()];
		Node tmp = head;
		for (int i=0; i<keys.length; i++) {
			keys[i] = tmp.getKey();
			tmp = tmp.getNext();
		}
		return keys;
	}
}
