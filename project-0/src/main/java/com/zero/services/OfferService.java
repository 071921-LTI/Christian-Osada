package com.zero.services;

import java.util.List;

import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Offer;

public interface OfferService {
	
	//Checks if inputed offers exists
	public abstract Offer getOfferById(int id) throws OfferNotFoundException;
	public abstract List<Offer> getOffers();
	public abstract boolean addOffer(Offer offer);
	public abstract int deleteOffer(int id) throws OfferNotFoundException;
	public abstract boolean updateOfferStatus(int offerId, String status) throws OfferNotFoundException;
}
