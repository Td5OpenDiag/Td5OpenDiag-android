package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class ManifoldAirPressure extends Td5Gauge {


    public ManifoldAirPressure(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    private void init()
    {
        int lGaugeValueMax
                = getContext().getResources().getInteger( R.integer.intake_manifoldAirPressure_max_bar);
        int lGaugeValueMin
                = getContext().getResources().getInteger( R.integer.intake_manifoldAirPressure_min_bar);


        this.setMin( lGaugeValueMin );
        this.setMax( lGaugeValueMax );

        this.setUnit("bar");


        this.setStartDegree(90);
        this.setEndDegree(360);

        this.setMarksNumber(5);
        this.setTickNumber(4);
    }
}
