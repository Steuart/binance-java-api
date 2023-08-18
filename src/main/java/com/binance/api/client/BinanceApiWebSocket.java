package com.binance.api.client;

import com.binance.api.client.impl.BinanceApiWebSocketListener;
import okhttp3.WebSocket;

import java.io.Closeable;
import java.io.IOException;

/**
 * @Author: wuhaiming
 * @Date: 2023/8/18 11:16
 */
public class BinanceApiWebSocket implements Closeable {

    private final String channel;

    private final BinanceApiWebSocketListener<?> listener;

    private final WebSocket webSocket;

    public BinanceApiWebSocket(String channel, BinanceApiWebSocketListener<?> listener, WebSocket webSocket) {
        this.channel = channel;
        this.listener = listener;
        this.webSocket = webSocket;
    }

    @Override
    public void close() throws IOException {
        final int code = 1000;
        listener.onClosing(webSocket, code, "Active trigger");
        webSocket.close(code, null);
        listener.onClosed(webSocket, code, "Active trigger");
    }

    public String getChannel() {
        return channel;
    }

    public BinanceApiWebSocketListener<?> getListener() {
        return listener;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }
}
