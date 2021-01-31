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

//    @BindView(R.id.gRPM) CircleProgressView gRPM; // Deprecated
//    @BindView(R.id.gVOLT) CircleProgressView gVOLT; // Deprecated
//    @BindView(R.id.gMPH) CircleProgressView gMPH;   // Deprecated

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

        this.init_gauge_batteryVoltage();
        this.init_gauge_ect();
        //this.init_gauge_engineRPM();

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


    private void    init_gauge_batteryVoltage()
    {
        int lGaugeValueMax   = getContext().getResources().getInteger( R.integer.batteryVoltage_gauge_max_V);
        int lGaugeValueMin   = getContext().getResources().getInteger( R.integer.batteryVoltage_gauge_min_V);


        Td5Gauge lGauge   = m_binding.gaugeBatteryVoltage;


        lGauge.setMin( lGaugeValueMin );
        lGauge.setMax( lGaugeValueMax );

        lGauge.setUnit("V");

        lGauge.setTickNumber(7);
        lGauge.setMarksNumber(7 - 2);


        /*
            Sections definition
         */

        /* "Empty" section */
        lGauge.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                Color.RED);

        /* "ok" section */
        lGauge.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_full_mV) / 1000.0f,
                Color.GRAY );

        /* "Charging" section */
        lGauge.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_charging_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                Color.GREEN );

        /* "high" section */
        lGauge.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                lGaugeValueMax,
                Color.RED );
    }


    private void    init_gauge_ect()
    {
        int lGaugeValueMax   = getContext().getResources().getInteger( R.integer.ect_gauge_max);
        int lGaugeValueMin   = getContext().getResources().getInteger( R.integer.ect_gauge_min);


        Td5Gauge lGaugeEct   = m_binding.gaugeEct;


        lGaugeEct.setMin( lGaugeValueMin );
        lGaugeEct.setMax( lGaugeValueMax );

        lGaugeEct.setUnit("°C");


        /*
            Sections definition
         */

        /* "Cold" section */
        lGaugeEct.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.td5_ect_cold),
                Color.BLUE);

        /* "Thermostat closed" section */
        lGaugeEct.section_add(
                getContext().getResources().getInteger( R.integer.td5_ect_cold),
                getContext().getResources().getInteger( R.integer.td5_ect_thermostat_closed),
                Color.rgb(0,128,128) );
        //Color.rgb(0,128,64) );

        /* "Thermostat regulation" section */
        lGaugeEct.section_add(
                getContext().getResources().getInteger( R.integer.td5_ect_thermostat_closed),
                getContext().getResources().getInteger( R.integer.td5_ect_thermostat_opened),
                Color.GREEN );

        /* "Thermostat opened" section */
        lGaugeEct.section_add(
                getContext().getResources().getInteger( R.integer.td5_ect_thermostat_opened),
                getContext().getResources().getInteger( R.integer.td5_ect_load_reduction),
                Color.rgb(255,255,0)/*Color.YELLOW*/ );
        //Color.rgb(230,230,0)/*Color.YELLOW*/ );

        /* "Load reduction" section */
        lGaugeEct.section_add(
                getContext().getResources().getInteger( R.integer.td5_ect_load_reduction),
                getContext().getResources().getInteger( R.integer.td5_ect_dangerzone),
                Color.rgb(255, 165, 0) );

        /* "Overheat" section */
        lGaugeEct.section_add(
                getContext().getResources().getInteger( R.integer.td5_ect_dangerzone),
                lGaugeValueMax,
                Color.RED );
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