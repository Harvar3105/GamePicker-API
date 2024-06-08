package recordstore.model;

import recordstore.model.helpers.Timing;

public interface RecordInterface {

  String play();
  String play(Timing start);
  String play(Timing start, Timing end);

}
