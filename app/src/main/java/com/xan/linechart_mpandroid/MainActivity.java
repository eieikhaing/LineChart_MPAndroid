package com.xan.linechart_mpandroid;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private LineChart mChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChart = (LineChart)findViewById(R.id.lineChart);
        /*mChart.setOnChartGestureListener(MainActivity.this);
        mChart.setOnChartValueSelectedListener(MainActivity.this);*/
        mChart.setDragEnabled(true);

        ArrayList<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,60f));
        yValues.add(new Entry(1,50f));
        yValues.add(new Entry(2,70f));
        yValues.add(new Entry(3,30f));
        yValues.add(new Entry(4,50f));
        yValues.add(new Entry(5,60f));
        yValues.add(new Entry(6,65f));

        LineDataSet set1 = new LineDataSet(yValues,"Data set1");
        set1.setColor(Color.RED);
        set1.setValueTextSize(10f);
        set1.setValueTextColor(Color.GREEN);
        set1.setLineWidth(3f);
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData lineData = new LineData(dataSets);
        mChart.setData(lineData);

    }
}
