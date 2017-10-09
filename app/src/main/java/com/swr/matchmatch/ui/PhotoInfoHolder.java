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
 * Created by Steve on 10/7/2017.
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
