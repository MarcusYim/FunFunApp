package com.marcus.funfunapp;

import android.os.Parcel;
import android.os.Parcelable;

public class DimArrSerial implements Parcelable
{
    private boolean[][] starredArr;

    public DimArrSerial(boolean[][] starredArr)
    {
        this.starredArr = starredArr;
    }

    protected DimArrSerial(Parcel in)
    {
        starredArr = (boolean[][]) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeArray(starredArr);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    public static final Creator<DimArrSerial> CREATOR = new Creator<DimArrSerial>()
    {
        @Override
        public DimArrSerial createFromParcel(Parcel in)
        {
            return new DimArrSerial(in);
        }

        @Override
        public DimArrSerial[] newArray(int size)
        {
            return new DimArrSerial[size];
        }
    };

    public boolean[][] getStarredArr()
    {
        return starredArr;
    }

    public void setStarredArr(boolean[][] starred)
    {
        starredArr = starred;
    }
}
