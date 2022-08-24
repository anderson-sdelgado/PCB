package br.com.usinasantafe.pcb.view;

import android.os.Bundle;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class AtualOrdemCargaActivity extends ActivityGeneric {

    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atual_ordem_carga);

        pcbContext = (PCBContext) getApplication();

        LogProcessoDAO.getInstance().insertLogProcesso("pcbContext.getConfigCTR().atualDados(AtualOrdemCargaActivity.this, ListaOrdemCargaActivity.class, \"OrdemCarga\", 3, getLocalClassName());", getLocalClassName());
        pcbContext.getConfigCTR().atualDados(AtualOrdemCargaActivity.this, ListaOrdemCargaActivity.class, "OrdemCarga", 3, getLocalClassName());

    }

    public void onBackPressed() {
    }

}