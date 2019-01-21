MyBatis Hazelcast Extension
===========================

[![Build Status](https://travis-ci.org/mybatis/hazelcast-cache.svg?branch=master)](https://travis-ci.org/mybatis/hazelcast-cache)
[![Coverage Status](https://coveralls.io/repos/mybatis/hazelcast-cache/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/hazelcast-cache?branch=master)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.caches/mybatis-hazelcast/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.caches/mybatis-hazelcast)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![mybatis-logo](http://mybatis.github.io/images/mybatis-logo.png)

MyBatis-Hazelcast extension Hazelcast support for MyBatis Cache.

Essentials
----------

* [See the docs](http://mybatis.github.io/hazelcast-cache/)


This module contains two cache implementations utilizing Hazelcast: 


1) org.mybatis.caches.hazelcast.HazelcastCache:

Use "org.mybatis.caches.hazelcast.HazelcastCache" if you want the JVM running MyBatis to be part of the Hazelcast cache cluster.

Internally this is calling "Hazelcast.newHazelcastInstance()".


2) org.mybatis.caches.hazelcast.HazelcastClientCache:

Use "org.mybatis.caches.hazelcast.HazelcastClientCache" if you want the JVM running MyBatis to be a client to a Hazelcast cache cluster running outside the JVM running MyBatis.

Internally this is calling "HazelcastClient.newHazelcastClient()". Also see http://hazelcast.org/docs/latest/manual/html/nativeclient.html