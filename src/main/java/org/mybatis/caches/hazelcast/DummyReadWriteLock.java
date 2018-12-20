/**
 *    Copyright 2010-2018 the original author or authors.
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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * The Class DummyReadWriteLock.
 *
 * @author Iwao AVE!
 */
class DummyReadWriteLock implements ReadWriteLock {

  /** The lock. */
  private Lock lock = new DummyLock();

  /*
   * (non-Javadoc)
   * @see java.util.concurrent.locks.ReadWriteLock#readLock()
   */
  @Override
  public Lock readLock() {
    return this.lock;
  }

  /*
   * (non-Javadoc)
   * @see java.util.concurrent.locks.ReadWriteLock#writeLock()
   */
  @Override
  public Lock writeLock() {
    return this.lock;
  }

  /**
   * The Class DummyLock.
   */
  static class DummyLock implements Lock {

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#lock()
     */
    @Override
    public void lock() {
      // Do Nothing
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#lockInterruptibly()
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
      // Do Nothing
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#tryLock()
     */
    @Override
    public boolean tryLock() {
      return true;
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#tryLock(long, java.util.concurrent.TimeUnit)
     */
    @Override
    public boolean tryLock(long paramLong, TimeUnit paramTimeUnit) throws InterruptedException {
      return true;
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#unlock()
     */
    @Override
    public void unlock() {
      // Do Nothing
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.locks.Lock#newCondition()
     */
    @Override
    public Condition newCondition() {
      return null;
    }
  }

}
