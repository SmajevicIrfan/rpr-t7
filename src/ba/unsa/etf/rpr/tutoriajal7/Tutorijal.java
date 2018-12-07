package ba.unsa.etf.rpr.tutoriajal7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

    private static UN ucitajXml(List<Grad> gradovi) {
        Document document = null;
        try {
            DocumentBuilder documentReader = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = documentReader.parse(new File("drzave.xml"));
        } catch (Exception e) {
            throw new RuntimeException("Problem sa citanjem datoteke drzave.xml");
        }

        NodeList countries = document.getDocumentElement().getChildNodes();
        List<Drzava> drzave = new ArrayList<>();
        for (int i = 0; i < countries.getLength(); i++) {
            Node child = countries.item(i);
            if (child instanceof Element) {
                Drzava newCountry = uzitajDrzavu((Element) child, gradovi);
                if (newCountry != null) {
                    drzave.add(newCountry);
                }
            }
        }

        UN response = new UN();
        response.setDrzave(drzave);
        return response;
    }

    private static Drzava uzitajDrzavu(Element element, List<Grad> context) {
        Drzava response = new Drzava();
        response.setBrojStanovnika(Integer.parseInt(element.getAttribute("stanovnika")));
        response.setNaziv(element.getElementsByTagName("naziv").item(0).getTextContent());

        Element area = (Element) element.getElementsByTagName("povrsina").item(0);
        response.setPovrsina(Double.parseDouble(area.getTextContent()));
        response.setJedinicaZaPovrsinu(area.getAttribute("jedinica"));

        Element capitalCity = (Element) element.getElementsByTagName("glavnigrad").item(0);
        Grad capital = new Grad();
        capital.setNaziv(capitalCity.getElementsByTagName("naziv").item(0).getTextContent());
        int capitalCityIndex = context.indexOf(capital);
        if (capitalCityIndex == -1) {
            return null;
        }

        response.setGlavniGrad(context.get(capitalCityIndex));
        return response;
    }

    public static void main(String[] args) {
        try {
            List<Grad> cities = ucitajGradove();
            UN un = ucitajXml(cities);

            for (Drzava country : un.getDrzave()) {
                System.out.println(country.getNaziv());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka ne postoji ili se ne može otvoriti");
        }
    }
}
