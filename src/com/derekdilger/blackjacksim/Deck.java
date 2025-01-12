package com.derekdilger.blackjacksim;

public class Deck {
    private Card[] theDeck = new Card[52];
    
    public Deck(boolean isShuffled) { //constructor under construction
    }

    public Deck(int deckID) {
        Card[] theDeck = new Card[52];
        
        //I acknowledge the STINKY codesmell here... Don't care, cards are atomic (and this is vim macro practice) (and idk how to iterate over a list of emojis  

        this.theDeck[0] = new Card("A", "♠️");
        this.theDeck[1] = new Card("K", "♠️");
        this.theDeck[2] = new Card("Q", "♠️");
        this.theDeck[3] = new Card("J", "♠️");
        this.theDeck[4] = new Card("10", "♠️");
        this.theDeck[5] = new Card("9", "♠️");
        this.theDeck[6] = new Card("8", "♠️");
        this.theDeck[7] = new Card("7", "♠️");
        this.theDeck[8] = new Card("6", "♠️");
        this.theDeck[9] = new Card("5", "♠️");
        this.theDeck[10] = new Card("4", "♠️");
        this.theDeck[11] = new Card("3", "♠️");
        this.theDeck[12] = new Card("2", "♠️");
        this.theDeck[13] = new Card("A", "♥️");
        this.theDeck[14] = new Card("K", "♥️");
        this.theDeck[15] = new Card("Q", "♥️");
        this.theDeck[16] = new Card("J", "♥️");
        this.theDeck[17] = new Card("10", "♥️");
        this.theDeck[18] = new Card("9", "♥️");
        this.theDeck[19] = new Card("8", "♥️");
        this.theDeck[20] = new Card("7", "♥️");
        this.theDeck[21] = new Card("6", "♥️");
        this.theDeck[22] = new Card("5", "♥️");
        this.theDeck[23] = new Card("4", "♥️");
        this.theDeck[24] = new Card("3", "♥️");
        this.theDeck[25] = new Card("2", "♥️");
        this.theDeck[26] = new Card("A", "♦️");
        this.theDeck[27] = new Card("K", "♦️");
        this.theDeck[28] = new Card("Q", "♦️");
        this.theDeck[29] = new Card("J", "♦️");
        this.theDeck[30] = new Card("10", "♦️");
        this.theDeck[31] = new Card("9", "♦️");
        this.theDeck[32] = new Card("8", "♦️");
        this.theDeck[33] = new Card("7", "♦️");
        this.theDeck[34] = new Card("6", "♦️");
        this.theDeck[35] = new Card("5", "♦️");
        this.theDeck[36] = new Card("4", "♦️");
        this.theDeck[37] = new Card("3", "♦️");
        this.theDeck[38] = new Card("2", "♦️");
        this.theDeck[39] = new Card("A", "♣️");
        this.theDeck[40] = new Card("K", "♣️");
        this.theDeck[41] = new Card("Q", "♣️");
        this.theDeck[42] = new Card("J", "♣️");
        this.theDeck[43] = new Card("10", "♣️");
        this.theDeck[44] = new Card("9", "♣️");
        this.theDeck[45] = new Card("8", "♣️");
        this.theDeck[46] = new Card("7", "♣️");
        this.theDeck[47] = new Card("6", "♣️");
        this.theDeck[48] = new Card("5", "♣️");
        this.theDeck[49] = new Card("4", "♣️");
        this.theDeck[50] = new Card("3", "♣️");
        this.theDeck[51] = new Card("2", "♣️");
    }

    public void printDeck() {
        for (Card cardAtHand : theDeck) {
            System.out.println(cardAtHand.getRank() + cardAtHand.getSuit() );
        }
    }

}
