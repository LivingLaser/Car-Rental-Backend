package com.main.crs.config;

import java.io.File;

public interface AppConstants {
	
	Integer USER_ADMIN = 500;
	Integer USER_EMPLOYEE = 501;
	Integer USER_NORMAL = 502;
	String PAGE_NUMBER = "0";
	String PAGE_SIZE = "10";
	String USER_SORT_BY = "name";
	String CAR_SORT_BY = "modelName";
	String PATH = new File("src/main/resources/static/images/").getAbsolutePath();

}
