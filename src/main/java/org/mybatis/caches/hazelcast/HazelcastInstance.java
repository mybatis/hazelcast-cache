package org.mybatis.caches.hazelcast;

import java.util.Map;

import com.hazelcast.core.Hazelcast;

public class HazelcastInstance {
    private final com.hazelcast.core.HazelcastInstance hazelcastInstance;

    private HazelcastInstance() {
        hazelcastInstance = Hazelcast.newHazelcastInstance();
    }

    public static HazelcastInstance getInstance() {
        return HazelcastInstanceHolder.INSTANCE;
    }

    public com.hazelcast.core.HazelcastInstance getHazelcastInstance() {
        return hazelcastInstance;
    }

    /**
     * @param name Name of the distributed map 
     */
    public <K, V> Map<K, V> getMap(String name) {
        return hazelcastInstance.getMap(name);
    }

    private static class HazelcastInstanceHolder {
        private static final HazelcastInstance INSTANCE = new HazelcastInstance();
    }
}
