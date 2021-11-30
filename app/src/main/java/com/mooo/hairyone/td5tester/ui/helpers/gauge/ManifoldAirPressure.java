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



        this.setGaugeName(getContext().getResources().getString(R.string.manifold_turbo_pressure_short));

        this.setAngles(
                -135,
                90
        );

        this.setGraduationMin( lGaugeValueMin_bar );
        this.setGraduationMax( lGaugeValueMax_bar );

        this.setValue(8.8f);


        this.getValueDisplay().setValueDisplayFormat("% 1.1f");
        this.getValueDisplay().setUnitText(getContext().getResources().getString(R.string.manifold_turbo_pressure_unit_short));


        this.setGraduationCountMajor((int) ((lGaugeValueMax_bar - lGaugeValueMin_bar) / 0.5f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex())
                .setValuesTextFormat("%.1f");

        this.setGraduationCountMinor((int) ((lGaugeValueMax_bar - lGaugeValueMin_bar) / 0.25f) + 1);
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex())
                .setValuesTextFormat("");


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
