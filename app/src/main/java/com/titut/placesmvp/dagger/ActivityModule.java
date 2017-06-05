package com.titut.placesmvp.dagger;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by karthi.ponnusamy on 4/6/17.
 */

@Module
public class ActivityModule {
    Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @ActivityContext
    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return mActivity;
    }
}

