package com.nunens.pinkd.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.nunens.pinkd.dto.RequestDTO;
import com.nunens.pinkd.dto.ResponseDTO;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.java_websocket.handshake.ServerHandshake;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

/**
 * Utility class to manage web socket communications for the application
 * Created by aubreyM on 2014/08/10.
 */
public class WebSocketUtil {
    static final String LOG = WebSocketUtil.class.getSimpleName();
    static final Gson gson = new Gson();
    static WebSocketListener webSocketListener;
    static RequestDTO request;
    static Context ctx;
    static long start, end;
    static WebSocketClient mWebSocketClient;
    static SharedPreferences sp;
    static SharedPreferences.Editor ed;

    public static void disconnectSession() {
        if (mWebSocketClient != null) {
            mWebSocketClient.close();
            Log.e(LOG, "@@@@@@@@ webSocket session disconnected");
        }
    }

    public static void sendRequest(Context c, RequestDTO req, final String extension, WebSocketListener listener) {
        webSocketListener = listener;
        request = req;
        ctx = c;
        /*TimerUtil.startTimer(new TimerUtil.TimerListener() {
            @Override
            public void onSessionDisconnected() {
                try {
                    openConnection(request, extension);
                    return;
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });*/
        if (WebCheck.checkMobileNetwork(ctx) || WebCheck.checkWifi(ctx)) {
            try {
                if (mWebSocketClient == null) {
                    openConnection(request, extension);
                    return;
                } else {
                    String json = gson.toJson(req);
                    mWebSocketClient.send(json);
                    Log.d(LOG, "########### web socket message sent\n" + json);
                }
            } catch (WebsocketNotConnectedException e) {
                try {
                    Log.e(LOG, "WebsocketNotConnectedException. Problems with web socket", e);
                    openConnection(request, extension);
                } catch (URISyntaxException e1) {
                    Log.e(LOG, "Problems with web socket", e);
                    webSocketListener.onError("Problem starting server socket communications\n" + e1.getMessage());
                }
            } catch (URISyntaxException e) {
                Log.e(LOG, "Problems with web socket", e);
                webSocketListener.onError("Problem starting server socket communications");
            }
        }else{
            ToastUtil.toast(ctx, "No network connectivity");
            WebCheck.startWirelessSettings(ctx);
        }
    }

    private static void openConnection(final RequestDTO req, String extension) throws URISyntaxException {
        URI uri = new URI(Statics.WEBSOCKET_URL + extension);
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.w(LOG, "########## WEBSOCKET Opened: " + serverHandshake.getHttpStatusMessage() + " elapsed ms: " + (end - start));
            }

            @Override
            public void onMessage(ByteBuffer bb) {
                end = System.currentTimeMillis();
                ResponseDTO resp;
                try {
                    if (bb.capacity() > 512) {
                        resp = ZipUtil.unzipString(bb);
                    } else {
                        resp = ZipUtil.unpack(bb);
                    }
                    if (resp.getStatusCode() == 0 && resp.getMessage().equals("Handshake successful")) {
                        String json = gson.toJson(req);
                        mWebSocketClient.send(json);
                        Log.d(LOG, "########### websocket message sent\n" + json.toString());
                    } else {
                        if (resp.getStatusCode() == 0) {
                            String json = gson.toJson(req);
                            //mWebSocketClient.send(json);
                            webSocketListener.onMessage(resp);
                        } else {
                            Log.d(LOG, "########### websocket message sent\n");
                            ToastUtil.toast(ctx, resp.getMessage());
                            webSocketListener.onMessage(resp);
                        }
                    }

                } catch (ZipUtil.ZipException e) {
                    Log.d("ERROR", "Error unpacking Byte Buffer", e);
                    webSocketListener.onError("Failed to parse response from server");
                }
            }

            @Override
            public void onMessage(String response) {
                TimerUtil.killTimer();
                end = System.currentTimeMillis();
                Log.i(LOG, "########## onMessage, length: " + response.length() + " elapsed: " + getElapsed());
                Log.d("RESPONSE", "Response: " + response);
                try {
                    ResponseDTO resp = gson.fromJson(response, ResponseDTO.class);
                    if (resp.getStatusCode() == 0) {
                        if (resp.getSessionID() != null) {
                            String json = gson.toJson(req);
                            mWebSocketClient.send(json);
                            Log.d(LOG, "########### websocket message sent\n" + json.toString());
                        } else {
                            webSocketListener.onMessage(resp);
                        }
                    } else {
                        webSocketListener.onMessage(resp);
                        webSocketListener.onError(resp.getMessage());
                    }
                } catch (Exception e) {
                    Log.e(LOG, "Failed to parse response from server", e);
                    webSocketListener.onError("Failed to parse response from server");
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.e(LOG, "########## WEBSOCKET onClose, status code:  " + i + " boolean: " + b);
                webSocketListener.onClose();
            }

            @Override
            public void onError(Exception e) {
                Log.e(LOG, "----------> onError connectWebSocket", e);
                webSocketListener.onError("Server communications failed. Please try again");
            }
        };
        Log.d(LOG, "#### #### -------------> starting mWebSocketClient.connect ...");
        mWebSocketClient.connect();
    }

    public static String getElapsed() {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));

        return "" + m.doubleValue() + " seconds";
    }

    public interface WebSocketListener {
        public void onMessage(ResponseDTO response);

        public void onClose();

        public void onError(String message);

    }
}
