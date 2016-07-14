package com.tinyurl4j;

import com.tinyurl4j.data.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Defines static functions to call <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a>.
 *
 * @author Xinyue Zhao
 */
public class Api {
	public interface TinyUrl {
		@GET("/")
		Call<Response> getTinyUrl( @Query("q") String q );
	}

	private static final String             HOST     = "https://tinyurl-wrapper.appspot.com/";
	public static final retrofit2.Retrofit Retrofit = new Retrofit.Builder().addConverterFactory( GsonConverterFactory.create() )
																			 .baseUrl( HOST )
																			 .build();


	/**
	 * Call <a href="https://github.com/XinyueZ/tinyurl-wrapper">TinyUrlWrapper</a> to transform {@code q} into tinyurl.
	 *
	 * @param q
	 * 		The original url to transform.
	 * @param listener
	 * 		Callback when this call finishes.
	 *
	 * @deprecated Use new API {@link TinyUrl#getTinyUrl}.
	 */
	@Deprecated
	public static void call( String q, final TinyUrl4JListener listener ) {
		Call<Response> call = Api.Retrofit.create( TinyUrl.class )
										  .getTinyUrl( q );
		call.enqueue( new Callback<Response>() {
			@Override
			public void onResponse( Call<Response> call,retrofit2.Response<Response> response ) {
				if( response.isSuccessful() ) {
					listener.onResponse( response.body() );
				} else {
					listener.onResponse( null );
				}
			}

			@Override
			public void onFailure( Call<Response> call, Throwable t ) {
				listener.onResponse( null );
			}
		} );
	}
}
