package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCargaBean;
import br.com.usinasantafe.pcb.util.Tempo;

public class CabecCargaDAO {

    private CabecCargaBean cabecCargaBean;

    public CabecCargaBean getCabecCargaBean() {
        if (cabecCargaBean == null)
            cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean;
    }

    public void salvarCabecCargaAberto(){
        Long dthrLong = Tempo.getInstance().dthrAtualLong();
        cabecCargaBean.setDthrCabecCarga(Tempo.getInstance().dthrLongToString(dthrLong));
        cabecCargaBean.setDthrLongCabecCarga(dthrLong);
        cabecCargaBean.setStatusCabecCarga(1L);
        cabecCargaBean.insert();
    }

    public void fecharCabecCarga(){
        CabecCargaBean cabecCargaBean = getCabecCargaAberto();
        cabecCargaBean.setStatusCabecCarga(2L);
        cabecCargaBean.update();
    }

    public void deleteCabecCarga(Long idCabecCarga){
        List<CabecCargaBean> cabecCarregList = cabecCargaListId(idCabecCarga);
        CabecCargaBean cabecCargaBean = cabecCarregList.get(0);
        cabecCargaBean.delete();
        cabecCarregList.clear();
    }

    public CabecCargaBean getCabecCargaAberto() {
        List<CabecCargaBean> cabecCarregList = cabecCargaAbertoList();
        CabecCargaBean cabecCargaBean = cabecCarregList.get(0);
        cabecCarregList.clear();
        return cabecCargaBean;
    }

    public boolean verCabecCargaAberto(){
        List<CabecCargaBean> cabecList = cabecCargaAbertoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public boolean verCabecCargaFechado(){
        List<CabecCargaBean> cabecList = cabecCargaFechadoList();
        boolean ret = (cabecList.size() > 0);
        cabecList.clear();
        return ret;
    }

    public ArrayList<Long> idCabecCargaArrayList(List<CabecCargaBean> cabecList){
        ArrayList<Long> idCabecList = new ArrayList<Long>();
        for (CabecCargaBean cabecCargaBean : cabecList) {
            idCabecList.add(cabecCargaBean.getIdCabecCarga());
        }
        cabecList.clear();
        return idCabecList;
    }

    public List<CabecCargaBean> cabecCargaAbertoList(){
        CabecCargaBean cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean.get("statusCabecCarga", 1L);
    }

    public List<CabecCargaBean> cabecCargaFechadoList(){
        CabecCargaBean cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean.get("statusCabecCarga", 2L);
    }

    public List<CabecCargaBean> cabecCargaEnviadoList(){
        CabecCargaBean cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean.get("statusCabecCarga", 3L);
    }

    public List<CabecCargaBean> cabecCargaListId(Long idCabecCarreg){
        CabecCargaBean cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean.get("idCabecCarga", idCabecCarreg);
    }

    public String dadosEnvioCabecCargaFechado(){
        return dadosEnvioCabecCarga(cabecCargaFechadoList());
    }

    private String dadosEnvioCabecCarga(List<CabecCargaBean> cabecList){

        JsonArray cabecJsonArray = new JsonArray();

        for (CabecCargaBean cabecCargaBean : cabecList) {
            Gson gsonCabec = new Gson();
            cabecJsonArray.add(gsonCabec.toJsonTree(cabecCargaBean, cabecCargaBean.getClass()));
        }

        cabecList.clear();

        JsonObject cabecJsonObj = new JsonObject();
        cabecJsonObj.add("cabec", cabecJsonArray);

        return cabecJsonObj.toString();
    }

    public void updateCabecCargaFechado(String object) throws Exception {

        JSONObject cabecJsonObj = new JSONObject(object);
        JSONArray cabecJsonArray = cabecJsonObj.getJSONArray("cabec");

        for (int i = 0; i < cabecJsonArray.length(); i++) {

            JSONObject objBol = cabecJsonArray.getJSONObject(i);
            Gson gsonCabec = new Gson();
            CabecCargaBean cabecCargaBean = gsonCabec.fromJson(objBol.toString(), CabecCargaBean.class);

            List<CabecCargaBean> cabecList = cabecCargaBean.get("idCabecCarga", cabecCargaBean.getIdCabecCarga());
            CabecCargaBean cabecCargaBD = cabecList.get(0);
            cabecList.clear();

            if(cabecCargaBD.getStatusCabecCarga() == 2L){
                cabecCargaBD.setStatusCabecCarga(3L);
                cabecCargaBD.update();
            }

        }

    }

    public ArrayList<String> cabecCargaAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("CABEÇALHO CARGA");
        CabecCargaBean cabecCargaBean = new CabecCargaBean();
        List<CabecCargaBean> cabecCargaList = cabecCargaBean.orderBy("idCabecCarga", true);
        for (CabecCargaBean cabecCargaBD : cabecCargaList) {
            dadosArrayList.add(dadosCabecCarga(cabecCargaBD));
        }
        cabecCargaList.clear();
        return dadosArrayList;
    }

    private String dadosCabecCarga(CabecCargaBean cabecCargaBean){
        Gson gsonCabec = new Gson();
        return gsonCabec.toJsonTree(cabecCargaBean, cabecCargaBean.getClass()).toString();
    }

    public ArrayList<CabecCargaBean> cabecCargaEnviadoExcluirArrayList(){

        List<CabecCargaBean> cabecCargaList = cabecCargaEnviadoList();

        ArrayList<CabecCargaBean> cabecCargaArrayList = new ArrayList<>();
        for (CabecCargaBean cabecCargaBeanBD : cabecCargaList) {
            if(cabecCargaBeanBD.getDthrLongCabecCarga() < Tempo.getInstance().dthrLongDiaMenos(3)) {
                cabecCargaArrayList.add(cabecCargaBeanBD);
            }
        }
        cabecCargaList.clear();
        return cabecCargaArrayList;

    }

}
