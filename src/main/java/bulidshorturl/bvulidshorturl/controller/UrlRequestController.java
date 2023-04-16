package bulidshorturl.bvulidshorturl.controller;

import bulidshorturl.bvulidshorturl.models.UrlMapping;
import bulidshorturl.bvulidshorturl.models.UrlRequest;
import bulidshorturl.bvulidshorturl.service.UrlRequestservice;
import bulidshorturl.bvulidshorturl.service.urlmappinservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1")
public class UrlRequestController {
    @Autowired
    private UrlRequestservice urlRequestservice;


    @GetMapping("/{shortUrl}")
    public String redirectToOriginalUrl(@PathVariable String shortUrl) {

  return  urlRequestservice.redirect_To_Original_Url(shortUrl);
    }
}
