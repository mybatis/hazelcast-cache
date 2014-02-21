package org.mybatis.caches.hazelcast;

/**
 *  Cache adapter for Hazelcast using "Hazelcast Client" API {@link com.hazelcast.client.HazelcastClient}. 
 *  
 *  This means that the HazelcastClientCache is not a member of the cluster.
 */
public class HazelcastClientCache extends AbstractHazelcastCache {

    /**
     * Instantiates a new Hazelcast cache for the specified id.
     *
     * @param id the cache id.
     */
    public HazelcastClientCache(String id) {
        super(id, HazelcastClientInstance.getInstance().<Integer, Object> getMap(id));
    }

}
