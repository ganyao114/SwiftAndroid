package net.swiftos.feizhai.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.swiftos.feizhai.R;
import net.swiftos.usermodule.aop.DebugLog;


public class MainActivity extends AppCompatActivity {

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
