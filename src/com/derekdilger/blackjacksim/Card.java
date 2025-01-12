package com.derekdilger.blackjacksim;

public class Card {
    private String rank; //ranks are A K Q J T 9 8 7 6 5 4 3 2 
    private String suit; //suits are S H D C or  ♠️  ♥️  ♦️  ♣️  NO INPUT SANITIZING (yet)
    private int UID;
    private int deckID;

    public Card(String instanceRank, String instanceSuit) {
        this.rank = instanceRank;
        this.suit = instanceSuit;
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getCardAsString() {
        String toReturn = "";
        toReturn += this.rank;
        toReturn += this.suit;
        return toReturn;
    }

    public void printCard() {

        System.out.println();
    }

}
