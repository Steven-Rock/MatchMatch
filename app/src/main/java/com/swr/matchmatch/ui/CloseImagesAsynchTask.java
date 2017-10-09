package com.swr.matchmatch.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.swr.matchmatch.R;

/**
 * Created by Steve on 10/8/2017.
 */

public class CloseImagesAsynchTask extends AsyncTask<String, Void, String> {


    private static final String TAG = CloseImagesAsynchTask.class.getSimpleName();
    private PhotoInfoHolder lastHolder;
    private PhotoInfoHolder currentHolder;


    @Override
    protected String doInBackground(String... strings) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


    public PhotoInfoHolder getLastHolder() {
        return lastHolder;
    }

    public void setLastHolder(PhotoInfoHolder lastHolder) {
        this.lastHolder = lastHolder;
    }

    public PhotoInfoHolder getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(PhotoInfoHolder currentHolder) {
        this.currentHolder = currentHolder;
    }

    @Override
    protected void onPostExecute(String result) {

        Log.d(TAG, "onPostExecute(): Starting");

        if(lastHolder != null) {
            Picasso.with(lastHolder.getImageView().getContext()).load(lastHolder.getImageView().getContext().getResources().getString(R.string.CARD_BACK_SIDE)).into(lastHolder.getImageView());
        }

        if(currentHolder != null) {
            Picasso.with(currentHolder.getImageView().getContext()).load(currentHolder.getImageView().getContext().getResources().getString(R.string.CARD_BACK_SIDE)).into(currentHolder.getImageView());
        }
    }
}
