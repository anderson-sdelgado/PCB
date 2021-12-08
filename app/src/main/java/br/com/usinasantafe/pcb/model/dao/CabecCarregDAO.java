package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCarregBean;
import br.com.usinasantafe.pcb.util.Tempo;

public class CabecCarregDAO {

    private CabecCarregBean cabecCarregBean;

    public CabecCarregDAO() {
    }

    public CabecCarregBean getCabecCargaBean() {
        if (cabecCarregBean == null)
            cabecCarregBean = new CabecCarregBean();
        return cabecCarregBean;
    }

    public void salvarCabecAberto(){
        cabecCarregBean.setDthrCabecCarreg(Tempo.getInstance().dthr());
        cabecCarregBean.setStatusCabecCarreg(1L);
        cabecCarregBean.insert();
    }

    public CabecCarregBean getCabecAberto() {
        List<CabecCarregBean> cabecCarregList = cabecCarregAbertoList();
        CabecCarregBean cabecCarregBean = (CabecCarregBean) cabecCarregList.get(0);
        cabecCarregList.clear();
        return cabecCarregBean;
    }

    public boolean verCabecAberto(){
        List<CabecCarregBean> cabecList = cabecCarregAbertoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public boolean verCabecFechado(){
        List<CabecCarregBean> cabecList = cabecCarregFechadoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public ArrayList<Long> idCabecArrayList(List<CabecCarregBean> cabecList){
        ArrayList<Long> idCabecList = new ArrayList<Long>();
        for (CabecCarregBean cabecCarregBean : cabecList) {
            idCabecList.add(cabecCarregBean.getIdCabecCarreg());
        }
        cabecList.clear();
        return idCabecList;
    }

    public List<CabecCarregBean> cabecCarregAbertoList(){
        CabecCarregBean cabecCarregBean = new CabecCarregBean();
        return cabecCarregBean.get("statusCabecCarreg", 1L);
    }

    public List<CabecCarregBean> cabecCarregFechadoList(){
        CabecCarregBean cabecCarregBean = new CabecCarregBean();
        return cabecCarregBean.get("statusCabecCarreg", 2L);
    }

    public String dadosEnvioCabecAberto(){
        return dadosEnvioCabec(cabecCarregAbertoList());
    }

    public String dadosEnvioCabecFechado(){
        return dadosEnvioCabec(cabecCarregFechadoList());
    }

    private String dadosEnvioCabec(List<CabecCarregBean> cabecList){

        JsonArray cabecJsonArray = new JsonArray();

        for (CabecCarregBean cabecCarregBean : cabecList) {
            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecCarregBean, cabecCarregBean.getClass()));
        }

        cabecList.clear();

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", cabecJsonArray);

        return cabecJsonObj.toString();
    }

    public void updateCabecFechado(String object) throws Exception {

        JSONObject cabecJsonObj = new JSONObject(object);
        JSONArray cabecJsonArray = cabecJsonObj.getJSONArray("cabec");

        for (int i = 0; i < cabecJsonArray.length(); i++) {

            JSONObject objBol = cabecJsonArray.getJSONObject(i);
            Gson gsonBol = new Gson();
            CabecCarregBean cabecCarregBean = gsonBol.fromJson(objBol.toString(), CabecCarregBean.class);

            List<CabecCarregBean> cabecList = cabecCarregBean.get("idCabecCarreg", cabecCarregBean.getIdCabecCarreg());
            CabecCarregBean cabecCarregBD = cabecList.get(0);
            cabecList.clear();

            if(cabecCarregBD.getStatusCabecCarreg() == 2L){
                cabecCarregBD.setStatusCabecCarreg(3L);
                cabecCarregBD.update();
            }

        }

    }

    public ArrayList<String> cabecAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEÇALHO");
        CabecCarregBean cabecCarregBean = new CabecCarregBean();
        List<CabecCarregBean> cabecCarregList = cabecCarregBean.orderBy("idCabecCarreg", true);
        for (CabecCarregBean cabecCarregBD : cabecCarregList) {
            dadosArrayList.add(dadosBolMMFert(cabecCarregBD));
        }
        cabecCarregList.clear();
        return dadosArrayList;
    }

    private String dadosBolMMFert(CabecCarregBean cabecCarregBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecCarregBean, cabecCarregBean.getClass()).toString();
    }

}
