package com;

public class Need {
    private String name;
    private double cost;
    private int quantity; 
    private String type;  

    public Need(String name, double cost, int quantity, String type){
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.type = type;
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

}
