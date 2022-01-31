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
