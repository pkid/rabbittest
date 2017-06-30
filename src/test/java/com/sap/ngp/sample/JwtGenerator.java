//package com.sap.ngp.sample;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.apache.commons.io.IOUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.RsaSigner;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class JwtGenerator {
//    private static final String IDENTITY_ZONE = "uaa";
//    private static final String CLIENT_ID = "testClient";
//
//    // return a value suitable for the HTTP "Authorization" header containing the JWT with the given scopes
//    public String getTokenForAuthorizationHeader(String... scopes) {
//        System.out.println("Bearer " + getToken(scopes));
//        return "Bearer " + getToken(scopes);
//    }
//
//    // return the JWT for the given scopes
//    private String getToken(String... scopes) {
//        ObjectMapper mapper = new ObjectMapper();
//
//        ObjectNode root = mapper.createObjectNode();
//        root.put("client_id", CLIENT_ID);
//        root.put("exp", Integer.MAX_VALUE);
//        root.set("scope", getScopesJSON(scopes));
//        root.put("user_name", "user name");
//        root.put("user_id", "D012345");
//        root.put("email", "testUser@testOrg");
//        root.put("zid", IDENTITY_ZONE);
//
//        return getTokenForClaims(root.toString());
//    }
//
//    // convert Java array into JSON array
//    private ArrayNode getScopesJSON(String[] scopes) {
//        ObjectMapper mapper = new ObjectMapper();
//        ArrayNode scopesArray = mapper.createArrayNode();
//        for (String scope : scopes) {
//            scopesArray.add(scope);
//        }
//        return scopesArray;
//    }
//
//    // sign the claims and return the resulting JWT
//    private String getTokenForClaims(String claims) {
//        RsaSigner signer = new RsaSigner(readFromFile("/privateKey.txt"));
//        return JwtHelper.encode(claims, signer).getEncoded();
//    }
//
//    public String getPublicKey() {
//        String publicKey = readFromFile("/publicKey.txt");
//        return removeLinebreaks(publicKey);
//    }
//
//    private String removeLinebreaks(String input) {
//        return input.replace("\n", "").replace("\r", "");
//    }
//
//    private String readFromFile(String path) {
//        InputStream is = null;
//        try {
//            is = getClass().getResourceAsStream(path);
//            return IOUtils.toString(is);
//        } catch (IOException exception) {
//            throw new IllegalStateException(exception);
//        } finally {
//            IOUtils.closeQuietly(is);
//        }
//    }
//
//    public String getClientId() {
//        return CLIENT_ID;
//    }
//
//    public String getIdentityZone() {
//        return IDENTITY_ZONE;
//    }
//}