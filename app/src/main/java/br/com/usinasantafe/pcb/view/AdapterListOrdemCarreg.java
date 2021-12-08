package br.com.usinasantafe.pcb.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pcb.R;
import br.com.usinasantafe.pcb.model.bean.estaticas.OrdemCarregBean;

/**
 * Created by anderson on 08/03/2018.
 */
public class AdapterListOrdemCarreg extends BaseAdapter {

    private List itens;
    private LayoutInflater layoutInflater;
    private TextView textViewTicketCarreg;
    private TextView textViewQtdeCarreg;

    public AdapterListOrdemCarreg(Context context, List itens) {
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

        view = layoutInflater.inflate(R.layout.activity_item_ordem_carreg, null);
        textViewTicketCarreg = view.findViewById(R.id.textViewTicketCarreg);
        textViewQtdeCarreg = view.findViewById(R.id.textViewQtdeCarreg);

        OrdemCarregBean ordemCarregBean = (OrdemCarregBean) itens.get(position);
        textViewTicketCarreg.setText(ordemCarregBean.getTicketOrdemCarreg());
        textViewQtdeCarreg.setText(String.valueOf(ordemCarregBean.getQtdeEmbProdOrdemCarreg()));

        return view;
    }

}
