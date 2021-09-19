package com.example.DataClass;

/*
    Class designed to store the info of sale, never be used
 */
public class Sell {

    private String ID;
    private String seller_id;
    private String price;
    private String name;
    private String description;
    private String subject_id;
    private String status;

    public Sell(String ID,String seller_id,String price,String name,String description,String subject_id,String status){
        this.ID=ID;
        this.seller_id=seller_id;
        this.price=price;
        this.name=name;
        this.description=description;
        this.subject_id=subject_id;
        this.status=status;
    }


}
