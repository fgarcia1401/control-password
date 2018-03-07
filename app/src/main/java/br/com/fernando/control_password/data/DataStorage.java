package br.com.fernando.control_password.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

public final class DataStorage {

    private static final String CONTROL_PASSWD_PREFERENCE = "CONTROL_PASSWD_PREFERENCE";
    private static final String TOKEN = "TOKEN";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String FINGER_PRINT_AUTHENTICATE = "FINGER_PRINT_AUTHENTICATE";
    private static final String FINGER_PRINT_FIRST_TIME = "FINGER_PRINT_FIRST_TIME";


    private static DataStorage instance;
    private SharedPreferences sharedPreferences;

    private DataStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(CONTROL_PASSWD_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static DataStorage getInstance(Context context) {
        if (instance == null) {
            instance = new DataStorage(context);
        }
        return instance;
    }


    public String getToken() {
        String accessToken = sharedPreferences.getString(TOKEN, null);
        if (!TextUtils.isEmpty(accessToken)) {
            byte[] data = Base64.decode(accessToken, Base64.DEFAULT);
            accessToken = new String(data);
        }
        return accessToken;
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        token = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        email = Base64.encodeToString(email.getBytes(), Base64.DEFAULT);
        editor.putString(EMAIL, email);
        editor.commit();
    }

    public String getEmail() {
        String email = sharedPreferences.getString(EMAIL, null);
        if (!TextUtils.isEmpty(email)) {
            byte[] data = Base64.decode(email, Base64.DEFAULT);
            email = new String(data);
        }
        return email;
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        password = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
        editor.putString(PASSWORD, password);
        editor.commit();
    }

    public String getPassword() {
        String password = sharedPreferences.getString(PASSWORD, null);
        if (!TextUtils.isEmpty(password)) {
            byte[] data = Base64.decode(password, Base64.DEFAULT);
            password = new String(data);
        }
        return password;
    }

    public void setFingerPrintFirstTime(Boolean fingerPrintAuthenticate) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FINGER_PRINT_FIRST_TIME, fingerPrintAuthenticate);
        editor.commit();
    }

    public boolean getFingerPrintFirstTime() {
        return sharedPreferences.getBoolean(FINGER_PRINT_FIRST_TIME, true);
    }

    public void setFingerPrintAuthenticate(Boolean fingerPrintAuthenticate) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FINGER_PRINT_AUTHENTICATE, fingerPrintAuthenticate);
        editor.commit();
    }

    public boolean getFingerPrintAuthenticate() {
        return sharedPreferences.getBoolean(FINGER_PRINT_AUTHENTICATE, false);
    }

    public final void clearOnLogoff() {
        sharedPreferences.edit().clear().commit();
    }
}
