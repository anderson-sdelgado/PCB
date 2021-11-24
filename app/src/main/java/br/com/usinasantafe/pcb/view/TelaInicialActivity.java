package br.com.usinasantafe.pcb.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.EnvioDadosServ;
import br.com.usinasantafe.pcb.util.VerifDadosServ;

public class TelaInicialActivity extends ActivityGeneric {

    private PCBContext pcbContext;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        pcbContext = (PCBContext) getApplication();

        customHandler.postDelayed(excluirBDThread, 0);

    }


    public void goMenuInicial(){

        LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread);", getLocalClassName());
        customHandler.removeCallbacks(encerraAtualThread);
        LogProcessoDAO.getInstance().insertLogProcesso("}\n" +
                "        else{\n" +
                "Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);", getLocalClassName());
        Intent it = new Intent(TelaInicialActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();

    }

    public void atualizarAplic(){
        LogProcessoDAO.getInstance().insertLogProcesso("public void atualizarAplic(){", getLocalClassName());
        if (connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {", getLocalClassName());
            if (pcbContext.getConfigCTR().hasElemConfig()) {
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().hasElemConfig()\n" +
                        "                customHandler.postDelayed(updateTimerThread, 10000);", getLocalClassName());
                customHandler.postDelayed(encerraAtualThread, 10000);
                LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().verAtualAplic(pmmContext.versaoAplic, this, getLocalClassName());", getLocalClassName());
                pcbContext.getConfigCTR().verAtualAplic(pcbContext.versaoAplic, this, getLocalClassName());
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "                VerifDadosServ.status = 3;\n" +
                        "goMenuInicial();", getLocalClassName());
                VerifDadosServ.status = 3;
                goMenuInicial();
            }
        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                    "                VerifDadosServ.status = 3;\n" +
                    "goMenuInicial();", getLocalClassName());
            VerifDadosServ.status = 3;
            goMenuInicial();
        }
    }

    public void clearBD() {
        LogProcessoDAO.getInstance().insertLogProcesso("pmmContext.getConfigCTR().deleteLogs();", getLocalClassName());
        pcbContext.getConfigCTR().deleteLogs();
    }

    private Runnable encerraAtualThread = new Runnable() {

        public void run() {
            LogProcessoDAO.getInstance().insertLogProcesso("    private Runnable updateTimerThread = new Runnable() {\n" +
                    "        public void run() {", getLocalClassName());
            LogProcessoDAO.getInstance().insertLogProcesso("verifEnvio();", getLocalClassName());
            if(VerifDadosServ.status < 3) {
                LogProcessoDAO.getInstance().insertLogProcesso("if(VerifDadosServ.status < 3) {\n" +
                        "VerifDadosServ.getInstance().cancel();", getLocalClassName());
                VerifDadosServ.getInstance().cancel();
            }
            LogProcessoDAO.getInstance().insertLogProcesso("goMenuInicial();", getLocalClassName());
            goMenuInicial();
        }
    };

    private Runnable excluirBDThread = new Runnable() {

        public void run() {

            LogProcessoDAO.getInstance().insertLogProcesso("clearBD();", getLocalClassName());
            clearBD();

            if(EnvioDadosServ.getInstance().verifDadosEnvio()){
                LogProcessoDAO.getInstance().insertLogProcesso("EnvioDadosServ.getInstance().verifDadosEnvio()", getLocalClassName());
                if(connectNetwork){
                    LogProcessoDAO.getInstance().insertLogProcesso("if(connectNetwork){\n" +
                            "EnvioDadosServ.getInstance().envioDados()", getLocalClassName());
                    EnvioDadosServ.getInstance().envioDados(getLocalClassName());
                }
                else{
                    LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                            "                EnvioDadosServ.status = 1;", getLocalClassName());
                    EnvioDadosServ.status = 1;
                }
            }
            else{
                LogProcessoDAO.getInstance().insertLogProcesso("else{\n" +
                        "            EnvioDadosServ.status = 3;", getLocalClassName());
                EnvioDadosServ.status = 3;
            }

            LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.status = 3;", getLocalClassName());
            VerifDadosServ.status = 3;

            LogProcessoDAO.getInstance().insertLogProcesso("atualizarAplic()", getLocalClassName());
            atualizarAplic();

        }
    };


}