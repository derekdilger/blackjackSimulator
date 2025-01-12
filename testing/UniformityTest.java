import java.util.random;

public class UniformityTest {

    public static int getRandomIntBetweenZeroAnd312ExclusivePRNG()
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

    public static void main(String[] args) {

        double[] zeroTo311ActualCumulativeFrequencyArray = new double[312];
        double maxIntAsDouble = (double)Integer.MAX_VALUE;
        double expectedCount = (double) maxIntAsDouble/312;
        double expectedFrequency = expectedCount/maxIntAsDouble;

        int numOuterIters = 120;
        while (numOuterIters > 0){
            int[] zeroTo311CountingArray = new int[312];
            int numInnerIters = 0;
            while (numInnerIters < Integer.MAX_VALUE)
            {
                int randomResult = getRandomIntBetweenZeroAnd312ExclusivePRNG();
                zeroTo311CountingArray[randomResult]++;
                numInnerIters++;
            }
            for (int i=0; i<312; i++) //increment each result to index in results array
            {
                double actualCount = (double)zeroTo311CountingArray[i];
                double actualFrequency = actualCount/maxIntAsDouble;
                double actualMinusExpectedFrequency = actualFrequency - expectedFrequency;
                zeroTo311ActualCumulativeFrequencyArray[i] += actualMinusExpectedFrequency;
            }
            numOuterIters--;
        }
        for (int j=0; j<312; j++) {
            System.out.println("number "+j+" generated this often:" +zeroTo311ActualCumulativeFrequencyArray[j]);
        }
    }
}
