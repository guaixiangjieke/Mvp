package com.nl.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by NiuLei on 2018/1/29.
 * mvp契约类
 */

public interface MvpContract {
    interface IModel {

    }

    interface IView<P extends IPresenter> {

        /**
         * 获取控制器
         */
        P getPresenter();

        /**
         * 获取字符串
         *
         * @param resId
         * @return
         */
        String getString(@StringRes int resId);

        String getString(@StringRes int resId, Object... formatArgs);

        Context getContext();

        Activity getActivity();

        void showToast(String text);
        /**
         * 开始进度提示
         */
        void startProgress(String text);

        /**
         * 停止进度提示
         */
        void stopProgress();
        /**
         * 运行到主线程
         * @param action
         */
        void runOnUiThread(Runnable action);
    }

    interface IPresenter extends ViewCallback {
        /**
         * 获取父层控制器
         */
        IPresenter getParent();

        /**
         * 对话框取消
         */
        void onDialogCancel();
    }
}
