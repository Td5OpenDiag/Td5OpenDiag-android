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
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                R.integer.intake_manifoldAirPressure_gaugeMin_bar);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                        R.integer.intake_manifoldAirPressure_gaugeMax_bar);


        this.setMin( lGaugeValueMin );
        this.setMax( lGaugeValueMax );

        this.setUnit("bar");


        this.setStartDegree(90);
        this.setEndDegree(360);

        this.setMarksNumber(5);
        this.setTickNumber(4);


        /*
            Sections definition
         */

        /* "Low" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.intake_manifoldAirPressure_gaugeMin_bar),
                0,
                getContext().getResources().getColor( R.color.valueInc_veryLow ) );

        /* "Normal" section */
        this.section_add(
                0,
                getContext().getResources().getInteger( R.integer.intake_manifoldAirPressure_max_mBar) / 1000.0f,
                getContext().getResources().getColor( R.color.valueInc_ok ) );

        /* "High" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.intake_manifoldAirPressure_max_mBar) / 1000.0f,
                lGaugeValueMax,
                getContext().getResources().getColor( R.color.valueInc_high ) );
    }
}
