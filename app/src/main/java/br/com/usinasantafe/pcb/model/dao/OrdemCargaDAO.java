package br.com.usinasantafe.pcb.model.dao;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;

public class OrdemCargaDAO {

    public OrdemCargaDAO() {
    }

    public boolean verOrdemCargaNro(String nroOrdemCarga){
        List<OrdemCargaBean> ordemCargaList = ordemCargaNroList(nroOrdemCarga);
        boolean ret = ordemCargaList.size() > 0;
        ordemCargaList.clear();
        return ret;
    }

    public OrdemCargaBean getOrdemCargaNro(String nroOrdemCarga){
        List<OrdemCargaBean> ordemCargaList = ordemCargaNroList(nroOrdemCarga);
        OrdemCargaBean ordemCargaBean = (OrdemCargaBean) ordemCargaList.get(0);
        ordemCargaList.clear();
        return ordemCargaBean;
    }

    private List<OrdemCargaBean> ordemCargaNroList(String nroOrdemCarga){
        OrdemCargaBean ordemCargaBean = new OrdemCargaBean();
        return ordemCargaBean.get("nroOrdemCarga", nroOrdemCarga);
    }

}
