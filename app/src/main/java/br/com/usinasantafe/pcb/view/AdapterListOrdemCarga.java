package br.com.usinasantafe.pcb.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCargaBean;

/**
 * Created by anderson on 08/03/2018.
 */
public class AdapterListOrdemCarga extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewTicketCarreg;
    private TextView textViewQtdeCarreg;

    public AdapterListOrdemCarga(Context context, List itens) {
        this.itens = itens;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.activity_item_ordem_carga, null);
        textViewTicketCarreg = view.findViewById(R.id.textViewTicketCarreg);
        textViewQtdeCarreg = view.findViewById(R.id.textViewQtdeCarreg);

        OrdemCargaBean ordemCargaBean = (OrdemCargaBean) itens.get(position);
        textViewTicketCarreg.setText("TICKET: " + ordemCargaBean.getTicketOrdemCarga());
        textViewQtdeCarreg.setText("QTDE EMBALAGENS: " + ordemCargaBean.getQtdeEmbProdOrdemCarga());

        return view;
    }

}
