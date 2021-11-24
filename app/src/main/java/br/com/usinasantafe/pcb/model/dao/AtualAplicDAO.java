package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.com.usinasantafe.pcb.model.bean.AtualAplicBean;

public class AtualAplicDAO {

    public AtualAplicDAO() {
    }

    public String dadosVerAtualAplicBean(Long nroAparelho, String versaoAplic){

        AtualAplicBean atualAplicBean = new AtualAplicBean();
        atualAplicBean.setVersaoAtual(versaoAplic);
        atualAplicBean.setNroAparelhoAtual(nroAparelho);

        JsonArray jsonArray = new JsonArray();

        Gson gson = new Gson();
        jsonArray.add(gson.toJsonTree(atualAplicBean, atualAplicBean.getClass()));

        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);

        return json.toString();
    }

}
