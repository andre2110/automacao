
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class CadastroUsuario {
    private ChromeDriver driver;
    private WebDriverWait wait;
    private Random random;
    private String name = "";
    private String email = "";

    @Before
    public void acessaUrl(){
        System.getProperty("webdriver.chrome.driver", "chromedriver.exe");
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, 90);
        this.driver.get("https://srbarriga.herokuapp.com/login");
        this.driver.manage().window().maximize();
    }

    @Test
    public void cadastrarUsuario(){
        this.driver.findElement(By.xpath("//a[@href='/cadastro']")).click();
        this.wait.until(ExpectedConditions.elementToBeClickable(this.driver.findElement(By.id("nome"))));

        this.random = new Random();
        int number = random.nextInt()*100;
        setName("André Gonçalves Santos"+number);
        setEmail("drusta21@gmail.com"+number);

        this.driver.findElement(By.id("nome")).sendKeys(getName());
        this.driver.findElement(By.id("email")).sendKeys(getEmail());
        this.driver.findElement(By.id("senha")).sendKeys("bolinha0809");
        this.driver.findElement(By.xpath("//input[@type='submit']")).click();
        //this.wait.until(ExpectedConditions.visibilityOf(this.driver.findElement(By.xpath("//div[@class='alert alert-success']"))));
        this.driver.findElement(By.xpath("//div[@class='alert alert-success']"));
        String valorTela = this.driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

        Assert.assertEquals("Usuário inserido com sucesso", valorTela);
    }

        @Test
        public void validarLogin(){
                this.driver.findElement(By.id("email")).sendKeys("containvalida@containvalida.com");
                this.driver.findElement(By.id("senha")).sendKeys("naoexiste");
                this.driver.findElement(By.xpath("//button[@type='submit']")).click();
                this.driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
                String valorTela = this.driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();

                Assert.assertEquals("Problemas com o login do usuário", valorTela);

        }


        @Test
        public void criarConta(){
            this.driver.findElement(By.id("email")).sendKeys("drusta21@gmail.com");
            this.driver.findElement(By.id("senha")).sendKeys("bolinha0809");
            this.driver.findElement(By.xpath("//button[@type='submit']")).click();

            this.driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
            this.driver.findElement(By.xpath("//a[@href='/addConta']")).click();
            this.driver.findElement(By.id("nome")).sendKeys("Conta André");
            this.driver.findElement(By.xpath("//button[@type='submit']")).click();
        }

        @Test
        public void criarMovimentacao(){
            this.driver.findElement(By.id("email")).sendKeys("drusta21@gmail.com");
            this.driver.findElement(By.id("senha")).sendKeys("bolinha0809");
            this.driver.findElement(By.xpath("//button[@type='submit']")).click();

            this.driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
            this.driver.findElement(By.xpath("//a[@href='/movimentacao']")).click();
            this.driver.findElement(By.xpath("//option[@value='REC']")).click();
            this.driver.findElement(By.id("data_transacao")).sendKeys("22/03/2019");
            this.driver.findElement(By.id("data_pagamento")).sendKeys("22/03/2020");
            this.driver.findElement(By.id("descricao")).sendKeys("Contas");
            this.driver.findElement(By.id("interessado")).sendKeys("Cliente");
            this.driver.findElement(By.id("valor")).sendKeys("2000");
            this.driver.findElement(By.id("status_pago")).click();
            this.driver.findElement(By.xpath("//button[@type='submit']")).click();
            String valorTela = this.driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();

            Assert.assertEquals("Movimentação adicionada com sucesso!", valorTela);

        }
        @After
            public void fecherJanela(){
            this.driver.quit();
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
