# Manual d’usuari

En aquest manual explicarem com utilitzar correctament cadascuna de les funcionalitats ofertes pel Mastermind. 

## 1. Registrar-se/ Identificar-se

Per poder accedir al joc, és obligatori dur a terme una d’aquestes dues opcions.

Només executar el programa, apareixerà la següent vista:

Per poder registrar-se o identificar-se és necessari omplir els camps de Nom d’usuari i Contrasenya. Un cop s’han introduit les dades necessàries, s’ha de prèmer el botó Identificar-se si l’usuari ja havia sigut registrat prèviament; o Registrar-se, altrament.

Problemes que es poden presentar en cas de no haver introduït de manera correcta les dades:

- Entrada invàlida: Es dona quan qualsevol dels dos camps no s’ha omplert. Per continuar només s’ha de tancar l’avís prement la X, i omplir novament les dades corresponents.
- L’usuari no s’ha trobat: El nom d’usuari introduit a l’hora de identificar-se  no es troba a la base de dades. Per continuar, les opcions disponibles són prèmer el botó Registrar-se per tal de crear un nou usuari amb aquell nom, o provar d’introduir un altre nom d’usuari que si que existeixi a la base de dades.
- Contrasenya incorrecta: El nom d’usuari és correcte, però la contrasenya introduida per identificar-se no coincideix amb la que està guardada a la base de dades. Per continuar, es pot provar amb una altra contrasenya o crear un nou usuari, ja que, com que el programa no demana més dades personals que el nom d’usuari i la contrasenya, no hi ha cap manera de recuperar la contrasenya en cas de no  recordar-la.
- L’usuari ja existeix: El nom d’usuari introduït per registrar-se ja existeix a la base de dades, per tant serà necessari triar un altre nom d’usuari, o si l’usuari existent va ser creat per la mateixa persona, prèmer el botó Identificar-se en comptes de Registrar-se.

## 2. Jugar una partida

Un cop registrat o identificat, per defecte, accedirà a una pantalla que li permetrà configurar una nova partida o carregar una antiga que hagués sigut guardada pel mateix usuari. 

### 2.1. Jugar una partida nova

Jugar una partida nova requereix primerament la seva configuració. A continuació s’expliquen els camps a decidir, com fer-ho, i el seu impacte en el lloc.

- Nombre de fitxes i nombre de colors: Tant el nombre de fitxes, com el de colors, es trien a través d’un desplegable que permet triar des del nombre 3 fins al 9. En augmentar el nombre de fitxes i colors, també ho fa la dificultat i, en conseqüència el temps de resposta de l’ordinador.
- Rol: El Mastermind permet jugar com a Breaker o com a Maker.
Breaker: és el jugador que ha d’endevinar la combinació escollida pel Maker, a través de les respostes obtingudes per aquest, en haver provat alguna combinació.
Maker: és el jugador que tria una combinació de fitxes, que el Breaker haurà d’endevinar, i dona resposta a les combinacions provades pel Breaker.
El rol que no sigui triat per l’usuari, evidentment, el durà a terme l’ordinador.
- Algorisme: Aquesta opció només s’ha de triar en cas de ser Maker, ja que decideix quin algorisme farà servir el Breaker (l’ordinador) per endevinar la combinació. Aquesta versió del Mastermind, permet jugar de manera que la combinació correcta sigui endevinada per l’algorisme Five Guess o pel Genètic. En un entorn en que el nombre de fitxes és 4 o inferior, i el nombre de colors és màxim 6, és millor el Five Guess, ja que assegura que la combinació serà endevinada en 5 o menys torns. Mentre que per condicions superiors, és millor emprar el Genètic, ja que el temps de resposta és més petit. 

Un cop configurats tots els camps, s’haurà de prèmer el botó Començar partida, i apareixerà una vista que permetrà jugar directament.

#### 2.1.1. Jugar una partida com a Breaker

Com ja s’havia explicat prèviament, el Breaker haurà de fer combinacions per tal d’endevinar la combinació triada pel Maker. 

Per poder provar una combinació, primerament s’han de triar cadascun dels colors de les fitxes d’aquesta. Per triar el color d’una fitxa, s’ha de prèmer sobre d’aquesta, de manera que aquesta canviarà de color. La fitxa es premerà tants cops com sigui necessari per poder triar el color desitjat.

Un cop triada la combinació, s’haurà d’enviar prement el botó Send. De manera inmediata s’obtindrà la resposta i es podrà continuar provant opcions. L resposta constarà del nombre de blanques i el nombre de negres que hi ha entre la nostra última combinació i la combinació a endevinar. El nombre de blanques fa referència al nombre de fitxes que coincideixen en color però no en posició; i el nombre de negres, a les que coincideixen en color i posició.

Un cop sigui endevinada la combinació, s’obrirà una pantalla com aquesta, i en tancar-la reconduirà de nou al jugador al menú per jugar una partida.

#### 2.1.2. Jugar una partida com a  Maker
Primerament s’haurà de triar la combinació que haurà de ser endevinada pel Breaker. Per fer-ho s’ha de triar cadascun dels colors de les fitxes. Per canviar el color d’una fitxa s’ha de prèmer a sobre d’aquesta fins que aparegui el color desitjat. Un cop estigui la combinació completa, s’ha de prèmer el botó d’Enviar. 

Depenent del nombre de fitxes i colors de la partida s’obtindrà l’intent del Breaker, de manera més o menys ràpida. A continuació, el programa demanarà el nombre de negres i blanques corresponents entre la combinació del Breaker i la triada pel Maker inicialment. El nombre de negres fa referència al nombre de fitxes que coincideixen en color i posició en ambdues combinacions. Mentre que el nombre de blanques fa referència a aquelles que només coincideixen en color. Per indicar el nombre de negres i blanques, s’ha d’intruduir un nombre del 0 al nombre màxim de fitxes seleccionat previament. 

En cas que el nombre de blanques i negres introduit no sigui el correcte, l’ordinador ho detectarà i enviarà directament la resposta correcta. 

### 2.2. Carregar una partida

## 3. Veure ranking i rècords

## 4. Modificar la configuració de l’usuari

## 5. Ajuda

## 6. Log out
