package com.carreg.acceptancetest.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCarInputFile {

  public static List<String> getCarRegDetails(String carRegNumber)
      throws IOException, CsvValidationException {
    List<List<String>> records = new ArrayList<List<String>>();
    List<String> carRegDetails = new ArrayList<>();
    try (CSVReader csvReader =
        new CSVReader(new FileReader("src\\test\\resources\\data\\car_output.csv")); ) {
      String[] values = null;
      while ((values = csvReader.readNext()) != null) {
        records.add(Arrays.asList(values));
      }
    }
    for (List<String> record : records) {

      if (record
          .get(0)
          .replaceAll("\\s+", "")
          .equalsIgnoreCase(carRegNumber.replaceAll("\\s+", ""))) {
        carRegDetails = record;
      }
    }
    return carRegDetails;
  }

  public static List<String> getMatchingCarReg() throws IOException {
    Pattern regPattern = Pattern.compile("\\w{2}\\d{2}\\s?\\w{3}");

    List<String> matchingPatterns = new ArrayList<>();

    try (BufferedReader br =
        new BufferedReader(new FileReader("src\\test\\resources\\data\\car_input.txt"))) {
      for (String line; (line = br.readLine()) != null; ) {
        Matcher match = regPattern.matcher(line);
        while (match.find()) {
          int start = match.start(0);
          int end = match.end(0);
          matchingPatterns.add(line.substring(start, end));
        }
      }
    }
    return matchingPatterns;
  }
}
