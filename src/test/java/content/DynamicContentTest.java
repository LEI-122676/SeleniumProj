package content;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DynamicContentTest {
    private WebDriver driver;
    private DynamicContentPage dynamicContentPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/dynamic_content");

        dynamicContentPage = new DynamicContentPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

//    @Test
//    @DisplayName("Verifica que existem 3 linhas de conteúdo")
//    public void testContentRowsExist() {
//        assertEquals(3, dynamicContentPage.getNumberOfRows(),
//                "Devem existir 3 linhas de conteúdo");
//    }

    @Test
    @DisplayName("Verifica que todas as imagens são exibidas")
    public void testAllImagesDisplayed() {
        for (int i = 0; i < dynamicContentPage.getContentImages().size(); i++) {
            assertTrue(dynamicContentPage.isImageDisplayed(i),
                    "Imagem " + i + " deve estar visível");
        }
    }

    @Test
    @DisplayName("Verifica que todos os textos são exibidos")
    public void testAllTextsDisplayed() {
        for (int i = 0; i < dynamicContentPage.getContentTexts().size(); i++) {
            assertTrue(dynamicContentPage.isTextDisplayed(i),
                    "Texto " + i + " deve estar visível");
            assertNotNull(dynamicContentPage.getTextContent(i),
                    "Texto " + i + " não deve ser nulo");
            assertFalse(dynamicContentPage.getTextContent(i).isEmpty(),
                    "Texto " + i + " não deve estar vazio");
        }
    }

    @Test
    @DisplayName("Verifica que o conteúdo muda após refresh")
    public void testContentChangesAfterRefresh() {
        // Guarda os URLs das imagens antes do refresh
        List<String> imagesBefore = new ArrayList<>();
        for (int i = 0; i < dynamicContentPage.getContentImages().size(); i++) {
            imagesBefore.add(dynamicContentPage.getImageSrc(i));
        }

        // Guarda os textos antes do refresh
        List<String> textsBefore = new ArrayList<>();
        for (int i = 0; i < dynamicContentPage.getContentTexts().size(); i++) {
            textsBefore.add(dynamicContentPage.getTextContent(i));
        }

        // Faz refresh através do link
        dynamicContentPage.clickRefreshLink();

        // Aguarda um pouco para garantir que a página recarregou
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Reinicializa a página após o refresh
        dynamicContentPage = new DynamicContentPage(driver);

        // Verifica se pelo menos uma imagem mudou
        boolean atLeastOneImageChanged = false;
        for (int i = 0; i < dynamicContentPage.getContentImages().size(); i++) {
            String currentSrc = dynamicContentPage.getImageSrc(i);
            if (!imagesBefore.get(i).equals(currentSrc)) {
                atLeastOneImageChanged = true;
                break;
            }
        }

        // Verifica se pelo menos um texto mudou
        boolean atLeastOneTextChanged = false;
        for (int i = 0; i < dynamicContentPage.getContentTexts().size(); i++) {
            String currentText = dynamicContentPage.getTextContent(i);
            if (!textsBefore.get(i).equals(currentText)) {
                atLeastOneTextChanged = true;
                break;
            }
        }

        assertTrue(atLeastOneImageChanged || atLeastOneTextChanged,
                "Pelo menos uma imagem ou texto deve ter mudado após o refresh");
    }

    @Test
    @DisplayName("Verifica navegação para conteúdo estático")
    public void testNavigateToStaticContent() {
        dynamicContentPage.clickStaticContentLink();

        assertTrue(driver.getCurrentUrl().contains("with_content=static"),
                "URL deve conter o parâmetro de conteúdo estático");
    }
}