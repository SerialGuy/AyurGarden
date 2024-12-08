package com.example.ayurgarden;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AlbumsActivity extends AppCompatActivity {
    private String[] model_list={"https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/main/2.0/2CylinderEngine/glTF/2CylinderEngine.gltf"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

    }
    public void onClickAlbum(View v){
        Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
        sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Avocado/glTF/Avocado.gltf"));
        sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
        startActivity(sceneViewerIntent);
    }
    public void onClickAlbum2(View v){
        Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
        sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/SerialGuy/AyurGarden/main/3DModels/Cactus.gltf"));
        sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
        startActivity(sceneViewerIntent);
    }
    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px * (displayMetrics.densityDpi / 160f));
    }

    public void onCreateNewCard(View v){
        LinearLayout gridLayout = findViewById(R.id.rootGrid);

        CardView cardView = new CardView(this);
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.setMargins(pxToDp(this,10), pxToDp(this,10), pxToDp(this,10), pxToDp(this,10));
        cardView.setLayoutParams(layoutParams);
        cardView.setCardBackgroundColor(Color.parseColor("#807F7D"));
        cardView.setRadius(5f);
        cardView.setContentPadding(pxToDp(this,10), pxToDp(this,10), pxToDp(this,10), pxToDp(this,10));
        cardView.setCardElevation(4f);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAlbum(v);
            }
        });

// Create content for the CardView
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(pxToDp(this,150),pxToDp(this,150));
        frameLayout.setLayoutParams(frameLayoutParams);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.drawable.img);
        frameLayout.addView(imageView);

        linearLayout.addView(frameLayout);

        LinearLayout horizontalLayout = new LinearLayout(this);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView textView = new TextView(this);
        textView.setText("Avocado");
        textView.setPadding(pxToDp(this,10), pxToDp(this,10), pxToDp(this,10), pxToDp(this,10));
        horizontalLayout.addView(textView);

        RatingBar ratingBar = new RatingBar(this, null, android.R.attr.ratingBarStyleSmall);
        ratingBar.setNumStars(5);
        ratingBar.setRating(3);
        horizontalLayout.addView(ratingBar);

        linearLayout.addView(horizontalLayout);

        cardView.addView(linearLayout);

// Add the CardView to the GridLayout
        gridLayout.addView(cardView);

    }


    private void launchSceneViewer(Context context, String modelUrl, boolean isAugmented) {
        Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);

        // Prepare the model URL
        Uri modelUri = Uri.parse("https://arvr.google.com/scene-viewer/1.0")
                .buildUpon()
                .appendQueryParameter("file", modelUrl)
                .appendQueryParameter("mode", isAugmented ? "ar_only" : "3d_only")
                .build();

        sceneViewerIntent.setData(modelUri);
        sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");

        if (sceneViewerIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(sceneViewerIntent);
        } else {
            // Handle the case where ARCore or Scene Viewer is not available
            // You can prompt the user to install ARCore or handle it accordingly
        }
    }

    public void onClickPlus(View v){
        launchSceneViewer(this,"https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/main/2.0/2CylinderEngine/glTF/2CylinderEngine.gltf",true);
    }


}
