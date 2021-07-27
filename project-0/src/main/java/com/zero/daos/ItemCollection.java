package com.zero.daos;

import java.util.ArrayList;
import java.util.List;
import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;

public class ItemCollection implements ItemDao {

	private static List<Item> items;
	
	public ItemCollection(){
		items = new ArrayList<>();
		items.add(new Item("Baseball", "$20"));
		items.add(new Item("Bat", "$5"));
	}

	@Override
	public Item getItem(String itemName) throws ItemNotFoundException{
		for(Item u: items) {
			if(itemName.equals(u.getItemName())) {
				return u;
			}
		}
		throw new ItemNotFoundException();
	}

	@Override
	public boolean addItem(Item item) {
		return items.add(item);
	}
	
	
}