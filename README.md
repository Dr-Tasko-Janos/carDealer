# CarDealershipApplication

# CarDealershipApplication
Az alkalmazás a bizományi autóértékesítés megkönnyítésére hivatott, melynek keretében nyilván tudjuk tartani a segítségével az eladási bizományba átvett gépjárműveket, ügyelve a 95/46/EC (General Data Protection) Regulation /GDPR/ adatkezelésre vonatkozó rendelkezéseinek megtartására is, mint amilyen többek között az adatkezelés célhoz kötöttsége. Ennek jegyében a bizományba átvett gépjárművek adatait külön kezeli az eladó személyére vonatkozó szenzitív adatoktól, gátolva ezáltal az adatkezelésre vonatkozó jogi rendelkezések megsértésének kockázatát.

## Az alkalmazás által létrehozott adatszerkezet két részre osztható, az gépjárművekre vonatkozó adatokat a ’cars’-, míg a tulajdonosokhoz kötődő személyes adatokat (vagy a gépjárműre vonatkozó védendő adatokat) a ’sensitivedataofownerandvehicle’   nevű táblában tárolja.
Az alkalmazás fentebb említett két logikai egységéhez az alábbi belépési pontok tartoznak:
###/api/cars/  belépési ponton keresztül lehet lekérdezni az eladó kezelésében lévő járműpark elemeit, megjelenítve a vevők szemszögéből lényeges tulajdonságait a járműnek, melyek a következők: gyártmány, modell, évjárat, eddigi futásteljesítmény, állapot, motor típusának megjelölése a felhasznált üzemanyag/erőforrás függvényében, ülések száma valamint a vételár.
###/api/owners/ belépési pontok keresztül érhetőek el azon szenzitív adatok, melyeknek a járművek nyilvános adataival történő együtt kezelése jogi aggályokat vethet fel adatvédelmi incidens estén.
Ilyen adat többek között a gépjármű tulajdonosának vezetékneve, keresztneve, telefonszáma, a gépjármű egyedi azonosítószáma. (Eme adatok köre igény szerint bővíthető az ügyfél kérésére)
#Egyes rétegek funkciójának rövid bemutatása
## Cars – GET metódusok
###/api/cars/id végponton keresztül GET metódus segítségével a gépjármű belső nyilvántartási száma(id) alapján válik lehetővé a gépjármű publikus adatainak lekérdezése.
###/api/cars/make?make=gyártóNév Modelnév (gyártóllNév) alapján lehetőség van a készleten lévő gépjárművek között gyártónév alapján történő keresésére, mely visszaadja mindazon gépjárművek publikus adatát, melyek az adott gyártót képviselik.

###/api/cars/model?model=modellNév Modelnév (modellNév) alapján lehetőség van a készleten lévő gépjárművek között modellnév alapján történő keresésére, mely visszaadja mindazon gépjárművek publikus adatát, melyek az adott modellt képviselik.
###/api/cars/year?year=gyártásiÉvszámLegkorább-gyártásiÉvszámLegkésőbb azon gépjárművek publikus adatai kérdezhetőek le, melyeknek a gyártási éve a megadott értékek közé esik a két záróértéket is beleértve.
###/api/cars/kilometer?kilometer=kezdőÉrték-végÉrték Az elvárt legkisebb futásteljesítmény, valamint kötőjellel hozzáfűzve a még elfogadható maximális futásteljesítmény (mindkét érték a tálaltokhoz hozzátartozik) közötti futásteljesítmény értékkel rendelkező gépjárművek publikus adatai kérhetőek le.
###/api/cars/condition/USED Előre definiált értékekkel (EXCELLENT, AVERAGE, USED) értékekkel lekérdezhető mindazon gépjárművek köre, melyek állapot tulajdonsága megfelel a fentebb megadott értéknek.
###/api/cars/engine?engine=ELECTRIC Előre definiált értékekkel (ELECTRICT, GASOLINE, DIESEL) értékekkel lekérdezhető mindazon gépjárművek köre, melyek erőforrásigénye megfelel a fentebb megadott energiahordozónak/üzemanyagtípusnak.
###/api/cars/numberOfSeats?numberOfSeats=SEVEN_SEATER Előre definiált értékekkel (TWO_SEATER, FOUR_SEATER, FIVE_SEATER, SEVEN_SEATER) értékekkel lekérdezhető mindazon gépjárművek köre, melyek ülőhelyeinek száma megfelel a fentebb megadott férőhelyszámnak.
###/api/cars/price?minimumÁr-maximumÁr Az elvárt legkisebb vételár valamint kötőjellel hozzáfűzve a még elfogadható maximális vételár (mindkét érték a tálaltokhoz hozzátartozik) közötti ár értékkel rendelkező gépjárművek publikus adatai kérhetőek le.
##Cars POST metódus
###/api/cars/id végponton UpdateCarPriceCommand beküldésével és a hozzá tartozó id azonosítószámmal elvégezhető az adott id-vel rendelkező gépjármű vételáradatának frissítése.
##Cars DELETE metódus
###/api/cars/id végponton DELETE metódus segítségével törlésre kerül az adott id-vel rendelkező gépjármű valamennyi publikus, illetőleg valamennyi szenzitív adata egyidőben.
## Owners GET metódus
###/api/owners/id oldalon lekérdezhető az adott gépjármű id-hoz tartozó tulajdonos szenzitív adata, valamint a gépjárműre vonatkozó szenzitív adatok egyéb köre.
