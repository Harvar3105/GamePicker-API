package recordstore.model;

import recordstore.model.helpers.Timing;

//@Getter
//@Setter
public class MovieRecord extends Record {

  private String filmDirector;
  private String name;

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
