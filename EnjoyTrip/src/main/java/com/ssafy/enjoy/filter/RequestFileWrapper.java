package com.ssafy.enjoy.filter;


import software.amazon.awssdk.utils.IoUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

public class RequestFileWrapper extends HttpServletRequestWrapper{
    private byte[] bytes;
    private String requestBody;
    private final Map<String,String> customHeaders;

    public RequestFileWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream in = super.getInputStream();
        this.bytes = IoUtils.toByteArray(in);
        this.requestBody =  new String(this.bytes);
        customHeaders = new HashMap<>();
        System.out.println("Filter" + getRequestBody());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bytes);
        return new ServletInputStreamImpl(byteArrayInputStream);
    }

    public String getRequestBody() {
        return requestBody;
    }

    class ServletInputStreamImpl extends ServletInputStream {
        private InputStream inputStream;

        public ServletInputStreamImpl(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() throws IOException {
            return this.inputStream.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return this.inputStream.read();
        }
    }

}
