package com.nl.simple.basic;

/**
 * MvpActivity基类 便于统一修改
 */

import com.nl.mvp.MvpActivity;
import com.nl.mvp.MvpContract;

public abstract class BasicMvpActivity<P extends MvpContract.IPresenter> extends MvpActivity<P> {
}
