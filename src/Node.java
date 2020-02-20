
public class Node implements Comparable<Node> {

	public Node _parent;
	public Node _right;
	public Node _left;
	public Visitor data;

	public Node(Visitor data) {
		this._right = null;
		this._left = null;
		this.data = data;
		this._parent = null;
	}

	public Node(Node left, Node right, Visitor tmpVisitor) {
		this._left = left;
		this._right = right;
		this.data = tmpVisitor;
	}

	@Override
	public int compareTo(Node o) {
		Visitor v1 = this.data;
		Visitor v2 = o.data;

		return v1.compareTo(v2);
	}

	public Boolean isLeaf() {
		return !data._data.equals("__LEAF__");
	}

	@Override
	public String toString() {
		return this.data.toString();
	}

}
