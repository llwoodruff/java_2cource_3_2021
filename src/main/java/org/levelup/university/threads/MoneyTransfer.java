package org.levelup.university.threads;

import javax.persistence.criteria.CriteriaBuilder;

public class MoneyTransfer {
    public void transferMoney(Integer acc1, Integer acc2, double amount) {
        Integer firstLocked = acc1 > acc2 ? acc2:acc1;
        Integer secondLocked = acc2 > acc1 ? acc1:acc2;
        synchronized (firstLocked) {
            synchronized (secondLocked){

                System.out.println(amount);
            }
        }
    }

    public static void main(String[] args) {
        MoneyTransfer mt = new MoneyTransfer();

        Integer fAcc = 100;
        Integer sAcc = 101;
        mt.transferMoney(fAcc, sAcc, 54.32);
        mt.transferMoney(sAcc, fAcc, 32.41);
    }
}
