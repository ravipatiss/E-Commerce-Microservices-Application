package com.dailycode.OrderService.controller;

import com.dailycode.OrderService.model.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/authenticate")
public class AuthenticationController {


    public ResponseEntity<AuthenticationResponse> login(
            @AuthenticationPrincipal OidcUser oidcUser,
            Model model,
            @RegisteredOAuth2AuthorizedClient("okta")
            OAuth2AuthorizedClient client
    ) {

        AuthenticationResponse authenticationResponse =
                AuthenticationResponse.builder()
                        .userId(oidcUser.getEmail())
                        .accessToken(client.getAccessToken().getTokenValue())
                        .refreshToken(client.getRefreshToken().getTokenValue())
                        .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                        .authorityList(oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .build();
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}