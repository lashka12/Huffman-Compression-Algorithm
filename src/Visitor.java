
public class Visitor implements Comparable<Visitor> {

	public String _data;
	public Integer _prob;

	public Visitor(Integer prob, String data) {
		this._data = data;
		this._prob = prob;
	}

	@Override
	public int compareTo(Visitor o) {
		return this._prob.compareTo(o._prob);
	}

	@Override
	public String toString() {
		return this._prob + " " + this._data + "";
	}

}
