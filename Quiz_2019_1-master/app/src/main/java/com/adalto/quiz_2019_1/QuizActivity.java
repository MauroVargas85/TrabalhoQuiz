package com.adalto.quiz_2019_1;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private TextView tvAfirmacao;
    private ImageView ivEsquerda, ivDireita;

    private String tipo;
    private String[] afirmacoes;
    private boolean[] gabarito;
    private int contadorRespostas=  1;

    private LinearLayout layout;

    private int contador, acertos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        afirmacoes = new String[] {
                " A metade de 2 + 2 é 2",
                "Pelé é o rei do futebol",
                "Maradona é ruim",
                "O Brasil é Hexa",
                "Capão da Canoa é a melhor praia do RS "

        };

        gabarito = new boolean[]{  false, true, true, false, true };

        tipo = getIntent().getExtras().getString("tipo");
        contador = 0;

        layout = (LinearLayout) findViewById(R.id.layout);
        tvAfirmacao = (TextView) findViewById(R.id.tvAfirmacao);
        ivDireita = (ImageView) findViewById(R.id.ivDireita);
        ivEsquerda = (ImageView) findViewById(R.id.ivEsquerda);

        if (tipo.equals("vertical")){
            ivEsquerda.setImageResource(R.drawable.abaixo);
            ivDireita.setImageResource(R.drawable.acima);
        }

        tvAfirmacao.setText( afirmacoes[contador] );

        layout.setOnTouchListener( new OnSwipeTouchListener(this){

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                contador --;
                tvAfirmacao.setText( afirmacoes[contador]);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(contadorRespostas == afirmacoes.length){
                    tvAfirmacao.setText("Acertou! " + acertos);
                }
                contadorRespostas++;


                contador ++;
                tvAfirmacao.setText( afirmacoes[contador]);
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                if( !gabarito[contador]){
                    acertos ++;
                }
                this.onSwipeLeft();
            }


            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                if(gabarito[contador]){
                    acertos ++;
                }
                this.onSwipeLeft();

            }

        });


    }


}


















