package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "informacoesUsuarioTestData.csv")

public class informacoesUsuarioTest {
    private WebDriver navegador;
    //Pegar o nome do Método
        @Rule
        public TestName test = new TestName();

    @Before
    public void setUp(){
        navegador = Web.createChrome();

        // clicar no link que possui o texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click(); //ou da forma abaixo
        // WebElement linkSignIn = navegador.findElement(By.linkText("Sign in"));
        // linkSignIn.click();

        // Identificando o formulário de Login
        // WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com nome "login" que está debtro do formulário de id = "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        // Digitar no campo com nome "password" que está debtro do formulário de id = "signinbox o texto "123456"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    //public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo,@Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

        // Clicar no botão atraves do seu xpath = "//button[@data-target="addmoredata"]"
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificat a popup onde está o formulário de id= "addmoredata"
        WebElement popupAssMoteData = navegador.findElement(By.id("addmoredata"));

        //Na combo de name "type" escolhe a opção "Phone"
        WebElement campoType = popupAssMoteData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        // No campo de name = "contact" digitar "+5532999991111"
        popupAssMoteData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar no link de text "SAVE" que está no popup
        popupAssMoteData.findElement(By.linkText("SAVE")).click();

        // na mensagem  de id= "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mesagem = mensagemPop.getText();
        Assert.assertEquals(mensagemEsperada, mesagem);


        // Validar que dentro do elemento com class "me" está o texto "Hi, Julio"
        // WebElement me = navegador.findElement(By.className("me"));
        // String textoElementoMe = me.getText();
        // Assert.assertEquals("Hi, Julio",textoElementoMe);
    }
   @Test
    public void removerUmcontatoDeUmUsuario(){
        //+55999991111

        // CLicar no elemento pelo seu elemento xpath //span[text()="+55999991111"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+55999991111\"]/following-sibling::a")).click();

        // Confirmar a janela javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mesagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mesagem);

        //Evidenciar o resultado do teste com um screenshot
        String screenShotArquivo = "C:/Users/I5/IdeaProjects/test-report/taskit/" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenShotArquivo);

        // Aguardar até 10 segundo para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();

    }



       @After
       public void tearDown(){
            // Fechar o navegador
           navegador.quit();
        }

}
