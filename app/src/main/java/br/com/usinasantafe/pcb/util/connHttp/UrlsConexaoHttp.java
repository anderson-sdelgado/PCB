package br.com.usinasantafe.pcb.util.connHttp;

import br.com.usinasantafe.pcb.PCBContext;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "https://www.usinasantafe.com.br/pcbqa/view/";
    public static String urlPrincEnvio = "https://www.usinasantafe.com.br/pcbqa/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcb.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcb.util.connHttp.UrlsConexaoHttp";

    public static String put = "?versao=" + PCBContext.versaoAplic.replace(".", "_");

    public static String BagCarregBean = urlPrincipal + "bag.php" + put;
    public static String FuncBean = urlPrincipal + "func.php" + put;
    public static String OrdemCarregBean = urlPrincipal + "ordemcarreg.php" + put;

    public UrlsConexaoHttp() {
    }

    public String getsInsertCarreg() {
        return urlPrincEnvio + "inserircarreg.php" + put;
    }

    public String getsInsertLogErro() {
        return urlPrincEnvio + "inserirlogerro.php" + put;
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("OrdemCarreg")) {
            retorno = urlPrincipal + "ordemcarreg.php" + put;
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincipal + "atualaplic.php" + put;
        }
        return retorno;
    }

}
