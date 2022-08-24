package br.com.usinasantafe.pcb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class ListaTipoApontActivity extends ActivityGeneric {

    private ListView tipoApontListView;
    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_apont);

        pcbContext = (PCBContext) getApplication();

        Button buttonRetTipoApont = findViewById(R.id.buttonRetTipoApont);

        LogProcessoDAO.getInstance().insertLogProcesso("pcbContext = (PCBContext) getApplication();\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"ORDEM CARGA\");\n" +
                "        itens.add(\"TRANSFERÊNCIA\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        tipoApontListView = findViewById(R.id.listTipoApont);\n" +
                "        tipoApontListView.setAdapter(adapterList);", getLocalClassName());

        ArrayList<String> itens = new ArrayList<>();

        itens.add("ORDEM CARGA");
        itens.add("TRANSFERÊNCIA");

        AdapterList adapterList = new AdapterList(this, itens);
        tipoApontListView = findViewById(R.id.listTipoApont);
        tipoApontListView.setAdapter(adapterList);
        tipoApontListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("tipoApontListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();
                if (text.equals("ORDEM CARGA")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"ORDEM CARGA\")) {\n" +
                            "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdFuncCabecCarga(pcbContext.getConfigCTR().getFuncMatric(pcbContext.getMatricFunc()).getIdFunc());\n" +
                            "                    Intent it = new Intent(ListaTipoApontActivity.this, ListaOrdemCargaActivity.class);", getLocalClassName());
                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setIdFuncCabecCarga(pcbContext.getConfigCTR().getFuncMatric(pcbContext.getMatricFunc()).getIdFunc());
                    Intent it = new Intent(ListaTipoApontActivity.this, ListaTipoOrdemCargaActivity.class);
                    startActivity(it);
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"TRANSFERÊNCIA\")) {\n" +
                            "                    pcbContext.getConfigCTR().setTipoApont(2L);\n" +
                            "                    pcbContext.getTransfCTR().salvarCabecTransfAberto(pcbContext.getConfigCTR().getFuncMatric(Long.parseLong(editTextPadrao.getText().toString())).getIdFunc());\n" +
                            "                    Intent it = new Intent(ListaTipoApontActivity.this, ListaBagCargaActivity.class);", getLocalClassName());
                    pcbContext.getConfigCTR().setTipoApont(2L);
                    pcbContext.getConfigCTR().setIdSafra();
                    pcbContext.getTransfCTR().salvarCabecTransfAberto(pcbContext.getConfigCTR().getFuncMatric(pcbContext.getMatricFunc()).getIdFunc());
                    Intent it = new Intent(ListaTipoApontActivity.this, ListaBagTransfActivity.class);
                    startActivity(it);
                }
                finish();

            }

        });

        buttonRetTipoApont.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonRetOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "            Intent it = new Intent(ListaTipoApontActivity.this, LeitorFuncActivity.class);", getLocalClassName());
                Intent it = new Intent(ListaTipoApontActivity.this, LeitorFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}