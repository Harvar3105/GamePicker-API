import Entities.*;
import Enums.ECurrency;
import Enums.EPlatforms;

import java.io.*;
import java.util.*;

public class DAO {

    private static final String Separator = ";";
    private static final String DeveloperHeader = "ID || Name || Description || Hr";
    private static final String DeveloperLocation = "Tables\\Developer.csv";
    private static final String PublisherHeader = "ID || Name || Description || Hr";
    private static final String PublisherLocation = "Tables\\Publisher.csv";
    private static final String TagHeader = "ID || Name || Description";
    private static final String TagLocation = "Tables\\Tag.csv";
    private static final String Game = "ID || Name || Description || Developers || Publishers || Tags || Prices || Platforms || budget || Languages";
    private static final String GameLocation = "Tables\\Game.csv";

    public <T extends BaseEntity> void Save(T entity) {
        String location = "";
        if (entity.getClass().equals(Developer.class)){
            location = DeveloperLocation;
        } else if (entity.getClass().equals(Game.class)){
            location = GameLocation;
        } else if (entity.getClass().equals(Publisher.class)){
            location = PublisherLocation;
        } else if (entity.getClass().equals(Tag.class)){
            location = TagLocation;
        } else throw new RuntimeException("No such entity " + entity);

        try {
            ValidateId(entity, location);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(location, true))){
            writer.append(entity.toString()).append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private <T extends BaseEntity> void ValidateId(T object, String location) throws FileNotFoundException {
        LinkedList<String> contents = GetContents(location);
        for (String line : contents){
            String[] data = line.split(";");
            if (Integer.parseInt(data[0]) == object.id){
                throw new RuntimeException("Element already exists: " + object.toString());
            }
        }
    }

    public LinkedList<Developer> GetAllDevelopers() throws FileNotFoundException {
        LinkedList<Developer> result = new LinkedList<>();
        LinkedList<String> contents = GetContents(DeveloperLocation);
        for (String line : contents) {
            String[] data = line.split(Separator);
            result.add(new Developer(Integer.parseInt(data[0]), data[1], data[2], data[3]));
        }
        return result;
    }

    public Developer GetDeveloper(int id) throws FileNotFoundException {
        LinkedList<String> contents = GetContents(DeveloperLocation);
        for (String line : contents){
            String[] data = line.split(Separator);
            if (data[0].equals(String.valueOf(id))){
                return new Developer(Integer.parseInt(data[0]), data[1], data[2], data[3]);
            }
        }
        throw new RuntimeException("No such Developer id: " + id);
    }

    public LinkedList<Game> GetAllGames() throws FileNotFoundException {
        LinkedList<Game> result = new LinkedList<>();
        LinkedList<String> contents = GetContents(GameLocation);
        for (String line : contents) {
            String[] parts = Arrays.stream(line.split("(?=\\{)|(?<=\\})"))
                    .filter(cell -> !cell.equals(";"))
                    .map(Object::toString)
                    .toArray(String[]::new);

            String[] data = parts[0].split(";");
            Game game = new Game(Integer.parseInt(data[0]), data[1], data[2], Float.valueOf(parts[6].replaceAll(";", "")));

            String[] developerIds = parts[1].replace("{", "")
                            .replace("}", "")
                                    .split(",");
            for (String devId : developerIds){
                try {
                    game.developerList.add(GetDeveloper(Integer.parseInt(devId)));
                } catch (Exception e){
                    System.out.println(e.getMessage());;
                }
            }

            String[] publisherIds = parts[2].replace("{", "")
                            .replace("}", "")
                                    .split(",");
            for (String pubId : publisherIds) {
                try {
                    game.publishers.add(GetPublisher(Integer.parseInt(pubId)));
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            String[] tagIds = parts[3].replace("{", "")
                            .replace("}", "")
                                    .split(",");
            for (String tagId : tagIds) {
                try {
                    game.tags.add(GetTag(Integer.parseInt(tagId)));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            String[] prices = parts[4].replace("{", "")
                    .replace("}", "")
                        .split(",");
            for (String currency : prices){
                String[] set = currency.split("\\|");
                game.prices.put(ECurrency.valueOf(set[0]), Float.valueOf(set[1]));
            }

            String[] platforms = parts[5].replace("{", "")
                    .replace("}", "")
                        .split(",");
            for (String platform : platforms){
                game.platforms.add(EPlatforms.valueOf(platform));
            }

            String[] languages = parts[7].replace("{", "")
                    .replace("}", "")
                        .split(",");
            Collections.addAll(game.languages, languages);

            result.add(game);
        }
        return result;
    }

    public Game GetGame(int id){
        throw new RuntimeException("Not implemented yet!");
    }

    public LinkedList<Publisher> GetAllPublishers() throws FileNotFoundException {
        LinkedList<Publisher> result = new LinkedList<>();
        LinkedList<String> contents = GetContents(DeveloperLocation);
        contents.remove(0);
        for (String line : contents) {
            String[] data = line.split(Separator);
            result.add(new Publisher(Integer.parseInt(data[0]), data[1], data[2], data[3]));
        }
        return result;
    }

    public Publisher GetPublisher(int id) throws FileNotFoundException {
        LinkedList<String> contents = GetContents(PublisherLocation);
        for (String line : contents) {
            String[] data = line.split(Separator);
            if (data[0].equals(String.valueOf(id))) return new Publisher(Integer.parseInt(data[0]), data[1], data[2], data[3]);
        }
        throw new RuntimeException("No such Publisher id: " + id);
    }

    public LinkedList<Tag> GetAllTags() throws FileNotFoundException {
        LinkedList<Tag> result = new LinkedList<>();
        LinkedList<String> contents = GetContents(DeveloperLocation);
        contents.remove(0);
        for (String line : contents) {
            String[] data = line.split(Separator);
            result.add(new Tag(Integer.parseInt(data[0]), data[1], data[2]));
        }
        return result;
    }

    public Tag GetTag(int id) throws FileNotFoundException {
        LinkedList<String> contents = GetContents(TagLocation);
        for (String line : contents) {
            String[] data = line.split(Separator);
            if (data[0].equals(String.valueOf(id))) return new Tag(Integer.parseInt(data[0]), data[1], data[2]);
        }
        throw new RuntimeException("No such Tag id: " + id);
    }


    private LinkedList<String> GetContents(String location) throws FileNotFoundException {
        LinkedList<String> result = new LinkedList<>();
        Scanner scanner = new Scanner(new File(location));
        scanner.useDelimiter("\n");

        while (scanner.hasNext()){
            result.add(scanner.next());
        }

//        System.out.println(result);
        scanner.close();
        return result;
    }
}
