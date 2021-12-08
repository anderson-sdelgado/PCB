package br.com.usinasantafe.pcb.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pcb.PCBContext;
import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pcb.model.dao.LogProcessoDAO;

public class LeitorFuncActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private PCBContext pcbContext;
    private TextView txtRetColab;
    private ProgressDialog progressBar;
    private FuncBean funcBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_func);

        pcbContext = (PCBContext) getApplication();

        txtRetColab = (TextView) findViewById(R.id.txtRetColab);
        Button buttonOkColab = (Button) findViewById(R.id.buttonOkColab);
        Button buttonCancColab = (Button) findViewById(R.id.buttonCancColab);
        Button buttonDigColab = (Button) findViewById(R.id.buttonDigColab);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);
        Button buttonCaptColab = (Button) findViewById(R.id.buttonCaptColab);

        funcBean = new FuncBean();
        funcBean.setMatricFunc(0L);
        funcBean.setNomeFunc("");

        txtRetColab.setText("POR FAVOR, REALIZE A LEITURA DO CARTÃO DO COLABORADOR RESPONSÁVEL.");

        buttonOkColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonOkColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {", getLocalClassName());

                if (funcBean.getMatricFunc() > 0) {

                    pcbContext.getCarregCTR().getCabecCargaDAO().getCabecCargaBean().setIdFuncCabecCarreg(funcBean.getMatricFunc());

                    LogProcessoDAO.getInstance().insertLogProcesso("                if (funcBean.getMatricFunc() > 0) {\n" +
                            "                    pcbContext.getCargaCTR().getCabecCargaDAO().getCabecCargaBean().setMatricFuncCabecCarga(funcBean.getMatricFunc());\n" +
                            "                    Intent it = new Intent(LeitorFuncActivity.this, ListaOrdemCarregActivity.class);", getLocalClassName());

                    Intent it = new Intent(LeitorFuncActivity.this, ListaOrdemCarregActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("        buttonCancColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(LeitorFuncActivity.this, TelaInicialActivity.class);", getLocalClassName());
                Intent it = new Intent(LeitorFuncActivity.this, TelaInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonDigColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("buttonDigColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);", getLocalClassName());
                Intent it = new Intent(LeitorFuncActivity.this, DigFuncActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonCaptColab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogProcessoDAO.getInstance().insertLogProcesso("        buttonCaptColab.setOnClickListener(new View.OnClickListener() {\n" +
                        "            @Override\n" +
                        "            public void onClick(View v) {\n" +
                        "                Intent it = new Intent(LeitorFuncActivity.this, br.com.usinasantafe.pcb.zxing.CaptureActivity.class);\n" +
                        "                startActivityForResult(it, REQUEST_CODE);", getLocalClassName());
                Intent it = new Intent(LeitorFuncActivity.this, br.com.usinasantafe.pcb.zxing.CaptureActivity.class);
                startActivityForResult(it, REQUEST_CODE);
            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                        "                alerta.setTitle(\"ATENÇÃO\");\n" +
                        "                alerta.setMessage(\"DESEJA REALMENTE ATUALIZAR BASE DE DADOS?\");", getLocalClassName());
                AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorFuncActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setNegativeButton(\"SIM\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());

                        if (connectNetwork) {

                            LogProcessoDAO.getInstance().insertLogProcesso("if (connectNetwork) {\n" +
                                    "progressBar = new ProgressDialog(OperadorActivity.this);\n" +
                                    "                            progressBar.setCancelable(true);\n" +
                                    "                            progressBar.setMessage(\"ATUALIZANDO ...\");\n" +
                                    "                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);\n" +
                                    "                            progressBar.setProgress(0);\n" +
                                    "                            progressBar.setMax(100);\n" +
                                    "                            progressBar.show();", getLocalClassName());
                            progressBar = new ProgressDialog(LeitorFuncActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("ATUALIZANDO ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();

                            LogProcessoDAO.getInstance().insertLogProcesso("customHandler.removeCallbacks(updateTimerThread)", getLocalClassName());
                            pcbContext.getConfigCTR().atualDados(LeitorFuncActivity.this, LeitorFuncActivity.class, progressBar, "Func", 1, getLocalClassName());

                        } else {

                            LogProcessoDAO.getInstance().insertLogProcesso("AlertDialog.Builder alerta = new AlertDialog.Builder( OperadorActivity.this);\n" +
                                    "                            alerta.setTitle(\"ATENÇÃO\");\n" +
                                    "                            alerta.setMessage(\"FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.\");\n" +
                                    "                            alerta.setPositiveButton(\"OK\", new DialogInterface.OnClickListener() {\n" +
                                    "                                @Override\n" +
                                    "                                public void onClick(DialogInterface dialog, int which) {\n" +
                                    "                                }\n" +
                                    "                            });\n" +
                                    "                            alerta.show();", getLocalClassName());
                            AlertDialog.Builder alerta = new AlertDialog.Builder( LeitorFuncActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alerta.show();

                        }


                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LogProcessoDAO.getInstance().insertLogProcesso("alerta.setPositiveButton(\"NÃO\", new DialogInterface.OnClickListener() {\n" +
                                "                    @Override\n" +
                                "                    public void onClick(DialogInterface dialog, int which) {", getLocalClassName());
                    }
                });
                alerta.show();

            }

        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            LogProcessoDAO.getInstance().insertLogProcesso("    @Override\n" +
                    "    public void onActivityResult(int requestCode, int resultCode, Intent data) {\n" +
                    "        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {\n" +
                    "            String matricula = data.getStringExtra(\"SCAN_RESULT\");", getLocalClassName());
            String matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                LogProcessoDAO.getInstance().insertLogProcesso("            if (matricula.length() == 8) {\n" +
                        "                matricula = matricula.substring(0, 7);", getLocalClassName());
                matricula = matricula.substring(0, 7);
                if (pcbContext.getCarregCTR().verFunc(Long.parseLong(matricula))) {
                    LogProcessoDAO.getInstance().insertLogProcesso("if (pcbContext.getCargaCTR().verFunc(Long.parseLong(matricula))) {\n" +
                            "                    funcBean = pcbContext.getCargaCTR().getFuncMatric(Long.parseLong(matricula));\n" +
                            "                    txtRetColab.setText(matricula + \"\\n\" + funcBean.getNomeFunc());", getLocalClassName());
                    funcBean = pcbContext.getCarregCTR().getFuncMatric(Long.parseLong(matricula));
                    txtRetColab.setText(matricula + "\n" + funcBean.getNomeFunc());
                } else {
                    LogProcessoDAO.getInstance().insertLogProcesso("} else {\n" +
                            "                    txtRetColab.setText(\"Funcionário Inexistente\");", getLocalClassName());
                    txtRetColab.setText("Funcionário Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
    }

}