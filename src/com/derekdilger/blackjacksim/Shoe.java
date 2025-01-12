package com.derekdilger.blackjacksim;
import java.security.SecureRandom;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class Shoe 
{
    private int THE_NUM_DECKS = 6;
    public static int THE_NUM_CARDS_IN_A_DECK = 52; 
    private Card[] theShoe = new Card[THE_NUM_DECKS * THE_NUM_CARDS_IN_A_DECK];
    private int lowestEmptyIndex; //useful when filling the deck
    private int lowestFilledIndex; //useful when we need to draw a card
    private static String[] suitsArr = {"♣️", "♠️", "♦️", "♥️"};
    private static String[] ranksArr = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

    public Shoe(int numDecksInShoe) 
    { //constructor
        this.THE_NUM_DECKS = numDecksInShoe;
        this.theShoe = new Card[THE_NUM_DECKS * THE_NUM_CARDS_IN_A_DECK];
    }

    public int countCardsInShoe() {
        int counter = 0;
        for (int i = 0; i < THE_NUM_CARDS_IN_A_DECK * THE_NUM_DECKS; i++) { 
            if (this.theShoe[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public Card getCardAt(int indexOfCard) 
    {
        return theShoe[indexOfCard];
    }

    public void setCardAt(int indexOfCard, String rankAtHand, String suitAtHand) 
    {
        this.theShoe[indexOfCard] = new Card(rankAtHand, suitAtHand);
    }

    public void setCardAt(int indexOfCard, Card cardToInsert) 
    {
        this.theShoe[indexOfCard] = cardToInsert;
    }

    public void swapCardsAtIndices(int indexOfCard1, int indexOfCard2) 
    {
        Card tempCard1 = getCardAt(indexOfCard1);
        Card tempCard2 = getCardAt(indexOfCard2);
        setCardAt(indexOfCard1, tempCard2);
        setCardAt(indexOfCard2, tempCard1);
    }

    public void fillWithCards() 
    {
        int decksInsertedSoFar = 0;
        while (decksInsertedSoFar < THE_NUM_DECKS)
        {
            for(String suitAtHand : suitsArr)
            {
                for(String rankAtHand : ranksArr)
                {
                    this.theShoe[ lowestEmptyIndex ] = new Card(rankAtHand, suitAtHand);
                    lowestEmptyIndex++;
                }
            }
            decksInsertedSoFar++;
        }
        lowestFilledIndex = 0;
        lowestEmptyIndex = 0; 
    }

    public int getRandomIntBetweenZeroAndHighExclusiveLINUXrandom(BigInteger high) 
    {
        try (FileInputStream urandomStream = new FileInputStream("/dev/urandom"))
        {
            byte[] randomBytesFromUrandom = new byte[3];
            urandomStream.read(randomBytesFromUrandom);

            BigInteger randomBigInt = new BigInteger(1, randomBytesFromUrandom);
            BigInteger intermediaryBigInt = randomBigInt.mod(high);
            int toReturn = intermediaryBigInt.intValue();
            return toReturn;
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return -1; //if you reached this line, UH OH
    }

    public int getRandomIntBetweenZeroAnd312ExclusivePRNG()
    {
        boolean gotGoodValue = false;
        while (!gotGoodValue)
        {
            Random myRand = new Random();
            byte[] myBytes = new byte[2];
            myRand.nextBytes(myBytes);
            // Combine the bytes into a single integer
            int combinedFromPRNG = ((myBytes[0] & 0xFF) << 8) | (myBytes[1] & 0xFF);

            // Mask to extract 9 bits (values between 0 and 511)
            int resultFromPRNG = combinedFromPRNG & 0x1FF; // 0x1FF = 511 in binary: 00000001 11111111
            if (resultFromPRNG < 312)
            {
                gotGoodValue = true;
                return resultFromPRNG;
            } else {
                continue;
            }
        }
        return -1;
    }

    public int getRandomIntBetweenZeroAnd312ExclusivePRNG(SecureRandom randArg)
    {
        boolean gotGoodValue = false;
        while (!gotGoodValue)
        {
            byte[] myBytes = new byte[2];
            randArg.nextBytes(myBytes);
            // Combine the bytes into a single integer
            int combinedFromPRNG = ((myBytes[0] & 0xFF) << 8) | (myBytes[1] & 0xFF);

            // Mask to extract 9 bits (values between 0 and 511)
            int resultFromPRNG = combinedFromPRNG & 0x1FF; // 0x1FF = 511 in binary: 00000001 11111111
            if (resultFromPRNG < 312)
            {
                gotGoodValue = true;
                return resultFromPRNG;
            } else {
                continue;
            }
        }
        return -1;
    }

    public void shuffle() 
    {
        for (int i = 0; i < THE_NUM_DECKS*THE_NUM_CARDS_IN_A_DECK; i++)
        {
            int randomIntAtHand = getRandomIntBetweenZeroAnd312ExclusivePRNG();
            //int randomIntAtHand = myRand.nextInt(this.THE_NUM_DECKS * THE_NUM_CARDS_IN_A_DECK);
            swapCardsAtIndices(i, randomIntAtHand);
        }
    }

    //THE CURSED SHUFFLING METHOD; retained for gawking
//    public void shuffle(SecureRandom randArg) 
//    {
//        for (int i = 0; i < THE_NUM_DECKS*THE_NUM_CARDS_IN_A_DECK; i++)
//        {
//            int randomIntAtHand = getRandomIntBetweenZeroAnd312ExclusivePRNG(randArg);
//            //int randomIntAtHand = myRand.nextInt(this.THE_NUM_DECKS * THE_NUM_CARDS_IN_A_DECK);
//            swapCardsAtIndices(i, randomIntAtHand);
//        }
//    }

    public void fisherYatesShuffle(SecureRandom randArg) 
    {
        for (int i = THE_NUM_DECKS * THE_NUM_CARDS_IN_A_DECK - 1; i > 0; i--)
        {
            int randomIndex = randArg.nextInt(i + 1);
            swapCardsAtIndices(i, randomIndex);
        }
    }

    public int getLowestEmptyIndex() 
    {
        return this.lowestEmptyIndex;
    }

    public int getLowestFilledIndex() 
    {
        return this.lowestFilledIndex;
    }

    public Card drawCard() //draws & destroys card at top of shoe, as in a real shoe
    { 
        Card cardToReturn = this.theShoe[lowestFilledIndex];
        this.theShoe[lowestFilledIndex] = null;
        lowestFilledIndex++;
        return cardToReturn;
    }
}
