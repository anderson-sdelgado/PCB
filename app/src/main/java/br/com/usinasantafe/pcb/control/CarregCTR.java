package br.com.usinasantafe.pcb.control;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.BagCarregBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.CabecCarregBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemCarregBean;
import br.com.usinasantafe.pcb.model.dao.BagCarregDAO;
import br.com.usinasantafe.pcb.model.dao.CabecCarregDAO;
import br.com.usinasantafe.pcb.model.dao.FuncDAO;
import br.com.usinasantafe.pcb.model.dao.ItemCarregDAO;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.model.dao.OrdemCarregDAO;
import br.com.usinasantafe.pcb.util.EnvioDadosServ;

public class CarregCTR {

    private CabecCarregDAO cabecCarregDAO;

    public CabecCarregDAO getCabecCargaDAO(){
        if (cabecCarregDAO == null)
            cabecCarregDAO = new CabecCarregDAO();
        return cabecCarregDAO;
    }

    /////////////////////////////////// CABEC CARREG //////////////////////////////////////////////

    public void salvarCabecAberto(){
        cabecCarregDAO.salvarCabecAberto();
    }

    public void fecharCabec(String activity){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        cabecCarregDAO.fecharCabec();
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public boolean verCabecAberto(){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        return cabecCarregDAO.verCabecAberto();
    }

    public boolean verCabecFechado(){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        return cabecCarregDAO.verCabecFechado();
    }

    public CabecCarregBean getCabecAberto(){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        return cabecCarregDAO.getCabecAberto();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// ITEM CARREG //////////////////////////////////////////////

    public void inserirItemCarreg(String codBarraBag, String activity){
        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        itemCarregDAO.inserirItemCarreg(getCabecAberto().getIdCabecCarreg(), getBagCarregCodBarra(codBarraBag).getIdRegMedPesBag());
    }

    public int qtdeRestItemCarreg(){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
        int qtdeTotalItem = ordemCarregDAO.getOrdemCargaId(cabecCarregDAO.getCabecAberto().getIdOrdemCabecCarreg()).getQtdeEmbProdOrdemCarreg().intValue();
        int qtdeItemCabec = itemCarregDAO.qtdeItemCarreg(cabecCarregDAO.getCabecAberto().getIdCabecCarreg());
        return (qtdeTotalItem - qtdeItemCabec);
    }

    public int qtdeItemCarreg(){
        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        return itemCarregDAO.qtdeItemCarreg(cabecCarregDAO.getCabecAberto().getIdCabecCarreg());
    }

    public ArrayList<String> bagItemCarregArrayList(){
        ArrayList<String> itens = new ArrayList<String>();
        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        List<ItemCarregBean> itemCarregList = itemCarregDAO.itemCarregListIdCabec(getCabecAberto().getIdCabecCarreg());
        for (int i = 0; i < itemCarregList.size(); i++) {
            ItemCarregBean itemCarregBean = itemCarregList.get(i);
            itens.add(String.valueOf(itemCarregBean.getIdRegMedPesBagCarreg()));
        }
        return itens;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

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

    /////////////////////////////////// ORDEM CARREG //////////////////////////////////////////////

    public List<OrdemCarregBean> ordemCargaList(){
        OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
        return ordemCarregDAO.ordemCargaList();
    }

    public boolean verOrdemCarregTicket(String ticketOrdemCarreg){
        OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
        return ordemCarregDAO.verOrdemCargaTicket(ticketOrdemCarreg);
    }

    public OrdemCarregBean getOrdemCarregTicket(String ticketOrdemCarreg){
        OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
        return ordemCarregDAO.getOrdemCargaTicket(ticketOrdemCarreg);
    }

    public OrdemCarregBean getOrdemCargaId(Long idOrdemCarreg){
        OrdemCarregDAO ordemCarregDAO = new OrdemCarregDAO();
        return ordemCarregDAO.getOrdemCargaId(idOrdemCarreg);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// BAG CARREG ////////////////////////////////////////////////

    public boolean verBagCarregCodBarra(String codBarra){
        BagCarregDAO bagCarregDAO = new BagCarregDAO();
        OrdemCarregBean ordemCarregBean = getOrdemCargaId(getCabecAberto().getIdOrdemCabecCarreg());
        if(codBarra.matches("[+-]?\\d*(\\.\\d+)?")){
            if(bagCarregDAO.verBagCarregCodBarra(codBarra, ordemCarregBean.getIdEmprUsuOrdemCarreg(), ordemCarregBean.getIdPeriodProdOrdemCarreg(), ordemCarregBean.getIdEmbProdOrdemCarreg())){
                BagCarregBean bagCarregBean = bagCarregDAO.getBagCarregCodBarra(codBarra, ordemCarregBean.getIdEmprUsuOrdemCarreg(), ordemCarregBean.getIdPeriodProdOrdemCarreg(), ordemCarregBean.getIdEmbProdOrdemCarreg());
                ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
                CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
                if(itemCarregDAO.verBagRepetido(cabecCarregDAO.getCabecAberto().getIdCabecCarreg(), bagCarregBean.getIdRegMedPesBag())){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public BagCarregBean getBagCarregCodBarra(String codBarra){
        BagCarregDAO bagCarregDAO = new BagCarregDAO();
        OrdemCarregBean ordemCarregBean = getOrdemCargaId(getCabecAberto().getIdOrdemCabecCarreg());
        return bagCarregDAO.getBagCarregCodBarra(codBarra, ordemCarregBean.getIdEmprUsuOrdemCarreg(), ordemCarregBean.getIdPeriodProdOrdemCarreg(), ordemCarregBean.getIdEmbProdOrdemCarreg());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ENVIO DE DADOS ///////////////////////////////////////////

    public String dadosEnvioCabecFechado(){

        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        String cabecDadosEnvio = cabecCarregDAO.dadosEnvioCabecFechado();

        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        String itemDadosEnvio = itemCarregDAO.dadosEnvioItem(itemCarregDAO.itemEnvioList(cabecCarregDAO.idCabecArrayList(cabecCarregDAO.cabecCarregFechadoList())));

        return cabecDadosEnvio + "_" + itemDadosEnvio;
    }

    public void updCabec(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;

            String objPrinc = retorno.substring(pos1);

            CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
            cabecCarregDAO.updateCabecFechado(objPrinc);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////// EXCLUIR DADOS /////////////////////////////////////////


    public void deleteCabecEnviados(){

        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        ArrayList<CabecCarregBean> cabecCarregArrayList = cabecCarregDAO.cabecEnviadoExcluirArrayList();

        for (CabecCarregBean cabecCarregBean : cabecCarregArrayList) {

            ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
            List<ItemCarregBean> itemCarregFertList = itemCarregDAO.itemCarregListIdCabec(cabecCarregBean.getIdCabecCarreg());
            ArrayList<Long> idItemCarregArrayList = itemCarregDAO.idItemArrayList(itemCarregFertList);
            itemCarregDAO.deleteItemCabec(idItemCarregArrayList);

            cabecCarregDAO.deleteCabecCarreg(cabecCarregBean.getIdCabecCarreg());

        }

        cabecCarregArrayList.clear();

    }


    public void deleteCabecAberto(){

        CabecCarregDAO cabecCarregDAO = new CabecCarregDAO();
        CabecCarregBean cabecCarregBean = cabecCarregDAO.getCabecAberto();

        ItemCarregDAO itemCarregDAO = new ItemCarregDAO();
        List<ItemCarregBean> itemCarregFertList = itemCarregDAO.itemCarregListIdCabec(cabecCarregBean.getIdCabecCarreg());
        ArrayList<Long> idItemCarregArrayList = itemCarregDAO.idItemArrayList(itemCarregFertList);
        itemCarregDAO.deleteItemCabec(idItemCarregArrayList);

        cabecCarregDAO.deleteCabecCarreg(cabecCarregBean.getIdCabecCarreg());

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

}
