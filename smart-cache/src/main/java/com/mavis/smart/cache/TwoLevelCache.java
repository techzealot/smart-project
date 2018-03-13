package com.mavis.smart.cache;

import java.util.Arrays;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import com.mavis.smart.eventbus.DistributeEventbus;

public class TwoLevelCache extends AbstractValueAdaptingCache {
	private final Cache firstLevelCache;
	private final Cache secondLevelCache;
	private final DistributeEventbus eventbus;

	protected TwoLevelCache(Cache firstLevelCache, Cache secondLevelCache, DistributeEventbus eventbus,
			boolean allowNullValues) {
		super(allowNullValues);
		this.firstLevelCache = firstLevelCache;
		this.secondLevelCache = secondLevelCache;
		this.eventbus=eventbus;
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Object getNativeCache() {
		return Arrays.asList(firstLevelCache, secondLevelCache);
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void put(Object key, Object value) {
		//1.put data into second level
		secondLevelCache.put(key, value);
		//2.public cache change event to all first level cache
		eventbus.publish(String.valueOf(key));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evict(Object key) {
		secondLevelCache.evict(key);
		eventbus.publish("change",String.valueOf(key));
	}

	@Override
	public void clear() {
		secondLevelCache.clear();
		eventbus.publish("clear");
	}

	@Override
	protected Object lookup(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

}
