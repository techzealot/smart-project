package com.mavis.smart.manager;

import java.util.Collection;
import java.util.Collections;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

public class TwoLevelCacheManager extends AbstractTransactionSupportingCacheManager {
	@Override
	protected Collection<? extends Cache> loadCaches() {
		// 加载初始缓存
		return Collections.emptyList();
	}
}
