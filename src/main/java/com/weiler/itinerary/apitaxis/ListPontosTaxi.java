package com.weiler.itinerary.apitaxis;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class ListPontosTaxi {


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(new File("arquivo.csv"));

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            Scanner linhaScanner = new Scanner(linha);
            linhaScanner.useDelimiter("#");

            String nomedoPonto = linhaScanner.next();
            String latitude = linhaScanner.next();
            String longitude = linhaScanner.next();
            String dataHoraCadastro = linhaScanner.next();

            String format = String.format(new Locale("pt","BR"), "%s # %s # %s # %s # %n",nomedoPonto, latitude, longitude, dataHoraCadastro);
            System.out.println(format);
            linhaScanner.close();
            String[] taxi = linha.split("#");

            System.out.println("NOME_DO_PONTO: " + taxi[0]);
            System.out.println("LATITUDE: " + taxi[1]);
            System.out.println("LONGITUDE: " + taxi[2]);
            System.out.println("DATA_HORA_CADASTRO: " + taxi[3] + "\n");

        }
        scanner.close();
    }
}



