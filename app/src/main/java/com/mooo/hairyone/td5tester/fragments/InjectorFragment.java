package com.mooo.hairyone.td5tester.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.DashboardFragmentBinding;
import com.mooo.hairyone.td5tester.databinding.InjectorFragmentBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import at.grabner.circleprogress.CircleProgressView;

public class InjectorFragment extends BaseFragment {

    Logger log = Log4jHelper.getLogger(this.getClass());

    private InjectorFragmentBinding m_binding;


    public InjectorFragment() { /* Required empty public constructor*/ }


    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log.trace("");

        m_binding   = InjectorFragmentBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();

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

    @Override protected int getFragmentLayout() { return R.layout.injector_fragment; }


    private void    setInputAccTrack1(float pValue)
    {
        this.m_binding.gaugeInputAccTrack1.speedTo(pValue);
    }


    private void    setInputAccTrack2(float pValue)
    {
        this.m_binding.gaugeInputAccTrack2.speedTo(pValue);
    }


    private void    setInputAccTrack3(float pValue)
    {
        this.m_binding.gaugeInputAccTrack3.speedTo(pValue);
    }

}