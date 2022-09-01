package nz.ac.auckland.se281.a2;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;

public class Combo extends MenuOrder {
	private String nameBurger;
	private float priceBurger;
	private String nameSnack;
	private float priceSnack;
	private String nameDrink;
	private float priceDrink;
	private float comboPrice;

	// record instance of combo
	public Combo(String nameBurger, float priceBurger, String nameSnack, float priceSnack, String nameDrink,
			float priceDrink, SIZE size) {
		this.nameBurger = nameBurger;
		this.priceBurger = priceBurger;
		this.nameSnack = nameSnack;
		this.priceSnack = priceSnack;
		this.nameDrink = nameDrink;
		this.priceDrink = priceDrink / 2;
		this.size = size;
		// update price based on size of drink and snack
		if (size == SIZE.XL) {
			this.priceSnack = priceSnack + 4;
			this.priceDrink = (priceDrink + 4) / 2;
		} else if (size == SIZE.L) {
			this.priceSnack = priceSnack + 3;
			this.priceDrink = (priceDrink + 3) / 2;
		}
		this.comboPrice = priceBurger + this.priceSnack + this.priceDrink;

	}

	// methods to get items in comco information
	public String getNameBurger() {
		return nameBurger;
	}

	public float getPriceBurger() {
		return priceBurger;
	}

	public String getNameSnack() {
		return nameSnack;
	}

	public float getPriceSnack() {
		return priceSnack;
	}

	public String getNameDrink() {
		return nameDrink;
	}

	public float getPriceDrink() {
		return priceDrink;
	}

	public float getComboPrice() {
		return comboPrice;
	}

}
