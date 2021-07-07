package com.hrz.crm.settings.web.Exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/07/05/17:03
 * @Description:
 */
public class NoLoginException extends MyException{
    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }
}
