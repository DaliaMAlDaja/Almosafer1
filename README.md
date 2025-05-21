# 🧪 Almosafer Automation Testing (Java + Selenium + TestNG)

This project automates testing for the **Almosafer** travel website using Java, Selenium WebDriver, and TestNG.  
The main goal is to validate key functionalities of the website such as default settings, UI components, and search results.

---

## ⚙️ Tools & Technologies Used

- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Maven** (for project management)
- **Eclipse IDE**
- **ChromeDriver** (for running tests on Google Chrome)

---

## 🧪 Test Cases Covered

This project includes several important automated test cases:

1. **Default language should be English**  
   Checks if the default HTML `lang` attribute is set to `en`.

2. **Default currency should be SAR**  
   Validates that the displayed currency is `SAR`.

3. **Phone number should be: +966554400000**  
   Ensures the customer support number is correct.

4. **Qitaf logo should be visible in the footer**  
   Checks that the Qitaf logo is displayed at the bottom of the page.

5. **Hotels tab should not be active by default**  
   Ensures the "Hotels" tab is inactive on initial page load.

6. **Departure and return dates should be set to tomorrow and the day after**  
   Verifies that the date pickers show tomorrow's and the day-after-tomorrow's dates by default.

7. **Randomly switch between Arabic and English**  
   Selects a random language (EN/AR) and verifies the switch.

8. **Search for a hotel in a random city**  
   Picks a city randomly (based on current language) and performs a search.

9. **Pick check-in and check-out dates manually**  
   Selects check-in date (11 days from today) and check-out date (15 days from today).

10. **Randomly choose number of rooms and guests**  
    Selects rooms and guests randomly, then initiates a search.

11. **Validate that hotel search results appear**  
    Ensures search results are shown, or the appropriate "no results" message appears depending on the language.

---
