package culik.br.com.listacompra.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.Arrays;
import java.util.HashSet;

import culik.br.com.listacompra.utils.database.MySQLiteHelper;

/**
 * Created by LUIZ on 09/07/2016.
 */
public class ProdutoProvider extends ContentProvider {

        private MySQLiteHelper database;
        private static final String PROVIDER_NAME = "culik.br.com.listacompra.Provider.Produtos";
        private static final String BASE_PATH = "produto";
        private static final String URL = "content://" + PROVIDER_NAME + "/"+BASE_PATH;
        static final Uri CONTENT_URI = Uri.parse(URL);
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/todos";
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/todo";
        // used for the UriMacher
        private static final int TODOS = 10;
        private static final int TODO_ID = 20;

        private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
        static {
        sURIMatcher.addURI(PROVIDER_NAME, BASE_PATH, TODOS);
        sURIMatcher.addURI(PROVIDER_NAME, BASE_PATH + "/#", TODO_ID);
        }
        @Override
        public boolean onCreate() {
           database = new MySQLiteHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);

        // Set the table
        queryBuilder.setTables(MySQLiteHelper.TABLE_PRODUTO);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case TODOS:
                break;
            case TODO_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(MySQLiteHelper.COLUMN_IDPRODUTO + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase db = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case TODOS:
                id = sqlDB.insert(MySQLiteHelper.TABLE_PRODUTO, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case TODOS:
                rowsDeleted = sqlDB.delete(MySQLiteHelper.TABLE_PRODUTO, selection,
                        selectionArgs);
                break;
            case TODO_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(
                            MySQLiteHelper.TABLE_PRODUTO,
                            MySQLiteHelper.COLUMN_IDPRODUTO + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(
                            MySQLiteHelper.TABLE_PRODUTO,
                            MySQLiteHelper.COLUMN_IDPRODUTO + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case TODOS:
                rowsUpdated = sqlDB.update(MySQLiteHelper.TABLE_PRODUTO,
                        values,
                        selection,
                        selectionArgs);
                break;
            case TODO_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqlDB.update(MySQLiteHelper.TABLE_PRODUTO,
                            values,
                            MySQLiteHelper.COLUMN_IDPRODUTO + "=" + id,
                            null);
                } else {
                    rowsUpdated = sqlDB.update(MySQLiteHelper.TABLE_PRODUTO,
                            values,
                            MySQLiteHelper.COLUMN_IDPRODUTO + "=" + id
                                    + " and "
                                    + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = { MySQLiteHelper.COLUMN_IDPRODUTO,
                MySQLiteHelper.COLUMN_LOCAL, MySQLiteHelper.COLUMN_PRECO,
                MySQLiteHelper.COLUMN_SNOME };
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }

}
