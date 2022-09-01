package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;

public abstract class MenuOrder {
	protected String name;
	protected float price;
	protected SIZE size;

	// get size of item
	public SIZE getSize() {
		return size;
	}
}
