package br.com.usinasantafe.pcb.model.dao;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;

public class OrdemCarregDAO {

    public OrdemCarregDAO() {
    }

    public List<OrdemCarregBean> ordemCargaList(){
        OrdemCarregBean ordemCarregBean = new OrdemCarregBean();
        return ordemCarregBean.orderBy("ticketOrdemCarreg", true);
    }

    public boolean verOrdemCargaTicket(String ticketOrdemCarreg){
        List<OrdemCarregBean> ordemCarregList = ordemCargaTicketList(ticketOrdemCarreg);
        boolean ret = ordemCarregList.size() > 0;
        ordemCarregList.clear();
        return ret;
    }

    public OrdemCarregBean getOrdemCargaTicket(String ticketOrdemCarreg){
        List<OrdemCarregBean> ordemCarregList = ordemCargaTicketList(ticketOrdemCarreg);
        OrdemCarregBean ordemCarregBean = (OrdemCarregBean) ordemCarregList.get(0);
        ordemCarregList.clear();
        return ordemCarregBean;
    }

    public OrdemCarregBean getOrdemCargaId(Long idOrdemCarreg){
        List<OrdemCarregBean> ordemCarregList = ordemCargaIdList(idOrdemCarreg);
        OrdemCarregBean ordemCarregBean = (OrdemCarregBean) ordemCarregList.get(0);
        ordemCarregList.clear();
        return ordemCarregBean;
    }

    private List<OrdemCarregBean> ordemCargaTicketList(String ticketOrdemCarreg){
        OrdemCarregBean ordemCarregBean = new OrdemCarregBean();
        return ordemCarregBean.get("ticketOrdemCarreg", ticketOrdemCarreg);
    }

    private List<OrdemCarregBean> ordemCargaIdList(Long idOrdemCarreg){
        OrdemCarregBean ordemCarregBean = new OrdemCarregBean();
        return ordemCarregBean.get("idOrdemCarreg", idOrdemCarreg);
    }

}
