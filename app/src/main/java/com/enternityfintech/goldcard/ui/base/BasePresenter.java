package com.enternityfintech.goldcard.ui.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cgy
 * 2018/6/14  14:22
 */
public class BasePresenter<V> {

    /*=============以下是网络请求接口============*/

    public BaseActivity mContext;

    public CompositeSubscription compositeSubscription;

    public BasePresenter(BaseActivity context) {
        mContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }
    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        onUnsubscribe();
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (compositeSubscription == null)
            compositeSubscription = new CompositeSubscription();

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    //RxJava取消注册 避免内存泄漏
    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

}
