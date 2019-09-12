package com.example.fourtitudetask1.base.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fourtitudetask1.AppApplication;
import com.example.fourtitudetask1.injection.component.AppComponent;

public abstract class BaseMvpFragment extends Fragment {
    protected abstract void injectAppComponent();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectAppComponent();
    }

    public AppComponent getAppComponent() {
        return AppApplication.getAppComponent(getActivity());
    }
}
