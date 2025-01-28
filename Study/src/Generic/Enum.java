package Generic;

enum COUNTRY {
    INDIA, USA, UK, CANADA;
}


public class Enum {

    COUNTRY country;

    public Enum(COUNTRY country){
        this.country  = country;
    }

    public void test ()
    {
        int count = 0;
        switch(country) {
            case INDIA:
                System.out.println("Bharat");

                break;
            case USA:
                System.out.println("US");

                break;

            case UK:
                System.out.println("KINGDOM");

                break;
            case CANADA:
                System.out.println("VISA");



                break;
        }

    }


    public static void main(String args[]) {

        String value = "INDIA";
        String value1 = "USA";
        String value2 = "UK";
        String value3 = "CANADA";
//        Sorting.Main nation = new Sorting.Main(COUNTRY.valueOf(value));
//
//        nation.test();
//
//        Sorting.Main nation1 = new Sorting.Main(COUNTRY.valueOf(value1));
//
//        nation1.test();
//        nation.test();
//
//        Sorting.Main nation2 = new Sorting.Main(COUNTRY.valueOf(value2));
//
//        nation2.test();
//
//        Sorting.Main nation3 = new Sorting.Main(COUNTRY.valueOf(value3));
//        nation3.test();

    }
}
