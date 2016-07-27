package com.goeuro.app;

import com.goeuro.app.model.Position;
import com.goeuro.app.util.JsonToCsvConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 7/27/2016.
 */
@SpringBootApplication
@Component
public class Application implements CommandLineRunner {

    @Autowired
    private RestClient restClient;

    @Autowired
    JsonToCsvConverter jsonToCsvConverter;

    @Override
    public void run(String... args) {
        if (args.length <= 0 ) {
            System.out.println("Usage: java -jar goEuro.jar <city name>");
        }
        List<Position> positionList = restClient.getPosition(args[0]);

        try(BufferedWriter writer =  new BufferedWriter(new FileWriter("outputcsv.csv"))) {
            positionList.forEach((temp) -> {
                try {
                    writer.write(jsonToCsvConverter.convert(temp).toString());
                } catch (IOException e) {
                }
            });
            if(positionList.size() == 0 ){
                System.out.println(" No position found. Blank CSV File is generated. File name : outputcsv.csv");
            }else {
                System.out.println(" CSV File is generated. File name : outputcsv.csv ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder()
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .sources(Application.class)
                .web(false)
                .run(args);
       // SpringApplication.run(Aapplication.class, args);
    }
}
