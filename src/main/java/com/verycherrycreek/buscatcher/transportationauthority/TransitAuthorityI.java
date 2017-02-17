/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;

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
	public FeedMessage getVehiclePositions();
	
	
	/**
	 * 
	 * Get all Trip Positions from the Transit Authority
	 * 
	 * @return FeedMessage Contains All current Trip Updates available
	 */
	public FeedMessage getTripUpdates();
	
}
