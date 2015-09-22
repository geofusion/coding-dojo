package br.com.geofusion.geocoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("geocodingRepository")
public class GeocodingDAO implements GeocodingRepository {

	private DataSource dataSource;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public GeocodingDAO(@Qualifier("myDataSource") final DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Address reverse(Double lng, Double lat) {
		Address address = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM ADDRESS WHERE LONGITUTE = ? AND LATITUDE  = ?");
			preparedStatement.setDouble(1, lng);
			preparedStatement.setDouble(2, lat);
			
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {				
				address = new Address(resultSet.getDouble("LONGITUDE"),
						    resultSet.getDouble("LATITUDE"), 
						    resultSet.getString("STREETNAME"), 
						    resultSet.getString("MUNICIPALITY"),
						    resultSet.getString("STATE"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Ocorreu um erro na geocodificacao reversa", e);
		} finally {
			close(connection, preparedStatement, resultSet);
		}
		
		return address;
	}

	private void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		try {
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Address> geocode(String streetName,String municipality, String state) {
		List<Address> listAddress = new ArrayList<Address>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String query = "SELECT * FROM ADDRESS WHERE streetName = ? and municipality = ? and state = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, streetName);
			preparedStatement.setString(2, municipality);
			preparedStatement.setString(3, state);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Address address = new Address(resultSet.getDouble("LONGITUDE"),
					    resultSet.getDouble("LATITUDE"), 
					    resultSet.getString("STREETNAME"), 
					    resultSet.getString("MUNICIPALITY"),
					    resultSet.getString("STATE"));
				listAddress.add(address);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Ocorreu um erro na geocodificacao", e);
		} finally {
			close(connection, preparedStatement, resultSet);
		}
		
		return listAddress;
		
	}

}
