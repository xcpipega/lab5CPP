Feature: Funcționalitatea "Quick View" pentru produsele din catalog
  Scenario: Fiecare buton Quick View deschide produsul corect
    Given utilizatorul este pe pagina womens pentru Quick View
    When utilizatorul deschide Quick View pentru primul produs
    And utilizatorul deschide Quick View pentru al doilea produs
    Then URL-urile paginilor de produs sunt diferite
