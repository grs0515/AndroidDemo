package com.grs.tinker;

import android.content.Context;
import android.widget.Toast;

/**
 * @author:gaoruishan
 * @date:202019-08-09/10:03
 * @email:grs0515@163.com
 */
public class TestUtil {

    public static void show(Context context,String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
