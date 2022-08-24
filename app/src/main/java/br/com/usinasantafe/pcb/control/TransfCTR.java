package br.com.usinasantafe.pcb.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pcb.model.bean.variaveis.CabecTransfBean;
import br.com.usinasantafe.pcb.model.bean.variaveis.ItemTransfBean;
import br.com.usinasantafe.pcb.model.dao.BagDAO;
import br.com.usinasantafe.pcb.model.dao.CabecTransfDAO;
import br.com.usinasantafe.pcb.model.dao.ConfigDAO;
import br.com.usinasantafe.pcb.model.dao.ItemTransfDAO;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;
import br.com.usinasantafe.pcb.util.EnvioDadosServ;
import br.com.usinasantafe.pcb.util.Json;
import br.com.usinasantafe.pcb.util.VerifDadosServ;

public class TransfCTR {

    public TransfCTR() {
    }

    public void salvarCabecTransfAberto(Long idFunc){
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        cabecTransfDAO.salvarCabecTransfAberto(idFunc);
    }

    public CabecTransfBean getCabecTransfAberto(){
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        return cabecTransfDAO.getCabecTransfAberto();
    }

    public int qtdeItemTransf(){
        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        return itemTransfDAO.qtdeItemTransf(getCabecTransfAberto().getIdCabecTransf());
    }

    public ArrayList<String> bagItemTransfArrayList(){
        ArrayList<String> itens = new ArrayList<String>();
        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        List<ItemTransfBean> itemTransfList = itemTransfDAO.itemTransfListIdCabec(getCabecTransfAberto().getIdCabecTransf());
        for (int i = 0; i < itemTransfList.size(); i++) {
            ItemTransfBean itemTransfBean = itemTransfList.get(i);
            itens.add(String.valueOf(itemTransfBean.getNroBag()));
        }
        return itens;
    }

    public void deleteCabecTransfAberto(){

        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        CabecTransfBean cabecTransfBean = cabecTransfDAO.getCabecTransfAberto();

        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        List<ItemTransfBean> itemTransfList = itemTransfDAO.itemTransfListIdCabec(cabecTransfBean.getIdCabecTransf());
        ArrayList<Long> idItemTransfArrayList = itemTransfDAO.idItemTransfArrayList(itemTransfList);
        itemTransfDAO.deleteItemTransf(idItemTransfArrayList);

        cabecTransfDAO.deleteCabecTransf(cabecTransfBean.getIdCabecTransf());

    }

    public void deleteCabecTransfEnviados(){

        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        ArrayList<CabecTransfBean> cabecTransfArrayList = cabecTransfDAO.cabecTransfEnviadoExcluirArrayList();

        for (CabecTransfBean cabecTransfBean : cabecTransfArrayList) {

            ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
            List<ItemTransfBean> itemTransfList = itemTransfDAO.itemTransfListIdCabec(cabecTransfBean.getIdCabecTransf());
            ArrayList<Long> idItemTransfArrayList = itemTransfDAO.idItemTransfArrayList(itemTransfList);
            itemTransfDAO.deleteItemTransf(idItemTransfArrayList);

            cabecTransfDAO.deleteCabecTransf(cabecTransfBean.getIdCabecTransf());

        }

        cabecTransfArrayList.clear();

    }

    public void updCabecTransf(String retorno, String activity){

        try {

            int pos1 = retorno.indexOf("_") + 1;

            String objPrinc = retorno.substring(pos1);

            CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
            cabecTransfDAO.updateCabecTransfFechado(objPrinc);

            EnvioDadosServ.getInstance().envioDados(activity);

        }
        catch (Exception e){
            EnvioDadosServ.status = 1;
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

    public void fecharTransfCarga(String activity){
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        cabecTransfDAO.fecharCabecTransf();
        EnvioDadosServ.getInstance().envioDados(activity);
    }

    public boolean verBagRepetidoTransf(Long codBarraBag){
        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        return itemTransfDAO.verBagRepetidoTransf(codBarraBag);
    }

    public boolean verCabecTransfAberto(){
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        return cabecTransfDAO.verCabecTransfAberto();
    }

    public boolean verCabecTransfFechado(){
        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        return cabecTransfDAO.verCabecTransfFechado();
    }

    public void verBagTransfCod(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        BagDAO bagDAO = new BagDAO();
        ConfigDAO configDAO = new ConfigDAO();
        bagDAO.verBagTransfCod(dado + "_" + configDAO.getConfig().getIdSafra() , telaAtual, telaProx, progressDialog, activity);
    }

    public void verBagTransfNro(String dado, Context telaAtual, Class telaProx, ProgressDialog progressDialog, String activity){
        BagDAO bagDAO = new BagDAO();
        ConfigDAO configDAO = new ConfigDAO();
        bagDAO.verBagTransfNro(dado + "_" + configDAO.getConfig().getIdSafra() , telaAtual, telaProx, progressDialog, activity);
    }

    public String dadosEnvioCabecTransfFechado(){

        CabecTransfDAO cabecTransfDAO = new CabecTransfDAO();
        String cabecDadosEnvio = cabecTransfDAO.dadosEnvioCabecTransfFechado();

        ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
        String itemDadosEnvio = itemTransfDAO.dadosTransfEnvioItem(itemTransfDAO.itemTransfEnvioList(cabecTransfDAO.idCabecTransfArrayList(cabecTransfDAO.cabecTransfFechadoList())));

        return cabecDadosEnvio + "_" + itemDadosEnvio;
    }

    public void receberVerifBag(String result){

        try {
            if (!result.contains("exceeded")) {

                Json json = new Json();
                JSONArray jsonArray = json.jsonArray(result);

                if (jsonArray.length() > 0) {

                    BagDAO bagDAO = new BagDAO();
                    ItemTransfDAO itemTransfDAO = new ItemTransfDAO();
                    itemTransfDAO.inserirItemTransf(getCabecTransfAberto().getIdCabecTransf(), bagDAO.recDadosBag(jsonArray));
                    VerifDadosServ.getInstance().pulaTela();

                } else {

                    VerifDadosServ.getInstance().msg("Embalagem não encontrada! Por favor verifique e realize uma nova leitura.");
                }

            }
            else{
                VerifDadosServ.getInstance().msg("EXCEDEU TEMPO LIMITE DE PESQUISA! POR FAVOR, PROCURE UM PONTO MELHOR DE CONEXÃO DOS DADOS.");
            }

        } catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
            VerifDadosServ.getInstance().msg("FALHA DE PESQUISA DE BAG! POR FAVOR, TENTAR NOVAMENTE COM UM SINAL MELHOR.");
        }
    }

}
