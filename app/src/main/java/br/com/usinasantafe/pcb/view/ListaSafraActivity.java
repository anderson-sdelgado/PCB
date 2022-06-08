package br.com.usinasantafe.pcb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.SafraBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class ListaSafraActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private List<SafraBean> safraBeanList;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_safra);

        pcbContext = (PCBContext) getApplication();

        Button buttonAtualSafra = findViewById(R.id.buttonAtualSafra);
        Button buttonRetSafra = findViewById(R.id.buttonRetSafra);

        LogProcessoDAO.getInstance().insertLogProcesso("safraBeanList = pcbContext.getConfigCTR().safraList();\n" +
                "        ArrayList<String> itens = new ArrayList<String>();\n" +
                "        for(SafraBean safraBean : safraBeanList){\n" +
                "            itens.add(safraBean.getDescrSafra());\n" +
                "        }\n" +
                "        ListView safraListView = findViewById(R.id.listSafra);\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        safraListView.setAdapter(adapterList);", getLocalClassName());
        safraBeanList = pcbContext.getConfigCTR().safraList();

        ArrayList<String> itens = new ArrayList<String>();
        for(SafraBean safraBean : safraBeanList){
            itens.add(safraBean.getDescrSafra());
        }

        ListView safraListView = findViewById(R.id.listSafra);
        AdapterList adapterList = new AdapterList(this, itens);
        safraListView.setAdapter(adapterList);
        safraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("safraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                SafraBean safraBean = safraBeanList.get(position);\n" +
                        "                safraBeanList.clear();\n" +
                        "                pcbContext.getConfigCTR().setIdSafra(safraBean.getIdSafra());\n" +
                        "                Intent it = new Intent(ListaSafraActivity.this, ListaBagTransfActivity.class);", getLocalClassName());

                SafraBean safraBean = safraBeanList.get(position);
                safraBeanList.clear();

                pcbContext.getConfigCTR().setIdSafra(safraBean.getIdSafra());
                Intent it = new Intent(ListaSafraActivity.this, ListaBagTransfActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetSafra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetSafra.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(ListaSafraActivity.this, ListaBagTransfActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaSafraActivity.this, ListaBagTransfActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonAtualSafra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonAtualSafra.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaSafraActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaSafraActivity.this);
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
                            progressBar = new ProgressDialog(ListaSafraActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(ListaOrdemCarregActivity.this, ListaOrdemCarregActivity.class, progressBar, \"OrdemCarreg\", 1, getLocalClassName());", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(ListaSafraActivity.this, ListaSafraActivity.class, progressBar, "Safra", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso(" } else {\n" +
                                    "AlertDialog.Builder alerta = new AlertDialog.Builder(ListaSafraActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder(ListaSafraActivity.this);
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

    public void onBackPressed() {
    }

}