package culik.br.com.listacompra.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import culik.br.com.listacompra.R;
import culik.br.com.listacompra.utils.model.Produto;

/**
 * Created by LUIZ on 07/05/2016.
 */
public class ListaProdutoAdapter extends BaseAdapter {
    /**
     * Variavel para guardar o inflater
     */
    private final LayoutInflater mInflater;
    /**
     * Lista de produtos
     */
    private ArrayList<Produto> listaCompra;

    /**
     *
     * @param context Contexto da aplicaçao
     * @param pProduto Lista de produtos
     */
    public ListaProdutoAdapter(Context context, ArrayList<Produto> pProduto) {
        mInflater = LayoutInflater.from(context);
        listaCompra =pProduto;
    }

    /**
     * Atualiza o vetor de produtos
     * @param pProduto Lista de produtos
     */
    public void updateResults(ArrayList<Produto> pProduto) {
        listaCompra = pProduto;
        //Triggers the list update
        notifyDataSetChanged();
    }
    public void SetListValue(ArrayList<Produto> pProduto ){
        listaCompra =pProduto;
    }

    /**
     *
     * @return retorna o numero de elementos na lista
     */
    public int getCount() {
        return listaCompra==null?0:listaCompra.size();
    }

    /**
     *
     * @param position posicao do vetor a ser lido
     * @return retorna o objeto referente a posicao
     */
    public Object getItem(int position) {
        return listaCompra==null?null:listaCompra.get(position);
    }

    public long getItemId(int position) {
        return position;

    }

    /**
     *
     * @param position posicao
     * @param convertView     View a ser convertida
     * @param parent Grupo
     * @return view a ser mostrada na tela
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.two_col_rol, null);
            holder = new ViewHolder();
            holder.text1 = (TextView) convertView
                    .findViewById(R.id.TextView01);
            holder.text2 = (TextView) convertView
                    .findViewById(R.id.TextView02);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.text1.setText(  Long.toString(listaCompra.get(position).getIdProduto()));
//        holder.text2.setText((int) listaCompra.get(position).getIdtime());
        holder.text2.setText( listaCompra.get(position).getsNome());

        return convertView;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
    }

}
