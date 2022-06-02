package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemCargaBean;
import br.com.usinasantafe.pcb.model.pst.EspecificaPesquisa;

public class ItemCargaDAO {

    public ItemCargaDAO() {
    }

    public void inserirItemCarga(Long idCabec, BagBean bagBean){
        ItemCargaBean itemCargaBean = new ItemCargaBean();
        itemCargaBean.setIdCabecCarga(idCabec);
        itemCargaBean.setIdRegMedPesBagCarga(bagBean.getIdRegMedPesBag());
        itemCargaBean.setNroBag(bagBean.getNroBag());
        itemCargaBean.setCodBarraBag(bagBean.getCodBarraBag());
        itemCargaBean.insert();
    }

    public int qtdeItemCarga(Long idCabec){
        List<ItemCargaBean> itemCarregList = itemCargaListIdCabec(idCabec);
        int qtde = itemCarregList.size();
        itemCarregList.clear();
        return qtde;
    }

    public boolean verBagRepetidoCarga(Long idCabecCarga, Long codBarraBag){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecCarga(idCabecCarga));
        pesqArrayList.add(getPesqCodBarraBagCarga(codBarraBag));

        ItemCargaBean itemCargaBean = new ItemCargaBean();
        List<ItemCargaBean> itemCarregList = itemCargaBean.get(pesqArrayList);
        boolean ret = itemCarregList.size() == 0;
        itemCarregList.clear();
        pesqArrayList.clear();
        return ret;
    }

    public List<ItemCargaBean> itemCargaListIdCabec(Long idCabec){
        ItemCargaBean itemCargaBean = new ItemCargaBean();
        return itemCargaBean.get("idCabecCarga", idCabec);
    }

    public List<ItemCargaBean> itemCargaListId(ArrayList<Long> idItemCarregArrayList){
        ItemCargaBean itemCargaBean = new ItemCargaBean();
        return itemCargaBean.in("idItemCarga", idItemCarregArrayList);
    }

    public List<ItemCargaBean> itemCargaEnvioList(ArrayList<Long> idCabecList){
        ItemCargaBean itemCargaBean = new ItemCargaBean();
        return itemCargaBean.inAndOrderBy("idCabecCarga", idCabecList, "idItemCarga", true);
    }

    public String dadosCargaEnvioItem(List<ItemCargaBean> itemCargaList){
        JsonArray itemJsonArray = new JsonArray();
        for (ItemCargaBean itemCargaBean : itemCargaList) {
            Gson itemGson = new Gson();
            itemJsonArray.add(itemGson.toJsonTree(itemCargaBean, itemCargaBean.getClass()));
        }
        itemCargaList.clear();
        JsonObject itemJsonObj = new JsonObject();
        itemJsonObj.add("item", itemJsonArray);
        return itemJsonObj.toString();
    }

    public ArrayList<Long> idItemCargaArrayList(List<ItemCargaBean> itemCargaList){
        ArrayList<Long> idItemList = new ArrayList<Long>();
        for (ItemCargaBean itemCargaBean : itemCargaList) {
            idItemList.add(itemCargaBean.getIdItemCarga());
        }
        return idItemList;
    }

    private EspecificaPesquisa getPesqIdCabecCarga(Long idCabecItemCarreg){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecCarga");
        pesquisa.setValor(idCabecItemCarreg);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCodBarraBagCarga(Long codBarraBag){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codBarraBag");
        pesquisa.setValor(codBarraBag);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    public ArrayList<String> itemCargaAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("ITEM CARGA");
        ItemCargaBean itemCargaBean = new ItemCargaBean();
        List<ItemCargaBean> itemCarregList = itemCargaBean.orderBy("idItemCarga", true);
        for (ItemCargaBean itemCargaBeanBD : itemCarregList) {
            dadosArrayList.add(dadosItemCarga(itemCargaBeanBD));
        }
        itemCarregList.clear();
        return dadosArrayList;
    }

    private String dadosItemCarga(ItemCargaBean itemCargaBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(itemCargaBean, itemCargaBean.getClass()).toString();
    }

    public void deleteItemCarga(ArrayList<Long> idItemCabecArrayList){
        List<ItemCargaBean> itemCabecList = itemCargaListId(idItemCabecArrayList);
        for (ItemCargaBean itemCargaBean : itemCabecList) {
            itemCargaBean.delete();
        }
        itemCabecList.clear();
        idItemCabecArrayList.clear();
    }

}
