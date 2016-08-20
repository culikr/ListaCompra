package culik.br.com.listacompra.utils.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import culik.br.com.listacompra.utils.model.Produto;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class ProdutoDataSource {
    private SQLiteDatabase database;
    private final MySQLiteHelper dbHelper;

    private final String[] allColumns = {MySQLiteHelper.COLUMN_IDPRODUTO,
            MySQLiteHelper.COLUMN_SNOME, MySQLiteHelper.COLUMN_LOCAL, MySQLiteHelper.COLUMN_PRECO, MySQLiteHelper.COLUMN_CODBAR};

    public ProdutoDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public boolean isOpen() {
        return dbHelper.getReadableDatabase().isOpen();
    }

    public void close() {
        dbHelper.close();
    }


    public void deletaProduto(long id) {

        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUTO, MySQLiteHelper.COLUMN_IDPRODUTO
                + " = " + id, null);
    }

    public void insereProduto(Produto p) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_SNOME, p.getNome());

        if (p.getIdMercado() > 0)
            values.put(MySQLiteHelper.COLUMN_LOCAL, p.getIdMercado());

        if (p.getPreco() > 0)
            values.put(MySQLiteHelper.COLUMN_PRECO, p.getPreco());

        values.put(MySQLiteHelper.COLUMN_CODBAR, p.getCodbar());

        database.insert(MySQLiteHelper.TABLE_PRODUTO, null,
                values);
    }

    public Produto pegaDadosCampeonatoProduto(long insertID) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUTO,
                allColumns, MySQLiteHelper.COLUMN_IDPRODUTO + " = " + insertID, null,
                null, null, null);
        cursor.moveToFirst();
        Produto newComment = cursorToProduto(cursor);
        cursor.close();
        return newComment;
    }

    public ArrayList<Produto> getAllProduto() {
        ArrayList<Produto> comments = new ArrayList<Produto>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUTO,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Produto comment = cursorToProduto(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Produto cursorToProduto(Cursor cursor) {
        return new Produto(cursor.getLong(0), cursor.getString(1), cursor.getLong(2), cursor.getDouble(3), cursor.getString(4));
    }


}
