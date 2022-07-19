package Tests;


import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@ Test
public class TestesAutomatizados {

    private WebDriver driver;

    String webdriver = "C:\\Users\\glmgu\\Guilherme Documentos\\chromedriver.exe";



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
            //Verifica se está na pagina de GamingStore
            Assert.assertEquals(driver.findElement(By.cssSelector(".logo")).getText(), ("GamingStore"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }


    public void testeUC01FluxoAlternativo() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/signin");
            // clica na caixa para digitar email
            driver.findElement(By.linkText("conta")).click();
            // 4 | assertText | css=h3 | Cadastro |
            Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(), ("Cadastro"));
            // 5 | click | id=name |  |
            driver.findElement(By.id("name")).click();
            // 6 | type | id=name | teste001 |
            driver.findElement(By.id("name")).sendKeys("teste001");
            // 7 | click | name=date |  |
            driver.findElement(By.name("date")).click();
            // 8 | click | name=date |  |
            driver.findElement(By.name("date")).click();
            // 9 | type | name=date | 0002-12-25 |
            driver.findElement(By.name("date")).sendKeys("0002-12-25");
            // 10 | type | name=date | 0020-12-25 |
            driver.findElement(By.name("date")).sendKeys("0020-12-25");
            // 11 | type | name=date | 0202-12-25 |
            driver.findElement(By.name("date")).sendKeys("0202-12-25");
            // 12 | type | name=date | 2021-12-25 |
            driver.findElement(By.name("date")).sendKeys("2021-12-25");
            // 13 | click | name=address |  |
            driver.findElement(By.name("address")).click();
            // 14 | type | name=address | teste001 |
            driver.findElement(By.name("address")).sendKeys("teste001");
            // 15 | type | id=email | teste001@com |
            driver.findElement(By.id("email")).sendKeys("teste0@com");
            // 16 | click | id=password |  |
            driver.findElement(By.id("password")).click();
            // 17 | type | id=password | teste001 |
            driver.findElement(By.id("password")).sendKeys("teste001");
            // 18 | type | id=confirmPassword | teste001 |
            driver.findElement(By.id("confirmPassword")).sendKeys("teste001");
            {
                WebElement dropdown = driver.findElement(By.name("platforms"));
                dropdown.findElement(By.xpath("//option[. = 'XBOX One']")).click();
            }

            {
                WebElement dropdown = driver.findElement(By.name("genres"));
                dropdown.findElement(By.xpath("//option[. = 'Ação']")).click();
            }
            driver.findElement(By.cssSelector(".btn")).click();
            // Usuário cadastrado com sucesso! |
            Assert.assertEquals(driver.findElement(By.cssSelector("span:nth-child(1)")).getText(), ("Usuário cadastrado com sucesso!"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC01FluxoExcecao() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/signin");
            driver.findElement(By.linkText("conta")).click();
            // 4 | click | id=name |  |
            driver.findElement(By.id("name")).click();
            // 5 | type | id=name | testeexcecao |
            driver.findElement(By.id("name")).sendKeys("testeexcecao");
            // 6 | click | css=.btn |  |
            driver.findElement(By.cssSelector(".btn")).click();
            // verifica se deu aviso
            {
                List<WebElement> elements = driver.findElements(By.cssSelector(".col-4 span"));
                assert (elements.size() > 0);
            }
            // 8 | click | name=date |  |
            driver.findElement(By.name("date")).click();
            // 9 | click | name=date |  |
            driver.findElement(By.name("date")).click();
            // 10 | click | name=date |  |
            driver.findElement(By.name("date")).click();
            // 11 | type | name=date | 2022-02-03 |
            driver.findElement(By.name("date")).sendKeys("2022-02-03");
            // 12 | type | name=date | 2022-02-03 |
            driver.findElement(By.name("date")).sendKeys("2022-02-03");
            // 13 | sendKeys | name=date | ${KEY_UP} |
            driver.findElement(By.name("date")).sendKeys(Keys.UP);
            // 14 | type | name=date | 2023-02-03 |
            driver.findElement(By.name("date")).sendKeys("2023-02-03");
            // 15 | click | name=address |  |
            driver.findElement(By.name("address")).click();
            // 16 | click | id=email |  |
            driver.findElement(By.id("email")).click();
            // 17 | type | id=email | teste@gmail.com |
            driver.findElement(By.id("email")).sendKeys("teste@gmail.com");
            // 18 | type | id=password | testeexcecao |
            driver.findElement(By.id("password")).sendKeys("testeexcecao");
            // 19 | type | id=confirmPassword | testeexcecao |
            driver.findElement(By.id("confirmPassword")).sendKeys("testeexcecao");
            // 20 | addSelection | name=platforms | label=XBOX One |
            {
                WebElement dropdown = driver.findElement(By.name("platforms"));
                dropdown.findElement(By.xpath("//option[. = 'XBOX One']")).click();
            }
            // 21 | addSelection | name=genres | label=Ação |
            {
                WebElement dropdown = driver.findElement(By.name("genres"));
                dropdown.findElement(By.xpath("//option[. = 'Ação']")).click();
            }
            // 22 | addSelection | name=platforms | label=PS5 |
            {
                WebElement dropdown = driver.findElement(By.name("platforms"));
                dropdown.findElement(By.xpath("//option[. = 'PS5']")).click();
            }
            // 23 | click | css=.btn |  |
            driver.findElement(By.cssSelector(".btn")).click();
            // 24 | assertText | css=span:nth-child(1) | Usuário cadastrado com sucesso! |
            Assert.assertEquals(driver.findElement(By.cssSelector("span:nth-child(1)")).getText(), ("Usuário cadastrado com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC02FluxoPrincipal() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/users/");
            {
                WebElement element = driver.findElement(By.cssSelector(".odd:nth-child(1) .btn-primary"));
                Actions builder = new Actions(driver);
                builder.moveToElement(element).perform();
            }
            driver.findElement(By.cssSelector(".odd:nth-child(1) .btn-primary > .fa-solid")).click();
            driver.findElement(By.name("address")).click();
            driver.findElement(By.name("address")).sendKeys("testeeditar");
            {
                WebElement dropdown = driver.findElement(By.id("platforms"));
                dropdown.findElement(By.xpath("//option[. = 'XBOX One']")).click();
            }
            {
                WebElement dropdown = driver.findElement(By.id("platforms"));
                dropdown.findElement(By.xpath("//option[. = 'PS5']")).click();
            }
            {
                WebElement dropdown = driver.findElement(By.id("genres"));
                dropdown.findElement(By.xpath("//option[. = 'Ação']")).click();
            }
            {
                WebElement dropdown = driver.findElement(By.id("genres"));
                dropdown.findElement(By.xpath("//option[. = 'Aventura']")).click();
            }
            driver.findElement(By.cssSelector(".btn-success")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span")).getText(), ("Usuário atualizado com sucesso!"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC02FluxoAlternativo() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/users/");
            driver.findElement(By.cssSelector(".odd:nth-child(1) .btn-danger > .fa-solid")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".ajs-content")).getText(), ("Deseja realmente deletar este usuário?"));
            driver.findElement(By.cssSelector(".ajs-ok")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span")).getText(), ("Usuário excluído com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC02FluxoExcecao() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/users/");
            driver.findElement(By.cssSelector(".odd:nth-child(1) .btn-danger")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".ajs-content")).getText(), ("Deseja realmente deletar este usuário?"));
            driver.findElement(By.cssSelector(".ajs-cancel")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".ajs-message")).getText(), ("Operação cancelada!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC03FluxoPrincipal() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/games/");
            Assert.assertEquals(driver.findElement(By.linkText("Cadastrar")).getText(), ("Cadastrar"));
            driver.findElement(By.linkText("Cadastrar")).click();
            driver.findElement(By.id("name")).click();
            driver.findElement(By.id("name")).sendKeys("jogoteste");
            driver.findElement(By.id("developer")).sendKeys("jogoteste");
            driver.findElement(By.id("date")).sendKeys("0002-02-25");
            driver.findElement(By.id("date")).sendKeys("0020-02-25");
            driver.findElement(By.id("date")).sendKeys("0202-02-25");
            driver.findElement(By.id("date")).sendKeys("2022-02-25");
            driver.findElement(By.id("publisher")).sendKeys("jogoteste");
            driver.findElement(By.id("price")).sendKeys("50");
            driver.findElement(By.id("description")).sendKeys("jogoteste");
            {
                WebElement dropdown = driver.findElement(By.name("gamePlatforms"));
                dropdown.findElement(By.xpath("//option[. = 'XBOX One']")).click();
            }
            {
                WebElement dropdown = driver.findElement(By.name("gameGenres"));
                dropdown.findElement(By.xpath("//option[. = 'Ação']")).click();
            }
            driver.findElement(By.cssSelector(".btn-success")).click();
            Assert.assertEquals((driver.findElement(By.cssSelector("span")).getText()), ("Jogo cadastrado com sucesso."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC03FluxoExcecao() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/games/");
            driver.findElement(By.linkText("Cadastrar")).click();
            driver.findElement(By.cssSelector(".btn-success")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".col-6:nth-child(3) span")).getText(), ("Campo obrigatório."));
            driver.findElement(By.id("name")).click();
            driver.findElement(By.id("name")).sendKeys("jgosoteste2");
            driver.findElement(By.id("developer")).click();
            driver.findElement(By.id("developer")).sendKeys("jogoteste2");
            driver.findElement(By.id("date")).sendKeys("2022-02-02");
            driver.findElement(By.id("date")).sendKeys("2022-02-02");
            driver.findElement(By.id("date")).sendKeys(Keys.UP);
            driver.findElement(By.id("date")).sendKeys("2023-02-02");
            driver.findElement(By.id("date")).sendKeys(Keys.DOWN);
            driver.findElement(By.id("date")).sendKeys("2022-02-02");
            driver.findElement(By.id("date")).sendKeys(Keys.TAB);
            driver.findElement(By.id("publisher")).sendKeys("jogoteste2");
            driver.findElement(By.id("price")).sendKeys("1");
            driver.findElement(By.id("description")).sendKeys("jogo teste2");
            {
                WebElement dropdown = driver.findElement(By.name("gamePlatforms"));
                dropdown.findElement(By.xpath("//option[. = 'XBOX One']")).click();
            }
            {
                WebElement dropdown = driver.findElement(By.name("gameGenres"));
                dropdown.findElement(By.xpath("//option[. = 'FPS']")).click();
            }
            driver.findElement(By.cssSelector(".btn-success")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span")).getText(), ("Jogo cadastrado com sucesso."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC04FluxoPrincipal() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/games/");
            driver.findElement(By.cssSelector(".odd .btn-primary > .fa-solid")).click();
            driver.findElement(By.id("publisher")).click();
            driver.findElement(By.id("publisher")).sendKeys("jogoteste2editado");
            driver.findElement(By.id("price")).click();
            driver.findElement(By.id("price")).sendKeys("50");
            driver.findElement(By.cssSelector(".col-12 > .form-group")).click();
            driver.findElement(By.cssSelector(".btn-success")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span")).getText(), ("Jogo atualizado com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }

    public void testeUC04FluxoAlternativo() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/games/");
            driver.findElement(By.cssSelector(".odd .btn-danger > .fa-solid")).click();
           Assert.assertEquals(driver.findElement(By.cssSelector(".ajs-content")).getText(), ("Deseja realmente deletar este jogo?"));
            driver.findElement(By.cssSelector(".ajs-ok")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector("span")).getText(), ("Jogo excluído com sucesso!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public void testeUC04FluxoExcecao() {
        System.setProperty("webdriver.chrome.driver", webdriver);
        driver = new ChromeDriver();
        try {
            Thread.sleep(2000);
            driver.get("http://localhost:8080/admin/games/");
            driver.findElement(By.cssSelector(".fa-trash-can")).click();
            Assert.assertEquals(driver.findElement(By.cssSelector(".ajs-content")).getText(), ("Deseja realmente deletar este jogo?"));
            driver.findElement(By.cssSelector(".ajs-cancel")).click();
            {
                List<WebElement> elements = driver.findElements(By.cssSelector(".ajs-message"));
                assert(elements.size() > 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }


}
