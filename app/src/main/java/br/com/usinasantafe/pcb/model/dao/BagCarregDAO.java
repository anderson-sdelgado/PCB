package br.com.usinasantafe.pcb.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagCarregBean;
import br.com.usinasantafe.pcb.model.pst.EspecificaPesquisa;

public class BagCarregDAO {

    public BagCarregDAO() {
    }

    public boolean verBagCarregCodBarra(String codBarra, Long idEmprUsu, Long idPeriodProd, Long idEmbProd){
        List<BagCarregBean> bagCarregList = bagCarregCodBarraList(codBarra, idEmprUsu, idPeriodProd, idEmbProd);
        boolean ret = bagCarregList.size() > 0;
        bagCarregList.clear();
        return ret;
    }

    public BagCarregBean getBagCarregCodBarra(String codBarra, Long idEmprUsu, Long idPeriodProd, Long idProd){
        List<BagCarregBean> bagCarregList = bagCarregCodBarraList(codBarra, idEmprUsu, idPeriodProd, idProd);
        BagCarregBean bagCarregBean =  bagCarregList.get(0);
        bagCarregList.clear();
        return bagCarregBean;
    }

    private List<BagCarregBean> bagCarregCodBarraList(String codBarraBag, Long idEmprUsu, Long idPeriodProd, Long idProd){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCodBarra(codBarraBag));
        pesqArrayList.add(getPesqIdEmprUsu(idEmprUsu));
        pesqArrayList.add(getPesqIdPeriodProd(idPeriodProd));
        pesqArrayList.add(getPesqIdProd(idProd));

        BagCarregBean bagCarregBean = new BagCarregBean();
        return bagCarregBean.get(pesqArrayList);

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
