package com.manavas.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBeanStaticFiltering filtering(){
        return new SomeBeanStaticFiltering("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBeanStaticFiltering> filterList(){
        return Arrays.asList(
                new SomeBeanStaticFiltering("value1", "value2", "value3"),
                new SomeBeanStaticFiltering("value4", "value5", "value6"),
                new SomeBeanStaticFiltering("value7", "value8", "value9")
        );
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue filteringDynamic(){
        SomeBeanDynamicFiltering sbdf = new SomeBeanDynamicFiltering("value1", "value2", "value3");
        return filterJsonData(sbdf, new String[]{"field1", "field3"});

    }

    @GetMapping("/filtering-list-dynamic")
    public MappingJacksonValue filterListDynamic(){

        List<SomeBeanDynamicFiltering> list = Arrays.asList(
                new SomeBeanDynamicFiltering("value1", "value2", "value3"),
                new SomeBeanDynamicFiltering("value4", "value5", "value6"),
                new SomeBeanDynamicFiltering("value7", "value8", "value9")
        );

        return filterJsonData(list, new String[]{"field2", "field3"});
    }

    private static MappingJacksonValue filterJsonData(Object object, String[] fieldsToFilter) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);
        mappingJacksonValue.setFilters(
                new SimpleFilterProvider().addFilter(
                        "SomeBeanFilter", SimpleBeanPropertyFilter.filterOutAllExcept(fieldsToFilter)));
        return mappingJacksonValue;
    }
}
