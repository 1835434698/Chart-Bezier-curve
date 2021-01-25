package com.tangzy.minedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DemoView extends View {

    private Paint mPaintLineH;// 横坐标画笔
    private Paint mPaintLineV;//纵坐标画笔
    private Paint mPaintLineCustom; // 自定义线段画笔
    private Paint mPaintText;//文字画笔　

    private int width;
    private int height;

    private int topLine;//文字大小

    private Path mPath;

    private float MaxY = 0 ;
    private float MinY = 0;

    private ArrayList<float[]> lines = new ArrayList<>();

    private ArrayList<TextLocation> textsOnY = new ArrayList<>();
    private ArrayList<TextLocation> textsOnXTop = new ArrayList<>();
    private ArrayList<TextLocation> textsOnXBottom = new ArrayList<>();
    private ArrayList<Integer> customLines = new ArrayList<>();

    public DemoView(Context context) {
        super(context);
        initView();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        topLine = 25;

        mPaintLineH = new Paint();
        mPaintLineH.setAntiAlias(true);
        mPaintLineH.setColor(R.color.color_EDEDED);
        mPaintLineH.setStrokeWidth(2);
        mPaintLineH.setStyle(Paint.Style.STROKE);
        mPaintLineH.setStrokeCap(Paint.Cap.ROUND);
        mPaintLineH.setStrokeJoin(Paint.Join.BEVEL);

        mPaintLineV = new Paint();
        mPaintLineV.setAntiAlias(true);
        mPaintLineV.setColor(R.color.color_666666);
        mPaintLineV.setStrokeWidth(2);
        mPaintLineV.setStyle(Paint.Style.STROKE);
        mPaintLineV.setPathEffect(new DashPathEffect(new float[]{12, 6}, 0));
        mPaintLineV.setStrokeCap(Paint.Cap.ROUND);
        mPaintLineV.setStrokeJoin(Paint.Join.BEVEL);

        mPaintLineCustom = new Paint();
        mPaintLineCustom.setAntiAlias(true);
//        mPaintLineCustom.setColor(R.color.color_FF0000);
        mPaintLineCustom.setColor(Color.RED);
        mPaintLineCustom.setStrokeWidth(6);
//        mPaintLineCustom.setStyle(Paint.Style.STROKE);
//        mPaintLineCustom.setStrokeCap(Paint.Cap.ROUND);
//        mPaintLineCustom.setStrokeJoin(Paint.Join.BEVEL);

        mPaintText = new Paint();
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setStrokeWidth(1f);
        mPaintText.setTextSize(topLine); // TODO: 22.1.21 转px
        mPaintText.setColor(R.color.color_666666);
        mPaintText.setTypeface(Typeface.DEFAULT_BOLD);
        mPaintText.setAntiAlias(true);

        mPath = new Path();
    }

    public void setDataListY(ArrayList<TextLocation> textsOnY){
        this.textsOnY = textsOnY;
    }

    public float getMaxY() {
        return MaxY;
    }

    public void setMaxY(float maxY) {
        MaxY = maxY;
    }

    public float getMinY() {
        return MinY;
    }

    public void setMinY(float minY) {
        MinY = minY;
    }

    public ArrayList<TextLocation> getTextsOnY() {
        return textsOnY;
    }

    public void setTextsOnY(ArrayList<TextLocation> textsOnY) {
        this.textsOnY = textsOnY;
    }

    public ArrayList<TextLocation> getTextsOnXTop() {
        return textsOnXTop;
    }

    public void setTextsOnXTop(ArrayList<TextLocation> textsOnXTop) {
        this.textsOnXTop = textsOnXTop;
    }

    public ArrayList<TextLocation> getTextsOnXBottom() {
        return textsOnXBottom;
    }

    public void setTextsOnXBottom(ArrayList<TextLocation> textsOnXBottom) {
        this.textsOnXBottom = textsOnXBottom;
    }

    public ArrayList<Integer> getCustomLines() {
        return customLines;
    }

    public void setCustomLines(ArrayList<Integer> customLines) {
        this.customLines = customLines;
    }
    public void onStartDraw(){
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        Log.d("tangzy", "width = "+width+", height = "+height);


        if (textsOnXTop.size() > 0){
            for (int i = textsOnXTop.size()-1; i >= 0; i--){
                if (textsOnXTop.get(i).getScale() >= 0){
                    float v = textsOnXTop.get(i).getScale() * (width - 3f *  topLine);
                    float x = v + 1.6f * topLine;
                    canvas.drawText(textsOnXTop.get(i).getName(), x, topLine ,mPaintText);

                    canvas.drawLine(x+0.4f*topLine, 1.75f*topLine, x+0.5f*topLine, height-1.75f*topLine, mPaintLineV);
                }
            }
        }
        if (textsOnXBottom.size() > 0){
            for (int i = textsOnXBottom.size()-1; i >= 0; i--){
                if (textsOnXBottom.get(i).getScale() >= 0){
                    float v = textsOnXBottom.get(i).getScale() * (width - 3f * topLine);
                    canvas.drawText(textsOnXBottom.get(i).getName(), v+1f*topLine, height ,mPaintText);
                }
            }
        }
        if (textsOnY.size() > 0){
            for (int i = textsOnY.size()-1; i >= 0; i--){
                if (textsOnY.get(i).getScale() >= 0){
                    float v = textsOnY.get(i).getScale() * (height - 3.5f * topLine);
                    float y = v + 2.2f * topLine;
                    canvas.drawText(textsOnY.get(i).getName(), 0 , y,mPaintText);
                    canvas.drawLine(2f * topLine, y-topLine/2, width-topLine, y-topLine/2, mPaintLineH);
                }
            }
        }

        int size = customLines.size();

        if (size > 0){
//            drawLines(canvas, size);
            drawBezierLine(canvas, size);
        }



    }

    private void drawLines(Canvas canvas, int size) {
        float widthView = (width - 3f * topLine);
        float vW = widthView / (size - 1);
        float x = vW * 0 + 2f * topLine;

        float H = height - 3.5f * topLine;
        float y = H * (MaxY - customLines.get(0))/(MaxY - MinY) + 1.7f * topLine;

        mPath.reset();
        mPath.moveTo((int) x, y);
        for(int j = 0; j < size; j++) {
            y = H * (MaxY - customLines.get(j))/(MaxY - MinY) + 1.7f * topLine;
            mPath.lineTo((int) (vW*j + 2f * topLine), y);
            canvas.drawPath(mPath, mPaintLineCustom);
        }
    }

    private void drawBezierLine(Canvas canvas, int size) {
        float lineStartX = 0f,lineStartY = 0f;
        float lineStopX = 0.0f,lineStopY = 0.0f;
        float widthView = (width - 3f * topLine);
        float vW = widthView / (size - 1);

        float H = height - 3.5f * topLine;
        mLstPoints.clear();
        for(int j = 0; j < size; j++) {
            lineStopX = vW * j + 2f * topLine;
            lineStopY = H * (MaxY - customLines.get(j))/(MaxY - MinY) + 1.7f * topLine;
            if(0 == j ) {
                lineStartX = lineStopX;
                lineStartY = lineStopY;
                //line
                mLstPoints.add( new PointF(lineStartX,lineStartY));
            }
            mLstPoints.add( new PointF(lineStopX,lineStopY));
        }
        renderBezierCurveLine(canvas, mPaintLineCustom, mPath, mLstPoints);
    }

    //平滑曲线
    private List<PointF> mLstPoints = new ArrayList<PointF>();

    private PointF[] BezierControls;

    // 遍历曲线
    protected void renderBezierCurveLine(Canvas canvas, Paint paint,
                                         Path bezierPath, List<PointF> lstPoints) {
        if (null == BezierControls)
            BezierControls = new PointF[2];
        paint.setStyle(Paint.Style.STROKE);

        int count = lstPoints.size();
        for ( int i = 0; i< count; i++) {
            Log.d("tangzy", " item ["+i+"] x = "+lstPoints.get(i).x + ", y = "+lstPoints.get(i).y);
        }

        if (count <= 2)
            return; // 没有或仅一个点就不需要了

        if (count == 3) {
            if (null == bezierPath)
                bezierPath = new Path();
            bezierPath.moveTo(lstPoints.get(0).x, lstPoints.get(0).y);

            PointF ctl3 = PointHelper.percent(lstPoints.get(1), 0.5f, lstPoints.get(2), 0.8f);

            bezierPath.quadTo(ctl3.x, ctl3.y, lstPoints.get(2).x, lstPoints.get(2).y);

            canvas.drawPath(bezierPath, paint);
            bezierPath.reset();
            return;
        }

        float axisMinValue = getBottom();
        for (int i = 0; i < count; i++) {
            if (i < 3)
                continue;

            // 连续两个值都为0,控制点有可能会显示在轴以下，则此种情况下，将其处理为直线
            if (lstPoints.get(i - 1).y >= axisMinValue && lstPoints.get(i).y >= axisMinValue) {

                if (null == bezierPath){
                    bezierPath = new Path();
                }
                bezierPath.reset();
                bezierPath.moveTo(lstPoints.get(i - 2).x, lstPoints.get(i - 2).y);

                // change by chenqiang
                if (lstPoints.get(i - 2).y>=axisMinValue) {//连续3个点为0
                    bezierPath.lineTo(lstPoints.get(i - 1).x, lstPoints.get(i - 1).y);
                }else{
                    CurveHelper.curve3(lstPoints.get(i - 2), lstPoints.get(i - 1), lstPoints.get(i - 3), lstPoints.get(i), BezierControls);
                    bezierPath.quadTo(BezierControls[0].x, BezierControls[0].y, lstPoints.get(i - 1).x, lstPoints.get(i - 1).y);

                    // i-2与i-1之间的曲线
                    canvas.drawPath(bezierPath, paint);
                    bezierPath.reset();
                }

                // i-1与i之间的直线
                canvas.drawLine(lstPoints.get(i - 1).x, lstPoints.get(i - 1).y, lstPoints.get(i).x, lstPoints.get(i).y, paint);

                continue;
            }

            CurveHelper.curve3(lstPoints.get(i - 2), lstPoints.get(i - 1), lstPoints.get(i - 3), lstPoints.get(i), BezierControls);

            // change by chenqiang
            renderBezierCurvePath(canvas, paint, bezierPath, lstPoints.get(i - 2), lstPoints.get(i - 1), BezierControls);

        }

        if (count > 3) {
            PointF stop = lstPoints.get(count - 1);
            // PointF start = lstPoints.get(lstPoints.size()-2);
            CurveHelper.curve3(lstPoints.get(count - 2), stop, lstPoints.get(count - 3), stop, BezierControls);

            renderBezierCurvePath(canvas, paint, bezierPath, lstPoints.get(count - 2), lstPoints.get(count - 1), BezierControls);
        }

    }

    // 绘制曲线
    private void renderBezierCurvePath(Canvas canvas, Paint paint,
                                       Path bezierPath, PointF start, PointF stop, PointF[] bezierControls) {
        if (null == bezierPath)
            bezierPath = new Path();
        bezierPath.reset();
        Log.d("tangzy", " start x = "+start.x + ", y = "+start.y);
        bezierPath.moveTo(start.x, start.y);

        // change by chenqiang
        bezierCurvePathAxisMinValue(bezierPath, start,stop, bezierControls);

        canvas.drawPath(bezierPath, paint);
        bezierPath.reset();
    }


    // add by chenqiang
    protected void bezierCurvePathAxisMinValue(Path bezierPath, PointF start,
                                               PointF stop, PointF[] bezierControls) {
        float axisMinValue = getBottom();
        if (start.y >= axisMinValue && stop.y >= axisMinValue) {
            bezierPath.lineTo(stop.x, stop.y);
        } else {
            if (bezierControls[0].y >= axisMinValue && bezierControls[1].y >= axisMinValue) {
                bezierPath.lineTo(stop.x, stop.y);
            } else if (bezierControls[0].y >= axisMinValue && bezierControls[1].y < axisMinValue) {
                bezierPath.cubicTo(bezierControls[0].x, axisMinValue, bezierControls[1].x, bezierControls[1].y, stop.x, stop.y);
            } else if (bezierControls[0].y < axisMinValue && bezierControls[1].y >= axisMinValue) {
                bezierPath.cubicTo(bezierControls[0].x, bezierControls[0].y, bezierControls[1].x, axisMinValue, stop.x, stop.y);
            } else {
                bezierPath.cubicTo(bezierControls[0].x, bezierControls[0].y, bezierControls[1].x, bezierControls[1].y, stop.x, stop.y);
            }
        }
    }
}
