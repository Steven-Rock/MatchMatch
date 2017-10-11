package com.swr.matchmatch.util;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.swr.matchmatch.model.Observable;
import com.swr.matchmatch.model.PhotoInfo;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Steve Rock, SWR Technologies, LLC  on 10/6/2017.
 *
 * MIT License

 Copyright (c) [2017] Steven William Rock, SWR Technologies, LLC

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

 */

public class HttpAsyncRequest extends AsyncTask<String, Void, String> {

    private static final String TAG = HttpAsyncRequest.class.getSimpleName();

    public static final String REQUEST_TYPE = "GET";
    public static final int TIMEOUT = 15000;

    private static final String ID = "id";
    private static final String OWNER = "owner";
    private static final String SECRET = "secret";
    private static final String SERVER = "server";
    private static final String FARM = "farm";
    private static final String TITLE = "title";

    private static final String XML_FILTER = "photo";
    private static final String XML_FORMAT  ="UTF-8";

    private List<Observable> observers = new ArrayList<>();
    public List<Observable> getObservers() {
        return observers;
    }
    public void addObserver(Observable observer) {
        this.observers.add(observer);
    }

    private String url = null;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    private XmlPullParserFactory xmlFactoryObject;
    private XmlPullParser myparser;

    @Override
    protected String doInBackground(String... params) {
        String stringUrl = url;
        String result = null;

        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);

            HttpURLConnection connection = connectionInstance(myUrl);
            connection.connect();
            result = readStream(connection);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    private String readStream(HttpURLConnection connection)throws IOException{

        String inputLine;
        String result = null;

        InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(streamReader);
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        reader.close();
        streamReader.close();
        result = stringBuilder.toString();
        return result;
    }
    private static HttpURLConnection connectionInstance( URL myUrl ) throws IOException{

        HttpURLConnection connection = (HttpURLConnection)
                myUrl.openConnection();

        connection.setRequestMethod(REQUEST_TYPE);
        connection.setReadTimeout(TIMEOUT);
        connection.setConnectTimeout(TIMEOUT);

        return connection;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "result = " + result);

        List<PhotoInfo> photos = new ArrayList<>();

        // parse results
        if (result != null) {
            try {
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                xmlFactoryObject.setNamespaceAware(true);
                myparser = xmlFactoryObject.newPullParser();

                myparser.setInput(new StringReader(result));

                int eventType = myparser.getEventType();

                PhotoInfo photo;
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setCoalescing(true);
                DocumentBuilder builder = null;

                try {
                    builder = factory.newDocumentBuilder();
                    InputStream is = new ByteArrayInputStream(result.getBytes(XML_FORMAT));
                    Document dom = builder.parse(is);

                    org.w3c.dom.Element root = dom.getDocumentElement();
                    NodeList items = root.getElementsByTagName(XML_FILTER);

                    // Loop over all photos
                    int max = items.getLength();
                    for (int i = 0; i < max; i++) {

                        Node item = items.item(i);
                        NamedNodeMap map = item.getAttributes();

                        photo = new PhotoInfo();
                        photo.setPhotoNum(i + 1);

                        // loop over all attributes
                        int max2 = map.getLength() - 1;
                        for (int j = 0; j < max2; j++) {

                            Node attr = map.item(j);
                            System.out.println(attr.getNodeName() + " = \"" + attr.getNodeValue() + "\"");

                            switch (attr.getNodeName()){

                                case ID:
                                    photo.setId(attr.getNodeValue());
                                    break;

                                case OWNER:
                                    photo.setOwner(attr.getNodeValue());
                                    break;

                                case SECRET:
                                    photo.setSecret(attr.getNodeValue());
                                    break;

                                case SERVER:
                                    photo.setServer(attr.getNodeValue());
                                    break;

                                case FARM:
                                    photo.setFarm(attr.getNodeValue());
                                    break;

                                case TITLE:
                                    photo.setTitle(attr.getNodeValue());
                                    break;
                            }
                        }

                        photo.generatePhotoUrl();
                        photos.add(photo);

                    }
                }
                catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                catch (SAXException e) {
                    e.printStackTrace();
                }


                for(Observable obs: observers){
                    obs.dataReceived(photos);
                }



            } catch (XmlPullParserException e) {

                Log.e(TAG, "ERROR = " + e.toString());
                e.printStackTrace();
            } catch (IOException e) {
                Log.e(TAG, "IO ERROR = " + e.toString());
                e.printStackTrace();
            }

        }
    }


}
