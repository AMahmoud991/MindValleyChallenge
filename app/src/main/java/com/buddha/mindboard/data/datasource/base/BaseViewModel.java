package com.buddha.mindboard.data.datasource.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel {
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel() {
        mCompositeDisposable =new CompositeDisposable();
    }

    public void addSubscription(Disposable d){
        mCompositeDisposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.clear();
    }
}
