package com.mooo.hairyone.td5tester.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.databinding.FuelDemandFragmentBinding;
import com.mooo.hairyone.td5tester.databinding.InjectorFragmentBinding;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import at.grabner.circleprogress.CircleProgressView;

public class FuelDemandFragment extends BaseFragment {

    Logger log = Log4jHelper.getLogger(this.getClass());

    /* Butterknife is deprecated !
    @BindView(R.id.gDRIVER_DEMAND)      CircleProgressView gDRIVER_DEMAND;
    @BindView(R.id.gMAF_AIR_MASS)       CircleProgressView gMAF_AIR_MASS;
    @BindView(R.id.gMAP_AIR_MASS)       CircleProgressView gMAP_AIR_MASS;
    @BindView(R.id.gINJECTION_QUANTITY) CircleProgressView gINJECTION_QUANTITY;
    @BindView(R.id.gAF_RATIO)           CircleProgressView gAF_RATIO;
    @BindView(R.id.gTORQUE_LIMIT)       CircleProgressView gTORQUE_LIMIT;
    @BindView(R.id.gSMOKE_LIMIT)        CircleProgressView gSMOKE_LIMIT;
    @BindView(R.id.gIDLE_DEMAND)        CircleProgressView gIDLE_DEMAND;
     */
    private FuelDemandFragmentBinding   m_binding;

    public FuelDemandFragment() { /* Required empty public constructor*/ }


    /**
     *  OnCreateView gets called when Android is ready draw fragment user interface. To draw UI
     *  for the fragment we must return a View Component from this method. */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        log.trace("");

        m_binding   = FuelDemandFragmentBinding.inflate(inflater, container, false);
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
        switch (event.data_type) {
            case DRIVER_DEMAND:      m_binding.gDRIVERDEMAND.setValue(value);       break;
            case MAF_AIR_MASS:       m_binding.gMAFAIRMASS.setValue(value);         break;
            case MAP_AIR_MASS:       m_binding.gMAPAIRMASS.setValue(value);         break;
            case INJECTION_QUANTITY: m_binding.gINJECTIONQUANTITY.setValue(value);  break;
            case AF_RATIO:           m_binding.gAFRATIO.setValue(value);            break;
            case TORQUE_LIMIT:       m_binding.gTORQUELIMIT.setValue(value);        break;
            case SMOKE_LIMIT:        m_binding.gSMOKELIMIT.setValue(value);         break;
            case IDLE_DEMAND:        m_binding.gIDLEDEMAND.setValue(value);         break;
            case AIR_FLOW:
                m_binding.gAIRFLOW.setValue(value);
                break;
        }
    }

    @Override protected int getFragmentLayout() { return R.layout.fuel_demand_fragment; }

}