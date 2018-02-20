package br.com.fernando.control_password.model;

import android.os.Parcel;
import android.os.Parcelable;


public class RegisterRequest implements Parcelable {

    public static final Creator<RegisterRequest> CREATOR = new Creator<RegisterRequest>() {
        @Override
        public RegisterRequest createFromParcel(Parcel source) {
            return new RegisterRequest(source);
        }

        @Override
        public RegisterRequest[] newArray(int size) {
            return new RegisterRequest[size];
        }
    };

    private String userName;
    private String email;
    private String password;


    public RegisterRequest() {
    }

    protected RegisterRequest(Parcel in) {
        this.email = in.readString();
        this.userName = in.readString();
        this.password = in.readString();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.userName);
        dest.writeString(this.password);
    }
}