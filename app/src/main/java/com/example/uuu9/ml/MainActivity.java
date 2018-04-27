package com.example.uuu9.ml;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnpic;
    ImageView imgTakenPic;
    private static final int CAM_REQUEST=1313;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnpic = (Button) findViewById(R.id.button);
        imgTakenPic = (ImageView)findViewById(R.id.imageView);
        btnpic.setOnClickListener(new btnTakePhotoClicker());
        imgTakenPic.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgTakenPic.setImageBitmap(bitmap);
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {


                AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                animation1.setDuration(500);

                animation1.setFillAfter(true);
                imgTakenPic.startAnimation(animation1);


            }
        }, 1 * 1000);
        new Handler().postDelayed(new Runnable() {
            public void run() {


                Intent intentMain = new Intent(MainActivity.this ,
                        Main2Activity.class);
                MainActivity.this.startActivity(intentMain);


            }
        }, 1 * 2000);

    }

    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }
}