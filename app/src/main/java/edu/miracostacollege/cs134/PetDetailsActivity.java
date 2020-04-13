package edu.miracostacollege.cs134;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import edu.miracostacollege.cs134.Model.Pet;

public class PetDetailsActivity extends AppCompatActivity {

    public static final String TAG = PetDetailsActivity.class.getSimpleName() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        ImageView petDetailsImageView = findViewById(R.id.petDetailsImageView) ;
        TextView petNameDetailsTextView = findViewById(R.id.petNameDetailsTextView) ;
        TextView descriptionDetailsTextView = findViewById(R.id.descriptionDetailsTextView) ;
        TextView phoneDetailsTextView = findViewById(R.id.phoneDetailsTextView) ;

        Intent detailsIntent = getIntent() ;

        Pet selectedPet =detailsIntent.getParcelableExtra("SelectedPet") ;

        AssetManager am = getAssets() ;

        InputStream stream = null;
        try {
            stream = am.open(String.valueOf(selectedPet.getmImageURI()));
        } catch (IOException e) {
            Log.e(TAG, "Error loading: " + selectedPet.getmName(), e);
        }
        Drawable image = Drawable.createFromStream(stream, selectedPet.getmName()) ;
        petDetailsImageView.setImageDrawable(image);

        petNameDetailsTextView.setText(selectedPet.getmName());
        descriptionDetailsTextView.setText(selectedPet.getmDetails());
        phoneDetailsTextView.setText(selectedPet.getmPhone());
    }
}
