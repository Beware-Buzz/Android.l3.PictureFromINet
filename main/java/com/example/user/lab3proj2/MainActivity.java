package com.example.user.lab3proj2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    final String path = "http://open.ua/uploads/ckeditor/images/hetlive.jpg";
    ImageView iv;
    Button b;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button)findViewById(R.id.button);
        iv = (ImageView)findViewById(R.id.imageView);
        tv = (TextView)findViewById(R.id.textView2);
        tv.setText(path);
        Thread t = new Thread()  {
            @Override
            public void run() {
                try {
                    final Bitmap bitmap = BitmapFactory.decodeStream(new URL(path).openStream());
                    iv.post(new Runnable() {
                        @Override
                        public void run() {
                            iv.setImageBitmap(bitmap);
                        }
                    });
                } catch (Exception e) { e.printStackTrace(); }
            };
        };
        iv.setImageResource(R.drawable.q);
        t.start();
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
                startActivity(browserIntent);
            }
        };
        b.setOnClickListener(onClickListener);
    }


}
