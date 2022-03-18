package br.com.usinasantafe.pcb.model.dao;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;

public class OrdemCarregDAO {

    public OrdemCarregDAO() {
    }

    public List<OrdemCarregBean> ordemCargaList(){
        OrdemCarregBean ordemCarregBean = new OrdemCarregBean();
        return ordemCarregBean.orderBy("idOrdemCarreg", true);
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

    public void atualDados(String result, String activity){

        try{

            LogProcessoDAO.getInstance().insertLogProcesso("JSONObject jObj = new JSONObject(result);\n" +
                    "            JSONArray jsonArray = jObj.getJSONArray(\"dados\");\n" +
                    "            OrdemCarregBean ordemCarregBean = new OrdemCarregBean();\n" +
                    "            ordemCarregBean.deleteAll();", activity);

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            OrdemCarregBean ordemCarregBean = new OrdemCarregBean();
            ordemCarregBean.deleteAll();

            LogProcessoDAO.getInstance().insertLogProcesso("for(int i = 0; i < jsonArray.length(); i++){\n" +
                    "                JSONObject objeto = jsonArray.getJSONObject(i);\n" +
                    "                Gson gson = new Gson();\n" +
                    "                ordemCarregBean = new OrdemCarregBean();\n" +
                    "                ordemCarregBean = gson.fromJson(objeto.toString(), ordemCarregBean.getClass());\n" +
                    "                ordemCarregBean.insert();\n" +
                    "            }", activity);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject objeto = jsonArray.getJSONObject(i);
                Gson gson = new Gson();
                ordemCarregBean = new OrdemCarregBean();
                ordemCarregBean = gson.fromJson(objeto.toString(), ordemCarregBean.getClass());
                ordemCarregBean.insert();
            }

        }
        catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

}
