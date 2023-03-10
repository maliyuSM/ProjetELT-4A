package com.example.testyourandroidsmartphone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.widget.RelativeLayout;

public class Ecran_Tactile extends AppCompatActivity {
    private RelativeLayout MyLayout;
    private ToucheScreenComponent MyComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ecran_tactile);
        MyLayout = findViewById(R.id.activity_ecran);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MyComponent = new ToucheScreenComponent(this, 400, 400, 500, 500) {
            };
        }
        MyLayout.addView(MyComponent);
    }
}
