package com.revature;

import com.revature.controllers.AppExceptionHandler;
import com.revature.controllers.AuthController;
import com.revature.controllers.UserController;
import com.revature.util.LoggingUtil;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApplication {

    private final UserController userController = new UserController();
    private final LoggingUtil loggingUtil = new LoggingUtil();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private final AuthController authController = new AuthController();

    private Javalin app = Javalin.create().routes(()->{
        path("user",()->{
            post(userController::handleCreateUser);
            get(userController::handleAllUsers);
            delete(userController::handleDeleteUser);
            path("{id}", ()->{
                get(userController::handleOneUser);
                put(userController::handleUpdateUser);
                path("deposit", ()->{
                    path("{account}", ()->{
                        put(userController::handleDeposit);
                    });
                });
                path("withdraw", ()->{
                    path("{account}", ()->{
                        put(userController::handleWithdraw);
                    });
                });
                path("transfer", ()->{
                    path("{account}", ()->{
                        put(userController::handleTransfer);
                    });
                });
            });
        });

        path("login", ()->{
            post(authController::authenticate);
        });
        //before("*", loggingUtil::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

    public void start(int port){
        app.start(port);
    }
}
