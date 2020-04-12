package edu.miracostacollege.cs134.Model;

import android.net.Uri;

public class Pet {
    private String mDetails;
    private int mId;
    private String mName;
    private String mPhone;
    private Uri mImageURI;

    public Pet(String mDetails, int mId, String mName, String mPhone, Uri mImageURI) {
        this.mDetails = mDetails;
        this.mId = mId;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    public Pet(String mDetails, String mName, String mPhone, Uri mImageURI) {
        this.mDetails = mDetails;
        this.mName = mName;
        this.mPhone = mPhone;
        this.mImageURI = mImageURI;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public int getmId() {
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
