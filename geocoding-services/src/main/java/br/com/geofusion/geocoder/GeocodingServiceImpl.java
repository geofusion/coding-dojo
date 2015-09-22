package br.com.geofusion.geocoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("geocodingService")
public class GeocodingServiceImpl implements GeocodingService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private GeocodingRepository geocodingRepository;
	
	@Autowired
	public GeocodingServiceImpl(@Qualifier("geocodingRepository") final GeocodingRepository geocodingRepository) {
		this.geocodingRepository = geocodingRepository;
	}
	
	@Override
	public Address reverse(Double lng, Double lat) {
		logger.info("Lng: " + lng);
		logger.info("Lat: " + lat);
		return geocodingRepository.reverse(lng, lat);
	}

	@Override
	public  List<Address> geocode(String streetName,
			String municipality, String state) {
		
		logger.info("Street Name: " + streetName);
		logger.info("Municipality: " + municipality);
		logger.info("State: " + state);
		
		return geocodingRepository.geocode(streetName, municipality, state);
	}

}
