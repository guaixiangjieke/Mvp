package com.nl.simple.basic;

import com.nl.mvp.MvpContract;
import com.nl.mvp.MvpPresenter;

/**
 * MvpPresenter基类 便于统一修改
 */
public class BasicMvpPresenter<M extends MvpContract.IModel, V extends MvpContract.IView> extends MvpPresenter<M, V> {
    public BasicMvpPresenter(M model, V view) {
        super(model, view);
    }
}
