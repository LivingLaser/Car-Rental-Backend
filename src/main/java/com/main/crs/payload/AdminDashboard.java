package com.main.crs.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDashboard {
	
	private String totalUsers;
	private String totalOwners;
	private String totalRevenue;
	private String totalBookings;
	private String activeCars;
	private String bookedCars;
	private String confirmedBookings;
	private String canceledBookings;
	private String declinedBookings;

}
