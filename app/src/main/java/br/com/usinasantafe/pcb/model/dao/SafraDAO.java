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
        Long idSafra = 0L;
        SafraBean safraBean = new SafraBean();
        List<SafraBean> safraBeanList = safraBean.orderBy("idSafra", true);
        Long dthrAtual = Tempo.getInstance().dthrAtualLong();
        for (SafraBean safraBeanBD : safraBeanList){
            if(Tempo.getInstance().dtStringToLong(safraBeanBD.getDataInicioSafra()) <= dthrAtual){
                idSafra = safraBeanBD.getIdSafra();
            }
        }
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
