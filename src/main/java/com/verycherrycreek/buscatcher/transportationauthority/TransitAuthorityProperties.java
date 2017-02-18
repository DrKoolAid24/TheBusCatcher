/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;


/**
 * @author skilker
 *
 */
public class TransitAuthorityProperties {

	public static final String USER_NAME = "username";
	public static final String PASSWORD = "password";
	public static final String BASE_URL = "baseurl";
	public static final String VEHICLE_POSITION_API = "vehiclepositionapi";
	public static final String TRIP_UPDATE_API = "tripupdateapi";
	
	// Possible Transit Authority properties values
	public enum TRANSIT_AUTHORITY {
	    RTD,
	    INVALID
	}
	
	static public TRANSIT_AUTHORITY createTransitAuthorityEnum(String pTransitAuthority) {
		TRANSIT_AUTHORITY transitAuthority = TRANSIT_AUTHORITY.INVALID;
		if (pTransitAuthority.equals("RTD")) {
			transitAuthority = TRANSIT_AUTHORITY.RTD;
		}
		return transitAuthority;
	}
}
