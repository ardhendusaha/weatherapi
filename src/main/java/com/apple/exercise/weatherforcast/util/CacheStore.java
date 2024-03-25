package com.apple.exercise.weatherforcast.util;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class CacheStore<T> {
	private Cache<Integer, T> cache;

    //Constructor to build Cache Store
    public CacheStore() {
        cache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    //Method to fetch previously stored record using record key
    public T get(Integer key) {
        return cache.getIfPresent(key);
    }

    //Method to put a new record in Cache Store with record key
    public void add(Integer key, T value) {
        if(key != null && value != null) {
            cache.put(key, value);
            System.out.println("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }
    }
}
