package com.futuristic.careweb.clients;


import android.os.AsyncTask;

import com.futuristic.careweb.beans.AuthResponse;
import com.google.gson.Gson;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;


public class AuthClient  {
    private static final String contextPath =  "http://192.168.1.4:9999/careweb/";
    public static AsyncHttpClient getClient(){
        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config().setConnectTimeout(500);
        return  Dsl.asyncHttpClient(clientBuilder);
    }

    public static BoundRequestBuilder buildPostRequest(String endPoint, Object data, String type, String token){
        String url =  contextPath + endPoint;
        String json = new Gson().toJson(data);
        if(type.equalsIgnoreCase("login") || type.equalsIgnoreCase("signup")) {
            return getClient().preparePost(url).setBody(json).addHeader("content-type", "application/json");
        } else {
            AuthResponse response = (AuthResponse) data;
            return getClient().preparePost(url).setBody(json)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "Bearer " + token);

        }
    }
    private class Connection extends AsyncTask {

        @Override
        protected Object doInBackground(Object... arg0) {
            {
                BoundRequestBuilder request = (BoundRequestBuilder) arg0[0];
                Response response = null;
                Gson gson = new Gson();
                try {
                    ListenableFuture<Object> futureObj = request.execute(new AsyncCompletionHandler<Object>() {
                        @Override
                        public void onThrowable(Throwable t) {
                            t.printStackTrace();
                        }

                        @Override
                        public State onStatusReceived(HttpResponseStatus status) throws Exception {
                            return super.onStatusReceived(status);
                        }

                        @Override
                        public Object onCompleted(Response response) throws Exception {
                            return response;
                        }
                    });
                    while (!futureObj.isDone())
                        System.out.println("Waiting for Response...");
                    response = (Response) futureObj.get();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                return response;
            }
        }

    }
    public Response executeRequest(BoundRequestBuilder request){
        Response response = null;
        try {
            response = (Response) new Connection().execute(request).get();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return response;
    }

}
