package sources.Models.Interfaces;

import sources.Enums.Place;

public interface CreatureInterface {
    void act(Place place);
    void act(String action);
    void think(String thought);
    void talk(String phrase);
}
