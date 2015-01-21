package com.tinyurl4j.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.chopping.activities.BaseActivity;
import com.chopping.application.BasicPrefs;
import com.tinyurl4j.Api;
import com.tinyurl4j.TinyUrl4JListener;
import com.tinyurl4j.data.Response;

/**
 * Demo for tinyurl4j.
 *
 * @author Xinyue Zhao
 */
public class MainActivity extends BaseActivity {
	/**
	 * Main layout for this component.
	 */
	private static final int LAYOUT = R.layout.activity_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(LAYOUT);
	}


	public void toTinyUrl(View view) {
		String input = ((EditText) findViewById(R.id.input_et)).getText().toString();
		Api.call(input, new TinyUrl4JListener() {
			@Override
			public void onResponse(Response response) {
				if (response != null) {
					Button outputBtn = (Button) findViewById(R.id.output_btn);
					outputBtn.setVisibility(View.VISIBLE);
					outputBtn.setText(response.getResult());
					final Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(response.getResult()));
					outputBtn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Button btn = (Button) v;
							if (!TextUtils.isEmpty(btn.getText())) {
								startActivity(i);
							}
						}
					});
				}
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//Generated for Chopping bootstrap.
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	protected BasicPrefs getPrefs() {
		return Prefs.getInstance();
	}
	@Override
	protected void onAppConfigLoaded() {
		super.onAppConfigLoaded();
		showAppList();
	}

	@Override
	protected void onAppConfigIgnored() {
		super.onAppConfigIgnored();
		showAppList();
	}

	/**
	 * Show all external applications links.
	 */
	private void showAppList() {
		getSupportFragmentManager().beginTransaction().replace(R.id.app_list_fl, AppListImpFragment.newInstance(this))
				.commit();
	}

}
