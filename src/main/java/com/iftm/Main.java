package com.iftm;

import java.util.Random;
public class Main {
    public static void main(String[] args) {

        String[][] period1 = makeMatrix();
        // String[][] period2 = makeMatrix();
        // String[][] period3 = makeMatrix();
        // String[][] period4 = makeMatrix();
        // String[][] period5 = makeMatrix();


    }

    public static String[][] makeMatrix(){

        Random random = new Random();

        String[][] matrixProf = {{"WE"}, {"EE"}, {"EA"}, {"CM"}, {"WP"}, {"CB"}, {"AA"}, {"CE"}, {"CS"},
                {"HR"}, {"PA"}, {"RA"}, {"LI"}, {"VP"}};

        String[] vetorDisc ={"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24"};

        int columns = 29; // mais 4 para os intervalos
        String[][] matrix = new String[4][columns];
        int prof = 0;

        for(int i = 0; i < columns; i++){

            if (i == 5 || i == 11 || i == 17 || i == 23) {
                matrix[0][i] = "\s";
                matrix[1][i] = "\s";
                matrix[2][i] = "\s";
                matrix[3][i] = "\s";
                continue;
            }

            int disc = random.nextInt(25);

            for (int j = 0; j < 2; j++) {

                prof = random.nextInt(12);


                if (i > 4 && matrixProf[prof].length > 1) {

                    while (checkEquality(i, matrixProf[prof])) {
                        prof = random.nextInt(12);
                    }
                }

                String union = matrixProf[prof][0] + vetorDisc[disc];
                if(j == 0){
                    matrix[0][i] = union;
                    matrix[1][i] = union;
                } else {
                    matrix[2][i] = union;
                    matrix[3][i] = union;
                }

            }

            String[] newArray = new String[matrixProf[prof].length + 1];
            System.arraycopy(matrixProf[prof], 0, newArray, 0, matrixProf[prof].length);
            newArray[newArray.length - 1] = i + "";
            matrixProf[prof] = newArray;
        }

        showMatrix(matrix);

        return matrix;
    }

    private static void showMatrix(String[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkEquality(int i, String[] weekDay) {

        String[] copy = new String[weekDay.length];
        System.arraycopy(weekDay, 0, copy, 0, weekDay.length);

        copy[copy.length - 1] = i + "";
        copy = convertInWeekDay(weekDay,1);
        String[] arrayI = new String[1];
        arrayI[0] = i + "";

        for(int j = 1; j < weekDay.length; j++){
            convertInWeekDay(arrayI, 0);
            if(copy[j].equals(arrayI[0]) || copy.length >= 4){
                return true;
            }
        }
        System.out.println();
        return false;
    }


    private static String[] convertInWeekDay(String[] weekDay, int valueOfX) {


        for(int x = valueOfX; x < weekDay.length; x++) {
            if(weekDay[x].equals("0") || weekDay[x].equals("6") || weekDay[x].equals("12") || weekDay[x].equals("18") || weekDay[x].equals("24")) {
                weekDay[x] = "0";
            } else if(weekDay[x].equals("1") || weekDay[x].equals("7") || weekDay[x].equals("13") || weekDay[x].equals("19") || weekDay[x].equals("25")) {
                weekDay[x] = "1";
            } else if(weekDay[x].equals("2") || weekDay[x].equals("8") || weekDay[x].equals("14") || weekDay[x].equals("20") || weekDay[x].equals("26")) {
                weekDay[x] = "2";
            } else if(weekDay[x].equals("3") || weekDay[x].equals("9") || weekDay[x].equals("15") || weekDay[x].equals("21") || weekDay[x].equals("27")) {
                weekDay[x] = "3";
            } else if(weekDay[x].equals("4") || weekDay[x].equals("10") || weekDay[x].equals("16") || weekDay[x].equals("22") || weekDay[x].equals("28")) {
                weekDay[x] = "4";
            }
        }
        return weekDay;
    }



}