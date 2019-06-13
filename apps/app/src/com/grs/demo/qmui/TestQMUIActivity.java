package com.grs.demo.qmui;

import android.os.Bundle;
import android.view.View;

import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import org.xutils.view.annotation.ContentView;

/**
 * @author:gaoruishan
 * @date:202019-05-31/15:23
 * @email:grs0515@163.com
 */
@ContentView(R.layout.activity_qmui_test)
public class TestQMUIActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void QMUIBottomSheet(View view) {
        QMUIBottomSheet qmuiBottomSheet = new QMUIBottomSheet(this);
        qmuiBottomSheet.show();
    }
}
