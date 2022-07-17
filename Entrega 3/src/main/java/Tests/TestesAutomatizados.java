package Tests;


import org.junit.Assert;
import org.testng.annotations.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ Test
public class TestesAutomatizados {

    private WebDriver driver;

    String webdriver = "C:\\Users\\glmgu\\Guilherme Documentos\\chromedriver.exe";


    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", webdriver);
       // driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        //sair do sistema
       // driver.quit();
    }

    public void testeUC01FluxoPrincipal() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/signin");
            // clica na caixa para digitar email
            driver.findElement(By.id("email")).click();
            // digita email
            driver.findElement(By.id("email")).sendKeys("teste@teste");
            //  clica para digitar senha
            driver.findElement(By.cssSelector(".card-body")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".btn")).getText(), ("Entrar"));
            // Clica no botão Entrar.
            driver.findElement(By.cssSelector(".btn")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

