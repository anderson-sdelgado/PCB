package br.com.usinasantafe.pcb.model.dao;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagBean;
import br.com.usinasantafe.pcb.model.pst.EspecificaPesquisa;
import br.com.usinasantafe.pcb.util.VerifDadosServ;

public class BagDAO {

    public BagDAO() {
    }

    public boolean verBagCarregCodBarra(String codBarra, Long idEmprUsu, Long idPeriodProd, Long idEmbProd){
        List<BagBean> bagCarregList = bagCarregCodBarraList(codBarra, idEmprUsu, idPeriodProd, idEmbProd);
        boolean ret = bagCarregList.size() > 0;
        bagCarregList.clear();
        return ret;
    }

    public BagBean getBagCarregCodBarra(String codBarra, Long idEmprUsu, Long idPeriodProd, Long idProd){
        List<BagBean> bagCarregList = bagCarregCodBarraList(codBarra, idEmprUsu, idPeriodProd, idProd);
        BagBean bagBean =  bagCarregList.get(0);
        bagCarregList.clear();
        return bagBean;
    }

    private List<BagBean> bagCarregCodBarraList(String codBarraBag, Long idEmprUsu, Long idPeriodProd, Long idProd){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCodBarra(codBarraBag));
        pesqArrayList.add(getPesqIdEmprUsu(idEmprUsu));
        pesqArrayList.add(getPesqIdPeriodProd(idPeriodProd));
        pesqArrayList.add(getPesqIdProd(idProd));

        BagBean bagBean = new BagBean();
        return bagBean.get(pesqArrayList);

    }

    public void verBag(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        VerifDadosServ.getInstance().verifDados(dado, "Bag", telaAtual, telaProx, progressDialog, activity);
    }

    public BagBean recDadosBag(JSONArray jsonArray) throws JSONException {

        BagBean bagBean = new BagBean();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject objeto = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            bagBean =  gson.fromJson(objeto.toString(), BagBean.class);
        }
        return bagBean;
    }

    private EspecificaPesquisa getPesqCodBarra(String codBarraBag){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codBarraBag");
        pesquisa.setValor(codBarraBag);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdEmprUsu(Long idEmprUsu){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idEmprUsuBag");
        pesquisa.setValor(idEmprUsu);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdPeriodProd(Long idPeriodProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idPeriodProdBag");
        pesquisa.setValor(idPeriodProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdProd(Long idProd){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idProdBag");
        pesquisa.setValor(idProd);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
