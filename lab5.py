from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from webdriver_manager.chrome import ChromeDriverManager
import unittest
import time


class TestLab5Variant8(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))
        self.driver.maximize_window()
        self.wait = WebDriverWait(self.driver, 10)

    def _click_shuffle(self):
        driver = self.driver

        # încercăm mai întâi după href / aria-label
        try:
            shuffle_button = self.wait.until(
                EC.element_to_be_clickable(
                    (By.CSS_SELECTOR, "a[href*='shuffle'], a[aria-label='Shuffle']")
                )
            )
        except Exception:
            # fallback: căutăm orice <a> care conține textul "Shuffle"
            shuffle_button = self.wait.until(
                EC.element_to_be_clickable(
                    (By.XPATH, "//a[contains(., 'Shuffle')]")
                )
            )

        shuffle_button.click()

    def _click_trending(self):
        driver = self.driver

        try:
            trending_button = self.wait.until(
                EC.element_to_be_clickable(
                    (By.CSS_SELECTOR, "a[href*='trending']")
                )
            )
        except Exception:
            # fallback după text
            trending_button = self.wait.until(
                EC.element_to_be_clickable(
                    (By.XPATH, "//a[contains(., 'Trending')]")
                )
            )

        trending_button.click()

    def test_shuffle_to_trending(self):
        driver = self.driver

        # 1. Deschidem 9GAG
        driver.get("https://9gag.com")
        self.wait.until(EC.title_contains("9GAG"))

        # 2. Click pe "Shuffle"
        self._click_shuffle()
        time.sleep(2)  # un mic delay ca să se încarce shuffle

        # 3. Click pe "Trending"
        self._click_trending()

        # 4. Verificăm că suntem pe Trending (URL conține 'trending')
        self.wait.until(EC.url_contains("trending"))
        current_url = driver.current_url
        print("Current URL:", current_url)

        self.assertIn(
            "trending",
            current_url,
            "Pagina Trending nu s-a deschis corect!"
        )

    def tearDown(self):
        time.sleep(5)  # să vezi vizual rezultatul
        self.driver.quit()


if __name__ == "__main__":
    unittest.main()
