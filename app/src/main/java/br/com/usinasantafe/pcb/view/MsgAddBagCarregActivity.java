package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.zxing.CaptureActivity;

public class MsgAddBagCarregActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCBContext pcbContext;
    private TextView textViewMsgBag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_add_bag_carreg);

        pcbContext = (PCBContext) getApplication();
        textViewMsgBag = findViewById(R.id.textViewMsgBag);
        Button buttonMsgBagNao = findViewById(R.id.buttonMsgBagNao);
        Button buttonMsgBagSim = findViewById(R.id.buttonMsgBagSim);

        LogProcessoDAO.getInstance().insertLogProcesso("msgBag();", getLocalClassName());
        msgBag();

        buttonMsgBagNao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgBagNao.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(MsgAddBagCarregActivity.this, ListaBagCarregActivity.class);", getLocalClassName());
                Intent it = new Intent(MsgAddBagCarregActivity.this, ListaBagCarregActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonMsgBagSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("buttonMsgBagSim.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                int qtdeRest = pcbContext.getCarregCTR().qtdeRestItemCarreg();", getLocalClassName());
                if(pcbContext.getCarregCTR().qtdeRestItemCarreg() > 0){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(qtdeRest > 0){\n" +
                            "                    Intent it = new Intent(MsgAddBagCarregActivity.this, CaptureActivity.class);\n" +
                            "                    startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
                    Intent it = new Intent(MsgAddBagCarregActivity.this, CaptureActivity.class);
                    startActivityForResult(it, REQUEST_CODE);
                }
                else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {" +
                            "AlertDialog.Builder alerta = new AlertDialog.Builder(MenuInicialActivity.this);\n" +
                            "                        alerta.setTitle(\"ATENÇÃO\");\n" +
                            "                        alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                            "                        alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                            "                            @Override\n" +
                            "                            public void onClick(DialogInterface dialog, int which) {\n" +
                            "                            }\n" +
                            "                        });\n" +
                            "                        alerta.show()", getLocalClassName());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MsgAddBagCarregActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("CARGA COMPLETA! POR FAVOR, FINALIZE A CARREGAMENTO CLICANDO NO BOTÃO 'NÃO'.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alerta.show();
                }

            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        LogProcessoDAO.getInstance().insertLogProcesso("public void onActivityResult(int requestCode, int resultCode, Intent data){", getLocalClassName());
        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            LogProcessoDAO.getInstance().insertLogProcesso("if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){\n" +
                    "            String codBarraBag = data.getStringExtra(\"SCAN_RESULT\");\n" +
                    "            pcbContext.setCodBarraBagLido(codBarraBag);\n" +
                    "            msgBag();", getLocalClassName());
            String codBarraBag = data.getStringExtra("SCAN_RESULT");
            pcbContext.setCodBarraBagLido(codBarraBag);
            msgBag();
        }

    }

    public void msgBag(){

        LogProcessoDAO.getInstance().insertLogProcesso("public void msgBag(){\n" +
                "        String msgBag = \"\";", getLocalClassName());
        String msgBag = "";

        if(pcbContext.getCarregCTR().verBagCarregCodBarra(pcbContext.getCodBarraBagLido())){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().verBagCarregCodBarra(pcbContext.getCodBarraBagLido())){\n" +
                    "            pcbContext.getCarregCTR().inserirItemCarreg(pcbContext.getCodBarraBagLido());\n" +
                    "            msgBag = msgBag + \"NUMERAÇÃO DE BAG \" + pcbContext.getCodBarraBagLido();", getLocalClassName());
            pcbContext.getCarregCTR().inserirItemCarreg(pcbContext.getCodBarraBagLido(), getLocalClassName());
            msgBag = msgBag + "NUMERAÇÃO DE BAG " + pcbContext.getCodBarraBagLido();
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            msgBag = msgBag + \"NUMERAÇÃO DE BAG INVÁLIDA!\";", getLocalClassName());
            msgBag = msgBag + "NUMERAÇÃO DE BAG INVÁLIDA!";
        }


        if(pcbContext.getCarregCTR().qtdeRestItemCarreg() == 0){
            LogProcessoDAO.getInstance().insertLogProcesso("if(pcbContext.getCarregCTR().qtdeRestItemCarreg() == 0){\n" +
                    "            msgBag = msgBag + \"\\nCARGA COMPLETA!\" +\n" +
                    "                    \"\\nPOR FAVOR, FINALIZE A CARREGAMENTO CLICANDO NO BOTÃO 'NÃO'.\";", getLocalClassName());
            msgBag = msgBag + "\nCARGA COMPLETA!" +
                    "\nPOR FAVOR, FINALIZE A CARREGAMENTO CLICANDO NO BOTÃO 'NÃO'.";
        }
        else{
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "            msgBag = msgBag + \"\\nFALTA \" + qtdeRest + \" BAG(S) PARA FECHA O CARREGAMENTO.\" +\n" +
                    "                    \"\\nDESEJA ADICIONAR MAIS UM BAG NA CARGA?\";", getLocalClassName());
            msgBag = msgBag + "\nFALTA " + pcbContext.getCarregCTR().qtdeRestItemCarreg() + " BAG(S) PARA FECHA O CARREGAMENTO." +
                    "\nDESEJA ADICIONAR MAIS UM BAG NA CARGA?";
        }

        LogProcessoDAO.getInstance().insertLogProcesso("textViewMsgBag.setText(msgBag);", getLocalClassName());
        textViewMsgBag.setText(msgBag);

    }

}