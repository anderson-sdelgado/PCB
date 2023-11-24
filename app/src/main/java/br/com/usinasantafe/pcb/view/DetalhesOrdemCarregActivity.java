package br.com.usinasantafe.pcb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class DetalhesOrdemCarregActivity extends ActivityGeneric {

    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ordem_carga);

        Button buttonOkDetalhesOrdemCarreg = findViewById(R.id.buttonOkDetalhesOrdemCarreg);
        Button buttonCancDetalhesOrdemCarreg = findViewById(R.id.buttonCancDetalhesOrdemCarreg);
        TextView textViewDescrOrdemCarreg = findViewById(R.id.textViewDescrOrdemCarreg);

        pcbContext = (PCBContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("OrdemCarregBean ordemCarregBean = pcbContext.getCarregCTR().getOrdemCargaId(pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().getIdOrdemCabecCarreg());\n" +
                "        String descr = \"ORDEM DE CARGA: \" + ordemCarregBean.getIdOrdemCarreg() + \"\\n\" +\n" +
                "                            \"TICKET: \" + ordemCarregBean.getTicketOrdemCarreg() + \"\\n\" +\n" +
                "                            \"EXPORTAÇÃO? \" + ((ordemCarregBean.getDestExpOrdemCarreg() == 1L) ? \"SIM\" : \"NÃO\") + \"\\n\" +\n" +
                "                            \"PRODUTO: \" + ordemCarregBean.getProdutoOrdemCarreg() + \"\\n\" +\n" +
                "                            \"CLASSIFICAÇÃO: \" + ordemCarregBean.getClassifOrdemCarreg();\n" +
                "        textViewDescrOrdemCarreg.setText(descr);", getLocalClassName());

        OrdemCargaBean ordemCargaBean = pcbContext.getCargaCTR().getOrdemCargaId(pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().getIdOrdemCabecCarga());
        String descr = "ORDEM DE CARGA: " + ordemCargaBean.getIdOrdemCarga() + "\n" +
                            "TICKET: " + ordemCargaBean.getTicketOrdemCarga() + "\n" +
                            "EXPORTAÇÃO? " + ((ordemCargaBean.getDestExpOrdemCarga() == 1L) ? "SIM" : "NÃO") + "\n" +
                            "PRODUTO: " + ordemCargaBean.getProdutoOrdemCarga() + "\n" +
                            "CLASSIFICAÇÃO: " + ordemCargaBean.getClassifOrdemCarga();
        textViewDescrOrdemCarreg.setText(descr);

        buttonOkDetalhesOrdemCarreg.setOnClickListener(v -> {

            pcbContext.getCargaCTR().salvarCabecCargaAberto();
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "                pcbContext.getCarregCTR().salvarCabecAberto();\n" +
                    "                Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaBagCarregActivity.class);", getLocalClassName());
            Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaBagCargaActivity.class);
            startActivity(it);
            finish();
        });

        buttonCancDetalhesOrdemCarreg.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {\n" +
                    "            Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());
            Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaOrdemCargaActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}