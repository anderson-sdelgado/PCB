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

public class ListaBagCargaActivity extends ActivityGeneric {

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
        setContentView(R.layout.activity_lista_bag_carga);

        pcbContext = (PCBContext) getApplication();

        TextView textViewTotalBagCarreg = findViewById(R.id.textViewTotalBagCarga);
        TextView textViewQtdeBagAdd = findViewById(R.id.textViewQtdeBagAddCarga);
        TextView textViewDthr = findViewById(R.id.textViewDthrCarga);
        TextView textViewTituloListaBag = findViewById(R.id.textViewTituloListaBagCarga);
        Button buttonLeituraBagCarreg = findViewById(R.id.buttonLeituraBagCarga);
        Button buttonDigBagCarreg = findViewById(R.id.buttonDigBagCarga);
        Button buttonCancelarCarreg = findViewById(R.id.buttonCancelarCarga);
        Button buttonFinalizarCarreg = findViewById(R.id.buttonFinalizarCarga);
        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        textViewProcesso = findViewById(R.id.textViewProcesso);

        LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.setCodBarraBagLido(\"\");\n" +
                "                customHandler.postDelayed(updateTimerThread, 0);\n" +
                "textViewTotalBagCarreg.setText(\"TOTAL DE EMBALAGENS PARA CARREGAMENTO \" + pcbContext.getCarregCTR().getOrdemCargaId(pcbContext.getCarregCTR().getCabecAberto().getIdOrdemCabecCarreg()).getQtdeEmbProdOrdemCarreg());\n" +
                "        textViewQtdeBagAdd.setText(\"QTDE BAG ADICIONADO: \" + pcbContext.getCarregCTR().qtdeItemCarreg());\n" +
                "        textViewDthr.setText(\"DATA HORA: \" + pcbContext.getCarregCTR().getCabecAberto().getDthrCabecCarreg());\n" +
                "        textViewTituloListaBag.setText(\"ORDEM CARGA: \" + pcbContext.getCarregCTR().getOrdemCargaId(pcbContext.getCarregCTR().getCabecAberto().getIdOrdemCabecCarreg()).getTicketOrdemCarreg());\n" +
                "        ListView bagListView = findViewById(R.id.bagListView);\n" +
                "        AdapterList adapterList = new AdapterList(this, pcbContext.getCarregCTR().bagItemCarregArrayList());\n" +
                "        bagListView.setAdapter(adapterList);", getLocalClassName());

        pcbContext.setCodBarraBagLido("");
        customHandler.postDelayed(updateTimerThread, 0);

        textViewTotalBagCarreg.setText("TOTAL DE EMBALAGENS PARA CARREGAMENTO: " + pcbContext.getCargaCTR().getQtdeEmbProdOrdemCarga());
        textViewQtdeBagAdd.setText("QUANTIDADE DE EMBALAGENS RELACIONADAS: " + pcbContext.getCargaCTR().qtdeItemCarga());
        textViewDthr.setText("DATA HORA: " + pcbContext.getCargaCTR().getCabecCargaAberto().getDthrCabecCarga());
        textViewTituloListaBag.setText("TICKET DE CARREGAMENTO: " + pcbContext.getCargaCTR().getTicketOrdemCarga());

        ListView bagListView = findViewById(R.id.bagCargaListView);
        AdapterList adapterList = new AdapterList(this, pcbContext.getCargaCTR().bagItemCargaArrayList());
        bagListView.setAdapter(adapterList);

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCarregActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
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
                                    "progressBar = new ProgressDialog(ListaBagCarregActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog(ListaBagCargaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(ListaBagCarregActivity.this, ListaBagCarregActivity.class, progressBar, \"BagCarreg\", 1, getLocalClassName());", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(ListaBagCargaActivity.this, ListaBagCargaActivity.class, progressBar, "BagCarreg", 1, getLocalClassName());

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
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
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

        buttonLeituraBagCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonLeituraBagCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcbContext.getCargaCTR().qtdeRestItemCarga() > 0) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeRestItemCarreg() > 0) {\n" +
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CARGA COMPLETA! POR FAVOR, FINALIZE A CARREGAMENTO.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }
            }

        });

        buttonDigBagCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pcbContext.getCargaCTR().qtdeRestItemCarga() > 0) {
                    LogProcessoDAO.getInstance().insertLogProcesso("buttonDigBagCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                            "            @Override\n" +
                            "            public void onClick(View v) {\n" +
                            "if(pcbContext.getCarregCTR().qtdeRestItemCarreg() > 0) {\n" +
                            "                Intent it = new Intent(ListaBagCarregActivity.this, DigBagCarregActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaBagCargaActivity.this, DigBagCargaActivity.class);
                    startActivity(it);
                    finish();
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
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CONFIRMA A QUANTIDADE DE EMBALAGENS RELACIONADAS AO TICKET?");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }
            }

        });

        buttonCancelarCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE CANCELAR O PROCESSO DE CARREGAMENTO?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( ListaBagCargaActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE CANCELAR O PROCESSO DE CARREGAMENTO?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "@Override\n" +
                                "public void onClick(DialogInterface dialog, int which) {\n" +
                                "pcbContext.getCarregCTR().deleteCabecAberto();\n" +
                                "Intent it = new Intent(ListaBagCarregActivity.this, MenuInicialActivity.class);", getLocalClassName());
                        pcbContext.getCargaCTR().deleteCabecCargaAberto();
                        Intent it = new Intent(ListaBagCargaActivity.this, TelaInicialActivity.class);
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

        buttonFinalizarCarreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonFinalizarCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());
                if(pcbContext.getCargaCTR().qtdeItemCarga() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeItemCarreg() > 0){", getLocalClassName());
                    if(pcbContext.getCargaCTR().qtdeRestItemCarga() == 0) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeRestItemCarreg() == 0) {\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCarregActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"DESEJA FINALIZAR A CARGA?\");", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA FINALIZAR A CARGA?");
                        alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                        "@Override\n" +
                                        "public void onClick(DialogInterface dialog, int which) {\n" +
                                        "pcbContext.getCarregCTR().fecharCabec();\n" +
                                        "Intent it = new Intent(ListaBagCarregActivity.this, MenuInicialActivity.class);", getLocalClassName());
                                pcbContext.getCargaCTR().fecharCabecCarga(getLocalClassName());
                                Intent it = new Intent(ListaBagCargaActivity.this, TelaInicialActivity.class);
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
                        LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                                "                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCarregActivity.this);\n" +
                                "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                                "                        alerta.setMessage(\"FINALIZAÇÃO CANCELADA! POR FAVOR, INSIRA TODOS OS BAG PARA PODER FINALIZAR O CARREGAMENTO.\");\n" +
                                "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                "                            @Override\n" +
                                "                            public void onClick(DialogInterface dialog, int which) {\n" +
                                "                            }\n" +
                                "                        });\n" +
                                "                        alerta.show();", getLocalClassName());
                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FINALIZAÇÃO CANCELADA! POR FAVOR, INSIRA TODOS OS BAG PARA PODER FINALIZAR O CARREGAMENTO.");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alerta.show();

                    }
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "alerta.setMessage(\"FINALIZAÇÃO CANCELADA! POR FAVOR, INSIRÁ PELO MENOS UM BAG PARA REALIZAR A FINALIZAÇÃO DA CARGA.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show();", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaBagCargaActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FINALIZAÇÃO CANCELADA! POR FAVOR, INSIRA PELO MENOS UM BAG PARA REALIZAR A FINALIZAÇÃO DA CARGA.");
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
                pcbContext.getConfigCTR().setPosicaoTela(4L);
                LogProcessoDAO.getInstance().insertLogProcesso("textViewProcesso.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcbContext.getConfigCTR().setPosicaoTela(3L);\n" +
                        "                Intent it = new Intent(ListaBagCarregActivity.this, SenhaActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaBagCargaActivity.this, SenhaActivity.class);
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
                    String codBarraBag = intent.getStringExtra("data");
                    pcbContext.setCodBarraBagLido(codBarraBag);
                    LogProcessoDAO.getInstance().insertLogProcesso("if (version >= 1) {\n" +
                            "                    String codBarraBag = intent.getStringExtra(\"data\");\n" +
                            "                    pcbContext.setCodBarraBagLido(codBarraBag);\n" +
                            "                    Intent it = new Intent(ListaBagCarregActivity.this, MsgAddBagCarregActivity.class);", getLocalClassName());
                    Intent it = new Intent(ListaBagCargaActivity.this, MsgAddBagCargaActivity.class);
                    startActivity(it);
                    finish();
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