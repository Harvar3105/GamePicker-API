package Entities;

import Enums.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Game extends BaseEntity{
//    public int id;
//    public String name;
//    public String description;
    public LinkedList<Developer> developerList = new LinkedList<>();
    public LinkedList<Publisher> publishers = new LinkedList<>();
    public LinkedList<Tag> tags = new LinkedList<>();
    public HashMap<ECurrency, Float> prices = new HashMap<>(); //Currency | price
    public LinkedList<EPlatforms> platforms = new LinkedList<>();
    public float budget;
    public LinkedList<String> languages = new LinkedList<>();

    public Game(int id, String name, String description, float budget){
        this.id = id;
        this.name = name;
        this.description = description;
        this.budget = budget;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(this.id)).append(';')
                .append(this.name).append(';').append(this.description).append(';');

        builder.append("{");
        for (Developer developer : this.developerList) {
            builder.append(developer.id).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        builder.append(';');

        builder.append("{");
        for (Publisher publisher : this.publishers) {
            builder.append(publisher.id).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        builder.append(';');

        builder.append("{");
        for (Tag tag : this.tags) {
            builder.append(tag.id).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        builder.append(';');

        builder.append("{");
        for (Map.Entry<ECurrency, Float> cell : this.prices.entrySet()) {
            builder.append(cell.getKey()).append('|').append(cell.getValue()).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        builder.append(';');

        builder.append("{");
        for (EPlatforms platform : this.platforms) {
            builder.append(platform).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        builder.append(';').append(this.budget).append(';');

        builder.append("{");
        for (String language : this.languages){
            builder.append(language).append(',');
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");

        return builder.toString();
    }
}
