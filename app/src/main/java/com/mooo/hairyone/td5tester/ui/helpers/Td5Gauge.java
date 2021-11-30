package com.mooo.hairyone.td5tester.ui.helpers;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import com.github.aloike.libgauge.Gauge;

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


    public Td5Gauge(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        this.init();
    }

    private void init()
    {
        this.LOG_TAG    = this.getClass().getName();


        /*
            Needle parameters
         */
        this.setEnabled(true);

        this.init_graduations();
        this.init_labels();
        this.init_sections();
        this.init_valueDisplay();
    }


    private void    init_graduations()
    {

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

    }


    private void    init_labels()
    {
        /* Clear existing labels */
        this.getDial().getLabelsList().clear();

        /* Create gauge name's label */
        Label   lLabelGaugeName = new Label();
        this.getDial().getLabelsList().add(lLabelGaugeName);

        lLabelGaugeName.setColor(getContext().getResources().getColor( R.color.textColorPrimary));
        lLabelGaugeName.setTypeface(Typeface.MONOSPACE);
        lLabelGaugeName.setTextSizeFactor(10.0f);
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
    }


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

}
