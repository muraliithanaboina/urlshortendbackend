package bulidshorturl.bvulidshorturl.controller;

import bulidshorturl.bvulidshorturl.models.UrlMapping;
import bulidshorturl.bvulidshorturl.models.UrlRequest;
import bulidshorturl.bvulidshorturl.service.urlmappinservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class urlshortencontroller {
    @Autowired
   private urlmappinservice urlmappinservice ;


     @PostMapping("/shorten")
       public UrlMapping shortenUrl(@Validated @RequestBody UrlRequest urlRequest) {


          return urlmappinservice.shortenUrl(urlRequest);
      }

}

