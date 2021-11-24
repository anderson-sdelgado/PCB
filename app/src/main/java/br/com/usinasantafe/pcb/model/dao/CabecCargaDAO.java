package br.com.usinasantafe.pcb.model.dao;

import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCargaBean;

public class CabecCargaDAO {

    private CabecCargaBean cabecCargaBean;

    public CabecCargaDAO() {
    }

    public CabecCargaBean getCabecCargaBean() {
        if (cabecCargaBean == null)
            cabecCargaBean = new CabecCargaBean();
        return cabecCargaBean;
    }


}
