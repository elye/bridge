package com.livefront.bridge.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorageUtil {

    public static void deleteFile(@NonNull String filename, Context context) {
        Log.d("FileTracking", "DeleteFile start:" + filename);
        File file = new File(context.getCacheDir().getAbsolutePath() + "/" + filename);

        if (file.exists()) {
            file.delete();
            Log.d("FileTracking", "DeleteFile end:" + filename);
        }
    }

    public static void writeToFile(@NonNull String filename, @NonNull byte[] data, Context context) {
        try {
            Log.d("FileTracking", "WriteToFile start:" + filename);

            File file = new File(context.getCacheDir().getAbsolutePath() + "/" + filename);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            Log.d("FileTracking", "WriteToFile end:" + filename);

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] readFromFile(@NonNull String filename, Context context) {
        try {
            Log.d("FileTracking", "ReadFromFile start:" + filename);
            File file = new File(context.getCacheDir().getAbsolutePath() + "/" + filename);
            FileInputStream fis = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byte[] bytes = bos.toByteArray();

            fis.close();
            Log.d("FileTracking", "ReadFromFile end:" + filename);

            return bytes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
