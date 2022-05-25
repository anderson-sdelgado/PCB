package br.com.usinasantafe.pcb;

import android.app.Application;

import br.com.usinasantafe.pcb.control.CargaCTR;
import br.com.usinasantafe.pcb.control.ConfigCTR;
import br.com.usinasantafe.pcb.control.TransfCTR;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;

public class PCBContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private ConfigCTR configCTR;
    private CargaCTR cargaCTR;
    private TransfCTR transfCTR;
    private String codBarraBagLido;
    private Long matricFunc;

    public static String versaoAPP = "1.00";
    public static String versaoWS = "1.00";

    @Override
    public void onCreate() {
        super.onCreate();
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(handler);
    }

    public ConfigCTR getConfigCTR(){
        if (configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public CargaCTR getCargaCTR(){
        if (cargaCTR == null)
            cargaCTR = new CargaCTR();
        return cargaCTR;
    }

    public TransfCTR getTransfCTR(){
        if (transfCTR == null)
            transfCTR = new TransfCTR();
        return transfCTR;
    }

    public String getCodBarraBagLido() {
        return codBarraBagLido;
    }

    public void setCodBarraBagLido(String codBarraBagLido) {
        this.codBarraBagLido = codBarraBagLido;
    }

    public Long getMatricFunc() {
        return matricFunc;
    }

    public void setMatricFunc(Long matricFunc) {
        this.matricFunc = matricFunc;
    }

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };
}
