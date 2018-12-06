package ba.unsa.etf.rpr.tutoriajal7;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Tutorijal {

    public static List<Grad> ucitajGradove() throws FileNotFoundException {
        Scanner scanner;
        try {
            scanner = new Scanner(new FileReader("mjerenja.txt"));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Datoteka ulaz.txt ne postoji ili se ne može otvoriti");
        }

        List<Grad> result = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                continue;
            }

            String[] values = line.split(",");
            Grad newCity = new Grad();
            newCity.setNaziv(values[0]);
            newCity.setTemperature(Arrays.stream(values)
                    .skip(1)
                    .limit(1000)
                    .mapToDouble(Double::parseDouble)
                    .toArray());
            result.add(newCity);
        }

        return result;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
