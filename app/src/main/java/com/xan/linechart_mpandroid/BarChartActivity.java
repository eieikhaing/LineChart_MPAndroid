package com.xan.linechart_mpandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;


import java.util.ArrayList;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    float[] pricesAt10AM = {1112000, 1080000, 1012000, 912000};
    float[] pricesAt12PM = {1120000, 1080000, 1020000, 920000};
    float[] pricesAt2PM = {1115000, 1070000, 1030000, 915000};
    float[] pricesAt4PM = {1125000, 1080000, 1030000, 915000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barchart_layout);

        BarChart mChart = (BarChart) findViewById(R.id.barChart);

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

        float groupSpace = 0.1f;
        float barSpace = 0.01f;
        float barWidth = 0.22f;


        BarData data = new BarData(set1, set2,set3,set4);
        data.setBarWidth(barWidth);
        mChart.setData(data);

        Description desc = new Description();
        desc.setText("Today Price");
        desc.setTextSize(20F);
        desc.setPosition(460F, 35F);
        mChart.setDescription(desc);

        String[] values = new String[]{"16 Karat","15 Karat","14 Karat","13 Karat"};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyxAixValueFormatter(values));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        //xAxis.setAxisMaximum(4);



        mChart.setDragEnabled(true);
        mChart.setVisibleXRangeMaximum(3);
        mChart.getAxisRight().setEnabled(false);
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setAxisMaximum(1200000);
        yAxis.setAxisMinimum(900000);

        int groupCount = 4;

        //IMPORTANT *****
        data.setBarWidth(0.15f);
        mChart.getXAxis().setAxisMinimum(0f);
        //mChart.getXAxis().setAxisMaximum(0 +mChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mChart.groupBars(0, groupSpace, barSpace); // perform the "explicit" grouping
        //***** IMPORTANT
        mChart.setFitBars(true);
        mChart.invalidate(); // refresh


    }
    public class MyxAixValueFormatter implements IAxisValueFormatter
    {
        private String[] mValues;
        public MyxAixValueFormatter(String[] values) {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}
