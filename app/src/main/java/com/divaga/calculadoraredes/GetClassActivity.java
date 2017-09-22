package com.divaga.calculadoraredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GetClassActivity extends AppCompatActivity {

    EditText edtxDecimal;
    EditText edtxDecimal2;
    EditText edtxDecimal3;
    EditText edtxDecimal4;
    Button BtnConvert;
    Button btnLimpiar;
    TextView TxvResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_class);
        initUi();
    }

    public void initUi(){
        edtxDecimal = (EditText) findViewById(R.id.edtx_numero_decimal);
        edtxDecimal2 = (EditText) findViewById(R.id.edtx_numero_decimal_2);
        edtxDecimal3 = (EditText) findViewById(R.id.edtx_numero_decimal_3);
        edtxDecimal4 = (EditText) findViewById(R.id.edtx_numero_decimal_4);
        BtnConvert = (Button) findViewById(R.id.btn_convertir);
        btnLimpiar = (Button) findViewById(R.id.btn_limpiar);
        TxvResultado = (TextView) findViewById(R.id.txv_resultado);

        BtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edtxDecimal.getText().toString().equals("") &&
                        !edtxDecimal2.getText().toString().equals("") &&
                        !edtxDecimal3.getText().toString().equals("") &&
                        !edtxDecimal4.getText().toString().equals("")) {

                    if (valid()) {

                        int octeto = Integer.parseInt(edtxDecimal.getText().toString());

                        if (octeto >= 0 && octeto <= 127){
                            TxvResultado.setText("La clase es:  A");
                        }else {
                            if (octeto >= 128 && octeto <= 191){
                                TxvResultado.setText("La clase es:  B");
                            }else {
                                if (octeto >= 192 && octeto <= 223){
                                    TxvResultado.setText("La clase es:  C");
                                }else {
                                    Toast.makeText(GetClassActivity.this, "Entrada invalida... ", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                }
                            }
                        }
                    }

                }else {
                    Toast.makeText(GetClassActivity.this, "Falta llenar algunos campos", Toast.LENGTH_SHORT).show();
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
        if (Integer.parseInt(edtxDecimal.getText().toString()) > 255){
            sendToast("El primer octeto esta mal");
            edtxDecimal.setText("");
            return false;
        }
        if (Integer.parseInt(edtxDecimal2.getText().toString()) > 255){
            sendToast("El segundo octeto esta mal");
            edtxDecimal2.setText("");
            return false;
        }
        if (Integer.parseInt(edtxDecimal3.getText().toString()) > 255){
            sendToast("El tercer octeto esta mal");
            edtxDecimal3.setText("");
            return false;
        }
        if (Integer.parseInt(edtxDecimal4.getText().toString()) > 255){
            sendToast("El cuarto octeto esta mal");
            edtxDecimal4.setText("");
            return false;
        }

        return true;
    }

    public void sendToast(String mensaje){
        Toast.makeText(GetClassActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void clearFields(){
        edtxDecimal.setText("");
        edtxDecimal2.setText("");
        edtxDecimal3.setText("");
        edtxDecimal4.setText("");
        TxvResultado.setText("");
    }

}
