package com.mooo.hairyone.td5tester.ui.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.github.aloike.libgauge.Gauge;

import com.github.aloike.libgauge.attributes.Range;
import com.github.aloike.libgauge.parts.Graduation;
import com.github.aloike.libgauge.parts.Label;
import com.github.aloike.libgauge.parts.Section;
import com.mooo.hairyone.td5tester.R;

import java.util.Locale;
import java.util.zip.DataFormatException;

public class Td5Gauge extends Gauge {

    protected enum  EGraduationsIndex
    {
        GRAD_MINOR(0),
        GRAD_MAJOR(1);


        private EGraduationsIndex(int pIndex)
        {
            this.m_index    = pIndex;
        }

        public int  getIndex()
        {
            return this.m_index;
        }

        private final int m_index;
    }

    protected enum  ELabelsIndex
    {
        LABEL_GAUGENAME(0),
        LABEL_DIALRANGEFACTOR(1);


        private ELabelsIndex(int pIndex)
        {
            this.m_index    = pIndex;
        }

        public int  getIndex()
        {
            return this.m_index;
        }

        private final int m_index;
    }

    private String      LOG_TAG = new String("Td5Gauge");

    protected float     m_section_width_factor_pc   = 7.5f;
//    protected float     m_dialValueRangeFactor      = 1.0f;

//    protected final float   f_centerRadius  = getContext().getResources().getDimension( R.dimen.gauges_center_circle_radius);

//    protected Indicator.Indicators  m_indicator         = Indicator.Indicators.KiteIndicator;
//    protected int                   m_indicatorColor    = Color.WHITE;


//    public Td5Gauge(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    public Td5Gauge(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }

//    public Td5Gauge(Context context) {
//        super(context);
//    }


//    public void setIndicatorColor(int pColor)
//    {
//        this.m_indicatorColor   = pColor;
//        this.getIndicator().setColor(this.m_indicatorColor);
//    }


//    public void setIndicatorEnabled(boolean pEnabled)
//    {
//        if( pEnabled )
//        {
//            this.setIndicator(this.m_indicator);
//            this.getIndicator().setColor(this.m_indicatorColor);
//        }
//        else
//        {
//            this.setIndicator(Indicator.Indicators.NoIndicator);
//        }
//    }


    private void init()
    {
        this.LOG_TAG    = this.getClass().getName();


        /*
            Needle parameters
         */
        this.setEnabled(true);

//        this.setCenterCircleColor(Color.DKGRAY);
//        this.setCenterCircleRadius( f_centerRadius );


        this.init_graduations();
        this.init_labels();
        this.init_sections();
        this.init_valueDisplay();


        /*
            Text parameters
         */
//        this.setTextTypeface(Typeface.MONOSPACE);

//        this.setTextColor(getContext().getResources().getColor( R.color.textColorPrimary));



//        Paint lPaint    = getSpeedBackgroundPaint();
//        lPaint.setStyle( Paint.Style.STROKE );
//        lPaint.setColor(getContext().getResources().getColor( R.color.windowBackground));
//        lPaint.setStrokeWidth(5f);
//        setSpeedBackgroundPaint(lPaint);
//        this.setSpeedTextColor( getContext().getResources().getColor( R.color.textColorPrimary) );
//        this.setSpeedTextPosition(Gauge.Position.BOTTOM_CENTER);
//        this.setSpeedTextSize(this.dpTOpx(24));




//        /*
//            Effects
//         */
//        // Add/remove blur effect
//        this.setWithEffects(false);
//
//        this.setWithTremble(false);
//        this.setTrembleDegree(5);
//        this.setTrembleDuration(500);
    }


    private void    init_graduations()
    {
//        this.setMarkStyle(Paint.Cap.ROUND);
//        this.setMarkColor(Color.LTGRAY);
//        this.setMarksNumber(9);

        /* Clear existing graduations */
        this.getDial().getGraduationsList().clear();


        /* First, set minor graduations (frequent, without text) */
        Graduation  lGraduationMinor    = new Graduation();

        lGraduationMinor.setCount(11);
        lGraduationMinor.setGraduationColor(Color.LTGRAY);
        lGraduationMinor.setValuesTextFormat(""); //< This graduation won't have text

        this.getDial().getGraduationsList().add( lGraduationMinor );


        /* Then, set major graduations (less frequent, with text value) */
        Graduation  lGraduationMajor    = new Graduation();

        lGraduationMajor.setCount(6);
        lGraduationMajor.setGraduationColor(Color.BLACK);
        lGraduationMajor.setValuesTextFormat("%.0f");
        lGraduationMajor.setTextColor( getContext().getResources().getColor( R.color.textColorPrimary) );

        this.getDial().getGraduationsList().add( lGraduationMajor );

//        this.setTickNumber(6);
//        this.setTickPadding((int)(this.getSpeedometerWidth() * 0.4f));
//        this.getDial().getGraduationsList().get(0).setLinesLengthFactor();
    }


    private void    init_labels()
    {
        /* Clear existing labels */
        this.getDial().getLabelsList().clear();

        /* Create gauge name's label */
        Label   lLabelGaugeName = new Label();
        this.getDial().getLabelsList().add(lLabelGaugeName);

        //this.setGaugeNameBackgroundColor( getContext().getResources().getColor( R.color.windowBackground));
//        this.setGaugeNameTextColor(getContext().getResources().getColor( R.color.textColorPrimary));
        lLabelGaugeName.setColor(getContext().getResources().getColor( R.color.textColorPrimary));
        lLabelGaugeName.setTypeface(Typeface.MONOSPACE);
//        this.setGaugeNameTextSize(this.dpTOpx(16));
        lLabelGaugeName.setTextSizeFactor(10.0f);

//        this.setGaugeNameTextPosition(Gauge.Position.MIDTOP_CENTER);
        lLabelGaugeName.setPosX_pc(50.0f);
        lLabelGaugeName.setPosY_pc(25.0f);


        /* Create gauge name's label */
        Label   lLabelDialRangeFactor = new Label();
        this.getDial().getLabelsList().add(lLabelDialRangeFactor);

        lLabelDialRangeFactor.setColor(
                getContext().getResources().getColor( R.color.textColorPrimary));
        lLabelDialRangeFactor.setTypeface(Typeface.MONOSPACE);
        lLabelDialRangeFactor.setTextSizeFactor(10.0f);

        lLabelDialRangeFactor.setPosX_pc(50.0f);
        lLabelDialRangeFactor.setPosY_pc(35.0f);


    }


    private void    init_sections()
    {
        /* First, clear all existing sections */
        this.getDial().getSectionsList().clear();
    }


    private void    init_valueDisplay()
    {
        /*
            "Value" parameters
         */
        this.getValueDisplay().setValueTextColor(
                getContext().getResources().getColor( R.color.textColorPrimary)
        );


        /*
            "Unit" parameters
         */
        this.getValueDisplay().setUnitText("[unit]");
        this.getValueDisplay().setUnitTextColor(
                getContext().getResources().getColor( R.color.textColorPrimary) );
//        this.setUnitTextSize(this.dpTOpx(16));
//        this.setUnitUnderSpeedText(true);
    }


//    @Override
//    protected void  onDraw(Canvas canvas) {
//        super.onDrawSuper(canvas);
//
//        RectF speedBackgroundRect = getSpeedUnitTextBounds();
//        speedBackgroundRect.left -= dpTOpx(this.getViewWidthNoPadding() * 0.01f);
//        speedBackgroundRect.right += dpTOpx(this.getViewWidthNoPadding() * 0.01f);
//        speedBackgroundRect.top -= dpTOpx(this.getViewHeightNoPadding() * 0.005f);
//        speedBackgroundRect.bottom += dpTOpx(this.getViewHeightNoPadding() * 0.005f);
//        canvas.drawRect(speedBackgroundRect, getSpeedBackgroundPaint());
//
//        //this.setUnitTextSize(this.dpTOpx(this.getViewHeightNoPadding() * 0.03f));
//
//        drawGaugeNameText(canvas);
//        drawSpeedUnitText(canvas);
//        drawIndicator(canvas);
//        canvas.drawCircle(
//                getSize() * .5f,
//                getSize() * .5f,
//                getCenterCircleRadius(),
//                getCirclePaint() );
//        drawNotes(canvas);
//    }


//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        /* Take into account the dial value range factor */
//        Range   lRangeWithFactor    = new Range();
//        lRangeWithFactor.setValueDisplayRange(
//                this.m_range.getValueDisplayMin(),
//                this.m_range.getValueDisplayMax() );
//        lRangeWithFactor.setGraduationRange(
//                this.m_range.getGraduationMin() / this.m_dialValueRangeFactor,
//                this.m_range.getGraduationMax() / this.m_dialValueRangeFactor );
//
//
//        /* Draw the gauge dial */
//        this.m_dial.draw(
//                canvas,
//                lRangeWithFactor,
//                this.m_angles
//        );
//
//        /* Draw the value display */
//        this.m_valueDisplay.draw(
//                canvas,
//                lRangeWithFactor,
//                this.m_valueCurrent
//        );
//
//        /* Draw the needle */
//        if(this.isEnabled()) {
//            this.m_needle.draw(
//                    canvas,
//                    lRangeWithFactor,
//                    this.m_angles,
//                    this.m_valueCurrent / this.m_dialValueRangeFactor
//            );
//        }
//
//        /* Draw the needle axis */
//        this.m_needleAxis.draw(
//                canvas
//        );
//    }


    public void
            section_add(
                    float pValueFrom,
                    float pValueTo,
                    int pColor)
    {
        try
        {
            Section lSection
                    = new Section(
                    pValueFrom,
                    pValueTo,
                    pColor );

            lSection.setWidthFactor_pc(this.m_section_width_factor_pc);

            this.getDial().getSectionsList().add( lSection );
        }
        catch (DataFormatException e)
        {
            Log.e(  LOG_TAG,
                    "An exception has occured while setting sections: " + e.getMessage());
        }
    }

    public void setGaugeName(String pName)
    {
        this.getDial().getLabelsList().get(ELabelsIndex.LABEL_GAUGENAME.getIndex() ).setText(pName);
    }


    public void setGraduationCountMajor(int pCount)
    {
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MAJOR.getIndex() )
                .setCount(pCount);
    }

    public void setGraduationCountMinor(int pCount)
    {
        this.getDial().getGraduationsList().get(EGraduationsIndex.GRAD_MINOR.getIndex() )
                .setCount(pCount);
    }


    public void setDialValueRangeFactor(float pFactor)
    {
        this.getDial().setValueRangeFactor(pFactor);

        String lText    = new String("");

        if(pFactor != 1)
        {
            lText   = String.format(
                            Locale.ENGLISH,
                            "x%.0f",
                            pFactor );
        }


        this.getDial().getLabelsList().get(ELabelsIndex.LABEL_DIALRANGEFACTOR.getIndex() )
                .setText( lText );
    }


    public void setGraduationMin(float pValue)
    {
        this.setGraduationRange(
                pValue,
                this.getRange().getGraduationMax()
        );
    }


    public void setGraduationMax(float pValue)
    {
        this.setGraduationRange(
                this.getRange().getGraduationMin(),
                pValue
        );
    }


    public void setUnitText(String pText)
    {
        this.getValueDisplay().setUnitText( pText );
    }


//    @Override
//    protected void updateBackgroundBitmap()
//    {
//        super.updateBackgroundBitmap();
//
///*        Canvas lCanvas  = new Canvas(this.getBackgroundBitmap());
//
//        RectF speedBackgroundRect = getSpeedUnitTextBounds();
//        speedBackgroundRect.left -= dpTOpx(this.getWidth() * 0.03f);
//        speedBackgroundRect.right += dpTOpx(this.getWidth() * 0.03f);
//        speedBackgroundRect.top -= dpTOpx(this.getViewHeightNoPadding() * 0.005f);
//        speedBackgroundRect.bottom += dpTOpx(this.getViewHeightNoPadding() * 0.005f);
//        lCanvas.drawRect(speedBackgroundRect, getSpeedBackgroundPaint());
// */
//    }


//    private float
//            valueToGaugePercent(float pValue)
//    {
//        float   lGaugeMin   = getMinSpeed();
//        float   lGaugeMax   = getMaxSpeed();
//        float retval    = 0.0f;
//
//        retval  = (pValue - lGaugeMin) / (lGaugeMax - lGaugeMin);
//
//        return retval;
//    }
}
