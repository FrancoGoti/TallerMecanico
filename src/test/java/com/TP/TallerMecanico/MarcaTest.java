package com.TP.TallerMecanico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import java.time.Duration;


public class MarcaTest {

    @Test
    public void testAgregarMarca() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\niqui\\Desktop\\\\Testing 2024\\chromedriver-win64\\chromedriver.exe"); 
        WebDriver driver = new ChromeDriver();


        try {
            // LOGUEO
            driver.get("http://localhost:8080/login");
            Thread.sleep(1500); // Pausa de 2 segundos
            
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("nico");
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("nico");
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();
            Thread.sleep(1500); // Pausa de 2 segundos

            // PRUEBA MARCA
            driver.get("http://localhost:8080/marcas");
            Thread.sleep(1500);

            WebElement botonAgregar = driver.findElement(By.linkText("Agregar"));
            botonAgregar.click();
            Thread.sleep(1500);


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);

            WebElement impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            WebElement botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 1: ingresar una nueva marca");

            // PRUEBA 2: Agregar "Chevrolet"
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(1500);

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Chevrolet");
            Thread.sleep(1000);

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 2: ingresar una nueva marca diferente");

            // PRUEBA 3: Intentar agregar "Ford" nuevamente
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(1500);

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 3: ingresar una marca ya existente");

        } finally {
           // Cerrar el navegador
           Thread.sleep(3000);
           driver.quit();
        }
    }
}
