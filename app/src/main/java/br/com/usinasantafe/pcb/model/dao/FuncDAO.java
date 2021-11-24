package br.com.usinasantafe.pcb.model.dao;

import java.util.List;

import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;

public class FuncDAO {

    public FuncDAO() {
    }

    public boolean hasElements(){
        FuncBean funcBean = new FuncBean();
        return funcBean.hasElements();
    }

    public boolean verFuncMatric(Long matricFunc){
        List<FuncBean> funcList = funcMatricList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public FuncBean getFuncMatric(Long matricColab){
        List<FuncBean> funcList = funcMatricList(matricColab);
        FuncBean colabBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return colabBean;
    }

    private List<FuncBean> funcMatricList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        return funcBean.get("matricFunc", matricFunc);
    }

}
