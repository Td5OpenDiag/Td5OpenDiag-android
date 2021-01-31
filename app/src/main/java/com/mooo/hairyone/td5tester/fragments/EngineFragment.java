package com.mooo.hairyone.td5tester.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.FragmentEngineBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EngineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EngineFragment extends Fragment {

    Logger log = Log4jHelper.getLogger(this.getClass());

    private FragmentEngineBinding   m_binding;


    public EngineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EngineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EngineFragment newInstance(String param1, String param2) {
        EngineFragment fragment = new EngineFragment();
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

        m_binding   = FragmentEngineBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();


        //this.init_gauge_idleDemand();

        setEngineCoolantTemperature(0.0f);
        //setIdleDemand(0.0f);

        m_binding.gPOWERBALANCE1.setCylinderNumber( 1 );
        m_binding.gPOWERBALANCE2.setCylinderNumber( 2 );
        m_binding.gPOWERBALANCE3.setCylinderNumber( 3 );
        m_binding.gPOWERBALANCE4.setCylinderNumber( 4 );
        m_binding.gPOWERBALANCE5.setCylinderNumber( 5 );


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
            case COOLANT_TEMP:
                this.setEngineCoolantTemperature(value);
                break;


            case POWER_BALANCE_1:
                m_binding.gPOWERBALANCE1.setValue(value);
                break;

            case POWER_BALANCE_2:
                m_binding.gPOWERBALANCE2.setValue(value);
                break;

            case POWER_BALANCE_3:
                m_binding.gPOWERBALANCE3.setValue(value);
                break;

            case POWER_BALANCE_4:
                m_binding.gPOWERBALANCE4.setValue(value);
                break;

            case POWER_BALANCE_5:
                m_binding.gPOWERBALANCE5.setValue(value);
                break;

            default:
                break;
        }
    }


//    private void    init_gauge_idleDemand()
//    {
//        int lGaugeValueMax
//                = getContext().getResources().getInteger( R.integer.engine_idleDemand_max);
//        int lGaugeValueMin
//                = getContext().getResources().getInteger( R.integer.engine_idleDemand_min);
//
//
//        Td5Gauge lGauge = m_binding.gaugeIdleDemand;
//
//
//        lGauge.setMin( lGaugeValueMin );
//        lGauge.setMax( lGaugeValueMax );
//
//        lGauge.setTickNumber( ((lGaugeValueMax - lGaugeValueMin) / 5) + 1);
//        lGauge.setMarksNumber( ((lGaugeValueMax - lGaugeValueMin) / 5) + 1 - 2);
//
//        lGauge.setUnit("mg");
//    }


    private void    setEngineCoolantTemperature(float pValue)
    {
        m_binding.gaugeEct.setValue(pValue);
    }
}