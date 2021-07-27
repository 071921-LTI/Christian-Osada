package com.zero.daos;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;

public interface ItemDao {
	public abstract Item getItem(String itemName) throws ItemNotFoundException;
	public abstract boolean addItem(Item item);
}
