package com.derekdilger.blackjacksim;

public class Round {
    //the ultimate justification for creating a 'round' object is to have a 
    //level of control, or object, to contain multiple hands when they split.
    //for statistics also, how often splitting happens and whatnot
    //and for nomenclature: a 'hand' in blackjack is both a round of play and also a set of cards.
    // it feels like Hands should not be in control of 'hitting' themselves- that's another justification

    //every blackjack round must have these items: they will be passed as arguments
    //a dealer hand
    //1-4 player hands (always just 1 to start)
    // a shoe (note that we will never check the remaining amount of cards DURING a ROUND of blackjack, just make sure it's passed in with 78 or more cards)
    
    //
    Hand[] arrayOfPlayersHands = new Hand[4];
    Hand playerHand;
    Hand dealerHand;
    
    public Round(Hand playerHandArg, Shoe shoeArg, Hand dealerHandArg) {
    }

}
