package com.derekdilger.blackjacksim;
import java.util.Scanner;

public class Round {
    //turn-taking states:
    //hasStarted this boolean is false until the 2nd card of the hand is dealt (and maybe reaches some introductory block of code that gives it a chance to play)
    //hasFinished as soon as some final state for the hand has been reached: like after doubling or busting, this becomes true
    //result states:
    //hasDoubled 
    //hasBusted
    //hasStayed
    //hasSurrendered
    //desire states: (the design choice is that the Hand object keeps track of what it wants. this allows hands to determine their desires from different input mechanisms, whether keyboard input, or a digital hit chart.)
    //wantsToHit
    //wantsToStay
    //wantsToDouble
    //wantsToSurrender
    //wantsToSplit

    //VARIABLES USED FOR COMPUTING
    Hand[] arrOfPlayersHands = new Hand[4];
    int indexOfActn;
    Hand playerHand;
    Hand dealerHand;
    Shoe shoeForRound;
    Card dealersFaceUpCard; //redundant object but easier to think about
    final String BOLD = "\033[1m";
    final String RESET = "\033[0m";

    //VARIABLES USED FOR RETURNING RESULTS
    boolean dealerGotBlackjack;
    boolean playerGotBlackjack;
    int numPlayerHands;
    static int numRounds;

    //playHand_manual()
    //playAllHands_manual()
    //playHand_auto()
    //playAllHands_auto()

    public Round(Shoe shoeArg) { //constructor
        this.playerHand = new Hand(false);
        arrOfPlayersHands[indexOfActn] = this.playerHand;
        this.dealerHand = new Hand(true);
        this.shoeForRound = shoeArg;
        if (shoeArg.countCardsInShoe() < 78) {
            System.out.println("you passed in a shoe with too few cards!!!");
        }
    }

    public void init() {
        this.indexOfActn = 0;
        playerHand.addCard( shoeForRound.drawCard() );
        Card temp = shoeForRound.drawCard();
        dealerHand.addCard(temp);
        dealersFaceUpCard = temp;
        playerHand.addCard( shoeForRound.drawCard() );
        dealerHand.addCard( shoeForRound.drawCard() );
    }

    public void play() {
        printBoardState();
        if (checkIfDealerHasBlackjack() && checkIfPlayerHasBlackjack()) {
            System.out.println("both natural blackjack, push!");
            playerHand.hasFinished = true;
            dealerHand.hasFinished = true;
            dealerGotBlackjack = true;
            playerGotBlackjack = true;
        } else {
            while (!playerHand.hasFinished) {
                inquireDesire_manual();
                actuateDesire_manual();
                resetDesires();
                printBoardState();
            }
        }
    }

    public void inquireDesire_manual() {
        Scanner myscanner = new Scanner(System.in);
        System.out.println("h=Hit, s=Stay, r=suRrender, d=Double-down, p=sPlit");
        // no input sanitizing for now. assume cooperative inputter
        String userinput = myscanner.nextLine();
        if (userinput.equals("h")) {
            //System.out.println("hit");
            playerHand.wantsToHit = true;
        } else if (userinput.equals("s")) {
            //System.out.println("stay");
            playerHand.wantsToStay = true;
            playerHand.hasFinished = true;
        } else if (userinput.equals("r")) {
            //System.out.println("surrender");
            
            //playerHand.wantsToSurrender = true;
            //playerHand.hasFinished = true;
            surrender();
        } else if (userinput.equals("d")) {
            //System.out.println("double-down");
            playerHand.wantsToDouble = true;
            playerHand.hasFinished = true;
            doubleDown();
        } else if (userinput.equals("p")) {
            //System.out.println("split");
            playerHand.wantsToSplit = true;
        }
    }

    public void actuateDesire_manual()
    {
        String desireAtHand = playerHand.returnDesire();
        System.out.println("desire is " + desireAtHand);
        switch (desireAtHand) {
            case "h":
                hit();
                playerHand.resetDesires();
            case "s":
                stay();
                //playerHand.resetDesires();
            case "r":
                surrender();
                //playerHand.resetDesires();
            case "d":
                doubleDown();
                System.out.println("not supposed to be here, in actuateDesire");
                //playerHand.resetDesires();
            case "p":
                split();
                playerHand.resetDesires();
        }
    }

    public void resetDesires() {
        playerHand.wantsToHit = false;
        playerHand.wantsToStay = false;
        playerHand.wantsToDouble = false;
        playerHand.wantsToSurrender = false;
        playerHand.wantsToSplit = false;
    }

    public void hit()
    {
        //can't have yet stayed, doubled, busted, surrendered, or otherwise played to completion
        if (!playerHand.hasDoubled && !playerHand.hasFinished && !playerHand.hasSurrendered && !playerHand.hasBusted && !playerHand.hasStayed) 
        {
            playerHand.addCard(shoeForRound.drawCard());
            boolean busted_on_hit = playerHand.determineIfBusted();
            if (busted_on_hit) {
                playerHand.hasFinished = true;
            }
        }
        else 
        {
            System.out.println("you can't hit anymore");
        }
    }

    public void doubleDown()
    {
        if (!playerHand.hasDoubled && !playerHand.hasFinished && !playerHand.hasSurrendered && !playerHand.hasBusted && !playerHand.hasStayed) 
        {
            playerHand.hasDoubled = true;
            playerHand.hasFinished = true;
            playerHand.addCard( shoeForRound.drawCard() );
        } else {
            System.out.println("you can't double down now");
        }
    }

    public void surrender() {
        if (!playerHand.hasDoubled && !playerHand.hasFinished && !playerHand.hasSurrendered && !playerHand.hasBusted && !playerHand.hasStayed) 
        {
            playerHand.hasSurrendered = true;
            playerHand.hasFinished = true;
        } else {
            System.out.println("you can't surrender now");
        }
    }

    public void stay() {
        if (!playerHand.hasDoubled && !playerHand.hasFinished && !playerHand.hasSurrendered && !playerHand.hasBusted && !playerHand.hasStayed) 
        {
            playerHand.hasStayed = true;
            playerHand.hasFinished = true;
        } else {
            System.out.println("it's too late to stay");
        }
    }

    public void split() {
        //algorithm:
        //first determine if splitting is allowed by making sure the hand is a pair and 
        //!hasDoubled, !hasFinished, !hasSurrendered, !hasBusted !hasStayed
        if (!playerHand.hasDoubled && !playerHand.hasFinished && !playerHand.hasSurrendered && !playerHand.hasBusted && !playerHand.hasStayed && playerHand.isAPair()) {
        }
    }
    public void printBoardState() {
        System.out.println("dealer:       " + dealerHand.getCardAtIndex(0).getCardAsString() + "  ??");
        System.out.println();
        System.out.println();
        System.out.println("player:" + BOLD + playerHand.computeHandValue() + RESET + "   " + playerHand.getHandAsString());
    }


    public void determineIfRoundShouldContinue() {
        if (!playerHand.hasFinished) {
        }
    }

    public boolean checkIfPlayerHasBlackjack() {
        if ( playerHand.computeHandValue() == 21 && playerHand.getNumCardsInHand() == 2 && !playerHand.isAPair()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfDealerHasBlackjack() { //for help within Round; ONLY CALL WHEN DEALER HAS
        if ( dealerHand.computeHandValue() == 21 && dealerHand.getNumCardsInHand() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
