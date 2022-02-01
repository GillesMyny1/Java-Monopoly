/**
 * The States enum responsible for holding the values of States of the game.
 *
 * @author Jeremy Trendoff - 101160306
 * @version 1.0
 * @since Dec 4th, 2021
 */

public enum State {
    /**
     * State to represent printing the users game state.
     */
    state,

    /**
     * State to represent passing a turn.
     */
    pass,

    /**
     * State to represent rolling.
     */
    roll,

    /**
     * State to represent buying a property.
     */
    yes,

    /**
     * State to represent not buying a property.
     */
    no,

    /**
     * State to represent quitting the game.
     */
    quit,

    /**
     * State to represent buying a house.
     */
    purchaseHouse,

    /**
     * State to represent buying house 1 of 3.
     */
    buyingHouse1,

    /**
     * State to represent buying house 2 of 3.
     */
    buyingHouse2,

    /**
     * State to represent buying house 3 of 3.
     */
    buyingHouse3,

    /**
     * State to represent paying a fine.
     */
    payFine,

    /**
     * State to represent saving the game.
     */
    saveGame;
}
