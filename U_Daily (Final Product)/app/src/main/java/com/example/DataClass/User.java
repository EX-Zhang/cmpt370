package com.example.DataClass;

/*
    Class to store the user of the app.
 */
public class User {

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String University;
    private boolean isProfessor;    // A boolean variable store the identity of a user

    public User(String username, String first_name, String last_name,String email,String Univrsity, String Identity){
        this.username=username;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.University=Univrsity;

        if(Identity.equals("Professor")){
            this.isProfessor=true;
        }
        else {
            this.isProfessor=false;
        }
    }



    public String toString(){
        String str="";

        str+="Username: "+this.username+"\n";
        str+="Full name: "+this.first_name+" "+this.last_name+"\n";
        str+="Email: "+this.email+"\n";
        str+="University: "+this.University+"\n";
        str+="Identity: "+(this.isProfessor?"Professor":"Student")+"\n";

        return str;
    }
}
