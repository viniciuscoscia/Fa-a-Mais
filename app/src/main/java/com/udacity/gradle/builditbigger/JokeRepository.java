package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class JokeRepository extends AsyncTask<Void, String, String> {

    private AsyncTaskDelegate delegate = null;

    private JokeRepository(){}

    public JokeRepository(AsyncTaskDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(Void... objects) {

        GoogleClientRequestInitializer googleClientRequestInitializer = new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                abstractGoogleClientRequest.setDisableGZipContent(true);
            }
        };

        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null);

        MyApi myApiService = builder
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(googleClientRequestInitializer)
                            .build();

        try {
            return myApiService.tellJoke().execute().getData();
        } catch (IOException e) {
            Log.e("ERRO", e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String joke) {
        delegate.processFinish(joke);
    }

    public interface AsyncTaskDelegate {
        void processFinish(String joke);
    }

}
