package com.swr.matchmatch.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Steve Rock, SWR Technologies, LLC  on 10/6/2017.
 */
public class PhotoInfo {

    private static final String TAG = PhotoInfo.class.getSimpleName();
    private static final Integer HIDDEN = 1;
    private static final Integer VISIBLE = 2;

    private static final String BASE_URL = "https://farm";
    private static final String BASE2_URL = ".staticflickr.com/";
    private static final String IMAGE_SUFFIX= "_z.jpg";

    private static final String ID = "id";
    private static final String OWNER = "owner";
    private static final String SECRET = "secret";
    private static final String SERVER = "server";
    private static final String FARM = "farm";
    private static final String TITLE = "title";


    Map<String, String> data;
    List<String> keys = new ArrayList<>();

    private String id;
    private String owner;
    private String secret;
    private String server;
    private String farm;
    private String title;
    private String url;

    // All photos are numbered 1-8, any two images that match have the same num.
    // This allows for easy image matching on this num
    private int photoNum = 0;

    private int state = HIDDEN;


    public PhotoInfo copy(){

        PhotoInfo pi = new PhotoInfo();
        pi.setId(this.getId());
        pi.setOwner(this.getOwner());
        pi.setSecret(this.getSecret());
        pi.setServer(this.getServer());
        pi.setFarm(this.getFarm());
        pi.setTitle(this.getTitle());
        pi.setPhotoNum(this.getPhotoNum());

        pi.generatePhotoUrl();
        return pi;
    }

    public PhotoInfo( ){

        //Map<String, String> map
        //if(map != null){
          //  this.data = map;
          //  parseData();
        //}

    }


    public void generatePhotoUrl(){

        // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
        // https://farm5.staticflickr.com/4493/23703255648_859b136e61_z.jpg

        StringBuilder sb = new StringBuilder(BASE_URL);
        sb.append(farm);
        sb.append(BASE2_URL);
        sb.append(server);
        sb.append("/");
        sb.append(id);
        sb.append("_");
        sb.append(secret);
        sb.append(IMAGE_SUFFIX);

        url = sb.toString();
        Log.d(TAG,"url = " + url);
    }



    private void parseData() {

        String value = null;

        if (data != null) {
            for (String key:keys) {

                value = data.get(key);
                if (value != null) {

                    switch (key) {
                        case ID:
                            id = value;
                            break;

                        case OWNER:
                            owner = value;
                            break;

                        case SECRET:
                            secret = value;
                            break;

                        case SERVER:
                            server = value;
                            break;

                        case FARM:
                            farm = value;
                            break;

                        case TITLE:
                            title = value;
                            break;

                    }
                }

            }

            generatePhotoUrl();

        }

        data = null;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }

    public int getState() {
        return state;
    }

    public void flipState() {
        switch(state){

            case 1: // card is up
                state = VISIBLE;
                break;

            case 2: // card is face down
                state = HIDDEN;
                break;

        }
    }
}

