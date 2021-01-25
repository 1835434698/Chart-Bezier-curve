package com.tangzy.minedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DemoView demoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoView = findViewById(R.id.demoView);

        init();
    }

    private void init() {
        ArrayList<TextLocation> textsOnY = new ArrayList<>();
        textsOnY.add(new TextLocation("-20", 1f));
        textsOnY.add(new TextLocation("0", 0.75f));
        textsOnY.add(new TextLocation("20", 0.5f));
        textsOnY.add(new TextLocation("40", 0.25f));
        textsOnY.add(new TextLocation("60", 0f));
        demoView.setDataListY(textsOnY);
        demoView.setMaxY(60);
        demoView.setMinY(-20);

        ArrayList<TextLocation> textsOnXTop = new ArrayList<>();
        textsOnXTop.add(new TextLocation("t1", 0f));
        textsOnXTop.add(new TextLocation("t2", 0.1f));
        textsOnXTop.add(new TextLocation("t3", 0.4f));
        textsOnXTop.add(new TextLocation("t4", 0.7f));
        textsOnXTop.add(new TextLocation("t5", 1f));
        demoView.setTextsOnXTop(textsOnXTop);

        ArrayList<TextLocation> textsOnXBottom = new ArrayList<>();
        TextLocation t1 = new TextLocation("130", 0f);
        textsOnXBottom.add(t1);
        t1 = new TextLocation("140", 0.25f);
        textsOnXBottom.add(t1);
        t1 = new TextLocation("150", 0.5f);
        textsOnXBottom.add(t1);
        t1 = new TextLocation("160", 0.75f);
        textsOnXBottom.add(t1);
        t1 = new TextLocation("170", 1f);
        textsOnXBottom.add(t1);
        demoView.setTextsOnXBottom(textsOnXBottom);

        ArrayList<Integer> customLines = demoView.getCustomLines();
        customLines.clear();

        customLines.add(40);
        customLines.add(45);
        customLines.add(55);
        customLines.add(56);
        customLines.add(57);
        customLines.add(60);
        customLines.add(58);
        customLines.add(55);
        customLines.add(53);
        customLines.add(50);
        customLines.add(45);
        customLines.add(45);
        customLines.add(44);
        customLines.add(44);
        customLines.add(44);
        customLines.add(41);
        customLines.add(40);
        customLines.add(40);
        customLines.add(35);
        customLines.add(35);
        customLines.add(40);
        customLines.add(40);
        customLines.add(45);
        customLines.add(50);
        customLines.add(55);
        customLines.add(60);
        customLines.add(60);
        customLines.add(60);
        customLines.add(60);
        customLines.add(55);
        customLines.add(57);
        customLines.add(55);
        customLines.add(55);
        customLines.add(52);
        customLines.add(52);
        customLines.add(55);
        customLines.add(55);
        customLines.add(50);
        customLines.add(50);
        customLines.add(50);
        customLines.add(55);

        demoView.onStartDraw();
    }
}