package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCargaBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.CabecTransfBean;
import br.com.usinasantafe.pcb.util.Tempo;

public class CabecTransfDAO {

    public CabecTransfDAO() {
    }

    public void salvarCabecTransfAberto(Long idFunc){
        Long dthrLong = Tempo.getInstance().dthr();
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        cabecTransfBean.setIdFuncCabecTransf(idFunc);
        cabecTransfBean.setDthrCabecTransf(Tempo.getInstance().dthr(dthrLong));
        cabecTransfBean.setDthrLongCabecTransf(dthrLong);
        cabecTransfBean.setStatusCabecTransf(1L);
        cabecTransfBean.insert();
    }

    public void deleteCabecTransf(Long idCabecTransf){
        List<CabecTransfBean> cabecTransfList = cabecTransfListId(idCabecTransf);
        CabecTransfBean cabecTransfBean = cabecTransfList.get(0);
        cabecTransfBean.delete();
        cabecTransfList.clear();
    }

    public void fecharCabecTransf(){
        CabecTransfBean cabecTransfBean = getCabecTransfAberto();
        cabecTransfBean.setStatusCabecTransf(2L);
        cabecTransfBean.update();
    }

    public void updateCabecTransfFechado(String object) throws Exception {

        JSONObject cabecJsonObj = new JSONObject(object);
        JSONArray cabecJsonArray = cabecJsonObj.getJSONArray("cabec");

        for (int i = 0; i < cabecJsonArray.length(); i++) {

            JSONObject objBol = cabecJsonArray.getJSONObject(i);
            Gson gsonCabec = new Gson();
            CabecTransfBean cabecTransfBean = gsonCabec.fromJson(objBol.toString(), CabecTransfBean.class);

            List<CabecTransfBean> cabecList = cabecTransfBean.get("idCabecTransf", cabecTransfBean.getIdCabecTransf());
            CabecTransfBean cabecTransfBD = cabecList.get(0);
            cabecList.clear();

            if(cabecTransfBD.getStatusCabecTransf() == 2L){
                cabecTransfBD.setStatusCabecTransf(3L);
                cabecTransfBD.update();
            }

        }

    }

    public ArrayList<String> cabecTransfAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEÇALHO TRANSF");
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        List<CabecTransfBean> cabecTransfList = cabecTransfBean.orderBy("idCabecTransf", true);
        for (CabecTransfBean cabecTransfBD : cabecTransfList) {
            dadosArrayList.add(dadosCabecTransf(cabecTransfBD));
        }
        cabecTransfList.clear();
        return dadosArrayList;
    }

    private String dadosCabecTransf(CabecTransfBean cabecTransfBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecTransfBean, cabecTransfBean.getClass()).toString();
    }

    public boolean verCabecTransfAberto(){
        List<CabecTransfBean> cabecList = cabecTransfAbertoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public boolean verCabecTransfFechado(){
        List<CabecTransfBean> cabecList = cabecTransfFechadoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public CabecTransfBean getCabecTransfAberto() {
        List<CabecTransfBean> cabecTransfList = cabecTransfAbertoList();
        CabecTransfBean cabecTransfBean = cabecTransfList.get(0);
        cabecTransfList.clear();
        return cabecTransfBean;
    }

    public CabecTransfBean getCabecTransfFechado() {
        List<CabecTransfBean> cabecTransfList = cabecTransfFechadoList();
        CabecTransfBean cabecTransfBean = cabecTransfList.get(0);
        cabecTransfList.clear();
        return cabecTransfBean;
    }

    public ArrayList<Long> idCabecTransfArrayList(List<CabecTransfBean> cabecList){
        ArrayList<Long> idCabecList = new ArrayList<Long>();
        for (CabecTransfBean cabecTransfBean : cabecList) {
            idCabecList.add(cabecTransfBean.getIdCabecTransf());
        }
        cabecList.clear();
        return idCabecList;
    }

    public List<CabecTransfBean> cabecTransfAbertoList(){
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        return cabecTransfBean.get("statusCabecTransf", 1L);
    }

    public List<CabecTransfBean> cabecTransfFechadoList(){
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        return cabecTransfBean.get("statusCabecTransf", 2L);
    }

    public List<CabecTransfBean> cabecTransfEnviadoList(){
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        return cabecTransfBean.get("statusCabecTransf", 3L);
    }

    public List<CabecTransfBean> cabecTransfListId(Long idCabecTransf){
        CabecTransfBean cabecTransfBean = new CabecTransfBean();
        return cabecTransfBean.get("idCabecTransf", idCabecTransf);
    }

    public String dadosEnvioCabecTransfFechado(){
        return dadosEnvioCabecTransf(cabecTransfFechadoList());
    }

    private String dadosEnvioCabecTransf(List<CabecTransfBean> cabecList){

        JsonArray cabecJsonArray = new JsonArray();

        for (CabecTransfBean cabecTransfBean : cabecList) {
            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecTransfBean, cabecTransfBean.getClass()));
        }

        cabecList.clear();

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", cabecJsonArray);

        return cabecJsonObj.toString();
    }

    public ArrayList<CabecTransfBean> cabecTransfEnviadoExcluirArrayList(){

        List<CabecTransfBean> cabecTransfList = cabecTransfEnviadoList();

        ArrayList<CabecTransfBean> cabecTransfArrayList = new ArrayList<>();
        for (CabecTransfBean cabecTransfBeanBD : cabecTransfList) {
            if(cabecTransfBeanBD.getDthrLongCabecTransf() < Tempo.getInstance().dthrLongDia1Menos()) {
                cabecTransfArrayList.add(cabecTransfBeanBD);
            }
        }
        cabecTransfList.clear();
        return cabecTransfArrayList;

    }

}
