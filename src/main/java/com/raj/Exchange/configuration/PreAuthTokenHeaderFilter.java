package com.raj.Exchange.configuration;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthTokenHeaderFilter
        extends AbstractPreAuthenticatedProcessingFilter {

    private String authHeaderName;
    private String requestUrl;
    private String requestMethod;
    private boolean headerIsNull;

    public PreAuthTokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        this.requestUrl = request.getRequestURL().toString();
        //System.out.println(requestUrl);
        this.requestMethod = request.getMethod().toString();
        if (request.getHeader("key") == null) {
            System.out.println("Brak tokena !!!");
            this.headerIsNull = true;
        }
        // System.out.println("PRe auth" + request.getRequestURL());
        return request.getHeader(authHeaderName);

    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        //System.out.println("PRe auth" + request.getRequestURL());
        return request.getHeader("N/a");
        //return request.getRequestURL();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public boolean isHeaderIsNull() {
        return headerIsNull;
    }
}