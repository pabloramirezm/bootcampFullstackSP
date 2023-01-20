package com.project.portal;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class FinancialPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialPortalApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper mapper = new ModelMapper();
		mapper.addConverter(this.dateToLongConverter());
		mapper.addConverter(this.longToDateConverter());
		return mapper;
	}
	private Converter<Date, Long> dateToLongConverter() {
		return context -> context.getSource().getTime();
	}

	private Converter<Long, Date> longToDateConverter() {
		return context -> {
			final Calendar calendar = Calendar.getInstance(Locale.getDefault());
			calendar.setTimeZone(TimeZone.getDefault());
			calendar.setTimeInMillis(context.getSource());
			return calendar.getTime();
		};
	}

}
