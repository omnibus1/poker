import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Card implements Comparable<Card> {
    private final Rank rank;
    private final Suit suit;

    private Card(final Rank rank,final Suit suit){
        this.rank=rank;
        this.suit=suit;
    }
    private final static Map<String,Card> CARD_CACHE=initCache();
    private static Map<String,Card> initCache(){
        final Map<String,Card> cache =new HashMap<>();
        for(final Suit suit:Suit.values()){
            for(final Rank rank:Rank.values()){
                cache.put(cardKey(rank,suit), new Card(rank,suit));
            }
        }
        return Collections.unmodifiableMap(cache);


    }
    private static String cardKey(final Rank rank,final Suit suit){
        return rank+ "of" + suit;
    }
    @Override
    public String toString(){
        return this.rank + "of" + this.suit;
    }
    public static Card getCard(final Rank rank, final Suit suit){
        final Card card=CARD_CACHE.get(cardKey(rank,suit));
        if(card!=null){
            return card;
        }
        throw new RuntimeException("Invalid card!"+rank+" "+suit);


    }

    @Override
    public int compareTo(Card o) {
        final int rankComparisont=Integer.compare(this.rank.getRankValue(),o.rank.getRankValue());
        if(rankComparisont!=0){
            return rankComparisont;
        }
        return Integer.compare(this.suit.getSuitValue(),o.suit.getSuitValue());
    }

    enum Rank{
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14),
        ;
        private final int rankValue;

        Rank(final int rank) {
            this.rankValue=rank;
        }
        public int getRankValue(){
            return this.rankValue;

        }
    }
    enum Suit{
        PIK(1),
        KIER(2),
        TREFL(3),
        KARO(4);
        private final int suitValue;
        Suit (int suitValue){
            this.suitValue=suitValue;
        }
        public int getSuitValue(){
            return this.suitValue;
        }
    }
}
