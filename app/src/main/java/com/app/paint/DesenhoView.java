package com.app.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DesenhoView extends View {

    private static class Traco {
        Path caminho;
        int cor;
        float espessura;

        Traco(Path caminho, int cor, float espessura) {
            this.caminho = caminho;
            this.cor = cor;
            this.espessura = espessura;
        }
    }

    private ArrayList<Traco> desenhos = new ArrayList<>();

    private Path caminhoAtual;
    private Paint pincel;

    private int corAtual = Color.BLACK;
    private float espessura = 10f;

    public DesenhoView(Context context,
                       android.util.AttributeSet attrs) {
        super(context, attrs);

        pincel = new Paint();
        pincel.setAntiAlias(true);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeJoin(Paint.Join.ROUND);
        pincel.setStrokeCap(Paint.Cap.ROUND);
        pincel.setStrokeWidth(espessura);

        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Traco traco : desenhos) {

            pincel.setColor(traco.cor);
            pincel.setStrokeWidth(traco.espessura);

            canvas.drawPath(traco.caminho, pincel);
        }

        if (caminhoAtual != null) {
            pincel.setColor(corAtual);
            pincel.setStrokeWidth(espessura);

            canvas.drawPath(caminhoAtual, pincel);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                caminhoAtual = new Path();
                caminhoAtual.moveTo(x, y);
                invalidate();
                return true;

            case MotionEvent.ACTION_MOVE:

                if (caminhoAtual != null) {
                    caminhoAtual.lineTo(x, y);
                    invalidate();
                }
                return true;

            case MotionEvent.ACTION_UP:

                if (caminhoAtual != null) {
                    desenhos.add(
                            new Traco(caminhoAtual, corAtual, espessura)
                    );

                    caminhoAtual = null;
                    invalidate();
                }
                return true;
        }

        return true;
    }

    public void mudarCor(int cor) {
        corAtual = cor;
    }

    public void limpar() {
        desenhos.clear();
        caminhoAtual = null;
        invalidate();
    }
}
