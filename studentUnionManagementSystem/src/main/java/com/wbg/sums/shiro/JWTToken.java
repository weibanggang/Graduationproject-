package com.wbg.sums.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT token
 *
 * @author WBG
 * @date 2019/02/20
 */
public final class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = 9028340303836653244L;
    //密钥
    private String jwt;
    private String host;

    public JWTToken(String jwt) {
        this.jwt = jwt;
    }

    public JWTToken(String jwt, String host) {
        this.jwt = jwt;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
            return this.jwt;
        }

        public String getHost () {
            return this.host;
        }

    }
