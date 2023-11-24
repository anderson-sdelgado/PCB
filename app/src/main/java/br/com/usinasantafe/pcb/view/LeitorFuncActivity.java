package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class LeitorFuncActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private TextView txtRetColab;
    private ProgressDialog progressBar;
    private FuncBean funcBean;
    private boolean statusLeitor;

    private static final String EXTRA_CONTROL = "com.honeywell.aidc.action.ACTION_CONTROL_SCANNER";
    private static final String EXTRA_SCAN = "com.honeywell.aidc.extra.EXTRA_SCAN";
    public static final String ACTION_BARCODE_DATA = "com.honeywell.sample.intentapisample.BARCODE";
    public static final String ACTION_CLAIM_SCANNER = "com.honeywell.aidc.action.ACTION_CLAIM_SCANNER";
    public static final String ACTION_RELEASE_SCANNER = "com.honeywell.aidc.action.ACTION_RELEASE_SCANNER";
    public static final String EXTRA_SCANNER = "com.honeywell.aidc.extra.EXTRA_SCANNER";
    public static final String EXTRA_PROFILE = "com.honeywell.aidc.extra.EXTRA_PROFILE";
    public static final String EXTRA_PROPERTIES = "com.honeywell.aidc.extra.EXTRA_PROPERTIES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_func);

        pcbContext = (PCBContext) getApplication();

        txtRetColab = findViewById(R.id.txtRetColab);
        Button buttonOkColab = findViewById(R.id.buttonOkColab);
        Button buttonCancColab = findViewById(R.id.buttonCancColab);
        Button buttonDigColab = findViewById(R.id.buttonDigColab);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptColab = findViewById(R.id.buttonCaptColab);

        statusLeitor = true;
        funcBean = new FuncBean();
        funcBean.setMatricFunc(0L);
        funcBean.setNomeFunc("");

        txtRetColab.setText("POR FAVOR, REALIZE A LEITURA DO CRACHÁ DO COLABORADOR.");

        buttonOkColab.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkColab.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());

            if (funcBean.getMatricFunc() > 0) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (funcBean.getMatricFunc() > 0) {\n" +
                        "                    pcbContext.setMatricFunc(funcBean.getMatricFunc());\n" +
                        "                    Intent it = new Intent(LeitorFuncActivity.this, ListaTipoApontActivity.class);", getLocalClassName());
                pcbContext.setMatricFunc(funcBean.getMatricFunc());
                Intent it = new Intent(LeitorFuncActivity.this, ListaTipoApontActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancColab.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("        buttonCancColab.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(LeitorFuncActivity.this, TelaInicialActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorFuncActivity.this, TelaInicialActivity.class);
            startActivity(it);
            finish();
        });

        buttonDigColab.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("buttonDigColab.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);", getLocalClassName());
            Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);
            startActivity(it);
            finish();

        });

        buttonCaptColab.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCaptColab.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                sendBroadcast(new Intent(EXTRA_CONTROL)\n" +
                    "                        .setPackage(\"com.intermec.datacollectionservice\")\n" +
                    "                        .putExtra(EXTRA_SCAN, true)\n" +
                    "                );\n" +
                    "                statusLeitor = statusLeitor ? false : true;", getLocalClassName());
            sendBroadcast(new Intent(EXTRA_CONTROL)
                    .setPackage("com.intermec.datacollectionservice")
                    .putExtra(EXTRA_SCAN, statusLeitor)
            );
            statusLeitor = statusLeitor ? false : true;
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                    "                alerta.setTitle(\"ATENÇÃO\");\n" +
                    "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
            AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorFuncActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                        "                    @Override\n" +
                        "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                if (connectNetwork) {

                    LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                            "progressBar = new ProgressDialog(OperadorActivity.this);\n" +
                            "                            progressBar.setCancelable(true);\n" +
                            "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                            "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                            "                            progressBar.setProgress(0);\n" +
                            "                            progressBar.setMax(100);\n" +
                            "                            progressBar.show();", getLocalClassName());
                    progressBar = new ProgressDialog(LeitorFuncActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                    pcbContext.getConfigCTR().atualDados(LeitorFuncActivity.this, LeitorFuncActivity.class, progressBar, "Func", 1, getLocalClassName());

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                            "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                                @Override\n" +
                            "                                public void onClick(DialogInterface dialog, int which) {\n" +
                            "                                }\n" +
                            "                            });\n" +
                            "                            alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( LeitorFuncActivity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                    "                    @Override\n" +
                    "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName()));
            alerta.show();

        });

    }

    public void onBackPressed() {
    }

    private BroadcastReceiver barcodeDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogProcessoDAO.getInstance().insertLogProcesso("private BroadcastReceiver barcodeDataReceiver = new BroadcastReceiver() {\n" +
                    "        @Override\n" +
                    "        public void onReceive(Context context, Intent intent) {\n" +
                    "            String action = intent.getAction();", getLocalClassName());
            String action = intent.getAction();
            if (ACTION_BARCODE_DATA.equals(action)) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (ACTION_BARCODE_DATA.equals(action)) {\n" +
                        "                int version = intent.getIntExtra(\"version\", 0);", getLocalClassName());
                int version = intent.getIntExtra("version", 0);
                if (version >= 1) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (version >= 1) {\n" +
                            "                    String matricula = intent.getStringExtra(\"data\");", getLocalClassName());
                    String matricula = intent.getStringExtra("data");
                    if (matricula.length() == 8) {
                        LogProcessoDAO.getInstance().insertLogProcesso("            if (matricula.length() == 8) {\n" +
                                "                matricula = matricula.substring(0, 7);", getLocalClassName());
                        matricula = matricula.substring(0, 7);
                        if (pcbContext.getConfigCTR().verFunc(Long.parseLong(matricula))) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCargaCTR().verFunc(Long.parseLong(matricula))) {\n" +
                                    "                    funcBean = pcbContext.getCargaCTR().getFuncMatric(Long.parseLong(matricula));\n" +
                                    "                    txtRetColab.setText(matricula + \"\\n\" + funcBean.getNomeFunc());", getLocalClassName());
                            funcBean = pcbContext.getConfigCTR().getFuncMatric(Long.parseLong(matricula));
                            txtRetColab.setText(matricula + "\n" + funcBean.getNomeFunc());
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "                    txtRetColab.setText(\"Funcionário Inexistente\");", getLocalClassName());
                            txtRetColab.setText("Funcionário Inexistente");
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onResume() {
        LogProcessoDAO.getInstance().insertLogProcesso("protected void onResume() {\n" +
                "        super.onResume();\n" +
                "        IntentFilter intentFilter = new IntentFilter(ACTION_BARCODE_DATA);\n" +
                "        registerReceiver(barcodeDataReceiver, intentFilter);\n" +
                "        claimScanner();", getLocalClassName());
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ACTION_BARCODE_DATA);
        registerReceiver(barcodeDataReceiver, intentFilter);
        claimScanner();
    }

    private void claimScanner() {
        LogProcessoDAO.getInstance().insertLogProcesso("    private void claimScanner() {\n" +
                "        Bundle properties = new Bundle();\n" +
                "        properties.putBoolean(\"DPR_DATA_INTENT\", true);\n" +
                "        properties.putString(\"DPR_DATA_INTENT_ACTION\", ACTION_BARCODE_DATA);\n" +
                "        Intent intent = new Intent();\n" +
                "        intent.setAction(ACTION_CLAIM_SCANNER);\n" +
                "        intent.setPackage(\"com.intermec.datacollectionservice\");\n" +
                "        intent.putExtra(EXTRA_SCANNER, \"dcs.scanner.imager\");\n" +
                "        intent.putExtra(EXTRA_PROFILE, \"MyProfile1\");\n" +
                "        intent.putExtra(EXTRA_PROPERTIES, properties);\n" +
                "        sendBroadcast(intent);", getLocalClassName());
        Bundle properties = new Bundle();
        properties.putBoolean("DPR_DATA_INTENT", true);
        properties.putString("DPR_DATA_INTENT_ACTION", ACTION_BARCODE_DATA);
        Intent intent = new Intent();
        intent.setAction(ACTION_CLAIM_SCANNER);
        intent.setPackage("com.intermec.datacollectionservice");
        intent.putExtra(EXTRA_SCANNER, "dcs.scanner.imager");
        intent.putExtra(EXTRA_PROFILE, "MyProfile1");
        intent.putExtra(EXTRA_PROPERTIES, properties);
        sendBroadcast(intent);
    }

    @Override
    protected void onPause() {
        LogProcessoDAO.getInstance().insertLogProcesso("protected void onPause() {\n" +
                "        super.onPause();\n" +
                "        unregisterReceiver(barcodeDataReceiver);\n" +
                "        releaseScanner();", getLocalClassName());
        super.onPause();
        unregisterReceiver(barcodeDataReceiver);
        releaseScanner();
    }

    private void releaseScanner() {
        LogProcessoDAO.getInstance().insertLogProcesso("private void releaseScanner() {\n" +
                "        Intent intent = new Intent();\n" +
                "        intent.setAction(ACTION_RELEASE_SCANNER);\n" +
                "        sendBroadcast(intent);", getLocalClassName());
        Intent intent = new Intent();
        intent.setAction(ACTION_RELEASE_SCANNER);
        sendBroadcast(intent);
    }

}