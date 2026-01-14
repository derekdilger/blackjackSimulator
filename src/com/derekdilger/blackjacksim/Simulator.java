package com.derekdilger.blackjacksim;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ListIterator;
import java.security.SecureRandom;
import java.util.Scanner;
import java.math.BigInteger;

public class Simulator
{

    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        //System.out.println("enter a letter! p to play, s to simulate");
        //String userInput = myScan.nextLine();
        //System.out.println(userInput);

        int numDecksInCapitolBlackjack = 6; 
        long num2cardBB = 0;
        long num3cardBB = 0;
        long num4cardBB = 0;
        long num5cardBB = 0;
        long num6cardBB = 0;
        long num7cardBB = 0;
        long num8cardBB = 0;
        long num9cardBB = 0;
        long num10cardBB = 0;
        long num11cardBB = 0;
        long num12cardBB = 0;
        long num13cardBB = 0;
        long num2cardRF = 0;
        long num3cardRF = 0;
        long num4cardRF = 0;
        long num5cardRF = 0;
        long num6cardRF = 0;
        long num7cardRF = 0;
        long num8cardRF = 0;
        long num9cardRF = 0;
        long num10cardRF = 0;
        long num11cardRF = 0;
        long num12cardRF = 0;
        long num13cardRF = 0;
        long trifecta3 = 0;
        long trifecta4 = 0;
        long trifecta5 = 0;
        long trifecta6 = 0;
        long trifecta7 = 0;
        long trifecta8 = 0;
        long trifecta9 = 0;
        long trifecta10 = 0;
        long trifecta11 = 0;
        long trifecta12 = 0;
        long trifecta13 = 0;
        long balancedTrifecta3 = 0;
        long balancedTrifecta4 = 0;
        long balancedTrifecta5 = 0;
        long balancedTrifecta6 = 0;
        long balancedTrifecta7 = 0;
        long balancedTrifecta8 = 0;
        long balancedTrifecta9 = 0;
        long balancedTrifecta10 = 0;
        long balancedTrifecta11 = 0;
        long balancedTrifecta12 = 0;
        long balancedTrifecta13 = 0;
        long handsDealt = 0;
        long numDealerBusts = 0;
        long numRFwins = 0;
        long numTrifectas = 0;
        long numBalancedTrifectas = 0;
        SecureRandom myRand = new SecureRandom();
        Shoe myShoe = new Shoe(numDecksInCapitolBlackjack);
        myShoe.fillWithCards();
        myShoe.fisherYatesShuffle(myRand);
        Instant start = Instant.now();
        
        Round roundAtHand = new Round(myShoe);
        roundAtHand.init();
        roundAtHand.play();

        long numHandsToSimulate = 0L;
        while (handsDealt < numHandsToSimulate) {
            if ( myShoe.countCardsInShoe() < 78 )  {
                myShoe.fillWithCards();
                myShoe.fisherYatesShuffle(myRand);
            }
            Hand dealerHand = new Hand(myShoe.drawCard(), true); //round logic
            while ( dealerHand.decideIfDealerMustHit() ) { //end-of-round logic 
                dealerHand.addCard(myShoe.drawCard());
            }
            handsDealt++;

            boolean shouldIncr_numDealerBusts = false;  
            boolean shouldIncr_numRF = false;           
            if (dealerHand.computeHandValue() > 21) {  //determining if dealer hand busted in the round, let Round class deal with it 
                //numDealerBusts++;
                shouldIncr_numDealerBusts = true;

                switch ( dealerHand.getNumCardsInHand() ) {
                    case 2: num2cardBB++;
                            break;
                    case 3: num3cardBB++;
                            break;
                    case 4: num4cardBB++;
                            break;
                    case 5: num5cardBB++;
                            break;
                    case 6: num6cardBB++;
                            break;
                    case 7: num7cardBB++;
                            break;
                    case 8: num8cardBB++;
                            break;
                    case 9: num9cardBB++;
                            //System.out.println(" wow a 9card bust: " + dealerHand.getHandAsString() );
                            break;
                    case 10: num10cardBB++;
                             //System.out.println(" WOAH A 10card bust: " + dealerHand.getHandAsString() );
                             break;
                    case 11: num11cardBB++; 
                             //System.out.println(" HE-RO 11card bust: " + dealerHand.getHandAsString() );
                             break;
                    case 12: num12cardBB++;
                             //System.out.println(" HOLY SHIT 12card bust: " + dealerHand.getHandAsString() );
                             break;
                }
            }

            if (dealerHand.computeRFcardNum() >= 2)  {
                shouldIncr_numRF = true;
                switch ( dealerHand.computeRFcardNum() ) {
                    case 2: num2cardRF++;
                            break;
                    case 3: num3cardRF++;
                            break;
                    case 4: num4cardRF++;
                            break;
                    case 5: num5cardRF++;
                            break;
                    case 6: num6cardRF++;
                            //System.out.println("wow a 6card red: " + dealerHand.getHandAsString() );
                            break;
                    case 7: num7cardRF++;
                            break;
                    case 8: num8cardRF++;
                            //System.out.println(" wow an 8card red: " + dealerHand.getHandAsString() );
                            break;
                    case 9: num9cardRF++;
                            //System.out.println(" wow a 9card red: " + dealerHand.getHandAsString() );
                            break;
                    case 10: num10cardRF++;
                             //System.out.println(" WOW A 10card red: " + dealerHand.getHandAsString() );
                             break;
                    case 11: num11cardRF++; 
                             //System.out.println(" HE RO 11card red: " + dealerHand.getHandAsString() );
                             break;
                    case 12: num12cardRF++;
                             //System.out.println(" HOLY SHIT 12card red: " + dealerHand.getHandAsString() );
                             break;
                }
            }

            if (shouldIncr_numDealerBusts)  {
                numDealerBusts++;
            }
            if (shouldIncr_numRF)  {
                //String suspiciousRF = dealerHand.getHandAsString();
                //System.out.println("sus " + suspiciousRF);
                numRFwins++;
            }
            if (shouldIncr_numDealerBusts && shouldIncr_numRF)  {
                numTrifectas++;
                switch ( dealerHand.getNumCardsInHand() )  {
                    case 3: trifecta3++;
                            break;
                    case 4: trifecta4++;
                            break;
                    case 5: trifecta5++;
                            break;
                    case 6: trifecta6++;
                            break;
                    case 7: trifecta7++;
                            break;
                    case 8: trifecta8++;
                            break;
                    case 9: trifecta9++;
                            break;
                    case 10: trifecta10++;
                             break;
                    case 11: trifecta11++;
                             break;
                    case 12: trifecta12++; 
                             break;
                    case 13: trifecta13++;
                             break;
                }

                if ( dealerHand.countNumRedAnyOrder() == dealerHand.getNumCardsInHand() )  {
                    numBalancedTrifectas++;
                    switch ( dealerHand.getNumCardsInHand() )  {
                        case 3: balancedTrifecta3++;
                                break;
                        case 4: balancedTrifecta4++;
                                break;
                        case 5: balancedTrifecta5++;
                                break;
                        case 6: balancedTrifecta6++;
                                break;
                        case 7: balancedTrifecta7++;
                                break;
                        case 8: balancedTrifecta8++;
                                break;
                        case 9: balancedTrifecta9++;
                                break;
                        case 10: balancedTrifecta10++;
                                 System.out.println("BALANCED TRIFECTA " + dealerHand.getHandAsString() );
                                 break;
                        case 11: balancedTrifecta11++;
                                 break;
                        case 12: balancedTrifecta12++; 
                                 break;
                        case 13: balancedTrifecta13++;
                                 break;
                    }
                }
            }
        }
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        System.out.println("this program took " + elapsed.toSeconds() + " seconds");

       // System.out.println();
       // System.out.println("1. total hands dealt: " + handsDealt);
       // System.out.println("2. total dealer busts: " + numDealerBusts + " or " + (100 * (double) numDealerBusts/handsDealt + "%") );
       // System.out.println("3. total redflex wins: " + numRFwins + " or " + (100 * (double) numRFwins/handsDealt) + "%");
       // System.out.println("4. total trifectas (subset of lines 2 and 3) : " + numTrifectas + " or " + (100*(double)numTrifectas/handsDealt) + "%");
       // System.out.println("5. total balanced trifectas (subset of 4) : " + numBalancedTrifectas + " or " + (100*(double)numBalancedTrifectas/handsDealt) + "%");
       // System.out.println();

       // System.out.printf("3cardBB:  %9d %9.6f%%%n", num3cardBB, 100 * (double)num3cardBB / handsDealt);
       // System.out.printf("4cardBB:  %9d %9.6f%%%n", num4cardBB, 100 * (double)num4cardBB / handsDealt);
       // System.out.printf("5cardBB:  %9d %9.6f%%%n", num5cardBB, 100 * (double)num5cardBB / handsDealt);
       // System.out.printf("6cardBB:  %9d %9.6f%%%n", num6cardBB, 100 * (double)num6cardBB / handsDealt);
       // System.out.printf("7cardBB:  %9d %9.6f%%%n", num7cardBB, 100 * (double)num7cardBB / handsDealt);
       // System.out.printf("8cardBB:  %9d %9.6f%%%n", num8cardBB, 100 * (double)num8cardBB / handsDealt);
       // System.out.printf("9cardBB:  %9d %9.6f%%%n", num9cardBB, 100 * (double)num9cardBB / handsDealt);
       // System.out.printf("10cardBB: %9d %9.6f%%%n", num10cardBB, 100 * (double)num10cardBB / handsDealt);
       // System.out.printf("11cardBB: %9d %9.6f%%%n", num11cardBB, 100 * (double)num11cardBB / handsDealt);
       // System.out.printf("12cardBB: %9d %9.6f%%%n", num12cardBB, 100 * (double)num12cardBB / handsDealt);
       // System.out.printf("13cardBB: %9d %9.6f%%%n", num13cardBB, 100 * (double)num13cardBB / handsDealt);
       // System.out.println();
       // System.out.printf("2cardRF:  %9d %9.6f%%%n", num2cardRF, 100 * (double)num2cardRF / handsDealt);
       // System.out.printf("3cardRF:  %9d %9.6f%%%n", num3cardRF, 100 * (double)num3cardRF / handsDealt);
       // System.out.printf("4cardRF:  %9d %9.6f%%%n", num4cardRF, 100 * (double)num4cardRF / handsDealt);
       // System.out.printf("5cardRF:  %9d %9.6f%%%n", num5cardRF, 100 * (double)num5cardRF / handsDealt);
       // System.out.printf("6cardRF:  %9d %9.6f%%%n", num6cardRF, 100 * (double)num6cardRF / handsDealt);
       // System.out.printf("7cardRF:  %9d %9.6f%%%n", num7cardRF, 100 * (double)num7cardRF / handsDealt);
       // System.out.printf("8cardRF:  %9d %9.6f%%%n", num8cardRF, 100 * (double)num8cardRF / handsDealt);
       // System.out.printf("9cardRF:  %9d %9.6f%%%n", num9cardRF, 100 * (double)num9cardRF / handsDealt);
       // System.out.printf("10cardRF: %9d %9.6f%%%n", num10cardRF, 100 * (double)num10cardRF / handsDealt);
       // System.out.printf("11cardRF: %9d %9.6f%%%n", num11cardRF, 100 * (double)num11cardRF / handsDealt);
       // System.out.printf("12cardRF: %9d %9.6f%%%n", num12cardRF, 100 * (double)num12cardRF / handsDealt);
       // System.out.printf("13cardRF: %9d %9.6f%%%n", num13cardRF, 100 * (double)num13cardRF / handsDealt);

       // System.out.println();
       // System.out.printf("balancedTrifecta3:  %9d %9.6f%%%n", balancedTrifecta3, 100 * (double)balancedTrifecta3 / handsDealt);
       // System.out.printf("balancedTrifecta4:  %9d %9.6f%%%n", balancedTrifecta4, 100 * (double)balancedTrifecta4/ handsDealt);
       // System.out.printf("balancedTrifecta5:  %9d %9.6f%%%n", balancedTrifecta5, 100 * (double)balancedTrifecta5/ handsDealt);
       // System.out.printf("balancedTrifecta6:  %9d %9.6f%%%n", balancedTrifecta6, 100 * (double)balancedTrifecta6/ handsDealt);
       // System.out.printf("balancedTrifecta7:  %9d %9.6f%%%n", balancedTrifecta7, 100 * (double)balancedTrifecta7/ handsDealt);
       // System.out.printf("balancedTrifecta8:  %9d %9.6f%%%n", balancedTrifecta8, 100 * (double)balancedTrifecta8/ handsDealt);
       // System.out.printf("balancedTrifecta9:  %9d %9.6f%%%n", balancedTrifecta9, 100 * (double)balancedTrifecta9/ handsDealt);
       // System.out.printf("balancedTrifecta10: %9d %9.6f%%%n", balancedTrifecta10, 100 * (double)balancedTrifecta10/ handsDealt);
       // System.out.printf("balancedTrifecta11: %9d %9.6f%%%n", balancedTrifecta11, 100 * (double)balancedTrifecta11/ handsDealt);
       // System.out.printf("balancedTrifecta12: %9d %9.6f%%%n", balancedTrifecta12, 100 * (double)balancedTrifecta12 / handsDealt);
       // System.out.printf("balancedTrifecta13: %9d %9.6f%%%n", balancedTrifecta13, 100 * (double)balancedTrifecta13/ handsDealt);

    }

    static ArrayList<Card> getDecisionInfoForPlayer(Hand playerHandArg, Hand dealerHandArg)  {
        ArrayList<Card> arrayListToReturn = new ArrayList<Card>(0);
        //designing out loud:
        //ArrayList<Card> will always have the dealer's 'up' card at the first index
        //remaining indices will be the player's cards (like if the player has 4 2 2 that's like an 8)
        
        //algorithm: 
        //create copy of dealer's first card, put it in 0th index
        Card copyOfDealersUpCard = dealerHandArg.getCardAtIndex(0);
        
        //while loop over the unknown length of player's current hand and...
        //create copies of each of the player's cards, add each to the arraylist to be returned
        int indexFromWhichToCopyFrom = 0;
        while (indexFromWhichToCopyFrom < playerHandArg.getNumCardsInHand() )  {
            Card copyOfPlayerCard = playerHandArg.getCardAtIndex(indexFromWhichToCopyFrom);
            arrayListToReturn.add(copyOfPlayerCard);
            System.out.println("inside getDecisionInfoForPlayer, it thinks the player hand is " + playerHandArg.getHandAsString() );
            indexFromWhichToCopyFrom++;
        }
        return arrayListToReturn;
    }
}

