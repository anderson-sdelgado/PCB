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

public class ListaTipoOrdemCargaActivity extends ActivityGeneric {

    private ListView tipoOrdemCargaListView;
    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_ordem_carga);

        pcbContext = (PCBContext) getApplication();

        Button buttonRetTipoOrdemCarga = findViewById(R.id.buttonRetTipoOrdemCarga);

        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"ESTOQUE\");\n" +
                "        itens.add(\"PRODUÇÃO\");\n" +
                "        AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        tipoOrdemCargaListView= findViewById(R.id.listTipoOrdemCarga);\n" +
                "        tipoOrdemCargaListView.setAdapter(adapterList);", getLocalClassName());
        ArrayList<String> itens = new ArrayList<>();

        itens.add("ESTOQUE");
        itens.add("PRODUÇÃO");

        AdapterList adapterList = new AdapterList(this, itens);
        tipoOrdemCargaListView= findViewById(R.id.listTipoOrdemCarga);
        tipoOrdemCargaListView.setAdapter(adapterList);
        tipoOrdemCargaListView.setOnItemClickListener((l, v, position, id) -> {

            LogProcessoDAO.getInstance().insertLogProcesso("tipoOrdemCargaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                    "                                    long id) {\n" +
                    "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                    "                String text = textView.getText().toString();", getLocalClassName());
            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();
            if (text.equals("ESTOQUE")) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (text.equals(\"ESTOQUE\")) {\n" +
                        "                    pcbContext.getConfigCTR().setTipoApont(1L);\n" +
                        "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setTipoCabecCarga(0L);", getLocalClassName());
                pcbContext.getConfigCTR().setTipoApont(1L);
                pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setTipoCabecCarga(0L);

            } else  {
                LogProcessoDAO.getInstance().insertLogProcesso("} else  {\n" +
                        "                    pcbContext.getConfigCTR().setTipoApont(3L);\n" +
                        "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setTipoCabecCarga(1L);", getLocalClassName());
                pcbContext.getConfigCTR().setTipoApont(3L);
                pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setTipoCabecCarga(1L);
            }

            LogProcessoDAO.getInstance().insertLogProcesso("Intent it = new Intent(ListaTipoOrdemCargaActivity.this, AtualOrdemCargaActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaTipoOrdemCargaActivity.this, AtualOrdemCargaActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetTipoOrdemCarga.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonRetTipoOrdemCarga.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                Intent it = new Intent(ListaTipoOrdemCargaActivity.this, ListaTipoApontActivity.class);", getLocalClassName());
            Intent it = new Intent(ListaTipoOrdemCargaActivity.this, ListaTipoApontActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}