package com.main.crs.config;

import java.io.File;

public interface AppConstants {
	
	Integer USER_ADMIN = 500;
	Integer USER_OWNER = 501;
	Integer USER_NORMAL = 502;
	String PAGE_NUMBER = "0";
	String PAGE_SIZE = "10";
	String USER_SORT_BY = "name";
	String CAR_SORT_BY = "modelName";
	String CAR_SORT_BY_NATIVE = "model_name";
	String USER_IMAGE_PATH = new File("src/main/resources/static/images/user/").getAbsolutePath();
	String CAR_IMAGE_PATH = new File("src/main/resources/static/images/cars/").getAbsolutePath();

}
