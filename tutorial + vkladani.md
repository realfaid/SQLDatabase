NADPIS
Budeme používat vývojové prostředí "Android studio" a webhosting "000webhost",

==Vytvoření projektu a základního layoutu==
* V android studiu si vytvoříme nový projekt (v horní liště "file->new->New project") a&nbsp;vybereme&nbsp;"Empty&nbsp;Activity".

* Zadáme název (Name) našeho projektu (v mém případě "sqlprojekt"), jazyk (Language) vybereme "Java" a&nbsp;minimální SDK (Minimal SDK), doporučoval bych API androidu 5 nebo 6, aby šla aplikace spustit na&nbsp;většině mobilních zařízeních. Klikneme na finish pro vytvoření a počkáme než se projekt vytvoří.
* Nyní si vytvoříme vzhled hlavní stránky, v levém panelu přejdeme do "res->layout->activity_main.xml", v&nbsp;pravé horní části si můžete vybrat, jak chcete xml soubor zobrazovat, doporučuji používat zobrazení "Design" pro přidání objektů na&nbsp;stránku pomocí přetahování objektů z části vlevo od levé obrazovky, a&nbsp;potom používat "Split" na následnout úpravu objektů pomocí kódu. Nezapomeňte každý objekt po&nbsp;přidání ukotvit pomocí teček na stranách objektu.
* Na stránku přidáme tlačítko (Button), tlačítko bude sloužit pro zobrazení stránky na přidávání dat, přejdeme na verzi zobrazení "Split" a nastavíme tlačítku hodnotu atributu "id" (např.&nbsp;`android:id="@+id/btnAddRecept"`) a hodnotu atributu "text"(text který má na&nbsp;tlačítku být) (např.`android:text="Přidat recept"`), v atributech layoutu, na začátku kódu, necháme `tools:context=".MainActivity"`, pokud tomu tak není, připíšeme. To nám zařídí se kterou aktivitou je tenhle layout spojen
* V levém panelu přejdeme do: "java->com.example.sqlprojekt&nbsp;(sqlprojekt=název&nbsp;vašeho&nbsp;projektu)->MainActivity"
* V metodě "onCreate" máme uvedeno jaký layout cheme zobrazit: `setContentView(R.layout.activity_main);`-"activity_main" je náš xml soubor s tlačítkem.
* V tříde "MainAcvitiy" vytvoříme novou metodu "addRecept" s parametrem "View view" (v našem případě to bude instance tlačítka které bude tuto metodu volat.)`public void AddRecept(View v){  }`pokud nám "View" bude házet chybu a svítit červeně, stačí na něj najet myší a zmáčknout "alt+shift+enter" pro&nbsp;implementování.
* Vytvoříme novou třídu "AddReceptActivity", která bude sloužit na přidání receptu a zobrazení stránky pro přidání, klikneme pravým na složku, ve které je i "MainActivity.java"&nbsp;("java->com.example.sqlprojekt") dáme "New->Java class" a pojmenujeme "AddReceptActivity". Každou třídu, která bude sloužit jak aktivity pro zobrazení nějaké stránky, musíme definovat v manifestu ("manifests->AndroidManifest.xml"). Definujeme to pomocí `<activity android:name=".AddReceptActivity"/>`- .AddReceptActivity - je název třídy.
* Nastavíme třídu aby dědila od třídy "AppCompatActivity", je to základní třída pro aktivity, která se automaticky nachází ve vytvořeném projektu, dědičnost nastavíme pomocí "extends".
`public class MainActivity extends AppCompatActivity`.
* Nyní potřebujeme vytvořit nový layout pro přidávání, klikneme pravým na složku, ve které se již nachází "activity_main.xml" ("res->layout"), klikneme na "New->Layout Resource File", pojmenujeme si to (v mém případě "add_recept") a klikneme na "OK" pro vytvoření.
* Pomocí zobrazení "Design" si na stránku si přidáme objekty "Plain Text" (v kódu jako "EditText") a jedno tlačítko ("Button"), opět nezapomenem objekty ukotvit.
* Přejdeme na zobrazení "Split" nebo "Code" a nastavíme opět objektům své id (např. "editTextNazev", "editTextSuroviny", "editTextPostup", "btnConfirmAddRecept"), nastavíme tlačítku opět nějaký text (`android:text="Přidat"`), políčka na psaní budou sloužit pro zapsání hodnot dat a tlačítko bude sloužit na odeslání dat do databáze. (Více v další části tutoriálu)
* Spojíme layout se třídou AddReceptActivity pomocí `tools:context=".AddReceptActivity"`, které napíšeme nahoru do atributů layoutu.
* Vrátíme se do třídy "AddReceptActivity", kde si vytvoříme metodu "onCreate", stejně jako je ve tříde "MainActivity" (můžeme ji zkopírovat), a nastavíme zobrazení layoutu, který jsme si vytvořili ("add_recept.xml") `setContentView(R.layout.add_recept);`
* Teď se přesuneme do třídy "MainActivity" a nastavíme co má metoda "addRecept" dělat. Bude sloužit k přepnutí třídy z "MainActivity" na "AddReceptActivity".
* To zařídíme pomocí třídy Intent (abstraktní popis operace, která má být provedena), který použíjeme s metodou "startActivity()", která slouží ke spuštění aktivity. Náš kód bude vypadat takhle: 
`Intent intent1 = new Intent(MainActivity.this, AddReceptActivity.class);`- vytvoříme instanci třídy Intent kterou si pojmenujeme např. "intent1", jako první atribut píšeme třídu ve které se nacházíme, jako druhý atribut, třídu na kterou chceme přepnout, a následně spustíme pomocí "startActivity". `startActivity(intent1);`
* Nyní přejdeme do xml souboru "activity_main.xml" a tlačítku nastavíme atribut "onClick", hodnotou bude metoda, kterou má tlačítko zavolat, při kliknutí. V našem případě to bude `android:onClick="addRecept"`(Tlačítko zavolá metodu "addRecept" z třídy se kterou je layout spojen, v našem případě třída "MainActivity".
* Teď umí naše aplikace po kliknutí na tlačítko "Přidat recept" zobrazit stránku na přidávání, ale musíme vyřešit jak se z ní dostat, to vyřešíme pomocí menu.
* Ve složce "res" vytvoříme složku ("Directory") "menu", do složky vytvoříme "Menu Resource File" pojmenovaný "main_menu".
* Pomocí zobrazení "Design" přidáme "Menu Item" a přejdeme do kódu. Hodnota atributu "android:title" slouží jako název položky v menu. V našem případě nastavíme na "Domů", nastavíme položce atribut "id" `android:id="@+id/home"` a dále přidáme atribut, aby byla položka vždy zobrazena `app:showAsAction="always"`.
* Zobrazeni menu pomocí kódu:
`public boolean onCreateOptionsMenu(Menu menu){ `\
`MenuInflater inflater = getMenuInflater();  `\
` inflater.inflate(R.menu.main_menu, menu);  `//main_menu" je název našeho menu\
` return true;}`\
zobrazíme menu, kód voláme ve třídě, kde potřebujeme menu zobrazit, v našem případě to bude v&nbsp;"AddReceptActivity" 
* Nastavení položek v menu\
`public boolean onOptionsItemSelected(@NonNull MenuItem item) {  `\
`switch (item.getItemId()) {  `\
`case R.id.home:  `//"home" je hodnota atributu "id", které jsme si nastavili pro naše menu\
`Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);  `\
`startActivity(ht1);   `\
`return true;  `\
` default:  `\
` return super.onOptionsItemSelected(item);  } }`\
nastavíme položkám v menu, "co mají dělat". Položce "Domů", která má "id=home" nastavíme, že má přepnout na třídu\ "MainActivity".\

==Vytvoření databáze a připojení k ní==
* Databázi budeme provozovat na "https://cz.000webhost.com/", vytvoříme si zde novou stránku, používat budeme ale jenom databázi. Poté přejdeme na "Manage website" v levé liště "Tools" a následně Database manager. Vytvoříme si novou databázi a klikneme na "manage database" a vybereme "phpmyadmin". V naší databázi si vytvoříme novou tabulku, kterou budeme používat. V našem případě tabulka "recepty" se sloupci "id", "nazev", "suroviny", "postup". "id" bude typ int a bude auto-increment, zbytek typ text.

* Nyní přejdeme v levé liště na "File manager", klikneme na upload files a ve složce "public_html" vytvoříme soubor "insert.php"
* Zde nastavíme údaje pro připojení k databázi a sql dotaz pro vložení dat do databáze.\
` <?php `\
`$db_name = "id18087905_mojedatabaze";`  - název databáze\
`$mysql_username = "id18087905_realfaid";` - jména nás jako uživatele\
`$mysql_pasword = "heslo";` - naše heslo k databázi\
`$server_name = "localhost";` - název serveru na kterém to jede\
`$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name);` - slouží k připojení k databázi\
`$nazev = $_POST["nazev"];` - do proměnné $nazev uloží data které se odešlou z aplikace\
`$suroviny = $_POST["suroviny"];` - do proměnné $suroviny uloží data které se odešlou z aplikace\
`$postup = $_POST["postup"];` - do proměnné $postup uloží data které se odešlou z aplikace\
`$sql = "INSERT INTO recepty(nazev, suroviny, postup) VALUES('$nazev', '$suroviny', '$postup')";` - sql dotaz na vložení dat\
`$result = mysqli_query($connection,$sql);` - výsledek připojení a následného dotazu\
`if($result){`\
  `  echo "Data vložena";` - pokud se podaří vše vypíše se "Data vložena"\
`}`\
`else{`\
 `   echo "chyba";` - pokud se něco nepodaří vypíše to chyba\
`}`\
`mysqli_close($connection);` - ukončení připojení k databázi\
`?>`\
Uložíme a zavřeme.
* Jestli nám vše funguje jak má můžeme otestovat, klikneme pravým na soubor "insert.php" a vybereme "View", pokud nám to vyhodí hlášku, kterou jsme si nastavili pokud vše klapne, je to vpořádku, pokud nám to vyhodí hlášku, kterou jsem si nastavili, když něco neklapne, něco je nejspíš špatně napsané.

==Vložení dat v aplikaci==
* Jako první budeme potřebovat layout, který si vytvoříme. Bude obsahovat tolik EditTextů kolik potřebujete, v našem případě to budou 3, a tlačítko na odeslání dat. Vše nezapomeneme ukotvit a nastavit ID.

* Vytvoříme novou třídu "AddRecept" a bude dědit z třídy AppCompatActivity(stejně jako MainActivity), vytvoříme onCreate metodu a nastavíme layout, který jsme si vytvořili(Můžeme si pomoct a urychlit čas a zkopírovat to ze třídy MainActivity). Zkopírujeme si také kód pro zobrazení menu a ovládání položek v něm(onCreateOptionsMenu a onOptionsItemSelected).
* Když máme vytvořenou třídu i metodu onCreate nadefinujeme si Button a EditTexty, se kterýma budeme pracovat, následovně:\
`public class AddRecept extends AppCompatActivity {`\
 `   EditText txtname, txtresources, txtprocess;` - pojmenujeme si podle sebe\
  `  Button confirmRecept;` - pojmenujeme si podle sebe\
`    @Override`\
  `  protected void onCreate(Bundle savedInstanceState) {`\
     `   super.onCreate(savedInstanceState);`\
   `setContentView(R.layout.add_recept);`\
    `   txtname = findViewById(R.id.editReceptName);` - definujeme podle ID\
    `    txtresources = findViewById(R.id.editReceptResources);` - definujeme podle ID\
    `    txtprocess = findViewById(R.id.editReceptProcess);` - definujeme podle ID\
    `  confirmRecept = findViewById(R.id.btnConfirmAddRecept);` - definujeme podle ID\
* V metodě onCreate vytvoříme OnClickListener pro náš button a do něho provedení metody "insertData", kterou zachvíli i vytvoříme:\
 `confirmRecept.setOnClickListener(new View.OnClickListener()  {`\
  `@Override`\
  `public void onClick(View v) {`\
  `insertData();`\
  ` }`
* Vytvoříme metodu insertData, vytvoříme proměnné String, do kterých se bude ukládat text napsaný v EditTextech:\
 `public void insertData() { `\
 `  String nazev = txtname.getText().toString().trim(); ` - do proměnné se nám uloží text z EditTextu txtname\
 `  String suroviny = txtresources.getText().toString().trim(); `\
 `  String postup = txtprocess.getText().toString().trim(); `
* Teď si vytvoříme podmínky pro kontrolu, zda je v EditTextu něco napsané, to uděláme tak, že pokud proměnná textu(nazev, suroviny a postup) je prázdná, vypíše se, že je políčko prázdné, bude to vypadat nějak takhle:\
`if (nazev.isEmpty()) {`\
 `           Toast.makeText(this, "Zadej název", Toast.LENGTH_SHORT).show();`\
 `           return;`\
 `       } else if (suroviny.isEmpty()) {`\
  `          Toast.makeText(this, "Zadej Suroviny", Toast.LENGTH_SHORT).show();`\
  `          return;`\
  `      } else if (postup.isEmpty()) {`\
  `          Toast.makeText(this, "Zadej Postup", Toast.LENGTH_SHORT).show();`\
  `          return;`\
  `      }`
* Pokud ani jedna z podmínek nenastane, nastavíme co se má stát, a to bude samotné odeslání dat. Bude to vypadat nějak takhle a následně si to popíšeme:

* V první části si vytvoříme StringRequest na načtení kódu ze souboru na internetu, určíme metodu POST a zadáme url našeho insert souboru, potom nastavíme pokud to vypíše výsledek "Data vložena"(To co jsme si psali v souboru "insert.php") tak nám to napíše "Data Vložena", pokud ne, vypíše se "chybička".\
`else{`\
 `           StringRequest request = new\ StringRequest(Request.Method.POST,"https://sqlprojekt.000webhostapp.com/insert.php",` - tohle je URL adresa na náš soubor, který jsme si vytvořili\
 `                   new Response.Listener<String>() {`\
  `                      @Override`\
  `                      public void onResponse(String response) {`\
  `                          if(response.equalsIgnoreCase("Data vložena")) {`\
 `                               Toast.makeText(AddRecept.this, "Data vložena", Toast.LENGTH_SHORT).show();`\
 `                               Intent ht1 = new Intent(AddRecept.this, MainActivity.class);`\
 `                               startActivity(ht1);`\
 `                           }`\
 `                           else{`\
  `                              Toast.makeText(AddRecept.this, "chybička", Toast.LENGTH_SHORT).show();`\
 `                           }}`
* Tohle nám bude zajištovat, že když nastane error, vypíše se nám kód erroru, díky čemu to pro nás bude lepší opravit.\
  `      }, new Response.ErrorListener() {`\
  `          @Override`\
  `          public void onErrorResponse(VolleyError error) {`\
  `                  Toast.makeText(AddRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();`\
  `          }`\
 `       }`\
   `         ){`
* V další části si vytvoříme Mapu parametrů, které budou použiti pro metodu POST\
   `             @Override`\
   `             protected Map<String, String> getParams() throws AuthFailureError {`\
   `                 Map<String,String> params = new HashMap<String,String>();`\
   `                 params.put("nazev",nazev);` - na prvním místě v závorce, jak se pojmenuje, druhé místo, obsah.\
V našem případě na prvním místě název, který jsme použili i v souboru "insert.php" v závorce u metody POST, druhé místo naše proměnná\
    `                params.put("suroviny",suroviny);`\
   `                 params.put("postup",postup);`\
   `                 return params;`\
    `            }`
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".\
   `         };`\
   `         RequestQueue requestQueue = Volley.newRequestQueue(AddRecept.this);`\
   `         requestQueue.add(request);`\
  `  }`
  


