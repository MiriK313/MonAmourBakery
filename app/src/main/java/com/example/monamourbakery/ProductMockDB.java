package com.example.monamourbakery;

import android.util.Log;

import java.util.ArrayList;

public class ProductMockDB {

    public static ArrayList<Product> generateProducts() {
        ArrayList<Product> products = new ArrayList<>();

        for (int i=0;i<9;i++){
            Product product=new Product();
            product.setPastry_name("עוגת גזר");
            product.setShort_description("עוגת גזר עם קינמון ואגוזים רכה במיוחד");
            product.setKosher(Kosher.FUR);
            product.setPastry_img("data:image/jpeg;base64,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");
            product.setShort_description("\n" +
                    "עוגת גזר: מחממים תנור ל-160 מעלות.\n" +
                    "מגרדים גזר על פומפייה. שוברים גס אגוזי מלך.\n" +
                    "בקערה גדולה טורפים ביצים.\n" +
                    "מוסיפים שני סוגי סוכר ושמן ומערבבים היטב. מוסיפים גזר וקוקוס ומערבבים.\n" +
                    "מנפים יחד קמח, אבקת סודה, מלח וקינמון ומוסיפים לתערובת. טורפים עד שהתערובת אחידה. מערבבים פנימה אגוזים וצימוקים.\n" +
                    "אפייה: מוזגים את התערובת לתבניות ואופים במשך שעה או עד שקיסם יוצא כמעט יבש לגמרי.");
            product.setPrice(200);
            product.setType(Type.CAKE);
            product.setSize(Size.S);
            products.add(product);
            Log.d("pttt", products.get(i).toString());
        }

        return products;
    }
}