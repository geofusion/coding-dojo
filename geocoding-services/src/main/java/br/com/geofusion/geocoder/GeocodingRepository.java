package br.com.geofusion.geocoder;

import java.util.List;

public interface GeocodingRepository {

	public Address reverse(Double lng, Double lat);
	
	public List<Address> geocode(String streetName, String municipality, String state);
}
