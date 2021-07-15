package com.mooo.hairyone.td5tester.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.InputsFragmentBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class InputsFragment extends BaseFragment {

    private InputsFragmentBinding   m_binding;


    public InputsFragment() { /* Required empty public constructor*/ }


    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        m_binding   = InputsFragmentBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();

        init_acceleratorProgressBar(this.m_binding.acceleratorTrack1Progress);
        init_acceleratorProgressBar(this.m_binding.acceleratorTrack2Progress);
        init_acceleratorProgressBar(this.m_binding.acceleratorTrack3Progress);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        m_binding   = null;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDashboardEvent(DashboardEvent event) {
        float value = (float) event.value;
        switch (event.data_type)
        {
            case ACC_TRACK_1:
                //m_binding.gACCTRACK1.setValue(value);
                this.setInputAccTrack1(value);
                break;

            case ACC_TRACK_2:
                //m_binding.gACCTRACK2.setValue(value);
                this.setInputAccTrack2(value);
                break;

            case ACC_TRACK_3:
                //m_binding.gACCTRACK3.setValue(value);
                this.setInputAccTrack3(value);
                break;
        }
    }

    @Override protected int getFragmentLayout() { return R.layout.inputs_fragment; }


    private void    init_acceleratorProgressBar(ProgressBar pWidget)
    {
        pWidget.setMin(0);
        pWidget.setMax(5000);

        pWidget.setProgress(0);

        pWidget.setProgressTintList(
                ColorStateList.valueOf(Color.RED));
    }


    private void    setInputAccTrack1(float pValue_volts)
    {
        this.m_binding.acceleratorTrack1Progress.setProgress((int)(pValue_volts * 1000.0f));
        this.m_binding.acceleratorTrack1Value.setText(pValue_volts + " V");
    }


    private void    setInputAccTrack2(float pValue_volts)
    {
        this.m_binding.acceleratorTrack2Progress.setProgress((int)(pValue_volts * 1000.0f));
        this.m_binding.acceleratorTrack2Value.setText(pValue_volts + " V");
    }


    private void    setInputAccTrack3(float pValue_volts)
    {
        this.m_binding.acceleratorTrack3Progress.setProgress((int)(pValue_volts * 1000.0f));
        this.m_binding.acceleratorTrack3Value.setText(pValue_volts + " V");
    }

}
