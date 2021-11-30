package com.mooo.hairyone.td5tester.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.databinding.FragmentEngineBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class EngineFragment extends Fragment {

    private FragmentEngineBinding   m_binding;


    public EngineFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        m_binding   = FragmentEngineBinding.inflate(inflater, container, false);
        View view   = m_binding.getRoot();

        setEngineCoolantTemperature(0.0f);

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

    private void    setEngineCoolantTemperature(float pValue)
    {
        m_binding.gaugeEct.setValue(pValue);
    }
}
