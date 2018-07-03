package com.enternityfintech.goldcard.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cgy
 * 2018/6/14  14:23
 */
public abstract class BaseActivity extends BaseStatusBarActivity{


    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FIXME 这里会导致内存泄漏
        //MyApp.activities.add(this);
        init();
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        if (provideContentViewId() != 0) {
            setContentView(provideContentViewId());
            initView();
        }

        //setupAppBarAndToolbar();
    }

    /**
     * 检查是否为空，以免导致空指针
     */
    private void checkSubscription(){
        if (this.compositeSubscription == null){
            this.compositeSubscription = new CompositeSubscription();
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.compositeSubscription != null && !this.compositeSubscription.isUnsubscribed()) {
            this.compositeSubscription.unsubscribe();
        }
    }

    //在setContentView()调用之前调用,可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
    public void init() {

    }

    //初始化控件和监听
    protected abstract void initView();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    /**
     * 是否让Toolbar有返回按钮(默认可以，一般一个应用中除了主界面，其他界面都是可以有返回按钮的)
     */
    protected boolean isToolbarCanBack() {
        return true;
    }


    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

//    public void replaceFragment(Fragment fragment){
//        replaceFragment(R.id.flContent,fragment);
//    }
//
//    public void replaceFragment(@IdRes int id, Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().replace(id, fragment).commit();
//    }

}
