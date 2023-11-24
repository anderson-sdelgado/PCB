package br.com.usinasantafe.pcb.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class SenhaActivity extends ActivityGeneric {

    private EditText editTextSenha;
    private PCBContext pcbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senha);

        editTextSenha = findViewById(R.id.editTextSenha);
        Button buttonOkSenha = findViewById(R.id.buttonOkSenha);
        Button buttonCancSenha = findViewById(R.id.buttonCancSenha);

        pcbContext = (PCBContext) getApplication();

        buttonOkSenha.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonOkSenha.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @SuppressWarnings(\"unchecked\")\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L){
                LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L){", getLocalClassName());
                if (pcbContext.getConfigCTR().getConfig().getSenhaConfig().equals("")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (!pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(pcbContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if(pcbContext.getConfigCTR().verSenha(editTextSenha.getText().toString())) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();

                }
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("} else {", getLocalClassName());
                if (editTextSenha.getText().toString().equals("fgbny946")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (editTextSenha.getText().toString().equals(\"fgbny946\")) {\n" +
                            "Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);", getLocalClassName());
                    Intent it = new Intent(SenhaActivity.this, LogProcessoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

        buttonCancSenha.setOnClickListener(v -> {
            LogProcessoDAO.getInstance().insertLogProcesso("buttonCancSenha.setOnClickListener(new View.OnClickListener() {\n" +
                    "            @Override\n" +
                    "            public void onClick(View v) {", getLocalClassName());
            if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 2L) {\n" +
                        "                    Intent it = new Intent(SenhaActivity.this, MenuInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }
            else if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){
                LogProcessoDAO.getInstance().insertLogProcesso("else if (pcbContext.getConfigCTR().getConfig().getPosicaoTela() == 3L){\n" +
                        "                        Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            } else {
                LogProcessoDAO.getInstance().insertLogProcesso("}else{\n" +
                        "                        Intent it = new Intent(SenhaActivity.this, ListaBagCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(SenhaActivity.this, ListaBagCargaActivity.class);
                startActivity(it);
                finish();
            }
        });

    }
}