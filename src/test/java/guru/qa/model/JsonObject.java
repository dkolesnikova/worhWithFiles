package guru.qa.model;

import org.apache.poi.ss.formula.functions.Address;

import java.util.ArrayList;

public class JsonObject {
    public int id;
    public String name;
    public boolean work;
    public ArrayList <Object> products;
    public Address address;
    public static class Address {
        public String city;
        public String country;
    }
}

