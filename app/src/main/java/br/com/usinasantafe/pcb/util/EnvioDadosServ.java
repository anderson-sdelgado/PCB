package br.com.usinasantafe.pcb.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcb.control.CargaCTR;
import br.com.usinasantafe.pcb.control.TransfCTR;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.connHttp.PostCadGenerico;
import br.com.usinasantafe.pcb.util.connHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pcb.view.ActivityGeneric;

public class EnvioDadosServ {

    private static EnvioDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviado; 3 - Todos os Dados Foram Enviados;

    public EnvioDadosServ() {
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static EnvioDadosServ getInstance() {
        if (instance == null) {
            instance = new EnvioDadosServ();
        }
        return instance;
    }

    ////////////////////////////////// ENVIAR DADOS ///////////////////////////////////////////////

    public void enviarCabecCargaFechado(String activity) {

        CargaCTR cargaCTR = new CargaCTR();
        Log.i("PCB","envio(urlsConexaoHttp.getsInsertCarga() = " + urlsConexaoHttp.getsInsertCarga() + " , cargaCTR.dadosEnvioCabecCargaFechado() = " + cargaCTR.dadosEnvioCabecCargaFechado() + " , activity);");
        LogProcessoDAO.getInstance().insertLogProcesso("envio(urlsConexaoHttp.getsInsertCarga(), cargaCTR.dadosEnvioCabecCargaFechado(), activity);", activity);
        envio(urlsConexaoHttp.getsInsertCarga(), cargaCTR.dadosEnvioCabecCargaFechado(), activity);

    }

    public void enviarCabecTransfFechado(String activity) {

        TransfCTR transfCTR = new TransfCTR();
        Log.i("PCB","envio(urlsConexaoHttp.getsInsertTransf() = " + urlsConexaoHttp.getsInsertTransf() + " , transfCTR.dadosEnvioCabecTransfFechado() = " + transfCTR.dadosEnvioCabecTransfFechado() + ", activity);");
        LogProcessoDAO.getInstance().insertLogProcesso("envio(urlsConexaoHttp.getsInsertTransf(), transfCTR.dadosEnvioCabecTransfFechado(), activity);", activity);
        envio(urlsConexaoHttp.getsInsertTransf(), transfCTR.dadosEnvioCabecTransfFechado(), activity);

    }

    public void envio(String url, String dados, String activity){

        String[] strings = {url, activity};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);
        LogProcessoDAO.getInstance().insertLogProcesso("postCadGenerico.execute('" + url + "'); - Dados de Envio = " + dados, activity);
        PostCadGenerico postCadGenerico = new PostCadGenerico();
        postCadGenerico.setParametrosPost(parametrosPost);
        postCadGenerico.execute(strings);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////VERIFICAÇÃO DE DADOS/////////////////////////////////////////

    public boolean verifCabecCargaFechado() {
        CargaCTR cargaCTR = new CargaCTR();
        return cargaCTR.verCabecCargaFechado();
    }

    public boolean verifCabecTransfFechado() {
        TransfCTR transfCTR = new TransfCTR();
        return transfCTR.verCabecTransfFechado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(String activity) {
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifCabecCargaFechado()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecFechado()) {\n" +
                        "enviarCabecFechado(activity);", activity);
                enviarCabecCargaFechado(activity);
            } else {
                if (verifCabecTransfFechado()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecFechado()) {\n" +
                            "enviarCabecFechado(activity);", activity);
                    enviarCabecTransfFechado(activity);
                } else {
                    status = 3;
                }
            }
        } else{
            status = 3;
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifCabecCargaFechado())
                && (!verifCabecTransfFechado())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(String " + result + ", String activity){", activity);
        if (result.trim().startsWith("CARGA")) {
            LogProcessoDAO.getInstance().insertLogProcesso("if (result.trim().startsWith(\"CARGA\")) {\n" +
                    "            CargaCTR cargaCTR = new CargaCTR();\n" +
                    "            cargaCTR.updCabecCarga(result, activity);", activity);
            CargaCTR cargaCTR = new CargaCTR();
            cargaCTR.updCabecCarga(result, activity);
        }
        else if (result.trim().startsWith("TRANSF")) {
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"TRANSF\")) {\n" +
                    "            TransfCTR transfCTR = new TransfCTR();\n" +
                    "            transfCTR.updCabecTransf(result, activity);", activity);
            TransfCTR transfCTR = new TransfCTR();
            transfCTR.updCabecTransf(result, activity);
        }
        else {
            LogProcessoDAO.getInstance().insertLogProcesso("else {\n" +
                    "            status = 1;", activity);
            status = 1;
            LogErroDAO.getInstance().insertLogErro(result);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
