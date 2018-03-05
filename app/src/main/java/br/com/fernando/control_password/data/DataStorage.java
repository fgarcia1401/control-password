package br.com.fernando.control_password.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

public final class DataStorage {

    public static final String PIN = "PIN";
    private static final String CONVOCE_PREFERENCE = "CONVOCE_PREFERENCE";
    private static final String USER_NAME = "USER_NAME";
    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String PIN_EXISTS = "PIN_EXISTS";
    private static final String DEVICE_SECURE = "DEVICE_SECURE";
    private static final String ACCOUNT_CREATED = "ACCOUNT_CREATED";
    private static final String DDD_USER = "DDD_USER";
    private static final String LOGIN_USERNAME = "LOGIN_USERNAME";
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static final String LOGIN_DEVICE_SECURE_TOKEN = "LOGIN_DEVICE_SECURE_TOKEN";
    private static final String CARD_FAVORITE = "CARD_FAVORITE";
    private static final String CARD_ALIAS = "CARD_ALIAS";
    private static final String CAN_USE_FINGERPRINT = "CAN_USE_FINGER_PRINT";
    private static final String CAN_USE_NOTIFICATION = "CAN_USE_NOTIFICATION";
    private static final String CURSOR_CONTACTS_SIZE = "CURSOR_CONTACTS_SIZE";
    private static DataStorage instance;
    private SharedPreferences sharedPreferences;

    private DataStorage(Context context) {
        sharedPreferences = context.getSharedPreferences(CONVOCE_PREFERENCE, Context.MODE_PRIVATE);
    }

    public static DataStorage getInstance(Context context) {
        if (instance == null) {
            instance = new DataStorage(context);
        }
        return instance;
    }

    public String getDDDUser() {
        String email = sharedPreferences.getString(DDD_USER, "");
        byte[] data = Base64.decode(email, Base64.DEFAULT);
        return new String(data);
    }

    public void setDDDUser(String ddd) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ddd = Base64.encodeToString(ddd.getBytes(), Base64.DEFAULT);
        editor.putString(DDD_USER, ddd);
        editor.commit();
    }

    public void setAccountAlreadyCreated(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ACCOUNT_CREATED + email, true);
        editor.commit();
    }

    public Boolean getAccountAlreadyCreated(String email) {
        return sharedPreferences.getBoolean(ACCOUNT_CREATED + email, false);
    }

    public String getAccessToken() {
        String accessToken = sharedPreferences.getString(ACCESS_TOKEN, null);
        if (!TextUtils.isEmpty(accessToken)) {
            byte[] data = Base64.decode(accessToken, Base64.DEFAULT);
            accessToken = new String(data);
        }
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        accessToken = Base64.encodeToString(accessToken.getBytes(), Base64.DEFAULT);
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public boolean isPinExists() {
        return sharedPreferences.getBoolean(PIN_EXISTS, false);
    }

    public void setPinExists(boolean pinExists) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(PIN_EXISTS, pinExists);
        editor.commit();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String userName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getLoginUserID() {
        String uuid = sharedPreferences.getString(LOGIN_USER_ID, "");
        byte[] data = Base64.decode(uuid, Base64.DEFAULT);
        return new String(data);
    }

    public void setLoginUserID(String userID) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        userID = Base64.encodeToString(userID.getBytes(), Base64.DEFAULT);
        editor.putString(LOGIN_USER_ID, userID);
        editor.commit();
    }

    public String getLoginUsername() {
        String email = sharedPreferences.getString(LOGIN_USERNAME, "");
        byte[] data = Base64.decode(email, Base64.DEFAULT);
        return new String(data);
    }

    public void setLoginUsername(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        username = Base64.encodeToString(username.getBytes(), Base64.DEFAULT);
        editor.putString(LOGIN_USERNAME, username);
        editor.commit();
    }

    public void setLoginDeviceSecureToken(String device) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        device = Base64.encodeToString(device.getBytes(), Base64.DEFAULT);
        editor.putString(LOGIN_DEVICE_SECURE_TOKEN, device);
        editor.commit();
    }

    public boolean isDeviceSecure() {
        return sharedPreferences.getBoolean(DEVICE_SECURE, false);
    }

    public void setDeviceSecure(boolean deviceSecure) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DEVICE_SECURE, deviceSecure);
        editor.commit();
    }

    public void saveFingerprintConfig(final boolean enable) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CAN_USE_FINGERPRINT, enable);
        editor.commit();
    }

    public boolean isFingerprintEnable() {
        return sharedPreferences.getBoolean(CAN_USE_FINGERPRINT, true);
    }

    public void saveNotificationConfig(final boolean enable) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CAN_USE_NOTIFICATION, enable);
        editor.commit();
    }

    public boolean isNotificationEnable() {
        return sharedPreferences.getBoolean(CAN_USE_NOTIFICATION, true);
    }

    public String getPin() {
        String pin = sharedPreferences.getString(PIN, null);
        if (!TextUtils.isEmpty(pin)) {
            byte[] data = Base64.decode(pin, Base64.DEFAULT);
            pin = new String(data);
        }
        return pin;
    }

    public void setPin(String pin) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        pin = Base64.encodeToString(pin.getBytes(), Base64.DEFAULT);
        editor.putString(PIN, pin);
        editor.commit();
    }

    public String getCardFavorite() {
        return sharedPreferences.getString(CARD_FAVORITE, "");
    }

    public void setCardFavorite(String cardFavorite) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CARD_FAVORITE, cardFavorite);
        editor.commit();
    }

    public void setCardAlias(String card, String alias) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CARD_ALIAS + card, alias);
        editor.commit();
    }

    public String getCardAlias(String card) {
        return sharedPreferences.getString(CARD_ALIAS + card, null);
    }

    public int getCursorContactsSize() {
        return sharedPreferences.getInt(CURSOR_CONTACTS_SIZE, 0);
    }

    public void setCursorContactsSize(int size) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURSOR_CONTACTS_SIZE, size);
        editor.commit();
    }

    public final void clearOnLogoff() {
        sharedPreferences.edit().clear().commit();
    }
}
