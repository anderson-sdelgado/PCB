package br.com.usinasantafe.pcb;

import android.app.Application;

import br.com.usinasantafe.pcb.control.CargaCTR;
import br.com.usinasantafe.pcb.control.ConfigCTR;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;

public class PCBContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private ConfigCTR configCTR;
    private CargaCTR cargaCTR;

    public static String versaoAplic = "4.00";
    public static int aplic = 1;   // 1 - PMM; 2 - ECM; 3 - PCOMP

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

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };
}
