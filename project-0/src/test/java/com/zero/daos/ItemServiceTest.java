package com.zero.daos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;
import com.zero.services.ItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

	@Mock
	private ItemDao itd;
	
	@InjectMocks
	private ItemServiceImpl is;
	
	@Test
	public void getExistantItemByIdTest() {
		Item expected = new Item(1, "clock", 50, 0);
		Item actualResult = null;
			try {
				Mockito.when(itd.getItemById(1)).thenReturn(new Item (1, "clock", 50, 0));
				actualResult = is.getItemById(1);
			} catch (ItemNotFoundException e) {
			}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantItemByIdTest() {
		Item expected = new Item(1, "clock", 50, 0);
		Item actualResult = null;
			try {
				Mockito.when(itd.getItemById(1)).thenReturn(new Item (2, "wristwatch", 25, 0));
				actualResult = is.getItemById(1);
			} catch (ItemNotFoundException e) {
			}
		assertNotEquals(expected, actualResult);
	}
	
	
	@Test
	public void getExistantItems() {
		List<Item> expectedItems = new ArrayList<>();
		expectedItems.add(new Item(1, "clock", 50, 0));
		expectedItems.add(new Item(2, "wristwatch", 25, 0));
		
		List<Item> returnThis = new ArrayList<>();
		returnThis.add(new Item(1, "clock", 50, 0));
		returnThis.add(new Item(2, "wristwatch", 25, 0));
		
		List<Item> actualItems = new ArrayList<>();
		Mockito.when(itd.getItems()).thenReturn(returnThis);
		actualItems = is.getItems();
		assertEquals(expectedItems, actualItems);
	}
	@Test
	public void getNonExistantItems() {
		List<Item> expectedItems = new ArrayList<>();
		expectedItems.add(new Item(1, "clock", 50, 0));
		expectedItems.add(new Item (2, "wristwatch", 25, 0));
		
		List<Item> returnThis = new ArrayList<>();
		returnThis.add(new Item(1, "clock", 50, 0));
		returnThis.add(new Item (4, "chair", 100, 0));
		
		List<Item> actualItems = new ArrayList<>();
		Mockito.when(itd.getItems()).thenReturn(actualItems);
		actualItems = is.getItems();
		assertNotEquals(expectedItems, actualItems);
	}
	
	
	@Test
	public void getAddItem() {
		boolean expected = true;
		Item item = new Item(1, "clock", 50, 0);
		Mockito.when(itd.addItem(item)).thenReturn(true);
		boolean actualResult = is.addItem(item);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantOffer() {
		int expected = -1;
		int actualResult = 0;
		try {
			Mockito.when(itd.deleteItem(1)).thenReturn(-1);
			actualResult = is.deleteItem(1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantOffer() {
		int expected = 0;
		int actualResult = -1;
		try {
			Mockito.when(itd.deleteItem(1)).thenReturn(0);
			actualResult = is.deleteItem(1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}


	@Test
	public void updateExistantOffer() {
		boolean expected = true;
		boolean actualResult = false;
		try {
			Mockito.when(itd.updateItemOwner(1, 1)).thenReturn(true);
			actualResult = is.updateItemOwner(1, 1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void updateNonExistantOffer() {
		boolean expected = false;
		boolean actualResult = true;
		try {
			Mockito.when(itd.updateItemOwner(1000, 1)).thenReturn(false);
			actualResult = is.updateItemOwner(1000, 1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
}
