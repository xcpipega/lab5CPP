Feature: Google Search
  Testarea funcționalităților de bază ale motorului Google Search (google.co.in).

  Scenario: Pagina Google India se deschide după introducerea adresei
    Given I open Google India home page
    Then Google India home page is displayed

Scenario: Verificarea numărului de rezultate pe o singură pagină
  Given I open Google India home page
  When I search for "selenium testing"
  Then results are shown on first page

  Scenario: Apăsarea Search cu câmpul gol nu face nimic
    Given I open Google India home page
    When I press Search with empty query
    Then Google home page should remain opened

  Scenario: Linkul "Did you mean" este afișat pentru o căutare irelevantă
    Given I open Google India home page
    When I search for an irrelevant query
    Then "Did you mean" suggestion should be displayed
