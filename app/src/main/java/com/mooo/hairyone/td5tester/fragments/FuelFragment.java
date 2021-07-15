package com.mooo.hairyone.td5tester.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.FragmentFuelBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuelFragment extends Fragment {

    private FragmentFuelBinding m_binding;

    private final String    f_strUnitSuffix_milliGrams   = " mg";

    public FuelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FuelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FuelFragment newInstance(String param1, String param2) {
        FuelFragment fragment = new FuelFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        m_binding   = FragmentFuelBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();


        this.selectedFuelingValue_update();


        return view;
    }

    /**
     *  OnStart method is generally used to refresh any data in the View and View Groups, this
     *  method gets called when the Fragment is first visible.
     */
    @Override public void onStart()
    {
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
            case AF_RATIO:
                this.setAirFuelRatio(value);
                break;

            case DRIVER_DEMAND:
                this.setFuelDemandDriver(value);
                break;

            case FUEL_TEMP:
                this.setFuelTemperature(value);
                break;

            case IDLE_DEMAND:
                this.setFuelDemandIdle(value);
                break;

            case INJECTION_QUANTITY:
                this.setInjectionQuantity(value);
                break;

            case SMOKE_LIMIT:
                this.setFuelLimiterSmoke(value);
                break;

            case TORQUE_LIMIT:
                this.setFuelLimiterTorque(value);
                break;
        }

        this.selectedFuelingValue_update();
    }


    private void    selectedFuelingValue_update()
    {
        boolean lSelectDemandDriver = false;
        boolean lSelectDemandIdle   = false;
        boolean lSelectLimitTorque  = false;
        boolean lSelectLimitSmoke   = false;

        final int   lBackgroundResource_default         = android.R.color.transparent;
        final int   lBackgroundResource_selectedDemand  = R.color.fueling_demand_selected;
        final int   lBackgroundResource_selectedLimit   = R.color.fueling_limit_selected;


        if( m_binding.fuelInjectedQuantity.getText().equals( m_binding.fuelDemandDriverValue.getText() ) )
        {
            lSelectDemandDriver = true;
        }

        if( m_binding.fuelInjectedQuantity.getText().equals( m_binding.fuelDemandIdleValue.getText() ) )
        {
            lSelectDemandIdle   = true;
        }

        if( m_binding.fuelInjectedQuantity.getText().equals( m_binding.fuelLimiterSmokeValue.getText() ) )
        {
            lSelectLimitSmoke   = true;
        }

        if( m_binding.fuelInjectedQuantity.getText().equals( m_binding.fuelLimiterTorqueValue.getText() ) )
        {
            lSelectLimitTorque  = true;
        }


        if( lSelectDemandDriver )
        {
            m_binding.fuelDemandDriverValue
                    .setBackgroundResource( lBackgroundResource_selectedDemand );
        }
        else
        {
            m_binding.fuelDemandDriverValue
                    .setBackgroundResource(lBackgroundResource_default);
        }

        if( lSelectDemandIdle )
        {
            m_binding.fuelDemandIdleValue
                    .setBackgroundResource( lBackgroundResource_selectedDemand );
        }
        else
        {
            m_binding.fuelDemandIdleValue
                    .setBackgroundResource(lBackgroundResource_default);
        }

        if( lSelectLimitSmoke )
        {
            m_binding.fuelLimiterSmokeValue
                    .setBackgroundResource( lBackgroundResource_selectedLimit );
        }
        else
        {
            m_binding.fuelLimiterSmokeValue
                    .setBackgroundResource(lBackgroundResource_default);
        }

        if( lSelectLimitTorque )
        {
            m_binding.fuelLimiterTorqueValue
                    .setBackgroundResource( lBackgroundResource_selectedLimit );
        }
        else
        {
            m_binding.fuelLimiterTorqueValue
                    .setBackgroundResource(lBackgroundResource_default);
        }
    }


    private void    setAirFuelRatio(float pValue)
    {
        m_binding.gaugeAirFuelRatio.setValue(pValue);
        m_binding.textAirFuelRatioValue.setText(pValue + " : 1");
    }


    private void    setFuelDemandDriver(float pValue)
    {
        m_binding.fuelDemandDriverValue.setText( pValue + f_strUnitSuffix_milliGrams);
    }


    private void    setFuelDemandIdle(float pValue)
    {
        //m_binding.gaugeIdleDemand.setValue(pValue);
        m_binding.fuelDemandIdleValue.setText( pValue + f_strUnitSuffix_milliGrams);
        //m_binding.textIdleDemandValue.setText(pValue + " rpm");
    }


    private void    setFuelLimiterSmoke(float pValue)
    {
        m_binding.fuelLimiterSmokeValue.setText( pValue + f_strUnitSuffix_milliGrams);
    }


    private void    setFuelLimiterTorque(float pValue)
    {
        m_binding.fuelLimiterTorqueValue.setText( pValue + f_strUnitSuffix_milliGrams);
    }


    private void    setFuelTemperature(float pValue)
    {
        m_binding.gaugeFuelTemperature.setValue(pValue);
        m_binding.textFuelTemperatureValue.setText(pValue + " °C");
    }


    private void    setInjectionQuantity(float pValue)
    {
        //m_binding.gaugeInjectionQuantity.setValue(pValue);
        //m_binding.textInjectionQuantityValue.setText(pValue + " mg");
        m_binding.fuelInjectedQuantity.setText( pValue + f_strUnitSuffix_milliGrams);
    }
}
