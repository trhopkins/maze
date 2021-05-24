interface StackInterface {
	public void push(int key); // insert Node on top of Stack
	public int pop(); // return key of top Node and remove it
	public int ontop(); // return key of top Node without removing it
	public int size(); // return number of Nodes
	public boolean empty(); // return number of Nodes
	public void traverse(); // show all Nodes and empty Stack
}

class Stack implements StackInterface {
	private Node top;
	private int size;

	/* constructor */
	public Stack() {
		top = null;
		size = 0;
	}

	/* insert Node on top of Stack */
	public void push(int key) {
		Node newNode = new Node(key, top);
		top = newNode;
		size++;
	}

	/* return key of top Node and remove it */
	public int pop() {
		int i = top.getKey();
		top = top.getNext();
		size--;
		return i;
	}

	/* return key of top Node without removing it */
	public int ontop() {
		return top.getKey();
	}

	/* return number of Nodes */
	public int size() {
		return size;
	}

	/* return true if empty */
	public boolean empty() {
		return (top == null);
	}

    /* show all Nodes and empty Stack */
	public void traverse() {
		while (!this.empty()) {
			System.out.println(this.pop());
		}
	}
}
