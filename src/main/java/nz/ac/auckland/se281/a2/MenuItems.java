package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;

public class MenuItems extends MenuOrder {

	// record instance of item on the menu
	public MenuItems(String name, float price, SIZE size) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.price = price;
		this.size = size;
		// update price based on size of drink and snack
		if (size == SIZE.XL) {
			this.price = price + 4;
		} else if (size == SIZE.L) {
			this.price = price + 3;
		}
	}

	// method to get items information
	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

}
