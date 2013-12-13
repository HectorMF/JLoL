package com.perfectplay.org;

import org.joda.time.DateTime;

abstract class CachedObject {
	protected DateTime time_cached;
	
	public DateTime getTimeCached(){
		return time_cached;
	}
}
