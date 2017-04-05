package com.drawthink.telcom.quality.function.test;

import android.widget.TextView;

import com.drawthink.telcom.quality.R;
import com.drawthink.telcom.quality.component.AppComponent;
import com.drawthink.telcom.quality.function.BaseActivity;

import butterknife.BindView;

public class TestActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void injectDagger(AppComponent appComponent) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void afterViews() {
        text.setText("text Activity!!!!");
    }
}
