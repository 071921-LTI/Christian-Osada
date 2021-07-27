package com.zero.daos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.models.Item;

public class ItemDatabase implements ItemDao {

	File itemFile = new File("src/main/resources/users.txt");

	@Override
	public Item getItem(String item) throws ItemNotFoundException {
		try(BufferedReader reader = new BufferedReader(new FileReader(itemFile))){
			String currentLine = reader.readLine();
			
			while(currentLine != null) {
				String[] itemFields = currentLine.split(":");
				if(itemFields[0].equals(item)) {
					return new Item(itemFields[0], itemFields[1]);
				}
				currentLine = reader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ItemNotFoundException();
	}

	@Override
	public boolean addItem(Item item) {
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(itemFile, true))){
			writer.write("\n" + item.getItemName()+":" + item.getPrice());
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

}
