package com.Alex.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {


    //정적 응담 필터링 적용
    //@JsonProperty
    //@JsonIgnore
    @GetMapping("/filter")
    public SomeBean filtering() {
        return new SomeBean("bean1", "bean2", "bean3");
    }

    @GetMapping("/filter-array")
    public List<SomeBean> filtering적Array() {
        return Arrays.asList(new SomeBean("bean1", "bean2", "bean3")
                , new SomeBean("bean4", "bean5", "bean6"));
    }


    //동적 응담 필터링 적용
    //MappingJacksonValue
    //SimpleBeanPropertyFilter
    @GetMapping("/filtering") //field2
    public MappingJacksonValue filteringDynic() {

        SomeBeanDynic someBean = new SomeBeanDynic("value1","value2", "value3");

        //MappingJacksonValue 을 이용하여 응답값 필터링
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        //bean1,bean2 키값만 내보내는 필터 적용
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("bean1","bean2");

        //SomeBeanFilter 명칭은 응답할 빈에 값과 일치해야함 @JsonFilter("SomeBeanFilter") 붙인
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );

        mappingJacksonValue.setFilters(filters );


        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list") //field2, field3
    public MappingJacksonValue filteringListDynic() {
        List<SomeBeanDynic> list = Arrays.asList(new SomeBeanDynic("value1","value2", "value3"),
                new SomeBeanDynic("value4","value5", "value6"));

        //MappingJacksonValue 을 이용하여 응답값 필터링
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        //bean1,bean2 키값만 내보내는 필터 적용
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("bean2","bean3");

        //SomeBeanFilter 명칭은 응답할 빈에 값과 일치해야함 @JsonFilter("SomeBeanFilter") 붙인
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );

        mappingJacksonValue.setFilters(filters );

        return mappingJacksonValue;
    }


    @GetMapping("/filtering-list-except")
    public MappingJacksonValue filteringListExcept() {
        List<SomeBeanDynic> list = Arrays.asList(
                new SomeBeanDynic("value1", "value2", "value3"),
                new SomeBeanDynic("value4", "value5", "value6")
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        // bean1, bean2 키값을 제외한 필터 적용
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.serializeAllExcept("bean1", "bean2");

        // SomeBeanFilter 명칭은 응답할 빈에 값과 일치해야함 @JsonFilter("SomeBeanFilter")를 사용한 경우
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

}

