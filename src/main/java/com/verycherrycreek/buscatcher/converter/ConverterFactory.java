/*-------------------------------------------------------------------------
 *
 * Author: Scott Kilker        
 *
 *-------------------------------------------------------------------------*/
package com.verycherrycreek.buscatcher.converter;

import com.verycherrycreek.buscatcher.datastore.Datastore;
import com.verycherrycreek.buscatcher.datastore.DatastoreI;
import com.verycherrycreek.buscatcher.datastore.DatastoreProperties.DATASTORE_TECHNOLOGY;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthority;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityI;
import com.verycherrycreek.buscatcher.transportationauthority.TransitAuthorityProperties.TRANSIT_AUTHORITY;

public class ConverterFactory {
	
	static public ConverterI createConverter(TransitAuthorityI pTransitAuthority, DatastoreI pDatastore) {
		ConverterI retConverter = null;
		
		if (pDatastore.getDatastoreTechnology() == DATASTORE_TECHNOLOGY.MONGODB  && 
			pTransitAuthority.getTransitAuthority() == TRANSIT_AUTHORITY.RTD) {
			retConverter = new RTDtoMongoDBConverter(pTransitAuthority, pDatastore);
		}
		return retConverter;
	}

}