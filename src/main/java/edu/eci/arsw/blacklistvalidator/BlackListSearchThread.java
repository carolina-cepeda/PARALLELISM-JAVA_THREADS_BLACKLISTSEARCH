/*
 * Clase Thread para búsqueda paralela en listas negras
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.List;

/**
 * Thread que realiza la búsqueda de una IP en un segmento de servidores de
 * listas negras.
 * 
 * @author Jared Sebastian Farfan Guevara
 * @author Carolina Cepeda Valencia
 */
public class BlackListSearchThread extends Thread {

    private final int startIndex;
    private final int endIndex;
    private final String ipaddress;
    private final HostBlacklistsDataSourceFacade skds;

    private int ocurrencesCount;
    private int checkedListsCount;
    private final LinkedList<Integer> blackListOcurrences;

    /**
     * Constructor del hilo de búsqueda.
     * 
     * @param startIndex Índice inicial del segmento de servidores a buscar
     *                   (inclusivo)
     * @param endIndex   Índice final del segmento de servidores a buscar
     *                   (exclusivo)
     * @param ipaddress  Dirección IP a buscar en las listas negras
     * @param skds       Fachada de acceso a las listas negras
     */
    public BlackListSearchThread(int startIndex, int endIndex, String ipaddress, HostBlacklistsDataSourceFacade skds) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.ipaddress = ipaddress;
        this.skds = skds;
        this.ocurrencesCount = 0;
        this.checkedListsCount = 0;
        this.blackListOcurrences = new LinkedList<>();
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            checkedListsCount++;

            if (skds.isInBlackListServer(i, ipaddress)) {
                blackListOcurrences.add(i);
                ocurrencesCount++;
            }
        }
    }

    /**
     * Retorna el número de ocurrencias encontradas por este hilo.
     * 
     * @return Número de ocurrencias de la IP en listas negras
     */
    public int getOcurrencesCount() {
        return ocurrencesCount;
    }

    /**
     * Retorna el número de listas negras revisadas por este hilo.
     * 
     * @return Número de listas negras revisadas
     */
    public int getCheckedListsCount() {
        return checkedListsCount;
    }

    /**
     * Retorna la lista de índices de servidores donde se encontró la IP.
     * 
     * @return Lista con los números de las listas negras donde se encontró la IP
     */
    public List<Integer> getBlackListOcurrences() {
        return blackListOcurrences;
    }
}
