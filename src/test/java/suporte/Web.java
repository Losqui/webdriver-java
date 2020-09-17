package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String AUTOMATE_USERNAME = "raynervalentelos1";
    public static final String AUTOMATE_ACCESS_KEY = "uJ1qhxUF5Np87kvcpfP4";
    public static final String URL = "https://" + AUTOMATE_USERNAME + ":" + AUTOMATE_ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        // Abrindo o Navegador
        System.setProperty("webdriver.chrome.driver","C:\\driver\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Navegando para a página do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return navegador;
    }
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();
        caps . setCapability ( "os" , "Windows" );
        caps . setCapability ( "os_version" , "10" );
        caps . setCapability ( "navegador" , "Chrome" );
        caps . setCapability ( "browser_version" , "60" );
        caps . setCapability ("browserstack.debug", "true");

        WebDriver navegador = null;
        try {
            navegador  = new RemoteWebDriver(new URL(URL), caps);

            navegador.get("http://www.juliodelima.com.br/taskit");
            navegador.manage().window().maximize();
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }catch (MalformedURLException e){
            System.out.println("Houveram problemas com a URL: " + e.getMessage());
        }

        return navegador;


    }

}
