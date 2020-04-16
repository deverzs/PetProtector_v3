package edu.miracostacollege.cs134;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import edu.miracostacollege.cs134.Model.Pet;


/**
 * Pet List Adapter for the animals
 */
public class PetListAdapter extends ArrayAdapter {

    //instance variables
    private Context mContext ;
    private int mResource;
    private List<Pet> mAllPets;


    /**
     * The pet list adapter
     * @param context  where the list is coming from
     * @param resource the resources
     * @param objects  the list of objects
     */
    public PetListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        mContext = context ;
        mResource = resource ;
        mAllPets = objects ;
    }

    /**
     * Get the view that is overriden to retrieve the data
     * @param position  the selected pet
     * @param convertView  from the view
     * @param parent  to the view
     * @return  the view that is the selected pet
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //the selected animal's location in the list
        final Pet selectedPet = mAllPets.get(position) ;

        //inflate the list
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(mResource, null) ;

        //wire it up
        LinearLayout petListLinearLayout = view.findViewById(R.id.petListLinearLayout) ;
        ImageView petListImageView = view.findViewById(R.id.petListImageView) ;
        TextView nameListTextView = view.findViewById(R.id.nameListTextView) ;
        TextView descriptionListTextView = view.findViewById(R.id.descriptionListTextView) ;

        //set the tag
        petListLinearLayout.setTag(selectedPet);

        //get the animal's data and set it in the view
        nameListTextView.setText(selectedPet.getmName());
        descriptionListTextView.setText(selectedPet.getmDetails());
        Log.e( "PetListAdapter====> " , selectedPet.getmImageURI().toString()) ;
        petListImageView.setImageURI(selectedPet.getmImageURI());

        //return the created view
        return  view;

    }
}
