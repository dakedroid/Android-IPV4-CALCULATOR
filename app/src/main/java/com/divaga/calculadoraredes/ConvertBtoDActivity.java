package com.divaga.calculadoraredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertBtoDActivity extends AppCompatActivity {

    EditText edtxBinario;
    EditText edtxBinario2;
    EditText edtxBinario3;
    EditText edtxBinario4;
    Button BtnConvert;
    Button btnLimpiar;
    TextView TxvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_bto_d);
        initUi();
    }

    public void initUi(){
        edtxBinario = (EditText) findViewById(R.id.edtx_numero_binario);
        edtxBinario2 = (EditText) findViewById(R.id.edtx_binario_2);
        edtxBinario3 = (EditText) findViewById(R.id.edtx_binario_3);
        edtxBinario4 = (EditText) findViewById(R.id.edtx_binario_4);
        BtnConvert = (Button) findViewById(R.id.btn_convertir);
        btnLimpiar = (Button) findViewById(R.id.btn_limpiar);
        TxvResultado = (TextView) findViewById(R.id.txv_resultado);

        BtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtxBinario.getText().toString().equals("") &&
                        !edtxBinario2.getText().toString().equals("") &&
                        !edtxBinario3.getText().toString().equals("") &&
                        !edtxBinario4.getText().toString().equals("")) {

                    if(valid()){
                        int decimalValue = Integer.parseInt(edtxBinario.getText().toString(), 2);
                        int decimalValue2 = Integer.parseInt(edtxBinario2.getText().toString(), 2);
                        int decimalValue3 = Integer.parseInt(edtxBinario3.getText().toString(), 2);
                        int decimalValue4 = Integer.parseInt(edtxBinario4.getText().toString(), 2);
                        String result = convertToString(decimalValue) + "." +
                                convertToString(decimalValue2) + "." +
                                convertToString(decimalValue3) + "." +
                                convertToString(decimalValue4);
                        TxvResultado.setText(result);
                    }

                }else {
                    Toast.makeText(ConvertBtoDActivity.this, "Falto llenar algunos campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    public boolean valid(){
        if (edtxBinario.getText().toString().length()!=8){
            sendToast("El primer octeto esta mal");
            edtxBinario.setText("");
            return false;
        }
        if (edtxBinario2.getText().toString().length()!=8){
            sendToast("El segundo octeto esta mal");
            edtxBinario2.setText("");
            return false;
        }
        if (edtxBinario3.getText().toString().length()!=8){
            sendToast("El tercer octeto esta mal");
            edtxBinario3.setText("");
            return false;
        }
        if (edtxBinario4.getText().toString().length()!=8){
            sendToast("El cuarto octeto esta mal");
            edtxBinario4.setText("");
            return false;
        }

        return true;
    }

    public void sendToast(String mensaje){
        Toast.makeText(ConvertBtoDActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void clearFields(){
        edtxBinario.setText("");
        edtxBinario2.setText("");
        edtxBinario3.setText("");
        edtxBinario4.setText("");
        TxvResultado.setText("");
    }

    public String convertToString(int decimal){
        return String.valueOf(decimal);
    }
}
