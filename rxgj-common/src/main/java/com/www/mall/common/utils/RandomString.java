package com.www.mall.common.utils;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class RandomString {
    public static final String SOURCES_NUMBERS ="1234567890";
    public static final String SOURCES_NUMBERS_AND_UPLETTERS ="ABCDE123FGHIJKL456MNOPQRST789UVWXYZ0";
    public static final String SOURCES_NUMBERS_AND_LOWERLETTERS ="abcdefghijklmnopqrstuvwxyz1234567890";
    public static final String SOURCES_UPLETTERS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String SOURCES_LOWERLETTERS ="abcdefghijklmnopqrstuvwxyz";
    public static final String SOURCES_ALL ="1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    
    /**
     * Generate a random string.
     *
     * @param random the random number generator.
     * @param characters the characters for generating string.
     * @param length the length of the generated string.
     * @return
     */
    public static String generateString(Random random, String characters , int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
    
    public static String getOrderVerificationCode() {
    	StringBuilder builder = new StringBuilder(DateUtils.getTimeString(new Date(), DateUtils.TIME_FORMAT_SHORT));
    	builder.append(generateString(new SecureRandom(),SOURCES_NUMBERS,6));
    	return builder.toString();
    }
    
//    
//    public static void main(String[] args) {
//        RandomString rs = new RandomString();
//        System.out.println(generateString(new Random(),SOURCES_NUMBERS,8));
//        System.out.println(generateString(new SecureRandom(),SOURCES_NUMBERS_AND_UPLETTERS,8));
//        System.out.println(generateString(new Random(),SOURCES_NUMBERS_AND_LOWERLETTERS,8));
//        System.out.println(generateString(new Random(),SOURCES_UPLETTERS,8));
//        System.out.println(generateString(new Random(),SOURCES_LOWERLETTERS,8));
//        System.out.println(generateString(new Random(),SOURCES_ALL,8));
//        }

    
}