package com.carreg.acceptancetest.Config;

public interface Config {

  String baseUrl = System.getProperty("baseUrl", "https://cartaxcheck.co.uk/");
  String browser = System.getProperty("browser", "chrome");
}
