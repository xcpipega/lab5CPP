Feature: Google Search

  Scenario: Utilizatorul poate căuta în limbi diferite
    Given I open Google Search page
    When I search for "buenos días"
    Then Search results should be displayed

  Scenario: Google Search este case sensitive
    Given I open Google Search page
    When I search for "Python"
    And I search for "python"
    Then Results for "python" should differ from "Python"

  Scenario: Serviciul Google Calculator este afișat pentru cuvântul "calculator"
    Given I open Google Search page
    When I search for "calculator"
    Then Google calculator widget should appear

  Scenario: Serviciul de conversie apare la căutarea "Google converter services"
    Given I open Google Search page
    When I search for "Google converter services"
    Then Converter service appears at the top
