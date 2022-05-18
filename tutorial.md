## Tutoriál pro vytvoření příkladové aplikace pro práci s SQL databází.
* Budeme používat vývojové prostředí "Android studio" a webhosting "000webhost",
* Příkladovou aplikací bude veřejná kuchařka, uživatel do ní bude moct přidat recept, upravovat recepty a mazat recepty.

### Co budete potřebovat.
*  Pro vytvoření mobilní aplikace podle mého tutoriálu budete potřebovat:
    * Připojení k internetu
    * Android Studio
    * Chuť programovat  

## Vytvoření projektu a základního layoutu
* V android studiu si vytvoříme nový projekt (v horní liště "file->new->New project") a&nbsp;vybereme&nbsp;"Empty&nbsp;Activity".

    ![novy-projekt](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/file.PNG?raw=true)
    
    ![empty-activity](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/empty_acitivty.PNG?raw=true)

* Zadáme název (Name) našeho projektu (v mém případě "sqlprojekt"), jazyk (Language) vybereme "Java" a&nbsp;minimální SDK (Minimal SDK), doporučoval bych API androidu 5 nebo 6, aby šla aplikace spustit na&nbsp;většině mobilních zařízeních. Klikneme na finish pro vytvoření a počkáme než se projekt vytvoří.

    ![vytvoreni-projektu](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/projekt-nazev.PNG?raw=true)
    
* Nyní si vytvoříme vzhled hlavní stránky, v levém panelu přejdeme do "res->layout->activity_main.xml", v&nbsp;pravé horní části si můžete vybrat, jak chcete xml soubor zobrazovat, doporučuji používat zobrazení "Design" pro přidání objektů na&nbsp;stránku pomocí přetahování objektů z části vlevo od levé obrazovky, a&nbsp;potom používat "Split" na následnout úpravu objektů pomocí kódu. Nezapomeňte každý objekt po&nbsp;přidání ukotvit pomocí teček na stranách objektu.

    ![novy-xml](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/nove_xml.png?raw=true)
* Na stránku přidáme tlačítko (Button), tlačítko bude sloužit pro zobrazení stránky na přidávání dat, přejdeme na verzi zobrazení "Split" a nastavíme tlačítku hodnotu atributu "id" (např.&nbsp;`android:id="@+id/btnAddRecept"`) a hodnotu atributu "text"(text který má na&nbsp;tlačítku být) (např.`android:text="Přidat recept"`), v atributech layoutu, na začátku kódu, necháme `tools:context=".MainActivity"`, pokud tomu tak není, připíšeme. To nám zařídí se kterou aktivitou je tenhle layout spojen. Můžeme také přidat TextView pro nadpis.
 
    ![hlavni-xml](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/hlavni_clear.png?raw=true)

* Přejdeme do třídy MainActivity a v metodě "onCreate" si uvedeme jaký layout cheme zobrazit: `setContentView(R.layout.activity_main);`-"activity_main" je náš xml soubor s tlačítkem.
* V tříde "MainAcvitiy" vytvoříme novou metodu "addRecept" s parametrem "View view" (v našem případě to bude instance tlačítka které bude tuto metodu volat.)`public void addRecept(View v){  }`pokud nám "View" bude házet chybu a svítit červeně, stačí na něj najet myší a zmáčknout "alt+shift+enter" pro&nbsp; importování.
* Vytvoříme novou třídu "AddReceptActivity", která bude sloužit na přidání receptu a zobrazení stránky pro přidání, klikneme pravým na složku, ve které je i "MainActivity.java"&nbsp;("java->com.example.sqlprojekt") dáme "New->Java class" a pojmenujeme "AddReceptActivity".
 
    ![nova-trida](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/nova_trida.png?raw=true)

* Každou třídu, která bude sloužit jak aktivita pro zobrazení nějaké stránky, musíme definovat v manifestu ("manifests->AndroidManifest.xml"). Definujeme to pomocí nové "activity" `<activity android:name=".AddReceptActivity"/>`- .AddReceptActivity - je název třídy.

    ![manifest](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/manifest1.PNG?raw=true)

* Nastavíme třídu aby dědila od třídy "AppCompatActivity", je to základní třída pro aktivity, která se automaticky nachází ve vytvořeném projektu, dědičnost nastavíme pomocí "extends".
`public class MainActivity extends AppCompatActivity{`.
* Nyní potřebujeme vytvořit nový layout pro přidávání, klikneme pravým na složku, ve které se již nachází "activity_main.xml" ("res->layout"), klikneme na "New->Layout Resource File", pojmenujeme si to (v mém případě "add_recept") a klikneme na "OK" pro vytvoření.
* Pomocí zobrazení "Design" si na stránku si přidáme 3 objekty "Plain Text" (v kódu jako "EditText") a jedno tlačítko ("Button"), opět nezapomenem objekty ukotvit.

     ![pridat-xml](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/pridat-xml.PNG?raw=true)

* Přejdeme na zobrazení "Split" nebo "Code" a nastavíme opět objektům své id (např. "editTextNazev", "editTextSuroviny", "editTextPostup", "btnConfirmAddRecept"), nastavíme tlačítku opět nějaký text (`android:text="Přidat"`), políčka na psaní budou sloužit pro zapsání hodnot dat a tlačítko bude sloužit na odeslání dat do databáze. (Více v další části tutoriálu)
* Spojíme layout se třídou AddReceptActivity pomocí `tools:context=".AddRecept"`, které napíšeme nahoru do atributů layoutu.
* Vrátíme se do třídy "AddRecept", kde si vytvoříme metodu "onCreate", stejně jako je ve tříde "MainActivity" (můžeme ji zkopírovat), a nastavíme zobrazení layoutu, který jsme si vytvořili ("add_recept.xml") `setContentView(R.layout.add_recept);`
* Teď se přesuneme do třídy "MainActivity" a nastavíme co má metoda "addRecept" dělat. Bude sloužit k přepnutí třídy z "MainActivity" na "AddRecept".
* To zařídíme pomocí třídy Intent (abstraktní popis operace, která má být provedena), který použíjeme s metodou "startActivity()", která slouží ke spuštění aktivity. Náš kód bude vypadat takhle: 
`Intent intent1 = new Intent(MainActivity.this, AddRecept.class);`- vytvoříme instanci třídy Intent kterou si pojmenujeme např. "intent1", jako první atribut píšeme třídu ve které se nacházíme, jako druhý atribut, třídu na kterou chceme přepnout, a následně spustíme pomocí "startActivity". `startActivity(intent1);`
* Nyní přejdeme do xml souboru "activity_main.xml" a tlačítku nastavíme atribut "onClick", hodnotou bude metoda, kterou má tlačítko zavolat, při kliknutí. V našem případě to bude `android:onClick="addRecept"`(Tlačítko zavolá metodu "addRecept" z třídy se kterou je layout spojen, v našem případě třída "MainActivity".
* Teď umí naše aplikace po kliknutí na tlačítko "Přidat recept" zobrazit stránku na přidávání, ale musíme vyřešit jak se z ní dostat, to vyřešíme pomocí menu.

#### Vytvoření menu a zobrazování menu
* Ve složce "res" vytvoříme složku ("Directory") "menu", do složky vytvoříme "Menu Resource File" pojmenovaný "main_menu".

    ![menu-soubory](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/menu_soubory.PNG?raw=true)

* Pomocí zobrazení "Design" přidáme do xml souboru "Menu Item" a přejdeme do kódu. Hodnota atributu "android:title" slouží jako text položky, který se zobrazí v menu. V našem případě nastavíme na "Domů", nastavíme položce atribut "id" `android:id="@+id/home"` a dále přidáme atribut, aby byla položka vždy zobrazena `app:showAsAction="always"`.

    ![menu-xml](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/menu.PNG?raw=true)

* Menu budeme zobrazovat v kazdé třídě zvlášť a to pomocí následující metody:

```java
public boolean onCreateOptionsMenu(Menu menu){ 
MenuInflater inflater = getMenuInflater(); 
inflater.inflate(R.menu.main_menu, menu);  //main_menu" je název našeho menu
return true;}
```

* kód voláme ve třídě, kde potřebujeme menu zobrazit, v našem případě to bude v&nbsp;"AddReceptActivity" 

* Nastavení položek v menu, taky v každé třídě zvlášť společně se zobrazením menu

```java
public boolean onOptionsItemSelected(@NonNull MenuItem item) { 
switch (item.getItemId()) {  
case R.id.home:  //"home" je "id", které jsme si nastavili pro naše menu
Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);  //Na prvním místě třída, kde se nacházíme, na druhém místě, kam chceme.
startActivity(ht1);   
return true;  
default:  
return super.onOptionsItemSelected(item);  } }
```

* pomocí metody nastavíme položkám v menu, "co mají dělat". Položce "Domů", která má "id=home" nastavíme, že má přepnout na třídu "MainActivity".

## Vytvoření databáze a připojení k ní
* Databázi budeme provozovat na "https://cz.000webhost.com/", zaregistrujte se, proklikejte úvodní obrazovky, následně si pojmenujte projekt a zadejte heslo.
 
    ![hosting-projekt](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/hosting-projekt.PNG?raw=true)

* Poté přejdeme na "Manage website" v levé liště "Tools" a následně "Database manager". 
 
     ![database-manager](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/database-manager.png?raw=true)

* Vytvoříme si novou databázi, vyplníme údaje, klikneme na "manage database" a vybereme "phpmyadmin". 
 
    ![vytvoreni-databaze](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/vytvoreni-databaze.png?raw=true)

* V naší databázi si vytvoříme novou tabulku, kterou budeme používat. V našem případě tabulka "recepty" se sloupci "id", "nazev", "suroviny", "postup". "id" bude typ int a bude auto-increment, zbytek typ text. Bude vypadat takhle:
 
    ![tabulka](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/databaze.png?raw=true)

* ### Pro komunikaci s databází budeme používat webové API pomocí PHP souborů, uložených na stejném hostingu. Z&nbsp;aplikace tedy nebudeme komunikovat přímo s databází, ale s PHP souborem s potřebnými parametry. PHP soubor se následně spojí s databází.


* Přejdeme v levé liště na "File manager", klikneme na upload files a ve složce "public_html" vytvoříme soubor "insert.php"

    ![novy-soubor](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/novy-soubour.png?raw=true)

* Klikneme pravým tlačítkem myši a klikneme na edit. Zde nastavíme údaje pro připojení k databázi a sql dotaz pro vložení dat do databáze.

    ![insert-edit](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/insert-edit.png?raw=true)

```php
<?php 
$db_name = "id18087905_mojedatabaze";  // název databáze
$mysql_username = "id18087905_realfaid"; // jména nás jako uživatele
$mysql_pasword = "heslo"; // naše heslo k databázi
$server_name = "localhost"; // název serveru na kterém to jede
$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name); // slouží k připojení k databázi
$nazev = $_POST["nazev"]; // do proměnné $nazev uloží data které se odešlou z aplikace
$suroviny = $_POST["suroviny"]; // do proměnné $suroviny uloží data které se odešlou z aplikace
$postup = $_POST["postup"]; // do proměnné $postup uloží data které se odešlou z aplikace
$sql = "INSERT INTO recepty(nazev, suroviny, postup) VALUES('$nazev', '$suroviny', '$postup')"; // sql dotaz na vložení dat
$result = mysqli_query($connection,$sql); // výsledek připojení a následného dotazu
if($result){
    echo "Data vložena"; // pokud se podaří vše vypíše se "Data vložena"
}
else{
    echo "chyba"; // pokud se něco nepodaří vypíše to chyba
}`
mysqli_close($connection); // ukončení připojení k databázi
?>
```

* Uložíme a zavřeme.
* Jestli nám vše funguje, jak má, můžeme otestovat, klikneme pravým na soubor "insert.php" a vybereme "View", pokud nám to vyhodí hlášku, kterou jsme si nastavili pokud vše klapne, je to vpořádku, pokud nám to vyhodí hlášku, kterou jsem si nastavili, když něco neklapne, něco je nejspíš špatně napsané.
 
    ![soubor-view](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/soubor-view.png?raw=true)

## Vložení dat v aplikaci
* Ve tříde AddRecept, kterou již máme vytvořenou, vytvoříme onCreate metodu a nastavíme layout, který jsme si vytvořili(Můžeme si pomoct a urychlit čas a zkopírovat to ze třídy MainActivity). Zkopírujeme si také kód pro zobrazení menu a ovládání položek v něm(onCreateOptionsMenu a onOptionsItemSelected).

* Když máme vytvořenou třídu i metodu onCreate nadefinujeme si Button a EditTexty, se kterýma budeme pracovat, následovně:

```java
public class AddRecept extends AppCompatActivity {
EditText txtname, txtresources, txtprocess; // pojmenujeme si podle sebe
Button confirmRecept; // pojmenujeme si podle sebe
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.add_recept);
txtname = findViewById(R.id.editReceptName); // definujeme podle ID
txtresources = findViewById(R.id.editReceptResources); // definujeme podle ID
txtprocess = findViewById(R.id.editReceptProcess); // definujeme podle ID
confirmRecept = findViewById(R.id.btnConfirmAddRecept); // definujeme podle ID
```
* V metodě onCreate vytvoříme OnClickListener pro náš button a do něho provedení metody "insertData", kterou zachvíli i vytvoříme:
```java
confirmRecept.setOnClickListener(new View.OnClickListener()  {
@Override
public void onClick(View v) {
insertData();
}
```

* Vytvoříme metodu insertData, vytvoříme proměnné String, do kterých se bude ukládat text napsaný v EditTextech:

```java
public void insertData() { 
String nazev = txtname.getText().toString().trim();  // do proměnné se nám uloží text z EditTextu txtname
String suroviny = txtresources.getText().toString().trim(); 
String postup = txtprocess.getText().toString().trim(); 
```

* Teď si vytvoříme podmínky pro kontrolu, zda je v EditTextu něco napsané, to uděláme tak, že pokud proměnná textu(nazev, suroviny a postup) je prázdná, vypíše se, že je políčko prázdné, bude to vypadat nějak takhle:

```java
if (nazev.isEmpty()) {
    Toast.makeText(this, "Zadej název", Toast.LENGTH_SHORT).show();
    return;
} else if (suroviny.isEmpty()) {
    Toast.makeText(this, "Zadej Suroviny", Toast.LENGTH_SHORT).show();
    return;
} else if (postup.isEmpty()) {
    Toast.makeText(this, "Zadej Postup", Toast.LENGTH_SHORT).show();
    return;
}
```

* Pokud ani jedna z podmínek nenastane, nastavíme co se má stát, a to bude samotné odeslání dat. Bude to vypadat nějak takhle a následně si to popíšeme:

* V první části si vytvoříme StringRequest na načtení kódu ze souboru na internetu, určíme metodu POST(slouží pro odesílaní dat) a zadáme url našeho insert souboru, tu získáme když klikneme pravým tlačítkem myši na soubor, vybereme view a zkopírujeme url adresu, Potom vytvoříme metodu onResponse s parametrem String response, nastavíme: pokud se výsledek bude rovnat "Data vložena"(To co jsme si psali v souboru "insert.php") tak nám to napíše "Data Vložena" - to znamená, že se vše provedlo vpořádku, a přepne nás to zpět do třídy MainActivity , pokud ne, vypíše se "chybička".

```java
else{
    StringRequest request = new StringRequest(Request.Method.POST,"https://sqlprojekt.000webhostapp.com/insert.php",
new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
        if(response.equalsIgnoreCase("Data vložena")) {
            Toast.makeText(AddRecept.this, "Data vložena", Toast.LENGTH_SHORT).show();
            Intent ht1 = new Intent(AddRecept.this, MainActivity.class);
            startActivity(ht1);
        }
        else{
            Toast.makeText(AddRecept.this, "chybička", Toast.LENGTH_SHORT).show();
        }}
```

* Přidáme taky ErrorListener s metodou onErrorResponse, aby nám to v případě chyby, vypsalo tu chybu, díky čemu to pro nás bude jednodušší opravit.

```java
}, 
new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(AddRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
}})
```
* V další části si vytvoříme Mapu hodnot, které budou odeslány pomocí POST, pošleme pomocí ní hodnoty pro název, suroviny a postup.
```java
{
@Override
protected Map<String, String> getParams() throws AuthFailureError {
    Map<String,String> params = new HashMap<String,String>();
    params.put("nazev",nazev); //na prvním místě v závorce = jak se pojmenuje, druhé místo = obsah.
    //Na prvním místě máme hodnoty které jsme použili i v "insert.php"
    params.put("suroviny",suroviny);
    params.put("postup",postup);
    return params;
}
```
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".
```java        
};
RequestQueue requestQueue = Volley.newRequestQueue(AddRecept.this);
requestQueue.add(request);
}
```
## Výpis dat do Listview
* Jako první se na naší první stránku musíme přidat ListView, takže v xml souboru, který používáme na hlavní stránce, přidáme ListView, ukotvíme a nastavíme id.

    ![listview](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/listview-pridat.png?raw=true)

* Nyní si vytvoříme novou trídu s názvem Recept, bude to náš model, pro práci s databází. V něm si vytvoříme proměnné String, konstruktor a metody pro získávání a nastavování hodnot(gettery a settery), gettery a setter můžeme vygenerovat pomocí kliknutí pravým tlačítkem myši, následně klikneme na generate a pak na "Getter and Setter". Bude to vypadat takhle:
```java
public class Recept {
    private String id,nazev,suroviny,postup;
    public Recept() {
    }
    public Recept(String id, String nazev, String suroviny, String postup) {
        this.id = id;
        this.nazev = nazev;
        this.suroviny = suroviny;
        this.postup = postup;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNazev() {
        return nazev;
    }
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    public String getSuroviny() {
        return suroviny;
    }
    public void setSuroviny(String suroviny) {
        this.suroviny = suroviny;
    }
    public String getPostup() {
        return postup;
    }
    public void setPostup(String postup) {
        this.postup = postup;
    }
}
```
* Bude to fungovat tak, že při vytvoření instanci třídy, budeme psát jako parametry, hodnoty které získáme z databáze a ty se uloží do proměnných třídy Recept, poté už jen budeme používat metody pro získávání dat, případně nastavení nové hodnoty.

* Nyní si vytvoříme xml soubor, který se bude zobrazovat v ListView. Při vytváření si pojmenujeme soubor jako "recept_item" a Root element nastavíme LinearLayout. Když máme soubor vytvořený, v kódu přepíšeme `android:orientation` layoutu na `horizontal` a `android:layout_height` na `wrap_content`, nyní si do layoutu přidáme TextView a nastavíme id("vypisReceptNazev"). Bude to vypadat takhle:
 
    ![recept-item](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/recept-item.PNG?raw=true)

```java
android:orientation="horizontal"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:padding="8dp">`

<TextView
android:id="@+id/vypisReceptNazev"
android:layout_width="342dp"
android:layout_height="69dp"
android:gravity="center_vertical|center"
android:text="nazev"
android:textSize="20dp" />
```
* Jako další budeme potřebovat adapter, takže vytvoříme novou třídu a pojmenujeme ji ReceptAdapter, ta bude sloužit pro zobrazení položek v ListView. Třída bude dědit ArrayAdapter s modelem Recept : 
```java
public class ReceptAdapter extends ArrayAdapter<Recept>
```
* Vytvoříme objekt Context a pojmenujeme context a objekt List třídy Recept a pojmenujeme arrayListRecept:
```java
Context context;
List<Recept> arrayListRecept;
```
* Jako další vytvoříme konstruktor s parametrama Context context a List<Recept> arrayListRecept, taky nastavíme aby to neprošlo pokud hodnota context bude nulová(null) a to pomocí @NonNull před conext, konstruktor nastaví pro arrayList položku pro zobrazování pomocí super, také uloží zadané hodnoty při volání třídy do objektů context a arraylist ve třídě.
 ```java
public ReceptAdapter(@NonNull Context context, List<Recept> arrayListRecept) {
    super(context, R.layout.recept_item,arrayListRecept); // recept_item - název xml souboru pro položku v listview
    this.context = context;
    this.arrayListRecept = arrayListRecept;
}
```
* Vytvoříme metodu getView s parametry `int position`, `View convert`(před tím @Nullable - může být obsah nulový) a `ViewGroup parent`(před tím @NonNull). metoda bude používat LayoutInflater, který převezme náš "recept_item.xml" soubour a vytvoří různé objekty zobrazení. Dále nastavíme pro TextView název daného receptu podle pozice `position` a vrátíme hodnotu view.
```java
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recept_item, null, true);
    TextView vypisNazev = view.findViewById(R.id.vypisReceptNazev);
    vypisNazev.setText(arrayListRecept.get(position).getNazev());
    return view;
}
```
* Přejdeme to třídy MainActivity. Vytvoříme si nový objekt ListView, uložíme do něj náš listview podle id, dále objekt adapter, do kterého uložíme uložíme instanci našeho adaptéru. Vytvoříme si ArrayList pro třídu Recept.
Do třídy:
```java
ListView hlavniListView;
ReceptAdapter adapter;
public static ArrayList<Recept> receptArrayList = new ArrayList<>();
```
* Do metody onCreate:
```java
hlavniListView = findViewById(R.id.myListView);
adapter = new ReceptAdapter(this, receptArrayList);
hlavniListView.setAdapter(adapter);
```
#### Vytvoření souboru na stránkách hostitele
* Přesuneme se na stránky hostingu, do správce souborů, manage website->tools->file manager->upload, a ve složce public_html vytvoříme soubor retrieve.php, který nám bude sloužit pro získávání dat z databáze. Klikneme pravým tlačítkem myši, dáme edit a vložíme do něho následující kód:

 ![retrieve-edit](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/retrieve-edit.png?raw=true)

```php
<?php
$db_name = "id18087905_mojedatabaze";  // zase vyplníme údaje o databázi
$mysql_username = "id18087905_realfaid";
$mysql_pasword = "_Maturitni123";
$server_name = "localhost";
$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name); // připojíme se
$result = array();
$result["recepty"] = array(); // v hranatých závorkách název databáze
$select = "SELECT * from recepty" // sql dotaz 
$responce = mysqli_query($connection,$select);
while($row = mysqli_fetch_array($responce))
{
    $index["id"]        = $row["0"]; // uloží data z prvního sloupečku pod názvem "id"
    $index["nazev"]     = $row["1"];
    $index["suroviny"]  = $row["2"];
    $index["postup"]    = $row["3"];
    array_push($result["recepty"], $index); // pushne data 
}
$result["success"]="1"; 
echo json_encode($result); 
mysqli_close($connection); 
```
* Rozklikneme si retrieve.php na view, a zkopírujeme, následně si url adresu uložíme do proměnné url kterou si vytvoříme ve tříde MainActivity.
 
    ![retrieve-view](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/retrieve-view.png?raw=true)

```java
String url = "https://sqlprojekt.000webhostapp.com/retrieve.php";
```

* Vytvoříme metodu nacistData a budeme ji volat v metodě onCreate. Metoda bude sloužit pro načtení dat z databáze, hned na první obrazovce, při spuštění aplikace

```java
//... předchozí kód metody onCreate
nacistData();
}

public void nacistData(){
}
```

* V metodě si vytvoříme instanci StringRequest s názvem request, v parametrech bude metoda POST a url použijeme naši proměnnou. Další bude new Response.Listener, který když začneme psát a odtabujete, vytvoří se vám struktura sama. Bude to vypadat následovně:

```java
public void nactiData(){
    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
        }
    })
}
```
* Na začátek vyčístíme arraylist `receptArrayList.clear();`, potom vytvoříme příkazy try a catch, do kterých budeme psát. Pro získávání dat ze serveru budeme používat JSON pro ukládání dat. Vytvoříme JSONObject, proměnnou String, a JSONArray, uložíme do nich následující hodnoty:
```java
try{
    JSONObject jsonObject = new JSONObject(response);
    String sucess = jsonObject.getString("success"); // získá hodnotu "success"
    JSONArray jsonArray = jsonObject.getJSONArray("recepty"); // získa si pole "recepty"
```
* Následně vytvoříme podmínku a do podmínka bude pokud se "success" rovná "1", do podmínky dáme cyklus for, který se bude opakovat podle délky pole "jsonArray"(pole "recepty"), následně nový objekt JSONObject object, který vždy získá objekt podle hodnoty "i" z cyklu(půjde to postupně a vybere všechny objekty z databáze)
```java
    if(sucess.equals("1")){
        for(int i=0; i<jsonArray.length();i++){
            JSONObject object = jsonArray.getJSONObject(i);
```
* Vytvoříme proměnné string id, nazev, suroviny, postup a uložíme do nich hodnoty, které získáme z objektu(položka z databáze). Vytvoříme instanci třídy Recept a hodnoty parametrů budou ty Stringy, které jsme si vytvořili. Následně do našeho ArrayListu receptArrayList přidáme vytvořený objekt třídy Recept. Potom připomeneme adapteru, že se data změnila. Uzavřeme podmínku i try.
```java
            String id = object.getString("id"); // v závorkách jsou názvy, které jsme psali v "retrieve.php"
            String nazev = object.getString("nazev");
            String suroviny = object.getString("suroviny");
            String postup = object.getString("postup");
            Recept recept = new Recept(id,nazev,suroviny,postup);
            receptArrayList.add(recept);
            adapter.notifyDataSetChanged();
        }
    }
}
```
* Příkaz catch nám bude "chytat" JSONException, což označuje chybi v JSON, pojmenujeme e, a dovnitř napíšeme ať nám to vypíše tu chybu do, která by nastala, do konzole. Uzavřeme již celý Response.Listener. Na konec ještě vytvoříme Response.ErrorListener s metodou onErrorResponse, která nám vypíše chybu do zprávy Toast. Nevypisuje chybu JSON API, ale chybu requestu.
```java
    catch(JSONException e){
    e.printStackTrace();
    }
}}, 
new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
);
```
* V poslední části metody nacistData si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".\
```java
RequestQueue requestQueue = Volley.newRequestQueue(this);
requestQueue.add(request);
}
```
## Rozkliknutí položky v ListView
* Ve třídě MainActivity, v metodě onCreate, si vytvoříme OnItemClickListener na náš ListView. Do něho vložíme Intent, pro přepnutí do jiné třídy, do Extra přidáme hodnotou pozice("position") položky na kterou jsme kliknuli a následně provedeme.\
```java
    hlavniListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent ht1 = new Intent( view.getContext(), ReceptActivity.class);
            ht1.putExtra("position", position );
            view.getContext().startActivity(ht1);
        }
    });
```
* Vytvoříme si novou třídu ReceptActivity, bude sloužit pro zobrazení dáné položky, na kterou jsme kliknuli. Bude dědit po třídě AppCompatActivity, vytvoříme si metodu onCreate a metody pro zobrazení a nastavení menu.
```java
package com.example.sqlprojekt;
public class ReceptActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu); 
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                Intent ht1 = new Intent(ReceptActivity.this, MainActivity.class);
                startActivity(ht1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
```
* Budeme potřebovat si vytvořit xml soubor obrazovky, která bude sloužit pro zobrazení detailu receptu po rozkliknutí. Pojmenujeme ho recept_detail. Budeme v něm potřebovat 3x TextView, pro název receptu, suroviny a postup, dále tlačítko které nás přesměruje na úpravu receptu. Můžeme také přidat nadpisy k jednotlivým informacím o receptu. Všemu nastavíme id, v mém případě to jsou: receptDetailNazev, receptDetailSuroviny, receptDetailPostup a upravitBtn. Textview pro suroviny a recept si můžete vložit do Scrollview.
 
    ![recept-detail](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/recept-xml.PNG?raw=true)

* Do Scrollview se to vkládá následujícím způsobem
 ```java
<ScrollView
        ...
        ...
        <TextView
            ...
        />
        ...
        ...
    </ScrollView>
```
* Ve třídě ReceptActivity si v metodě onCreate nastavíme náš xml soubor na zobrazení.
```java
    setContentView(R.layout.recept_detail);
```
* Dále si vytvoříme ve třídě proměnné, se kterýma budeme pracovat.
```java
TextView detailNazev, detailSuroviny, detailPostup;
int position;
String id2;
Button smazatBtn;
```
* V metodě onCreate si definujeme naše TextView a Button z xml souboru a získáme Extras z operace Intent, která nás přepnula do této třídy.
```java
detailNazev = findViewById(R.id.receptDetailNazev);
detailSuroviny = findViewById(R.id.receptDetailSuroviny);
detailPostup = findViewById(R.id.receptDetailPostup);
smazatBtn = findViewById(R.id.smazatBtn);
Intent intent = getIntent();
position = intent.getExtras().getInt("position");
```
* Dále pomocí Arraylistu ze třídy MainActivity a pozice, kterou jsme si "poslali", uložíme do TextView hodnoty dané položky, na kterou jsme kliknuli.
```java
id2 = MainActivity.receptArrayList.get(position).getId();
detailNazev.setText(MainActivity.receptArrayList.get(position).getNazev());
detailSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());
detailPostup.setText(MainActivity.receptArrayList.get(position).getPostup());
```
* Jako poslední věc ve třídě ReceptActivity si vytvoříme metodu upravit, která nas přesměruje do třídy EditRecept, kterou si zachvíli vytvoříme. Opět využijeme Intent a opět do Extra přidáme hodnotu pozice("position") a spustíme popis operací.
```java
public void upravit(View v){
    Intent ht1 = new Intent( ReceptActivity.this, EditRecept.class);
    ht1.putExtra("position", position );
    startActivity(ht1);
}
```
* Teď už jenom v xml souboru nastavíme pro náš button tuhle metodu na onClick: `android:onClick="upravit"`.

## Edit a mazání dat
#### Vytvoření třídy, xml souboru, proměnných, mazání dat
* Pro mázání dat budeme potřebovat další php soubor na našem hostingu. Takže se přesuneme tam a vytvoříme soubor "delete.php". Klikneme pravým tlačítkem a dáme edit. V něm se jako vždy připojíme do databáze, uložíme do přoměnné id, které si pošleme z appky, a následně pres SQL dotaz vymažeme položku podle id.

    ![delete-edit](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/delete-edit.png?raw=true)

```php
<?php
$db_name = "id18087905_mojedatabaze";
$mysql_username = "id18087905_realfaid";
$mysql_pasword = "_Maturitni123";
$server_name = "localhost";
$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name);
$id = $_POST["id"];
$sql = "DELETE FROM recepty WHERE id='$id'";
$result = mysqli_query($connection, $sql);  
if($result){
    echo "uspech";
}
else{
    echo "Selhalo";
}
mysqli_close($connection);
?>
```
* Nyní si vytvoříme novou třídu EditRecept, bude stejná jako ostatní třídy, které používame pro zobrazení jiné stránky, bude dědit po třídě AppCompatActivity, vytvoříme metodu onCreate a metody pro zobrazení a práci s menu. (Vše můžeme zkopírovat z jiné třídy a přepsat hodnoty).
```java
package com.example.sqlprojekt;
public class EditRecept extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){}
```
* Vytvoříme si nový xml soubor, pro edit a mazání dat. Pojmenujeme si ho edit_recept, budeme potřebovat 3x EditText a 2x Button. Nastavíme objektům id(editNazev, editSuroviny, editPostup, ulozBtn, smazatBtn).
 
    ![edit-xml](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/edit%20xml.PNG?raw=true)

* Ve tříde EditRecept. V metodě onCreate nastavíme aby se xml soubor edit_recept nastavil jako view.
```java
setContentView(R.layout.edit_recept);
```
* Vytvoříme metodu smazatData s parametrem final String id1, kvůli tomu, ať se ví, co mazat. Vnitřek metody bude vypadat velmi podobně jako metoda pro přidávání dat. Použijeme StringRequest, vytvoříme její instanci, s parametry, metody push, url našeho souboru "delete.php", a onResponse a onErrorResponse metody.
```java
public void smazatData(final String id1){
StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/delete.php",
new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
        if(response.equalsIgnoreCase("uspech")){
            Toast.makeText(EditRecept.this, "Data se úspěšně smazala", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(EditRecept.this, "Chyba, data se nesmazala", Toast.LENGTH_SHORT).show();
        }
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(EditRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
    } 
})
```
* V další části si vytvoříme Mapu hodnot, které budou odeslány pomocí POST, pošleme pomocí ní hodnotu id.
```java
    {
    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        Map<String, String> params = new HashMap<String,String>();
        params.put("id", id1); //id je pod jakým "názvem" to posílame, id1 je hodnota, kterou posíláme
        return params;
    }
};
```
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek “request”.
```java
RequestQueue requestQueue = Volley.newRequestQueue(this);
requestQueue.add(request);
}
```
* Nyní si ve tříde EditRecept vytvoříme objekty EditText edNazev, edSuroviny, edPostup a proměnné String id a int position.
```java
int position;
String id;
EditText edNazev, edSuroviny, edPostup;
```
* V metodě onCreate si do EditTextů uložíme naše EditTexty z xml souboru, vytvoříme objekt intent, do kterého získame Intent, získáme z tama hodnotu pozice a následně hodnotu proměnné id a jako text EditTextů nastavíme hodnoty, dané položky, podle pozice.
```java
Intent intent = getIntent();
position = intent.getExtras().getInt("position");
id = MainActivity.receptArrayList.get(position).getId();
edNazev.setText(MainActivity.receptArrayList.get(position).getNazev());
edSuroviny.setText(MainActivity.receptArrayList.get(position).getSuroviny());
edPostup.setText(MainActivity.receptArrayList.get(position).getPostup());
```
* Ve tříde EditRecept vytvoříme novou metodu smazData s parametry View view, která bude sloužit pro zavolání metody smazatData s parametrem id, které získáváme v onCreate metodě. A následně nás přepne zpět do třídy MainActivity a na hlavní stránku.\
```java
public void smazData(View v){
    smazatData(id);
    Intent ht1 = new Intent( EditRecept.this, MainActivity.class);
    startActivity(ht1);
}
```
* Na konec nastavíme v xml souboru edit_recept, Buttonu na odstraněním, metodu smazData jako onClick.
```java
android:onClick="smazData"
```

#### Edit dat
* Pro úpravu budeme potřebovat další php soubor na našem hostingu. Ve složce public_html si vytvoříme soubor "update.php", klikneme pravým tlačítkem myši, klikneme na edit a kód bude následující:

    ![update-edit](https://github.com/realfaid/SQLDatabase/blob/main/screenshots/update-edit.png?raw=true)

```php
<?php
$db_name = "id18087905_mojedatabaze"; //údaje databáze
$mysql_username = "id18087905_realfaid";
$mysql_pasword = "_Maturitni123";
$server_name = "localhost";
$connection = mysqli_connect($server_name, $mysql_username, $mysql_pasword, $db_name); //připojení k databázi

$id = $_POST["id"];  // do proměnné $id uloží data které se odešlou z aplikace
$nazev = $_POST["nazev"];
$suroviny = $_POST["suroviny"];
$postup = $_POST["postup"];

$sql = "UPDATE recepty SET nazev = '$nazev', suroviny = '$suroviny', postup = '$postup' WHERE id = '$id' "; // aktualizuje(přepíše) hodnoty názvu, surovin a postupu podle id

$result = mysqli_query($connection,$sql);

if($result){
    echo "Data se aktualizovala"; //pokud se vše provede vypíše se "Data se aktualizovala"
}
else{
    echo "chybaaa"; //v opačném případě "chybaaa"
}
```
* Uložíme a zavřeme, když klikneme na soubor pravým tlačítkem na myši a vybereme "view", tak pokud se nám vypíše "Data se aktualizovala", vše se provedlo v pořádku(připojení, dotaz), pokud nám to napíše "chybaaa", někde nastala chyba.

* Na úpravu dat si vytvoříme ve třídě EditActivity novou metodu updateData s parametry View view. Metoda se bude volat po stiknutí tlačítka. Metoda bude hodně podobná metodě pro přidávání dat, bude navíc posílat id.
```java
public void updateData(View v){}
```
* V první části metody si z EditTextů uložíme text do nových proměnných String.
```java
public void updateData(View v){
    String novyNazev = edNazev.getText().toString();
    String noveSuroviny = edSuroviny.getText().toString();
    String novyPostup = edPostup.getText().toString();
}
```
* Dále do metody přidáme StringRequest na načtení kódu ze souboru na internetu, určíme metodu POST a zadáme url adresu našeho update souboru, tu získáme když klikneme pravým tlačítkem myši na soubor, vybereme View a zkopírujeme. Potom vytvoříme metodu onResponse s parametrem String respons, nastavíme: pokud se výsledek bude rovnat "Data se aktualizovala"(To co jsme si psali v souboru "update.php") tak nám to napíše "Data se úspěšně aktualizovala" - to znamená, že se vše provedlo vpořádku, a přepne nás zpět do třídy MainActivity, pokud ne, vypíše se "Chyba, data se neaktualizovala". Přidáme taky ErrorListener s metodou onErrorResponse, aby nám to v případě chyby, vypsalo tu chybu, díky čemu to pro nás bude jednodušší opravit.
```java
StringRequest request = new StringRequest(Request.Method.POST, "https://sqlprojekt.000webhostapp.com/update.php",
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("Data se aktualizovala")){
                    Toast.makeText(EditRecept.this, "Data se úspěšně aktualizovala", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditRecept.this, MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(EditRecept.this, "Chyba, data se neaktualizovala", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditRecept.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        })
```
* V další části si vytvoříme Mapu hodnot, které budou odeslány pomocí POST, pošleme pomocí ní hodnoty pro id, název, suroviny a postup.
```java
{
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String,String> params = new HashMap<String,String>();
        
        params.put("id", id);
        params.put("nazev",novyNazev);
        params.put("suroviny",noveSuroviny);
        params.put("postup",novyPostup);
        return params;
    }
};
```
* V poslední části si vytvoříme RequestQueue, frontu na odesílání požadavků a přidáme do ní náš požadavek "request".
```java
    RequestQueue requestQueue = Volley.newRequestQueue(EditRecept.this); 
    requestQueue.add(request);
}
```










