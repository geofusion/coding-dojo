package br.com.geofusion.geocoder;

import java.util.List;

public interface GeocodingRepository {
	public Address reverse(final Double longitude, 
						  final Double latitude);
	
	public List<Address> geocode(final String streetName, 
								 final Integer houseNumber, 
								 final String neighborhood, 
								 final String municipality, 
								 final String state, 
								 final String postalCode, 
								 final String country);
}
