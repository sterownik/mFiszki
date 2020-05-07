package com.example.mfiszki;

public class Upload {
    private  String mName;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUurl() {
        return mImageUurl;
    }

    public void setmImageUurl(String mImageUurl) {
        this.mImageUurl = mImageUurl;
    }

    private  String mImageUurl;

    public  Upload()
    {

    }
    public  Upload(String name, String imageUurl)
    {
        if(name.trim().equals(""))
        {
            name="noname";
        }

        mName=name;
        mImageUurl=imageUurl;
    }
}
