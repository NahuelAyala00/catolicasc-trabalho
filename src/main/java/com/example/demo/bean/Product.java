	package com.example.demo.bean;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	
	@Entity
	public class Product {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String Name;
	    private String description;
	    private String brand;
		private double price;
	    private int stock;
	
	    

	    public String getBrand() {
	        return brand;
	    }

	    public void setBrand(String brand) {
	        this.brand = brand;
	    }
	    public Long getId() {
	        return id;
	    }
	
	    public void setId(Long id) {
	        this.id = id;
	    }
	
	    public String getName() {
	        return Name;
	    }
	
	    public void setName(String Name) {
	        this.Name = Name;
	    }
	    
	    public String getDescription() {
	 		return description;
	 	}
	
	 	public void setDescription(String description) {
	 		this.description = description;
	 	}
	
	 	public double getPrice() {
	 		return price;
	 	}
	
	 	public void setPrice(double price) {
	 		this.price = price;
	 	}
	
	 	public int getStock() {
	 		return stock;
	 	}
	
	 	public void setStock(int stock) {
	 		this.stock = stock;
	 	}
	
	}
