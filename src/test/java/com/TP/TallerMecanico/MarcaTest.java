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
        // Configuración del WebDriver para Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\niqui\\Desktop\\Testing 2024\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // LOGUEO
            driver.get("http://localhost:8080/login");
            Thread.sleep(2000);  // Pausa de 2 segundos

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("nico");
            Thread.sleep(1000);  // Pausa de 1 segundo

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("nico");
            Thread.sleep(1000);  // Pausa de 1 segundo

            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            loginButton.click();
            Thread.sleep(2000);  // Pausa de 2 segundos

            // Navegar a la página de agregar marca
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(2000);  // Pausa de 2 segundos

            // PRUEBA MARCA
            // PASO 1: Navegar a la página para agregar Marca
            WebElement marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);  // Pausa de 1 segundo

            WebElement impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);  // Pausa de 1 segundo

            WebElement botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(2000);  // Pausa de 2 segundos

            System.out.println("Se ejecutó prueba 1: ingresar una nueva marca");

            // PASO 4: Agregar "Chevrolet"
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(2000);  // Pausa de 2 segundos

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Chevrolet");
            Thread.sleep(1000);  // Pausa de 1 segundo

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);  // Pausa de 1 segundo

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(2000);  // Pausa de 2 segundos

            System.out.println("Se ejecutó prueba 2: ingresar una nueva marca diferente");

            // PASO 5: Intentar agregar "Ford" nuevamente (caso duplicado)
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(2000);  // Pausa de 2 segundos

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);  // Pausa de 1 segundo

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);  // Pausa de 1 segundo

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(2000);  // Pausa de 2 segundos

            System.out.println("Se ejecutó prueba 3: ingresar una marca ya existente");

        } finally {
            driver.quit();
        }
    }
}
