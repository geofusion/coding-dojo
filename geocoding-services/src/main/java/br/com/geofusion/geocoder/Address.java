package br.com.geofusion.geocoder;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address implements Serializable {

	private static final long serialVersionUID = -3841930097224258676L;

	private Double longitude;
	private Double latitude;
	private String streetName;
	private String municipality;
	private String state;
	
	public Address() {		
	}
	
	public Address(Double longitude, Double latitude, String streetName,
			String municipality, String state) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.streetName = streetName;
		this.municipality = municipality;
		this.state = state;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}


	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
