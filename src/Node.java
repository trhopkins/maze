/* Node methods */
interface NodeInterface {
	public int getKey();
	public Node getNext();
	public void setNext(Node next);
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

	/* return next Node in AdjacencyList */
	public Node getNext() {
		return next;
	}

	/* set next Node in AdjacencyList */
	public void setNext(Node next) {
		this.next = next;
	}
}
