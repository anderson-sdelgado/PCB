package br.com.usinasantafe.pcb.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.usinasantafe.pcb.model.bean.AtualAplicBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pcb.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pcb.model.dao.ConfigDAO;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.AtualDadosServ;
import br.com.usinasantafe.pcb.util.VerifDadosServ;
import br.com.usinasantafe.pcb.view.TelaInicialActivity;

public class ConfigCTR {

    public boolean hasElemConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public boolean getConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig(senha);
    }

    public void setPosicaoTela(Long posicaoTela){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setPosicaoTela(posicaoTela);
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setStatusRetVerif(statusRetVerif);
    }

    public Long getStatusRetVerif(){
        ConfigBean configBean = getConfig();
        return configBean.getStatusRetVerif();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);", activity);
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        ArrayList classeArrayList = new ArrayList();
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = new ArrayList();\n" +
                "        switch (" + tipoAtual + ") {", activity);
        switch (tipoAtual) {
            case "Func":
                classeArrayList.add("FuncBean");
                break;
            case "OrdemCarga":
                classeArrayList.add("OrdemCargaBean");
                classeArrayList.add("BagCargaBean");
                break;
        }
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// ATUALIZAR APLIC /////////////////////////////////////////

    public AtualAplicBean recAtual(String result) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                ConfigDAO configDAO = new ConfigDAO();
                atualAplicBean = configDAO.recAtual(jsonArray);
            }

        } catch (Exception e) {
            VerifDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }
        return atualAplicBean;
    }

    public void verAtualAplic(String versaoAplic, TelaInicialActivity telaInicialActivity, String activity) {
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(equipBean.getNroEquip(), equipBean.getIdCheckList(), versaoAplic)\n" +
                "                , telaInicialActivity, progressDialog);", activity);
        VerifDadosServ.getInstance().verifAtualAplic(atualAplicDAO.dadosVerAtualAplicBean(getConfig().getNroAparelhoConfig(), versaoAplic)
                , telaInicialActivity, activity);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////// LOG ///////////////////////////////////////////////

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
