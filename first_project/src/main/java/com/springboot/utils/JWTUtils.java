package com.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JWTUtils {
    private static final String SING = "!Q@W#E$R";
    public static String getToken(String username){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        String token = JWT.create().withClaim("username",username).withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(SING));
        return token;
    }
    
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
