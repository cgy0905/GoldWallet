package com.enternityfintech.goldcard.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by cgy
 * 2018/6/19  9:39
 *
 * 封装了懒加载的实现
 * 1、ViewPager + Fragment情况下  fragment的生命周期因为ViewPager的缓存失去了意义
 * 该抽象类自定义新的回调方法 当Fragment可见状态时会触发的方法 和Fragment第一次可见时回调的方法
 */
public abstract class LazyLoadFragment extends Fragment{

    private static final String TAG = LazyLoadFragment.class.getSimpleName();

    private boolean isFirstEnter = true; //是否第一次进入 默认是

    private boolean isReuseView = true; //是否进行复用

    private boolean isFragmentVisible;

    private View rootView;


    //setUserVisibleHint()在Fragment创建时会先被调用一次 传入isVisibleToUser = false
    //如果当前Fragment可见 那么setUserVisibleHint()会再次被调用一次 传入 isVisibleToUser = true
    //如果Fragment不可见 那么setUserVisibleHint()也会被调用 传入 isVisibleToUser = false

    /**setUserVisibleHint()除了 Fragment 的可见状态发生变化时会被回调外, 在 new Fragment()也会被回调
     * 如果我们需要在 Fragment 可见与不可见时做某些操作 用这个的话就会有多余的回调了 那么就需要重新封装一个**/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在Fragment的生命周期外被调用
        if (rootView == null) {
            //如果view还未初始化 不进行处理
            return;
        }

        if (isFirstEnter && isVisibleToUser) {
            //如果是第一次进入并且是可见
            onFragmentFirstVisible(); //回调当前Fragment首次可见
            isFirstEnter = false;//第一次进入的标示改为false
        }

        if (isVisibleToUser) {
            //如果不是第一次进入可见的时候
            isFragmentVisible = true;
            onFragmentVisibleChange(true);//回调当前fragment可见
        }

        if (isFragmentVisible) {
            //如果当前Fragment不可见且标示为true
            isFragmentVisible = false;
            onFragmentVisibleChange(false);//回调当前fragment不可见
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时 那么就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后 以便支持UI操作
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstEnter) {
                    onFragmentFirstVisible();
                    isFirstEnter = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }

        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }


    /**
     * 设置是否使用view的复用 默认开启
     * view的复用是指 ViewPager在销毁和重建Fragment时会不断调用onCreateView() -> onDestroyView()
     * 之间的生命函数 这样可能会出现重复创建view的情况 导致界面上显示多个相同的Fragment
     * view的复用其实就是保存第一次创建的view 后面再onCreateView()时直接返回第一次创建的view
     * @param isReuse
     */

    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景 保证只有当Fragment可见状态发生变化时才回调
     * 回调时机在view创建完后 所以支持UI操作 解决在setUserVisibleHint()里进行UI操作有可能报null异常的问题
     *
     * 可在该方法中进行一些UI操作 比如进度条的隐藏显示
     * @param isVisible true 不可见 -> 可见
     *                  false 可见 ->不可见
     *
     */
    private void onFragmentVisibleChange(boolean isVisible) {

    }

    private void onFragmentFirstVisible() {

    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

     private void resetVariable() {
        isFirstEnter = true;
        isReuseView = true;
        isFragmentVisible = false;
        rootView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resetVariable();
    }
}
