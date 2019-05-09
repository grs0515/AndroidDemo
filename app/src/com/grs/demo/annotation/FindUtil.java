package com.grs.demo.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 注入视图
 * @author:gaoruishan
 * @date:202019-05-09/11:43
 * @email:grs0515@163.com
 */
public class FindUtil {

    private static final String TAG = FindUtil.class.getSimpleName();

    public static void inject(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        injectView(activity, null);
    }

    private static void injectView(Object obj, View v) {
        Field[] fArray = obj.getClass().getDeclaredFields();
        for (Field field : fArray) {
            // FindViewUtil: Field: android.widget.Button,button
            Log.e(TAG, "Field: " + field.getType().getName() + "," + field.getName());
            findView(obj, field, v);
            findFragment(obj, field);
        }
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method m : methods) {
            Log.e(TAG, "Method: " + m.getName());
            findEvent(obj, m, v);
        }
    }

    /**
     * 寻找View注入
     * @param obj
     * @param field
     */
    private static void findView(Object obj, Field field, View v) {
        try {
            FindView annotation = field.getAnnotation(FindView.class);
            if (annotation != null) {
                int viewId = annotation.value();
                View view;
                if (v != null) {
                    view = findById(v, viewId);
                } else {
                    view = findById(obj, viewId);
                }
                if (view != null) {
                    field.setAccessible(true);
                    field.set(obj, view);
                }
            }
        } catch (Exception e) {
            error(e);
        }
    }

    /**
     * 寻找fragment注入
     * @param obj
     * @param field
     */
    private static void findFragment(Object obj, Field field) {
        String name = field.getType().getName();
        FindFragment annotation = field.getAnnotation(FindFragment.class);
        if (annotation != null) {
            int viewId = annotation.value();
            Fragment fragment = addFragment((FragmentActivity) obj, viewId, name);
            try {
                if (fragment != null) {
                    field.setAccessible(true);
                    field.set(obj, fragment);
                }
            } catch (IllegalAccessException e) {
                error(e);
            }
        }
    }

    /**
     * 寻找Event注入
     * @param obj
     * @param m
     * @param v
     */
    private static void findEvent(Object obj, Method m, View v) {
        try {
            FindEvent annotation = m.getAnnotation(FindEvent.class);
            if (annotation != null) {
                int viewId = annotation.value();
                View view;
                if (v != null) {
                    view = findById(v, viewId);
                } else {
                    view = findById(obj, viewId);
                }
                if (view != null) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                m.invoke(obj);
                            } catch (Exception e) {
                                error(e);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            error(e);
        }


    }

    private static View findById(Object o, int viewId) {
        if (o instanceof Activity) {
            return ((Activity) o).findViewById(viewId);
        }
        if (o instanceof View) {
            return ((View) o).findViewById(viewId);
        }
        return null;
    }

    private static void error(Exception e) {
        Log.e(TAG, "Exception: " + e.getMessage());
    }

    /**
     * 跳转指定fragment
     * @param activity     需要继承FragmentActivity
     * @param fragmentName 类名
     */
    public static Fragment addFragment(Activity activity, @IdRes int containerViewId, String fragmentName, Bundle... args) {
        if (!TextUtils.isEmpty(fragmentName) && activity != null) {
            try {
                Class<?> aClass = Class.forName(fragmentName);
                Fragment fragment = addFragment(activity, containerViewId, aClass, args);
                return fragment;
            } catch (Exception e) {
                error(e);
            }
        }
        return null;
    }

    public static Fragment addFragment(Activity activity, @IdRes int containerViewId, Class<?> aClass, Bundle... args) {
        try {
            Object o = aClass.newInstance();
            if (o instanceof Fragment) {
                Fragment fragment = (Fragment) o;
                if (args != null && args.length > 0) {
                    fragment.setArguments(args[0]);
                }
                if (activity instanceof FragmentActivity) {
                    ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction()
                            .add(containerViewId, fragment, fragment.getClass().getName())
                            .commitAllowingStateLoss();
                }
                return fragment;
            }
        } catch (Exception e) {
            error(e);
        }
        return null;
    }

    public static void inject(Fragment fragment, View view) {
        injectView(fragment, view);
    }

}
