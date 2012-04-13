/*
 *    Copyright 2010-2012 The MyBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.caches.hazelcast;

import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import com.hazelcast.core.Hazelcast;

/**
 * Cache adapter for Hazelcast.
 *
 * @version $Id$
 */
public final class HazelcastCache implements Cache {

    /**
     * The {@code ReadWriteLock}.
     */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * The cache id.
     */
    private final String id;

    /**
     * The cache map reference.
     */
    private final Map<Integer, Object> cacheMap;

    /**
     * Instantiates a new Hazelcast cache for the specified id.
     *
     * @param id the cache id.
     */
    public HazelcastCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
        this.cacheMap = Hazelcast.getMap(this.id);
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
        return "Hazelcast {"
                + this.id
                + "}";
    }

}
