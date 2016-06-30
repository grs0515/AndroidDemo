package com.cmcc.hyapps.KunlunTravel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cmcc.hyapps.KunlunTravel.base.BaseActivity;
import com.cmcc.hyapps.KunlunTravel.download.DownloadListActivity;
import com.cmcc.hyapps.KunlunTravel.mvp.view.HomeActivity;
import com.cmcc.hyapps.KunlunTravel.rxjava.RxJavaActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_index)
public class IndexActivity extends BaseActivity {

    public static final String TITLE = "title";
    private LayoutInflater mInflater;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(this);

        mListView = (ListView) findViewById(R.id.id_listview);

        mListView.setAdapter(
                new ArrayAdapter<Class>(this, -1, CLAZZES) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        String title = getItem(position).getSimpleName();
                        if (convertView == null) {
                            convertView = mInflater.inflate(R.layout.item_list, parent, false);
                        }
                        TextView tv = (TextView) convertView.findViewById(R.id.id_title);
                        tv.setText(title);
                        return convertView;
                    }
                }

        );

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(IndexActivity.this, CLAZZES[position]);
                intent.putExtra(IndexActivity.TITLE, CLAZZES[position].getSimpleName());
                startActivity(intent);
            }
        });
    }
    //添加Activity 到列表中
    private Class[] CLAZZES = new Class[]{
            HomeActivity.class,//mvp
            DownloadListActivity.class,
            RxJavaActivity.class

    };
}
