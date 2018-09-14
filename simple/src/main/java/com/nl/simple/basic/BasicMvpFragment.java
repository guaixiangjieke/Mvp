package com.nl.simple.basic;

/**
 * MvpFragment基类 便于统一修改
 */

import com.nl.mvp.MvpContract;
import com.nl.mvp.MvpFragment;

public abstract class BasicMvpFragment<P extends MvpContract.IPresenter> extends MvpFragment<P> {
}
