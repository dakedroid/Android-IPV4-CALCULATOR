package com.divaga.calculadoraredes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConvertDtoBActivity extends AppCompatActivity {

    EditText edtxDecimal;
    EditText edtxDecimal2;
    EditText edtxDecimal3;
    EditText edtxDecimal4;
    Button BtnConvert;
    Button btnLimpiar;
    TextView TxvResultado;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_dto_b);
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
                        !edtxDecimal4.getText().toString().equals("")){
                    if (valid()) {
                        StringBuffer octeto1 =  calBinary(Integer.parseInt(edtxDecimal.getText().toString()));
                        StringBuffer octeto2 =  calBinary(Integer.parseInt(edtxDecimal2.getText().toString()));
                        StringBuffer octeto3 =  calBinary(Integer.parseInt(edtxDecimal3.getText().toString()));
                        StringBuffer octeto4 =  calBinary(Integer.parseInt(edtxDecimal4.getText().toString()));

                        String result = addCeros(octeto1) + "." + addCeros(octeto2) + "." + addCeros(octeto3) + "." + addCeros(octeto4);

                        TxvResultado.setText(result);
                    }

                }else {
                    Toast.makeText(ConvertDtoBActivity.this, "Falta llenar algunos campos", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(ConvertDtoBActivity.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void clearFields(){
        edtxDecimal.setText("");
        edtxDecimal2.setText("");
        edtxDecimal3.setText("");
        edtxDecimal4.setText("");
        TxvResultado.setText("");
    }

    public String addCeros(StringBuffer octeto){

        String k = "";

        for(int i = 8 - octeto.toString().length(); i > 0; i--){
            k+= "0";
        }

        return k + octeto.toString();
    }

    public StringBuffer calBinary(int decnum){

        StringBuffer sBuf = new StringBuffer();
        int temp = 0;
        while(decnum>0){
            temp = decnum%2;
            sBuf.append(temp);
            decnum = decnum / 2;
        }
        return sBuf.reverse();
    }

}
