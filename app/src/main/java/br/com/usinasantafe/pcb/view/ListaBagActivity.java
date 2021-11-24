package br.com.usinasantafe.pcb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.zxing.CaptureActivity;

public class ListaBagActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bag);

        Button buttonLeituraBag = (Button) findViewById(R.id.buttonLeituraBag);
        Button buttonDigBag = (Button) findViewById(R.id.buttonDigBag);
        Button buttonFinalizarCarga = (Button) findViewById(R.id.buttonFinalizarCarga);

        buttonLeituraBag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaBagActivity.this, CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonDigBag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaBagActivity.this, DigBagActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonFinalizarCarga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaBagActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricula = data.getStringExtra("SCAN_RESULT");
            Intent it = new Intent(ListaBagActivity.this, MsgAddBagActivity.class);
            startActivity(it);
            finish();
        }

    }

}