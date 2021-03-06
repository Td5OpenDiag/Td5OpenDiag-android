package com.mooo.hairyone.td5tester.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.FragmentIntakeBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IntakeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntakeFragment extends Fragment {

    Logger log = Log4jHelper.getLogger(this.getClass());

    private FragmentIntakeBinding   m_binding;

    private final float f_gauge_section_width_factor    = 0.4f;

    public IntakeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntakeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntakeFragment newInstance(String param1, String param2) {
        IntakeFragment fragment = new IntakeFragment();
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
        log.trace("");

        m_binding   = FragmentIntakeBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();


        return view;
    }

    /**
     *  OnStart method is generally used to refresh any data in the View and View Groups, this
     *  method gets called when the Fragment is first visible.
     */
    @Override public void onStart()
    {
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
            case AIR_FLOW:
                this.setMassAirflow(value * 10.0f);
                break;

            case AMBIENT_PRESSURE:
                this.setAmbientPressure(value);
                break;

            case INLET_TEMP:
                this.setIntakeAirTemperature(value);
                break;

            case MAF_AIR_MASS:
                this.setMafAirMass(value);
                break;

            case MANIFOLD_AIR_PRESSURE:
                double c = value * 0.01 - 1 ;
                float lValue_bar = (float) c ;
                this.setManifoldAirPressure(lValue_bar);
                break;

            case MAP_AIR_MASS:
                this.setMapAirMass(value);
                break;

            default:
                break;
        }
    }


    private void    setAmbientPressure(float pValue)
    {
        m_binding.gaugeAmbientPressure.setValue(pValue);
    }


    private void    setIntakeAirTemperature(float pValue)
    {
        m_binding.gaugeIntakeAirTemp.setValue(pValue);
    }


    private void    setMafAirMass(float pValue)
    {
        m_binding.gaugeMAFAirMass.setValue(pValue);
    }


    private void    setManifoldAirPressure(float pValue)
    {
        m_binding.gaugeManifoldTurboPressure.setValue(pValue);
    }


    private void    setMapAirMass(float pValue)
    {
        m_binding.gaugeMAPAirMass.setValue(pValue);
    }


    private void    setMassAirflow(float pValue)
    {
        m_binding.gaugeMassAirflow.setValue(pValue);
    }
}