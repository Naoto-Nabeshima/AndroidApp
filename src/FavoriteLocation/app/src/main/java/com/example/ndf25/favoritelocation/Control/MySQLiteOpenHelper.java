package com.example.ndf25.favoritelocation.Control;

/**
 * Created by ndf25 on 2017/01/24.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    public Context m_context;
    public static final String TAG = "MySQLiteOpenHelper";
    public static final String DB_NAME = "android_sqlite";
    public static final int DB_VERSION = 1;

    // コンストラクタ
    public MySQLiteOpenHelper(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.m_context = context;
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Log.d(TAG, "onCreate version : " + db.getVersion());
        this.execFileSQL(db, "create_table.sql");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Log.d(TAG, "onUpgrade version : " + db.getVersion());
        Log.d(TAG, "onUpgrade oldVersion : " + oldVersion);
        Log.d(TAG, "onUpgrade newVersion : " + newVersion);
    }

    /**
     * assetsフォルダのSQLファイルを実行する
     * @param db
     * @param fileName
     */
    private void execFileSQL(SQLiteDatabase db, String fileName) {
        InputStream mInputStream = null;
        InputStreamReader mInputStreamReader = null;
        BufferedReader mBufferedReader = null;

        try {


            // 文字コード(UTF-8)を指定して、ファイルを読み込み
            mInputStream = m_context.getAssets().open(fileName);
            mInputStreamReader = new InputStreamReader(mInputStream, "UTF-8");
            mBufferedReader = new BufferedReader(mInputStreamReader);

            // ファイル内の全ての行を処理
            String s;
            while((s = mBufferedReader.readLine()) != null){
                // 先頭と末尾の空白除去
                s = s.trim();

                // 文字が存在する場合（空白行は処理しない）
                if (0 < s.length()){
                    // SQL実行
                    db.execSQL(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (mInputStream != null) {
                try {
                    mInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mInputStreamReader != null) {
                try {
                    mInputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mBufferedReader != null) {
                try {
                    mBufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
