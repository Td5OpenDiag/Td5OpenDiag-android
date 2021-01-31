package com.mooo.hairyone.td5tester.ui.helpers;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.anastr.speedviewlib.DeluxeSpeedView;
import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.components.Section;
import com.github.anastr.speedviewlib.components.Style;
import com.github.anastr.speedviewlib.components.indicators.Indicator;
import com.mooo.hairyone.td5tester.R;

public class Td5Gauge extends DeluxeSpeedView {

    protected final float   f_centerRadius  = getContext().getResources().getDimension( R.dimen.gauges_center_circle_radius);
    protected final float   f_section_width_factor    = 0.4f;


    public Td5Gauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Td5Gauge(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.init();
    }

    public Td5Gauge(Context context) {
        super(context);
    }


    private void init()
    {
        /*
            Needle parameters
         */
        //this.setIndicator(Indicator.Indicators.NeedleIndicator);
        this.setIndicator(Indicator.Indicators.KiteIndicator);
        this.getIndicator().setColor(Color.RED);
        this.getIndicator().setColor(Color.WHITE);

        this.setCenterCircleColor(Color.DKGRAY);
        this.setCenterCircleRadius( f_centerRadius );


        /*
            Marks
         */
//        this.setMarkStyle(Paint.Cap.ROUND);
        this.setMarkColor(Color.LTGRAY);
        this.setMarksNumber(9);
//        this.setMarksPadding()

        this.setTickNumber(6);
        this.setTickPadding((int)(this.getSpeedometerWidth() * 0.4f));


        /*
            Text parameters
         */
        this.setTextColor(Color.WHITE);

        this.setSpeedTextSize(this.dpTOpx(10));
        this.setSpeedBackgroundColor(Color.WHITE);
        this.setSpeedTextPosition(Gauge.Position.BOTTOM_CENTER);

        this.setUnit("[unit]");
        this.setUnitTextSize(this.dpTOpx(10));
        this.setUnitUnderSpeedText(true);


        /*
            Effects
         */
        // Add/remove blur effect
        this.setWithEffects(false);

        this.setWithTremble(false);
        this.setTrembleDegree(5);
        this.setTrembleDuration(500);


        /*
            Sections definition
         */

        /* First, clear all existing sections */
        this.clearSections();
    }


    public void
            section_add(
                    float pValueFrom, float pValueTo,
                    int pColor)
    {
        float lSectionBegin = 0.0f;
        float lSectionEnd   = 1.0f;

        lSectionBegin   = valueToGaugePercent(pValueFrom);
        lSectionEnd     = valueToGaugePercent(pValueTo);

        Section lSection
                = new Section(
                lSectionBegin,
                lSectionEnd,
                pColor,
                this.getSpeedometerWidth(),
                Style.BUTT );


        lSection.setWidth(this.getSpeedometerWidth() * f_section_width_factor);


        this.addSections( lSection );
    }


    public void setMax(float pValue)
    {
        this.setMaxSpeed(pValue);
    }


    public void setMin(float pValue)
    {
        this.setMinSpeed(pValue);
    }


    public void setValue(float pValue)
    {
        //this.setSpeedAt(pValue);
        this.speedTo(pValue, 100);
    }


    private float
            valueToGaugePercent(float pValue)
    {
        float   lGaugeMin   = getMinSpeed();
        float   lGaugeMax   = getMaxSpeed();
        float retval    = 0.0f;

        retval  = (pValue - lGaugeMin) / (lGaugeMax - lGaugeMin);

        return retval;
    }
}
