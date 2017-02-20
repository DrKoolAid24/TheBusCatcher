/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.converter;

import com.verycherrycreek.buscatcher.datastore.DatastoreI;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI;

/**
 * @author skilker
 *
 */
public abstract class Converter {
	
	protected TransitAuthorityI transitAuthority;
	protected DatastoreI datastore;
	
	public Converter(TransitAuthorityI pTransitAuthority, DatastoreI pDatastore) {
		this.transitAuthority = pTransitAuthority;
		this.datastore = pDatastore;
	}
	
	

}
