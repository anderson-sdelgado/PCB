package br.com.usinasantafe.pcb;

import android.app.Application;

import br.com.usinasantafe.pcb.control.CarregCTR;
import br.com.usinasantafe.pcb.control.ConfigCTR;
import br.com.usinasantafe.pcb.model.dao.LogErroDAO;

public class PCBContext extends Application {

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    private ConfigCTR configCTR;
    private CarregCTR carregCTR;
    private String codBarraBagLido;

    public static String versaoAplic = "1.00";

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

    public CarregCTR getCarregCTR(){
        if (carregCTR == null)
            carregCTR = new CarregCTR();
        return carregCTR;
    }

    public String getCodBarraBagLido() {
        return codBarraBagLido;
    }

    public void setCodBarraBagLido(String codBarraBagLido) {
        this.codBarraBagLido = codBarraBagLido;
    }

    private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            LogErroDAO.getInstance().insertLogErro(ex);
            mDefaultExceptionHandler.uncaughtException(thread, ex);
        }
    };
}
