package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class IntakeAirTemperature extends Td5Gauge
{


    public IntakeAirTemperature(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                        R.integer.intake_inletAirTemperature_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                        R.integer.intake_inletAirTemperature_gaugeMax);



        this.setGaugeName(getContext().getResources().getString(
                R.string.intake_air_temperature_short));

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

        this.setValue(88.8f);


        this.getValueDisplay().setValueDisplayFormat("%3.1f");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(
                R.string.intake_air_temperature_unit_short));


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
        /* "Cold" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.intake_inletAirTemperature_cold),
                getContext().getResources().getColor(R.color.valueInc_low) );

        /* "Hot" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.intake_inletAirTemperature_hot),
                lGaugeValueMax,
                getContext().getResources().getColor(R.color.valueInc_high) );
    }
}
