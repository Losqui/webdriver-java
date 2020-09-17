package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{


    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage digitarLogin(String login){ //Método estrutural
        navegador.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);

        return this;
    }
    public LoginFormPage digitarSenha(String password){
        navegador.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);
        return this;// Retornar a mesma página
    }
    public SecretaPage clicarSignIn(){
        navegador.findElement(By.linkText("SIGN IN")).click();
        return new SecretaPage(navegador);
    }
    // Foi utilizado o método estrutural dentro do métodp funcional - Combinação dos métodos
    public SecretaPage fazerLogin(String login, String senha){ // Método funcional
        digitarLogin(login);
        digitarSenha(senha);
        clicarSignIn();

        return new SecretaPage(navegador);
    }
}
