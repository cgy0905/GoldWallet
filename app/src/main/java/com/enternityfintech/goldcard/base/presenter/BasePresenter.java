package com.enternityfintech.goldcard.base.presenter;

import com.enternityfintech.goldcard.base.BaseView;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cgy
 * 2018/7/4  9:33
 */
public class BasePresenter<V extends BaseView> implements Presenter<V>{

    protected Reference<V> mViewRef;

    private CompositeSubscription compositeSubscription;

    @Override
    public void attachView(@NotNull V view) {
        mViewRef = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

        if (compositeSubscription != null) {
            compositeSubscription.clear();
            compositeSubscription = null;
        }
        unsubscribe();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RxJava取消注册，以避免内存泄露
    public void unsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
