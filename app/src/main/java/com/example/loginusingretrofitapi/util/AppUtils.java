
package com.example.loginusingretrofitapi.util;

import static com.example.loginusingretrofitapi.helpers.Constant.DIRECTORY;
import static com.example.loginusingretrofitapi.helpers.Constant.DIRECTORY_V11_PARENT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.loginusingretrofitapi.R;
import com.example.loginusingretrofitapi.helpers.DataResource;
import com.example.loginusingretrofitapi.model.MyOfficeLocation;
import com.example.loginusingretrofitapi.model.up_zila.Upozila;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppUtils {

    private static final String TAG = "AppUtils";

    public static String millisToDateString(String millis) {
        return millisLongToDateString(Long.valueOf(millis));
    }

    public static String millisLongToDateString(long millis) {
        Date d = new Date();
        d.setTime(millis);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(d);
    }

    public static String millisToDateString(long millis, String pattern, String language) {
        Date d = new Date();
        d.setTime(millis);

        DateFormat dateFormat = new SimpleDateFormat(pattern, new Locale(language));
        return dateFormat.format(d);
    }

    public static long getTodayMillis() {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());

        String date = dateFormat.format(d);

        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    public static String getTodayDate() {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd MMM , yyyy", Locale.getDefault());
        return dateFormat.format(d);

    }
    public static String getTodayDate(String format) {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(d);

    }

    public static Date formatStringToDate(String dateStr) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    .parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }


    public static MaterialDialog.Builder dialog(Context context, String title, String body, boolean isCancelable) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .titleColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                .cancelable(isCancelable)
                .content(body);
    }

    public static void hideSoftInput(final Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm == null) return;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static int visibility(boolean visibility) {
        return visibility ? View.VISIBLE : View.GONE;
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void message(View view, String msg, int textColor, int backgroundColor) {
        if (view == null) return;
        Snackbar snack = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT);
        View snackBarView = snack.getView();
        snackBarView.setBackgroundColor(backgroundColor);
        TextView snackBarText = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
        snackBarText.setTextColor(textColor);
        snack.show();
    }

    public static String dateToMillisecond(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            return String.valueOf(dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static String changeDateFormat(String date) {
        SimpleDateFormat datePickerFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.US);
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dateFromUser = datePickerFormat.parse(date);
            return myFormat.format(dateFromUser);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "0";
    }

    public static List<String> changeDateFormat(List<String> dates) {
        SimpleDateFormat datePickerFormat = new SimpleDateFormat("dd MMM, yyyy", Locale.US);
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            try {
                Date dateFromUser = datePickerFormat.parse(dates.get(i));
                list.add(myFormat.format(dateFromUser));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return list;
    }

    public static String getPercentage(int numberOfGiven, int total) {
        if (numberOfGiven != 0 && total != 0) {
            int percentage = (numberOfGiven * 100) / total;
            return String.valueOf(percentage);
        } else {
            return String.valueOf(0);
        }

    }

    public static long getTodayAndTimeMillis() {
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a", Locale.getDefault());

        String date = dateFormat.format(d);

        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    public static SpannableString substringTextColor(String text, int startPosition, int endPosition, int color) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(foregroundColorSpan, startPosition, endPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }

    public static MyOfficeLocation getMyLocation(String location) {
        if (location != null) {
            Gson gson = new Gson();
            return gson.fromJson(location, MyOfficeLocation.class);
        } else {
            return null;
        }
    }

    public static boolean getDistanceBetween(String officeLatitude, String officeLongitude, String myLatitude, String myLongitude, int distance) throws RuntimeException {
        if (officeLatitude == null || officeLongitude == null || myLatitude == null || myLongitude == null)
            return false;

        float[] result = new float[1];

        Location.distanceBetween(Double.parseDouble(officeLatitude),
                Double.parseDouble(officeLongitude),
                Double.parseDouble(myLatitude),
                Double.parseDouble(myLongitude), result);

        return !(result[0] / distance > 1);
    }

    public static AlertDialog.Builder customAlertDialog(Context context, String title, String body, boolean isCancelable) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(isCancelable)
                .setMessage(body);
    }

    public static MaterialDialog showProgressDialog(Context context, String title, String body) {
        return new MaterialDialog.Builder(context)
                .title(title)
                .cancelable(false)
                .content(body)
                .progress(true, 1)
                .build();
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static String digitFormat(String s) {
        double d = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        if (s != null && !s.equals("")) {
            d = Double.parseDouble(s);
        }

        if (d == (long) d)
            return String.format("%s", (long) d);
        else
            return String.format("%s", df.format(d));
    }

    public static String getYoutubeVideoId(String youtubeUrl) {
        String video_id = "";
        if (youtubeUrl != null && youtubeUrl.trim().length() > 0 && youtubeUrl.startsWith("http")) {

            String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            CharSequence input = youtubeUrl;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                String groupIndex1 = matcher.group(7);
                if (groupIndex1 != null && groupIndex1.length() == 11)
                    video_id = groupIndex1;
            }
        }
        return video_id;
    }

    public static String MillisectoDate(long millis) {
        Date d = new Date();
        d.setTime(millis);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(d);
    }

    @SuppressLint("SimpleDateFormat")
    public static File imageToFile(Bitmap image) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "sajida_field_force_" + timeStamp + ".jpg";
        File direct = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            direct = new File(DIRECTORY_V11_PARENT + "image/");
        } else {
            direct = new File(DIRECTORY + "image/");

        }


        if (!direct.exists()) {
            File wallpaperDirectory = null;

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                wallpaperDirectory = new File(new File(DIRECTORY_V11_PARENT + "image/"), imageFileName);
            } else {
                wallpaperDirectory = new File(new File(DIRECTORY + "image/"), imageFileName);
            }
            wallpaperDirectory.mkdirs();
        }

        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            file = new File(new File(DIRECTORY_V11_PARENT + "image/"), imageFileName);
        } else {
            file = new File(new File(DIRECTORY + "image/"), imageFileName);
        }
        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 75, out);
            out.flush();
            out.close();
            Log.d(TAG, "moneyReceiptFile path: " + file.getAbsolutePath());
            return new File(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void preventTwoClick(final View view) {
        view.setEnabled(false);
        view.postDelayed(() -> view.setEnabled(true), 500);
    }

    @SuppressLint("DefaultLocale")
    public static String setCopyRight() {
        Calendar calendar = Calendar.getInstance();
        return String.format("© %d %s", calendar.get(Calendar.YEAR), "Appinion BD Limited All Rights Reserved.");
    }

}
