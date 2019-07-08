package com.xan.linechart_mpandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class TestChart extends AppCompatActivity {
    private LineChart mChart;
    float[] pricesAt10AM = {1112000, 1080000, 1012000, 912000};
    float[] pricesAt12PM = {1120000, 1080000, 1020000, 920000};
    float[] pricesAt2PM = {1115000, 1070000, 1030000, 915000};
    float[] pricesAt4PM = {1125000, 1080000, 1030000, 915000};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (LineChart)findViewById(R.id.lineChart);
        /*mChart.setOnChartGestureListener(MainActivity.this);
        mChart.setOnChartValueSelectedListener(MainActivity.this);*/
        mChart.setDragEnabled(true);
        mChart.getAxisRight().setEnabled(false);
        YAxis yAxis = mChart.getAxisLeft();
        yAxis.setAxisMaximum(pricesAt2PM[0]+50000);
        yAxis.setAxisMinimum(900000);
        //mChart.getDescription().setText("Today price");
       // mChart.getDescription().setPosition(100f,20f);
        Description desc = new Description();
        desc.setText("Today Price");
        desc.setTextSize(20F);
        desc.setPosition(460F, 35F);
        mChart.setDescription(desc);


        ArrayList<Entry> yValues1 = new ArrayList<>();
        for(int i=0 ; i<pricesAt10AM.length ;i++){
            yValues1.add(new Entry(i,pricesAt10AM[i]));
        }
        ArrayList<Entry> yValues2 = new ArrayList<>();
        for(int i=0 ; i<pricesAt12PM.length ;i++){
            yValues2.add(new Entry(i,pricesAt12PM[i]));
        }
        ArrayList<Entry> yValues3 = new ArrayList<>();
        for(int i=0 ; i<pricesAt2PM.length ;i++){
            yValues3.add(new Entry(i,pricesAt2PM[i]));
        }
        ArrayList<Entry> yValues4 = new ArrayList<>();
        for(int i=0 ; i<pricesAt4PM.length ;i++){
            yValues4.add(new Entry(i,pricesAt4PM[i]));
        }


        LineDataSet set1 = new LineDataSet(yValues1,"10:00AM");
        LineDataSet set2 = new LineDataSet(yValues2,"12:00PM");
        LineDataSet set3 = new LineDataSet(yValues3,"02:00PM");
        LineDataSet set4 = new LineDataSet(yValues4,"04:00PM");

        set1.setColor(Color.RED);
        set1.setValueTextSize(10f);
        set1.setLineWidth(3f);
        set1.setFillAlpha(1200000);

        set2.setColor(Color.YELLOW);
        set2.setValueTextSize(10f);
        set2.setLineWidth(3f);
        set2.setFillAlpha(1200000);

        set3.setColor(Color.BLUE);
        set3.setValueTextSize(10f);
        set3.setLineWidth(3f);
        set3.setFillAlpha(1200000);

        set4.setValueTextSize(10f);
        set4.setLineWidth(3f);
        set4.setFillAlpha(1200000);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);

        LineData lineData = new LineData(dataSets);
        lineData.setDrawValues(false);
        mChart.setData(lineData);

        String[] values = new String[]{"16 Karat","15 Karat","14 Karat","13 Karat"};
        XAxis xAxis = mChart.getXAxis();
        xAxis.setValueFormatter(new MyxAixValueFormatter(values));

        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);

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
