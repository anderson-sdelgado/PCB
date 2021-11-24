package br.com.usinasantafe.pcb.util;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
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


    public void enviarBolFechadoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolFechadoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolFechadoMMFert(), motoMecFertCTR.dadosEnvioBolFechadoMMFert(), activity);

    }

    public void enviarBolAbertoMMFert(String activity) {

        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();

        LogProcessoDAO.getInstance().insertLogProcesso("motoMecFertCTR.dadosEnvioBolAbertoMMFert()", activity);
        envio(urlsConexaoHttp.getsInsertBolAbertoMMFert(), motoMecFertCTR.dadosEnvioBolAbertoMMFert(), activity);

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

    public Boolean verifBolFechadoMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioBolFech();
    }

    public Boolean verifBolAbertoMMFert() {
        MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
        return motoMecFertCTR.verEnvioApont();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void envioDados(String activity) {
        status = 1;
        if(ActivityGeneric.connectNetwork) {
            LogProcessoDAO.getInstance().insertLogProcesso("ActivityGeneric.connectNetwork", activity);
            status = 2;
            if (verifBolFechadoMMFert()) {
                LogProcessoDAO.getInstance().insertLogProcesso("if (verifBolFechadoMMFert()) {", activity);
                LogProcessoDAO.getInstance().insertLogProcesso("enviarBolFechadoMMFert()", activity);
                enviarBolFechadoMMFert(activity);
            } else {
                if (verifBolAbertoMMFert()) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (verifBolAbertoMMFert()) {", activity);
                    LogProcessoDAO.getInstance().insertLogProcesso("enviarBolAbertoMMFert()", activity);
                    enviarBolAbertoMMFert(activity);
                } else {
                    status = 3;
                }
            }
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifBolFechadoMMFert())){
            return false;
        } else {
            return true;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////MECANISMO DE ENVIO/////////////////////////////////////////

    public void recDados(String result, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("public void recDados(String " + result + ", String activity){", activity);
        else if (result.trim().startsWith("BOLABERTOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLABERTOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.updBolAberto(result)", activity);
            motoMecFertCTR.updBolAberto(result, activity);
        }
        else if (result.trim().startsWith("BOLFECHADOMM")) {
            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();
            LogProcessoDAO.getInstance().insertLogProcesso("else if (result.trim().startsWith(\"BOLFECHADOMM\")) {\n" +
                    "            MotoMecFertCTR motoMecFertCTR = new MotoMecFertCTR();\n" +
                    "motoMecFertCTR.delBolFechado(result)", activity);
            motoMecFertCTR.updateBolEnviado(result, activity);
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
