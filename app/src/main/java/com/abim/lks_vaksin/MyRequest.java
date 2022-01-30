package com.abim.lks_vaksin;

public class MyRequest {
    private static final String baseURL = "http://192.168.47.120:666/";
    private static final String loginURL = "api/login";
    private static final String vaksinURL = "api/vaksin";

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getLoginURL() {
        return getBaseURL() + loginURL;
    }

    public static String getVaksinURL() {
        return getBaseURL() + vaksinURL;
    }
}
