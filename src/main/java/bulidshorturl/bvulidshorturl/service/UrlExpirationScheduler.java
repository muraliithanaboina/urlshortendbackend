package bulidshorturl.bvulidshorturl.service;

import bulidshorturl.bvulidshorturl.dao.UrlMappindao;
import bulidshorturl.bvulidshorturl.models.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class UrlExpirationScheduler {

    private Timer timer;
    private UrlMappindao urlMappindao;

    @Autowired
    public UrlExpirationScheduler(UrlMappindao urlMappindao) {
        this.urlMappindao = urlMappindao;
        timer = new Timer();
        timer.schedule(new ExpirationTask(this.urlMappindao), 0, 60 * 1000); // Run every minute
    }

    static class ExpirationTask extends TimerTask {

        private UrlMappindao urlMappindao;

        public ExpirationTask(UrlMappindao urlMappindao) {
            this.urlMappindao = urlMappindao;
        }

        public void run() {
            Date now = new Date();
            long expirationTimeMillis = now.getTime() - (5 * 60 * 1000); // 5 minutes ago


            // Query the database for expired URLs
            List<UrlMapping> expiredUrls = urlMappindao.findByCreationTimeBefore(expirationTimeMillis);

            // Remove the expired URLs from the database

                for (UrlMapping urlMapping : expiredUrls) {
                    urlMappindao.delete(urlMapping);
                }

        }
    }
}
