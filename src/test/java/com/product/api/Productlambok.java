package com.product.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //getter setter

public class Productlambok {
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	// Rating class: Inner class

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder

	public static class Rating {

		private double rate;
		private int count;

	}
}

//This class is also POJO class but The POJO we created with the help of Lambok (not a lenghy lombok)