package br.com.usinasantafe.pcb.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.control.CargaCTR;
import br.com.usinasantafe.pcb.control.ConfigCTR;
import br.com.usinasantafe.pcb.control.TransfCTR;
import br.com.usinasantafe.pcb.model.dao.BagDAO;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.model.dao.OrdemCargaDAO;
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
    private String classe;
    private TelaInicialActivity telaInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status;
    private MediaPlayer mp;

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
        if (this.classe.equals("Atualiza")) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (this.tipo.equals(\"Atualiza\")) {\n" +
                    "            configCTR.recAtual(result.trim());\n" +
                    "            status = 3;\n" +
                    "            this.telaInicialActivity.goMenuInicial();", activity);
            configCTR.recAtual(result.trim());
            status = 3;
            this.telaInicialActivity.atualizarOrdemCarreg();
        } else if (this.classe.equals("OrdemCarreg")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OrdemCarreg\")) {\n" +
                    "            OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();\n" +
                    "            ordemCarregDAO.atualDados(result, activity);\n" +
                    "            status = 3;\n" +
                    "            this.telaInicialActivity.goMenuInicial();", activity);
            OrdemCargaDAO ordemCargaDAO = new OrdemCargaDAO();
            ordemCargaDAO.atualDados(result, activity);
            status = 3;
            this.telaInicialActivity.goMenuInicial();
        } else if (this.classe.equals("BagTransfCod") || this.classe.equals("BagTransfNro")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OrdemCarreg\")) {\n" +
                    "            OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();\n" +
                    "            ordemCarregDAO.atualDados(result, activity);\n" +
                    "            status = 3;", activity);
            TransfCTR transfCTR = new TransfCTR();
            transfCTR.receberVerifBag(result);
            status = 3;
        } else if (this.classe.equals("BagCargaCod") || this.classe.equals("BagCargaNro")) {
            LogProcessoDAO.getInstance().insertLogProcesso("} else if (this.tipo.equals(\"OrdemCarreg\")) {\n" +
                    "            OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();\n" +
                    "            ordemCarregDAO.atualDados(result, activity);\n" +
                    "            status = 3;", activity);
            CargaCTR cargaCTR = new CargaCTR();
            cargaCTR.receberVerifBag(result);
            status = 3;
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
        this.classe = "Atualiza";
        this.dados = dados;
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void verifDados(String dados, String classe, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.telaAtual = telaAtual;
        this.telaProx = telaProx;
        this.progressDialog = progressDialog;
        this.classe = classe;
        this.dados = dados;

        envioVerif(activity);

    }

    public void atualDados(TelaInicialActivity telaInicialActivity, String activity) {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        this.classe = "OrdemCarreg";
        this.telaInicialActivity = telaInicialActivity;

        envioVerif(activity);

    }

    public void envioVerif(String activity) {

        status = 2;
        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(classe), activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        LogProcessoDAO.getInstance().insertLogProcesso("postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados, activity);
        Log.i("PCB", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
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
            if(this.classe.equals("BagTransf") || this.classe.equals("BagCarga")){
                mp = MediaPlayer.create(telaAtual, R.raw.beep);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(mp != null) {
                            mp.release();
                        }
                    }
                });
                mp.start();
            }
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
