package com.mavis.smart.cache;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import com.mavis.smart.eventbus.EventBus;

public class TwoLevelCache extends AbstractValueAdaptingCache {
	private final Cache firstLevelCache;
	private final Cache secondLevelCache;
	private final EventBus eventbus;

	protected TwoLevelCache(Cache firstLevelCache, Cache secondLevelCache, EventBus eventbus, boolean allowNullValues) {
		super(allowNullValues);
		this.firstLevelCache = firstLevelCache;
		this.secondLevelCache = secondLevelCache;
		this.eventbus = eventbus;
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
		T value = firstLevelCache.get(key, valueLoader);
		if (Objects.nonNull(value)) {
			return value;
		} else {
			value = secondLevelCache.get(key, valueLoader);
			if (Objects.nonNull(value)) {
				eventbus.publish("change", Objects.toString(key));
			}
			return value;
		}
	}

	@Override
	public void put(Object key, Object value) {
		// 1.put data into second level
		secondLevelCache.put(key, value);
		// 2.public cache change event to all first level cache
		eventbus.publish(Objects.toString(key));
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		ValueWrapper wrapper = secondLevelCache.putIfAbsent(key, value);
		if (Objects.nonNull(wrapper)) {
			eventbus.publish("change", Objects.toString(key));
		}
		return wrapper;
	}

	@Override
	public void evict(Object key) {
		secondLevelCache.evict(key);
		eventbus.publish("change", String.valueOf(key));
	}

	@Override
	public void clear() {
		secondLevelCache.clear();
		eventbus.publish("clear");
	}

	@Override
	// lazy load
	protected Object lookup(Object key) {
		Object value = firstLevelCache.get(key).get();
		if (Objects.nonNull(value)) {
			return value;
		} else {
			value = secondLevelCache.get(key).get();
			if (Objects.nonNull(value)) {
				// put value in first cache,lazy load
				firstLevelCache.put(key, value);
			}
			return value;
		}
	}

}
