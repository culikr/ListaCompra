package culik.br.com.listacompra.utils.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.ui.Cadastra_Lista;
import culik.br.com.listacompra.utils.model.ListaCompra;

/**
 * Created by LUIZ on 07/05/2016.
 */
public class ListaCompraAdapterWithButton extends BaseAdapter {
    private final LayoutInflater mInflater;
    private ArrayList<ListaCompra> listaCompra;
    private Context context;

    public ListaCompraAdapterWithButton(Context context, ArrayList<ListaCompra> pProduto) {
        mInflater = LayoutInflater.from(context);
        listaCompra =pProduto;
        this.context = context;
    }
    public void updateResults(ArrayList<ListaCompra> pProduto) {
        listaCompra = pProduto;
        //Triggers the list update
        notifyDataSetChanged();
    }
    public void SetListValue(ArrayList<ListaCompra> pProduto ){
        listaCompra =pProduto;
    }

    public int getCount() {
        return listaCompra ==null? 0: listaCompra.size();
    }

    public Object getItem(int position) {
        return listaCompra ==null?null:listaCompra.get(position);
    }

    public long getItemId(int position) {
        return position;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.two_col_rol_btn, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.text2.setText( listaCompra.get(position).getsNome());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Cadastra_Lista.class);
                i.putExtra("lista", listaCompra.get(position));
                i.putExtra("posicao", position);
                i.putExtra("ledicao",0);
                context.startActivity(i);
            }
        });

        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Cadastra_Lista.class);
                i.putExtra("lista", listaCompra.get(position));
                i.putExtra("posicao", position);
                i.putExtra("ledicao",1);
                context.startActivity(i);
            }
        });

        return convertView;
    }

    static class ViewHolder {

        @Nullable @BindView(R.id.TextView02) TextView text2;
        @BindView(R.id.button2)
        Button btn;
        @BindView(R.id.button3)
        Button btn3;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
