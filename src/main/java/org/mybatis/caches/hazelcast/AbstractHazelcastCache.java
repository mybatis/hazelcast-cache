/*
 *    Copyright 2010-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.caches.hazelcast;

import com.hazelcast.map.IMap;

import java.util.concurrent.locks.ReadWriteLock;

import org.apache.ibatis.cache.Cache;

/**
 * The Class AbstractHazelcastCache.
 */
public abstract class AbstractHazelcastCache implements Cache {

  /** The {@code ReadWriteLock}. */
  protected final ReadWriteLock readWriteLock = new DummyReadWriteLock();

  /** The cache id. */
  protected final String id;

  /** The cache map reference. */
  protected final IMap<Object, Object> cacheMap;

  /**
   * Instantiates a new abstract hazelcast cache.
   *
   * @param id
   *          the id
   * @param imap
   *          the i map
   */
  protected AbstractHazelcastCache(String id, IMap<Object, Object> imap) {
    if (id == null) {
      throw new IllegalArgumentException("Cache instances require an id");
    }
    if (imap == null) {
      throw new IllegalArgumentException("Cache instances require a cacheMap");
    }
    this.id = id;
    this.cacheMap = imap;
  }

  @Override
  public void clear() {
    this.cacheMap.clear();
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public Object getObject(Object key) {
    return this.cacheMap.get(key);
  }

  @Override
  public ReadWriteLock getReadWriteLock() {
    return this.readWriteLock;
  }

  @Override
  public int getSize() {
    return this.cacheMap.size();
  }

  @Override
  public void putObject(Object key, Object value) {
    if (value != null) {
      this.cacheMap.set(key, value);
    } else {
      if (this.cacheMap.containsKey(key)) {
        this.cacheMap.remove(key);
      }
    }
  }

  @Override
  public Object removeObject(Object key) {
    return this.cacheMap.remove(key);
  }

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

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

  @Override
  public String toString() {
    return "Hazelcast {" + this.id + "}";
  }

}
