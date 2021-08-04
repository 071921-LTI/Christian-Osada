package com.zero.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zero.exceptions.ItemNotFoundException;
import com.zero.exceptions.UserNotFoundException;
import com.zero.models.Item;
import com.zero.models.User;
import com.zero.util.ConnectionUtil;

public class ItemPostgres implements ItemDao {
	private static Logger log = LogManager.getRootLogger();
	@Override
	public Item getItemById(int id) {//Retrieve an item from the database item table based on the given ID
		String sql = "select * from items where item_id = ?";
		Item item = null;
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id); // 1 refers to first ? to parameterize
			ResultSet rs = ps.executeQuery();
		
			if(rs.next()) {
				int itemId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				int itemPrice = rs.getInt("item_price");
				int owner = rs.getInt("owned_by");
				item = new Item(itemId, itemName, itemPrice, owner);
			}
		} catch (SQLException e) {
			log.error("Failed to find item with Id " + id);
		}
		return item;
	}
	
	@Override
	public Item getItemByName(String name) throws ItemNotFoundException {//Retrieve an user from the database user table based on the given ID
		String sql = "select * from items where item_name = ?";
		Item item = null;
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name); // 1 refers to first ? to parameterize
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int itemId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				int itemPrice = rs.getInt("item_price");
				int owner = rs.getInt("owned_by");
				item = new Item(itemId, itemName, itemPrice, owner);
			}
		} catch (SQLException e) {
			log.error("Failed to find item with name " + name);
		}
		
		return item;
	}

	@Override
	public List<Item> getItems() {//Retrieve an array list of all the rows on the items table from the database
		List<Item> items = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			String sql = "select * from items;";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int itemId = rs.getInt("item_id");
				String itemName = rs.getString("item_name");
				int itemPrice = rs.getInt("item_price");
				int owner = rs.getInt("owned_by");
				Item item = new Item(itemId, itemName, itemPrice, owner);

				items.add(item);
			}
			
		} catch (SQLException e) {
			log.error("Failed to find any items.");
		}
		return items;
	}

	@Override
	public boolean addItem(Item item) {//Add an item to the items database table
		int id = -1;
		String sql = "insert into items (item_name, item_price) values (?,?) returning item_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, item.getItemName());
			ps.setInt(2, item.getPrice());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt("item_id");
			}
			return true;
		} catch (SQLException e) {
			log.error("Failed to add given item with information " + item);
		}
		return false;
	}
	
	@Override
	public boolean updateItemOwner(int id, int ownerId) {//Update the owner of an item on the items database table
		// TODO Auto-generated method stub
		String sql = "update items set owned_by = ? where item_id = ?;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, ownerId);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			log.error("Failed to update given item with Id " + id + " to owner with id " + ownerId);
		}
		return false;
	}

	@Override
	public int deleteItem(int id) {//Delete an item from the items table on the database
		String sql = "delete from items where item_id = ?;";
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			log.error("Failed to delete item with Id " + id);
		}
		return rowsChanged;
	}
}
