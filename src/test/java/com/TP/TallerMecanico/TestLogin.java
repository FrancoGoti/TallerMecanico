package com.TP.TallerMecanico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import java.time.Duration;

public class TestLogin {

    @Test
    public void testLoginIncorrectoYCorrecto() throws InterruptedException {
        // Configuración del WebDriver para Chrome
        System.setProperty("webdriver.edge.driver", "C:\\Users\\franc\\Desktop\\Universidad\\Testing de Software\\Driver_Notes\\msedgedriver.exe"); 
        WebDriver driver = new EdgeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // LOGUEO
            driver.get("http://localhost:8080/login");
            Thread.sleep(2000);  // Pausa de 2 segundos

            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.sendKeys("INCORRECTO");
            Thread.sleep(1000);  // Pausa de 1 segundo

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("INCORRECTO");
            Thread.sleep(1000);  // Pausa de 1 segundo

            // Hacer clic en el botón de login
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            loginButton.click();
            Thread.sleep(2000);  // Pausa de 2 segundos

            if (driver.getCurrentUrl().contains("/login?error")) {
                System.out.println("Prueba 1: Logueo con credenciales incorrectas - falló como se esperaba.");
            }
           

            // INTENTO 2: Logueo exitoso con credenciales correctas
            WebElement usernameField2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField2.sendKeys("franco");
            Thread.sleep(1000); 
            WebElement passwordField2 = driver.findElement(By.id("password"));
            passwordField2.sendKeys("gotas");
            Thread.sleep(1000); 

            WebElement loginButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            loginButton2.click();
            

            // Esperar que se redirija a la página correcta después de un login exitoso
            
            Thread.sleep(2000);  // Pausa de 2 segundos
            if (driver.getCurrentUrl().contains("http://localhost:8080/")) {
            System.out.println("Prueba 2: Logueo con credenciales correctas - exitoso.");
            }

        } finally {
            // Cerrar el navegador
            Thread.sleep(2000);
            driver.quit();
        }
    }
}