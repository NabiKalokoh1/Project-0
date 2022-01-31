package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

//makes sure user-pass inputs are actually in the table 'user'
public class AuthController {
    private UserService userService = new UserService();

    public void authenticate(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        User user = userService.getByUserAndPass(username, password);

        if (user == null){
            throw new UnauthorizedResponse("Incorrect Credentials");
        } else{
            //for the enums
            String token = user.getType() + "-TOKEN";
            ctx.header("Authorization", token);
            ctx.status(200);
        }
    }

    //checks for specific token (might have to make two more for employee and admin)
    //come back to this since i might need to flip the lines
    public void authorizeUser(Context ctx){
        String authHeader = ctx.header("Authorization");
        if (authHeader != null){
            if(authHeader.equals("CONSUMER-TOKEN")){
                return;
            } else if (authHeader.equals("EMPLOYEE-TOKEN") || authHeader.equals("ADMIN-TOKEN")){
                throw new ForbiddenResponse("You are unable to access this feature.");
            }
        }
        throw new UnauthorizedResponse("Please try again.");
    }
}
