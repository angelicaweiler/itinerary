package com.weiler.itinerary.apitaxis;

import java.io.*;

public class AddPontosTaxi {

    public static void main(String[] args) throws IOException {

        System.out.println("Clique abaixo e insira as informações para cadastro de novos pontos de Taxi no seguinte formato:");
        System.out.println(" ");
        System.out.println("NOME_DO_PONTO#LATITUDE#LONGITUDE#DATA_HORA_CADASTRO");
        System.out.println(" ");
        System.out.println("ATENÇÃO: Importante o uso de # como separador das informações!");
        System.out.println(" ");
        System.out.println("Para finalizar o processo dê dois cliques na tecla Enter do seu teclado!");
        InputStream fis = System.in;
        Reader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        OutputStream fos = new FileOutputStream("arquivo.csv");
        Writer osw = new OutputStreamWriter(fos);
        BufferedWriter bw = new BufferedWriter(osw);

        String linha = br.readLine();

        while( !(linha == null || linha.isEmpty()) ) {
            bw.write(linha);
            bw.newLine();
            bw.flush();
            linha = br.readLine();
        }

        br.close();
        bw.close();

    }
}
//    //codigo criado para uso com método servlet - não utilizado
//    public void add(String lista) throws IOException {
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\Users\\angel\\Dev\\ItineraryDimed\\src\\main\\java\\com\\weiler\\itinerarydimed\\arquivo\\arquivo.csv"));
//        oos.writeObject(lista);
//        oos.close();
//    }
