package com.zero.models;

public class Offer {
	
	private int offerId;
	private int offererId;
	private int itemIdOffer;
	private String status;
	
	public Offer(int offerId) {
		super();
		this.offerId = offerId;
	}
	public Offer(int offerId, int offererId, int itemIdOffer, String status) {
		super();
		this.offerId = offerId;
		this.offererId = offererId;
		this.itemIdOffer = itemIdOffer;
		this.status = status;
	}

	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getOffererId() {
		return offererId;
	}
	public void setOffererId(int offererId) {
		this.offererId = offererId;
	}
	public int getItemIdOffer() {
		return itemIdOffer;
	}
	public void setItemIdOffer(int itemIdOffer) {
		this.itemIdOffer = itemIdOffer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + itemIdOffer;
		result = prime * result + offerId;
		result = prime * result + offererId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (itemIdOffer != other.itemIdOffer)
			return false;
		if (offerId != other.offerId)
			return false;
		if (offererId != other.offererId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Offer Id = " + offerId + ", Offerer Id = " + offererId + ", Item Id = " + itemIdOffer
			 + ", Offer Status = " + status;
	}
}
