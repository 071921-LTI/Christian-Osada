package com.zero.services;

import java.util.List;
import java.util.ArrayList;
import com.zero.daos.OfferCollection;
import com.zero.daos.OfferDao;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Offer;

public class OfferServiceImpl implements OfferService{
	private OfferDao of = new OfferCollection();

	@Override
	public Offer getOfferById(int id) throws OfferNotFoundException {
		return of.getOfferById(id);
	}
	@Override
	public List<Offer> getOffers() {
		return of.getOffers();
	}
	@Override
	public boolean addOffer(Offer item) {
		return of.addOffer(item);
	}
	@Override
	public int deleteOffer(int id) throws OfferNotFoundException {
		return of.deleteOffer(id);
	}
	@Override
	public boolean updateOfferStatus(int id, String status) throws OfferNotFoundException {
		return of.updateOfferStatus(id, status);
	}
}
