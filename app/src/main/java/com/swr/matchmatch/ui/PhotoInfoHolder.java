package com.swr.matchmatch.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.swr.matchmatch.R;
import com.swr.matchmatch.model.PhotoInfo;
import com.swr.matchmatch.util.UIUtils;

/**
 * Created by Steve Rock, SWR Technologies, LLC  on 10/6/2017.
 *
 * MIT License

 Copyright (c) [2017] Steven William Rock, SWR Technologies, LLC

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 */
public class PhotoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = PhotoInfoHolder.class.getSimpleName();
    private static final String PHOTO_KEY = "PHOTO";

    private ImageView imageView;
    private PhotoInfo pInfo;

    int position = 0;

    public PhotoInfoHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.image1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

       // itemView.setOnClickListener(this);
    }

    public void setData(PhotoInfo info, int position, Context cxt){

        if(info == null) return;
        this.position = position + 1;

        pInfo = info;

        Picasso.with(cxt).load(R.string.CARD_BACK_SIDE).into(imageView);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, (String) pInfo.getTitle());

        UIUtils.showSnackBar(pInfo.getTitle(), imageView);

    }

    public ImageView getImageView() {
        return imageView;
    }
    public PhotoInfo getpInfo() {
        return pInfo;
    }


}
