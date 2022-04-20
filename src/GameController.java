import java.awt.event.*;

public class GameController implements ActionListener {

    /**
     * The model game.
     */
    private final Game game;

    /**
     * The State the controller will execute.
     */
    private final State gameState;

    /**
     * Constructor to initialize game object.
     *
     * @param state State object representing the state to process.
     * @param game Game object representing the current game of monopoly.
     */
    public GameController(Game game, State state) {
        this.game = game;
        this.gameState = state;
    }

    /**
     * Overrides all ActionEvents for user controls.
     * @param e ActionEvent object for passed event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (gameState) {
            case state:
                game.printState();
                break;
            case pass:
                game.updatePlayer();
                break;
            case roll:
                game.roll();
                break;
            case yes:
                game.purchaseProperty();
                break;
            case no:
                game.doNotPurchaseProperty();
                break;
            case quit:
                game.endGame();
                break;
            case purchaseHouse:
                game.purchaseHouse();
                break;
            case buyingHouse1:
                game.buyHouse1();
                break;
            case buyingHouse2:
                game.buyHouse2();
                break;
            case buyingHouse3:
                game.buyHouse3();
                break;
            case payFine:
                game.payFine();
                break;
            case saveGame:
                game.saveGameButton();
                break;
        }
    }
}
