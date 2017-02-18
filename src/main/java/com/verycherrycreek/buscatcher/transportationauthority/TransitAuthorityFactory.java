/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.transportationauthority;

import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

/**
 * @author skilker
 *
 */
public class TransitAuthorityFactory {
	
	static public TransitAuthorityI createTransitAuthority(TRANSIT_AUTHORITY pTransitAuthority, String pConfigFileName) {
		TransitAuthorityI TA = null;
		if (pTransitAuthority.RTD == TRANSIT_AUTHORITY.RTD) {
			TA = new TransitAuthorityRTD(pTransitAuthority, pConfigFileName);
		}
		return TA;
	}
}
