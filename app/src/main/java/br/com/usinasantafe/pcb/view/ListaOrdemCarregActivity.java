package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class ListaOrdemCarregActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private ProgressDialog progressBar;
    private List<OrdemCarregBean> ordemCarregList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordem_carreg);

        Button buttonCapturaOrdemCarreg = findViewById(R.id.buttonCapturaOrdemCarreg);
        Button buttonAtualOrdemCarreg = findViewById(R.id.buttonAtualOrdemCarreg);
        Button buttonRetOrdemCarreg = findViewById(R.id.buttonRetOrdemCarreg);

        pcbContext = (PCBContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("ordemCarregList = pcbContext.getCarregCTR().ordemCargaList();\n" +
                "        ListView listOrdemCarreg = findViewById(R.id.listOrdemCarreg);\n" +
                "        AdapterListOrdemCarreg adapterListOrdemCarreg = new AdapterListOrdemCarreg(this, ordemCarregList);\n" +
                "        listOrdemCarreg.setAdapter(adapterListOrdemCarreg);", getLocalClassName());

        ordemCarregList = pcbContext.getCarregCTR().ordemCargaList();

        ListView ordemCarregListView = findViewById(R.id.listOrdemCarreg);
        AdapterListOrdemCarreg adapterListOrdemCarreg = new AdapterListOrdemCarreg(this, ordemCarregList);
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

                OrdemCarregBean ordemCarregBean = ordemCarregList.get(position);
                ordemCarregList.clear();

                pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdOrdemCabecCarreg(ordemCarregBean.getIdOrdemCarreg());

                Intent it = new Intent(ListaOrdemCarregActivity.this, DetalhesOrdemCarregActivity.class);
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
                        "                Intent it = new Intent(ListaOrdemCarregActivity.this, LeitorOrdemCargaActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaOrdemCarregActivity.this, LeitorOrdemCarregActivity.class);
                startActivity(it);
                finish();
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
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCarregActivity.this);
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
                            progressBar = new ProgressDialog(ListaOrdemCarregActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(ListaOrdemCarregActivity.this, ListaOrdemCarregActivity.class, progressBar, \"OrdemCarreg\", 1, getLocalClassName());", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(ListaOrdemCarregActivity.this, ListaOrdemCarregActivity.class, progressBar, "OrdemCarreg", 1, getLocalClassName());

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
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaOrdemCarregActivity.this);
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
                Intent it = new Intent(ListaOrdemCarregActivity.this, LeitorFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}