package com.verycherrycreek.buscatcher.datastore;

import com.verycherrycreek.buscatcher.datastore.DatastoreProperties.DATASTORE_TECHNOLOGY;

public class DatastoreFactory {
	
	static public DatastoreI createDatastore(DATASTORE_TECHNOLOGY pDatastore, String pResourceName) {
		DatastoreI retDatastore = null;
		if (pDatastore.MONGODB == DATASTORE_TECHNOLOGY.MONGODB) {
			retDatastore = new MongoDBDatastore(pDatastore, pResourceName);
		}
		return retDatastore;
	}

}
