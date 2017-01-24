package com.example.ndf25.favoritelocation.Control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import java.io.File;

/**
 * Created by ndf25 on 2017/01/24.
 */

public class MySQLiteHelper {
    public SQLiteDatabase db;
    private final MySQLiteOpenHelper mDbOpenHelper;

    // コンストラクタ
    public MySQLiteHelper (final Context context) {
        this.mDbOpenHelper = new MySQLiteOpenHelper(context);
        establishDb();
    }

    // Dbへの接続
    private void establishDb() {
        if (this.db == null) {
            this.db = this.mDbOpenHelper.getWritableDatabase();
        }
    }

    public void cleanup() {
        if (this.db != null) {
            this.db.close();
            this.db = null;
        }
    }

    /**
     * Databaseが削除できればtrue。できなければfalse
     * @param context
     * @return
     */
    public boolean isDatabaseDelete(final Context context) {
        boolean result = false;
        if (this.db != null) {
            File file = context.getDatabasePath(mDbOpenHelper.getDatabaseName());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                result = this.db.deleteDatabase(file);
            }
        }
        return result;
    }
}
