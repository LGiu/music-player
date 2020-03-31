package br.com.global.Util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs {

    public static void error(Class clazz, String msg) {
        Logger.getLogger(clazz.getName()).log(Level.SEVERE, msg);
    }
}
