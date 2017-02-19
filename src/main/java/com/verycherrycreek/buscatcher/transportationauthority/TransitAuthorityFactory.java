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
	
	static public TransitAuthorityI createTransitAuthority(TRANSIT_AUTHORITY pTransitAuthority, String pResourceName) {
		TransitAuthorityI retTransitAuthority = null;
		if (pTransitAuthority.RTD == TRANSIT_AUTHORITY.RTD) {
			retTransitAuthority = new RTDTransitAuthority(pTransitAuthority, pResourceName);
		}
		return retTransitAuthority;
	}
}
