package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.variaveis.ItemCarregBean;
import br.com.usinasantafe.pcb.model.pst.EspecificaPesquisa;

public class ItemCarregDAO {

    public ItemCarregDAO() {
    }

    public void inserirItemCarreg(Long idCabec, Long idRegMedPesBag){
        ItemCarregBean itemCarregBean = new ItemCarregBean();
        itemCarregBean.setIdCabecItemCarreg(idCabec);
        itemCarregBean.setIdRegMedPesBagCarreg(idRegMedPesBag);
        itemCarregBean.insert();
    }

    public int qtdeItemCarreg(Long idCabec){
        List<ItemCarregBean> itemCarregList = itemCarregListIdCabec(idCabec);
        int qtde = itemCarregList.size();
        itemCarregList.clear();
        return qtde;
    }

    public boolean verBagRepetido(Long idCabecItemCarreg, Long idRegMedPesBagCarreg){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabecItemCarreg));
        pesqArrayList.add(getPesqIdReg(idRegMedPesBagCarreg));

        ItemCarregBean itemCarregBean = new ItemCarregBean();
        List<ItemCarregBean> itemCarregList = itemCarregBean.get(pesqArrayList);
        boolean ret = itemCarregList.size() == 0;
        itemCarregList.clear();
        pesqArrayList.clear();
        return ret;
    }

    public List<ItemCarregBean> itemCarregListIdCabec(Long idCabec){
        ItemCarregBean itemCarregBean = new ItemCarregBean();
        return itemCarregBean.get("idCabecItemCarreg", idCabec);
    }

    public List<ItemCarregBean> itemCarregListId(ArrayList<Long> idItemCarregArrayList){
        ItemCarregBean itemCarregBean = new ItemCarregBean();
        return itemCarregBean.in("idItemCarreg", idItemCarregArrayList);
    }

    public List<ItemCarregBean> itemEnvioList(ArrayList<Long> idCabecList){
        ItemCarregBean itemCarregBean = new ItemCarregBean();
        return itemCarregBean.inAndOrderBy("idCabecItemCarreg", idCabecList, "idItemCarreg", true);
    }

    public String dadosEnvioItem(List<ItemCarregBean> itemCarregList){
        JsonArray itemJsonArray = new JsonArray();
        for (ItemCarregBean itemCarregBean : itemCarregList) {
            Gson itemGson = new Gson();
            itemJsonArray.add(itemGson.toJsonTree(itemCarregBean, itemCarregBean.getClass()));
        }
        itemCarregList.clear();
        JsonObject itemJsonObj = new JsonObject();
        itemJsonObj.add("item", itemJsonArray);
        return itemJsonObj.toString();
    }

    public ArrayList<Long> idItemArrayList(List<ItemCarregBean> apontMMFertList){
        ArrayList<Long> idApontList = new ArrayList<Long>();
        for (ItemCarregBean itemCarregBean : apontMMFertList) {
            idApontList.add(itemCarregBean.getIdItemCarreg());
        }
        return idApontList;
    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabecItemCarreg){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecItemCarreg");
        pesquisa.setValor(idCabecItemCarreg);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdReg(Long idRegMedPesBagCarreg){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idRegMedPesBagCarreg");
        pesquisa.setValor(idRegMedPesBagCarreg);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public ArrayList<String> itemCarregAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("ITEM");
        ItemCarregBean itemCarregBean = new ItemCarregBean();
        List<ItemCarregBean> itemCarregList = itemCarregBean.orderBy("idItemCarreg", true);
        for (ItemCarregBean itemCarregBeanBD : itemCarregList) {
            dadosArrayList.add(dadosApont(itemCarregBeanBD));
        }
        itemCarregList.clear();
        return dadosArrayList;
    }

    private String dadosApont(ItemCarregBean itemCarregBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(itemCarregBean, itemCarregBean.getClass()).toString();
    }

    public void deleteItemCabec(ArrayList<Long> idItemCabecArrayList){
        List<ItemCarregBean> itemCabecList = itemCarregListId(idItemCabecArrayList);
        for (ItemCarregBean itemCarregBean : itemCabecList) {
            itemCarregBean.delete();
        }
        itemCabecList.clear();
        idItemCabecArrayList.clear();
    }

}
