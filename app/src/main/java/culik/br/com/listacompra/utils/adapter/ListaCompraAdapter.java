package culik.br.com.listacompra.utils.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.model.ListaCompra;

/**
 * Created by LUIZ on 07/05/2016.
 */
public class ListaCompraAdapter extends BaseAdapter {
    private final LayoutInflater mInflater;
    private ArrayList<ListaCompra> listaCompra;

    public ListaCompraAdapter(Context context, ArrayList<ListaCompra> pProduto) {
        mInflater = LayoutInflater.from(context);
        listaCompra =pProduto;
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.two_col_rol, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(  Long.toString(listaCompra.get(position).getIdLista()));
//        holder.text2.setText((int) listaCompra.get(position).getIdtime());
        holder.text2.setText( listaCompra.get(position).getsNome());

        return convertView;
    }

    static class ViewHolder {
        @Nullable @BindView(R.id.TextView01) TextView text1;
        @Nullable @BindView(R.id.TextView02) TextView text2;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
