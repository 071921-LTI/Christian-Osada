package com.zero.daos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.zero.exceptions.OfferNotFoundException;
import com.zero.models.Offer;
import com.zero.services.OfferService;
import com.zero.services.OfferServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

	@Mock
	private OfferDao od;
	
	@InjectMocks
	private OfferServiceImpl es;
	
	@Test
	public void getExistantOffereByIdTest() {
		Offer expected = new Offer(1, 1, 1, "N/A");
		Offer actualResult = new Offer (1, 3, 4, "N/A");
		try {
			Mockito.when(es.getOfferById(1)).thenReturn(new Offer(1, 1, 1, "N/A"));
			actualResult = es.getOfferById(1);
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
	@Test
	public void getNonExistantOffereByIdTest() {
		Offer expected = new Offer(1, 1, 1, "N/A");
		Offer actualResult = new Offer (1, 3, 4, "N/A");
		try {
			Mockito.when(es.getOfferById(1)).thenReturn(null);
			actualResult = es.getOfferById(1);
		} catch (OfferNotFoundException e) {
		}
		assertEquals(expected, actualResult);
	}
}
