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
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.zxing.CaptureActivity;

public class MsgAddBagCarregActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private TextView textViewMsgBag;
    private ProgressDialog progressBar;

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
        setContentView(R.layout.activity_msg_add_bag_carreg);

        pcbContext = (PCBContext) getApplication();
        textViewMsgBag = findViewById(R.id.textViewMsgBag);
        Button buttonMsgBagNao = findViewById(R.id.buttonMsgBagNao);
        Button buttonMsgBagSim = findViewById(R.id.buttonMsgBagSim);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);

        LogProcessoDAO.getInstance().insertLogProcesso("msgBag();", getLocalClassName());
        msgBag();

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(ListaOrdemCarregActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog(MsgAddBagCarregActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(MsgAddBagCarregActivity.this, MsgAddBagCarregActivity.class, progressBar, \"BagCarreg\", 1, getLocalClassName());", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(MsgAddBagCarregActivity.this, MsgAddBagCarregActivity.class, progressBar, "BagCarreg", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });
                alerta.show();

            }

        });

        buttonMsgBagNao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgBagNao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgAddBagCarregActivity.this, ListaBagCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgAddBagCarregActivity.this, ListaBagCarregActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonMsgBagSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgBagSim.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcbContext.getCarregCTR().qtdeRestItemCarreg() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeRestItemCarreg() > 0){\n" +
                            "                    sendBroadcast(new Intent(EXTRA_CONTROL)\n" +
                            "                            .setPackage(\"com.intermec.datacollectionservice\")\n" +
                            "                            .putExtra(EXTRA_SCAN, true)\n" +
                            "                    );", getLocalClassName());
                    sendBroadcast(new Intent(EXTRA_CONTROL)
                            .setPackage("com.intermec.datacollectionservice")
                            .putExtra(EXTRA_SCAN, true)
                    );
                }
                else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show()", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CARGA COMPLETA! POR FAVOR, FINALIZE A CARREGAMENTO CLICANDO NO BOTÃO 'NÃO'.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }

            }

        });

    }

    public void msgBag(){

        LogProcessoDAO.getInstance().insertLogProcesso("public void msgBag(){\n" +
                "        String msgBag = \"\";", getLocalClassName());
        String msgBag = "";

        if(pcbContext.getCarregCTR().verBagCarregCodBarra(pcbContext.getCodBarraBagLido())){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().verBagCarregCodBarra(pcbContext.getCodBarraBagLido())){\n" +
                    "            pcbContext.getCarregCTR().inserirItemCarreg(pcbContext.getCodBarraBagLido());\n" +
                    "            msgBag = msgBag + \"NUMERAÇÃO DE BAG \" + pcbContext.getCodBarraBagLido();", getLocalClassName());
            pcbContext.getCarregCTR().inserirItemCarreg(pcbContext.getCodBarraBagLido(), getLocalClassName());
            msgBag = msgBag + "NUMERAÇÃO DE EMBALAGEM " + pcbContext.getCodBarraBagLido();
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            msgBag = msgBag + \"NUMERAÇÃO \" + pcbContext.getCodBarraBagLido() + \" DE EMBALAGEM INVÁLIDA!\";", getLocalClassName());
            msgBag = msgBag + "NUMERAÇÃO " + pcbContext.getCodBarraBagLido() + " DE EMBALAGEM INVÁLIDA!";
        }


        if(pcbContext.getCarregCTR().qtdeRestItemCarreg() == 0){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeRestItemCarreg() == 0){\n" +
                    "            msgBag = msgBag + \"\\nCARGA COMPLETA!\" +\n" +
                    "                    \"\\nPOR FAVOR, RETORNE A TELA DE LISTAGEM DE BAG CLICANDO NO BOTÃO 'NÃO'.\";", getLocalClassName());
            msgBag = msgBag + "\nCARGA COMPLETA!" +
                    "\nPOR FAVOR, RETORNE A TELA DE LISTAGEM DE BAG CLICANDO NO BOTÃO 'NÃO'.";
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            msgBag = msgBag + \"\\nQUANTIDADE DE EMBALAGENS A SEREM RELACIONADAS: \" + pcbContext.getCarregCTR().qtdeRestItemCarreg() +\n" +
                    "                    \"\\nDESEJA REALIZAR UMA NOVA LEITURA?\"", getLocalClassName());
            msgBag = msgBag + "\nQUANTIDADE DE EMBALAGENS A SEREM RELACIONADAS: " + pcbContext.getCarregCTR().qtdeRestItemCarreg() +
                    "\nDESEJA REALIZAR UMA NOVA LEITURA?";
        }

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMsgBag.setText(msgBag);", getLocalClassName());
        textViewMsgBag.setText(msgBag);

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
                            "                    String codBarraBag = intent.getStringExtra(\"data\");\n" +
                            "                    pcbContext.setCodBarraBagLido(codBarraBag);\n" +
                            "                    msgBag();", getLocalClassName());
                    String codBarraBag = intent.getStringExtra("data");
                    pcbContext.setCodBarraBagLido(codBarraBag);
                    msgBag();
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
        LogProcessoDAO.getInstance().insertLogProcesso("private void claimScanner() {\n" +
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