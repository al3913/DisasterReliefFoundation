package com.ufund.api.model;

public class Need {
    private String name;
    private double cost;
    private int quantity; 
    private String type;  
    private int id;

    public Need(String name, double cost, int quantity, String type, int id){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public double getCost(){
        return this.cost;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public String getType(){
        return this.type;
    }

    public int getId(){
        return this.id;
    }

}
