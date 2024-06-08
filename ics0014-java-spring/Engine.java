import Entities.Developer;
import Entities.Game;
import Entities.Publisher;
import Entities.Tag;
import Enums.ECurrency;
import Enums.EPlatforms;

import java.io.IOException;

public class Engine {
    private static final DAO Dao = new DAO();

    public static void main(String[] args) throws IOException {
//        Dto.SaveDeveloper(new Developer(0, "test", "test", "test"));
//        Dto.SaveDeveloper(new Developer(1, "test2", "test2", "test2"));
//        System.out.println(Dto.GetAllDevelopers());

//        Dao.Save(new Developer(4, "test4", "test4", "test4"));
//        System.out.println(Dao.GetAllDevelopers());
//        Developer developer = Dao.GetDeveloper(4);
//        Dao.Save(new Tag(0, "Shooter", "Shooter"));
//        Tag tag1 = Dao.GetTag(0);
//        Dao.Save(new Tag(1, "Grind", "Grind or donate"));
//        Tag tag2 = Dao.GetTag(1);
//        Dao.Save(new Publisher(0, "EA", "Me want money", "Somewhere"));
//        Publisher publisher = Dao.GetPublisher(0);
//        Game game = new Game(0, "BoberRunner", "Some runner", 15.0f);
//        game.tags.add(tag1);
//        game.tags.add(tag2);
//        game.developerList.add(developer);
//        game.publishers.add(publisher);
//        game.prices.put(ECurrency.EUR, 17.99f);
//        game.languages.add("English");
//        game.languages.add("Russian");
//        game.languages.add("German");
//        game.languages.add("Estonian");
//        game.platforms.add(EPlatforms.Mac);
//        game.platforms.add(EPlatforms.PS5);
//        Dao.Save(game);

        System.out.println(Dao.GetAllGames());
    }
}
