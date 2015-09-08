package br.com.geofusion.service;

import javax.ws.rs.core.Response;

public interface GeocodingService {
	public Response reverse(final Double longitude, final Double latitude);
	public Response geocode(final String streetName, final Integer houseNumber, final String neighborhood, final String municipality, final String state, final String postalCode, final String country);
}
