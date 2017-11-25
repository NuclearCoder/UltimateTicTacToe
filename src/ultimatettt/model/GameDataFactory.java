package ultimatettt.model;

/**
 * Factory class for game data model
 */
public final class GameDataFactory {

    private GameDataFactory() {}

    public static GameData createGameData() {
        return new GameDataImpl();
    }

}
