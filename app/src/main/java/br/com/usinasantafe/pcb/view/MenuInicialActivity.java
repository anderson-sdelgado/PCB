package br.com.usinasantafe.pcb.view;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pcb.BuildConfig;
import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;
import br.com.usinasantafe.pcb.util.EnvioDadosServ;
import br.com.usinasantafe.pcb.util.VerifDadosServ;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PCBContext pcbContext;
    private ProgressDialog progressBar;

    private TextView textViewProcesso;
    private TextView textViewPrincipal;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);

        pcbContext = (PCBContext) getApplication();
        textViewProcesso = findViewById(R.id.textViewProcesso);
        textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        if (!checkPermission(Manifest.permission.INTERNET)) {
            String[] PERMISSIONS = {android.Manifest.permission.INTERNET};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        if (!checkPermission(Manifest.permission.CAMERA)) {
            String[] PERMISSIONS = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);
        }

        LogProcessoDAO.getInstance().insertLogProcesso("        if (!checkPermission(Manifest.permission.CAMERA)) {\n" +
                "            String[] PERMISSIONS = {Manifest.permission.CAMERA};\n" +
                "            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);\n" +
                "        }\n" +
                "        if (!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {\n" +
                "            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};\n" +
                "            ActivityCompat.requestPermissions(this, PERMISSIONS, 112);\n" +
                "        }\n" +
                "customHandler.postDelayed(updateTimerThread, 0);", getLocalClassName());
        customHandler.postDelayed(updateTimerThread, 0);

        LogProcessoDAO.getInstance().insertLogProcesso("progressBar = new ProgressDialog(this);\n" +
                "        ArrayList<String> itens = new ArrayList<>();\n" +
                "        itens.add(\"APONTAMENTO\");\n" +
                "        itens.add(\"CONFIGURAÇÃO\");\n" +
                "        itens.add(\"SAIR\");\n" +
                "itens.add(\"LOG\");", getLocalClassName());

        progressBar = new ProgressDialog(this);

        ArrayList<String> itens = new ArrayList<>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");
        itens.add("LOG");

        LogProcessoDAO.getInstance().insertLogProcesso("AdapterList adapterList = new AdapterList(this, itens);\n" +
                "        menuInicialListView = findViewById(R.id.listaMenuInicial);\n" +
                "        menuInicialListView.setAdapter(adapterList);", getLocalClassName());
        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = findViewById(R.id.listaMenuInicial);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                LogProcessoDAO.getInstance().insertLogProcesso("listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onItemClick(AdapterView<?> l, View v, int position,\n" +
                        "                                    long id) {\n" +
                        "                TextView textView = v.findViewById(R.id.textViewItemList);\n" +
                        "                String text = textView.getText().toString();", getLocalClassName());
                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if text.equals(\"APONTAMENTO\")) {", getLocalClassName());
                    if (pcbContext.getConfigCTR().hasElemFunc()
                            && pcbContext.getConfigCTR().hasElemConfig()
                            && (VerifDadosServ.status == 3)) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCarregCTR().hasElemFunc()\n" +
                                "                            && pcbContext.getConfigCTR().hasElemConfig()\n" +
                                "                            && (VerifDadosServ.status == 3)) {\n" +
                                "                        pcbContext.getConfigCTR().setPosicaoTela(1L);\n" +
                                "                        Intent it = new Intent(MenuInicialActivity.this, LeitorFuncActivity.class);", getLocalClassName());
                        pcbContext.getConfigCTR().setPosicaoTela(1L);
                        Intent it = new Intent(MenuInicialActivity.this, LeitorFuncActivity.class);
                        startActivity(it);
                        finish();
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"CONFIGURAÇÃO\")) {\n" +
                            "                    pmmContext.getConfigCTR().setPosicaoTela(2L);\n" +
                            "                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                    pcbContext.getConfigCTR().setPosicaoTela(2L);
                    Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("SAIR")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else if (text.equals(\"SAIR\")) {\n" +
                            "Intent intent = new Intent(Intent.ACTION_MAIN);\n" +
                            "                    intent.addCategory(Intent.CATEGORY_HOME);\n" +
                            "                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);\n" +
                            "                    startActivity(intent);", getLocalClassName());
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if (text.equals("LOG")) {
                    LogProcessoDAO.getInstance().insertLogProcesso("else if (text.equals(\"LOG\")) {", getLocalClassName());
                    if(pcbContext.getConfigCTR().hasElemConfig()) {
                        LogProcessoDAO.getInstance().insertLogProcesso("if(pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                                "                        pmmContext.getConfigCTR().setPosicaoTela(12L);\n" +
                                "                        Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);", getLocalClassName());
                        pcbContext.getConfigCTR().setPosicaoTela(3L);
                        Intent it = new Intent(MenuInicialActivity.this, SenhaActivity.class);
                        startActivity(it);
                        finish();
                    }
                }

            }

        });

    }

    public boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed() {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (pcbContext.getConfigCTR().hasElemConfig()) {
                pcbContext.getConfigCTR().setStatusRetVerif(0L);
                LogProcessoDAO.getInstance().insertLogProcesso("        if (pmmContext.getConfigCTR().hasElemConfig()) {\n" +
                        "            pmmContext.getConfigCTR().setStatusRetVerif(0L);\n" +
                        "EnvioDadosServ.status = " + EnvioDadosServ.status, getLocalClassName());
                if (EnvioDadosServ.status == 1) {
                    textViewProcesso.setTextColor(Color.RED);
                    textViewProcesso.setText("Existem Dados para serem Enviados");
                } else if (EnvioDadosServ.status == 2) {
                    textViewProcesso.setTextColor(Color.YELLOW);
                    textViewProcesso.setText("Enviando Dados...");
                } else if (EnvioDadosServ.status == 3) {
                    textViewProcesso.setTextColor(Color.GREEN);
                    textViewProcesso.setText("Todos os Dados já foram Enviados");
                }
            } else {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Aparelho sem Equipamento");
            }

            LogProcessoDAO.getInstance().insertLogProcesso("if(EnvioDadosServ.status != 3){\n" +
                    "                customHandler.postDelayed(this, 10000);\n" +
                    "            }", getLocalClassName());
            if(EnvioDadosServ.status != 3){
                customHandler.postDelayed(this, 10000);
            }
        }
    };

}