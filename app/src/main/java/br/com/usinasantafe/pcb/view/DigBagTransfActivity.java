package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class DigBagTransfActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig_bag_transf);

        pcbContext = (PCBContext) getApplication();

        Button buttonOkBag = findViewById(R.id.buttonOkPadrao);
        Button buttonCancBag = findViewById(R.id.buttonCancPadrao);

        buttonOkBag.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkMotorista.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @SuppressWarnings(\"rawtypes\")\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if (!editTextPadrao.getText().toString().equals("")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!editTextPadrao.getText().toString().equals(\"\")) {", getLocalClassName());
                    if(pcbContext.getTransfCTR().verBagRepetidoTransf(Long.valueOf(editTextPadrao.getText().toString()))) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getTransfCTR().verBagRepetidoTransf(Long.valueOf(editTextPadrao.getText().toString()))) {\n" +
                                "                        progressBar = new ProgressDialog(DigBagTransfActivity.this);\n" +
                                "                        progressBar.setCancelable(true);\n" +
                                "                        progressBar.setMessage(\"PESQUISANDO BAG...\");\n" +
                                "                        progressBar.show();\n" +
                                "                    pcbContext.getTransfCTR().verBag(" + editTextPadrao.getText().toString() + ", ListaBagTransfActivity.this, ListaBagTransfActivity.class, progressBar, getLocalClassName());", getLocalClassName());
                        progressBar = new ProgressDialog(DigBagTransfActivity.this);
                        progressBar.setCancelable(true);
                        progressBar.setMessage("PESQUISANDO BAG...");
                        progressBar.show();
                        pcbContext.getTransfCTR().verBagTransf(editTextPadrao.getText().toString(), DigBagTransfActivity.this, ListaBagTransfActivity.class, progressBar, getLocalClassName());

                    }
                    else{
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(DigBagTransfActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"BAG REPETIDO! POR FAVOR VERIFIQUE A NUMERAÇÃO DO BAG O DIGITADO.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(DigBagTransfActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("BAG REPETIDO! POR FAVOR VERIFIQUE A NUMERAÇÃO DO BAG O DIGITADO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();
                    }
                }

            }

        });

        buttonCancBag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancBag.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "if (editTextPadrao.getText().toString().length() > 0) {\n" +
                        "                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));\n" +
                        "                }", getLocalClassName());
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }


    public void onBackPressed() {
        LogProcessoDAO.getInstance().insertLogProcesso("public void onBackPressed() {\n" +
                "Intent it = new Intent(DigBagTransfActivity.this, ListaBagTransfActivity.class);", getLocalClassName());
        Intent it = new Intent(DigBagTransfActivity.this, ListaBagTransfActivity.class);
        startActivity(it);
        finish();
    }

}