package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.EnvioDadosServ;

public class ListaBagTransfActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private TextView textViewProcesso;
    private Handler customHandler = new Handler();
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
        setContentView(R.layout.activity_lista_bag_transf);

        pcbContext = (PCBContext) getApplication();

        TextView textViewTotalBagTransf = findViewById(R.id.textViewTotalBagTransf);
        TextView textViewDthr = findViewById(R.id.textViewDthrTransf);
        TextView textViewSafra = findViewById(R.id.textViewSafra);
        Button buttonLeituraBagTransf = findViewById(R.id.buttonLeituraBagTransf);
        Button buttonDigBagTransf = findViewById(R.id.buttonDigBagTransf);
        Button buttonCancelarTransf = findViewById(R.id.buttonCancelarTransf);
        Button buttonFinalizarTransf = findViewById(R.id.buttonFinalizarTransf);
        Button buttonSafra = findViewById(R.id.buttonSafra);
        textViewProcesso = findViewById(R.id.textViewProcesso);

        LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.setCodBarraBagLido(\"\");\n" +
                "        customHandler.postDelayed(updateTimerThread, 0);\n" +
                "        textViewTotalBagTransf.setText(\"TOTAL DE EMBALAGENS LIDAS: \" + pcbContext.getTransfCTR().qtdeItemTransf());\n" +
                "        textViewDthr.setText(\"DATA HORA: \" + pcbContext.getTransfCTR().getCabecTransfAberto().getDthrCabecTransf());\n" +
                "        ListView bagListView = findViewById(R.id.bagTransfListView);\n" +
                "        AdapterList adapterList = new AdapterList(this, pcbContext.getTransfCTR().bagItemTransfArrayList());\n" +
                "        bagListView.setAdapter(adapterList);", getLocalClassName());

        pcbContext.setCodBarraBagLido("");
        customHandler.postDelayed(updateTimerThread, 0);

        textViewTotalBagTransf.setText("TOTAL DE EMBALAGENS LIDAS: " + pcbContext.getTransfCTR().qtdeItemTransf());
        textViewDthr.setText("DATA HORA: " + pcbContext.getTransfCTR().getCabecTransfAberto().getDthrCabecTransf());
        textViewSafra.setText(pcbContext.getConfigCTR().getSafraBean().getDescrSafra());

        ListView bagListView = findViewById(R.id.bagTransfListView);
        AdapterList adapterList = new AdapterList(this, pcbContext.getTransfCTR().bagItemTransfArrayList());
        bagListView.setAdapter(adapterList);

        buttonLeituraBagTransf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonLeituraBagTransf.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                sendBroadcast(new Intent(EXTRA_CONTROL)\n" +
                        "                        .setPackage(\"com.intermec.datacollectionservice\")\n" +
                        "                        .putExtra(EXTRA_SCAN, true));", getLocalClassName());
                sendBroadcast(new Intent(EXTRA_CONTROL)
                        .setPackage("com.intermec.datacollectionservice")
                        .putExtra(EXTRA_SCAN, true));
            }

        });

        buttonDigBagTransf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonDigBagTransf.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaBagTransfActivity.this, DigBagTransfActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaBagTransfActivity.this, DigBagTransfActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonSafra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonDigBagTransf.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaBagTransfActivity.this, ListaSafraActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaBagTransfActivity.this, ListaSafraActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancelarTransf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE CANCELAR O PROCESSO DE TRANSFERÊNCIA?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBagTransfActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE CANCELAR O PROCESSO DE TRANSFERÊNCIA?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        pcbContext.getTransfCTR().deleteCabecTransfAberto();\n" +
                                "                        Intent it = new Intent(ListaBagTransfActivity.this, TelaInicialActivity.class);", getLocalClassName());
                        pcbContext.getTransfCTR().deleteCabecTransfAberto();
                        Intent it = new Intent(ListaBagTransfActivity.this, TelaInicialActivity.class);
                        startActivity(it);
                        finish();
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

        buttonFinalizarTransf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pcbContext.getTransfCTR().qtdeItemTransf() > 0){

                    LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalizarTransf.setOnClickListener(new View.OnClickListener() {\n" +
                            "            @Override\n" +
                            "            public void onClick(View v) {\n" +
                            "                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagTransfActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"DESEJA FINALIZAR A TRANSFERÊNCIA?\");", getLocalClassName());

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagTransfActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("Deseja finalizar as leituras?");
                    alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                    "@Override\n" +
                                    "public void onClick(DialogInterface dialog, int which) {\n" +
                                    "pcbContext.getTransfCTR().fecharTransfCarga(getLocalClassName());\n" +
                                    "Intent it = new Intent(ListaBagCarregActivity.this, MenuInicialActivity.class);", getLocalClassName());
                            pcbContext.getTransfCTR().fecharTransfCarga(getLocalClassName());
                            Intent it = new Intent(ListaBagTransfActivity.this, TelaInicialActivity.class);
                            startActivity(it);
                            finish();

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

                } else {

                    LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "alerta.setMessage(\"FINALIZAÇÃO CANCELADA! POR FAVOR, INSIRA PELO MENOS UM BAG PARA REALIZAR A FINALIZAÇÃO DA TRANSFÊRENCIA.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagTransfActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("Operação cancelada! Por Favor, insira ao menos uma embalagem válida para prosseguir.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();

                }

            }

        });

        textViewProcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pcbContext.getConfigCTR().setPosicaoTela(5L);
                LogProcessoDAO.getInstance().insertLogProcesso("textViewProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcbContext.getConfigCTR().setPosicaoTela(3L);\n" +
                        "                Intent it = new Intent(ListaBagCarregActivity.this, SenhaActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaBagTransfActivity.this, SenhaActivity.class);
                startActivity(it);
                finish();
            }

        });


    }


    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (pcbContext.getConfigCTR().hasElemConfig()) {
                pcbContext.getConfigCTR().setStatusRetVerif(0L);
                LogProcessoDAO.getInstance().insertLogProcesso("        if (pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                        "            pmmContext.getConfigCTR().setStatusRetVerif(0L);\n" +
                        "EnvioDadosServ.status = " + EnvioDadosServ.status, getLocalClassName());
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }

            LogProcessoDAO.getInstance().insertLogProcesso("if(EnvioDadosServ.status != 3){\n" +
                    "                customHandler.postDelayed(this, 10000);\n" +
                    "            }", getLocalClassName());
            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };

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
                            "                    String codBarraBag = intent.getStringExtra(\"data\");", getLocalClassName());
                    String codBarraBag = intent.getStringExtra("data");
                    if(pcbContext.getTransfCTR().verBagRepetidoTransf(Long.valueOf(codBarraBag))) {

                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getTransfCTR().verBagRepetidoTransf(Long.valueOf(codBarraBag))) {\n" +
                                "                        progressBar = new ProgressDialog(ListaBagTransfActivity.this);\n" +
                                "                        progressBar.setCancelable(true);\n" +
                                "                        progressBar.setMessage(\"PESQUISANDO BAG...\");\n" +
                                "                        progressBar.show();\n" +
                                "                    pcbContext.getTransfCTR().verBagTransf(" + codBarraBag + ", ListaBagTransfActivity.this, ListaBagTransfActivity.class, progressBar, getLocalClassName());", getLocalClassName());

                        progressBar = new ProgressDialog(ListaBagTransfActivity.this);
                        progressBar.setCancelable(true);
                        progressBar.setMessage("PESQUISANDO BAG...");
                        progressBar.show();

                        pcbContext.getTransfCTR().verBagTransfCod(codBarraBag, ListaBagTransfActivity.this, ListaBagTransfActivity.class, progressBar, getLocalClassName());

                    }
                    else{

                        LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagTransfActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"Embalagem duplicada! Por favor realize novamente a leitura ou verifique a etiqueta e tente novamente.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagTransfActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("Embalagem duplicada! Por favor realize novamente a leitura ou verifique a etiqueta e tente novamente.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    }

                }
            }
        }
    };

    @Override
    protected void onResume() {
        LogProcessoDAO.getInstance().insertLogProcesso("    protected void onResume() {\n" +
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