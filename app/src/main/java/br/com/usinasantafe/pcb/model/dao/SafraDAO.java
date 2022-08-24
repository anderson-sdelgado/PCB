package br.com.usinasantafe.pcb.model.dao;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.SafraBean;
import br.com.usinasantafe.pcb.util.Tempo;


public class SafraDAO {

    public SafraDAO() {
    }

    public List<SafraBean> safraList(){
        SafraBean safraBean = new SafraBean();
        return safraBean.orderBy("idSafra", false);
    }

    public Long idSafraAtual(){
        SafraBean safraBean = new SafraBean();
        List<SafraBean> safraBeanList = safraBean.get("atualSafra", 1L);
        if(safraBeanList.size() == 0){
            safraBeanList = safraList();
        }
        safraBean = safraBeanList.get(0);
        Long idSafra = safraBean.getIdSafra();
        safraBeanList.clear();
        return idSafra;
    }

    public SafraBean getSafraBean(Long idSafra){
        SafraBean safraBean = new SafraBean();
        List<SafraBean> safraBeanList = safraBean.get("idSafra", idSafra);
        safraBean = safraBeanList.get(0);
        safraBeanList.clear();
        return safraBean;
    }

}
