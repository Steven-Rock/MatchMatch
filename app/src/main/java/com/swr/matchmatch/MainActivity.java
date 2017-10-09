package com.swr.matchmatch;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.swr.matchmatch.model.Observable;
import com.swr.matchmatch.model.PhotoInfo;
import com.swr.matchmatch.ui.PhotoRecylclerAdapter;
import com.swr.matchmatch.util.HttpAsyncRequest;
import com.swr.matchmatch.util.UIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by Steve Rock, SWR Technologies, LLC  on 10/6/2017.
 */
public class MainActivity extends AppCompatActivity implements Observable {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView image;
    private RecyclerView mRecyclerView;
    private PhotoRecylclerAdapter mAdapter;

    List<PhotoInfo> fullPhotos;
    List<PhotoInfo> photos = new ArrayList();

    Activity act = this;
    int loopCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //UIUtils.showSnackBar("Restarting",image);
                resetData(fullPhotos);
            }
        });

        image = (ImageView) findViewById(R.id.image);
        // Picasso.with(this).load().into(image);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        fetchFlickrData();
    }

    private void fetchFlickrData(){

        HttpAsyncRequest req = new HttpAsyncRequest();
        req.setUrl(getString(R.string.SEARCH_URL));
        req.addObserver((Observable)this);
        req.execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    private void showProgress(final boolean show) {}


    private void resetData(final List<PhotoInfo> photos){

        showProgress(true);

        if(photos != null) {

            this.fullPhotos = photos;
            this.photos.clear();
            int start = loopCount*8;
            int max = start + 8;
            for(int i = start; i < max; i++){
                this.photos.add(fullPhotos.get(i));
            }
            loopCount++;

            final List<PhotoInfo> newPhotos = new ArrayList<>();
            newPhotos.addAll(this.photos);
            for (PhotoInfo info: this.photos) {

                PhotoInfo newPhoto = info.copy();
                newPhotos.add(newPhoto);

            }
            Collections.shuffle(newPhotos);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    UIUtils.showSnackBar(getString(R.string.DATA_RETURNED) , image);

                    mAdapter = new PhotoRecylclerAdapter(newPhotos);
                    mRecyclerView.setAdapter(mAdapter);
                }
            });

        }

    }

    @Override
    public void dataReceived(final List<PhotoInfo> photos) {

        final Activity act = this;

        showProgress(false);


        if(photos != null) {
            resetData(photos);
        }
        else{
           // Log.d(TAG, "No data returned from server");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    UIUtils.showSnackBar(getString(R.string.NO_DATA_RETURNED) , mRecyclerView);

                }
            });
        }
    }

}
