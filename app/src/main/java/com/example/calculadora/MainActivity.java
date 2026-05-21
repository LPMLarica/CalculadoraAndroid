package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView visor;

    double valor1 = 0;
    double valor2 = 0;

    String operacao = "";

    boolean novoNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.visor);

        configurarNumeros();
        configurarOperacoes();
    }

    private void configurarNumeros() {

        int[] botoes = {
                R.id.btn0, R.id.btn1, R.id.btn2,
                R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8,
                R.id.btn9
        };

        View.OnClickListener listener = v -> {

            Button b = (Button) v;

            if (novoNumero) {
                visor.setText(b.getText());
                novoNumero = false;
            } else {
                visor.append(b.getText());
            }
        };

        for (int id : botoes) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void configurarOperacoes() {

        findViewById(R.id.btnMais)
                .setOnClickListener(v -> selecionarOperacao("+"));

        findViewById(R.id.btnMenos)
                .setOnClickListener(v -> selecionarOperacao("-"));

        findViewById(R.id.btnMultiplicar)
                .setOnClickListener(v -> selecionarOperacao("*"));

        findViewById(R.id.btnDividir)
                .setOnClickListener(v -> selecionarOperacao("/"));

        findViewById(R.id.btnIgual)
                .setOnClickListener(v -> calcular());

        findViewById(R.id.btnLimpar)
                .setOnClickListener(v -> limpar());

        findViewById(R.id.btnRaiz)
                .setOnClickListener(v -> raizQuadrada());

        findViewById(R.id.btnQuadrado)
                .setOnClickListener(v -> elevarQuadrado());

        findViewById(R.id.btnPorcentagem)
                .setOnClickListener(v -> porcentagem());
    }

    private void selecionarOperacao(String op) {

        valor1 = Double.parseDouble(visor.getText().toString());

        operacao = op;

        novoNumero = true;
    }

    private void calcular() {

        valor2 = Double.parseDouble(visor.getText().toString());

        double resultado = 0;

        switch (operacao) {

            case "+":
                resultado = valor1 + valor2;
                break;

            case "-":
                resultado = valor1 - valor2;
                break;

            case "*":
                resultado = valor1 * valor2;
                break;

            case "/":

                if (valor2 == 0) {
                    visor.setText("Erro");
                    return;
                }

                resultado = valor1 / valor2;
                break;
        }

        visor.setText(String.valueOf(resultado));

        novoNumero = true;
    }

    private void limpar() {

        visor.setText("0");

        valor1 = 0;
        valor2 = 0;

        operacao = "";

        novoNumero = true;
    }

    private void raizQuadrada() {

        double numero = Double.parseDouble(visor.getText().toString());

        visor.setText(String.valueOf(Math.sqrt(numero)));

        novoNumero = true;
    }

    private void elevarQuadrado() {

        double numero = Double.parseDouble(visor.getText().toString());

        visor.setText(String.valueOf(numero * numero));

        novoNumero = true;
    }

    private void porcentagem() {

        double numero = Double.parseDouble(visor.getText().toString());

        visor.setText(String.valueOf(numero / 100));

        novoNumero = true;
    }
}