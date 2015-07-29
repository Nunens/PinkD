package com.nunens.pinkd.util;


import android.util.Log;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.ResponseDTO;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by aubreyM on 2014/08/10.
 */
public class ZipUtil {
    static final int BUFFER = 2048;
    static final String LOG = ZipUtil.class.getSimpleName();
    static final Gson gson = new Gson();

    public static ResponseDTO unzipString(ByteBuffer bb) throws ZipException {
        ResponseDTO resp = new ResponseDTO();
        long start = System.currentTimeMillis();
        ZipInputStream zip;
        byte[] zippedBytes = bb.array();
        byte[] unZippedBytes = new byte[BUFFER];
        System.out.println("###----> pack zip zippedFile, capacity: " + bb.capacity());
        StringBuilder string = new StringBuilder();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bb.array());
        int count = 0;
        GZIPInputStream gzipI = null;
        try {
            gzipI = new GZIPInputStream(inputStream, BUFFER);

            while ((count = gzipI.read(unZippedBytes)) != -1) {
                string.append(new String(unZippedBytes, 0, count));
            }

            System.out.println("###----> Unpack zip zippedFile, Length: " + string.length());
        } catch (Exception e) {
            Log.e(LOG, "######## Unable to unpack the zip file", e);
            throw new ZipException();
        } finally {
            try {
                gzipI.close();
                inputStream.close();
            } catch (IOException e) {
                Log.e(LOG, "######## I/O InputStream problem ", e);
            }
        }
        long end = System.currentTimeMillis();
        Log.w(LOG, "######## The time taken to unzip is in ms: " + getElapsed(start, end));
        if (string.equals(null)) throw new ZipException();
        resp = gson.fromJson(string.toString(), ResponseDTO.class);
        return resp;
    }

    private static double getElapsed(long start, long end) {
        BigDecimal d = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return d.doubleValue();
    }

    //TODO implement in the other apps - maybe make a jar of this class
    public static ResponseDTO unpack(ByteBuffer bb) throws ZipException {
        //notify listener
        ResponseDTO response = unpackBytes(bb);
        Log.e(LOG, "##### unpack - telling listener that response object is ready after unpack");
        return response;
    }

    public static ResponseDTO unpackBytes(ByteBuffer bb) throws ZipException {
        Log.d(LOG, "##### unpack - starting to unpack byte buffer: " + bb.capacity());
        InputStream is = new ByteArrayInputStream(bb.array());
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(is));
        ResponseDTO response = new ResponseDTO();
        Log.d(LOG, "##### before the try .....");
        try {
            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[BUFFER];
                int count;
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }
                String filename = ze.getName();
                byte[] bytes = baos.toByteArray();
                String json = new String(bytes);
                Log.e(LOG, "#### Downloaded file: " + filename + ", length: " + json.length()
                        + "\n" + json);
                response = gson.fromJson(json, ResponseDTO.class);
            }
        } catch (Exception e) {
            Log.e(LOG, "Failed to unpack byteBuffer", e);
            throw new ZipException();
        } finally {
            try {
                zis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new ZipException();
            }
        }
        if (response == null) throw new ZipException();
        return response;
    }

    public static class ZipException extends Exception {

    }
}
