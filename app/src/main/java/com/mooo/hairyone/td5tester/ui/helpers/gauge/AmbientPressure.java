package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class AmbientPressure extends Td5Gauge
{


    public AmbientPressure(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
        this.setValue(888f);
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                R.integer.intake_ambientPressure_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                R.integer.intake_ambientPressure_gaugeMax);



        this.setGaugeName(getContext().getResources().getString(R.string.ambient_pressure_short));

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

//        this.setValue(lGaugeValueMax);
        this.setValue(888);


        this.getValueDisplay().setValueDisplayFormat("%3.0f");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(R.string.ambient_pressure_unit_short));


        this.setGraduationCountMajor((int) ((lGaugeValueMax - lGaugeValueMin) / 10.0f) + 1);
        //this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
         //       .setValuesTextFormat("%.0f");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
                .setGraduationColor(Color.LTGRAY);

        this.setGraduationCountMinor((int) ((lGaugeValueMax - lGaugeValueMin) / 5.0f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setValuesTextFormat("");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setLinesLengthFactor(5.0f);


        /*
            Sections definition
         */

        /* No section on this gauge. */
    }
}
