package com.tenmaker.backupwd.exception;

public class BackupwdRuntimeException extends RuntimeException {
    private String message;

    public BackupwdRuntimeException(String message) {
        super(message);
        this.message = message;
    }
}
