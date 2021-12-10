package br.com.usinasantafe.pcb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.AtualDadosServ;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextLinhaConfig;
    private EditText editTextSenhaConfig;
    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);
        Button buttonSalvarConfig =  (Button) findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        editTextLinhaConfig = (EditText) findViewById(R.id.editTextLinhaConfig);
        editTextSenhaConfig = (EditText) findViewById(R.id.editTextSenhaConfig);

        pcbContext = (PCBContext) getApplication();

        if (!pcbContext.getConfigCTR().getConfig().getSenhaConfig().equals("")) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getConfigCTR().hasElemConfig()) {\n" +
                    "            editTextLinhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getNroAparelhoConfig()));\n" +
                    "editTextSenhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getSenhaConfig()));", getLocalClassName());
            editTextLinhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
            editTextSenhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getSenhaConfig()));
        }

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "                    progressBar = new ProgressDialog(v.getContext());\n" +
                            "                    progressBar.setCancelable(true);\n" +
                            "                    progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                    progressBar.setProgress(0);\n" +
                            "                    progressBar.setMax(100);\n" +
                            "                    progressBar.show();", getLocalClassName());

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar);", getLocalClassName());
                    pcbContext.getConfigCTR().atualTodasTabelas(ConfigActivity.this, progressBar, getLocalClassName());
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                    "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                                    "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                        }
                    });

                    alerta.show();
                }
            }
        });

        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(!editTextLinhaConfig.getText().toString().equals("")
                    && !editTextSenhaConfig.getText().toString().equals("")) {
                    pcbContext.getConfigCTR().salvarConfig(Long.parseLong(editTextLinhaConfig.getText().toString()), editTextSenhaConfig.getText().toString());
                    LogProcessoDAO.getInstance().insertLogProcesso("if(!editTextLinhaConfig.getText().toString().equals(\"\")\n" +
                            "                    && !editTextSenhaConfig.getText().toString().equals(\"\")) {\n" +
                            "                    pcbContext.getConfigCTR().salvarConfig(Long.parseLong(editTextLinhaConfig.getText().toString()), editTextLinhaConfig.getText().toString());\n" +
                            "                    Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
                    Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                    startActivity(it);
                    finish();

                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"POR FAVOR! DIGITE O NUMERO DA LINHA.\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                        }\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed()  {
    }
}