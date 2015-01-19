package com.tinyurl4j.data;

import com.google.gson.annotations.SerializedName;

/**
 * The domain class of the response of <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a>.
 *
 * @author Xinyue Zhao
 */
public final class Response {
	/**
	 * The status of the response, {@code true} if all process is correct.
	 */
	@SerializedName("status")
	private boolean mStatus;
	/**
	 * Original url that is transformed in tinyurl.
	 */
	@SerializedName("q")
	private String mOriginal;
	/**
	 * Transformed tinyurl.
	 */
	@SerializedName("result")
	private String mResult;
	/**
	 * {@code true} when {@link #mResult} was from backend directly instead of calling on <a  href="http://www.tinyurl.com">Tinyurl</a>.
	 */
	@SerializedName("stored")
	private boolean mStored;

	/**
	 * Constructor of {@link com.tinyurl4j.data.Response}.
	 *
	 * @param status
	 * 		The status of the response, {@code true} if all process is correct.
	 * @param original
	 * 		Original url that is transformed in tinyurl.
	 * @param result
	 * 		Transformed tinyurl.
	 * @param stored
	 * 		{@code true} when {@link #mResult} was from backend directly instead of calling on <a href="http://www.tinyurl.com">Tinyurl</a>.
	 */
	public Response(boolean status, String original, String result, boolean stored) {
		mStatus = status;
		mOriginal = original;
		mResult = result;
		mStored = stored;
	}

	/**
	 * @return The status of the response, {@code true} if all process is correct.
	 */
	public boolean isStatus() {
		return mStatus;
	}

	/**
	 * @return Original url that is transformed in tinyurl.
	 */
	public String getOriginal() {
		return mOriginal;
	}

	/**
	 * @return Transformed tinyurl.
	 */
	public String getResult() {
		return mResult;
	}

	/**
	 * @return {@code true} when {@link #mResult} was from backend directly instead of calling on <a href="http://www.tinyurl.com">Tinyurl</a>.
	 */
	public boolean isStored() {
		return mStored;
	}

	/**
	 * Set the status of the response.
	 *
	 * @param status
	 * 		The status of the response, {@code true} if all process is correct.
	 */
	public void setStatus(boolean status) {
		mStatus = status;
	}

	/**
	 * Set original url.
	 *
	 * @param original
	 * 		Original url that is transformed in tinyurl.
	 */
	public void setOriginal(String original) {
		mOriginal = original;
	}

	/**
	 * Set transformed result.
	 *
	 * @param result
	 * 		Transformed tinyurl.
	 */
	public void setResult(String result) {
		mResult = result;
	}

	/**
	 * To set whether data is from backend or not.
	 *
	 * @param stored{@code true} when {@link #mResult} was from backend directly instead of calling on <a href="http://www.tinyurl.com">Tinyurl</a>.
	 */
	public void setStored(boolean stored) {
		mStored = stored;
	}
}
