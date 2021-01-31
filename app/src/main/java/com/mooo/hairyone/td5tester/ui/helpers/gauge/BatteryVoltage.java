package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class BatteryVoltage extends Td5Gauge
{


    public BatteryVoltage(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger( R.integer.batteryVoltage_gaugeMin_V);
        int lGaugeValueMax
                = getContext().getResources().getInteger( R.integer.batteryVoltage_gaugeMax_V);


        this.setMin( lGaugeValueMin );
        this.setMax( lGaugeValueMax );

        this.setUnit("V");

        this.setTickNumber( (lGaugeValueMax - lGaugeValueMin) + 1);
        this.setMarksNumber( (lGaugeValueMax - lGaugeValueMin) + 1 - 2);


        /*
            Sections definition
         */

        /* "Empty" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                Color.RED);

        /* "ok" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_full_mV) / 1000.0f,
                Color.GRAY );

        /* "Charging" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_charging_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                Color.GREEN );

        /* "high" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                lGaugeValueMax,
                Color.RED );
    }
}
