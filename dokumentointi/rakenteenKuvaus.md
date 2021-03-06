##Shakkipelin logiikka on jaettu seuraaviin luokkiin:

Logiikka:
* PeliHallinta:
- pelihallinta on logiikan ylin elementti, ja kayttöliittymä ohjailee logiikkaa sen kautta. Pelihallinan tehtävänä on pelien lataaminen ja tallentaminen. Pelihallinta käsittelee tiedostoihin kirjoittamisen ja niistä lukemisen. Pelihallinta tarjoaa käyttöliittymälle yhteyden peliin ja sitäkautta muihin logiikan luokkiin.
* Peli:
- Peli on pelilogiikan keskeinen elementti. Pelin tehtävä on asettaa käyttöliittymän ilmoittamassa ruudussa oleva nappula aktiiviseksi, sekä yhdessä nappulan toteuttavien luokkien kanssa tarkistaa onko käyttöliittymän ehdottama siirto sääntöjen mukainen. Jos peli hyväksyy siirron, pyytää se PeliLautaa toteuttamaan siirron.
* PeliTarkistus:
- Pelitarkistus on ikäänkuin pelin apuluokka, joka tarjoaa metodit, joilla tarkistetaan erilaisia pelitilanteeseen liittyviä asioita. Esimerkiksi:
-- Onko peli matissa?
-- Onko kuningas uhattuna?
-- Onko vielä mahdollista saavuttaa mattia? 
* PeliLauta:
- Pelilauta itsessään on toteutettu 8x8 taulukkona nappuloita (tyhjä ruutu = null). Pelilauta luokan tehtävänä on asettaa nappuloita laudalle sen mukaan miten Peliluokka sitä pyytää.
* Pelaaja:
- Pelaaja pitää tallessa tietoa pelaajaan ja hänen nappuloihinsa liittyvistä asioista. Esimerkiksi: 
-- Pelaajan perusrivi, jne.
-- Lista pelaajan nappuloista
-- Viite pelaajan kunkkuun
-- Pelaajan kello
* Kello:
- Kello pitää tallessa tietoa siitä kuinka paljon pelaajalla on aikaa jäljellä suorittaa loput siirtonsa.
- Kellolla on suora viite KelloPiirtoon käyttöliittymän puolelle, sillä kellon pitää pystyä pyytämään KelloPiirtoa päivittämään itsensä.
* Ajastin:
- Ajastin pyytää vuorossa olevan pelaajan kelloa päivittämään itseään kerran sekunnissa.
* Nappula: 
- Nappula on abstrakti luokka, jonka eri nappuloita simuloivat luokat toteuttavat.
- Nappula ja sen toteuttavat luokat tarkistavat onko pyydetty siirto nappulan liikkumistavan mukainen, ja onko nappulan reitillä muita nappuloita.
* NappulaApumetodeja:
- Tarjoaa apumetodeja, kuningattaren, lähetin ja tornin käyttöön.

Pelin toiminta eteenee siis jotakuinkin seuraavan kaavan mukaan: Käyttöliittymä pyytää Peliä tekemään siirron->Peli pyytää Nappulaa tarkistamaan liikkuuko se ehdotetulla tavalla -> Peli kokeilee siirtoa, ja käyttäen hyväksi PeliTarkistuksen metodeja sekä pelilautaa, toteaa jääkö peli sallittuun asemaan siirron jälkeen. Jos ei jää siirto perutaan. Mikäli siirto oli sallittu jää se voimaan, ja Peli vaihtaa vuoroa. -> Käyttöliittymä pyytää PeliTarkistukselta tiedon onko peli tullut päätökseen, ja jos ei ole voi nyt vuorossa oleva pelaaja jatkaa.

Pelin tallennus tapahtuu siten, että käyttöliittymä pyytää PeliHallintaa tallentamaan pelin, joka puolestaan kutsuu Pelin toString metodia, joka puolestaan kutsuu lautaa, joka kutsuu jokaista ruutua. PeliHallinta tallentaa saadun String olion tiedostoon ja sulkee sen.

Pelin lataus tapahtuu siten, että Pelihallinta lukee tiedoston sisällön String olioon, ja antaa sen Pelille. tämän jälkeen peli asettaa Vuorossa olevan pelaajan, laudalla olevat nappulat, sekä kellon oikeisiin arvoihin.

##Käyttöliittymä:

Käyttöliittymä koostuu laudasta (PeliPiirto), yläreunan valikoista, sekä alareunan kellosta. Käyttöliittymän keskeisiä osia ovat PeliPiirron lisäksi LautaKuuntelija, joka kuuntelee hiiren klikkauksia pelilaudalla, ja pyytää logiikkaa toimimaan niiden mukaan. Yläreunan valikoilla on omat kuuntelijansa. Pelilauta koostuu staattisesta ruudukosta, joka piirretään ohjelmallisesti, sekä nappuloiden kuvista, jotka ovat .png tiedostoja. Jokaisella logiikan nappulalla on oma NappulaPiirto luokka, joka palauttaa kyseisen nappulan kuvan. 



