package com.tpig.githubsdk.exception;

/**
 * Created by wangruoruo on 2019/6/20.
 */
public class GitRequestException extends Exception{
    public GitRequestException() {
    }

    public GitRequestException(String message) {
        super(message);
    }

    public GitRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitRequestException(Throwable cause) {
        super(cause);
    }

    public GitRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
