package com.mooo.hairyone.td5tester.ui.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
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

    protected Indicator.Indicators  m_indicator         = Indicator.Indicators.KiteIndicator;
    protected int                   m_indicatorColor    = Color.WHITE;


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


    public void setIndicatorColor(int pColor)
    {
        this.m_indicatorColor   = pColor;
        this.getIndicator().setColor(this.m_indicatorColor);
    }


    public void setIndicatorEnabled(boolean pEnabled)
    {
        if( pEnabled )
        {
            this.setIndicator(this.m_indicator);
            this.getIndicator().setColor(this.m_indicatorColor);
        }
        else
        {
            this.setIndicator(Indicator.Indicators.NoIndicator);
        }
    }


    private void init()
    {
        /*
            Needle parameters
         */
        this.setIndicatorEnabled(true);

        this.setCenterCircleColor(Color.DKGRAY);
        this.setCenterCircleRadius( f_centerRadius );


        /*
            Marks
         */
//        this.setMarkStyle(Paint.Cap.ROUND);
        this.setMarkColor(Color.LTGRAY);
        this.setMarksNumber(9);

        this.setTickNumber(6);
        this.setTickPadding((int)(this.getSpeedometerWidth() * 0.4f));


        /*
            Text parameters
         */
        this.setTextTypeface(Typeface.MONOSPACE);

        this.setTextColor(getContext().getResources().getColor( R.color.textColorPrimary));

        //this.setGaugeNameBackgroundColor( getContext().getResources().getColor( R.color.windowBackground));
        this.setGaugeNameTextColor(getContext().getResources().getColor( R.color.textColorPrimary));
        this.setGaugeNameTextTypeface(Typeface.MONOSPACE);
        this.setGaugeNameTextSize(this.dpTOpx(16));
        //this.setGaugeNameTextSize(dpTOpx(this.getHeight() * 0.2f));
        this.setGaugeNameTextPosition(Gauge.Position.MIDTOP_CENTER);

        Paint lPaint    = getSpeedBackgroundPaint();
        lPaint.setStyle( Paint.Style.STROKE );
        lPaint.setColor(getContext().getResources().getColor( R.color.windowBackground));
        lPaint.setStrokeWidth(5f);
        setSpeedBackgroundPaint(lPaint);
        this.setSpeedTextColor( getContext().getResources().getColor( R.color.textColorPrimary) );
        this.setSpeedTextPosition(Gauge.Position.BOTTOM_CENTER);
        this.setSpeedTextSize(this.dpTOpx(24));

        this.setUnit("[unit]");
        this.setUnitTextColor( getContext().getResources().getColor( R.color.textColorPrimary) );
        this.setUnitTextSize(this.dpTOpx(16));
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

    @Override
    protected void  onDraw(Canvas canvas) {
        super.onDrawSuper(canvas);

        RectF speedBackgroundRect = getSpeedUnitTextBounds();
        speedBackgroundRect.left -= dpTOpx(this.getViewWidthNoPadding() * 0.01f);
        speedBackgroundRect.right += dpTOpx(this.getViewWidthNoPadding() * 0.01f);
        speedBackgroundRect.top -= dpTOpx(this.getViewHeightNoPadding() * 0.005f);
        speedBackgroundRect.bottom += dpTOpx(this.getViewHeightNoPadding() * 0.005f);
        canvas.drawRect(speedBackgroundRect, getSpeedBackgroundPaint());

        //this.setUnitTextSize(this.dpTOpx(this.getViewHeightNoPadding() * 0.03f));

        drawGaugeNameText(canvas);
        drawSpeedUnitText(canvas);
        drawIndicator(canvas);
        canvas.drawCircle(
                getSize() * .5f,
                getSize() * .5f,
                getCenterCircleRadius(),
                getCirclePaint() );
        drawNotes(canvas);
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


    @Override
    protected void updateBackgroundBitmap()
    {
        super.updateBackgroundBitmap();

/*        Canvas lCanvas  = new Canvas(this.getBackgroundBitmap());

        RectF speedBackgroundRect = getSpeedUnitTextBounds();
        speedBackgroundRect.left -= dpTOpx(this.getWidth() * 0.03f);
        speedBackgroundRect.right += dpTOpx(this.getWidth() * 0.03f);
        speedBackgroundRect.top -= dpTOpx(this.getViewHeightNoPadding() * 0.005f);
        speedBackgroundRect.bottom += dpTOpx(this.getViewHeightNoPadding() * 0.005f);
        lCanvas.drawRect(speedBackgroundRect, getSpeedBackgroundPaint());
 */
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
