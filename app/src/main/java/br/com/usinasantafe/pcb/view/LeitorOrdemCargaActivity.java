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
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class LeitorOrdemCargaActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCBContext pcbContext;
    private TextView txtRetOrdemCarga;
    private ProgressDialog progressBar;
    private OrdemCargaBean ordemCargaBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_ordem_carga);

        pcbContext = (PCBContext) getApplication();

        txtRetOrdemCarga = (TextView) findViewById(R.id.txtRetOrdemCarga);
        Button buttonOkOrdemCarga = (Button) findViewById(R.id.buttonOkOrdemCarga);
        Button buttonCancOrdemCarga = (Button) findViewById(R.id.buttonCancOrdemCarga);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptOrdemCarga = (Button) findViewById(R.id.buttonCaptOrdemCarga);

        ordemCargaBean = new OrdemCargaBean();
        ordemCargaBean.setIdOrdemCarga(0L);
        ordemCargaBean.setNroOrdemCarga("");

        txtRetOrdemCarga.setText("POR FAVOR, REALIZE A LEITURA DO CÓDIGO DA ORDEM DE CARGA.");

        buttonOkOrdemCarga.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (ordemCargaBean.getIdOrdemCarga() > 0) {
                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCargaCabecCarga(ordemCargaBean.getIdOrdemCarga());

                    LogProcessoDAO.getInstance().insertLogProcesso("if (ordemCargaBean.getIdOrdemCarga() > 0) {\n" +
                            "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCargaCabecCarga(ordemCargaBean.getIdOrdemCarga());\n" +
                            "                    Intent it = new Intent(LeitorOrdemCargaActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());

                    Intent it = new Intent(LeitorOrdemCargaActivity.this, ListaOrdemCarregActivity.class);
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
                Intent it = new Intent(LeitorOrdemCargaActivity.this, ListaOrdemCarregActivity.class);
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
                Intent it = new Intent(LeitorOrdemCargaActivity.this, br.com.usinasantafe.pcb.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder(LeitorOrdemCargaActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorOrdemCargaActivity.this);
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
                            progressBar = new ProgressDialog(LeitorOrdemCargaActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(LeitorOrdemCargaActivity.this, LeitorOrdemCargaActivity.class, progressBar, "OrdemCarga", 1, getLocalClassName());

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
                            AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorOrdemCargaActivity.this);
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
                    "            String matricula = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String nroOrdemCarga = data.getStringExtra("SCAN_RESULT");
//            if (matricula.length() == 8) {
//                LogProcessoDAO.getInstance().insertLogProcesso("            if (matricula.length() == 8) {\n" +
//                        "                matricula = matricula.substring(0, 7);", getLocalClassName());
//                matricula = matricula.substring(0, 7);
                if (pcbContext.getCargaCTR().verOrdemCargaNro(nroOrdemCarga)) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCargaCTR().verFunc(Long.parseLong(matricula))) {\n" +
                            "                    funcBean = pcbContext.getCargaCTR().getFuncMatric(Long.parseLong(matricula));\n" +
                            "                    txtRetColab.setText(matricula + \"\\n\" + funcBean.getNomeFunc());", getLocalClassName());
                    ordemCargaBean = pcbContext.getCargaCTR().getOrdemCargaNro(nroOrdemCarga);
                    txtRetOrdemCarga.setText(nroOrdemCarga);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    txtRetColab.setText(\"Nro de Ordem de Carga Inexistente\");", getLocalClassName());
                    txtRetOrdemCarga.setText("Nro de Ordem de Carga Inexistente");
                }
//            }
        }

    }

    public void onBackPressed() {
    }

}