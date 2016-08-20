package culik.br.com.listacompra.utils.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import culik.br.com.listacompra.utils.model.Mercados;

/**
 * Created by LUIZ on 03/05/2016.
 */
public class MercadoDataSource {
    private SQLiteDatabase database;
    private final MySQLiteHelper dbHelper;

    private final String[] allColumns = {MySQLiteHelper.COLUMN_IDMERCADO,
            MySQLiteHelper.COLUMN_LGN,
            MySQLiteHelper.COLUMN_LAT,
            MySQLiteHelper.COLUMN_SNOME

    };

    public MercadoDataSource(Context context) {
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


    public void deletaMercado(long id) {

        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_MERCADO, MySQLiteHelper.COLUMN_IDMERCADO
                + " = " + id, null);
    }

    public Mercados pegaDadoProduto(long insertID) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_MERCADO,
                allColumns, MySQLiteHelper.COLUMN_IDLISTA + " = " + insertID, null,
                null, null, null);
        cursor.moveToFirst();
        Mercados newComment = cursorToMercados(cursor);
        cursor.close();
        return newComment;
    }

    public ArrayList<Mercados> getAllMercados() {
        ArrayList<Mercados> comments = new ArrayList<>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_MERCADO,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mercados comment = cursorToMercados(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    private Mercados cursorToMercados(Cursor cursor) {
        return new Mercados(cursor.getLong(0), cursor.getDouble(1), cursor.getDouble(2), cursor.getString(3));
    }

    public long insereMercados(Mercados c) {
        ContentValues co = new ContentValues();

        co.put(MySQLiteHelper.COLUMN_SNOME, c.getNome());

        co.put(MySQLiteHelper.COLUMN_LGN, c.getLgn());
        co.put(MySQLiteHelper.COLUMN_LAT, c.getLat());
        return database.insert(MySQLiteHelper.TABLE_MERCADO, null, co);

    }

    public void atualizaMercados(Mercados c) {
        ContentValues co = new ContentValues();

        co.put(MySQLiteHelper.COLUMN_SNOME, c.getNome());
        co.put(MySQLiteHelper.COLUMN_IDMERCADO, c.getId());
/*
        if (!c.getsTelefone().isEmpty())
            co.put( MySQLiteHelper.COLUMN_TELEFONE  ,c.getsTelefone());

        if (!c.getsEmail().isEmpty())
            co.put(MySQLiteHelper.COLUMN_EMAIL    ,c.getsEmail());
        if (!c.getdDataCad().isEmpty())
            co.put( MySQLiteHelper.COLUMN_DDATACAD, c.getdDataCad());

        if (!c.getsMensagem().isEmpty())
            co.put( MySQLiteHelper.COLUMN_MENSAGEM, c.getsMensagem());
*/
        database.update(MySQLiteHelper.TABLE_MERCADO, co, MySQLiteHelper.COLUMN_IDLISTA + "=" + c.getId(), null);

    }
}