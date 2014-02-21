package org.mybatis.caches.hazelcast;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

public abstract class AbstractHazelcastCache implements Cache {

    /**
     * The {@code ReadWriteLock}.
     */
    protected final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * The cache id.
     */
    protected final String id;

    /**
     * The cache map reference.
     */
    protected final Map<Integer, Object> cacheMap;

    protected AbstractHazelcastCache(String id, Map<Integer, Object> cacheMap) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        if (cacheMap == null) {
            throw new IllegalArgumentException("Cache instances require a cacheMap");
        }
        this.id = id;
        this.cacheMap = cacheMap;
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        this.cacheMap.clear();
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    public Object getObject(Object key) {
        return this.cacheMap.get(key.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    /**
     * {@inheritDoc}
     */
    public int getSize() {
        return this.cacheMap.size();
    }

    /**
     * {@inheritDoc}
     */
    public void putObject(Object key, Object value) {
        this.cacheMap.put(key.hashCode(), value);
    }

    /**
     * {@inheritDoc}
     */
    public Object removeObject(Object key) {
        return this.cacheMap.remove(key.hashCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Cache)) {
            return false;
        }

        Cache otherCache = (Cache) obj;
        return this.id.equals(otherCache.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Hazelcast {" + this.id + "}";
    }

}
