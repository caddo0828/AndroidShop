package com.example.shop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.example.product.Product;
import com.example.product.ProductAdapter;
import com.example.thread.ProductHttpThread;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProductHttpThread productHttpThread = new ProductHttpThread();
        productHttpThread.start();

        try{
            productHttpThread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        List<Product> list = JSON.parseArray(productHttpThread.getResult(), Product.class);
        ProductAdapter productAdapter = new ProductAdapter(this, R.layout.product, list);

        ListView listView =(ListView) findViewById(R.id.listView);
        listView.setAdapter(productAdapter);

        //跳转到另外一个界面
        ImageButton imageButton = (ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this ,shopping.class );
                startActivity(intent);
            }
        });

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
        if (id == R.id.action_settings) {
            return true;
       }

        return super.onOptionsItemSelected(item);
    }
}
