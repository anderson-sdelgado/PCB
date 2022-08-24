package br.com.usinasantafe.pcb.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.AtualAplicBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.SafraBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.LogErroBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.LogProcessoBean;
import br.com.usinasantafe.pcb.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pcb.model.dao.CabecCargaDAO;
import br.com.usinasantafe.pcb.model.dao.CabecTransfDAO;
import br.com.usinasantafe.pcb.model.dao.ConfigDAO;
import br.com.usinasantafe.pcb.model.dao.FuncDAO;
import br.com.usinasantafe.pcb.model.dao.ItemCargaDAO;
import br.com.usinasantafe.pcb.model.dao.ItemTransfDAO;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.model.dao.SafraDAO;
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

    public void setTipoApont(Long tipoApont) {
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setTipoApont(tipoApont);
    }

    public Long getStatusRetVerif(){
        ConfigBean configBean = getConfig();
        return configBean.getStatusRetVerif();
    }

    public boolean verSenha(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.verSenha(senha);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho, senha);
    }

    public void setIdSafra(){
        ConfigDAO configDAO = new ConfigDAO();
        SafraDAO safraDAO = new SafraDAO();
        configDAO.setIdSafra(safraDAO.idSafraAtual());
    }

    public void setIdSafra(Long idSafra){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.setIdSafra(idSafra);
    }

    public SafraBean getSafraBean(){
        SafraDAO safraDAO = new SafraDAO();
        return safraDAO.getSafraBean(getConfig().getIdSafra());
    }

    public List<SafraBean> safraList(){
        SafraDAO safraDAO = new SafraDAO();
        return safraDAO.safraList();
    }

    /////////////////////////////////// FUNCIONARIOS //////////////////////////////////////////////

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFuncMatric(matricFunc);
    }

    public FuncBean getFuncMatric(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matricFunc);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////// ATUALIZAR DADOS ////////////////////////////////////////

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog, String activity){
        LogProcessoDAO.getInstance().insertLogProcesso("AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);", activity);
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog, activity);
    }

    public void atualDados(Context telaAtual, Class telaProx, String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeArrayList(tipoAtual, activity);\n" +
                "AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeArrayList(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, classeArrayList, tipoReceb, activity);
    }

    public void atualDados(Context telaAtual, Class telaProx, ProgressDialog progressDialog, String tipoAtual, int tipoReceb, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = classeArrayList(tipoAtual, activity);\n" +
                "AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);", activity);
        ArrayList classeArrayList = classeArrayList(tipoAtual, activity);
        AtualDadosServ.getInstance().atualGenericoBD(telaAtual, telaProx, progressDialog, classeArrayList, tipoReceb, activity);
    }

    public void atualDados(TelaInicialActivity telaInicialActivity, String activity) {
        LogProcessoDAO.getInstance().insertLogProcesso("VerifDadosServ.getInstance().atualDados(telaInicialActivity, activity);", activity);
        VerifDadosServ.getInstance().atualDados(telaInicialActivity, activity);
    }

    private ArrayList classeArrayList(String tipoAtual, String activity){
        ArrayList classeArrayList = new ArrayList();
        LogProcessoDAO.getInstance().insertLogProcesso("ArrayList classeArrayList = new ArrayList();\n" +
                "        switch (" + tipoAtual + ") {", activity);
        switch (tipoAtual) {
            case "Func":
                classeArrayList.add("FuncBean");
                break;
            case "OrdemCarga":
                classeArrayList.add("OrdemCargaBean");
                break;
            case "Bag":
                classeArrayList.add("BagBean");
                break;
            case "Safra":
                classeArrayList.add("SafraBean");
                break;
        }
        return classeArrayList;
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

    public List<LogProcessoBean> logProcessoList(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        return logProcessoDAO.logProcessoList();
    }

    public ArrayList<String> logBaseDadoList(){
        ArrayList<String> dadosArrayList = new ArrayList<>();
        CabecCargaDAO cabecCargaDAO = new CabecCargaDAO();
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        ItemCargaDAO itemCargaDAO = new ItemCargaDAO();
        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        dadosArrayList = cabecCargaDAO.cabecCargaAllArrayList(dadosArrayList);
        dadosArrayList = itemCargaDAO.itemCargaAllArrayList(dadosArrayList);
        dadosArrayList = cabecTransfDAO.cabecTransfAllArrayList(dadosArrayList);
        dadosArrayList = itemTransfDAO.itemTransfAllArrayList(dadosArrayList);
        return dadosArrayList;
    }

    public List<LogErroBean> logErroList(){
        LogErroDAO logErroDAO = new LogErroDAO();
        return logErroDAO.logErroBeanList();
    }

    public void deleteLogs(){
        LogProcessoDAO logProcessoDAO = new LogProcessoDAO();
        LogErroDAO logErroDAO = new LogErroDAO();
        logProcessoDAO.deleteLogProcesso();
        logErroDAO.deleteLogErro();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////


}
