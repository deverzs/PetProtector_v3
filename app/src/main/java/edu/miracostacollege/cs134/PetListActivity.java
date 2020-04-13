package edu.miracostacollege.cs134;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.Model.DBHelper;
import edu.miracostacollege.cs134.Model.Pet;

//Main
public class PetListActivity extends AppCompatActivity {


    private ImageView petImageView;
    private Uri currentImage;
    public static final int RESULT_LOAD_IMAGE = 101;
    public static final String TAG = PetListActivity.class.getSimpleName();

    private DBHelper db;
    private List<Pet> petList ;
    private PetListAdapter petListAdapter ;
    private ListView petListView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        db = new DBHelper(this);

        db.addPet(new Pet( "Fluffy", "Fluffy as a cloud","12547896")) ;
        petImageView = findViewById((R.id.petImageView)) ;
        currentImage = getUriToResource(this, R.drawable.none) ;

        //assign petImageView to current Image in one line of code
        petImageView.setImageURI(currentImage);
        //to read the description as the screen reader, but content description
        //petImageView.setContentDescription(pet.getDescription());


        petList = db.getAllPets() ;
        petListAdapter = new PetListAdapter(this, R.layout.pet_list_item, petList) ;
        petListView = findViewById(R.id.petListView) ;
        petListView.setAdapter(petListAdapter);




    }

    public  void viewPetDetails(View v){
        Pet selectedPet = (Pet) v.getTag() ;

        Intent detailsIntent = new Intent(this, PetDetailsActivity.class) ;
        detailsIntent.putExtra("SelectedPet", selectedPet) ;
        startActivity(detailsIntent);
    }


    //URI not being added --- FIGURE IT OUT
    public void addPetButton(View v) {
        EditText petNameEditText = findViewById(R.id.petNameEditText) ;
        EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        EditText phoneEditText = findViewById(R.id.phoneEditText);

        String name = petNameEditText.getText().toString() ;
        String description = descriptionEditText.getText().toString() ;
        String phone = phoneEditText.getText().toString() ;

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "All fields have to be filled in." , Toast.LENGTH_LONG) ;
            return ;
        }

        Pet newPet = new Pet(description, name, phone) ;
        db.addPet(newPet);
        petListAdapter.add(newPet);

        petNameEditText.setText("");
        descriptionEditText.setText("");
        phoneEditText.setText("");

    }
    //Helper method to construct URI in th form
    //Android.resource:packageName/ResourceTyep/ResourceName

    public static Uri  getUriToResource(Context context, int resID)
    {
        //Get reference to all resources in app
        Resources res = context.getResources();

        String uriString = ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resID)  +
                "/" + res.getResourceTypeName(resID) +
                "/" + res.getResourceEntryName(resID) ;
        //convert string to Uri object
        return Uri.parse(uriString) ;


    }

    //method responds to click of image view
    public void selectPetImage(View v)
    {
        //make up request code for perm, arbitrary
        int permRequestCode = 100;

        //request permission from user since API 29

        //to request from user, see what's already enabled
        //to not ask again, perms is stored
        List<String> permsRequestList = new ArrayList<>();

        //perms: camera, read and write external storage

        //check camera
        //perms are integer codes and GRANTED or not
        int haveCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ;
        int haveReadPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ;
        int haveWritePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ;


        // three states, granted, not or nothing (at the start of install)
        //adding to list to want it to grant
        if(haveCameraPerm != PackageManager.PERMISSION_GRANTED)
        {   permsRequestList.add(Manifest.permission.CAMERA) ;    }
        if(haveReadPerm != PackageManager.PERMISSION_GRANTED)
        {  permsRequestList.add(Manifest.permission.READ_EXTERNAL_STORAGE) ;  }
        if(haveWritePerm != PackageManager.PERMISSION_GRANTED)
        {  permsRequestList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE) ;  }

        //if size of list > 0, we have something to request
        //to request the list has to be an array; easier to start with List
        if(permsRequestList.size() > 0)
        {
            String[] perm = new String[permsRequestList.size()] ;
            // conversion between list and array, helper method built in
            permsRequestList.toArray(perm) ;
            //request
            //we make up requst code, this is the id of the request we are making now
            //if come back as same, it was granted
            ActivityCompat.requestPermissions(this, perm, permRequestCode);
        }

        //must check if all three permissions have been granted
        if(haveCameraPerm == PackageManager.PERMISSION_GRANTED
                && haveReadPerm == PackageManager.PERMISSION_GRANTED
                && haveWritePerm == PackageManager.PERMISSION_GRANTED)
        {
            //open camera gallery
            //intent to pick image from gallery
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_LOAD_IMAGE); //purpose to get a result to load image


        }
        //get result and put in image view
        else //user not given permissions
        {
            //make toast
            Toast.makeText(this, "App needs the access to the external storage. Enable!!", Toast.LENGTH_LONG).show();

        }




    } //end

    //Override onActivityResult, after user has picked something
    //short cut is Cntr+O

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //rid of super
        // this method is called after firing intent for a result
        //when start activity and fire intent, we want a result from it,
        // it will call this method after intent finishes

        //IMPORTANT - result_load_Image constant to identify which activity collecting result from
        //identify which activity
        /// identify that we're loading an image

        //check to see if this parameter request code matches it, to load image
        // result code is not ok if didn;t pick an image
        //result code is ok if actually pick picture

        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) //meaning didn't cancel out of it and they actually picked an image
        {
            //URI - assign curernt image to intent that is chosen
            //data is where URI is coming from
            currentImage = data.getData() ; //reference to picture, intent stores image as URI already
            petImageView.setImageURI(currentImage);
        }

    }


}
