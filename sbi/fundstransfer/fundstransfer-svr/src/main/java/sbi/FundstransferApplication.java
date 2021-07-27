package sbi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringCloudApplication
@EnableCaching
public class FundstransferApplication {

    public static void main(final String[] args) {
        SpringApplication.run(FundstransferApplication.class, args);
    }

}