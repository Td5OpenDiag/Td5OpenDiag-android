package com.mooo.hairyone.td5tester.ui.helpers.gauge;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.aloike.libgauge.parts.Graduation;
import com.github.aloike.libgauge.parts.Label;
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


        this.setGraduationRange(
                lGaugeValueMin,
                lGaugeValueMax );

        this.setValue(lGaugeValueMax);


        init_graduations(lGaugeValueMin, lGaugeValueMax);
        init_labels();
        init_sections(lGaugeValueMin, lGaugeValueMax);
        init_valueDisplay();
    }

    private void    init_graduations(float pGaugeMin, float pGaugeMax)
    {
        Graduation  lGraduationMajor    = new Graduation();
        lGraduationMajor.setCount((int)(pGaugeMax - pGaugeMin) + 1);
        lGraduationMajor.setGraduationColor(Color.LTGRAY);
        lGraduationMajor.setLinesLengthFactor(7.5f);

        this.getDial().getGraduationsList().clear();
        this.getDial().getGraduationsList().add(lGraduationMajor);
    }

    private void   init_labels()
    {
        Label lLabelGaugeName   = new Label();

        lLabelGaugeName.setText(
                getContext().getResources().getString(R.string.battery_voltage_short)
        );
        lLabelGaugeName.setPosX_pc(50.0f);
        lLabelGaugeName.setPosY_pc(25.0f);
        lLabelGaugeName.setTextSizeFactor(10.0f);

        this.getDial().getLabelsList().clear();
        this.getDial().getLabelsList().add(lLabelGaugeName);
    }

    private void    init_sections(float pGaugeMin, float pGaugeMax)
    {
        /*
            Remove exinsting sections
         */
        this.getDial().getSectionsList().clear();


        /*
            Add dial sections
         */
        /* "Empty" section */
        this.section_add(
                pGaugeMin,
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                Color.RED
        );

        /* "ok" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_empty_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_full_mV) / 1000.0f,
                Color.GRAY
        );

        /* "Charging" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_charging_mV) / 1000.0f,
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                Color.GREEN
        );

        /* "high" section */
        this.section_add(
                getContext().getResources().getInteger( R.integer.batteryVoltage_high_mV) / 1000.0f,
                pGaugeMax,
                Color.RED
        );
    }

    private void    init_valueDisplay()
    {
        this.getValueDisplay().setValueDisplayFormat("%2.1f");

        this.getValueDisplay().setUnitText("V");
        this.getValueDisplay().setValueTextSizeFactor_pc(12);
    }
}
