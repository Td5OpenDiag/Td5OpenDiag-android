package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.mooo.hairyone.td5tester.R;
import com.mooo.hairyone.td5tester.ui.helpers.Td5Gauge;

public class CylinderBalance extends Td5Gauge
{
    private int m_cylinderNumber    = 0;


    public CylinderBalance(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }


    protected void init()
    {
        int lGaugeValueMin
                = getContext().getResources().getInteger(
                R.integer.engine_cylinder_balance_gaugeMin);
        int lGaugeValueMax
                = getContext().getResources().getInteger(
                R.integer.engine_cylinder_balance_gaugeMax);


        this.setGaugeName(
                getContext().getResources().getString(
                        R.string.cylinder_balance_short,
                        this.m_cylinderNumber )
                //String.format(
                //        getContext().getResources().getString(R.string.cylinder_balance_short),
                //        42d )
        );

        this.setGraduationMin( lGaugeValueMin );
        this.setGraduationMax( lGaugeValueMax );

//        this.setValue(lGaugeValueMax);
        this.setValue(88f);


        this.getValueDisplay().setValueDisplayFormat("%+2.0f");
        this.getValueDisplay().setUnitText(
                getContext().getResources().getString(R.string.cylinder_balance_unit) );


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
        this.getDial().getSectionsList().clear();

        /* "Too low" section */
        this.section_add(
                lGaugeValueMin,
                getContext().getResources().getInteger( R.integer.engine_cylinder_balance_too_low),
                getContext().getResources().getColor(R.color.warning) );


        /* "Ok" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_cylinder_balance_ok_low),
                getContext().getResources().getInteger( R.integer.engine_cylinder_balance_ok_high),
                getContext().getResources().getColor(R.color.valueInc_ok) );


        /* "Too high" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.engine_cylinder_balance_too_high),
                lGaugeValueMax,
                getContext().getResources().getColor(R.color.warning) );
    }

    public void setCylinderNumber(int pNumber)
    {
        this.m_cylinderNumber   = pNumber;

        this.init();
    }
}