

package com.example.amitwalke.financialhdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.example.amitwalke.financialhdemo.R;
/**
 * Created by Amit Walke on 27-06-2017.
 */
public class NetworkUtil {

    public static boolean isAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
