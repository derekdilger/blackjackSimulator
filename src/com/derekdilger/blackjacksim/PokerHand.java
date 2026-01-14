package com.derekdilger.blackjacksim;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.HashMap;
public class PokerHand
{
    private ArrayList<Card> theHand = new ArrayList<Card>(0); 
    private static HashMap<String, Integer> rankMap = new HashMap<String, Integer>();
    static {
        rankMap.put("A", 1);
        rankMap.put("K", 10);
        rankMap.put("Q", 10);
        rankMap.put("J", 10);
        rankMap.put("10", 10);
        rankMap.put("9", 9);
        rankMap.put("8", 8);
        rankMap.put("7", 7);
        rankMap.put("6", 6);
        rankMap.put("5", 5);
        rankMap.put("4", 4);
        rankMap.put("3", 3);
        rankMap.put("2", 2);
    }

    public BlackJackHand(Card cardArg, boolean isDealersHand) {
        if (isDealersHand) {
            this.isDealerHand = true;
        }
        theHand.add(cardArg);
    }

    public BlackJackHand(boolean isDealersHand) {
        if (isDealersHand) {
            this.isDealerHand = true;
        }
    }

    public boolean isDealerHand() {
        if (isDealerHand) {
            return true;
        } else {
            return false;
        }
    }

    public void resetDesires() {
        this.wantsToHit = false;
        this.wantsToStay = false;
        this.wantsToDouble = false;
        this.wantsToSurrender = false;
        this.wantsToSplit = false;
    }

    public boolean isHard() {
        if ( !(this.containsAnAce()) ) {
            return true; // 4 6 and the like
        } else if ( this.computeMinHandValue() >= 12) {
            return true; // A A 10 and the like
        } else {
            return false; //A 4 and the like
        }
    }

    boolean decideIfDealerMustHit() {
        if (isDealerHand == true) 
        {
        boolean isHard = this.isHard();
        int handValue = this.computeHandValue();
        //if isHard & < 17: true
        if ( isHard && (handValue < 17) ) 
        {
            return true;
        }
        //if isHard & >= 17: false
        if ( isHard && (handValue >= 17) ) 
        {
            return false;
        }
        //if !isHard & > 17: false
        if ( !isHard && (handValue > 17) ) 
        {
            return false;
        }
        //if !isHard & <= 17: true
        if ( !isHard && (handValue <= 17) ) 
        {
            return true;
        }
        return false;
        } else {
            System.out.println("a player hand shouldn't strategize like a dealer");
        }
        return false;
    }

    public void addCard(Card cardToAdd) {
        theHand.add(cardToAdd);
    }

    public int getNumCardsInHand() {
        return theHand.size();
    }

    public Card getCardAtIndex(int indexToGet) {
        Card cardToReturn = this.theHand.get(indexToGet);
        return cardToReturn;
    }

    public String getHandAsString() {
        String handSoFar = "";
        ListIterator<Card> cardIteratorForGettingAsString = theHand.listIterator();
        while (cardIteratorForGettingAsString.hasNext()) {
            handSoFar += "  " + cardIteratorForGettingAsString.next().getCardAsString() ;
        }
        return handSoFar;
    }

    public void printHand() { 
        ListIterator<Card> cardIteratorForPrinting = theHand.listIterator();
        while (cardIteratorForPrinting.hasNext()) {
            System.out.print(" " + cardIteratorForPrinting.next().getCardAsString() );
        }
    }

    public int countNumRedAnyOrder() {
        int numRedsSoFar = 0;
        ListIterator<Card> cardIteratorForCountNumRedAnyOrder = theHand.listIterator();
        while ( cardIteratorForCountNumRedAnyOrder.hasNext() ) {
            Card cardAtHand = cardIteratorForCountNumRedAnyOrder.next();
            if ( cardAtHand.getSuit().equals("♦️") || cardAtHand.getSuit().equals("♥️") )
            {
                numRedsSoFar++;
            } else 
            {
                break;
            }
        }
        return numRedsSoFar;
    }

    public boolean containsAnAce() {
        ListIterator<Card> cardIteratorForAceSearch = theHand.listIterator();
        while(cardIteratorForAceSearch.hasNext()) {
            if ( cardIteratorForAceSearch.next().getRank().equals("A") ) {
                //System.out.println("contains an ace");
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    public boolean isAPair() {
        String firstCardRank = theHand.get(0).getRank();
        String secondCardRank = theHand.get(1).getRank();
        if (firstCardRank.equals(secondCardRank)) {
            return true;
        } else {
            return false;
        }
    }

    public int computeMinHandValue() {//this fctn treats all aces as 1, always! 
        //this fctn exists solely for its use in the isHard() function
        int handValue = 0;
        ListIterator<Card> cardIteratorForHandValue = theHand.listIterator();
        while( cardIteratorForHandValue.hasNext() ) {
            Card cardAtHand = cardIteratorForHandValue.next();
            String rankAtHand = cardAtHand.getRank();
            int valueAtHand = rankMap.get(rankAtHand);
            handValue += valueAtHand;
        }
        return handValue;
    }

    public boolean determineIfBusted() {
        int handValue = 0;
        boolean hasAnAce = false;
        ListIterator<Card> cardIteratorForHandValue = theHand.listIterator();
        if ( this.containsAnAce() ) {
            hasAnAce = true;
        }
        while( cardIteratorForHandValue.hasNext() ) {
            Card cardAtHand = cardIteratorForHandValue.next();
            String rankAtHand = cardAtHand.getRank();
            int valueAtHand = rankMap.get(rankAtHand);
            handValue += valueAtHand;
        }
        if (handValue < 12 && hasAnAce) {
            handValue += 10;
        }
        if (handValue > 21) {
            return true;
        } else {
            return false;
        }
    }

    public int computeHandValue() { 
        int handValue = 0;
        boolean hasAnAce = false;
        ListIterator<Card> cardIteratorForHandValue = theHand.listIterator();
        if ( this.containsAnAce() ) {
            hasAnAce = true;
        }
        while( cardIteratorForHandValue.hasNext() ) {
            Card cardAtHand = cardIteratorForHandValue.next();
            String rankAtHand = cardAtHand.getRank();
            int valueAtHand = rankMap.get(rankAtHand);
            handValue += valueAtHand;
        }
        if (handValue < 12 && hasAnAce) {
            handValue += 10;
        }
        return handValue;
    }

    public int computeRFcardNum() {
        int numRF = 0;
        ListIterator<Card> cardIteratorForRF = theHand.listIterator();

        while (cardIteratorForRF.hasNext()) {
            boolean cardAtHandIsRed = false;
            boolean onlySeenRedSoFar = true;
            Card cardAtHand = cardIteratorForRF.next();
            String suitAtHand = cardAtHand.getSuit();
            if ( (suitAtHand.equals("♥️") || suitAtHand.equals("♦️")) && onlySeenRedSoFar) {
                cardAtHandIsRed = true;
            } else {
                onlySeenRedSoFar = false;
                cardAtHandIsRed = false;
                return numRF;
            }

            if (cardAtHandIsRed && onlySeenRedSoFar) {
                numRF++;
                continue;
            }
        }
        return numRF;
    }

    public String returnDesire() {
        String desire;
        if (this.wantsToHit = true) {
            desire = "h";
            return desire;
        }
        else if (this.wantsToStay = true) {
            desire = "s";
            return desire;
        }
        else if (this.wantsToDouble = true) {
            desire = "d";
            return desire;
        }
        else if (this.wantsToSurrender = true) {
            desire = "r";
            return desire;
        }
        else if (this.wantsToSplit = true) {
            desire = "p";
            return desire;
        }
            return desire="you reached the bottom of BlackJackHand's returnDesire()";
    }
}
