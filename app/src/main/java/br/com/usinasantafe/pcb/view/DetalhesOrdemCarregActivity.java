package br.com.usinasantafe.pcb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class DetalhesOrdemCarregActivity extends ActivityGeneric {

    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_ordem_carreg);

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

        OrdemCarregBean ordemCarregBean = pcbContext.getCarregCTR().getOrdemCargaId(pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().getIdOrdemCabecCarreg());
        String descr = "ORDEM DE CARGA: " + ordemCarregBean.getIdOrdemCarreg() + "\n" +
                            "TICKET: " + ordemCarregBean.getTicketOrdemCarreg() + "\n" +
                            "EXPORTAÇÃO? " + ((ordemCarregBean.getDestExpOrdemCarreg() == 1L) ? "SIM" : "NÃO") + "\n" +
                            "PRODUTO: " + ordemCarregBean.getProdutoOrdemCarreg() + "\n" +
                            "CLASSIFICAÇÃO: " + ordemCarregBean.getClassifOrdemCarreg();
        textViewDescrOrdemCarreg.setText(descr);

        buttonOkDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pcbContext.getCarregCTR().salvarCabecAberto();
                LogProcessoDAO.getInstance().insertLogProcesso("buttonOkDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                pcbContext.getCarregCTR().salvarCabecAberto();\n" +
                        "                Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaBagCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaBagCarregActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCancDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonCancDetalhesOrdemCarreg.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "            Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(DetalhesOrdemCarregActivity.this, ListaOrdemCarregActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }

}