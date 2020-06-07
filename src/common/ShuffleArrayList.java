package common;

import java.util.ArrayList;

public class ShuffleArrayList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5943061972981873216L;

	public ShuffleArrayList(int size) {
		super(size);
	}

	public void shuffle() {
		for (int i = 0; i < this.size(); i++) {
			int target = (int) (this.size()*Math.random());
			E image = this.get(i);
			this.set(i, this.get(target));
			this.set(target, image);
		}
	}
	
}
