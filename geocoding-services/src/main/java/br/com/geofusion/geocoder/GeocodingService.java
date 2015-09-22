package br.com.geofusion.geocoder;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface GeocodingService {
	@GET
	@Path("/reverse")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Address reverse(@QueryParam("lng") final Double lng, @QueryParam("lat") final Double lat);
	
	@GET
	@Path("/geocode")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Address> geocode(@QueryParam("streetName") String streetName,
			               @QueryParam("municipality") String municipality, 
			               @QueryParam("state") String state);
}
