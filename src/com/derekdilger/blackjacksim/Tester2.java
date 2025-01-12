package com.derekdilger.blackjacksim;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Random;
import java.util.Arrays;
public class Tester2 {
    public static void main(String[] args) {

        System.out.println("in Tester2");
        int numDecksInCapitolBlackjack = 6;

        Shoe myShoe = new Shoe(numDecksInCapitolBlackjack);
        myShoe.fillWithCards();
        myShoe.shuffle();

        System.out.println("num card in shoe: " + myShoe.countCardsInShoe() );

        for (int i = 0; i <312 ; i++) 
        {

            System.out.println("index " + i + " has " + myShoe.getCardAt(i).getCardAsString() );
        }

        int handsDealt = 0;
        while (handsDealt < 1) {
            if ( myShoe.countCardsInShoe() < 78 ) 
            {
                myShoe.fillWithCards();
                myShoe.shuffle();
            }

            Hand dealerHand = new Hand(myShoe.drawCard(), true);
            while ( dealerHand.decideIfDealerMustHit() )
            {
                dealerHand.addCard(myShoe.drawCard());
            }
            int lengthOfDealerHandAsInt = dealerHand.getNumCardsInHand();
            String lengthOfDealerHandAsStr = Integer.toString(lengthOfDealerHandAsInt);
            System.out.println("dealer hand has " + lengthOfDealerHandAsStr + " cards");
            System.out.println("and the hand is " + dealerHand.getHandAsString());
            System.out.println();
            handsDealt++;
        }

        for (int i = 0; i < 312; i++) 
        {

            if (myShoe.getCardAt(i) == null) 
            {
                System.out.println("index " + i + " has no card!" );
            } 
            else 
            {
                System.out.println("index " + i + " has " + myShoe.getCardAt(i).getCardAsString() );
            }

        }


        System.out.println("trying to view random bytes now: ");
        Random myRand = new Random();
        byte[] myBytes = new byte[2];
        myRand.nextBytes(myBytes);
        System.out.println("heres the memory address of the array " + myBytes);

        String arrayAsString = Arrays.toString(myBytes);
        System.out.println("here's the array as a string " + arrayAsString);

        try (FileInputStream urandom = new FileInputStream("/dev/urandom")) 
        {
            int numIter = 0;
            while (numIter < 4) 
            {
                // Read 2 bytes from /dev/urandom
                byte[] buffer = new byte[2];
                urandom.read(buffer);

                // Combine the bytes into a single integer
                int combined = ((buffer[0] & 0xFF) << 8) | (buffer[1] & 0xFF);

                // Mask to extract 9 bits (values between 0 and 511)
                int result = combined & 0x1FF; // 0x1FF = 511 in binary: 00000001 11111111

                System.out.println("random 9 bit integer from urandom: " + result);
                numIter++;
            }
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        int prngcount = 0;
        while (prngcount < 40000) 
        {
            byte[] bytesFromPRNG = new byte[2];
            myRand.nextBytes(bytesFromPRNG);
            // Combine the bytes into a single integer
            int combinedFromPRNG = ((bytesFromPRNG[0] & 0xFF) << 8) | (bytesFromPRNG[1] & 0xFF);

            // Mask to extract 9 bits (values between 0 and 511)
            int resultFromPRNG = combinedFromPRNG & 0x1FF; // 0x1FF = 511 in binary: 00000001 11111111

            System.out.println("random 9 bit integer from PRNG: " + resultFromPRNG);
            prngcount++;
        }
        int i = 0;
        while (i < 10000)
        {
            boolean gotGoodValue = false;
            while (!gotGoodValue)
            {
//                Random myRand = new Random();
//                byte[] myBytes = new byte[2];
                myRand.nextBytes(myBytes);
                // Combine the bytes into a single integer
                int combinedFromPRNG = ((myBytes[0] & 0xFF) << 8) | (myBytes[1] & 0xFF);

                // Mask to extract 9 bits (values between 0 and 511)
                int resultFromPRNG = combinedFromPRNG & 0x1FF; // 0x1FF = 511 in binary: 00000001 11111111
                if (resultFromPRNG < 312)
                {
                    gotGoodValue = true;
                    System.out.println("result is " + resultFromPRNG);
                } else {
                    continue;
                }
            }
            i++;
        }
        //return -1;
    }
}
