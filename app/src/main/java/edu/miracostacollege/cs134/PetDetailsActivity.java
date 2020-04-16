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

/**
 * The Details of the Activity
 */
public class PetDetailsActivity extends AppCompatActivity {

    //TAG
    public static final String TAG = PetDetailsActivity.class.getSimpleName() ;

    /**
     * On create the acitivy_pet_details
     * @param savedInstanceState  the saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        //wire it up
        ImageView petDetailsImageView = findViewById(R.id.petDetailsImageView) ;
        TextView petNameDetailsTextView = findViewById(R.id.petNameDetailsTextView) ;
        TextView descriptionDetailsTextView = findViewById(R.id.descriptionDetailsTextView) ;
        TextView phoneDetailsTextView = findViewById(R.id.phoneDetailsTextView) ;

        //get the intent from main
        Intent detailsIntent = getIntent() ;

        //get the parcel of pet selected
        Pet selectedPet =detailsIntent.getParcelableExtra("SelectedPet") ;

        //get the selected animal's data
        petDetailsImageView.setImageURI(selectedPet.getmImageURI());
        petNameDetailsTextView.setText(selectedPet.getmName());
        descriptionDetailsTextView.setText(selectedPet.getmDetails());
        phoneDetailsTextView.setText(selectedPet.getmPhone());
    }
}
