package bulidshorturl.bvulidshorturl.service;

import bulidshorturl.bvulidshorturl.dao.UrlMappindao;
import bulidshorturl.bvulidshorturl.models.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlRequestserviceimpl implements UrlRequestservice{

    @Autowired
    private UrlMappindao urlMappindao;
    @Override
    public String redirect_To_Original_Url( String shortUrl) {
      Optional<UrlMapping>urlMapping= urlMappindao.findbyurl(shortUrl);
        if(LocalDateTime.now().getMinute()-urlMapping.get().getExpirationTime().getMinute()>5){
            urlMappindao.delete(urlMapping.get());
            return "not found";
        }

        else {// Redirect the user to the original URL
            return urlMapping.get().getOriginalUrl();
        }
    }


    }

