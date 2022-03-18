package br.com.usinasantafe.pcb.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcb.control.ConfigCTR;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.model.dao.OrdemCarregDAO;
import br.com.usinasantafe.pcb.util.connHttp.PostVerGenerico;
import br.com.usinasantafe.pcb.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pcb.view.TelaInicialActivity;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String tipo;
    private TelaInicialActivity telaInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status;

    public VerifDadosServ() {
    }

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result, String activity) {

        ConfigCTR configCTR = new ConfigCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("public void manipularDadosHttp(String result) {", activity);
        if (this.tipo.equals("Atualiza")) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (this.tipo.equals(\"Atualiza\")) {\n" +
                    "            configCTR.recAtual(result.trim());\n" +
                    "            status = 3;\n" +
                    "            this.telaInicialActivity.goMenuInicial();", activity);
            configCTR.recAtual(result.trim());
            status = 3;
            this.telaInicialActivity.atualizarOrdemCarreg();
        } else if (this.tipo.equals("OrdemCarreg")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OrdemCarreg\")) {\n" +
                    "            OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();\n" +
                    "            ordemCarregDAO.atualDados(result, activity);\n" +
                    "            status = 3;\n" +
                    "            this.telaInicialActivity.goMenuInicial();", activity);
            OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
            ordemCarregDAO.atualDados(result, activity);
            status = 3;
            this.telaInicialActivity.goMenuInicial();

        } else {
            LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                    "            status = 1;", activity);
            status = 1;
        }

    }

    public Boolean statusRetVerif() {
        boolean ret = false;
        ConfigCTR configCTR = new ConfigCTR();
        if(configCTR.hasElemConfig()){
            if(configCTR.getStatusRetVerif() == 1){
                ret = true;
            }
        }
        return ret;
    }

    public void verifAtualAplic(String dados, TelaInicialActivity telaInicialActivity, String activity) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "Atualiza";
        this.dados = dados;
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void atualDados(TelaInicialActivity telaInicialActivity, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.tipo = "OrdemCarreg";
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(tipo), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(tipo) + "'); - Dados de Envio = " + this.dados, activity);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

    public void reenvioVerif(String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("statusRetVerif()", activity);
        if(statusRetVerif()){
            LogProcessoDAO.getInstance().insertLogProcesso("envioVerif()", activity);
            envioVerif(activity);
        }
    }

    public void cancel() {
        status = 3;
        if (postVerGenerico.getStatus() == AsyncTask.Status.RUNNING) {
            postVerGenerico.cancel(true);
        }
    }

    public void pulaTela(){
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void msg(String texto){
        if(status < 3){
            status = 3;
            this.progressDialog.dismiss();
            AlertDialog.Builder alerta = new AlertDialog.Builder(telaAtual);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage(texto);
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alerta.show();
        }
    }

    public void pulaTelaSemBarra(){
        if(status < 3){
            status = 3;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public void pulaTelaSemBarra(Class telaProx){
        if(status < 3){
            status = 3;
            Intent it = new Intent(telaAtual, telaProx);
            telaAtual.startActivity(it);
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
