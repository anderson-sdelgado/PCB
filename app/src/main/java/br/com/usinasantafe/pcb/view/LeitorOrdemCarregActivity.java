package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class LeitorOrdemCarregActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCBContext pcbContext;
    private TextView txtRetOrdemCarreg;
    private ProgressDialog progressBar;
    private OrdemCarregBean ordemCarregBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_ordem_carreg);

        pcbContext = (PCBContext) getApplication();

        txtRetOrdemCarreg = (TextView) findViewById(R.id.txtRetOrdemCarreg);
        Button buttonOkOrdemCarga = (Button) findViewById(R.id.buttonOkOrdemCarreg);
        Button buttonCancOrdemCarga = (Button) findViewById(R.id.buttonCancOrdemCarreg);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptOrdemCarga = (Button) findViewById(R.id.buttonCaptOrdemCarreg);

        ordemCarregBean = new OrdemCarregBean();
        ordemCarregBean.setIdOrdemCarreg(0L);

        txtRetOrdemCarreg.setText("POR FAVOR, REALIZE A LEITURA DO CÓDIGO DA ORDEM DE CARGA.");

        buttonOkOrdemCarga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (ordemCarregBean.getIdOrdemCarreg() > 0L) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (ordemCarregBean.getIdOrdemCarreg() > 0L) {\n" +
                            "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCargaCabecCarga(ordemCargaBean.getIdOrdemCarga());\n" +
                            "                    Intent it = new Intent(LeitorOrdemCargaActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());
                    pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarreg(ordemCarregBean.getIdOrdemCarreg());
                    Intent it = new Intent(LeitorOrdemCarregActivity.this, ListaOrdemCarregActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancOrdemCarga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonCancColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(LeitorOrdemCargaActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(LeitorOrdemCarregActivity.this, ListaOrdemCarregActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptOrdemCarga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("        buttonCaptColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(LeitorOrdemCargaActivity.this, br.com.usinasantafe.pcb.zxing.CaptureActivity.class);\n" +
                        "                startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
                Intent it = new Intent(LeitorOrdemCarregActivity.this, br.com.usinasantafe.pcb.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorOrdemCargaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorOrdemCarregActivity.this);
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
                                    "progressBar = new ProgressDialog(LeitorOrdemCargaActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog(LeitorOrdemCarregActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(LeitorOrdemCarregActivity.this, LeitorOrdemCarregActivity.class, progressBar, "OrdemCarga", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorOrdemCargaActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorOrdemCarregActivity.this);
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

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            LogProcessoDAO.getInstance().insertLogProcesso("    @Override\n" +
                    "    public void onActivityResult(int requestCode, int resultCode, Intent data) {\n" +
                    "        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {\n" +
                    "            String nroOrdemCarga = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String nroOrdemCarga = data.getStringExtra("SCAN_RESULT");
            if (nroOrdemCarga.length() == 7) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (nroOrdemCarga.length() == 7) {\n" +
                        "nroOrdemCarga = nroOrdemCarga.substring(0, 6);", getLocalClassName());
                nroOrdemCarga = nroOrdemCarga.substring(0, 6);
                if (pcbContext.getCarregCTR().verOrdemCarregTicket(nroOrdemCarga)) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCargaCTR().verOrdemCargaNro(nroOrdemCarga)) {\n" +
                            "                    ordemCarregBean = pcbContext.getCargaCTR().getOrdemCargaNro(nroOrdemCarga);\n" +
                            "                    txtRetOrdemCarga.setText(nroOrdemCarga);", getLocalClassName());
                    ordemCarregBean = pcbContext.getCarregCTR().getOrdemCarregTicket(nroOrdemCarga);
                    txtRetOrdemCarreg.setText(nroOrdemCarga);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    txtRetOrdemCarga.setText(\"Nro de Ordem de Carga Inexistente\");", getLocalClassName());
                    txtRetOrdemCarreg.setText("Nro de Ordem de Carga Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
    }

}