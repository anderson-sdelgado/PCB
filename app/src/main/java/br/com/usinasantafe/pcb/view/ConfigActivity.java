package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pcb.BuildConfig;
import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextNroAparelhoConfig;
    private EditText editTextSenhaConfig;
    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        Button buttonSalvarConfig =  findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        editTextNroAparelhoConfig = findViewById(R.id.editTextNroAparelhoConfig);
        editTextSenhaConfig = findViewById(R.id.editTextSenhaConfig);

        pcbContext = (PCBContext) getApplication();

        if (!pcbContext.getConfigCTR().getConfig().getSenhaConfig().equals("")) {
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getConfigCTR().hasElemConfig()) {\n" +
                    "            editTextLinhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getNroAparelhoConfig()));\n" +
                    "editTextSenhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getSenhaConfig()));", getLocalClassName());
            editTextNroAparelhoConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
            editTextSenhaConfig.setText(String.valueOf(pcbContext.getConfigCTR().getConfig().getSenhaConfig()));
        }

        buttonAtualizarBD.setOnClickListener(v -> {
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

            } else {
                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));

                alerta.show();
            }
        });

        buttonSalvarConfig.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if(!editTextNroAparelhoConfig.getText().toString().equals("")
                && !editTextSenhaConfig.getText().toString().equals("")) {

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Salvando dados inicial...");
                progressBar.show();
                pcbContext.getConfigCTR().salvarToken(editTextSenhaConfig.getText().toString(), BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, MenuInicialActivity.class, progressBar, getLocalClassName());

            } else {

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
                alerta.setPositiveButton("OK", (dialog, which) -> {});
                alerta.show();

            }

        });

        buttonCancConfig.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancConfig.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(ConfigActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed()  {
    }
}