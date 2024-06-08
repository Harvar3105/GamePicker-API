package recordstore.model;

import recordstore.model.helpers.Timing;

import java.util.List;

//@Getter
//@Setter
public class MusicRecord extends Record {

  private String artist;
  private String albumName;
  private List<String> songList;

  @Override
  public String play() {
    return null;
  }

  @Override
  public String play(Timing start) {
    return null;
  }

  @Override
  public String play(Timing start, Timing end) {
    return null;
  }
}
