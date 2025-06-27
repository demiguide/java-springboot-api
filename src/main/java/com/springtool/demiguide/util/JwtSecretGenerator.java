package com.springtool.demiguide.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtSecretGenerator {
    // 🔐 สร้าง HMAC Key
    public static String genSecretKey(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        return base64Key;
    }
}

