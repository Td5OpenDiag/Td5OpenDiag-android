package com.mooo.hairyone.td5tester.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mooo.hairyone.td5tester.databinding.DashboardFragmentBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DashboardFragment extends Fragment {

    private DashboardFragmentBinding m_binding;

    private float   m_lastInjectedFuelQuantity_mg   = 0;
    private float   m_lastEngineRPM                 = 0;
    private float   m_lastRoadSpeed_kmh             = 0;
    final float     f_dieselDensity                 = 0.830f;


    public DashboardFragment() { /* Required empty public constructor */ }


    /**
     *  OnCreate gets called when a fragment is first created. Actions like Creating Views & View
     *  Groups, Initialization are generally done in OnCreate Method. */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
        EventBus.getDefault().register(this);
    }


    /**
     *  OnStop is called when the Fragment is no longer visible to the user and is being stopped.
     */
    @Override public void onStop() {
        EventBus.getDefault().unregister(this);
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

            case INJECTION_QUANTITY:
                this.m_lastInjectedFuelQuantity_mg = value;
                break;

            case MANIFOLD_AIR_PRESSURE:
                double c = value * 0.01 - 1 ;
                float lValue_bar = (float) c ;
                this.setManifoldAirPressure(lValue_bar);
                break;

            case RPM:
                //m_binding.gRPM.setValue(value);
                this.setEngineRPM(value);
                this.m_lastEngineRPM    = value;
                break;

            case BATTERY_VOLTAGE:
                //m_binding.gVOLT.setValue(value);
                this.setBatteryVoltage(value);
                break;

            case VEHICLE_SPEED:
                m_binding.gMPH.setValue(value);
                this.m_lastRoadSpeed_kmh    = value;
                break;
        }

        this.updateFuelConsumption();
    }


    private void    setBatteryVoltage(float pValue)
    {
        m_binding.gaugeBatteryVoltage.setValue(pValue);
    }


    private void    setEngineCoolantTemperature(float pValue)
    {
        m_binding.gaugeEct.setValue(pValue);
    }


    private void    setEngineRPM(float pValue)
    {
        m_binding.gaugeEngineRpm.setValue(pValue);
    }


    private void    setManifoldAirPressure(float pValue)
    {
        m_binding.gaugeManifoldTurboPressure.setValue(pValue);
    }


    /**
     *  This method calculates the fuel consumption then updates its display.
     *
     *  @par Detailed calculation
     *
     *  The step-by-step calculus is as follows:
     *  + kilograms_per_stroke equals the injection_quantity divided
     *    by 1,000,000 (mg to kg);
     *  + Liters_per_stroke equals kilograms_per_stroke divided by diesel_density;
     *  + Liters_per_rotation equals liters_per_stroke multiplied by 2.5 (5 cylinders firing every
     *    two rotations);
     *  + Liters_per_minute : liters_per_rotation multiplied by engine_rpm;
     *  + Liters_per_hour : liters_per_minute multiplied by 60;
     *  + Liters_per_km : liters_per_hour divided by speed_kmh;
     *  + Liters_per_100km : liters_per_km * 100.
     *
     *  This calculation is simplified by using a multiplication factor obtained from all the
     *  constants, as follows:
     * @<code>
     *               strokes_per_rotation * minutes_in_an_hour * 100km
     * factor  = --------------------------------------------------------
     *            miligrams_in_gram * grams_in_kilogram * diesel_density
     *
     *              2.5 * 60 * 100         15000
     *         = --------------------- = --------
     *            1000 * 1000 * 0.840     840000
     *
     *         = 0.017857143
     * </code>
     */
    private void    updateFuelConsumption()
    {
        /* We use a multiplication factor in the calculus for performance. */
        final float f_factor    = 0.017857143f;

        String  lText   = "---.-";


        if(     m_lastRoadSpeed_kmh > 0 )
        {
            /* Calculate injected quantity as liters per stroke */
            float lValue
                    = (m_lastInjectedFuelQuantity_mg * f_factor * m_lastEngineRPM)
                    / m_lastRoadSpeed_kmh;

            lText = "" + String.format("%.1f", lValue);
        }


        this.m_binding.fuelConsumptionValue.setText( lText );
    }

}
