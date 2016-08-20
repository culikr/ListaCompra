package culik.br.com.listacompra.utils.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import culik.br.com.listacompra.utils.model.ListaCompra;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class ListaCompraDataSource {
    private SQLiteDatabase database;
    private final MySQLiteHelper dbHelper;

    private final String[] allColumns = {MySQLiteHelper.COLUMN_IDLISTA,
            MySQLiteHelper.COLUMN_SNOME,
            MySQLiteHelper.COLUMN_DDATACAD,
            MySQLiteHelper.COLUMN_EMAIL,
            MySQLiteHelper.COLUMN_TELEFONE,
            MySQLiteHelper.COLUMN_MENSAGEM,
            MySQLiteHelper.COLUMN_IDMERCADO
    };

    public ListaCompraDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public boolean isOpen() { return  dbHelper.getReadableDatabase().isOpen();}
    public void close() {
        dbHelper.close();
    }


    public void deletaProduto(long id) {

        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_LISTACOMPRA, MySQLiteHelper.COLUMN_IDLISTA
                + " = " + id, null);
    }


    public ListaCompra pegaDadoProduto(long insertID) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LISTACOMPRA,
                allColumns, MySQLiteHelper.COLUMN_IDLISTA + " = " + insertID, null,
                null, null, null);
        cursor.moveToFirst();
        ListaCompra newComment = cursorToListaCompra(cursor);
        cursor.close();
        return newComment;
    }

    public ArrayList<ListaCompra> getAllProduto() {
        ArrayList<ListaCompra> comments = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_LISTACOMPRA,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListaCompra comment = cursorToListaCompra(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private ListaCompra cursorToListaCompra(Cursor cursor) {
        return new ListaCompra(cursor.getLong(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getLong(6));
    }

    public long insereCompra(ListaCompra c){
        ContentValues co = new ContentValues();

        co.put(MySQLiteHelper.COLUMN_SNOME      ,c.getsNome());

        if (!c.getsTelefone().isEmpty())
           co.put( MySQLiteHelper.COLUMN_TELEFONE  ,c.getsTelefone());

        if (!c.getsEmail().isEmpty())
           co.put(MySQLiteHelper.COLUMN_EMAIL    ,c.getsEmail());
        if (!c.getdDataCad().isEmpty())
        co.put( MySQLiteHelper.COLUMN_DDATACAD, c.getdDataCad());

        if (!c.getsMensagem().isEmpty())
            co.put( MySQLiteHelper.COLUMN_MENSAGEM, c.getsMensagem());
        co.put(MySQLiteHelper.COLUMN_IDMERCADO,c.getIdMercado());

        return database.insert(MySQLiteHelper.TABLE_LISTACOMPRA,null,co);

    }

    public void atualizaListaCompra(ListaCompra c)
    {
        ContentValues co = new ContentValues();

        co.put(MySQLiteHelper.COLUMN_SNOME      ,c.getsNome());
        co.put(MySQLiteHelper.COLUMN_IDLISTA,c.getIdLista());

        if (!c.getsTelefone().isEmpty())
            co.put( MySQLiteHelper.COLUMN_TELEFONE  ,c.getsTelefone());

        if (!c.getsEmail().isEmpty())
            co.put(MySQLiteHelper.COLUMN_EMAIL    ,c.getsEmail());
        if (!c.getdDataCad().isEmpty())
            co.put( MySQLiteHelper.COLUMN_DDATACAD, c.getdDataCad());

        if (!c.getsMensagem().isEmpty())
            co.put( MySQLiteHelper.COLUMN_MENSAGEM, c.getsMensagem());
        co.put(MySQLiteHelper.COLUMN_IDMERCADO,c.getIdMercado());

        database.update(MySQLiteHelper.TABLE_LISTACOMPRA,co, MySQLiteHelper.COLUMN_IDLISTA  + "=" + c.getIdLista(),null);

    }

}
