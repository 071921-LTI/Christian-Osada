package com.zero.services;

import java.util.List;
import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;

public interface ItemService {
	
	//Checks if inputed item exists
	public abstract Item getItemById(int id) throws ItemNotFoundException;
	public abstract Item getItemByName(String name) throws ItemNotFoundException;
	public abstract List<Item> getItems();
	public abstract boolean addItem(Item item);
	public abstract int deleteItem(int id) throws ItemNotFoundException;
	public abstract boolean updateItemOwner(int id, int ownerId) throws ItemNotFoundException;
}
