package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class EngineRPM extends Td5Gauge {


    public EngineRPM(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger( R.integer.engine_rpm_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger( R.integer.engine_rpm_gaugeMax);


        this.setMin( lGaugeValueMin );
        this.setMax( lGaugeValueMax );
        this.setValue(8888.0f);
        this.setValueTextFormat("%4.0f");

        this.setUnit("RPM");

        this.setGaugeName(getContext().getResources().getString(R.string.engine_rpm_short));

        this.setTickNumber( ((lGaugeValueMax - lGaugeValueMin) / 1000) + 1);
        this.setMarksNumber( ((lGaugeValueMax - lGaugeValueMin) / 1000) + 1 - 2);


        /*
            Sections definition
         */

        /* "Low" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.engine_rpm_idle_low),
                getContext().getResources().getColor( R.color.valueInc_invalid ) );

        /* "Idle" section */
        this.section_add(
                //lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.engine_rpm_idle_low),
                getContext().getResources().getInteger( R.integer.engine_rpm_idle_high),
                getContext().getResources().getColor( R.color.valueInc_veryLow ) );

        /* low RPM torque */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_idle_high),
                getContext().getResources().getInteger( R.integer.engine_rpm_torque80_low),
                getContext().getResources().getColor( R.color.valueInc_low ) );

        /* 80% torque lower */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_torque80_low),
                getContext().getResources().getInteger( R.integer.engine_rpm_torque90),
                getContext().getResources().getColor( R.color.valueInc_ok_low ) );

        /* High torque */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_torque90),
                getContext().getResources().getInteger( R.integer.engine_rpm_torqueMax),
//                Color.GREEN
                getContext().getResources().getColor( R.color.valueInc_ok )
        );

        /* 80% torque higher */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_torqueMax),
                getContext().getResources().getInteger( R.integer.engine_rpm_torque80_high),
                getContext().getResources().getColor( R.color.valueInc_ok_high ) );

        /* 80% torque to max governed speed */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_torque80_high),
                getContext().getResources().getInteger( R.integer.engine_rpm_maxGovernedSpeed),
                getContext().getResources().getColor( R.color.valueInc_high ) );

        /* "Maximum governed speed" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_rpm_maxGovernedSpeed),
                lGaugeValueMax,
                getContext().getResources().getColor( R.color.valueInc_veryHigh ) );
    }
}
