package com.tinyurl4j;

import com.tinyurl4j.data.Response;

/**
 * Listener for calling on <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a>.
 *
 * @author Xinyue Zhao
 */
public interface TinyUrl4JListener {
	/**
	 * Callback when calling on <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a> finishes.
	 *
	 * @param response
	 * 		It might be null when any problem happened while calling.
	 */
	void onResponse(Response response);
}
