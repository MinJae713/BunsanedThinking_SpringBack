package com.example.bunsanedthinking_springback.global.constants.service.common;

public class S3Constants {
    public static final String META_CONTENT_TYPE = "image/png";
    public static final String TIME_FORMAT = "yyyyMMddHHmmssSSS";
    public static final String REGION = "ap-northeast-2";
    public static final String UNDER_BAR = "_";
    public static final String ENCODE = "UTF-8";
    public static final String URL_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";
    public static final String DELETE_STATE_EXCEPTION = "[Error] AWS S3 서비스 접근에 실패했습니다.";
}
