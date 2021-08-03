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

import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Offer;
import com.zero.services.OfferServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

	@Mock
	private OfferDao od;
	
	@InjectMocks
	private OfferServiceImpl es;
	
	@Test
	public void getExistantOfferByIdTest() {
		Offer expected = new Offer(1, 1, 1, "N/A");
		Offer actualResult = new Offer (1, 1, 1, "N/A");
		try {
			Mockito.when(es.getOfferById(1)).thenReturn(actualResult);
			actualResult = es.getOfferById(1);
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantOfferByIdTest() {
		Offer expected = new Offer(1, 1, 1, "N/A");
		Offer actualResult = new Offer (1, 3, 4, "N/A");
		try {
			Mockito.when(es.getOfferById(1)).thenReturn(actualResult);
			actualResult = es.getOfferById(1);
		} catch (OfferNotFoundException e) {
		}
		assertNotEquals(expected, actualResult);
	}
	
	@Test
	public void getExistantOffers() {
		Offer expected1 = new Offer(1, 1, 1, "N/A");
		Offer expected2 = new Offer(2, 1, 1, "N/A");
		List<Offer> expectedOffers = new ArrayList<>();
		expectedOffers.add(expected1);
		expectedOffers.add(expected2);
		
		Offer actualResult1 = new Offer (1, 1, 1, "N/A");
		Offer actualResult2 = new Offer(2, 1, 1, "N/A");
		List<Offer> actualOffers = new ArrayList<>();
		actualOffers.add(actualResult1);
		actualOffers.add(actualResult2);
		Mockito.when(es.getOffers()).thenReturn(actualOffers);
		actualOffers = es.getOffers();
		assertEquals(expectedOffers, actualOffers);
	}
	@Test
	public void getNonExistantOffers() {
		Offer expected1 = new Offer(1, 1, 1, "N/A");
		Offer expected2 = new Offer(2, 1, 1, "N/A");
		List<Offer> expectedOffers = new ArrayList<>();
		expectedOffers.add(expected1);
		expectedOffers.add(expected2);
		
		Offer actualResult1 = new Offer (1, 1, 1, "N/A");
		Offer actualResult2 = new Offer(3, 1, 1, "N/A");
		List<Offer> actualOffers = new ArrayList<>();
		expectedOffers.add(actualResult1);
		expectedOffers.add(actualResult2);
		Mockito.when(es.getOffers()).thenReturn(actualOffers);
		actualOffers = es.getOffers();
		assertNotEquals(expectedOffers, actualOffers);
	}
	
	
	@Test
	public void getAddOffer() {
		boolean expected = true;
		Offer offer = new Offer (1, 1, 1, "N/A");
		Mockito.when(es.addOffer(offer)).thenReturn(true);
		boolean actualResult = es.addOffer(offer);
		assertEquals(expected, actualResult);
	}
	
	
	@Test
	public void deleteExistantOffer() {
		int expected = -1;
		int actualResult = -1;
		try {
			Mockito.when(es.deleteOffer(1)).thenReturn(actualResult);
			actualResult = es.deleteOffer(1);
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void deleteNonExistantOffer() {
		int expected = 0;
		int actualResult = 0;
		try {
			Mockito.when(es.deleteOffer(1)).thenReturn(actualResult);
			actualResult = es.deleteOffer(1);
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}


	@Test
	public void updateExistantOffer() {
		boolean expected = true;
		boolean actualResult = false;
		try {
			Mockito.when(es.updateOfferStatus(1, "Test")).thenReturn(true);
			actualResult = es.updateOfferStatus(1, "Test");
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void updateNonExistantOffer() {
		boolean expected = false;
		boolean actualResult = true;
		try {
			Mockito.when(es.updateOfferStatus(1000, "Test")).thenReturn(false);
			actualResult = es.updateOfferStatus(1000, "Test");
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
}
