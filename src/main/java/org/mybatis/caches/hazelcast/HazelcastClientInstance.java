package org.mybatis.caches.hazelcast;

import java.util.Map;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastClientInstance {
    private final HazelcastInstance client;

    private HazelcastClientInstance() {
        client = HazelcastClient.newHazelcastClient();
    }

    public static HazelcastClientInstance getInstance() {
        return ClientInstanceHolder.INSTANCE;
    }

    public HazelcastInstance getClient() {
        return client;
    }

    /**
     * @param name Name of the distributed map 
     */
    public <K, V> Map<K, V> getMap(String name) {
        return client.getMap(name);
    }

    private static class ClientInstanceHolder {
        private static final HazelcastClientInstance INSTANCE = new HazelcastClientInstance();
    }
}
