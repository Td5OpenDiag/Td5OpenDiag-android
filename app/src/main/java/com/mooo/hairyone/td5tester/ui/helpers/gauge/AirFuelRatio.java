package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class AirFuelRatio extends Td5Gauge
{


    public AirFuelRatio(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                        R.integer.fueling_airFuelRatio_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                        R.integer.fueling_airFuelRatio_gaugeMax);


        this.setGaugeName(getContext().getResources().getString(
                R.string.air_fuel_ratio_short));

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

//        this.setValue(lGaugeValueMax);
        this.setValue(88f);


        this.getValueDisplay().setValueDisplayFormat("%2.0f:1");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(
                R.string.air_fuel_ratio_unit));


        this.setGraduationCountMajor((int) ((lGaugeValueMax - lGaugeValueMin) / 5.0f) + 1);
        //this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
        //       .setValuesTextFormat("%.0f");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
                .setGraduationColor(Color.LTGRAY);

        this.setGraduationCountMinor((int) ((lGaugeValueMax - lGaugeValueMin) / 2.5f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setValuesTextFormat("");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setLinesLengthFactor(5.0f);


        /*
            Sections definition
         */
    }
}