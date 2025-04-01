import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck,int winCount, int lossCount) {
        Scanner console = new Scanner(System.in);
        System.out.println("\nStarting Blind mans Bluff \n");
        // play the game FIXME

        int rageCount = 0;
        String cont = "y";
        int timesplayed = 0;
        while (rageCount < 3 && cont.equalsIgnoreCase("y")){
            if (timesplayed > 0) {
                int num_cards_dealt = 5;
                for (int i = 0; i < num_cards_dealt; i++) {
                    // player removes a card from the deck and adds to their hand
                    player1.add_at_tail(deck.remove_from_head());
                    computer.add_at_tail(deck.remove_from_head());
                }
            }
            for (int i = 0; i < 5; i++) {

                Card playerOneCard = player1.remove_from_head();
                Card computerCard = computer.remove_from_head();

                System.out.println("\nComputer card #" + (i+1) + ": " );
                computerCard.print_card();
                System.out.println("\nIs your card higher or lower..." );
                String answer = console.next();


                int numComparison = playerOneCard.compareRank(computerCard);

                if (answer.equalsIgnoreCase("higher")){
                    if (numComparison > 0) {
                        //player 1 wins
                        System.out.println("Win!");
                        winCount++;

                    }
                    else if (numComparison < 0) {
                        System.out.println("Loss!");
                        lossCount++;
                        rageCount++;

                    }
                    if (numComparison == 0) {
                        int comparison = playerOneCard.compareSuit(computerCard);
                         if (comparison == 1) {
                            // Player 1 win on suite
                             System.out.println("Win!");
                            winCount++;


                        } else {
                            //computer win on suite
                             System.out.println("Loss!");
                            lossCount++;
                            rageCount++;


                        }
                    }
                }

                if (answer.equalsIgnoreCase("lower")){
                    if (numComparison  > 0) {
                        //player 1 higher card
                        System.out.println("Loss!");
                        lossCount++;
                        rageCount++;
                    }
                    else if (numComparison < 0) {
                        //player one lower card
                        System.out.println("Win!");
                        winCount++;

                    }
                    //In the case it comes down to suits
                    if (numComparison == 0) {
                        int comparison = playerOneCard.compareSuit(computerCard);
                         if (comparison == 1) {
                            // Player 1 lower on suite
                             System.out.println("Loss!");
                            lossCount++;
                            rageCount++;


                        } else {
                            //computer higher on suite
                             System.out.println("Win!");
                            winCount++;


                        }
                    }
                }
                System.out.println("Your card was: ");
                playerOneCard.print_card();
                System.out.println("\n");

                deck.add_at_tail(playerOneCard);
                deck.add_at_tail(computerCard);
                if (rageCount == 3){
                    rage_Quit( player1, computer, deck, winCount, lossCount);
                }

            }
            System.out.println("Would you like to play again? (y/n)");
            cont = console.next();
            timesplayed ++;
            rageCount = 0;
        }

        System.out.print("Wins:         " + winCount + "\nLosses:           " + lossCount + "\n");
    }
    public static void rage_Quit (LinkedList player1, LinkedList computer, LinkedList deck, int wins, int losses){
        deck.shuffle(512);

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }
        System.out.println("RAGE QUIT: Maybe this game isn't for you...");
        play_blind_mans_bluff(player1, computer, deck, wins, losses);
    }


    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)

        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)

        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        int wins = 0;
        int losses = 0;
        play_blind_mans_bluff(player1, computer, deck, wins, losses);

    }
}
