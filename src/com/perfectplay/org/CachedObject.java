package com.perfectplay.org;

import java.util.Date;

abstract class CachedObject {
	protected Date time_cached;
	
	public Date getTimeCached(){
		return time_cached;
	}
}
