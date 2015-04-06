package com.tinyurl4j;

import java.io.IOException;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import com.tinyurl4j.data.Response;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Defines static functions to call <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a>.
 *
 * @author Xinyue Zhao
 */
public final class Api {
	public interface S {
		@GET("/")
		void getTinyUrl(@Query("q") String q, Callback<Response> callback);
	}

	/**
	 * The GSON parser.
	 */
	private static final Gson sGson = new Gson();
	private static final String HOST = "https://tinyurl-wrapper.appspot.com/";
	/**
	 * Call url of <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a>.
	 */
	private static final String BASE_URL = HOST + "?q=";

	/**
	 * Call <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a> to transform {@code q} into
	 * tinyurl.
	 *
	 * @param q
	 * 		The original url to transform.
	 * @param callback
	 * 		Callback when this call finishes.
	 */
	public static final void getTinyUrl(String q, Callback<Response> callback) {
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(HOST).build();
		S s = restAdapter.create(S.class);
		s.getTinyUrl(q, callback);
	}

	/**
	 * Call <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a> to transform {@code q} into
	 * tinyurl.
	 *
	 * @param q
	 * 		The original url to transform.
	 * @param listener
	 * 		Callback when this call finishes.
	 *
	 * @deprecated Use new API {@link #getTinyUrl}.
	 */
	public static void call(String q, TinyUrl4JListener listener) {
		W w = new W(BASE_URL + q, listener);
		AsyncTaskCompat.executeParallel(new AsyncTask<W, W, W>() {
			@Override
			protected W doInBackground(W... params) {
				W w = params[0];
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder().url(w.url).build();
				try {
					com.squareup.okhttp.Response response = client.newCall(request).execute();
					ResponseBody responseBody = response.body();
					int responseCode = response.code();
					if (responseCode >= 300) {
						responseBody.close();
						w.r = null;
					} else {
						w.r = sGson.fromJson(responseBody.string(), Response.class);
					}
				} catch (IOException e) {
					e.printStackTrace();
					w.r = null;
				}
				return w;
			}

			@Override
			protected void onPostExecute(W w) {
				super.onPostExecute(w);
				w.l.onResponse(w.r);
			}
		}, w);
	}

	private static class W {
		String url;
		Response r;
		TinyUrl4JListener l;

		private W(String url, TinyUrl4JListener l) {
			this.url = url;
			this.l = l;
		}
	}
}
