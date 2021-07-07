package com.hrz.crm.settings.web.Exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/10/21:06
 * @Description:
 */
public class UserExpireTimeException extends MyException{
    public UserExpireTimeException() {
        super();
    }

    public UserExpireTimeException(String message) {
        super(message);
    }
}
