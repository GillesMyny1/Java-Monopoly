import org.junit.*;
import static org.junit.Assert.*;

/**
 * SYSC 3110 Monopoly - Milestone 3 GameTest Test Model Class
 *
 * Test Class for Game Model
 *
 * @author Gilles Myny - 101145477, Frank Dow -101140402, Jeremy Trendoff - 101160306
 * @version 4.0
 * @since Nov 22nd, 2021
 */

public class GameTest {
    /**
     * The game model for tests.
     */
    Game game;

    /**
     * Tests to make sure when roll() is called that the diceroll variable isn't equal to 0.
     */
    @Test
    public void testRoll() {
        game = new Game(2, 0);
        game.roll();
        assertNotEquals(0, game.getDiceRoll());
    }

    /**
     * Tests that the double counter goes up after a double is rolled.
     */
    @Test
    public void testDoubleCounter(){
        game = new Game(2, 0);
        while(!game.getDoublesRolled()){
            game.roll();
        }
        assertEquals(1, game.getDoubleCounter());
    }

    /**
     * Tests that we can get up to 2 doubles, but the next roll resets it.
     */
    @Test
    public void testTripleDoubles(){
        game = new Game(2, 0);
        while(game.getDoubleCounter()!=3){
            game.roll();
            game.updatePlayer();
        }
        assertEquals(3, game.getDoubleCounter());
        game.roll();
        assertEquals(0, game.getDoubleCounter());
    }

    /**
     * Tests to see if the players current position is equal to the dice they rolled.
     */
    @Test
    public void testMovePiece() {
        game = new Game(2, 0);
        game.roll();
        assertEquals(game.getBoard().getTile(game.getDiceRoll()).getName(), game.getPieces(0).getPosition().getName());
    }

    /**
     * Tests if the game moves onto the next player when updatePlayer() is called.
     */
    @Test
    public void testUpdatePlayer() {
        game = new Game(2, 0);
        game.roll();
        game.movePiece(game.getPieces(0));
        game.updatePlayer();
        assertEquals(1, game.getCurrentPlayer());
    }

    @Test
    public void testNextTurn() {
        game = new Game(2, 0);
        game.nextTurn();
    }

    /**
     * Tests when player decides to purchase property, players funds should reflect the purchase, and players owned tiles should be up to date.
     */
    @Test
    public void testPurchaseProperty() {
        game = new Game(2, 0);
        game.roll();
        game.purchaseProperty();
        assertEquals((1500 - game.getPrice()), game.getPieces(0).getFunds());
        assertEquals(game.getCurrentProperty().getName(), game.getPieces(0).getOwnedTile(0).getName());
    }

    /**
     * Tests when player decides not to purchase property, players funds should be equal to the starting amount.
     */
    @Test
    public void testDoNotPurchaseProperty() {
        game = new Game(2, 0);
        game.roll();
        game.movePiece(game.getPieces(0));
        game.doNotPurchaseProperty();
        assertEquals(1500, game.getPieces(0).getFunds());
    }

    /**
     * Tests if funds are properly removed from payee to the recipient.
     */
    @Test
    public void testPay() {
        game = new Game(2, 0);
        int playerOneFundsBefore = game.getPieces(0).getFunds();
        int playerTwoFundsBefore = game.getPieces(1).getFunds();
        int payRate = (int)((game.getPrice())*0.1);
        game.pay(game.getPieces(0), game.getPieces(1), payRate);
        int playerOneFundsAfter = game.getPieces(0).getFunds();
        int playerTwoFundsAfter = game.getPieces(1).getFunds();
        assertEquals(playerOneFundsBefore - payRate, playerOneFundsAfter);
        assertEquals(playerTwoFundsBefore + payRate, playerTwoFundsAfter);
    }

    /**
     * Tests if a player goes bankrupt when their money is negative.
     */
    @Test
    public void testPieceGoingBankrupt(){
        game = new Game(2, 0);
        game.pay(game.getPieces(0), game.getPieces(1), 1600);
        assertTrue(game.getPieces(0).isBankrupt());
    }

    /**
     * Tests if a player is not bankrupt when they have 0$.
     */
    @Test
    public void testPieceNotBankrupt(){
        game = new Game(2, 0);
        game.pay(game.getPieces(0), game.getPieces(1), 1500);
        assertFalse(game.getPieces(0).isBankrupt());
    }

    /**
     * Tests if a player owns a set and enough money that they are able to purchases houses
     */
    @Test
    public void testPurchaseHouse(){
        game = new Game(2,0);
        game.getPieces(0).addTile(game.getBoard().getTile(1));
        game.getPieces(0).addTile(game.getBoard().getTile(2));
        game.purchaseHouse();
        assertEquals(game.getBoard().getTile(1),game.getProp1());
        assertEquals(game.getBoard().getTile(2),game.getProp2());
    }

    /**
     * Tests if a player is successfully sent to Jail.
     */
    @Test
    public void testSentToJail(){
        game = new Game(2,0);
        game.getPieces(0).setInJail(true);
        assertTrue(game.getPieces(0).isInJail());
    }

    /**
     * Tests if a player gets money deducted when paying a fine.
     */
    @Test
    public void testPayFine(){
        game = new Game(2,0);
        int initialFunds = game.getPieces(0).getFunds();
        game.payFine();
        assertEquals(initialFunds-game.getBail(),game.getPieces(0).getFunds());
    }

    /**
     * Tests if the player is released from jail after paying the fine.
     */
    @Test
    public void testReleasedFromJail(){
        game = new Game(2,0);
        game.getPieces(0).setInJail(true);
        game.getPieces(0).setTurnsInJail(0);
        game.payFine();
        assertFalse(game.getPieces(0).isInJail());
    }

    /**
     * Tests if the player gains 200$ for passing GO.
     */
    @Test
    public void testPassingGo(){
        game = new Game(2,0);
        int initialFunds = game.getPieces(0).getFunds();
        game.getPieces(0).updatePosition(game.getBoard().getTile(game.getBoard().numberOfTiles()-1));
        game.roll();
        assertEquals(initialFunds+200, game.getPieces(0).getFunds());
    }

    /**
     * Test the save and load functionality of the program.
     */
    @Test
    public void testSaveAndLoad() {
        game = new Game(2,0);
        game.saveGame("test");

        try {
            Game testGame = Game.loadGame("test");

            for (int i = 0; i < testGame.getBoard().getSet1().size(); i++) {
                assertEquals(testGame.getBoard().getSet1().get(i).getName(), game.getBoard().getSet1().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet2().size(); i++) {
                assertEquals(testGame.getBoard().getSet2().get(i).getName(), game.getBoard().getSet2().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet3().size(); i++) {
                assertEquals(testGame.getBoard().getSet3().get(i).getName(), game.getBoard().getSet3().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet4().size(); i++) {
                assertEquals(testGame.getBoard().getSet4().get(i).getName(), game.getBoard().getSet4().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet5().size(); i++) {
                assertEquals(testGame.getBoard().getSet5().get(i).getName(), game.getBoard().getSet5().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet6().size(); i++) {
                assertEquals(testGame.getBoard().getSet6().get(i).getName(), game.getBoard().getSet6().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet7().size(); i++) {
                assertEquals(testGame.getBoard().getSet7().get(i).getName(), game.getBoard().getSet7().get(i).getName());
            }

            for (int i = 0; i < testGame.getBoard().getSet8().size(); i++) {
                assertEquals(testGame.getBoard().getSet8().get(i).getName(), game.getBoard().getSet8().get(i).getName());
            }

            assertEquals(testGame.getBoard().getJailTile().getName(), game.getBoard().getJailTile().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test the import of the US version. This is also the default for the monopoly game.
     */
    @Test
    public void testImportUSVersion() {
        Board b = new Board();
        try {
            Board test = Game.importFromXmlFile(0);
            assertEquals(test, b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test if the import for the canadian version.
     */
    @Test
    public void testImportCanadianVersion() {
        Board b = new Board();
        b.clearBoard();

        b.addTile(new Go());
        b.addTile(new Property("Banff", false, 60, 50));
        b.addTile(new Property("Beauceville", false, 60, 50));
        b.addTile(new Railroad("St. John's International Airport", 200, false));
        b.addTile(new Property("Vancouver", false, 100, 50));
        b.addTile(new Property("Toronto", false, 100, 50));
        b.addTile(new Property("Ottawa", false, 120, 50));
        b.addTile(new Jail());
        b.addTile(new Property("St. John's", false, 140, 100));
        b.addTile(new Utility("Cell Phone Service", 150, false));
        b.addTile(new Property("North Bay", false, 140, 100));
        b.addTile(new Property("Kelowna", false, 160, 100));
        b.addTile(new Railroad("Vancouver International Airport", 200, false));
        b.addTile(new Property("Montreal", false, 180, 100));
        b.addTile(new Property("Chilliwack", false, 180, 100));
        b.addTile(new Property("Kawartha Lakes", false, 200, 100));
        b.addTile(new Parking());
        b.addTile(new Property("Shawinigan", false, 220, 150));
        b.addTile(new Property("Gatineau", false, 220, 150));
        b.addTile(new Property("Medicine Hat", false, 220, 150));
        b.addTile(new Railroad("Montreal International Airport", 200, false));
        b.addTile(new Property("Trois-Rivieres", false, 260, 150));
        b.addTile(new Property("Quebec City", false, 260, 150));
        b.addTile(new Utility("Internet Service", 150, false));
        b.addTile(new Property("Windsor", false, 280, 150));
        b.addTile(new SendJail());
        b.addTile(new Property("Edmonton", false, 300, 200));
        b.addTile(new Property("Sarnia", false, 300, 200));
        b.addTile(new Property("Calgary", false, 320, 200));
        b.addTile(new Railroad("Toronto International Airport", 200, false));
        b.addTile(new Property("Saint-Jean-Sur-Richelieu", false, 350, 200));
        b.addTile(new Property("Chatham-kent", false, 400, 200));

        b.createSets();

        try {
            Board test = Game.importFromXmlFile(1);

            for (int i = 0; i < test.getSet1().size(); i++) {
                assertEquals(test.getSet1().get(i).getName(), b.getSet1().get(i).getName());
            }

            for (int i = 0; i < test.getSet2().size(); i++) {
                assertEquals(test.getSet2().get(i).getName(), b.getSet2().get(i).getName());
            }

            for (int i = 0; i < test.getSet3().size(); i++) {
                assertEquals(test.getSet3().get(i).getName(), b.getSet3().get(i).getName());
            }

            for (int i = 0; i < test.getSet4().size(); i++) {
                assertEquals(test.getSet4().get(i).getName(), b.getSet4().get(i).getName());
            }

            for (int i = 0; i < test.getSet5().size(); i++) {
                assertEquals(test.getSet5().get(i).getName(), b.getSet5().get(i).getName());
            }

            for (int i = 0; i < test.getSet6().size(); i++) {
                assertEquals(test.getSet6().get(i).getName(), b.getSet6().get(i).getName());
            }

            for (int i = 0; i < test.getSet7().size(); i++) {
                assertEquals(test.getSet7().get(i).getName(), b.getSet7().get(i).getName());
            }

            for (int i = 0; i < test.getSet8().size(); i++) {
                assertEquals(test.getSet8().get(i).getName(), b.getSet8().get(i).getName());
            }

            assertEquals(test.getJailTile().getName(), b.getJailTile().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Test if the import for the UK version.
     */
    @Test
    public void testImportUKVersion() {
        Board b = new Board();
        b.clearBoard();

        b.addTile(new Go());
        b.addTile(new Property("Old Kent Road", false, 60, 50));
        b.addTile(new Property("Whitechapel Road", false, 60, 50));
        b.addTile(new Railroad("King's Cross station", 200, false));
        b.addTile(new Property("The Angel, Islington", false, 100, 50));
        b.addTile(new Property("Euston Road", false, 100, 50));
        b.addTile(new Property("Pentonville Road", false, 120, 50));
        b.addTile(new Jail());
        b.addTile(new Property("Pall Mall", false, 140, 100));
        b.addTile(new Utility("Electric Company", 150, false));
        b.addTile(new Property("Whitehall", false, 140, 100));
        b.addTile(new Property("Northumberland Avenue", false, 160, 100));
        b.addTile(new Railroad("Marylebone station", 200, false));
        b.addTile(new Property("Bow Street", false, 180, 100));
        b.addTile(new Property("Great Marlborough Street", false, 180, 100));
        b.addTile(new Property("Vine Street", false, 200, 100));
        b.addTile(new Parking());
        b.addTile(new Property("Strand", false, 220, 150));
        b.addTile(new Property("Fleet Street", false, 220, 150));
        b.addTile(new Property("Trafalgar Square", false, 220, 150));
        b.addTile(new Railroad("Fenchurch Street Station", 200, false));
        b.addTile(new Property("Leicester Square", false, 260, 150));
        b.addTile(new Property("Coventry Street", false, 260, 150));
        b.addTile(new Utility("Water Works", 150, false));
        b.addTile(new Property("Piccadilly", false, 280, 150));
        b.addTile(new SendJail());
        b.addTile(new Property("Regent Street", false, 300, 200));
        b.addTile(new Property("Oxford Street", false, 300, 200));
        b.addTile(new Property("Bond Street", false, 320, 200));
        b.addTile(new Railroad("Liverpool Street station", 200, false));
        b.addTile(new Property("Park Lane", false, 350, 200));
        b.addTile(new Property("Mayfair", false, 400, 200));

        b.createSets();

        try {
            Board test = Game.importFromXmlFile(2);

            for (int i = 0; i < test.getSet1().size(); i++) {
                assertEquals(test.getSet1().get(i).getName(), b.getSet1().get(i).getName());
            }

            for (int i = 0; i < test.getSet2().size(); i++) {
                assertEquals(test.getSet2().get(i).getName(), b.getSet2().get(i).getName());
            }

            for (int i = 0; i < test.getSet3().size(); i++) {
                assertEquals(test.getSet3().get(i).getName(), b.getSet3().get(i).getName());
            }

            for (int i = 0; i < test.getSet4().size(); i++) {
                assertEquals(test.getSet4().get(i).getName(), b.getSet4().get(i).getName());
            }

            for (int i = 0; i < test.getSet5().size(); i++) {
                assertEquals(test.getSet5().get(i).getName(), b.getSet5().get(i).getName());
            }

            for (int i = 0; i < test.getSet6().size(); i++) {
                assertEquals(test.getSet6().get(i).getName(), b.getSet6().get(i).getName());
            }

            for (int i = 0; i < test.getSet7().size(); i++) {
                assertEquals(test.getSet7().get(i).getName(), b.getSet7().get(i).getName());
            }

            for (int i = 0; i < test.getSet8().size(); i++) {
                assertEquals(test.getSet8().get(i).getName(), b.getSet8().get(i).getName());
            }

            assertEquals(test.getJailTile().getName(), b.getJailTile().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}