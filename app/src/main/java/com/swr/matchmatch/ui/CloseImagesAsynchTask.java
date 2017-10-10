package com.swr.matchmatch.ui;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.swr.matchmatch.R;


/**
 * Created by Steve Rock, SWR Technologies, LLC  on 10/6/2017.
 MIT License

 Copyright (c) [2017] Steven William Rock, SWR Technologies, LLC

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

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
