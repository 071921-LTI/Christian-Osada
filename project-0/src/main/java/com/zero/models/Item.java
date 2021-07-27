package com.zero.models;

import java.io.Serializable;

public class Item implements Serializable{

	private String itemName;
	private String itemPrice;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(String itemName, String itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPrice() {
		return itemPrice;
	}
	public void setPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((itemPrice == null) ? 0 : itemPrice.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice == null) {
			if (other.itemPrice != null)
				return false;
		} else if (!itemPrice.equals(other.itemPrice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "[Item Name = " + itemName + ", Item Price = " + itemPrice + "]";
	}
	
	public String toFileString() {
		return itemName + ":" + itemPrice;
	}
}
