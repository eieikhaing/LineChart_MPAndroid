package com.xan.linechart_mpandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

public class BarChartTest extends AppCompatActivity {
    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;

    float[] pricesAt10AM = {1112000, 1080000, 1012000, 912000};
    float[] pricesAt12PM = {1120000, 1080000, 1020000, 920000};
    float[] pricesAt2PM = {1115000, 1070000, 1030000, 915000};
    float[] pricesAt4PM = {1125000, 1080000, 1030000, 915000};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart_layout);
        barWidth = 0.22f;
        barSpace = 0.01f;
        groupSpace = 0.1f;
        chart = (BarChart)findViewById(R.id.barChart);
    /*    chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);*/
        chart.setDragEnabled(true);
        Description desc = new Description();
        desc.setText("Today Price");
        desc.setTextSize(20F);
        desc.setPosition(460F, 35F);
        chart.setDescription(desc);
        int groupCount = 4;

        ArrayList xVals = new ArrayList();

        xVals.add("16Karat");
        xVals.add("15Karat");
        xVals.add("14Karat");
        xVals.add("13Karat");


        ArrayList<BarEntry> entriesGroup1 = new ArrayList<>();
        ArrayList<BarEntry> entriesGroup2 = new ArrayList<>();
        ArrayList<BarEntry> entriesGroup3 = new ArrayList<>();
        ArrayList<BarEntry> entriesGroup4 = new ArrayList<>();


        for(int i = 0; i < pricesAt10AM.length; i++) {
            entriesGroup1.add(new BarEntry(i, pricesAt10AM[i]));
            entriesGroup2.add(new BarEntry(i, pricesAt12PM[i]));
            entriesGroup3.add(new BarEntry(i,pricesAt2PM[i]));
            entriesGroup4.add(new BarEntry(i,pricesAt4PM[i]));
        }
        BarDataSet set1 = new BarDataSet(entriesGroup1, "10:00AM");
        set1.setColor(Color.RED);
        BarDataSet set2 = new BarDataSet(entriesGroup2, "12:00PM");
        set2.setColor(Color.BLUE);
        BarDataSet set3 = new BarDataSet(entriesGroup3, "02:00PM");
        set3.setColor(Color.YELLOW);
        BarDataSet set4 = new BarDataSet(entriesGroup4, "04:00PM");
        set4.setColor(Color.GREEN);
        BarData data = new BarData(set1, set2 ,set3,set4);
        //data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setCenterAxisLabels(true);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();
       /* Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);*/
        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
//Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setValueFormatter(new LargeValueFormatter());
       /* leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);*/
        leftAxis.setAxisMaximum(1150000);
        leftAxis.setAxisMinimum(900000);
    }
}
