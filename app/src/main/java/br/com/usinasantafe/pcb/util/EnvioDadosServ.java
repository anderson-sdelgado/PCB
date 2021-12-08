package br.com.usinasantafe.pcb.util;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcb.control.CarregCTR;
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


    public void enviarCabecFechado(String activity) {

        CarregCTR carregCTR = new CarregCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), carregCTR.dadosEnvioCabecFechado(), activity);", activity);
        envio(urlsConexaoHttp.getsInsertCarreg(), carregCTR.dadosEnvioCabecFechado(), activity);

    }

    public void enviarCabecAberto(String activity) {

        CarregCTR carregCTR = new CarregCTR();
        LogProcessoDAO.getInstance().insertLogProcesso("envio(urlsConexaoHttp.getsInsertCabecAberto(), carregCTR.dadosEnvioCabecAberto(), activity);", activity);
        envio(urlsConexaoHttp.getsInsertCarreg(), carregCTR.dadosEnvioCabecAberto(), activity);

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

    public boolean verifCabecAberto() {
        CarregCTR carregCTR = new CarregCTR();
        return carregCTR.verCabecAberto();
    }

    public boolean verifCabecFechado() {
        CarregCTR carregCTR = new CarregCTR();
        return carregCTR.verCabecFechado();
    }

    public boolean verifItemAberto() {
        CarregCTR carregCTR = new CarregCTR();
        return carregCTR.verItemCarregNEnviado();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(String activity) {
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifCabecFechado()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecFechado()) {\n" +
                        "enviarBolFechadoMMFert()", activity);
                enviarCabecFechado(activity);
            } else {
                if (verifCabecAberto()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (verifCabecAberto()) {\n" +
                            "enviarBolAbertoMMFert()", activity);
                    enviarCabecAberto(activity);
                } else {
                    status = 3;
                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifItemAberto())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(String " + result + ", String activity){", activity);
        if (result.trim().startsWith("RETORNO")) {
            CarregCTR carregCTR = new CarregCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("if (result.trim().startsWith(\"RETORNO\")) {\n" +
                    "            CarregCTR carregCTR = new CarregCTR();\n" +
                    "carregCTR.updCabec(result, activity);", activity);
            carregCTR.updCabec(result, activity);
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
