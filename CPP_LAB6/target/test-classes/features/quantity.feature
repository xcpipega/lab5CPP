Feature: Dropdown-ul "Quantity" – valori disponibile

  # Verifică că dropdown-ul conține doar valorile permise 5, 6, 7, 10
  Scenario: Valorile 5-10 sunt vizibile în dropdown
    Given utilizatorul este pe pagina produsului single
    When accesati dropdown-ul Quantity
    Then valorile 5-10 sunt vizibile

  # Test negativ – încerc să aleg 1 Qty, care NU există în lista de opțiuni.
  # Aici ne așteptăm ca selecția să NU fie posibilă (testul va pica / aruncă excepție).
  Scenario: Nu se poate selecta o cantitate mai mică decât 5
    Given utilizatorul este pe pagina produsului single
    When utilizatorul selecteaza "1 Qty" din Quantity
    Then valoarea selectata este acceptata
