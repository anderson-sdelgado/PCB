package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.AtualAplicBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ConfigBean;


public class ConfigDAO {

    public ConfigDAO() {
    }

    public boolean hasElements(){
        ConfigBean configBean = new ConfigBean();
        return configBean.hasElements();
    }

    public ConfigBean getConfig(){
        List<ConfigBean> configList = configList();
        ConfigBean configBean = configList.get(0);
        configList.clear();
        return configBean;
    }

    public boolean getConfig(String senha){
        List<ConfigBean> configList = configList(senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    private List<ConfigBean> configList(){
        ConfigBean configBean = new ConfigBean();
        return configBean.all();
    }

    private List<ConfigBean> configList(String senha){
        ConfigBean configBean = new ConfigBean();
        return configBean.get("senhaConfig", senha);
    }

    public void salvarConfig(Long nroAparelho, String senha){
        ConfigBean configBean = new ConfigBean();
        configBean.deleteAll();
        configBean.setNroAparelhoConfig(nroAparelho);
        configBean.setSenhaConfig(senha);
        configBean.setTipoApont(0L);
        configBean.insert();
        configBean.commit();
    }

    public boolean verSenha(String senha){
        ConfigBean configBean = new ConfigBean();
        List<ConfigBean> configList = configBean.get("senhaConfig", senha);
        boolean ret = configList.size() > 0;
        configList.clear();
        return ret;
    }

    public void setPosicaoTela(Long posicaoTela){
        if(hasElements()){
            ConfigBean configBean = getConfig();
            configBean.setPosicaoTela(posicaoTela);
            configBean.update();
        }
        else{
            ConfigBean configBean = new ConfigBean();
            configBean.setPosicaoTela(posicaoTela);
            configBean.setSenhaConfig("");
            configBean.insert();
            configBean.commit();
        }
    }

    public void setTipoApont(Long tipoApont) {
        ConfigBean configBean = getConfig();
        configBean.setTipoApont(tipoApont);
        configBean.update();
    }

    public void setStatusRetVerif(Long statusRetVerif){
        ConfigBean configBean = getConfig();
        configBean.setStatusRetVerif(statusRetVerif);
        configBean.update();
    }

    public AtualAplicBean recAtual(JSONArray jsonArray) throws JSONException {

        JSONObject objeto = jsonArray.getJSONObject(0);
        Gson gson = new Gson();
        AtualAplicBean atualAplicBean = gson.fromJson(objeto.toString(), AtualAplicBean.class);

        ConfigBean configBean = getConfig();
        configBean.setDthrServConfig(atualAplicBean.getDthr());
        configBean.update();

        return atualAplicBean;

    }

}
