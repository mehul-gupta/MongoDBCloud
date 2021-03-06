package com.example.mehul.mongodbcloud;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by DELL on 2/24/2018.
 */

public class HTTPDataHandler{

    static String stream=null;

    public HTTPDataHandler(){
    }
    public String GetHTTPData(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            if(urlConnection.getResponseCode()==200)
            {
                InputStream in= new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r= new BufferedReader(new InputStreamReader(in));
                StringBuilder ab=new StringBuilder();
                String line;
                while((line=r.readLine())!=null)
                    ab.append(line);
                stream=ab.toString();
                urlConnection.disconnect();
            }
            else{
                Log.d("url","Error_in_url");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream;
    }

    public void PostHttpData(String urlString, String json) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            Log.d("post",json.toString());

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            urlConnection.connect();
            try(OutputStream os = urlConnection.getOutputStream()){
                os.write(out);
            }
            InputStream response = urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (ProtocolException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void PutHttpData(String urlString, String newValue){
        try{
            URL url =new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);

            byte[] out=newValue.getBytes(StandardCharsets.UTF_8);
            int length=out.length;

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-type","application/json; charset=UTF-8");
            urlConnection.connect();
            try(OutputStream os=urlConnection.getOutputStream()){
                os.write(out);
            }
            InputStream response=urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (ProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void DeleteHttpData(String urlString, String json){
        try{
            URL url =new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("DELETE");
            urlConnection.setDoOutput(true);

            byte[] out=json.getBytes(StandardCharsets.UTF_8);
            int length=out.length;

            urlConnection.setFixedLengthStreamingMode(length);
            urlConnection.setRequestProperty("Content-type","application/json; charset=UTF-8");
            urlConnection.connect();
            try(OutputStream os=urlConnection.getOutputStream()){
                os.write(out);
            }
            InputStream response=urlConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}