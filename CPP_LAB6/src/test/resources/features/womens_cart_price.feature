Feature: Verificarea egalității prețurilor între pagină și coșul de cumpărături
  Pentru a mă asigura că prețurile afișate sunt corecte,
  ca utilizator vreau ca prețul produsului din coș să fie egal
  cu prețul de pe pagina womens.

  Scenario: Prețul produsului din coș este egal cu cel de pe pagina womens
    Given utilizatorul este pe pagina womens cu un produs vizibil
    When utilizatorul noteaza pretul primului produs de pe pagina
    And utilizatorul adauga primul produs in cos
    And utilizatorul acceseaza cosul de cumparaturi
    Then pretul produsului din cos este egal cu pretul de pe pagina
