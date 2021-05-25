import java.util.Random;

interface Graph { // TODO: reorganize
    public void insert(int v1, int v2);
    public void delete(int v1, int v2);
    public int size();
    public boolean hasEdge(int v1, int v2);
    public LinkedList getEdges(int v);
    public int numNodes();
}

public class Graph {
	private int width, height;
	private LinkedList[] vertices;

	/* constructor */
	public Graph(int width, int height) {
		this.width = width;
		this.height = height;
		vertices = new LinkedList[width*height];
		for (int i=0; i<vertices.length; i++) { // populate Graph with Vertices
			vertices[i] = new LinkedList();
		}
	}

	/* populate Graph with Nodes */
	public void makeGrid() {
		vertices[0].insert(1); // top left corner
		vertices[0].insert(width);
		for (int i=1; i<width-1; i++) { // top side
			vertices[i].insert(i-1);
			vertices[i].insert(i+width);
			vertices[i].insert(i+1);
		}
		vertices[width-1].insert(width-2); // top right corner
		vertices[width-1].insert(2*width-1);
		for (int i=width; i<vertices.length-width; i+=width) { // left side
			vertices[i].insert(i-width);
			vertices[i].insert(i+width);
			vertices[i].insert(i+1);
		}
		for (int i=2*width-1; i<vertices.length-1; i+=width) { // right side
			vertices[i].insert(i-width);
			vertices[i].insert(i-1);
			vertices[i].insert(i+width);
		}
		vertices[vertices.length-width].insert(vertices.length-2*width); // bottom left corner
		vertices[vertices.length-width].insert(vertices.length-width+1);
		for (int i=vertices.length-width+1; i<vertices.length-1; i++) { // bottom side
			vertices[i].insert(i-1);
			vertices[i].insert(i-width);
			vertices[i].insert(i+1);
		}
		vertices[vertices.length-1].insert(vertices.length-2); // bottom right corner
		vertices[vertices.length-1].insert(vertices.length-1-width);
		for (int i=width+1; i<vertices.length-width-1; i++) { // center
			if (vertices[i].empty()) { // if not populated with side Nodes
				vertices[i].insert(i-1);
				vertices[i].insert(i+1);
				vertices[i].insert(i-width);
				vertices[i].insert(i+width);
			}
		}
	}

	/* add Node between Vertices 1 and 2 */
	public void insert(int v1, int v2) {
		vertices[v1].insert(v2);
	}

	/* delete Node between Vertices 1 and 2 */
	public void delete(int v1, int v2) {
		vertices[v1].delete(v2);
	}

	/* return number of Vertices in Graph */
	public int size() {
		return vertices.length;
	}

	/* return number of Nodes in Graph */
	public int numNodes() {
		int total = 0;
		for (LinkedList adj : vertices) {
			total += adj.numNodes();
		}
		return total;
	}

	/* better version of getEdges? */
	public LinkedList getEdges(int v) {
		return vertices[v];
	}

	/* return a random Edge from a Vertex */
	// delete and use the main version instead?
	public int getRandomEdge(int v) {
		Random random = new Random();
		int[] choices = vertices[v].getKeys();
		return choices[random.nextInt(vertices[v].size())];
		//return vertices[v].getKeys()[random.nextInt(vertices[v]vertices.length())];
	}

	/* traverse (print) every LinkedList in the Graph */
	public void traverse() {
		for (int i=0; i<vertices.length; i++) { // traverse each Vertex's adjacency list
			System.out.print(i + ": ");
			vertices[i].traverse();
			System.out.println();
		}
	}

	/* return true if Node exists between Vertices 1 and 2 */
	public boolean hasEdge(int v1, int v2) {
		return vertices[v1].search(v2);
	}
}
