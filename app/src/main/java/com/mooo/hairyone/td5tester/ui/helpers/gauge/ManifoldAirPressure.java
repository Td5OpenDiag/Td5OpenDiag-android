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
        float lGaugeValueMin_bar
                = getContext().getResources().getInteger(
                R.integer.intake_manifoldAirPressure_gaugeMin_mBar) / 1000.0f;
        float lGaugeValueMax_bar
                = getContext().getResources().getInteger(
                        R.integer.intake_manifoldAirPressure_gaugeMax_mBar) / 1000.0f;


        this.setMin( lGaugeValueMin_bar );
        this.setMax( lGaugeValueMax_bar );

        this.setValue(lGaugeValueMax_bar);
        this.setValueTextFormat("% 1.1f");


        this.setGaugeName(getContext().getResources().getString(R.string.manifold_turbo_pressure_short));
        this.setUnit("bar");


        this.setStartDegree(135);
        this.setEndDegree(360);

        this.setMarksNumber(4);
        this.setTickNumber(6);
        this.setTickTextFormat("%.1f");


        /*
            Sections definition
         */

        /* "Low" section */
        this.section_add(
                lGaugeValueMin_bar,
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
                lGaugeValueMax_bar,
                getContext().getResources().getColor( R.color.valueInc_high ) );
    }
}
