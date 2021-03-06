package com.grs.demo.chart;

import java.util.ArrayList;
  
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;  
import com.github.mikephil.charting.data.Entry;  
import com.github.mikephil.charting.data.LineData;  
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.grs.demo.R;
import com.grs.demo.base.BaseActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

public class ChartActivity extends BaseActivity {
  
    private LineChart mLineChart;
    private PieChart mChart;
//  private Typeface mTf;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_chart);
          
        mLineChart = (LineChart) findViewById(R.id.spread_line_chart);
          
//      mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");  
  
        LineData mLineData = getLineData(36, 100);
        showChart(mLineChart, mLineData, Color.rgb(114, 188, 223));


        mChart = (PieChart) findViewById(R.id.spread_pie_chart);
        PieData mPieData = getPieData(4, 100);
        showChart(mChart, mPieData);
    }  
      
    // 设置显示的样式  
    private void showChart(LineChart lineChart, LineData lineData, int color) {  
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框  
  
        // no description text  
        lineChart.setDescription("");// 数据描述  
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview  
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");  
          
        // enable / disable grid background  
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色  
        lineChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度  
  
        // enable touch gestures  
        lineChart.setTouchEnabled(true); // 设置是否可以触摸  
  
        // enable scaling and dragging  
        lineChart.setDragEnabled(true);// 是否可以拖拽  
        lineChart.setScaleEnabled(true);// 是否可以缩放  
  
        // if disabled, scaling can be done on x- and y-axis separately  
        lineChart.setPinchZoom(false);//   
  
        lineChart.setBackgroundColor(color);// 设置背景  
  
        // add data  
        lineChart.setData(lineData); // 设置数据  
  
        // get the legend (only possible after setting data)  
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的  
  
        // modify the legend ...  
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);  
        mLegend.setForm(LegendForm.CIRCLE);// 样式  
        mLegend.setFormSize(6f);// 字体  
        mLegend.setTextColor(Color.WHITE);// 颜色  
//      mLegend.setTypeface(mTf);// 字体  
  
        lineChart.animateX(2500); // 立即执行的动画,x轴  
      }  
      
    /** 
     * 生成一个数据 
     * @param count 表示图表中有多少个坐标点 
     * @param range 用来生成range以内的随机数 
     * @return 
     */  
    private LineData getLineData(int count, float range) {  
        ArrayList<String> xValues = new ArrayList<String>();  
        for (int i = 0; i < count; i++) {  
            // x轴显示的数据，这里默认使用数字下标显示  
            xValues.add("" + i);  
        }  
  
        // y轴的数据  
        ArrayList<Entry> yValues = new ArrayList<Entry>();  
        for (int i = 0; i < count; i++) {  
            float value = (float) (Math.random() * range) + 3;  
            yValues.add(new Entry(value, i));  
        }  
  
        // create a dataset and give it a type  
        // y轴的数据集合  
        LineDataSet lineDataSet = new LineDataSet(yValues, "测试折线图" /*显示在比例图上*/);  
        // mLineDataSet.setFillAlpha(110);  
        // mLineDataSet.setFillColor(Color.RED);  
  
        //用y轴的集合来设置参数  
        lineDataSet.setLineWidth(1.75f); // 线宽  
        lineDataSet.setCircleSize(3f);// 显示的圆形大小  
        lineDataSet.setColor(Color.WHITE);// 显示颜色  
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色  
        lineDataSet.setHighLightColor(Color.WHITE); // 高亮的线的颜色  
  
        ArrayList<LineDataSet> lineDataSets = new ArrayList<LineDataSet>();  
        lineDataSets.add(lineDataSet); // add the datasets  
  
        // create a data object with the datasets  
        LineData lineData = new LineData(xValues, lineDataSets);  
  
        return lineData;  
    }


    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        pieChart.setDescription("测试饼状图");

        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);

        pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

        pieChart.setCenterText("Quarterly Revenue");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360);
    }

    /**
     *
     * @param count 分成几部分
     * @param range
     */
    private PieData getPieData(int count, float range) {

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 14;
        float quarterly2 = 14;
        float quarterly3 = 34;
        float quarterly4 = 38;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "Quarterly Revenue 2014"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }
}