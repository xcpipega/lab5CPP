Feature: Sortarea produselor după preț

  Scenario: Valorile se schimbă fluent, fără lag
    Given utilizatorul este pe pagina womens pentru sortare
    When utilizatorul deschide meniul Sort By
    And selecteaza optiunea "Price: High -> Low" din sortare
    Then produsele sunt sortate descrescator dupa pret

    When selecteaza optiunea "Price: Low -> High" din sortare
    Then produsele sunt sortate crescator dupa pret
    And ordinea ramane corecta dupa refresh
