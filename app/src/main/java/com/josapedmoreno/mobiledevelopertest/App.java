package com.josapedmoreno.mobiledevelopertest;

import android.app.Application;
import android.util.Log;

import com.josapedmoreno.mobiledevelopertest.utils.FakeCrashLibrary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                    if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                        return;
                    }

                    FakeCrashLibrary.log(priority, tag, message);

                    if (t != null) {
                        if (priority == Log.ERROR) {
                            FakeCrashLibrary.logError(t);
                        } else if (priority == Log.WARN) {
                            FakeCrashLibrary.logWarning(t);
                        }
                    }
                }
            });
    }
}
