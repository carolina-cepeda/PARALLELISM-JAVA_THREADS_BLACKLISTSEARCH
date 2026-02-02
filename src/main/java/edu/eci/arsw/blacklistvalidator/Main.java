/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main {

    public static void main(String a[]) {
        HostBlackListsValidator hblv = new HostBlackListsValidator();

        int numberOfThreads = 32; //Runtime.getRuntime().availableProcessors()
        System.out.println("Using " + numberOfThreads + " threads");

        long startTime = System.currentTimeMillis();

        List<Integer> blackListOcurrences = hblv.checkHost("202.24.34.55", numberOfThreads); // 212.24.24.55 Reported as
                                                                                             // trustworthy
                                                                                             // 202.24.34.55 Reported as
                                                                                             // NOT trustworthy
                                                                                             // 200.24.34.55 Reported as
                                                                                             // NOT trustworthy
        long endTime = System.currentTimeMillis();

        System.out.println("The host was found in the following blacklists:" + blackListOcurrences);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    // ==============================================================
    // Metodo Original del laboratorio
    // ==============================================================

    public static void mainOld(String a[]) {
        long startTime = System.currentTimeMillis();
        HostBlackListsValidator hblv = new HostBlackListsValidator();
        long endTime = System.currentTimeMillis();
        List<Integer> blackListOcurrences = hblv.checkHost("200.24.34.55");
        System.out.println("The host was found in the following blacklists:" + blackListOcurrences);
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

}