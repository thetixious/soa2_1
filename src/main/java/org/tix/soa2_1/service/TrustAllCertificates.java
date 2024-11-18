package org.tix.soa2_1.service;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;

public class TrustAllCertificates {
    public static void disableSslVerification() throws Exception {
        // Создаем TrustManager, который не будет проверять сертификаты
        TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Инициализируем SSLContext с этим TrustManager
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCertificates, new java.security.SecureRandom());

        // Устанавливаем этот SSLContext в систему
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Отключаем проверку хостов
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
}
