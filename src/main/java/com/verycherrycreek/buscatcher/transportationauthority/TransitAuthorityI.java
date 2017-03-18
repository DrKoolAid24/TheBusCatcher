/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import java.io.IOException;
import java.net.MalformedURLException;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public interface TransitAuthorityI {

	/**
	 * 
	 * Get all Vehicle Positions from the Transit Authority
	 * 
	 * @return FeedMessage Contains All current Vehicle Positions available
	 */
	public FeedMessage getVehiclePositions() throws MalformedURLException,IOException;
	
	
	/**
	 * 
	 * Get all Trip Positions from the Transit Authority
	 * 
	 * @return FeedMessage Contains All current Trip Updates available
	 */
	public FeedMessage getTripUpdates() throws MalformedURLException,IOException;
	
	public void setUsername(String pUsername);
	
	public void setPassword(String pPassword);
	
	public TRANSIT_AUTHORITY getTransitAuthority();
}
