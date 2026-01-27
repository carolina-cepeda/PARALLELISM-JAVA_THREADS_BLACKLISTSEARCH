/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.threads.CountThread;

import java.util.List;

/**
 *
 * @author hcadavid
 */
public class Main{
    
    public static void main(String a[]) {
        HostBlackListsValidator hblv = new HostBlackListsValidator();
        List<Integer> blackListOcurrences = hblv.checkHost("200.24.34.55");
        System.out.println("The host was found in the following blacklists:" + blackListOcurrences);

        CountThread count1 = new CountThread(0, 99);
        CountThread count2 = new CountThread(99, 199);
        CountThread count3 = new CountThread(199, 299);


        // Con el metodo start los hilos empiezan y terminan de forma no deterministica

        //count1.start();
        //count2.start();
        //count3.start();

        //Con el metodo run los hilos empiezan y terminan en orden.
        count1.run();
        count2.run();
        count3.run();

        
    }
}
