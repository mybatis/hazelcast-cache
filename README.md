MyBatis Hazelcast Extension
===========================

[![Java CI](https://github.com/mybatis/hazelcast-cache/actions/workflows/ci.yaml/badge.svg)](https://github.com/mybatis/hazelcast-cache/actions/workflows/ci.yaml)
[![Coverage Status](https://coveralls.io/repos/mybatis/hazelcast-cache/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/hazelcast-cache?branch=master)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.caches/mybatis-hazelcast/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.caches/mybatis-hazelcast)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/org.mybatis.caches/mybatis-hazelcast.svg)](https://oss.sonatype.org/content/repositories/snapshots/org/mybatis/caches/mybatis-hazelcast/)
[![License](https://img.shields.io/:license-apache-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

![mybatis-logo](https://mybatis.org/images/mybatis-logo.png)

MyBatis-Hazelcast extension Hazelcast support for MyBatis Cache.

Essentials
----------

* [See the docs](https://mybatis.org/hazelcast-cache/)


This module contains two cache implementations utilizing Hazelcast: 


1) org.mybatis.caches.hazelcast.HazelcastCache:

Use "org.mybatis.caches.hazelcast.HazelcastCache" if you want the JVM running MyBatis to be part of the Hazelcast cache cluster.

Internally this is calling "Hazelcast.newHazelcastInstance()".


2) org.mybatis.caches.hazelcast.HazelcastClientCache:

Use "org.mybatis.caches.hazelcast.HazelcastClientCache" if you want the JVM running MyBatis to be a client to a Hazelcast cache cluster running outside the JVM running MyBatis.

Internally this is calling "HazelcastClient.newHazelcastClient()". Also see https://hazelcast.org/docs/latest/manual/html/nativeclient.html

Support Hazelcast
-----------------
* mybatis-hazelcast 1.1.x supports hazelcast 3 - end of life (jdk 8)
* mybatis-hazelcast 1.2.x supports hazelcast 4 - end of life (jdk 8)
* mybatis-hazelcast 1.3.x supports hazelcast 5 until 5.3.x (jdk 8)
* mybatis-hazelcast 1.4.x supports hazelcast 5.6.x on (jdk 17)
