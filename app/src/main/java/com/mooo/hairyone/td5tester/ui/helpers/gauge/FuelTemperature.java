package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class FuelTemperature extends Td5Gauge
{


    public FuelTemperature(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                        R.integer.fueling_fuelTemperature_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                        R.integer.fueling_fuelTemperature_gaugeMax);


        this.setGaugeName(getContext().getResources().getString(
                R.string.fuel_temperature_short));

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

        this.setValue(888.8f);


        this.getValueDisplay().setValueDisplayFormat("%3.1f");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(
                R.string.fuel_temperature_unit_short));


        this.setGraduationCountMajor((int) ((lGaugeValueMax - lGaugeValueMin) / 20.0f) + 1);

        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
                .setGraduationColor(Color.LTGRAY);

        this.setGraduationCountMinor((int) ((lGaugeValueMax - lGaugeValueMin) / 10.0f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setValuesTextFormat("");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setLinesLengthFactor(5.0f);


        /*
            Sections definition
         */
        /* "Freeze" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.fueling_fuelTemperature_freeze),
                getContext().getResources().getColor(R.color.valueInc_veryLow) );
    }
}
