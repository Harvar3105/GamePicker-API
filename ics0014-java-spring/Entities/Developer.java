package Entities;

public class Developer extends BaseEntity {
//    public int id;
//    public String name;
//    public String description;
    public String hr; //location

    public Developer (int id, String name, String description, String hr){
        this.id = id;
        this.name = name;
        this.description = description;
        this.hr = hr;
    }

    @Override
    public String toString(){
        return String.valueOf(this.id) + ';' + this.name + ';' + this.description + ';' + this.hr;
    }
}
