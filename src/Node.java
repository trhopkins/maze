/* Node methods */
interface NodeInterface {
	public int getKey(); // return key
	public Node getNext(); // return next Node
	public void setNext(Node next); // set next Node
}

/* Node ADT */
class Node implements NodeInterface {
	private int key;
	private Node next;

	/* constructor */
	public Node(int key, Node next) {
		this.key = key;
		this.next = next;
	}

	/* return key */
	public int getKey() {
		return key;
	}

	/* return next Node */
	public Node getNext() {
		return next;
	}

	/* set next Node */
	public void setNext(Node next) {
		this.next = next;
	}
}
