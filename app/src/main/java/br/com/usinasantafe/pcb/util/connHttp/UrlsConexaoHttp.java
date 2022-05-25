package br.com.usinasantafe.pcb.util.connHttp;

import br.com.usinasantafe.pcb.PCBContext;

public class UrlsConexaoHttp {

    public static String versao = "?versao=" + PCBContext.versaoWS.replace(".", "_");

//    public static String url = "https://www.usinasantafe.com.br/pcbdev/view/";
    public static String url = "https://www.usinasantafe.com.br/pcbqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcbprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcb.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcb.util.connHttp.UrlsConexaoHttp";

    public static String BagBean = url + "bagcarga.php";
    public static String FuncBean = url + "func.php";
    public static String OrdemCargaBean = url + "ordemcarga.php";

    public UrlsConexaoHttp() {
    }

    public String getsInsertCarga() {
        return url + "inserircarga.php";
    }

    public String getsInsertTransf() {
        return url + "inserirtransf.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("OrdemCarreg")) {
            retorno = url + "ordemcarreg.php";
        } else if (classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if (classe.equals("Bag")) {
            retorno = url + "pesqbagtransf.php";
        }
        return retorno;
    }

}
