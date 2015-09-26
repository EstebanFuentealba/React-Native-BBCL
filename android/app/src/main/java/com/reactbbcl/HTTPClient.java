package com.reactbbcl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.StatusLine;

public class HTTPClient {

  // constructor
  public HTTPClient() {}

    public String getJSON(String address){
      	//StringBuilder builder = new StringBuilder();
        String builder = "";
      	HttpClient client = new DefaultHttpClient();
      	HttpGet httpGet = new HttpGet(address);


        try{
          InputStream content;
          BufferedReader reader;
      		HttpResponse response = client.execute(httpGet);
      		StatusLine statusLine = response.getStatusLine();
      		int statusCode = statusLine.getStatusCode();
      		if(statusCode == 200){
      			HttpEntity entity = response.getEntity();
      			content = entity.getContent();
      			reader = new BufferedReader(new InputStreamReader(content));
      			String line;
      			while((line = reader.readLine()) != null){
      				//builder.append(line);
              builder += line;
      			}
      		} else {
      		}

      	}catch(ClientProtocolException e){
      		e.printStackTrace();
      	} catch (IOException e){
      		e.printStackTrace();
      	}
        return builder;
        //return "{\"person\":{\"name\":\"A\",\"age\":30,\"children\":[{\"name\":\"B\",\"age\":5}," + "\"name\":\"C\",\"age\":7},{\"name\":\"D\",\"age\":9}]}}";
      	//return builder.toString();
      }

}
