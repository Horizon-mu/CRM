package com.hrz.crm.settings.web.Exception;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Horizon~muu
 * @Date: 2021/05/10/21:07
 * @Description:
 */
public class UserLockStateException extends MyException{
    public UserLockStateException() {
        super();
    }

    public UserLockStateException(String message) {
        super(message);
    }
}
