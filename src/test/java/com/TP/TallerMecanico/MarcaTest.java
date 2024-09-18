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
    public void testAgregarMarca() {
        // Configuración del WebDriver para Chrome
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ef200\\Escritorio\\Enzo\\UTN\\3er Año\\Testing de Software 2024\\chromedriver-win64\\chromedriver.exe"); 
        WebDriver driver = new ChromeDriver();

        try {

/*             // LOGUEO
            // Accede a la página de login
            driver.get("http://localhost:8080/login");

            // Encuentra y completa el campo de usuario
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("admin");

            // Encuentra y completa el campo de contraseña
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("tu_contraseña");

            // Encuentra y hace clic en el botón de login
            WebElement loginButton = driver.findElement(By.id("loginButton"));
            loginButton.click();

            // Espera a que la página se cargue después del login
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.urlContains("pagina-destino"));
 */
            // PRUEBA MARCA
            // PASO 1: Navegar a la página para agregar Marca
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //
            driver.get("http://localhost:8080/agregarMarca"); 
            
            // PASO 2: Ingresar la marca "Ford" por primera vez
            WebElement marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford"); // Escribir "Ford" en el campo
            
            WebElement impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1"); // Escribir "1" en el campo
            
            WebElement botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))); // Selecciona el botón de envío
            botonGuardar.click(); // Enviar el formulario

            System.out.println("");
            System.out.println("");
            System.out.println("Se ejecutó prueba 1: ingresar una nueva marca");
            System.out.println("");
            System.out.println("");

            // PASO 4: Agregar "Chevrolet"
            driver.get("http://localhost:8080/agregarMarca"); // Navegar a la página del sistema
            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Chevrolet"); // Escribir "Chevrolet" en el campo
            
            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1"); // Escribir "1" en el campo
            
            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success"))); // Selecciona el botón de envío
            botonGuardar.click(); // Enviar el formulario

            System.out.println("");
            System.out.println("");
            System.out.println("Se ejecutó prueba 2: ingresar una nueva marca diferente");
            System.out.println("");
            System.out.println("");


            // PASO 5: Intentar agregar "Ford" nuevamente (caso duplicado)
            driver.get("http://localhost:8080/agregarMarca"); // Navegar a la página del sistema
            
            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            
            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();    
            
            System.out.println("");
            System.out.println("");
            System.out.println("Se ejecutó prueba 3: ingresar una marca ya existente");
            System.out.println("");
            System.out.println("");

        } finally {
            // Cerrar el navegador
            //driver.quit();
        }
    }
}
