package culik.br.com.listacompra.utils.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import culik.br.com.listacompra.utils.model.ListaProduto;


/**
 * Created by LUIZ on 03/05/2016.
 */
public class ListaProdutoDataSource {
    private SQLiteDatabase database;
    private final MySQLiteHelper dbHelper;

    private final String[] allColumns = {MySQLiteHelper.COLUMN_IDITEM,
            MySQLiteHelper.COLUMN_IDLISTA,MySQLiteHelper.COLUMN_IDPRODUTO,MySQLiteHelper.COLUMN_DQUANT};

    public ListaProdutoDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public boolean isOpen() { return  dbHelper.getReadableDatabase().isOpen();}
    public void close() {
        dbHelper.close();
    }


    public void deletaListaProduto(long id) {

        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUTO, MySQLiteHelper.COLUMN_IDPRODUTO
                + " = " + id, null);
    }


    public ListaProduto pegaDadosListaProduto(long insertID) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_LISTA,
                allColumns, MySQLiteHelper.COLUMN_IDITEM + " = " + insertID, null,
                null, null, null);
        cursor.moveToFirst();
        ListaProduto newComment = cursorToListaProduto(cursor);
        cursor.close();
        return newComment;
    }

    public ArrayList<ListaProduto> getAllListaProduto( ) {
        ArrayList<ListaProduto> comments = new ArrayList<ListaProduto>();
        String query = "Select a."+ MySQLiteHelper.COLUMN_IDPRODUTO+",a."+
                MySQLiteHelper.COLUMN_IDLISTA+",a."+ MySQLiteHelper.COLUMN_IDITEM+",a."+
                MySQLiteHelper.COLUMN_DQUANT+",b." +MySQLiteHelper.COLUMN_SNOME +
                " from " + MySQLiteHelper.TABLE_LISTA  + " a, " + MySQLiteHelper.TABLE_PRODUTO +
                " b, " +   MySQLiteHelper.TABLE_LISTACOMPRA + " c where c."+
                MySQLiteHelper.COLUMN_IDLISTA + " = a." + MySQLiteHelper.COLUMN_IDLISTA +
                " and b."+ MySQLiteHelper.COLUMN_IDPRODUTO + "= a." + MySQLiteHelper.COLUMN_IDPRODUTO ;

        Cursor cursor = database.rawQuery(query,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ListaProduto comment = cursorToListaProduto(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public ArrayList<ListaProduto> getAllListaProduto( long idLista ) {
        ListaProduto comment = null;
        ArrayList<ListaProduto> comments = new ArrayList<>();
        try{

        String query = "Select a."+ MySQLiteHelper.COLUMN_IDPRODUTO+",a."+
                MySQLiteHelper.COLUMN_IDLISTA+",a."+ MySQLiteHelper.COLUMN_IDITEM+",a."+
                MySQLiteHelper.COLUMN_DQUANT+",b." +MySQLiteHelper.COLUMN_SNOME +
                " from " + MySQLiteHelper.TABLE_LISTA  + " a, " + MySQLiteHelper.TABLE_PRODUTO +
                " b, " +   MySQLiteHelper.TABLE_LISTACOMPRA + " c where c."+
                MySQLiteHelper.COLUMN_IDLISTA + " = a." + MySQLiteHelper.COLUMN_IDLISTA +
                " and b."+ MySQLiteHelper.COLUMN_IDPRODUTO + " = a." + MySQLiteHelper.COLUMN_IDPRODUTO +
                " and a."+MySQLiteHelper.COLUMN_IDLISTA + " = " +idLista;
     //   Select b.idproduto,c.idlista,c.iditem,c.dquant,b.nome from lista a, produto b, listacompra c where c.idlista = a.idlista and b.idproduto = a.idproduto and a.idlista = 1
        Cursor cursor = database.rawQuery(query,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            comment = cursorToListaProduto(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();}
            catch ( Exception e1){
                Log.d("login", e1.toString());


            }
        return comments;
    }
    public long insereLista(ListaProduto c){
        ContentValues co = new ContentValues();
        co.put(MySQLiteHelper.COLUMN_DQUANT,c.getdQuant());
        co.put(MySQLiteHelper.COLUMN_IDPRODUTO,c.getIdProduto());
        co.put(MySQLiteHelper.COLUMN_IDLISTA,c.getIdLista());
        return database.insert(MySQLiteHelper.TABLE_LISTA,null,co);

    }

    private ListaProduto cursorToListaProduto(Cursor cursor) {
        return new ListaProduto(cursor.getLong(0),cursor.getLong(1),cursor.getLong(2),cursor.getDouble(3),cursor.getString(4));

    }


}
