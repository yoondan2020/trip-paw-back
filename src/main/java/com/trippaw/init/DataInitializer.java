package com.trippaw.init;

import com.trippaw.place.PlaceAPIService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner  {

    private PlaceAPIService placeAPIService;

    public DataInitializer (PlaceAPIService placeAPIService) {
        this.placeAPIService = placeAPIService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("first");

        placeAPIService.FetchPlace();

    }
}
