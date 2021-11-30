package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class MassAirflow extends Td5Gauge
{


    public MassAirflow(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                        R.integer.intake_massAirflow_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                        R.integer.intake_massAirflow_gaugeMax);



        this.setGaugeName(getContext().getResources().getString(
                R.string.mass_airflow_short));

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

        this.setValue(888.8f);


        this.getValueDisplay().setValueDisplayFormat("%3.1f");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(
                R.string.mass_airflow_unit_short));


        this.setGraduationCountMajor((int) ((lGaugeValueMax - lGaugeValueMin) / 100.0f) + 1);

        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
                .setGraduationColor(Color.LTGRAY);

        this.setGraduationCountMinor((int) ((lGaugeValueMax - lGaugeValueMin) / 50.0f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setValuesTextFormat("");
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setLinesLengthFactor(5.0f);


        /*
            Sections definition
         */
        /* "Low" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.intake_massAirflow_low),
                getContext().getResources().getColor(R.color.valueInc_veryLow) );
    }
}
