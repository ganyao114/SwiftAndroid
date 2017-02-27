package net.swiftos.feizhai.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import net.swiftos.feizhai.R;
import net.swiftos.usermodule.aop.DebugLog;
import net.swiftos.usermodule.aop.LoginRequired;


public class MainActivity extends AppCompatActivity {

    @DebugLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test("nihhao");
    }

    @LoginRequired
    public void test(String str) {
        Toast.makeText(this, str , Toast.LENGTH_LONG).show();
    }

}
