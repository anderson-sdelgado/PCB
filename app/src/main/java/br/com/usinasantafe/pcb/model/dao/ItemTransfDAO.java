package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemTransfBean;
import br.com.usinasantafe.pcb.model.pst.EspecificaPesquisa;

public class ItemTransfDAO {

    public ItemTransfDAO() {
    }

    public void inserirItemTransf(Long idCabec, BagBean bagBean){
        ItemTransfBean itemTransfBean = new ItemTransfBean();
        itemTransfBean.setIdCabecTransf(idCabec);
        itemTransfBean.setIdRegMedPesBagTransf(bagBean.getIdRegMedPesBag());
        itemTransfBean.setNroBag(bagBean.getNroBag());
        itemTransfBean.setCodBarraBag(bagBean.getCodBarraBag());
        itemTransfBean.insert();
    }

    public void deleteItemTransf(ArrayList<Long> idItemCabecArrayList){
        List<ItemTransfBean> itemCabecList = itemTransfListId(idItemCabecArrayList);
        for (ItemTransfBean itemTransfBean : itemCabecList) {
            itemTransfBean.delete();
        }
        itemCabecList.clear();
        idItemCabecArrayList.clear();
    }

    public int qtdeItemTransf(Long idCabec){
        List<ItemTransfBean> itemTransfList = itemTransfListIdCabec(idCabec);
        int qtde = itemTransfList.size();
        itemTransfList.clear();
        return qtde;
    }

    public ArrayList<Long> idItemTransfArrayList(List<ItemTransfBean> itemTransfList){
        ArrayList<Long> idItemList = new ArrayList<Long>();
        for (ItemTransfBean itemTransfBean : itemTransfList) {
            idItemList.add(itemTransfBean.getIdItemTransf());
        }
        return idItemList;
    }

    public List<ItemTransfBean> itemTransfListIdCabec(Long idCabec){
        ItemTransfBean itemTransfBean = new ItemTransfBean();
        return itemTransfBean.get("idCabecTransf", idCabec);
    }

    public List<ItemTransfBean> itemTransfListId(ArrayList<Long> idItemTransfArrayList){
        ItemTransfBean itemTransfBean = new ItemTransfBean();
        return itemTransfBean.in("idItemTransf", idItemTransfArrayList);
    }

    public List<ItemTransfBean> itemTransfEnvioList(ArrayList<Long> idCabecList){
        ItemTransfBean itemTransfBean = new ItemTransfBean();
        return itemTransfBean.inAndOrderBy("idCabecTransf", idCabecList, "idItemTransf", true);
    }

    public ArrayList<String> itemTransfAllArrayList(ArrayList<String> dadosArrayList){
        dadosArrayList.add("ITEM CARGA");
        ItemTransfBean itemTransfBean = new ItemTransfBean();
        List<ItemTransfBean> itemTransfList = itemTransfBean.orderBy("idItemTransf", true);
        for (ItemTransfBean itemTransfBeanBD : itemTransfList) {
            dadosArrayList.add(dadosItemTransf(itemTransfBeanBD));
        }
        itemTransfList.clear();
        return dadosArrayList;
    }

    private String dadosItemTransf(ItemTransfBean itemTransfBean){
        Gson gsonItemImp = new Gson();
        return gsonItemImp.toJsonTree(itemTransfBean, itemTransfBean.getClass()).toString();
    }

    public String dadosTransfEnvioItem(List<ItemTransfBean> itemTransfList){
        JsonArray itemJsonArray = new JsonArray();
        for (ItemTransfBean itemTransfBean : itemTransfList) {
            Gson itemGson = new Gson();
            itemJsonArray.add(itemGson.toJsonTree(itemTransfBean, itemTransfBean.getClass()));
        }
        itemTransfList.clear();
        JsonObject itemJsonObj = new JsonObject();
        itemJsonObj.add("item", itemJsonArray);
        return itemJsonObj.toString();
    }

    public boolean verBagRepetidoTransf(Long codBarraBag){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCodBarraBagTransf(codBarraBag));

        ItemTransfBean itemTransfBean = new ItemTransfBean();
        List<ItemTransfBean> itemTransfList = itemTransfBean.get(pesqArrayList);
        boolean ret = itemTransfList.size() == 0;
        itemTransfList.clear();
        pesqArrayList.clear();
        return ret;
    }

    private EspecificaPesquisa getPesqIdCabecTransf(Long idCabecItemTransf){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecTransf");
        pesquisa.setValor(idCabecItemTransf);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCodBarraBagTransf(Long codBarraBag){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codBarraBag");
        pesquisa.setValor(codBarraBag);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
