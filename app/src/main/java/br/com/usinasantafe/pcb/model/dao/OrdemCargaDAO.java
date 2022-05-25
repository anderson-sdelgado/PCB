package br.com.usinasantafe.pcb.model.dao;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;

public class OrdemCargaDAO {

    public OrdemCargaDAO() {
    }

    public List<OrdemCargaBean> ordemCargaList(){
        OrdemCargaBean ordemCargaBean = new OrdemCargaBean();
        return ordemCargaBean.orderBy("idOrdemCarga", true);
    }

    public boolean verOrdemCargaTicket(String ticketOrdemCarreg){
        List<OrdemCargaBean> ordemCarregList = ordemCargaTicketList(ticketOrdemCarreg);
        boolean ret = ordemCarregList.size() > 0;
        ordemCarregList.clear();
        return ret;
    }

    public OrdemCargaBean getOrdemCargaTicket(String ticketOrdemCarreg){
        List<OrdemCargaBean> ordemCarregList = ordemCargaTicketList(ticketOrdemCarreg);
        OrdemCargaBean ordemCargaBean = (OrdemCargaBean) ordemCarregList.get(0);
        ordemCarregList.clear();
        return ordemCargaBean;
    }

    public OrdemCargaBean getOrdemCargaId(Long idOrdemCarreg){
        List<OrdemCargaBean> ordemCarregList = ordemCargaIdList(idOrdemCarreg);
        OrdemCargaBean ordemCargaBean = (OrdemCargaBean) ordemCarregList.get(0);
        ordemCarregList.clear();
        return ordemCargaBean;
    }

    private List<OrdemCargaBean> ordemCargaTicketList(String ticketOrdemCarreg){
        OrdemCargaBean ordemCargaBean = new OrdemCargaBean();
        return ordemCargaBean.get("ticketOrdemCarga", ticketOrdemCarreg);
    }

    private List<OrdemCargaBean> ordemCargaIdList(Long idOrdemCarreg){
        OrdemCargaBean ordemCargaBean = new OrdemCargaBean();
        return ordemCargaBean.get("idOrdemCarga", idOrdemCarreg);
    }

    public void atualDados(String result, String activity){

        try{

            LogProcessoDAO.getInstance().insertLogProcesso("JSONObject jObj = new JSONObject(result);\n" +
                    "            JSONArray jsonArray = jObj.getJSONArray(\"dados\");\n" +
                    "            OrdemCarregBean ordemCarregBean = new OrdemCarregBean();\n" +
                    "            ordemCarregBean.deleteAll();", activity);

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            OrdemCargaBean ordemCargaBean = new OrdemCargaBean();
            ordemCargaBean.deleteAll();

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
                ordemCargaBean = new OrdemCargaBean();
                ordemCargaBean = gson.fromJson(objeto.toString(), ordemCargaBean.getClass());
                ordemCargaBean.insert();
            }

        }
        catch (Exception e) {
            LogErroDAO.getInstance().insertLogErro(e);
        }

    }

}
