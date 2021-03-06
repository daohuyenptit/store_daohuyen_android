package com.daohuyen.dell.store_cosmetics.common;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.daohuyen.dell.store_cosmetics.model.Customer;
import com.daohuyen.dell.store_cosmetics.model.Profile;
import com.daohuyen.dell.store_cosmetics.model.User;
import com.daohuyen.dell.store_cosmetics.model.view.CustomerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {



    public static void setSharePreferenceValues(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookmanager", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getSharePreferenceValues(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookmanager", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }
    public static void saveCustomer(Context context, CustomerView customerView) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        if (customerView == null) {
            editor.putString(Constants.PRE_AVATAR_URL, null);
            editor.putString(Constants.PRE_FULL_NAME, null);
            editor.putString(Constants.PRE_EMAIL, null);
            editor.putString(Constants.PRE_USERNAME, null);
            editor.putString(Constants.PRE_ADDRESS, null);
            editor.putString(Constants.PRE_PHONE, null);
            editor.putString(Constants.PRE_DES, null);
            editor.putString(Constants.PRE_PASS, null);
            editor.putString(Constants.PRE_BIRTH, null);
            editor.putString(Constants.PRE_GENDER, null);
            editor.putString(Constants.PRE_IDENTICARD, null);



        } else {
            editor.putString(Constants.PRE_AVATAR_URL, customerView.getAvatarUrl());
            editor.putString(Constants.PRE_FULL_NAME, customerView.getFullName());
            editor.putString(Constants.PRE_USERNAME, customerView.getAccount());
            editor.putString(Constants.PRE_EMAIL, customerView.getEmail());
            editor.putString(Constants.PRE_ADDRESS, customerView.getAddress());
            editor.putString(Constants.PRE_PHONE, customerView.getPhone());
            editor.putString(Constants.PRE_DES, customerView.getDescription());
            editor.putLong(Constants.PRE_BIRTH, customerView.getBirthday());
            editor.putString(Constants.PRE_GENDER, customerView.getGender());
            editor.putString(Constants.PRE_IDENTICARD, customerView.getIdentityCard());
        }
        editor.apply();
    }
    public static CustomerView getCustomerview(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        CustomerView customerView;
        customerView = new CustomerView();
        User user= new User();
        user.setUsername(preferences.getString(Constants.PRE_USERNAME, null));
        customerView.setAvatarUrl(preferences.getString(Constants.PRE_AVATAR_URL, null));
        customerView.setFullName(preferences.getString(Constants.PRE_FULL_NAME, null));
        customerView.setAddress(preferences.getString(Constants.PRE_ADDRESS, null));
        customerView.setBirthday(preferences.getLong(Constants.PRE_BIRTH, 0));
        customerView.setDescription(preferences.getString(Constants.PRE_DES, null));
        customerView.setEmail(preferences.getString(Constants.PRE_EMAIL, null));
        customerView.setGender(preferences.getString(Constants.PRE_GENDER, null));

        customerView.setIdentityCard(preferences.getString(Constants.PRE_IDENTICARD, null));
        customerView.setPhone(preferences.getString(Constants.PRE_PHONE, null));


        return customerView;
    }

    public static void saveHeaderProfile(Context context, Profile headerProfile) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        if (headerProfile == null) {
            editor.putString(Constants.PRE_AVATAR_URL, null);
            editor.putString(Constants.PRE_FULL_NAME, null);
            editor.putString(Constants.PRE_EMAIL, null);
            editor.putString(Constants.PRE_ADDRESS, null);
        } else {
            editor.putString(Constants.PRE_AVATAR_URL, headerProfile.getSrcAvatar());
            editor.putString(Constants.PRE_FULL_NAME, headerProfile.getName());
            editor.putString(Constants.PRE_EMAIL, headerProfile.getUser().getUsername());
            editor.putString(Constants.PRE_ADDRESS, headerProfile.getAddress());
        }
        editor.apply();
    }

    public static Profile getHeaderProfile(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Profile headerProfile;
        headerProfile = new Profile();
        User user= new User();
        user.setUsername(preferences.getString(Constants.PRE_USERNAME, null));
        headerProfile.setSrcAvatar(preferences.getString(Constants.PRE_AVATAR_URL, null));
        headerProfile.setName(preferences.getString(Constants.PRE_FULL_NAME, null));
        headerProfile.setUser(user);
        return headerProfile;
    }

    public static String getTimeAndDate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return calendar.get(Calendar.HOUR) +
                ":" +
                calendar.get(Calendar.MINUTE) +
                "  " +
                calendar.get(Calendar.DAY_OF_MONTH) +
                "/" +

                (calendar.get(Calendar.MONTH)+1) +
                "/" +
                calendar.get(Calendar.YEAR);
    }

    public static void dialogShowDate(Activity activity, String title, DatePickerDialog.OnDateSetListener dateChangedListener) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(activity,
                dateChangedListener,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH) + 1,
                now.get(Calendar.DAY_OF_MONTH));
        dpd.setTitle(title);
        dpd.show();
    }
    public static long milliseconds(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        try {
            Date mDate = sdf.parse(date);
            return mDate.getTime();
        } catch (ParseException ignored) {
            Log.i("timeerro", "milliseconds: "+ignored.toString());
            return 0;
        }
    }
    public static String formatNumberMoney(int salary) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter.format(salary);
    }
    public static String getDateFromMilliseconds(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static boolean checkNetwork(Context context) {
        boolean available = false;
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cn.getActiveNetworkInfo();
        if (info != null && info.isAvailable() && info.isConnected()) {
            available = true;
        }
        return available;
    }

    public static boolean isEmailValid(String target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
//        Uri uri = Uri.parse("market://details?id=" + "code.android.ngocthai.place" + "&hl=vi");
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }
    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
