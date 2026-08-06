/*
 *    Copyright 2010-2026 the original author or authors.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.ibatis.cache.Cache;
import org.junit.jupiter.api.Test;

abstract public class BaseHazelcastTestCase {

  protected static final String DEFAULT_ID = "Hazelcast";

  protected abstract Cache newCache();

  @Test
  public void shouldDemonstrateHowAllObjectsAreKept() {
    Cache cache = newCache();
    for (int i = 0; i < 100000; i++) {
      cache.putObject(i, i);
      assertEquals(i, cache.getObject(i));
    }
    assertEquals(100000, cache.getSize());
  }

  @Test
  public void shouldDemonstrateCopiesAreEqual() {
    Cache cache = newCache();
    for (int i = 0; i < 1000; i++) {
      cache.putObject(i, i);
      assertEquals(i, cache.getObject(i));
    }
  }

  @Test
  public void shouldRemoveItemOnDemand() {
    Cache cache = newCache();
    cache.putObject(0, 0);
    assertNotNull(cache.getObject(0));
    cache.removeObject(0);
    assertNull(cache.getObject(0));
  }

  @Test
  public void shouldFlushAllItemsOnDemand() {
    Cache cache = newCache();
    for (int i = 0; i < 5; i++) {
      cache.putObject(i, i);
    }
    assertNotNull(cache.getObject(0));
    assertNotNull(cache.getObject(4));
    cache.clear();
    assertNull(cache.getObject(0));
    assertNull(cache.getObject(4));
  }

  @Test
  public void shouldPutNullValueRemovesExistingKey() {
    Cache cache = newCache();
    cache.putObject(0, 0);
    assertNotNull(cache.getObject(0));
    cache.putObject(0, null);
    assertNull(cache.getObject(0));
  }

  @Test
  public void shouldPutNullValueWithAbsentKeyDoesNothing() {
    Cache cache = newCache();
    cache.putObject(0, null);
    assertNull(cache.getObject(0));
    assertEquals(0, cache.getSize());
  }

  @Test
  public void shouldReturnCorrectId() {
    Cache cache = newCache();
    assertNotNull(cache.getId());
  }

  @Test
  public void shouldBeEqualToItself() {
    Cache cache = newCache();
    assertTrue(cache.equals(cache));
  }

  @Test
  public void shouldNotBeEqualToNull() {
    Cache cache = newCache();
    assertFalse(cache.equals(null));
  }

  @Test
  public void shouldNotBeEqualToNonCacheObject() {
    Cache cache = newCache();
    assertFalse(cache.equals("not a cache"));
  }

  @Test
  public void shouldBeEqualToCacheWithSameId() {
    Cache cache1 = newCache();
    Cache cache2 = newCache();
    assertTrue(cache1.equals(cache2));
  }

  @Test
  public void shouldHaveConsistentHashCode() {
    Cache cache = newCache();
    assertEquals(cache.hashCode(), cache.hashCode());
  }

  @Test
  public void shouldReturnMeaningfulToString() {
    Cache cache = newCache();
    assertNotNull(cache.toString());
    assertTrue(cache.toString().contains(DEFAULT_ID));
  }

}
