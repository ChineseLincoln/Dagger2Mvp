package org.unreal.dagger.function.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.unreal.dagger.DaggerApplication;
import org.unreal.dagger.R;
import org.unreal.dagger.function.main.contract.MainContract;
import org.unreal.dagger.function.main.module.MainModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    @Inject
    Main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupActivityComponent();
    }

    private void setupActivityComponent() {
        DaggerApplication.get(this)
                .getAppComponent()
                .addSub(new MainModule(this))
                .inject(this);
    }
}
