package edu.miracostacollege.cs134.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * The animals to protect, Parcelable
 */
public class Pet implements Parcelable {

    //instance variables
    private long mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageURI;

    /**
     * Constructor for the parcelable
     * @param in  the parcel to parcelize
     */
    private Pet(Parcel in){
        mId = in.readLong() ;
        mName = in.readString() ;
        mDetails = in.readString() ;
        mPhone = in.readString() ;
        mImageURI = Uri.parse(in.readString()) ;
    }

    /**
     * Full constructor
     * @param mId the id
     * @param mName  the name
     * @param mDetails  the details/description
     * @param mPhone phone number
     * @param mImageURI  the image
     */
    public Pet(long mId, String mName, String mDetails, String mPhone, Uri mImageURI) {
        this.mId = mId;
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    /**
     * Constructor without the id
     * @param mName  name of animal
     * @param mDetails  the details
     * @param mPhone the phone
     * @param mImageURI  the image
     */
    public Pet( String mName, String mDetails, String mPhone, Uri mImageURI) {
        this.mName = mName;
        this.mDetails = mDetails;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    /**
     * Set the id once gotten from the database when a new animal is created
     * @param id  long id
     */
    public void setmId(long id){
        this.mId = id;
    }

    /**
     * CREATOR for parcelable
     */
    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel source) {
            return new Pet(source);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    } ;

    /**
     * For parcelable
     * @return the content
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * ability to write the parcel
     * @param dest  which parcel
     * @param flags  integer
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDetails);
        dest.writeString(mPhone);
        dest.writeString(String.valueOf(mImageURI));

    }

    /**
     * Get the details of the animal
     * @return the details
     */
    public String getmDetails() {
        return mDetails;
    }

    /**
     * Set the details of an animal
     * @param mDetails  the details
     */
    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    /**
     * Get the id of the animal
     * @return the id
     */
    public long getmId() {
        return mId;
    }

    /**
     * Get the name of the animal
     * @return the name
     */
    public String getmName() {
        return mName;
    }

    /**
     * Set the name of the animal
     * @param mName  the name
     */
    public void setmName(String mName) {
        this.mName = mName;
    }

    /**
     * Get the phone number of the animal
     * @return  the phone number
     */
    public String getmPhone() {
        return mPhone;
    }

    /**
     * Set the phone of the animal
     * @param mPhone the phone
     */
    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    /**
     * Get the image URI of the animal
     * @return  the image URI
     */
    public Uri getmImageURI() {
        return mImageURI;
    }

    /**
     * Set the image URI
     * @param mImageURI the image URI
     */
    public void setmImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

    /**
     * Create a string of the animal
     * @return the string with all the data
     */
    @Override
    public String toString() {
        return "Pet{" +
                "mDetails='" + mDetails + '\'' +
                ", mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mImageURI=" + mImageURI +
                '}';
    }




}
