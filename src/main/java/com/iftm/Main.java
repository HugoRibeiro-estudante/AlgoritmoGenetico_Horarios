package com.iftm;

import java.util.Random;
public class Main {
    public static void main(String[] args) {

        int[] arrayCounters = new int[5];
        String[][] period1 = makeMatrix();
        String[][] period2 = makeMatrix();
        String[][] period3 = makeMatrix();
        String[][] period4 = makeMatrix();
        String[][] period5 = makeMatrix();

        arrayCounters[0] = Integer.parseInt(period1[0][29]);
        arrayCounters[1] = Integer.parseInt(period2[0][29]);
        arrayCounters[2] = Integer.parseInt(period3[0][29]);
        arrayCounters[3] = Integer.parseInt(period4[0][29]);
        arrayCounters[4] = Integer.parseInt(period5[0][29]);

        try {
            sortAndDisplay(arrayCounters);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter para inteiro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }

    }

    public static void sortAndDisplay(int[] arrayCounters) {

        for (int i = 0; i < arrayCounters.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrayCounters.length; j++) {
                if (arrayCounters[j] < arrayCounters[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arrayCounters[minIndex];
            arrayCounters[minIndex] = arrayCounters[i];
            arrayCounters[i] = temp;
        }


        System.out.println("Valores ordenados:");
        for (int i = 0; i < arrayCounters.length; i++) {
            System.out.println("Period " + (i + 1) + " Conflict Counter: " + arrayCounters[i]);
        }
    }

    public static String[][] makeMatrix(){

        Random random = new Random();

        String[][] matrixProf = {{"WE"}, {"EE"}, {"EA"}, {"CM"}, {"WP"}, {"CB"}, {"AA"}, {"CE"}, {"CS"},
                {"HR"}, {"PA"}, {"RA"}, {"LI"}, {"VP"}};

        String[][] matrixDisc ={{"00", "01", "02", "03", "04"}, {"05", "06", "07", "08", "09"}, {"10",
                "11", "12", "13", "14"}, {"15", "16", "17", "18", "19"}, {"20", "21",
                "22", "23", "24"}};

        //array para contar vezes que uma disciplina foi sorteada
        int[] discTimes = new int[5];
        int conflictCounter = 0;

        int columns = 30; // mais 4 para os intervalos // = + 1 para counter
        String[][] matrix = new String[4][columns];
        int prof = 0;
        int disc = 0;
        int supportIndex = 0;

        
        for(int i = 0; i < columns - 1; i++){

            if (i == 5 || i == 11 || i == 17 || i == 23) {
                matrix[0][i] = " ";
                matrix[1][i] = " ";
                matrix[2][i] = " ";
                matrix[3][i] = " ";
                continue;
            }

            //definindo dentro do matrixDisc em qual array esta a posição de i [[],[]]
            switch (i) {
                case 0: case 1: case 2: case 3: case 4:
                    supportIndex = 0;
                    break;
                case 6: case 7: case 8: case 9: case 10:
                    supportIndex = 1;
                    break;
                case 12: case 13: case 14: case 15: case 16:
                    supportIndex = 2;
                    break;
                case 18: case 19: case 20: case 21: case 22:
                    supportIndex = 3;
                    break;
                case 24: case 25: case 26: case 27: case 28:
                    supportIndex = 4;
                    break;
                default:
                    break;
            }

            disc = random.nextInt(5);
            while(matrixDisc[supportIndex][disc].equals(" ")){
                conflictCounter++;
                disc = random.nextInt(5);
            }

            // definindo os 4 horarios dentro dia da semana
            for (int j = 0; j < 2; j++) {

                prof = random.nextInt(12);
                do{
                    disc = random.nextInt(5);
                }while(matrixDisc[supportIndex][disc] == " ");


                if (i > 4 && matrixProf[prof].length > 1) {

                    while (checkEquality(i, matrixProf[prof])) {
                        conflictCounter++;
                        prof = random.nextInt(12);
                    }
                }

                String union = matrixProf[prof][0] + matrixDisc[supportIndex][disc];
                if(j == 0){
                    matrix[0][i] = union;
                    matrix[1][i] = union;
                } else {
                    matrix[2][i] = union;
                    matrix[3][i] = union;
                }
                
                discTimes[disc] += 1;

            }

            if(discTimes[disc] >= 4)
                matrixDisc[supportIndex][disc] = " ";

            if(i == 4 || i == 9 || i == 14 || i == 19 || i == 24){
                discTimes = new int[5];
            }

            String[] newArray = new String[matrixProf[prof].length + 1];
            System.arraycopy(matrixProf[prof], 0, newArray, 0, matrixProf[prof].length);
            newArray[newArray.length - 1] = i + "";
            matrixProf[prof] = newArray;
        }

        showMatrix(matrix);
        System.out.println();
        matrix[0][29] = String.valueOf(conflictCounter);
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
