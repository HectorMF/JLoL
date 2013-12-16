package com.perfectplay.org;

import java.util.LinkedHashMap;
import java.util.Map;

//Written by: http://stackoverflow.com/a/1953516
@SuppressWarnings("serial")
class LruCache<A, B> extends LinkedHashMap<A, B> {
    private final int maxEntries;

    public LruCache(final int maxEntries) {
        super(maxEntries + 1, 1.0f, true);
        this.maxEntries = maxEntries;
    }

    @Override
    protected boolean removeEldestEntry(final Map.Entry<A, B> eldest) {
        return super.size() > maxEntries;
    }
}
