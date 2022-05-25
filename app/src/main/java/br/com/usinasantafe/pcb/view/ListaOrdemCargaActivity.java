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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class ListaOrdemCargaActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCBContext pcbContext;
    private ProgressDialog progressBar;
    private List<OrdemCargaBean> ordemCarregList;

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
        setContentView(R.layout.activity_lista_ordem_carga);

        Button buttonCapturaOrdemCarreg = findViewById(R.id.buttonCapturaOrdemCarreg);
        Button buttonAtualOrdemCarreg = findViewById(R.id.buttonAtualOrdemCarreg);
        Button buttonRetOrdemCarreg = findViewById(R.id.buttonRetOrdemCarreg);

        pcbContext = (PCBContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ordemCarregList = pcbContext.getCarregCTR().ordemCargaList();\n" +
                "        ListView listOrdemCarreg = findViewById(R.id.listOrdemCarreg);\n" +
                "        AdapterListOrdemCarreg adapterListOrdemCarreg = new AdapterListOrdemCarreg(this, ordemCarregList);\n" +
                "        listOrdemCarreg.setAdapter(adapterListOrdemCarreg);", getLocalClassName());

        ordemCarregList = pcbContext.getCargaCTR().ordemCargaList();

        ListView ordemCarregListView = findViewById(R.id.listOrdemCarreg);
        AdapterListOrdemCarga adapterListOrdemCarreg = new AdapterListOrdemCarga(this, ordemCarregList);
        ordemCarregListView.setAdapter(adapterListOrdemCarreg);

        ordemCarregListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                OrdemCarregBean ordemCarregBean = ordemCarregList.get(position);\n" +
                        "                ordemCarregList.clear();\n" +
                        "                pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarreg(ordemCarregBean.getIdOrdemCarreg());\n" +
                        "                Intent it = new Intent(ListaOrdemCarregActivity.this, DetalheOrdemCarregActivity.class);", getLocalClassName());

                OrdemCargaBean ordemCargaBean = ordemCarregList.get(position);
                ordemCarregList.clear();

                pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarga(ordemCargaBean.getIdOrdemCarga());

                Intent it = new Intent(ListaOrdemCargaActivity.this, DetalhesOrdemCarregActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCapturaOrdemCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCapturaOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                sendBroadcast(new Intent(EXTRA_CONTROL)\n" +
                        "                        .setPackage(\"com.intermec.datacollectionservice\")\n" +
                        "                        .putExtra(EXTRA_SCAN, true)\n" +
                        "                );", getLocalClassName());
                sendBroadcast(new Intent(EXTRA_CONTROL)
                        .setPackage("com.intermec.datacollectionservice")
                        .putExtra(EXTRA_SCAN, true)
                );
            }

        });

        buttonAtualOrdemCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCarregActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCargaActivity.this);
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
                            progressBar = new ProgressDialog(ListaOrdemCargaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(ListaOrdemCarregActivity.this, ListaOrdemCarregActivity.class, progressBar, \"OrdemCarreg\", 1, getLocalClassName());", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(ListaOrdemCargaActivity.this, ListaOrdemCargaActivity.class, progressBar, "OrdemCarreg", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCarregActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCargaActivity.this);
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

        buttonRetOrdemCarreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaOrdemCarregActivity.this, LeitorFuncActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaOrdemCargaActivity.this, LeitorFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        LogProcessoDAO.getInstance().insertLogProcesso("public void onActivityResult(int requestCode, int resultCode, Intent data){", getLocalClassName());
        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            LogProcessoDAO.getInstance().insertLogProcesso("if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String codBarraBag = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String ticketOrdemCarreg = data.getStringExtra("SCAN_RESULT");
            if (ticketOrdemCarreg.length() == 7) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (nroOrdemCarga.length() == 7) {", getLocalClassName());
                if (pcbContext.getCargaCTR().verOrdemCargaTicket(ticketOrdemCarreg)) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCarregCTR().verOrdemCarregTicket(codBarraBag)) {\n" +
                            "pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarreg(pcbContext.getCarregCTR().getOrdemCarregTicket(codBarraBag).getIdOrdemCarreg());\n" +
                            "                    Intent it = new Intent(ListaOrdemCarregActivity.this, DetalhesOrdemCarregActivity.class);", getLocalClassName());
                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarga(pcbContext.getCargaCTR().getOrdemCargaTicket(ticketOrdemCarreg).getIdOrdemCarga());
                    Intent it = new Intent(ListaOrdemCargaActivity.this, DetalhesOrdemCarregActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCarregActivity.this);\n" +
                            "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                    alerta.setMessage(\"NRO DE ORDEM DE CARGA INEXISTENTE!\");\n" +
                            "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                        @Override\n" +
                            "                        public void onClick(DialogInterface dialog, int which) {\n" +
                            "                        }\n" +
                            "                    });\n" +
                            "                    alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCargaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA LEITURA DO TICKET!");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCarregActivity.this);\n" +
                        "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                    alerta.setMessage(\"NRO DE ORDEM DE CARGA INEXISTENTE!\");\n" +
                        "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                        "                        @Override\n" +
                        "                        public void onClick(DialogInterface dialog, int which) {\n" +
                        "                        }\n" +
                        "                    });\n" +
                        "                    alerta.show();", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCargaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA LEITURA DO TICKET!");
                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alerta.show();
            }
        }
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
                            "                    String ticketOrdemCarreg = intent.getStringExtra(\"data\");", getLocalClassName());
                    String ticketOrdemCarreg = intent.getStringExtra("data");
                    if (ticketOrdemCarreg.length() == 7) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (ticketOrdemCarreg.length() == 7) {", getLocalClassName());
                        if (pcbContext.getCargaCTR().verOrdemCargaTicket(ticketOrdemCarreg)) {
                            LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCarregCTR().verOrdemCarregTicket(ticketOrdemCarreg)) {\n" +
                                    "pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarreg(pcbContext.getCarregCTR().getOrdemCarregTicket(ticketOrdemCarreg).getIdOrdemCarreg());\n" +
                                    "                    Intent it = new Intent(ListaOrdemCarregActivity.this, DetalhesOrdemCarregActivity.class);", getLocalClassName());
                            pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarga(pcbContext.getCargaCTR().getOrdemCargaTicket(ticketOrdemCarreg).getIdOrdemCarga());
                            Intent it = new Intent(ListaOrdemCargaActivity.this, DetalhesOrdemCarregActivity.class);
                            startActivity(it);
                            finish();
                        } else {
                            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCarregActivity.this);\n" +
                                    "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                    alerta.setMessage(\"NRO DE ORDEM DE CARGA INEXISTENTE!\");\n" +
                                    "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                        @Override\n" +
                                    "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                        }\n" +
                                    "                    });\n" +
                                    "                    alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCargaActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA LEITURA DO TICKET!");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();
                        }
                    } else {
                        LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                                "AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCarregActivity.this);\n" +
                                "                    alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                    alerta.setMessage(\"NRO DE ORDEM DE CARGA INEXISTENTE!\");\n" +
                                "                    alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                        @Override\n" +
                                "                        public void onClick(DialogInterface dialog, int which) {\n" +
                                "                        }\n" +
                                "                    });\n" +
                                "                    alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder( ListaOrdemCargaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALHA NA LEITURA DO TICKET!");
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