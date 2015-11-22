package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.chan1cyrus2.jokeacitivity.JokeActivity;
import com.example.chan1cyrus2.jokeacitivity.JokeFragment;
import com.example.chan1cyrus2.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by chan1cyrus2 on 11/20/2015.
 */
class EndpointsAsyncTask extends AsyncTask<Activity, Void, String> {
    private static final String LOG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private Activity activity;

    @Override
    protected String doInBackground(Activity... params) {
        activity = params[0];

        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        //String name = params[0].second;

        try {
            Log.v(LOG, "running my api Service");
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.v(LOG, "error on my api Service");
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent myIntent = new Intent(activity, JokeActivity.class).putExtra(JokeFragment.JOKE_INTENT_ID,
                result);
        //disable the progressbar
        ProgressBar pgBar = (ProgressBar) activity.findViewById(R.id.pbHeaderProgress);
        pgBar.setVisibility(View.GONE);

        activity.startActivity(myIntent);
    }
}