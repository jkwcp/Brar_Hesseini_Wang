package ca.bcit.brar_hesseini_wang;

public class Country {
    /*things to do, variable types need rework*/
    private String _name;
    private String _capital;
    private String _region;
    private String _population;
    private String _area;
    private String _borders;
    private String _flag;


    /*
        things to do, default constructor ok?
     */
    public Country(){ }

    // Each country has a name, description and an image resource
    public Country(String name, String capital, String region, String population, String area,
        String borders, String flag) {
        _name = name;
        _capital = capital;
        _region = region;
        _population = population;
        _area = area;
        _borders = borders;
        _flag = flag;

    }

    public String get_name() {
        return _name;
    }

    public String get_capital() {
        return _capital;
    }

    public String get_region() {
        return _region;
    }

    public String get_population() { return _population; }

    public String get_area() {return _area;}

    public String get_borders() { return _borders; }

    public String get_flag() {return _flag;}

    public void set_name(String name){
        _name = name;
    }

    public void set_region(String region){
        _region = region;
    }

    public void set_capital(String capital) {
        _capital = capital;
    }

    public void set_population(String population) {
        _population = population;
    }

    public void set_area(String area) {
        _area = area;
    }

    public void set_borders(String borders) {
        _borders = borders;
    }

    public void set_flag(String flag) {
        _flag = flag;
    }



    public String toString() {
        return _name;
    }
}