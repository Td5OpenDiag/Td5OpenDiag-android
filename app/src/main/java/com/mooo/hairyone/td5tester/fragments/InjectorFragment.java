package com.mooo.hairyone.td5tester.fragments;

import com.mooo.hairyone.td5tester.Log4jHelper;
import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.events.DashboardEvent;

import org.apache.log4j.Logger;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;

public class InjectorFragment extends BaseFragment {

    Logger log = Log4jHelper.getLogger(this.getClass());

    @BindView(R.id.gACC_TRACK_1)      CircleProgressView gACC_TRACK_1;
    @BindView(R.id.gACC_TRACK_2)       CircleProgressView gACC_TRACK_2;
    @BindView(R.id.gACC_TRACK_3)       CircleProgressView gACC_TRACK_3;
    @BindView(R.id.gPOWER_BALANCE_1) CircleProgressView gPOWER_BALANCE_1;
    @BindView(R.id.gPOWER_BALANCE_2)           CircleProgressView gPOWER_BALANCE_2;
    @BindView(R.id.gPOWER_BALANCE_3)       CircleProgressView gPOWER_BALANCE_3;
    @BindView(R.id.gPOWER_BALANCE_4)        CircleProgressView gPOWER_BALANCE_4;
    @BindView(R.id.gPOWER_BALANCE_5)        CircleProgressView gPOWER_BALANCE_5;

    public InjectorFragment() { /* Required empty public constructor*/ }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDashboardEvent(DashboardEvent event) {
        float value = (float) event.value;
        switch (event.data_type) {
            case ACC_TRACK_1:            gACC_TRACK_1.setValue(value);       break;
            case ACC_TRACK_2:            gACC_TRACK_2.setValue(value);       break;
            case ACC_TRACK_3:            gACC_TRACK_3.setValue(value);       break;
            case POWER_BALANCE_1:        gPOWER_BALANCE_1.setValue(value);   break;
            case POWER_BALANCE_2:        gPOWER_BALANCE_2.setValue(value);   break;
            case POWER_BALANCE_3:        gPOWER_BALANCE_3.setValue(value);   break;
            case POWER_BALANCE_4:        gPOWER_BALANCE_4.setValue(value);   break;
            case POWER_BALANCE_5:        gPOWER_BALANCE_5.setValue(value);   break;
        }
    }

    @Override protected int getFragmentLayout() { return R.layout.injector_fragment; }

}