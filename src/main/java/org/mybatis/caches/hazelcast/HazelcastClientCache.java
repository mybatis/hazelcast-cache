/**
 *    Copyright 2010-2015 the original author or authors.
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

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;

/**
 * Cache adapter for Hazelcast using "Hazelcast Client" API {@link com.hazelcast.client.HazelcastClient}. 
 *  
 * This means that the HazelcastClientCache is not a member of the cluster.
 *
 * @author Ronald Ploeger
 */
public class HazelcastClientCache extends AbstractHazelcastCache {

    /** The Constant CACHE. */
    private static final HazelcastInstance CACHE = HazelcastClient.newHazelcastClient();
    
    /**
     * Instantiates a new Hazelcast cache for the specified namespace.
     *
     * @param id the cache id.
     */
    public HazelcastClientCache(String id) {
        super(id, CACHE.getMap(id));
    }

}
