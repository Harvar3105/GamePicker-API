package Entities;

public class Tag extends BaseEntity {
//    public int id;
//    public String name;
//    public String description;

    public Tag(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString(){return String.valueOf(this.id) + ';' + this.name + ';' + this.description;
    }
}
