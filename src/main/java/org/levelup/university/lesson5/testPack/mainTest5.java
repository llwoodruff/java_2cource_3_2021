package org.levelup.university.lesson5.testPack;

public class mainTest5 {
    public static void main(String[] args){
    /*String str;
    int cnt;

    public mainTest5(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }*/

    double floatVar = 43.5;
    int intVar = 5;
    String stringVar = "test";
    String fs = String.format("Значение переменной float = " +
            "%f, пока значение integer " +
            "переменная = %d, и string " +
            "= %s", floatVar, intVar, stringVar);
    System.out.println(fs);

}
}
