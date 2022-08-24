package br.com.usinasantafe.pcb.util.connHttp;

import br.com.usinasantafe.pcb.PCBContext;

public class UrlsConexaoHttp {

    public static String versao = "versao_" + PCBContext.versaoWS.replace(".", "_");

//    public static String url = "https://www.usinasantafe.com.br/pcbdev/view/";
//    public static String url = "https://www.usinasantafe.com.br/pcbqa/view/";
    public static String url = "https://www.usinasantafe.com.br/pcbprod/" + versao + "/view/";

    public static String localPSTEstatica = "br.com.usinasantafe.pcb.model.bean.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pcb.util.connHttp.UrlsConexaoHttp";

    public static String FuncBean = url + "func.php";
    public static String OrdemCargaBean = url + "ordemcarga.php";
    public static String SafraBean = url + "safra.php";

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
        if(classe.equals("OrdemCarreg")) {
            retorno = url + "ordemcarreg.php";
        } else if(classe.equals("Atualiza")) {
            retorno = url + "atualaplic.php";
        } else if(classe.equals("BagCargaEstoqueCod")) {
            retorno = url + "pesqbagcargaestoquecod.php";
        } else if(classe.equals("BagCargaEstoqueNro")) {
            retorno = url + "pesqbagcargaestoquenro.php";
        } else if(classe.equals("BagCargaProducaoCod")) {
            retorno = url + "pesqbagcargaproducaocod.php";
        } else if(classe.equals("BagCargaProducaoNro")) {
            retorno = url + "pesqbagcargaproducaonro.php";
        } else if(classe.equals("BagTransfCod")) {
            retorno = url + "pesqbagtransfcod.php";
        } else if(classe.equals("BagTransfNro")) {
            retorno = url + "pesqbagtransfnro.php";
        }
        return retorno;
    }

}
