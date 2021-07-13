package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class EngineCoolantTemperature extends Td5Gauge
{


    public EngineCoolantTemperature(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                R.integer.engine_coolantTemperature_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                R.integer.engine_coolantTemperature_gaugeMax);


        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

//        this.setValue(lGaugeValueMax);
        this.setValue(888);

        this.getValueDisplay().setValueDisplayFormat("%3.0f");
        this.getValueDisplay().setUnitText("°C");

        this.setGaugeName(getContext().getResources().getString(R.string.engine_coolant_temperature_short));


        /*
            Sections definition
         */

        /* "Cold" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_cold),
                getContext().getResources().getColor(R.color.valueInc_low) );

        /* "Thermostat closed" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_cold),
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_thermostat_closed),
                getContext().getResources().getColor(R.color.valueInc_ok_low) );
        //Color.rgb(0,128,64) );

        /* "Thermostat regulation" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_thermostat_closed),
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_thermostat_opened),
                getContext().getResources().getColor(R.color.valueInc_ok) );

        /* "Thermostat opened" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_thermostat_opened),
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_load_reduction),
                getContext().getResources().getColor(R.color.valueInc_ok_high) );

        /* "Load reduction" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_load_reduction),
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_dangerzone),
                getContext().getResources().getColor(R.color.valueInc_high) );

        /* "Overheat" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_coolantTemperature_dangerzone),
                lGaugeValueMax,
                getContext().getResources().getColor(R.color.valueInc_veryHigh) );
    }
}
