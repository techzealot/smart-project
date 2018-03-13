package com.mavis.smart.manager;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

public class TwoLevelCacheManager extends AbstractTransactionSupportingCacheManager{

	@Override
	public Cache getCache(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getCacheNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		// TODO Auto-generated method stub
		return null;
	}

}
