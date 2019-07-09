package com.zetcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zetcode.model.City;
import com.zetcode.repository.CityRepository;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void run(String... args) throws Exception {



        cityRepository.save(new City("Bratislava", 432000));
        cityRepository.save(new City("Budapest", 1759000));
        cityRepository.save(new City("Prague", 1280000));
        cityRepository.save(new City("Warsaw", 1748000));
        cityRepository.save(new City("Los Angeles", 3971000));
        cityRepository.save(new City("New York", 8550000));
        cityRepository.save(new City("Edinburgh", 464000));

       City city = new City("Jiquilpan", 20000);

        JSONObject JsonObject = new JSONObject();

        JsonObject.put("", city);

        Files.write(Paths.get("target/cities.json"), JsonObject.toJSONString().getBytes());

        logger.info("# of cities: {}", cityRepository.count());

        logger.info("All cities unsorted:");
        var cities = cityRepository.findAll();
        logger.info("{}", cities);

        logger.info("------------------------");

        logger.info("All cities sorted by name in descending order");
        var sortedCities = cityRepository.findAll(new Sort(Sort.Direction.DESC, "name"));
        logger.info("{}", sortedCities);

        logger.info("------------------------");

        logger.info("Deleting all cities");
        cityRepository.deleteAllInBatch();

        logger.info("# of cities: {}", cityRepository.count());

       logger.info(JsonObject.toJSONString());
    }
}