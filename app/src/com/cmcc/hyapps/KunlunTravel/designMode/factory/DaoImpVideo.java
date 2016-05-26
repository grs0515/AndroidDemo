package com.cmcc.hyapps.KunlunTravel.designMode.factory;

/**
 * Created by gaoruishan on 16/4/4.
 */
public class DaoImpVideo implements IDao<Video> {
    private final static String TAG = "Video->";
    @Override
    public void save(Video video) {
        System.out.println(TAG+"save");
    }

    @Override
    public void delete(int id) {
        System.out.println(TAG+"delete");
    }
}
