/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;


import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.verycherrycreek.buscatcher.VehiclePosition;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public class TransitAuthorityRTD extends TransitAuthority implements TransitAuthorityI {
	
	private String username;
	private String password;
	private String baseUrl;
	private String vehiclePositionApi;
	private String tripUpdateApi;
	private String vehiclePositionUrl;
	private String tripUpdateUrl;
	
	
	/**
	 * @param identifier
	 */
	public TransitAuthorityRTD(TRANSIT_AUTHORITY pIdentifier, String pConfigFileName) {
		super(pIdentifier, pConfigFileName);
		if (super.getConfiguration()) {
			username = props.getProperty(TransitAuthorityProperties.USER_NAME);
			password = props.getProperty(TransitAuthorityProperties.PASSWORD);
			baseUrl = props.getProperty(TransitAuthorityProperties.BASE_URL);
			vehiclePositionApi = props.getProperty(TransitAuthorityProperties.VEHICLE_POSITION_API);
			tripUpdateApi = props.getProperty(TransitAuthorityProperties.TRIP_UPDATE_API);
			vehiclePositionUrl = baseUrl + vehiclePositionApi;
			tripUpdateUrl = baseUrl + tripUpdateApi;
		}
	}

	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI#getVehiclePositions()
	 */
	@Override
	public FeedMessage getVehiclePositions() {

		FeedMessage vehiclePositionFeedMessage = null;
		// Sets the authenticator that will be used by the networking code
	    // when a proxy or an HTTP server asks for authentication.
		Authenticator.setDefault(new CustomAuthenticator(username, password));
		
		URL url = new URL(vehiclePositionUrl);
		vehiclePositionFeedMessage = FeedMessage.parseFrom(url.openStream());		
		
		return vehiclePositionFeedMessage;
	}

	/* (non-Javadoc)
	 * @see com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI#getTripUpdates()
	 */
	@Override
	public FeedMessage getTripUpdates() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	/**
	 * @return the vehiclePositionApi
	 */
	public String getVehiclePositionApi() {
		return vehiclePositionApi;
	}

	/**
	 * @param vehiclePositionApi the vehiclePositionApi to set
	 */
	public void setVehiclePositionApi(String vehiclePositionApi) {
		this.vehiclePositionApi = vehiclePositionApi;
	}

	/**
	 * @return the tripUpdateApi
	 */
	public String getTripUpdateApi() {
		return tripUpdateApi;
	}

	/**
	 * @param tripUpdateApi the tripUpdateApi to set
	 */
	public void setTripUpdateApi(String tripUpdateApi) {
		this.tripUpdateApi = tripUpdateApi;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
