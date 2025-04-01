// Standard French-style cards
public class Card {

    // Suites
    public enum suites {
        NULL, SPADES, CLUBS, DIAMONDS, HEARTS
    }

    // Ranks
    public enum ranks {
        NULL, two, three, four, five, six, seven, eight, nine, ten, jack, king, queen, ace
    }

    private suites suit;
    private ranks rank;

    Card(){
        suit = suites.NULL;
        rank = ranks.NULL;
    }

    Card(suites s, ranks r){
        suit = s;
        rank = r;
    }

    public void print_card(){
        System.out.print(suit + ": " + rank);
    }

    // Compare the rank of two cards
    public int compareRank(Card other) {
        return this.rank.ordinal() - other.rank.ordinal();
    }

    // Compare the suit of two cards
    public int compareSuit(Card other) {
        return this.suit.ordinal() - other.suit.ordinal();
    }

}
