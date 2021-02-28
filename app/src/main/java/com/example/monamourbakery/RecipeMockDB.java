package com.example.monamourbakery;


import java.util.ArrayList;
import java.util.HashMap;

public class RecipeMockDB {

    public static ArrayList<Recipe> generateRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        Recipe r3 = new Recipe();
        Recipe r4 = new Recipe();
        Recipe r5 = new Recipe();
        Recipe r6 = new Recipe();
        Recipe r7 = new Recipe();

        r1.setPastry_name("עוגת גזר");
        r1.setShort_description("עוגת גזר עם קינמון ואגוזים רכה במיוחד");
        r1.setKosher(Kosher.FUR);
        r1.setPastry_img("data:image/jpeg;base64,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");
        r1.setDescription("\n" +
                "עוגת גזר: מחממים תנור ל-160 מעלות.\n" +
                "מגרדים גזר על פומפייה. שוברים גס אגוזי מלך.\n" +
                "בקערה גדולה טורפים ביצים.\n" +
                "מוסיפים שני סוגי סוכר ושמן ומערבבים היטב. מוסיפים גזר וקוקוס ומערבבים.\n" +
                "מנפים יחד קמח, אבקת סודה, מלח וקינמון ומוסיפים לתערובת. טורפים עד שהתערובת אחידה. מערבבים פנימה אגוזים וצימוקים.\n" +
                "אפייה: מוזגים את התערובת לתבניות ואופים במשך שעה או עד שקיסם יוצא כמעט יבש לגמרי.");
        HashMap<String, String> h1 = new HashMap();
        h1.put("3", "ביצים L");
        h1.put("4", "ביצים L");
        h1.put("2", "ביצים L");
        h1.put("2", "ביצים L");
        r1.setIngredients(h1);
        recipes.add(r1);

        r2.setPastry_name("אלפחורס");
        r2.setShort_description("עוגיות ארגנטינאיות ממולאות בריבת חלב ומצופות קוקוס");
        r2.setKosher(Kosher.MILK);
        r2.setPastry_img("https://img.mako.co.il/2014/08/03/alfajores_chen_c.jpg");
        r2.setDescription("להכנת הבצק: בקערת המיקסר עם וו גיטרה מערבלים את החמאה ואבקת הסוכר עד לקבלת תערובת חלקה ואחידה. מוסיפים את החלמונים ומערבלים עוד כמה דקות. לקערה אחרת מנפים את הקמח, הקורנפלור ואבקת האפייה, ומערבבים היטב. מוסיפים את תערובת הקמח לקערת המיקסר ומערבלים במהירות נמוכה רק עד להיווצרות בצק אחיד. מתקבל בצק חלק ורך במיוחד.\n" +
                "\n" +
                "\n" +
                "2\n" +
                "מחלקים את הבצק ל-2 חלקים ומרדדים כל חלק על גבי נייר אפייה לעלה דק בעובי של פחות מחצי ס\"מ. במידת הצורך מקמחים את הבצק במעט קמח.\n" +
                "\n" +
                "3\n" +
                "קורצים מהבצק עיגולים בקוטר 4 ס\"מ ובעזרת מרית מדורגת מעבירים אותם לתבנית אפייה מרופדת בנייר אפייה במרווחים קלים זה מזה. הבצק רך מאוד ולכן חשוב להיעזר במרית או בסכין רחבה להעברת עיגולי הבצק.\n" +
                "\n" +
                "4\n" +
                "מעבירים את התבנית למקרר לחצי שעה. בזמן זה מחממים את התנור לחום של 175 מעלות. אופים את העוגיות 9-10 דקות ועד להזהבה קלה מאוד. מוציאים ומצננים.\n" +
                "\n" +
                "5\n" +
                "מרכיבים: מעבירים את ריבת החלב לשק זילוף ופותחים בו פתח קטן, מעבירים את הקוקוס הטחון לקערית קטנה. מסדרים מחצית מהעוגיות כך שצידן השטוח כלפי מעלה. מזלפים במרכזן 1/2 כפית ריבת חלב. סוגרים עם עוגייה נוספת ולוחצים קלות. בשולי העוגייה, בקו התפר שלה, מזלפים פס דקיק של ריבת חלב. מגלגלים כל עוגייה בקוקוס הקלוי.");
        HashMap<String, String> h2 = new HashMap();
        h2.put("180", "גרם חמאה");
        h2.put("1", "כוס סוכר");
        h2.put("1.5", "כוסות קמח");
        h2.put("1.5", "כוסות קורנפלור");
        h2.put("1", "כפית אבקת אפיה");
        r2.setIngredients(h2);
        recipes.add(r2);

        r3.setPastry_name("מגולגלות נוטלה");
        r3.setShort_description("עוגיות מגולגות במילוי נוטלה");
        r3.setKosher(Kosher.MILK);
        r3.setPastry_img("https://adikosh.co.il/wp-content/uploads/2020/10/IMG-8611.jpg");
        r3.setDescription("בקערת מיקסר מניחים קמח, אבקת סוכר, קורט מלח ואבקת אפייה, מחברים וו גיטרה ומתחילים לערבב.\n" +
                "\n" +
                "מוסיפים תוך כדי ערבוב גביע שמנת חמוצה וחמאה קרה. ממשיכים לערבב עד בצק חלק ואחיד.\n" +
                "\n" +
                "עוטפים את הבצק בניילון נצמד ומניחים במקרר לשעה, לקירור קל.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מחממים את התנור ל-170 מעלות.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מוציאים את הבצק אחרי שהוא נח שעה במקרר, חוצים את הבצק ל-2.\n" +
                "\n" +
                "לוקחים חלק אחד של הבצק, מניחים מעל נייר אפייה מקומח ומרדדים למלבן דק דק ממש, אך תיזהרו שלא יקרע.\n" +
                "\n" +
                "מורחים מעל המלבן את ממרח הנוטלה ( כדאי להיעזר בפלטה או מרית) בשכבה אחידה ומגלגלים לרולדה.\n" +
                "\n" +
                "בעזרת סכין מקומחת עושים סימונים או חריצים ברולדה ( לא פורסים עד הסוף) לעוגיות , מניחים את הרולדה עם הנייר אפייה על תבנית אפייה\n" +
                "\n" +
                " \n" +
                "\n" +
                "כך חוזרים גם עם הרולדה השנייה. אופים את העוגיות כ-20 עד 30 דקות, הרולדה לא אמורה לקבל צבע זהוב, היא אמורה להישאר רכה ולא קשה.\n" +
                "\n" +
                " \n" +
                "\n" +
                "אחרי אפייה מוציאים את העוגיות מהתנור, פורסים לעוגיות ,מפדרים באבקת סוכר ושומרים בקופסא אטומה כשבוע  ימים.");
        HashMap<String, String> h3 = new HashMap();
        h3.put("1", "גביע שמנת חמוצה");
        h3.put("100", "גרם חמאה קרה");
        h3.put("2.5", "כוסות קמח");
        h3.put("50", "גרם אבקת סוכר");
        h3.put("0.5", "כפית אבקת אפיה");
        h3.put("0.3", "כפית מלח");
        r3.setIngredients(h3);
        recipes.add(r3);

        r6.setPastry_name("עוגת שוקולד");
        r6.setShort_description("עוגת שוקולד רכה ועסיסית מפוצה בסוכריות");
        r6.setKosher(Kosher.MILK);
        r6.setPastry_img("https://adikosh.co.il/wp-content/uploads/2020/06/IMG-2594.jpg");
        r6.setDescription("בקערת מיקסר מניחים קמח, אבקת סוכר, קורט מלח ואבקת אפייה, מחברים וו גיטרה ומתחילים לערבב.\n" +
                "\n" +
                "מוסיפים תוך כדי ערבוב גביע שמנת חמוצה וחמאה קרה. ממשיכים לערבב עד בצק חלק ואחיד.\n" +
                "\n" +
                "עוטפים את הבצק בניילון נצמד ומניחים במקרר לשעה, לקירור קל.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מחממים את התנור ל-170 מעלות.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מוציאים את הבצק אחרי שהוא נח שעה במקרר, חוצים את הבצק ל-2.\n" +
                "\n" +
                "לוקחים חלק אחד של הבצק, מניחים מעל נייר אפייה מקומח ומרדדים למלבן דק דק ממש, אך תיזהרו שלא יקרע.\n" +
                "\n" +
                "מורחים מעל המלבן את ממרח הנוטלה ( כדאי להיעזר בפלטה או מרית) בשכבה אחידה ומגלגלים לרולדה.\n" +
                "\n" +
                "בעזרת סכין מקומחת עושים סימונים או חריצים ברולדה ( לא פורסים עד הסוף) לעוגיות , מניחים את הרולדה עם הנייר אפייה על תבנית אפייה\n" +
                "\n" +
                " \n" +
                "\n" +
                "כך חוזרים גם עם הרולדה השנייה. אופים את העוגיות כ-20 עד 30 דקות, הרולדה לא אמורה לקבל צבע זהוב, היא אמורה להישאר רכה ולא קשה.\n" +
                "\n" +
                " \n" +
                "\n" +
                "אחרי אפייה מוציאים את העוגיות מהתנור, פורסים לעוגיות ,מפדרים באבקת סוכר ושומרים בקופסא אטומה כשבוע  ימים.");
        HashMap<String, String> h6 = new HashMap();
        h6.put("2", "ביצים");
        h6.put("1", "כוס סוכר");
        h6.put("0.5", "כוסות סוכר חום");
        h6.put("3/4", "כוס שמן");
        h6.put("1", "כוס מים");
        h6.put("1", "כוס חלב");
        h6.put("2", "כפיות סודה לשתיה");
        h6.put("1", "כפית אבקת אפיה");
        h6.put("1", "כפית תמצית וניל");
        r6.setIngredients(h6);
        recipes.add(r6);


        r4.setPastry_name("עוגת סולת");
        r4.setShort_description("עוגת סולת וקוקוס משגעת");
        r4.setKosher(Kosher.MILK);
        r4.setPastry_img("https://adikosh.co.il/wp-content/uploads/2020/09/IMG-8121-1.jpg");
        r4.setDescription("בקערת מיקסר מניחים קמח, אבקת סוכר, קורט מלח ואבקת אפייה, מחברים וו גיטרה ומתחילים לערבב.\n" +
                "\n" +
                "מוסיפים תוך כדי ערבוב גביע שמנת חמוצה וחמאה קרה. ממשיכים לערבב עד בצק חלק ואחיד.\n" +
                "\n" +
                "עוטפים את הבצק בניילון נצמד ומניחים במקרר לשעה, לקירור קל.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מחממים את התנור ל-170 מעלות.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מוציאים את הבצק אחרי שהוא נח שעה במקרר, חוצים את הבצק ל-2.\n" +
                "\n" +
                "לוקחים חלק אחד של הבצק, מניחים מעל נייר אפייה מקומח ומרדדים למלבן דק דק ממש, אך תיזהרו שלא יקרע.\n" +
                "\n" +
                "מורחים מעל המלבן את ממרח הנוטלה ( כדאי להיעזר בפלטה או מרית) בשכבה אחידה ומגלגלים לרולדה.\n" +
                "\n" +
                "בעזרת סכין מקומחת עושים סימונים או חריצים ברולדה ( לא פורסים עד הסוף) לעוגיות , מניחים את הרולדה עם הנייר אפייה על תבנית אפייה\n" +
                "\n" +
                " \n" +
                "\n" +
                "כך חוזרים גם עם הרולדה השנייה. אופים את העוגיות כ-20 עד 30 דקות, הרולדה לא אמורה לקבל צבע זהוב, היא אמורה להישאר רכה ולא קשה.\n" +
                "\n" +
                " \n" +
                "\n" +
                "אחרי אפייה מוציאים את העוגיות מהתנור, פורסים לעוגיות ,מפדרים באבקת סוכר ושומרים בקופסא אטומה כשבוע  ימים.");
        HashMap<String, String> h4 = new HashMap();
        h4.put("1", "גביע שמנת חמוצה");
        h4.put("100", "גרם חמאה קרה");
        h4.put("2.5", "כוסות קמח");
        h4.put("50", "גרם אבקת סוכר");
        h4.put("0.5", "כפית אבקת אפיה");
        h4.put("0.3", "כפית מלח");
        r4.setIngredients(h4);
        recipes.add(r4);

        r5.setPastry_name("עוגיות חמאה");
        r5.setShort_description("עוגיות חמאה קלאסיות נמסות");
        r5.setKosher(Kosher.MILK);
        r5.setPastry_img("data:image/jpeg;base64,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");
        r5.setDescription("להכנת הבצק: בקערת המיקסר עם וו גיטרה מערבלים את החמאה ואבקת הסוכר עד לקבלת תערובת חלקה ואחידה. מוסיפים את החלמונים ומערבלים עוד כמה דקות. לקערה אחרת מנפים את הקמח, הקורנפלור ואבקת האפייה, ומערבבים היטב. מוסיפים את תערובת הקמח לקערת המיקסר ומערבלים במהירות נמוכה רק עד להיווצרות בצק אחיד. מתקבל בצק חלק ורך במיוחד.\n" +
                "\n" +
                "\n" +
                "2\n" +
                "מחלקים את הבצק ל-2 חלקים ומרדדים כל חלק על גבי נייר אפייה לעלה דק בעובי של פחות מחצי ס\"מ. במידת הצורך מקמחים את הבצק במעט קמח.\n" +
                "\n" +
                "3\n" +
                "קורצים מהבצק עיגולים בקוטר 4 ס\"מ ובעזרת מרית מדורגת מעבירים אותם לתבנית אפייה מרופדת בנייר אפייה במרווחים קלים זה מזה. הבצק רך מאוד ולכן חשוב להיעזר במרית או בסכין רחבה להעברת עיגולי הבצק.\n" +
                "\n" +
                "4\n" +
                "מעבירים את התבנית למקרר לחצי שעה. בזמן זה מחממים את התנור לחום של 175 מעלות. אופים את העוגיות 9-10 דקות ועד להזהבה קלה מאוד. מוציאים ומצננים.\n" +
                "\n" +
                "5\n" +
                "מרכיבים: מעבירים את ריבת החלב לשק זילוף ופותחים בו פתח קטן, מעבירים את הקוקוס הטחון לקערית קטנה. מסדרים מחצית מהעוגיות כך שצידן השטוח כלפי מעלה. מזלפים במרכזן 1/2 כפית ריבת חלב. סוגרים עם עוגייה נוספת ולוחצים קלות. בשולי העוגייה, בקו התפר שלה, מזלפים פס דקיק של ריבת חלב. מגלגלים כל עוגייה בקוקוס הקלוי.");
        HashMap<String, String> h5 = new HashMap();
        h5.put("180", "גרם חמאה");
        h5.put("1", "כוס סוכר");
        h5.put("1.5", "כוסות קמח");
        h5.put("1.5", "כוסות קורנפלור");
        h5.put("1", "כפית אבקת אפיה");
        r5.setIngredients(h5);
        recipes.add(r5);


        r7.setPastry_name("עוגת בננות ונוטלה");
        r7.setShort_description("עוגת בננות ושוקולד עסיסית");
        r7.setKosher(Kosher.MILK);
        r7.setPastry_img("https://adikosh.co.il/wp-content/uploads/2020/06/IMG-0806.jpg");
        r7.setDescription("בקערת מיקסר מניחים קמח, אבקת סוכר, קורט מלח ואבקת אפייה, מחברים וו גיטרה ומתחילים לערבב.\n" +
                "\n" +
                "מוסיפים תוך כדי ערבוב גביע שמנת חמוצה וחמאה קרה. ממשיכים לערבב עד בצק חלק ואחיד.\n" +
                "\n" +
                "עוטפים את הבצק בניילון נצמד ומניחים במקרר לשעה, לקירור קל.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מחממים את התנור ל-170 מעלות.\n" +
                "\n" +
                " \n" +
                "\n" +
                "מוציאים את הבצק אחרי שהוא נח שעה במקרר, חוצים את הבצק ל-2.\n" +
                "\n" +
                "לוקחים חלק אחד של הבצק, מניחים מעל נייר אפייה מקומח ומרדדים למלבן דק דק ממש, אך תיזהרו שלא יקרע.\n" +
                "\n" +
                "מורחים מעל המלבן את ממרח הנוטלה ( כדאי להיעזר בפלטה או מרית) בשכבה אחידה ומגלגלים לרולדה.\n" +
                "\n" +
                "בעזרת סכין מקומחת עושים סימונים או חריצים ברולדה ( לא פורסים עד הסוף) לעוגיות , מניחים את הרולדה עם הנייר אפייה על תבנית אפייה\n" +
                "\n" +
                " \n" +
                "\n" +
                "כך חוזרים גם עם הרולדה השנייה. אופים את העוגיות כ-20 עד 30 דקות, הרולדה לא אמורה לקבל צבע זהוב, היא אמורה להישאר רכה ולא קשה.\n" +
                "\n" +
                " \n" +
                "\n" +
                "אחרי אפייה מוציאים את העוגיות מהתנור, פורסים לעוגיות ,מפדרים באבקת סוכר ושומרים בקופסא אטומה כשבוע  ימים.");
        HashMap<String, String> h7 = new HashMap();
        h7.put("2", "ביצים");
        h7.put("1", "כוס סוכר");
        h7.put("0.5", "כוסות סוכר חום");
        h7.put("3/4", "כוס שמן");
        h7.put("1", "כוס מים");
        h7.put("1", "כוס חלב");
        h7.put("2", "כפיות סודה לשתיה");
        h7.put("1", "כפית אבקת אפיה");
        h7.put("1", "כפית תמצית וניל");
        r7.setIngredients(h7);
        recipes.add(r7);



        return recipes;
    }


}
