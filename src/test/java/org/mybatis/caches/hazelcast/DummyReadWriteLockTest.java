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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import org.junit.jupiter.api.Test;

class DummyReadWriteLockTest {

  @Test
  void shouldReturnSameLockForReadAndWrite() {
    ReadWriteLock rwl = new DummyReadWriteLock();
    assertNotNull(rwl.readLock());
    assertNotNull(rwl.writeLock());
  }

  @Test
  void shouldLockDoNothing() {
    Lock lock = new DummyReadWriteLock().readLock();
    lock.lock();
  }

  @Test
  void shouldLockInterruptiblyDoNothing() throws InterruptedException {
    Lock lock = new DummyReadWriteLock().readLock();
    lock.lockInterruptibly();
  }

  @Test
  void shouldTryLockReturnTrue() {
    Lock lock = new DummyReadWriteLock().readLock();
    assertTrue(lock.tryLock());
  }

  @Test
  void shouldTryLockWithTimeoutReturnTrue() throws InterruptedException {
    Lock lock = new DummyReadWriteLock().readLock();
    assertTrue(lock.tryLock(1, TimeUnit.SECONDS));
  }

  @Test
  void shouldUnlockDoNothing() {
    Lock lock = new DummyReadWriteLock().readLock();
    lock.unlock();
  }

  @Test
  void shouldNewConditionReturnNull() {
    Lock lock = new DummyReadWriteLock().readLock();
    assertNull(lock.newCondition());
  }

}
