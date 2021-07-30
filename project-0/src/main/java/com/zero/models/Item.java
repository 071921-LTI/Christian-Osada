package com.zero.models;

import java.io.Serializable;

public class Item implements Serializable{

	private int itemId;
	private String itemName;
	private int itemPrice;
	private String ownedBy;
	
	public Item(int itemId) {
		super();
		this.itemId = itemId;
	}
	public Item(int itemId, String itemName, int itemPrice, String ownedBy) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.ownedBy = ownedBy;
	}
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return itemPrice;
	}
	public void setPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getOwnedBy() {
		return ownedBy;
	}
	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + itemId;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + itemPrice;
		result = prime * result + ((ownedBy == null) ? 0 : ownedBy.hashCode());
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
		if (itemId != other.itemId)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemPrice != other.itemPrice)
			return false;
		if (ownedBy == null) {
			if (other.ownedBy != null)
				return false;
		} else if (!ownedBy.equals(other.ownedBy))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", availability="
				+ ownedBy + "]";
	}
}
