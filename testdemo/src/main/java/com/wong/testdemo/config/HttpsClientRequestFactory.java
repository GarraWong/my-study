package com.wong.testdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * https配置类
 *
 * @author : WangYumou
 * @version : 1.0
 * Create in 2021/6/15 15:25
 */
public class HttpsClientRequestFactory extends SimpleClientHttpRequestFactory {

    private static final Logger logger = LoggerFactory.getLogger(HttpsClientRequestFactory.class);


    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        try {
            if (!(connection instanceof HttpsURLConnection)) {
                logger.error("期望一个httpsUrlConnection" );
                throw new RuntimeException("期望一个HttpsURLConnection");
            }
            HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException { }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            httpsConnection.setSSLSocketFactory(new MyCustomSSLSocketFactory(sslContext.getSocketFactory()));
            httpsConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            super.prepareConnection(httpsConnection, httpMethod);
        } catch (Exception e) {
            logger.error("prepareConnection发生了异常" );
        }
    }

    private static class MyCustomSSLSocketFactory extends SSLSocketFactory {

        private final SSLSocketFactory delegate;

        public MyCustomSSLSocketFactory(SSLSocketFactory delegate) {
            this.delegate = delegate;
        }

        // 返回默认启用的密码套件。除非一个列表启用，对SSL连接的握手会使用这些密码套件。
        // 这些默认的服务的最低质量要求保密保护和服务器身份验证
        @Override
        public String[] getDefaultCipherSuites() {
            return delegate.getDefaultCipherSuites();
        }

        // 返回的密码套件可用于SSL连接启用的名字
        @Override
        public String[] getSupportedCipherSuites() {
            return delegate.getSupportedCipherSuites();
        }

        @Override
        public Socket createSocket(final Socket socket, final String host, final int port,
                                   final boolean autoClose) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final String host, final int port) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final String host, final int port, final InetAddress localAddress,
                                   final int localPort) throws
                IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final InetAddress host, final int port) throws IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port);
            return overrideProtocol(underlyingSocket);
        }

        @Override
        public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress,
                                   final int localPort) throws
                IOException {
            final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
            return overrideProtocol(underlyingSocket);
        }

        private Socket overrideProtocol(final Socket socket) {
            if (!(socket instanceof SSLSocket)) {
                logger.error("不是SSL Socket");
                throw new RuntimeException("不是SSL Socket");
            }
            ((SSLSocket) socket).setEnabledProtocols(new String[]{"TLSv1"});
            return socket;
        }
    }
}
