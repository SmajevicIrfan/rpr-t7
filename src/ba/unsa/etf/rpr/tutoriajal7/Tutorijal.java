package ba.unsa.etf.rpr.tutoriajal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tutorijal {

    private static List<Grad> ucitajGradove() throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new FileReader("mjerenja.txt"));

        List<Grad> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            String[] values = line.split(",");
            Grad newCity = new Grad();
            newCity.setNaziv(values[0].trim());
            newCity.setTemperature(Arrays.stream(values)
                    .skip(1)
                    .limit(1000)
                    .mapToDouble(Double::parseDouble)
                    .toArray());
            result.add(newCity);
        }

        return result;
    }

    /*private static UN ucitajXml(List<Grad> gradovi) {
        Document document = null;
        try {
            DocumentBuilder documentReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            throw new RuntimeException("Problem sa citanjem datoteke drzave.xml");
        }

        NodeList countries = document.getDocumentElement().getChildNodes();
        for (int i = )
    }*/

    public static void main(String[] args) {
        try {
            List<Grad> cities = ucitajGradove();

            for (Grad city : cities) {
                System.out.print(city.getNaziv() + ": ");
                for (double temperature : city.getTemperature()) {
                    System.out.print(temperature + " ");
                }

                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka ulaz.txt ne postoji ili se ne može otvoriti");
        }
    }
}
