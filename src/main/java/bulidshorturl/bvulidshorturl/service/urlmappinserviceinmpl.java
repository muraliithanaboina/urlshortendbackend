package bulidshorturl.bvulidshorturl.service;

import bulidshorturl.bvulidshorturl.dao.UrlMappindao;

import bulidshorturl.bvulidshorturl.models.UrlMapping;
import bulidshorturl.bvulidshorturl.models.UrlRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class urlmappinserviceinmpl implements urlmappinservice {
    @Autowired
  private  UrlMappindao urlMappindao;
    @Override
    public UrlMapping shortenUrl(UrlRequest urlRequest) {

        if (isValidUrl(urlRequest.getOriginalUrl())) {


            String hash = DigestUtils.sha256Hex(urlRequest.getOriginalUrl());
            String shortenedUrl = Base64.encodeBase64URLSafeString(hash.getBytes()).substring(0, 8);
            UrlMapping urlMapping = UrlMapping.builder().expirationTime(new Date().getTime())
                    .shortenedUrl(shortenedUrl)
                    .originalUrl(urlRequest.getOriginalUrl()).build();
            return urlMappindao.save(urlMapping);
        }
            return null;

        }

    public static boolean isValidUrl(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
    }

}
