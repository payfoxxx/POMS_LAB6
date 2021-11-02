package com.example.lab2;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryItem implements Parcelable {
    private String a;
    private String b;
    private String c;
    private String result1;
    private String result2;
    private String type;

    public String getOperandA() {return a;}
    public String getOperandB() {return b;}
    public String getOperandC() {return c;}
    public String getOperandRes1() {return result1;}
    public String getOperandRes2() {return result2;}
    public String getOperandType() {return type;}

    public HistoryItem(String a, String b, String c, String result1, String result2,String type){
        this.a = a;
        this.b = b;
        this.c = c;
        this.result1 = result1;
        this.result2 = result2;
        this.type = type;
    }



    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>() {
        @Override
        public HistoryItem createFromParcel(Parcel in) {
            return new HistoryItem(in);
        }

        @Override
        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };

    public String getTextRepresentation(){
        String textRepresentation;

        if(type.equals("num")){
        textRepresentation = String.format("Quadratic equation %1sx^2 + %2sx + %3s Result 1: %4s, Result 2: %5s",
                a,b,c,result1,result2);
        } else{
            textRepresentation = String.format("Quadratic equation %1sx^2 + %2sx + %3s has top of the parabola in x0: %4s, y0: %5s",
                    a,b,c,result1,result2);
        }
        return textRepresentation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected HistoryItem(Parcel in) {
        a = in.readString();
        b = in.readString();
        c = in.readString();
        result1 = in.readString();
        result2 = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(a);
        dest.writeString(b);
        dest.writeString(c);
        dest.writeString(result1);
        dest.writeString(result2);
        dest.writeString(type);
    }
}
