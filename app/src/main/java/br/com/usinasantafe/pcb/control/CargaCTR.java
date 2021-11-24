package br.com.usinasantafe.pcb.control;

import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;
import br.com.usinasantafe.pcb.model.dao.CabecCargaDAO;
import br.com.usinasantafe.pcb.model.dao.FuncDAO;
import br.com.usinasantafe.pcb.model.dao.OrdemCargaDAO;

public class CargaCTR {

    private CabecCargaDAO cabecCargaDAO;

    public CabecCargaDAO getCabecCargaDAO(){
        if (cabecCargaDAO == null)
            cabecCargaDAO = new CabecCargaDAO();
        return cabecCargaDAO;
    }

    /////////////////////////////////// FUNCIONARIOS //////////////////////////////////////////////

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFuncMatric(matricFunc);
    }

    public FuncBean getFuncMatric(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFuncMatric(matricFunc);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// FUNCIONARIOS ///////////////////////////////////////////////

    public boolean verOrdemCargaNro(String nroOrdemCarga){
        OrdemCargaDAO ordemCargaDAO = new OrdemCargaDAO();
        return ordemCargaDAO.verOrdemCargaNro(nroOrdemCarga);
    }

    public OrdemCargaBean getOrdemCargaNro(String nroOrdemCarga){
        OrdemCargaDAO ordemCargaDAO = new OrdemCargaDAO();
        return ordemCargaDAO.getOrdemCargaNro(nroOrdemCarga);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
