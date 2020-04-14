package edu.miracostacollege.cs134;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.security.auth.login.LoginException;

import edu.miracostacollege.cs134.Model.Pet;

public class PetListAdapter extends ArrayAdapter {

    private Context mContext ;
    private int mResource;
    private List<Pet> mAllPets;


    public PetListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context ;
        mResource = resource ;
        mAllPets = objects ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Pet selectedPet = mAllPets.get(position) ;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(mResource, null) ;

        LinearLayout petListLinearLayout = view.findViewById(R.id.petListLinearLayout) ;
        ImageView petListImageView = view.findViewById(R.id.petListImageView) ;
        TextView nameListTextView = view.findViewById(R.id.nameListTextView) ;
        TextView descriptionListTextView = view.findViewById(R.id.descriptionListTextView) ;

        petListLinearLayout.setTag(selectedPet);
        nameListTextView.setText(selectedPet.getmName());
        descriptionListTextView.setText(selectedPet.getmDetails());
        Log.e( "PetListAdapter====> " , selectedPet.getmImageURI().toString()) ;
        petListImageView.setImageURI(selectedPet.getmImageURI());

        return  view;

    }
}
