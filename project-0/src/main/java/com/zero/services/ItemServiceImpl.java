package com.zero.services;

import java.util.List;

import com.zero.daos.ItemCollection;
import com.zero.daos.ItemDao;
import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;

public class ItemServiceImpl implements ItemService{
	private ItemDao itd = new ItemCollection();

	@Override
	public Item getItemById(int id) throws ItemNotFoundException {
		return itd.getItemById(id);
	}
	@Override
	public List<Item> getItems() {
		return itd.getItems();
	}
	@Override
	public boolean addItem(Item item) {
		return itd.addItem(item);
	}
	@Override
	public int deleteItem(int id) throws ItemNotFoundException {
		return itd.deleteItem(id);
	}
	@Override
	public boolean updateItemOwner(int id, int ownerId) throws ItemNotFoundException {
		return itd.updateItemOwner(id, ownerId);
	}
}
