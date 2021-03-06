package com.mahao.alex.architecture.dagger2_mvp;

import android.app.Activity;

import com.mahao.alex.architecture.dagger2_mvp.view.LoginActivity;

import dagger.Component;

/**
 * Created by Alex_MaHao on 2016/5/17.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();

    void inject(LoginActivity loginActivity);

}
