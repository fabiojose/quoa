package net.sourceforge.quoa.example.bean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Sale extends Abstract {
	private static final long serialVersionUID = 2615850325465831905L;

	private double price;
	private double discount;
	private double quantity;
	
	@OneToOne
	private Price item;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Price getItem() {
		return item;
	}

	public void setItem(Price item) {
		this.item = item;
	}
	
}
