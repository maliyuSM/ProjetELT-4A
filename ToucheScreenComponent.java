package com.example.testyourandroidsmartphone;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

@RequiresApi(api = Build.VERSION_CODES.M)
public class ToucheScreenComponent extends ConstraintLayout {
    private ConstraintLayout Component;
    private ImageView Contour;
    private View Fond;
    int width;
    int height;
    int positionX, positionY;
    Activity Context;
    static int usableHeight;
    static int usableWidth;

    public ToucheScreenComponent(Activity context, int width, int height, int positionX, int positionY) {
        super((Context) context);
        Context = context;
        LayoutInflater inflater = LayoutInflater.from(Context);
        Component = (ConstraintLayout) inflater.inflate(R.layout.touch_screen_component, this, false);
        Contour = findViewById(R.id.contour);
        Fond = findViewById(R.id.fond);
        getUsableScreenSize();

        ConstraintLayout.LayoutParams params1 = new ConstraintLayout.LayoutParams(ConvertToPixels(width), ConvertToPixels(height));
        params1.setMargins(positionX,usableHeight-positionY,usableWidth-positionX, positionY);
        //Ajouter la position
        this.setLayoutParams(params1);
        ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(ConvertToPixels(width - 1 / 20 * width), ConvertToPixels(height - 1 / 20 * height));
        Component.setLayoutParams(params2);
        addView(Component);
    }

    public int ConvertToPixels(int dp) {
        float scale = Context.getResources().getDisplayMetrics().density;
        int pixels = (int) (dp * scale + 0.5f);
        return pixels;
    }

    public void getUsableScreenSize();

    {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        usableWidth = displayMetrics.widthPixels;
        usableHeight = displayMetrics.heightPixels;

        if (Build.VERSION.SDK_INT >= 17)
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                DisplayCutout displayCutout = Context.getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();

                if(displayCutout != null)
                {
                    Rect rect = displayCutout.getBoundingRects().get(0);
                    usableHeight -= rect.bottom - rect.top;
                }
            }

        }
        else {
            Resources resources = Context.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen","android");
            usableHeight -= resources.getDimensionPixelSize(resourceId);
        }
    }
}
