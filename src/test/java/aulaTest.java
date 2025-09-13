import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class aulaTest {

    @Test
    public void cenarioSucesso_LoginValido(){
        // Cenário de Sucesso: Login com credenciais válidas

        WebDriver driver = new ChromeDriver();

        //Dado: que esteja na página saucedemo.com
        driver.get("https://www.saucedemo.com/");
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Swag Labs", driver.getTitle());

        //Quando: Inserido dados de usuário e senha válidos
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Então: deverá ser redirecionado para a página de inventário
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
        assertTrue(driver.findElement(By.id("shopping_cart_container")).isDisplayed());
        assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());

        driver.quit();
    }

    @Test(expected = AssertionError.class)
    public void cenarioExcecao1_UsuarioInvalido(){
        // Cenário de Exceção 1: Login com usuário inválido

        WebDriver driver = new ChromeDriver();

        //Dado: que esteja na página saucedemo.com
        driver.get("https://www.saucedemo.com/");
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        //Quando: Inserido usuário inválido e senha válida
        driver.findElement(By.id("user-name")).sendKeys("usuario_inexistente");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //Então: deve exibir mensagem de erro e permanecer na página de login
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(errorMessage.isDisplayed());
        assertTrue(errorMessage.getText().contains("Username and password do not match"));
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        // Força uma falha para demonstrar o cenário de exceção
        fail("Login deveria ter falhado com usuário inválido");

        driver.quit();
    }

    @Test(expected = AssertionError.class)
    public void cenarioExcecao2_SenhaIncorreta(){
        // Cenário de Exceção 2: Login com senha incorreta

        WebDriver driver = new ChromeDriver();

        //Dado: que esteja na página saucedemo.com
        driver.get("https://www.saucedemo.com/");
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        //Quando: Inserido usuário válido e senha incorreta
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("senha_incorreta");
        driver.findElement(By.id("login-button")).click();

        //Então: deve exibir mensagem de erro e permanecer na página de login
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(errorMessage.isDisplayed());
        assertTrue(errorMessage.getText().contains("Username and password do not match"));
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        // Força uma falha para demonstrar o cenário de exceção
        fail("Login deveria ter falhado com senha incorreta");

        driver.quit();
    }

    @Test(expected = AssertionError.class)
    public void cenarioExcecao3_CamposVazios(){
        // Cenário de Exceção 3: Login com campos vazios

        WebDriver driver = new ChromeDriver();

        //Dado: que esteja na página saucedemo.com
        driver.get("https://www.saucedemo.com/");
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        //Quando: Tentar fazer login sem preencher os campos
        driver.findElement(By.id("login-button")).click();

        //Então: deve exibir mensagem de erro solicitando preenchimento dos campos
        WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
        assertTrue(errorMessage.isDisplayed());
        assertTrue(errorMessage.getText().contains("Username is required"));
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        // Força uma falha para demonstrar o cenário de exceção
        fail("Login deveria ter falhado com campos vazios");

        driver.quit();
    }

}
