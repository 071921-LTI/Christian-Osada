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
		Item actualResult = new Item (1, "clock", 50, 0);
			try {
				Mockito.when(is.getItemById(1)).thenReturn(actualResult);
				actualResult = is.getItemById(1);
			} catch (ItemNotFoundException e) {
			}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantItemByIdTest() {
		Item expected = new Item(1, "clock", 50, 0);
		Item actualResult = new Item (2, "wristwatch", 25, 0);
			try {
				Mockito.when(is.getItemById(1)).thenReturn(actualResult);
				actualResult = is.getItemById(1);
			} catch (ItemNotFoundException e) {
			}
		assertNotEquals(expected, actualResult);
	}
	
	@Test
	public void getExistantItems() {
		Item expected1 = new Item(1, "clock", 50, 0);
		Item expected2 = new Item (2, "wristwatch", 25, 0);
		List<Item> expectedItems = new ArrayList<>();
		expectedItems.add(expected1);
		expectedItems.add(expected2);
		
		Item actualResult1 = new Item(1, "clock", 50, 0);
		Item actualResult2 = new Item (2, "wristwatch", 25, 0);
		List<Item> actualItems = new ArrayList<>();
		actualItems.add(actualResult1);
		actualItems.add(actualResult2);
		Mockito.when(is.getItems()).thenReturn(actualItems);
		actualItems = is.getItems();
		assertEquals(expectedItems, actualItems);
	}
	@Test
	public void getNonExistantItems() {
		Item expected1 = new Item(1, "clock", 50, 0);
		Item expected2 = new Item (2, "wristwatch", 25, 0);
		List<Item> expectedItems = new ArrayList<>();
		expectedItems.add(expected1);
		expectedItems.add(expected2);
		
		Item actualResult1 = new Item(1, "clock", 50, 0);
		Item actualResult2 = new Item (4, "chair", 100, 0);
		List<Item> actualItems = new ArrayList<>();
		actualItems.add(actualResult1);
		actualItems.add(actualResult2);
		Mockito.when(is.getItems()).thenReturn(actualItems);
		actualItems = is.getItems();
		assertNotEquals(expectedItems, actualItems);
	}
	
	
	@Test
	public void getAddItem() {
		boolean expected = true;
		Item item = new Item(1, "clock", 50, 0);
		Mockito.when(is.addItem(item)).thenReturn(true);
		boolean actualResult = is.addItem(item);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantOffer() {
		int expected = -1;
		int actualResult = -1;
		try {
			Mockito.when(is.deleteItem(1)).thenReturn(actualResult);
			actualResult = is.deleteItem(1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantOffer() {
		int expected = 0;
		int actualResult = 0;
		try {
			Mockito.when(is.deleteItem(1)).thenReturn(actualResult);
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
			Mockito.when(is.updateItemOwner(1, 1)).thenReturn(true);
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
			Mockito.when(is.updateItemOwner(1000, 1)).thenReturn(false);
			actualResult = is.updateItemOwner(1000, 1);
		} catch (ItemNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
}
