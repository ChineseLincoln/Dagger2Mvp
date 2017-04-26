package org.unreal.common.application.function.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.unreal.common.application.R;
import org.unreal.common.user.function.main.UserActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.text).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, UserActivity.class)));
    }
}
