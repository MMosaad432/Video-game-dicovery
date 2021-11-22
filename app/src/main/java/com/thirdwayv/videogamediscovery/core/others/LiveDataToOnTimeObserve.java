package com.thirdwayv.videogamediscovery.core.others;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public class LiveDataToOnTimeObserve<T> extends MediatorLiveData<T> {

    private static final String TAG = "LiveDataToOnTimeObserve";

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NotNull Observer<? super T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, value -> {
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(value);
            }
        });
    }

    @MainThread
    @Override
    public void setValue(T value) {
        mPending.set(true);
        super.setValue(value);
    }
}