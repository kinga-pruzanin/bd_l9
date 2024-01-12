import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.CategoriesModel;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

//      a) Wyświetl procent produktów, które dostarczają więcej niż 50% sumy żelaza i wapnia.
//         Zwróć wynik do dwóch miejsc po przecinku.
        var stringA = "SELECT ROUND(100.0 * COUNT(p) / ((SELECT COUNT(p2) FROM ProductsModel p2)), 2) FROM ProductsModel p WHERE (p.iron + p.calcium) > 50";
        Query queryA = em.createQuery(stringA);
        Float resultA = (Float) queryA.getSingleResult();
        System.out.println("a) " + resultA + "%");

//      b) Wyświetl średnią wartość kaloryczną produktów z bekonem w nazwie.
        var stringB = "SELECT AVG(p.calories) FROM ProductsModel p WHERE p.itemName LIKE '%bacon%'";
        Query queryB = em.createQuery(stringB);
        Double resultB = (Double) queryB.getSingleResult();
        System.out.println("b) " + resultB);

//      c) Dla każdej z kategorii wyświetl produkt o największej wartości cholesterolu.
        var stringC = "SELECT p.category, MAX(p.cholesterole) FROM ProductsModel p GROUP BY p.category";
        Query queryC = em.createQuery(stringC);
        List<Object[]> resultC = queryC.getResultList();
        System.out.println("c)");
        for (Object[] r : resultC) {
            CategoriesModel tmp = (CategoriesModel) r[0];
            String cat = tmp.getCatName();
            int chol = (int) r[1];

            System.out.println(cat + ": " + chol);
        }

//      d) Wyświetl liczbę kaw (Mocha lub Coffee w nazwie) niezawierających błonnika.
        var stringD = "SELECT COUNT(*) FROM ProductsModel p WHERE p.itemName LIKE '%Mocha%' OR p.itemName LIKE '%Coffee%' AND p.fiber = 0";
        Query queryD = em.createQuery(stringD);
        Long resultD = (Long) queryD.getSingleResult();
        System.out.println("d) " + resultD);

//      e) Wyświetl kaloryczność wszystkich McMuffinów. Wyniki wyświetl w kilodżulach (jedna
//         kaloria to 4184 dżule) rosnąco.
        var stringE = "SELECT p.itemName, p.calories FROM ProductsModel p WHERE itemName LIKE '%McMuffin%' ORDER BY p.calories ASC";
        Query queryE = em.createQuery(stringE);
        List<Object[]> resultE = queryE.getResultList();
        System.out.println("e)");
        for (Object[] r : resultE) {
            String item = (String) r[0];
            Integer kcal = (Integer) r[1];
            int kj = kcal * 4184 / 1000;

            System.out.println(item + ": " + kj);
        }


//      f) Wyświetl liczbę różnych wartości węglowodanów.
        var stringF = "SELECT COUNT(DISTINCT p.carbs) FROM ProductsModel p";
        Query queryF = em.createQuery(stringF);
        Long resultF = (Long) queryF.getSingleResult();
        System.out.println("f) " + resultF);
    }
}
