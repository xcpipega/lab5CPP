Feature: Verificarea elementelor UI din zona "Sort By"
  Pentru a asigura o interfata curata,
  utilizatorul vrea sa verifice ca in zona "Sort By" nu apar simboluri grafice nedorite.

  Scenario: Detectarea artefactelor grafice in zona Sort By
    Given utilizatorul este pe pagina mens2
    When utilizatorul se uita la zona Sort By
    Then nu exista simboluri suplimentare langa Sort By

    When utilizatorul muta cursorul peste zona Sort By
    Then nu apare niciun highlight neobisnuit in zona Sort By

    When utilizatorul deschide meniul Sort By pentru verificarea UI
    Then componenta Filter by Price este vizibila

    When utilizatorul verifica zona Sort By pe pagina womens
    Then zona Sort By are acelasi UI fara simboluri suplimentare

    When utilizatorul reincarca pagina mens2
    Then simbolul nedorit nu mai este afisat langa Sort By
