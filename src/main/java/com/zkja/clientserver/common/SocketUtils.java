package com.zkja.clientserver.common;

import java.io.InputStream;
import java.net.Socket;

/**
 * @author zzr
 */
public class SocketUtils {
    private Socket socket;

    public SocketUtils(Socket socket){
        this.socket = socket;
    }
}
