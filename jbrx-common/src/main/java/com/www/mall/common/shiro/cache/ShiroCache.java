package com.www.mall.common.shiro.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import io.jboot.Jboot;

/**
 * Shiro 缓存，使用jboot cache
 * @author Rlax
 *
 * @param <K>
 * @param <V>
 */
public class ShiroCache<K, V> implements Cache<K, V> {

	public ShiroCache(String cacheName) {
		this.cacheName = cacheName;
	}
	
	private String cacheName;
	
	public V get(K key) throws CacheException {
		return Jboot.me().getCache().get(cacheName, key);
	}

	public V put(K key, V value) throws CacheException {
		Jboot.me().getCache().put(cacheName, key, value);
		return value;
	}

	public V remove(K key) throws CacheException {
		V value = Jboot.me().getCache().get(cacheName, key);
		Jboot.me().getCache().remove(cacheName, key);
		return value;
	}

	public void clear() throws CacheException {
		Jboot.me().getCache().removeAll(cacheName);		
	}

	public int size() {
		return Jboot.me().getCache().getKeys(cacheName).size();
	}

	public Set<K> keys() {
		return (Set<K>) Jboot.me().getCache().getKeys(cacheName);
	}

	public Collection<V> values() {
		Collection<V> values = Collections.emptyList();
		List keys = Jboot.me().getCache().getKeys(cacheName);

		if (!CollectionUtils.isEmpty(keys)) {
			values = new ArrayList<V>(keys.size());
			for (Object key : keys) {
				V value = Jboot.me().getCache().get(cacheName, key);
				if (value != null) {
					values.add(value);
				}
			}
		}

		return values;
	}

}
