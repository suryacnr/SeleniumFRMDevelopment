package pageObjects;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"admin","admin2"},{"admin123456","admin123"},{"admin123","dmin1"}};
}
}