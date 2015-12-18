package fr.unice.polytech.al.drone.qrcode.infrastructure;

import fr.unice.polytech.al.drone.qrcode.utils.StorageTypeEnum;

/**
 * Created by camille on 06/11/15.
 *
 * This class aims to access easily StorageManager's instances.
 * Design pattern used to design this class is a combination of Factory
 * and Singleton patterns.
 */
public class StorageManagerFactory {

    private static StorageManager storageManager = null;

    /**
     * This allows to get a specific store manager instance.
     * @param storageType
     * @return
     * @throws StorageManagerException
     */
    public static StorageManager getStorageManager(StorageTypeEnum storageType)
            throws StorageManagerException {

        switch (storageType){
            case JSON:
                if(storageManager == null || !(storageManager instanceof JsonStorageManager)){
                    storageManager = new JsonStorageManager();
                }
                return storageManager;

            default:
                throw new StorageManagerException("Not allowed storage type : request abort");
        }
    }

    /**
     * This allows to reset storage manager instance
     */
    public static void reset(){
        storageManager = null;
    }

}
