Tutoriál pro vytvoření příkladové aplikace pro práci s SQL databází.
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
* Spojíme layout se třídou AddReceptActivity pomocí `tools:context=".AddRecept"`, které napíšeme nahoru do atributů layoutu.
* Vrátíme se do třídy "AddRecept", kde si vytvoříme metodu "onCreate", stejně jako je ve tříde "MainActivity" (můžeme ji zkopírovat), a nastavíme zobrazení layoutu, který jsme si vytvořili ("add_recept.xml") `setContentView(R.layout.add_recept);`
* Teď se přesuneme do třídy "MainActivity" a nastavíme co má metoda "addRecept" dělat. Bude sloužit k přepnutí třídy z "MainActivity" na "AddRecept".
* To zařídíme pomocí třídy Intent (abstraktní popis operace, která má být provedena), který použíjeme s metodou "startActivity()", která slouží ke spuštění aktivity. Náš kód bude vypadat takhle: 
`Intent intent1 = new Intent(MainActivity.this, AddRecept.class);`- vytvoříme instanci třídy Intent kterou si pojmenujeme např. "intent1", jako první atribut píšeme třídu ve které se nacházíme, jako druhý atribut, třídu na kterou chceme přepnout, a následně spustíme pomocí "startActivity". `startActivity(intent1);`
* Nyní přejdeme do xml souboru "activity_main.xml" a tlačítku nastavíme atribut "onClick", hodnotou bude metoda, kterou má tlačítko zavolat, při kliknutí. V našem případě to bude `android:onClick="addRecept"`(Tlačítko zavolá metodu "addRecept" z třídy se kterou je layout spojen, v našem případě třída "MainActivity".
* Teď umí naše aplikace po kliknutí na tlačítko "Přidat recept" zobrazit stránku na přidávání, ale musíme vyřešit jak se z ní dostat, to vyřešíme pomocí menu.
* Ve složce "res" vytvoříme složku ("Directory") "menu", do složky vytvoříme "Menu Resource File" pojmenovaný "main_menu".
* Pomocí zobrazení "Design" přidáme "Menu Item" a přejdeme do kódu. Hodnota atributu "android:title" slouží jako název položky v menu. V našem případě nastavíme na "Domů", nastavíme položce atribut "id" `android:id="@+id/home"` a dále přidáme atribut, aby byla položka vždy zobrazena `app:showAsAction="always"`.
* Zobrazeni menu pomocí kódu:\
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
nastavíme položkám v menu, "co mají dělat". Položce "Domů", která má "id=home" nastavíme, že má přepnout na třídu "MainActivity".

==Vytvoření databáze a připojení k ní==
* Databázi budeme provozovat na "https://cz.000webhost.com/", vytvoříme si zde novou stránku, používat budeme ale jenom databázi. Poté přejdeme na "Manage website" v levé liště "Tools" a následně Database manager. Vytvoříme si novou databázi a klikneme na "manage database" a vybereme "phpmyadmin". V naší databázi si vytvoříme novou tabulku, kterou budeme používat. V našem případě tabulka "recepty" se sloupci "id", "nazev", "suroviny", "postup". "id" bude typ int a bude auto-increment, zbytek typ text.

* Nyní přejdeme v levé liště na "File manager", klikneme na upload files a ve složce "public_html" vytvoříme soubor "insert.php"
* Zde nastavíme údaje pro připojení k databázi a sql dotaz pro vložení dat do databáze.\
` <?php `\
`$db_name = "id18087905_mojedatabaze";`  // název databáze\
`$mysql_username = "id18087905_realfaid";` // jména nás jako uživatele\
`$mysql_pasword = "heslo";` // naše heslo k databázi\
`$server_name = "localhost";` // název serveru na kterém to jede\
`$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name);` // slouží k připojení k databázi\
`$nazev = $_POST["nazev"];` // do proměnné $nazev uloží data které se odešlou z aplikace\
`$suroviny = $_POST["suroviny"];` // do proměnné $suroviny uloží data které se odešlou z aplikace\
`$postup = $_POST["postup"];` // do proměnné $postup uloží data které se odešlou z aplikace\
`$sql = "INSERT INTO recepty(nazev, suroviny, postup) VALUES('$nazev', '$suroviny', '$postup')";` // sql dotaz na vložení dat\
`$result = mysqli_query($connection,$sql);` // výsledek připojení a následného dotazu\
`if($result){`\
  `  echo "Data vložena";` // pokud se podaří vše vypíše se "Data vložena"\
`}`\
`else{`\
 `   echo "chyba";` // pokud se něco nepodaří vypíše to chyba\
`}`\
`mysqli_close($connection);` // ukončení připojení k databázi\
`?>`\
Uložíme a zavřeme.
* Jestli nám vše funguje jak má můžeme otestovat, klikneme pravým na soubor "insert.php" a vybereme "View", pokud nám to vyhodí hlášku, kterou jsme si nastavili pokud vše klapne, je to vpořádku, pokud nám to vyhodí hlášku, kterou jsem si nastavili, když něco neklapne, něco je nejspíš špatně napsané.

==Vložení dat v aplikaci==
* ,Ve tříde AddRecept, kterou již máme vytvořenou, vytvoříme onCreate metodu a nastavíme layout, který jsme si vytvořili(Můžeme si pomoct a urychlit čas a zkopírovat to ze třídy MainActivity). Zkopírujeme si také kód pro zobrazení menu a ovládání položek v něm(onCreateOptionsMenu a onOptionsItemSelected).
* Když máme vytvořenou třídu i metodu onCreate nadefinujeme si Button a EditTexty, se kterýma budeme pracovat, následovně:\
`public class AddRecept extends AppCompatActivity {`\
 `   EditText txtname, txtresources, txtprocess;` // pojmenujeme si podle sebe\
  `  Button confirmRecept;` // pojmenujeme si podle sebe\
`    @Override`\
  `  protected void onCreate(Bundle savedInstanceState) {`\
     `   super.onCreate(savedInstanceState);`\
   `setContentView(R.layout.add_recept);`\
    `   txtname = findViewById(R.id.editReceptName);` // definujeme podle ID\
    `    txtresources = findViewById(R.id.editReceptResources);` // definujeme podle ID\
    `    txtprocess = findViewById(R.id.editReceptProcess);` // definujeme podle ID\
    `  confirmRecept = findViewById(R.id.btnConfirmAddRecept);` // definujeme podle ID\
* V metodě onCreate vytvoříme OnClickListener pro náš button a do něho provedení metody "insertData", kterou zachvíli i vytvoříme:\
 `confirmRecept.setOnClickListener(new View.OnClickListener()  {`\
  `@Override`\
  `public void onClick(View v) {`\
  `insertData();`\
  ` }`
* Vytvoříme metodu insertData, vytvoříme proměnné String, do kterých se bude ukládat text napsaný v EditTextech:\
 `public void insertData() { `\
 `  String nazev = txtname.getText().toString().trim(); ` // do proměnné se nám uloží text z EditTextu txtname\
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

* V první části si vytvoříme StringRequest na načtení kódu ze souboru na internetu, určíme metodu POST a zadáme url našeho insert souboru, potom nastavíme pokud nenastane chyba a kód se provede a vásledek se bude rovnat "Data vložena"(To co jsme si psali v souboru "insert.php") tak nám to napíše "Data Vložena", pokud ne, vypíše se "chybička".\
`else{`\
 `           StringRequest request = new\ StringRequest(Request.Method.POST,"https://sqlprojekt.000webhostapp.com/insert.php",`\
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
   `         )`
* V další části si vytvoříme Mapu hodnot, které budou odeslány pomocí POST, pošleme pomocí ní hodnoty pro název, suroviny a postup.\
 ` {`\
 `             @Override`\
   `             protected Map<String, String> getParams() throws AuthFailureError {`\
   `                 Map<String,String> params = new HashMap<String,String>();`\
   `                 params.put("nazev",nazev);` //na prvním místě v závorce, jak se pojmenuje, druhé místo, obsah.\
//V našem případě na prvním místě název, který jsme použili i v souboru "insert.php" v závorce u metody POST, druhé místo naše proměnná\
    `                params.put("suroviny",suroviny);`\
   `                 params.put("postup",postup);`\
   `                 return params;`\
    `            }`
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".\
   `         };`\
   `         RequestQueue requestQueue = Volley.newRequestQueue(AddRecept.this);`\
   `         requestQueue.add(request);`\
  `  }`

==Výpis dat do Listview==
* Jako první sa na naší první stránku musíme přidat ListView, takže v xml souboru, který používáme na hlavní stránce, přidáme ListView, ukotvíme a nastavíme id.

* Nyní si vytvoříme novou trídu s názvem Recept, bude to náš model, pro práci s databází. V něm si vytvoříme proměnné String, konstruktor a metody pro získávání a nastavování hodnot(gettery a settery). Bude to vypadat takhle:\
`public class Recept {`\
   ` private String id,nazev,suroviny,postup;`\
 `   public Recept() {`\
 `   }`\
   ` public Recept(String id, String nazev, String suroviny, String postup) {`\
       ` this.id = id;`\
     `   this.nazev = nazev;`\
   `     this.suroviny = suroviny;`\
   `     this.postup = postup;`\
  `  }`\
  `  public String getId() {`\
`        return id;`\
 `   }`\
 `   public void setId(String id) {`\
 `       this.id = id;`\
 `   }`\
  `  public String getNazev() {`\
 `       return nazev;`\
 `   }`\
  `  public void setNazev(String nazev) {`\
 `       this.nazev = nazev;`\
 `   }`\
 `   public String getSuroviny() {`\
  `      return suroviny;`\
  `  }`\
  `  public void setSuroviny(String suroviny) {`\
  `      this.suroviny = suroviny;`\
  `  }`\
  ` public String getPostup() {`\
  `      return postup;`\
  `  }`\
  `  public void setPostup(String postup) {`\
  `      this.postup = postup;`\
  `  }`\
`}`
* Bude to fungovat tak, že při vytvoření instanci třídy, budeme psát jako parametry, hodnoty které získáme z databáze a ty se uloží do proměnných třídy Recept, poté už jen budeme používat metody pro získávání dat, případně nastavení nové hodnoty.
* Nyní si vytvoříme xml soubor, který se bude zobrazovat v ListView. Při vytváření si pojmenujeme soubor jako "recept_item" a Root element nastavíme LinearLayout. Když máme soubor vytvořený, v kódu přepíšeme "android:orientation" layoutu na "horizontal" a "android:layout_height" na "wrap_content", nyní si do layoutu přidáme TextView a nastavíme id("vypisReceptNazev")\
`android:orientation="horizontal"`\
 `   android:layout_width="match_parent"`\
  `  android:layout_height="wrap_content"`\
  `  android:padding="8dp">`

  `  <TextView`\
    `    android:id="@+id/vypisReceptNazev"`\
    `    android:layout_width="342dp"`\
     `   android:layout_height="69dp"`\
      `  android:gravity="center_vertical|center"`\
       ` android:text="nazev"`\
     `   android:textSize="20dp" />`
* Jako další budeme potřebovat adapter, takže vytvoříme novou třídu a pojmenujeme ji ReceptAdapter, ta bude sloužit pro zobrazení položek v ListView. Třída bude dědit ArrayAdapter s modelem Recept : `public class ReceptAdapter extends ArrayAdapter<Recept>`
* Vytvoříme objekt Context a pojmenujeme context a objekt List třídy Recept a pojmenujeme arrayListRecept:\
` Context context;`
`    List<Recept> arrayListRecept;`
* Jako další vytvoříme konstruktor s parametrama Context context a List<Recept> arrayListRecept, taky nastavíme aby to neprošlo pokud hodnota context bude nulová(null) a to pomocí @NonNull před conext, konstruktor nastaví pro arrayList položku pro zobrazování pomocí super, také uloží zadané hodnoty při volání třídy do objektů context a arraylist ve třídě.\
 ` public ReceptAdapter(@NonNull Context context, List<Recept> arrayListRecept) {`\
 `       super(context, R.layout.recept_item,arrayListRecept);` // recept_item - název xml souboru pro položku v listview\
 `       this.context = context;`\
 `       this.arrayListRecept = arrayListRecept;`\
 `   }`
* Vytvoříme metodu getView s parametry "int position", "View convert"(před tím @Nullable - může být obsah nulový) a "ViewGroup parent"(před tím @NonNull). metoda bude používat LayoutInflater, který převezme náš "recept_item.xml" soubour a vytvoří různé objekty zobrazení. Dále nastavíme pro TextView název daného receptu podle pozice "position" a vrátíme hodnotu view.\
 ` public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {`\
    `    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_item, null, true);`\
   `    TextView vypisNazev = view.findViewById(R.id.vypisReceptNazev);`\
    `    vypisNazev.setText(arrayListRecept.get(position).getNazev());`\
     `   return view;`\
  `  }`
* Přejdeme to třídy MainActivity. Vytvoříme si nový obejkt listView, uložíme do něj náš listview podle id, dále objekt adapter, do kterého uložíme uložíme instanci našeho adaptéru. Vytvoříme si ArrayList pro třídu Recept.\
Do třídy:\
   ` ListView hlavniListView;`\
   ` ReceptAdapter adapter;`\
   ` public static ArrayList<Recept> receptArrayList = new ArrayList<>();`\
Do metody onCreate:\
   ` hlavniListView = findViewById(R.id.myListView);`\
   ` adapter = new ReceptAdapter(this, receptArrayList);`\
   ` hlavniListView.setAdapter(adapter);`
* Přesuneme se na stránky hostingu, do správce souborů, manage website->tools->file manager->upload, a ve složce public_html vytvoříme soubor retrieve.php, který nám bude sloužit pro získávání dat z databáze. Vložíme do něho následující kód:\
    `<?php`\
   ` $db_name = "id18087905_mojedatabaze";`  // zase vyplníme údaje o databázi\
    `$mysql_username = "id18087905_realfaid";`\
    `$mysql_pasword = "_Maturitni123";`\
   ` $server_name = "localhost";`\
   ` $connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name);` // připojíme se\
   ` $result = array();`\
   ` $result["recepty"] = array();` // v závorce název databáze\
   ` $select = "SELECT * from recepty";` // sql dotaz \
   ` $responce = mysqli_query($connection,$select);`\
  `  while($row = mysqli_fetch_array($responce))`\
   ` {`\
    `    $index["id"]        = $row["0"];` // uloží data z prvního sloupečku pod názvem "id"\
    `    $index["nazev"]     = $row["1"];`\
    `    $index["suroviny"]  = $row["2"];`\
    `    $index["postup"]    = $row["3"];`\
     `   array_push($result["recepty"], $index); // pushne data `\
  `  }`\
      `   $result["success"]="1"; `\
       `  echo json_encode($result); `\
      `   mysqli_close($connection); `
* Rozklikneme si retrieve.php na view, a zkopírujeme, následně si url uložíme do proměnné url ve třídě MainActivity.\
`String url = "https://sqlprojekt.000webhostapp.com/retrieve.php";`
* Vytvoříme metodu nacistData a budeme ji volat v onCreate. Metoda bude sloužit pro načtení dat z databáze, hned na první obrazovce, při spuštění aplikace\
`...`\
`nacistData();`\
   ` }`\
\
   ` public void nacistData(){`\
`}`
* V metodě si vytvoříme instanci StringRequest s názvem request, v parametrech bude metoda POST a url použijeme naši proměnnou. Další bude new Response.Listener, který když začneme psát a odtabujete, vytvoří se vám struktura sama. Bude to vypadat následovně:\
` public void nactiData(){`\
      `  StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {`\
      `      @Override`\
      `      public void onResponse(String response) {`\
     `       }`\
    `    })`\
  `  }`
* Na začátek vyčístíme arraylist `receptArrayList.clear();`, potom vytvoříme příkazy try a catch, do kterých budeme psát. Pro získávání dat ze serveru budeme používat JSON pro ukládání dat. Vytvoříme JSONObject, proměnnou String, a JSONArray, uložíme do nich následující hodnoty:\
` try{`\
` JSONObject jsonObject = new JSONObject(response);`\
` String sucess = jsonObject.getString("success");` // získá hodnotu "success"\
`  JSONArray jsonArray = jsonObject.getJSONArray("recepty");` // získa si pole "recepty"
* Následně vytvoříme podmínku a do podmínka bude pokud se "success" rovná "1", do podmínky dáme cyklus for, který se bude opakovat podle délky pole "jsonArray"(pole "recepty"), následně nový objekt JSONObject object, který vždy získá objekt podle hodnoty "i" z cyklu(půjde to postupně a vybere všechny objekty z databáze)\
` if(sucess.equals("1")){`\
 ` for(int i=0; i<jsonArray.length();i++){`\
` JSONObject object = jsonArray.getJSONObject(i);`
* Vytvoříme proměnné string id, nazev, suroviny, postup a uložíme do nich hodnoty, které získáme z objektu(položka z databáze). Vytvoříme instanci třídy Recept a hodnoty parametrů budou ty Stringy, které jsme si vytvořili. Následně do našeho ArrayListu receptArrayList přidáme vytvořený objekt třídy Recept. Potom připomeneme adapteru, že se data změnila. Uzavřeme podmínku i try.\
` String id = object.getString("id");` // v závorkách jsou názvy, které jsme psali v "retrieve.php"\
 `  String nazev = object.getString("nazev");`\
`  String suroviny = object.getString("suroviny");`\
`  String postup = object.getString("postup");`\
`   Recept recept = new Recept(id,nazev,suroviny,postup);`\
`   receptArrayList.add(recept);`\
 `   adapter.notifyDataSetChanged();`\
 `  }}}`
* Příkaz catch nám bude "chytat" JSONException, což označuje chybi v JSON, pojmenujeme e, a dovnitř napíšeme ať nám to vypíše tu chybu do, která by nastala, do konzole. Uzavřeme již celý Response.Listener. Na konec ještě vytvoříme Response.ErrorListener s metodou onErrorResponse, která nám vypíše chybu do zprávy Toast. Nevypisuje chybu JSON API, ale chybu requestu.\
` catch(JSONException e){`\
`     e.printStackTrace();`\
`  }}}, `\
`new Response.ErrorListener() {`\
      `     @Override`\
         `   public void onErrorResponse(VolleyError error) {`\
        `        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();`\
          `  }`\
     `   });`
* V poslední části metody nacistData si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".\
`RequestQueue requestQueue = Volley.newRequestQueue(this);`\
   `     requestQueue.add(request);`\
  `  }`

==Rozkliknutí položky v ListView==
* Ve třídě MainActivity, v metodě onCreate, si vytvoříme OnItemClickListener na náš ListView. Do něho vložíme Intent, pro přepnutí do jiné třídy, do Extra přidáme hodnotou pozice("position") položky na kterou jsme kliknuli a následně provedeme.\
 `hlavniListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {`\
           ` @Override`\
           ` public void onItemClick(AdapterView<?> parent, View view, int position, long id) {`\
              `  Intent ht1 = new Intent( view.getContext(), ReceptActivity.class);`\
              `  ht1.putExtra("position", position );`\
              `  view.getContext().startActivity(ht1);`\
          `  }`\
    `    });`
* Vytvoříme si novou třídu ReceptActivity, bude sloužit pro zobrazení dáné položky, na kterou jsme kliknuli. Bude dědit po třídě AppCompatActivity, vytvoříme si metodu onCreate a metody pro zobrazení a nastavení menu.\
 `package com.example.sqlprojekt;`\
`public class ReceptActivity extends AppCompatActivity {`\
    `@Override`\
    `protected void onCreate(Bundle savedInstanceState){`\
    `    super.onCreate(savedInstanceState);`\
   ` }`\
  `  public boolean onCreateOptionsMenu(Menu menu) {`\
  `      MenuInflater inflater = getMenuInflater();`\
  `      inflater.inflate(R.menu.main_menu, menu); //fasfadf adf`\
  `      return true;`\
 `   }`\
  `  public boolean onOptionsItemSelected(@NonNull MenuItem item) {`\
  `      switch (item.getItemId()) {`

   `         case R.id.home:`\
   `             Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);`\
   `             startActivity(ht1);`\
   `             return true;`

    `        default:`\
    `            return super.onOptionsItemSelected(item);`\
   `     }}}`
* Budeme potřebovat si vytvořit xml soubor obrazovky, která bude sloužit pro zobrazení detailu receptu po rozkliknutí. Pojmenujeme ho recept_detail. Budeme v něm potřebovat 3x TextView, pro název receptu, suroviny a postup, dále tlačítko které nás přesměruje na úpravu receptu. Můžeme také přidat nadpisy k jednotlivým informacím o receptu. Všemu nastavíme id, v mém případě to jsou: receptDetailNazev, receptDetailSuroviny, receptDetailPostup a upravitBtn. Textview pro suroviny a recept si můžete vložit do Scrollview.
* Ve třídě ReceptActivity si v metodě onCreate nastavíme náš xml soubor jako view.\
` setContentView(R.layout.recept_detail);`
* Dále si vytvoříme ve třídě proměnné, se kterýma budeme pracovat.\
` TextView detailNazev, detailSuroviny, detailPostup;`\
`int position;`\
`String id2;`\
`Button smazatBtn;`
* V metodě onCreate si definujeme naše TextView a Button z xml souboru a získáme Extras z operace Intent, která nás přepnula do této třídy.
detailNazev = findViewById(R.id.receptDetailNazev);\
       ` detailSuroviny = findViewById(R.id.receptDetailSuroviny);`\
        `detailPostup = findViewById(R.id.receptDetailPostup);`\
       ` smazatBtn = findViewById(R.id.smazatBtn);`\
       ` Intent intent = getIntent();`\
       ` position = intent.getExtras().getInt("position");`
* Dále pomocí Arraylistu ze třídy MainActivity a pozice, kterou jsme si "poslali", uložíme do TextView hodnoty dané položky, na kterou jsme kliknuli.\
        ` id2 = MainActivity.receptArrayList.get(position).getId();`\
        `detailNazev.setText(MainActivity.receptArrayList.get(position).getNazev());`\
        `detailSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());`\
        `detailPostup.setText(MainActivity.receptArrayList.get(position).getPostup());`
* Jako poslední věc ve třídě ReceptActivity si vytvoříme metodu upravit, která nas přesměruje do třídy EditRecept, kterou si zachvíli vytvoříme. Opět využijeme Intent a opět do Extra přidáme hodnotu pozice("position") a spustíme popis operací.\
` public void upravit(View v){`\
    `    Intent ht1 = new Intent( ReceptActivity.this, EditRecept.class);`\
   `     ht1.putExtra("position", position );`\
    `    startActivity(ht1);`\
 `   }`
* Teď už jenom v xml souboru nastavíme pro náš button tuhle metodu na onClick: `android:onClick="upravit"`.

==Mazání dat==
* Pro mázání dat budeme potřebovat další php soubor na našem hostingu. Takže se přesuneme tam a vytvoříme soubor "delete.php". V něm se jako vždy připojíme do databáze, uložíme do přoměnné id, které si pošleme z appky, a následně pres SQL dotaz vymažeme položku podle id.\
` <?php`\
`$db_name = "id18087905_mojedatabaze";`\
`$mysql_username = "id18087905_realfaid";`\
`$mysql_pasword = "_Maturitni123";`\
`$server_name = "localhost";`\
`$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name);`\
`$id = $_POST["id"];`\
`$sql = "DELETE FROM recepty WHERE id='$id'";`\
`$result = mysqli_query($connection, $sql);  `\
`if($result){`\
 `   echo "uspech";`\
`}`\
`else{`\
 `   echo "Selhalo";`\
`}`\
`mysqli_close($connection);`\
`?>`
* Nyní si vytvoříme novou třídu EditRecept, bude stejná jako ostatní třídy, které používame pro zobrazení jiné stránky, bude dědit po třídě AppCompatActivity, vytvoříme metodu onCreate a metody pro zobrazení a práci s menu. (Vše můžeme zkopírovat z jiné třídy a přepsat hodnoty).\
` package com.example.sqlprojekt;`\
`public class EditRecept extends AppCompatActivity`\
`{`\
    `@Override`\
    `protected void onCreate(Bundle savedInstanceState){}`
* Vytvoříme si nový xml soubor, pro edit a mazání dat. Pojmenujeme si ho edit_recept, budeme potřebovat 3x EditText a 2x Button. Nastavíme objektům id(editNazev, editSuroviny, editPostup, ulozBtn, smazatBtn).
* Ve tříde EditRecept. V metodě onCreate nastavíme aby se xml soubor edit_recept nastavil jako view.\
`setContentView(R.layout.edit_recept);`
* Vytvoříme metodu smazatData s parametrem final String id1, kvůli tomu, ať se ví, co mazat. Vnitřek metody bude vypadat velmi podobně jako metoda pro přidávání dat. Použijeme StringRequest, vytvoříme její instanci, s parametry, metody push, url našeho souboru "delete.php", a onResponse a onErrorResponse metody.\
`StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/delete.php",`\
`   new Response.Listener<String>() {`\
`  @Override`\
`  public void onResponse(String response) {`\
`      if(response.equalsIgnoreCase("uspech")){`\
`          Toast.makeText(EditRecept.this, "Data se úspěšně smazala", Toast.LENGTH_SHORT).show();`\
 `           }`\
                     `       else{`\
                     `           Toast.makeText(EditRecept.this, "Chyba, data se nesmazala", Toast.LENGTH_SHORT).show();`\
                     `       }`\
               `    }`\
            `    }, new Response.ErrorListener() {`\
         `   @Override`\
         `   public void onErrorResponse(VolleyError error) {`\
         `       Toast.makeText(EditRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();`\
         `   } })`
* V další části si vytvoříme Mapu hodnot, které budou odeslány pomocí POST, pošleme pomocí ní hodnotu id.\
` {`\
          `  @Override`\
           ` protected Map<String, String> getParams() throws AuthFailureError{`\
             `   Map<String, String> params = new HashMap<String,String>();`\
            `    params.put("id", id1);` //id je pod jakým "názvem" to posílame, id1 je hodnota, kterou posíláme\
          `      return params;`\
         `   }};`
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek “request”.\
 `RequestQueue requestQueue = Volley.newRequestQueue(this);`\
     `   requestQueue.add(request);`\
   ` }`
* Nyní si ve tříde EditRecept vytvoříme objekty EditText edNazev, edSuroviny, edPostup a proměnné String id a int position.\
`int position;`\
  `  String id;`\
  `  EditText edNazev, edSuroviny, edPostup;`
* V metodě onCreate si do EditTextů uložíme naše EditTexty z xml souboru, vytvoříme objekt intent, do kterého získame Intent, získáme z tama hodnotu pozice a následně hodnotu proměnné id a jako text EditTextů nastavíme hodnoty, dané položky, podle pozice.\
`Intent intent = getIntent();`\
       `position = intent.getExtras().getInt("position");`\
       ` id = MainActivity.receptArrayList.get(position).getId();`\
       ` edNazev.setText(MainActivity.receptArrayList.get(position).getNazev());`\
        `edSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());`\
       ` edPostup.setText(MainActivity.receptArrayList.get(position).getPostup());`
* Ve tříde EditRecept vytvoříme novou metodu smazData s parametry View view, která bude sloužit pro zavolání metody smazatData s parametrem id, které získáváme v onCreate metodě. A následně nás přepne zpět do třídy MainActivity a na hlavní stránku.\
` public void smazData(View v){`\
        `smazatData(id);`\
       ` Intent ht1 = new Intent( EditRecept.this, MainActivity.class);`\
       ` startActivity(ht1);`\
   ` }`
* Na konec nastavíme v xml souboru edit_recept, Buttonu na odstraněním, metodu smazData jako onClick.\
`android:onClick="smazData"`


