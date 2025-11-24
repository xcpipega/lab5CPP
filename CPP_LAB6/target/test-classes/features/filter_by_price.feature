Feature: Funcționalitatea sliderului "Filter by Price" pe pagina womens

  # Preconditions: deschidere browser, accesare womens.html

  Scenario: Actualizarea instantă a valorii la mișcarea sliderului "Filter by Price"
    Given utilizatorul este pe pagina womens
    And componenta "Filter by Price" este vizibilă
    When mut cursorul stang al sliderului spre stanga
    And mut cursorul drept al sliderului spre dreapta
    And mut de doua ori rapid sliderul
    Then valoarea intervalului de pret se actualizeaza dupa fiecare miscare
