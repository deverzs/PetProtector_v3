package edu.miracostacollege.cs134;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

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

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(mResource, null) ;

        Pet selectedPet = mAllPets.get(position) ;

        return view;
    }
}
