package Entities;

public class Publisher extends BaseEntity{
//    public int id;
//    public String name;
//    public String description;
    public String hq; //location

    public Publisher(int id, String name, String description, String hq){
        this.id = id;
        this.name = name;
        this.description = description;
        this.hq = hq;
    }

    @Override
    public String toString(){
        return String.valueOf(this.id) + ';' + this.name + ';' + this.description + ';' + this.hq;
    }
}
