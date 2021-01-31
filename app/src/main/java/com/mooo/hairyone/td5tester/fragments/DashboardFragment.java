package com.mooo.hairyone.td5tester.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.anastr.speedviewlib.components.note.Note;
import com.github.anastr.speedviewlib.components.note.TextNote;
import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.DashboardFragmentBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import at.grabner.circleprogress.CircleProgressView;
//import butterknife.BindView;
//import butterknife.Unbinder;

//public class DashboardFragment extends BaseFragment {
public class DashboardFragment extends Fragment {

    Logger log = Log4jHelper.getLogger(this.getClass());


    private DashboardFragmentBinding m_binding;


    public DashboardFragment() { /* Required empty public constructor */ }


    /**
     *  OnCreate gets called when a fragment is first created. Actions like Creating Views & View
     *  Groups, Initialization are generally done in OnCreate Method. */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.trace("");
    }


    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log.trace("");

        m_binding   = DashboardFragmentBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        m_binding   = null;
    }


    /**
     *  OnStart method is generally used to refresh any data in the View and View Groups, this
     *  method gets called when the Fragment is first visible.
     */
    @Override public void onStart() {
        super.onStart();
        log.trace("");
        EventBus.getDefault().register(this);
    }


    /**
     *  OnStop is called when the Fragment is no longer visible to the user and is being stopped.
     */
    @Override public void onStop() {
        EventBus.getDefault().unregister(this);
        log.trace("");
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDashboardEvent(DashboardEvent event) {
        float value = (float) event.value;
        switch (event.data_type)
        {
            case COOLANT_TEMP:
                this.setEngineCoolantTemperature(value);
                break;

            case MANIFOLD_AIR_PRESSURE:
                double c = value * 0.01 - 1 ;
                float lValue_bar = (float) c ;
                this.setManifoldAirPressure(lValue_bar);
                break;

            case RPM:
                //m_binding.gRPM.setValue(value);
                this.setEngineRPM(value);
                break;

            case BATTERY_VOLTAGE:
                //m_binding.gVOLT.setValue(value);
                this.setBatteryVoltage(value);
                break;

            case VEHICLE_SPEED:
                m_binding.gMPH.setValue(value);
                break;
        }

    }


    private void    setBatteryVoltage(float pValue)
    {
        m_binding.gaugeBatteryVoltage.setValue(pValue);
        //m_binding.textAmbientPressureValue.setText(pValue + " kPa");
    }


    private void    setEngineCoolantTemperature(float pValue)
    {
        m_binding.gaugeEct.setValue(pValue);
        //m_binding.textAmbientPressureValue.setText(pValue + " kPa");
    }


    private void    setEngineRPM(float pValue)
    {
        m_binding.gaugeEngineRpm.setValue(pValue);
        //m_binding.textAmbientPressureValue.setText(pValue + " kPa");
    }


    private void    setManifoldAirPressure(float pValue)
    {
        m_binding.gaugeManifoldTurboPressure.setValue(pValue);
        //m_binding.text.setText(pValue + " bar");
    }

//    @Override protected int getFragmentLayout() { return R.layout.dashboard_fragment; }

}