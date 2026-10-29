DVG345 – laboration 2: Flyttning, händelser och verktyg

Detta är ett komplett, fristående Eclipse-projekt. Behåll ditt labb 1-projekt.
Java 17 eller senare och JUnit 5. Ingen installation av Python eller byggskript behövs.

1. Packa upp lab2-student.zip. I Eclipse: File > Import > General >
   Existing Projects into Workspace > Select root directory.
   Välj den uppackade mappen lab2-student och importera DVG345-Lab2-Student.
   Använd inte en redan importerad labb 1-mapp som mål.

2. Kör tests-junit/grading/StarterTest med Run As > JUnit Test.
   Kör app.DrawingApp som Java Application. Två linjer visas. Implementera först distance enligt nästa steg innan du
   provar markering eller flyttning. Cirkelverktyget är ännu ofärdigt.
   Övriga tester får vara röda: tre figurklasser är platshållare.

3. Första koduppgiften: implementera public double distance(Point2 other) i
   src/geometry/Point2.java. Metoden finns med ofärdig kropp. Returnera euklidiskt
   avstånd utan att ändra punkterna; samma punkt ger noll, null ger
   NullPointerException. Math.hypot rekommenderas för stora och små differenser.
   Ett avstånd större än double kan representera får vara positiv oändlighet.
   Kör Point2DistanceTest. HitTest och cirkelverktygets preview använder distance;
   markering och dessa gester fungerar först när metoden är klar.
   Prova nu att flytta de givna linjerna.

4. Kopiera dina egna src/figures/Circle.java, Rectangle.java och Square.java
   från labb 1 till det NYA projektets src/figures. Ersätt platshållarna.
   Kopiera också eventuella egna hjälp- och basklasser som dessa behöver.
   Kopiera inte hela src/: behåll det nya projektets Figure, Point2, Line,
   DrawingContext, app/, model/, tools/ och tester.

5. Nu är kompileringsfel väntade: Figure extends Drawable, Movable och kräver också
       public void move(double dx, double dy)
   Lägg till metoden i dina figurer. Börja vid behov med en metodkropp som
   kastar UnsupportedOperationException. Då kan projektet kompileras och
   dina konstruktorer och draw-metoder testas innan flyttning är färdig.
   En tom move-metod är inte en lösning.

6. Läs den färdiga Line.move och Point2.translated. Point2 är ett oföränderligt
   record; translated returnerar en ny punkt. Figuren ändrar sin position genom
   att ersätta sina lagrade punktvärden. Ett positionsfält som var final i labb 1
   kan därför behöva ändras; instansfälten ska fortsatt vara privata.
   Beräkna och validera alla nya punkter före första tilldelningen.
   Form, storlek och identitet ska bevaras. getCenter ska följa den nya positionen. Ogiltig flyttning ska lämna hela
   figuren oförändrad. En Square kan delegera flyttningen till sin Rectangle.

7. Kör CircleTest, RectangleTest, SquareTest, CenterTest, EncapsulationTest, MoveTest och MovableTest.
   Du kan nu kopiera din egen DemoContent.java från labb 1, eller lägga in
   dina figurer i den givna DemoContent. Kontrollera ritningen i app.DrawingApp.

8. Implementera tools/CircleTool.released med center.distance(point) för radien.
   Kör CircleToolTest och HitTestTest.
   Läs den fullständiga labbeskrivningen för gestregler och inlämning.
   Kör slutligen hela tests-junit med Run As > JUnit Test och prova GUI:t.

Öppna JUNIT.html i webbläsaren för JUnit-anvisningar för Windows och Mac.
