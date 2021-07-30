package com.zero.daos;

import java.util.ArrayList;
import java.util.List;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Offer;

public class OfferCollection implements OfferDao {

	private static List<Offer> offers;
	private OfferDao of = new OfferPostgres();
	
	public OfferCollection(){
		offers = new ArrayList<>();
	}

	public Offer getOfferById(int id) throws OfferNotFoundException {
		Offer offer = of.getOfferById(id);
		if(offer == null) {
			throw new OfferNotFoundException();
		}
		return offer;
	}
	@Override
	public List<Offer> getOffers() {
		offers = of.getOffers();
		return offers;
	}
	public boolean addOffer(Offer offer) {
		return of.addOffer(offer);
	}
	public int deleteOffer(int id) throws OfferNotFoundException {
		if(of.getOfferById(id).getOfferId() == id) {
			return of.deleteOffer(id);
		}
		throw new OfferNotFoundException();
	}
	public boolean updateOfferStatus(int id, String status) throws OfferNotFoundException {
		return of.updateOfferStatus(id, status);
	}
}