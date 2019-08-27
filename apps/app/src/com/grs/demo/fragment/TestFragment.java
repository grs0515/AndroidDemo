package com.grs.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.grs.demo.R;
import com.grs.demo.annotation.FindUtil;
import com.grs.lib.annotations.find.FindEvent;
import com.grs.lib.annotations.find.FindView;

/**
 * @author:gaoruishan
 * @date:202019-05-09/14:25
 * @email:grs0515@163.com
 */
public class TestFragment extends Fragment {

    @FindView(R.id.btn)
    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_atest, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FindUtil.inject(this, view);
        button.setText("添加的按钮");
    }

    @FindEvent(R.id.btn)
    public void btnEvent() {
        button.setText("ssjksssssss");
    }
}
