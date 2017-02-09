package com.android_mvvm.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;

import com.android_mvvm.viewmodel.BaseViewModel;
import com.android_mvvm.viewmodel.IView;

/**
 * Created by Tam Nguyen on 1/8/17.
 */

public abstract class BaseActivity<B extends ViewDataBinding, T extends BaseViewModel> extends AppCompatActivity implements IView {

    protected T viewModel;
    B binding;

    protected final void bindView(int layout){
        if(viewModel == null){
            throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
        }
        binding = DataBindingUtil.setContentView(this,layout);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.clearSubscription();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detach();
    }
}
