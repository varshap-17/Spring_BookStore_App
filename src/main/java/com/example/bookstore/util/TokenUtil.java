package com.example.bookstore.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private static final String token_secret="Varsha";
    //create a token with the id parameter as a claim it then returns the same token
    public String createToken(Long id){
        Algorithm algorithm=Algorithm.HMAC256(token_secret);
        String token= JWT.create().withClaim("userid",id).sign(algorithm);
        return token;
    }
    // This method decodes the passed token and returns the id claim. If the verification fails it will throw an exception
    public Long decodeToken(String token)throws SignatureVerificationException{
        // We specify the algorithm for the verifier here and then build the verifier in the next step
        Verification verification=JWT.require(Algorithm.HMAC256(token_secret));
        JWTVerifier verifier=verification.build();
        // We verify and decode the token using the verifier. If the token is incorrect it will throw an exception
        DecodedJWT decodedJWT=verifier.verify(token);
        // We extract the claim from the decoded token and the convert the claim to long type. We then return this id.
        Claim claim=decodedJWT.getClaim("userid");
        Long id=claim.asLong();
        return id;
    }
}
