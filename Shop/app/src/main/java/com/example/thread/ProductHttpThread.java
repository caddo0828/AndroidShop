package com.example.thread;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ProductHttpThread  extends  Thread{
    private String result;

       @Override
         public void run() {

               try {
                         URL url = new URL("http://192.168.56.1:8080/shopping/product");
                         HttpURLConnection httpURLConnection  = (HttpURLConnection) url.openConnection();
                         InputStream is = httpURLConnection.getInputStream();
                         InputStreamReader inputStreamReader = new InputStreamReader(is, "utf-8");
                         BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                         StringBuffer stringBuffer = new StringBuffer();
                          String temp ;
                      while((temp = bufferedReader.readLine()) != null){
                                stringBuffer.append(temp);
                            }
                         setResult(stringBuffer.toString());
                    } catch (IOException e) {
                       e.printStackTrace();
                     }
            }


        public String getResult() {
                 return result;
        }

        public void setResult(String result) {
                 this.result = result;
        }


}
