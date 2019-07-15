package com.aman.rest.webservices.restfulwebservices.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retriveFilterBeanDynamic() {
		FilterBean bean = new FilterBean("value1", "value2", "value3");

		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
				filterOutAllExcept("field1", "field3");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilterBeanFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(bean);		
		mapping.setFilters(filterProvider);
		return mapping;
	}
}
