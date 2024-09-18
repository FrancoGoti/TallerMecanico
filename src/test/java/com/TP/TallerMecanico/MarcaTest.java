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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ef200\\Escritorio\\Enzo\\UTN\\3er Año\\Testing de Software 2024\\chromedriver-win64\\chromedriver.exe"); 
        WebDriver driver = new ChromeDriver();

        try {
            // LOGUEO
            driver.get("http://localhost:8080/login"); // Se dirige a la página del login
            Thread.sleep(1500); // Pausa de 1,5 segundos
            
            WebElement usernameField = driver.findElement(By.name("username")); // Ubica el campo username
            usernameField.sendKeys("enzo"); // Ingresa el nombre de usuario
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement passwordField = driver.findElement(By.id("password")); // Ubica el campo password
            passwordField.sendKeys("enzo"); // Ingresa la contraseña
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']")); // Ubica el botón de ingreso
            loginButton.click(); // Click en el botón de ingreso
            Thread.sleep(1500); // Pausa de 1,5 segundos

            // PRUEBA MARCA
            driver.get("http://localhost:8080/marcas"); // Se dirige a la página de marcas
            Thread.sleep(1500); // Pausa de 2 segundos 

            WebElement botonAgregar = driver.findElement(By.linkText("Agregar")); // Ubica el botón de agregar marca
            botonAgregar.click(); // Click en el botón de agregar marca
            Thread.sleep(1500); // Pausa de 1,5 segundos


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // El WebDriver espera 10 segundos antes de continuar 

            WebElement marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre"))); // Ubica el campo nombre
            marcaInput.sendKeys("Ford"); // Ingresa el nombre de la marca
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto"))); // Ubica el campo impuesto
            impuestoInput.sendKeys("1"); // Ingresa el impuesto
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))); // Ubica el botón guardar
            botonGuardar.click(); // Click en el botón guardar
            Thread.sleep(1500); // Pausa de 2 segundos

            System.out.println("Se ejecutó prueba 1: ingresar una nueva marca");

            // PRUEBA 2: Agregar "Chevrolet"
            driver.get("http://localhost:8080/agregarMarca"); // Se dirige a la página agregarMarca
            Thread.sleep(1500); // Pausa de 1,5 segundos

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre"))); // Ubica el campo nombre
            marcaInput.sendKeys("Chevrolet"); // Ingresa el nombre de la marca
            Thread.sleep(1000); // Pausa de 1 segundo

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto"))); // Ubica el campo impuesto
            impuestoInput.sendKeys("1"); // Ingresa el impuesto
            Thread.sleep(1000); // Pausa de 1 segundo

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))); // Ubica el botón guardar
            botonGuardar.click(); // Click en el botón guardar
            Thread.sleep(1500); // Pausa de 1,5 segundos

            System.out.println("Se ejecutó prueba 2: ingresar una nueva marca diferente");

            // PRUEBA 3: Intentar agregar "Ford" nuevamente
            driver.get("http://localhost:8080/agregarMarca"); // Se dirige a la página agregarMarca
            Thread.sleep(1500); // Pausa de 1,5 segundos

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre"))); // Ubica el campo nombre
            marcaInput.sendKeys("Ford"); // Ingresa el nombre de la marca
            Thread.sleep(1000); // Pausa de 1 segundo

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto"))); // Ubica el campo impuesto
            impuestoInput.sendKeys("1"); // Ingresa el impuesto
            Thread.sleep(1000); // Pausa de 1 segundo

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))); // Ubica el botón guardar
            botonGuardar.click(); // Click en el botón guardar
            Thread.sleep(1500); // Pausa de 1,5 segundos

            System.out.println("Se ejecutó prueba 3: ingresar una marca ya existente");

        } finally {
           // Cerrar el navegador
           Thread.sleep(3000); // Pausa de 2 segundos
           driver.quit();
        }
    }
}
