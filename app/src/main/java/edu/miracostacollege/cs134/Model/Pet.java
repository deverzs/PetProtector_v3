package edu.miracostacollege.cs134.Model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Pet implements Parcelable {
    private long mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageURI;

    private Pet(Parcel in){
        mId = in.readLong() ;
        mName = in.readString() ;
        mDetails = in.readString() ;
        mPhone = in.readString() ;
        mImageURI = Uri.parse(in.readString()) ;
    }

    public Pet(long mId, String mName, String mDetails, String mPhone, Uri mImageURI) {
        this.mDetails = mDetails;
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    public Pet(long mId, String mName, String mDetails, String mPhone) {
        this.mDetails = mDetails;
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public Pet( String mName, String mDetails, String mPhone, Uri mImageURI) {
        this.mDetails = mDetails;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    public Pet(String mName, String mDetails,  String mPhone) {
        this.mDetails = mDetails;
        this.mName = mName;
        this.mPhone = mPhone;
    }


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDetails);
        dest.writeString(mPhone);
        dest.writeString(String.valueOf(mImageURI));

    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public long getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Uri getmImageURI() {
        return mImageURI;
    }

    public void setmImageURI(Uri mImageURI) {
        this.mImageURI = mImageURI;
    }

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
