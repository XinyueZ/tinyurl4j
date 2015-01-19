package com.tinyurl4j.sample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.tinyurl4j.Api;
import com.tinyurl4j.TinyUrl4JListener;
import com.tinyurl4j.data.Response;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void toTinyUrl(View view) {
		String input = ((EditText)findViewById(R.id.input_et)).getText().toString();
		Api.call(input, new TinyUrl4JListener() {
			@Override
			public void onResponse(Response response) {
				if(response != null) {
					Button outputBtn = (Button) findViewById(R.id.output_btn);
					outputBtn.setVisibility(View.VISIBLE);
					outputBtn.setText(response.getResult());
					final Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(response.getResult()));
					outputBtn.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Button btn = (Button) v;
							if( !TextUtils.isEmpty(btn.getText())) {
								startActivity(i);
							}
						}
					});
				}
			}
		});
	}
}
