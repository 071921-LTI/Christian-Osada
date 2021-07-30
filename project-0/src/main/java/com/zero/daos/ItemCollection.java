package com.zero.daos;

import java.util.ArrayList;
import java.util.List;
import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.Item;
import com.zero.models.User;

public class ItemCollection implements ItemDao {

	private static List<Item> items;
	private ItemDao itd = new ItemPostgres();
	
	public ItemCollection(){
		items = new ArrayList<>();
	}

	public Item getItemById(int id) throws ItemNotFoundException {
		Item item = itd.getItemById(id);
		if(item == null) {
			throw new ItemNotFoundException();
		}
		return item;
	}
	@Override
	public List<Item> getItems() {
		items = itd.getItems();
		return items;
	}
	public boolean addItem(Item item) {
		return itd.addItem(item);
	}
	public int deleteItem(int id) throws ItemNotFoundException {
		if(itd.getItemById(id).getItemId() == id) {
			return itd.deleteItem(id);
		}
		throw new ItemNotFoundException();
	}
	public boolean updateItemOwner(int id, int ownerId) throws ItemNotFoundException {
		return itd.updateItemOwner(id, ownerId);
	}
}