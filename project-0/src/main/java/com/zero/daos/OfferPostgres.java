package com.zero.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.zero.models.Offer;
import com.zero.util.ConnectionUtil;

public class OfferPostgres implements OfferDao {

	@Override
	public Offer getOfferById(int id) {//Retrieve a offer from the database offer table based on the given ID
		String sql = "select * from offers where offer_id = ?";
		Offer offer = null;
		try(Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id); // 1 refers to first ? to parameterize
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int offerId = rs.getInt("offer_id");
				int offererId = rs.getInt("offerer_id");
				int itemId = rs.getInt("item_id");
				String status = rs.getString("status");
				offer = new Offer(offerId, offererId, itemId, status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offer;
	}

	@Override
	public List<Offer> getOffers() {//Retrieve an array list of all the rows on the offers table from the database
		List<Offer> offers = new ArrayList<>();
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			String sql = "select * from offers;";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				int offerId = rs.getInt("offer_id");
				int offererId = rs.getInt("offerer_id");
				int itemId = rs.getInt("item_id");
				String status = rs.getString("status");
				Offer offer = new Offer(offerId, offererId, itemId, status);

				offers.add(offer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return offers;
	}

	@Override
	public boolean addOffer(Offer offer) {//Add an offer to the offers database table
//		int id = -1;
		String sql = "insert into offers (offerer_id, item_id, status) values (?,?,?) returning offer_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, offer.getOffererId());
			ps.setInt(2, offer.getItemIdOffer());
			ps.setString(3, offer.getStatus());
			
			ResultSet rs = ps.executeQuery();
			
//			if(rs.next()) {
//				id = rs.getInt("offer_id");
//			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateOfferStatus(int offerId, String status) {//Update the status of an offer on the offers database table
		// TODO Auto-generated method stub
		String sql = "update offers set status = ? where offer_id = ?;";
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, offerId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int deleteOffer(int id) {//Delete an offer from the offers table on the database
		String sql = "delete from offers where offer_id = ?;";
		int rowsChanged = -1;
		
		try (Connection con = ConnectionUtil.getConnectionFromEnv()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsChanged;
	}
}
