package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

// This code is written by SAAD MANSOOR
public class Geekdemy {
    public static void main (String[] args){
        File file = new File("C:\\Geekdemy\\sample_input\\input1.txt");
        //Change the path with your local directory where you have saved the project

        try {
            Scanner sc = new Scanner(file);
            HashMap<String,Integer> hashmapProgramme = new HashMap<>();
            HashMap<String,Float> hashmapPrice = new HashMap<>();
            HashMap<String, Float> bill = new HashMap<>();
            HashMap<String,Float> beforeMembership = new HashMap<>();
            HashMap<String,Integer> programmeDefault = new HashMap<>();
            float sum =0;
            int count = 0;
            int totalIterations=0;
            float membership = 200;
            boolean print = false;
            while(sc.hasNextLine()){

                String input = sc.nextLine();
//                Add Programme Logic here
                String [] arr = input.split(" ");
                if ( arr[0].equals("ADD_PROGRAMME") ){
//                    String str = sc.nextLine();
//                    String [] arr = str.split(" ");
                    hashmapProgramme.put(arr[1], Integer.valueOf(arr[2]));
                    hashmapPrice.put(arr[1],price(arr));
                    beforeMembership.put(arr[1],price(arr));
                    programmeDefault.put(arr[1],1);
                    bill.put("TOTAL",calculateSum(hashmapPrice));
                    bill.put("SUB_TOTAL",calculateSum(hashmapPrice));
                }
//                Add Programme Logic here

//                Pro Membership Logic Here
                if ( arr[0].equals("ADD_PRO_MEMBERSHIP") ){
//                    membership = sc.nextInt();
//                    if (membership == 200){
                        sum = 0;
                        for (float value : afterMembership(hashmapPrice).values()) {
                            sum += value;
                        }
                            bill.put("PRO MEMBERSHIP FEE",membership);
                            bill.put("TOTAL_PRO_DISCOUNT",calculateSum(beforeMembership)-sum);
                            bill.put("TOTAL",sum);
//                    }
                }
//              Pro Membership Logic Here

//              Coupon Logic Here
                if ( arr[0].equals("APPLY_COUPON") ){
//                    String str = sc.nextLine();
                    if ( arr[1].equals("DEAL_G20") ){
                        if ( bill.get("TOTAL") < 6666 ){
                            bill.put("TOTAL", bill.get("TOTAL") + 500  );           // logic for enrollment price
                            bill.put("ENROLLMENT_FEE", 500F);
                        }else{
                            bill.put("TOTAL", bill.get("TOTAL")  );
                        }
                        if (bill.get("TOTAL") >= 10000){
                            bill.put("COUPON_DISCOUNT", (float) (bill.get("TOTAL") * 0.2) );

                            bill.put("TOTAL",  bill.get("TOTAL")-(float) (bill.get("TOTAL")*0.2)) ;
                        }else{
                            System.out.println(couponError(bill));
                        }


                    }
                    if ( arr[1].equals("DEAL_G5") ){
                        if ( bill.get("TOTAL") < 6666 ){
                            bill.put("TOTAL", bill.get("TOTAL") + 500  );           // logic for enrollment price
                            bill.put("ENROLLMENT_FEE", 500F);
                        }else{
                            bill.put("TOTAL", bill.get("TOTAL")  );
                        }

                        if ( hashmapProgramme.size()>=2 || checkValueisGreater(hashmapProgramme) ){
                            System.out.println(bill.get("TOTAL"));
                            bill.put("COUPON_DISCOUNT", (float) (bill.get("TOTAL") * 0.05) );
                            bill.put("TOTAL", bill.get("TOTAL") - (float) (bill.get("TOTAL")*0.05)) ;
                            //start from here
                        }
                        else{
                            System.out.println("YOUR CART HAS NOT SUFFICIENT PROGRAMMES TO GET ELIGIBLE FOR THIS OFFER");
                        }
                    }
                }
                else{
                    count++;
                }
//              Coupon Logic Here
                if ( sc.hasNextLine()==false &&  input.equals("PRINT_BILL") ){
                    print=true;
                }

                totalIterations++;
            }
            if( count == totalIterations){
                if ( hashmapProgramme.size() >=4 || calculateSumProgramme(hashmapProgramme) >= 4 ){

                    if ( bill.get("TOTAL") < 6666 ){

                        bill.put("TOTAL", bill.get("TOTAL") + 500  );           // logic for enrollment price
                        bill.put("ENROLLMENT_FEE", 500F);
                    }else{

                        bill.put("TOTAL", bill.get("TOTAL")  );
                    }

                    bill.put("COUPON_DISCOUNT", Float.valueOf(lowestProgramme(programmeDefault)));
                    bill.put("TOTAL", bill.get("TOTAL") - Float.valueOf(lowestProgramme(programmeDefault)));
                }
                else{
                    if (bill.get("TOTAL") < 6666){
                        bill.put("TOTAL", bill.get("TOTAL") + 500 );            // logic for enrollment price
                        bill.put("ENROLLMENT_FEE", 500F);
                    }else{
                        bill.put("TOTAL", bill.get("TOTAL") );
                    }
                }

            }

            if ( print ){
                System.out.println("SUB_TOTAL"+" "+bill.get("SUB_TOTAL"));
                if (bill.containsKey("COUPON_DISCOUNT")){
                    System.out.println("COUPON_DISCOUNT"+" "+bill.get("COUPON_DISCOUNT"));
                }else{
                    System.out.println("COUPON_DISCOUNT 0");
                }
                if (bill.containsKey("TOTAL_PRO_DISCOUNT")){
                    System.out.println("TOTAL_PRO_DISCOUNT"+" "+bill.get("TOTAL_PRO_DISCOUNT"));
                }else{
                    System.out.println("TOTAL_PRO_DISCOUNT 0");
                }
                if (bill.containsKey("PRO MEMBERSHIP FEE")){
                    System.out.println("PRO MEMBERSHIP FEE"+" "+bill.get("PRO MEMBERSHIP FEE"));
                }else{
                    System.out.println("PRO MEMBERSHIP FEE 0");
                }
                if (bill.containsKey("ENROLLMENT_FEE")){
                    System.out.println("ENROLLMENT_FEE "+bill.get("ENROLLMENT_FEE"));
                }else{
                    System.out.println("ENROLLMENT_FEE 0");
                }
                System.out.println("TOTAL " + bill.get("TOTAL"));

            }else{
                System.out.println("ERROR");
            }

            sc.close(); // closes the scanner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static float price(String [] arr){
        int p = Integer.parseInt(arr[2]);
        String name = arr[1];
        if ( name.equals("CERTIFICATION") ){
            return p * 3000;
        }
        if ( name.equals("DEGREE") ){
            return p * 5000;
        }
        if ( name.equals("DIPLOMA") ){
            return p * 2500;
        }
        return Integer.parseInt(null);
    }

    static HashMap<String, Float> afterMembership(HashMap<String,Float> hashmapProgramme ){
        if ( hashmapProgramme.containsKey("CERTIFICATION") ){
            hashmapProgramme.put("CERTIFICATION", hashmapProgramme.get("CERTIFICATION") - (int) (hashmapProgramme.get("CERTIFICATION")*0.02)) ;
        }
        if ( hashmapProgramme.containsKey("DEGREE") ){
            hashmapProgramme.put("DEGREE", hashmapProgramme.get("DEGREE") - (int) (hashmapProgramme.get("DEGREE")*0.03)) ;
        }
        if ( hashmapProgramme.containsKey("DIPLOMA") ){
            hashmapProgramme.put("DIPLOMA",  hashmapProgramme.get("DIPLOMA") - (int) (hashmapProgramme.get("DIPLOMA")*0.01)) ;
        }
        return hashmapProgramme;
    }

    static float calculateSum(HashMap<String, Float> hashMap) {
        float sum = 0;
        for (float value : hashMap.values()) {
            sum += value;
        }
        return sum;
    }
    static int calculateSumProgramme(HashMap<String, Integer> hashMap) {
        int sum = 0;
        for (float value : hashMap.values()) {
            sum += value;
        }
        return sum;
    }
    static boolean checkValueisGreater(HashMap<String, Integer> hashMap){
        for ( int value :hashMap.values() ){
            if (value>2){
                return true;
            }
        }
        return false;
    }
    static String couponError( HashMap<String, Float> hashMap ){
        if ( hashMap.get("TOTAL") < 10000 ){
            return "COUPON CANNOT BE APPLIED AS YOUR BILL IS NOT GREATER THAN 10000";
        }
        else{
            return "COUPON CANNOT BE APPLIED AS PROGRAMME SIZE IS LESS";
        }

    }
    static int lowestProgramme(HashMap<String, Integer> hashMap){
        if ( hashMap.containsKey("CERTIFICATION") && hashMap.containsKey("DEGREE") && !hashMap.containsKey("DIPLOMA")) {
            return Integer.parseInt(String.valueOf(hashMap.get("CERTIFICATION")*3000));
        }
        if ( hashMap.containsKey("DIPLOMA") && hashMap.containsKey("DEGREE") && !hashMap.containsKey("CERTIFICATION")) {
            return Integer.parseInt(String.valueOf(hashMap.get("DIPLOMA")*2500));
        }
        if ( hashMap.containsKey("DIPLOMA") && hashMap.containsKey("CERTIFICATION") && !hashMap.containsKey("DEGREE")) {
            return Integer.parseInt(String.valueOf(hashMap.get("DIPLOMA")*2500));
        }
        if ( hashMap.containsKey("DIPLOMA") && hashMap.containsKey("CERTIFICATION") && hashMap.containsKey("DEGREE")) {
            return Integer.parseInt(String.valueOf(hashMap.get("DIPLOMA")*2500));
        }
        if ( hashMap.containsKey("DIPLOMA") && !hashMap.containsKey("CERTIFICATION") && !hashMap.containsKey("DEGREE")) {
            return Integer.parseInt(String.valueOf(hashMap.get("DIPLOMA")*2500));
        }
        if ( hashMap.containsKey("CERTIFICATION") && !hashMap.containsKey("DIPLOMA") && !hashMap.containsKey("DEGREE")) {
            return Integer.parseInt(String.valueOf(hashMap.get("CERTIFICATION")*3000));
        }
        if ( !hashMap.containsKey("CERTIFICATION") && !hashMap.containsKey("DIPLOMA") && hashMap.containsKey("DEGREE")) {
            return Integer.parseInt(String.valueOf(hashMap.get("DEGREE")*5000));
        }
        return 0;
    }



}
