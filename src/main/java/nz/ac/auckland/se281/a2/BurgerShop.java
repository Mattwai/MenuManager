package nz.ac.auckland.se281.a2;

import java.util.ArrayList;

import nz.ac.auckland.se281.a2.cli.Menu.SIZE;
import nz.ac.auckland.se281.a2.cli.MessagesCLI;

public class BurgerShop {
	// list of items ordered in cart
	private ArrayList<MenuOrder> order = new ArrayList<>();
	private ArrayList<SIZE> drinkSize = new ArrayList<>();
	private ArrayList<SIZE> snackSize = new ArrayList<>();

	public BurgerShop() {
	}

	/**
	 * Add a burger in the cart
	 * 
	 * @param name
	 * @param price
	 */
	public void addBurger(String name, float price) {
		// TODO TASK1
		// adds a burger to customers order
		Burger burger = new Burger(name, price);
		order.add(burger);

	}

	/**
	 * add a snack in the cart, if size is L the price should be incremented by $3
	 * if the size is XL the price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 * 
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addSnack(String name, float price, SIZE size) {
		// TODO TASK1
		// adds a snack to customers order
		Snack snack = new Snack(name, price, size);
		order.add(snack);

	}

	/**
	 * 
	 * add a drink in the cart
	 * 
	 * if size is L the price should be incremented by $3 if the size is XL the
	 * price should be incremented by $4 (@see
	 * nz.ac.auckland.se281.a2.cli.Menu.SIZE)
	 * 
	 *
	 * @param name
	 * @param price
	 * @param size
	 */
	public void addDrink(String name, float price, SIZE size) {
		// TODO TASK1
		// adds a drink to customers order
		Drink drink = new Drink(name, price, size);
		order.add(drink);
	}

	/**
	 * print the content of the cart, or print MessagesCLI.CART_EMPTY if the cart is
	 * empty
	 *
	 *
	 */
	public void showCart() {
		// TODO TASK1
		// initialise start of cart
		int itemNumber = 0;
		float totalPrice = 0;

		// check if cart is empty
		if (order.size() == 0) {
			System.out.println("Cart is empty");
		} else {
			// iterate through the items in order
			for (MenuOrder i : order) {
				if (i instanceof MenuItems) {
					MenuItems item = (MenuItems) i;

					// check type of item
					if (i instanceof Burger) {
						String cart = String.format("%d - %s: $%.2f", itemNumber, item.getName(), item.getPrice());
						totalPrice = totalPrice + item.getPrice();
						itemNumber++;
						System.out.println(cart);
					} else if (i instanceof Snack) {
						String cart = String.format("%d - %s (%s): $%.2f", itemNumber, item.getName(), item.getSize(),
								item.getPrice());
						totalPrice = totalPrice + item.getPrice();
						itemNumber++;
						System.out.println(cart);
					} else if (i instanceof Drink) {
						String cart = String.format("%d - %s (%s): $%.2f", itemNumber, item.getName(), item.getSize(),
								item.getPrice());
						totalPrice = totalPrice + item.getPrice();
						itemNumber++;
						System.out.println(cart);
					}
				}
				// if not an individual item, must be a combo
				else {
					Combo comboItems = (Combo) i;
					String cart = String.format("%d - COMBO : (%s, %s (%s), %s (%s)): $%.2f", itemNumber,
							comboItems.getNameBurger(), comboItems.getNameSnack(), comboItems.getSize(),
							comboItems.getNameDrink(), comboItems.getSize(), comboItems.getComboPrice());
					totalPrice = totalPrice + comboItems.getComboPrice();
					itemNumber++;
					System.out.println(cart);
				}
			}
			// apply discount based on total price of order
			if (totalPrice >= 100) {
				totalPrice = (totalPrice * (float) 0.75);
				System.out.println("You are spending $100 or more, we applied 25% discount!");
			}
			// print out total price
			String total = String.format("Total: $%.2f", totalPrice);
			System.out.println(total);
		}

	}

	/**
	 * add a combo in the cart.
	 * 
	 * The price of a combo is the sum of all the items, but the drink would be half
	 * price. Note that in a combo, both snacks and drinks have the same size, and
	 * the combo price must consider the size (@see addSnack and addDrink methods).
	 * 
	 * @param nameBurger
	 * @param priceBurger
	 * @param nameSnack
	 * @param priceSnack
	 * @param nameDrink
	 * @param priceDrink
	 * @param size
	 */
	public void addCombo(String nameBurger, float priceBurger, String nameSnack, float priceSnack, String nameDrink,
			float priceDrink, SIZE size) {
		// TODO TASK2
		// add combo to order
		Combo combo = new Combo(nameBurger, priceBurger, nameSnack, priceSnack, nameDrink, priceDrink, size);
		order.add(combo);
	}

	/**
	 * remove the item in the cart specified by the position posCart. Note that the
	 * position of the cart start from zero. if postCart is invalid: posCart < 0 OR
	 * posCart >= size of the cart the method prints
	 * MessagesCLI.NOT_VALID_CART_POSITION
	 * 
	 * @param posCart
	 */
	public void removeItem(int posCart) {
		// TODO TASK3
		// remove specified item in cart
		if (posCart < 0 || posCart >= order.size()) {
			MessagesCLI.NOT_VALID_CART_POSITION.printMessage();
		} else {
			order.remove(posCart);
			clearSize();
		}
	}

	/**
	 * removes all elements in the cart
	 */
	public void clearCart() {
		// TODO TASK3
		// remove all items from cart
		order.clear();
	}

	/**
	 * This method confirms the order, showing the estimated pick up time. It also
	 * give a warning if there are missing opportunities for COMBO menus
	 * 
	 */
	public void confirmOrder() {
		// TODO TASK4
		// initalise time and items in cart
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		int totalSeconds = 0;
		int burgers = 0;
		int snacks = 0;
		int drinks = 0;

		// check if there are items in cart
		if (order.size() == 0) {
			MessagesCLI.ORDER_INVALID_CART_EMPTY.printMessage();
		} else {
			clearSize();
			// iterate through items to order in cart
			for (MenuOrder i : order) {
				if (i instanceof MenuItems) {
					// add to record how many items of each type
					MenuItems item = (MenuItems) i;
					if (i instanceof Burger) {
						burgers++;
					} else if (i instanceof Snack) {
						snackSize.add(item.getSize());
						snacks++;

					} else if (i instanceof Drink) {
						drinkSize.add(item.getSize());
						drinks++;
					}
				} else if (i instanceof Combo) {
					burgers++;
					snacks++;
					drinks++;
				}

			}
			// check if there is a potential combo in the cart
			// potential order is if there is a burger, and snack and drink that are the
			// same size
			if (burgers > 0 && snackSize.contains(SIZE.L) && drinkSize.contains(SIZE.L)) {
				MessagesCLI.MISSED_COMBO.printMessage();
			} else if (burgers > 0 && snackSize.contains(SIZE.XL) && drinkSize.contains(SIZE.XL)) {
				MessagesCLI.MISSED_COMBO.printMessage();
			} else if (burgers > 0 && snackSize.contains(SIZE.M) && drinkSize.contains(SIZE.M)) {
				MessagesCLI.MISSED_COMBO.printMessage();
			} else {
				// print cart
				showCart();
				// calculate the total time will take for order to be ready
				if (burgers > 0) {
					totalSeconds = totalSeconds + (5 * 60);
					for (int i = 1; i < burgers; i++) {
						totalSeconds = totalSeconds + 60;
					}
				}
				if (snacks > 0) {
					totalSeconds = totalSeconds + (3 * 60);
					for (int i = 1; i < snacks; i++) {
						totalSeconds = totalSeconds + 30;
					}
				}
				if (drinks > 0) {
					totalSeconds = totalSeconds + 45;
					for (int i = 1; i < drinks; i++) {
						totalSeconds = totalSeconds + 15;
					}
				}
				// convert total seconds into hours, minutes, seconds
				// code adapted from https :// stackoverflow.com/a/6118983
				hours = totalSeconds / 3600;
				minutes = (totalSeconds % 3600) / 60;
				seconds = totalSeconds % 60;

				// print the waiting time for order
				String time = String.format("%s%d hours %d minutes %d seconds",
						MessagesCLI.ESTIMATE_WAITING_TIME.getMessage(), hours, minutes, seconds);
				System.out.println(time);
				// clear the cart once order has been received
				clearCart();
			}
		}
	}

	public void clearSize() {
		drinkSize.clear();
		snackSize.clear();
	}
}
