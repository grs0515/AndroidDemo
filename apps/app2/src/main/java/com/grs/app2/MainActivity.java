package com.grs.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.grs.web.test.TestWebActivity;

public class MainActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MyClass.test();
         intent = new Intent(this, TestWebActivity.class);
    }

    public void btnWeb(View view) {
        intent.putExtra(TestWebActivity.TYPE_KEY,TestWebActivity.TYPE_1);
        startActivity(intent);
    }
    public void btnWeb2(View view) {
        intent.putExtra(TestWebActivity.TYPE_KEY,TestWebActivity.TYPE_2);
        startActivity(intent);
    }
    public void btnWeb3(View view) {
        intent.putExtra(TestWebActivity.TYPE_KEY,TestWebActivity.TYPE_3);
        startActivity(intent);
    }
}
