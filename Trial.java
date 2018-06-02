/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package trial;
import java.util.*; 
import org.bson.Document; 
import com.mongodb.*;
import com.mongodb.client.*;
import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Indexes.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.bson.BsonType;
import javafx.scene.Cursor;
/**
*
* @author Ishank
*/

public class Trial extends Application 
{
    // function to make connection with the databse with db-> database, col->collection
    public static Stage primaryStage;
    public static Group root;
    public static Scene scene;
public static MongoCollection connection_images(String db,String col)
{
    MongoClientURI uri= new MongoClientURI("mongodb://Ishank09:test123@movies-shard-00-00-fyvij.mongodb.net:27017,movies-shard-00-01-fyvij.mongodb.net:27017,movies-shard-00-02-fyvij.mongodb.net:27017/test?ssl=true&replicaSet=Movies-shard-0&authSource=admin");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase(db);
    MongoCollection<Document> collection = database.getCollection(col);
    return collection;
}
public static void director_search(String a,String b,String c,int skip_num)
{
ScrollPane director_search=new ScrollPane();
Scene scene6=new Scene(director_search,1705,700);
Group box=new Group();
director_search.setContent(box);
director_search.setFitToHeight(true);
director_search.setFitToWidth(true);
primaryStage.setScene(scene6);
scene6.setCursor(Cursor.DEFAULT);
director_search.setVbarPolicy(ScrollBarPolicy.ALWAYS);

//connecting with database
MongoCollection collection =connection_images("video","movieDetails");
//quary for given condition
BasicDBObject quary= new BasicDBObject();
quary.put("year",Integer.parseInt(c.trim()));
quary.put("imdb.rating", new BasicDBObject("$gte", Integer.parseInt(b)));


FindIterable title;
FindIterable imdb_rating;
FindIterable plot;
FindIterable genres;
FindIterable runtime;
FindIterable year;
FindIterable poster;
FindIterable awards;
if(a.equals("Ascending"))
{
title = collection.find(quary).projection(fields(include("title"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
imdb_rating = collection.find(quary).projection(fields(include("imdb.rating"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
plot = collection.find(quary).projection(fields(include("plot"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
genres =collection.find(quary).projection(fields(include("genres"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
runtime = collection.find(quary).projection(fields(include("runtime"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
year = collection.find(quary).projection(fields(include("year"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
poster = collection.find(quary).projection(fields(include("poster"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
awards = collection.find(quary).projection(fields(include("awards"),excludeId())).limit(10).sort(ascending("imdb.rating")).skip(skip_num);
}
else if(a.equals("Descending"))
{
        title = collection.find(quary).projection(fields(include("title"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    imdb_rating = collection.find(quary).projection(fields(include("imdb.rating"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    plot = collection.find(quary).projection(fields(include("plot"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    genres =collection.find(quary).projection(fields(include("genres"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    runtime = collection.find(quary).projection(fields(include("runtime"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    year = collection.find(quary).projection(fields(include("year"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    poster = collection.find(quary).projection(fields(include("poster"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    awards = collection.find(quary).projection(fields(include("awards"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);  
}
else
{
   title = collection.find(quary).projection(fields(include("title"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
imdb_rating = collection.find(quary).projection(fields(include("imdb.rating"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
plot = collection.find(quary).projection(fields(include("plot"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
genres =collection.find(quary).projection(fields(include("genres"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
runtime = collection.find(quary).projection(fields(include("runtime"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
year = collection.find(quary).projection(fields(include("year"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
poster = collection.find(quary).projection(fields(include("poster"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
awards = collection.find(quary).projection(fields(include("awards"),excludeId())).limit(10).sort(ascending("title")).skip(skip_num);
}
int i=2;
String temp="";
Iterator ite_1=title.iterator();;
Iterator ite_2=imdb_rating.iterator();
Iterator ite_3=plot.iterator();
Iterator ite_4=genres.iterator();
Iterator ite_5=runtime.iterator();
Iterator ite_6=year.iterator();
Iterator ite_7=poster.iterator();
Iterator ite_8=awards.iterator();

//HOME SCENE
Button home=new Button("HOME");
home.setLayoutX(1700);
home.setLayoutY(110); 
home.setStyle(" -fx-font-size: 16px;\n" +
"   -fx-font-family: \"Times New Romes\";\n" +
        "-fx-color: blue;");
box.getChildren().add(home);
home.setOnAction(new EventHandler<ActionEvent>()
{
public void handle(ActionEvent event)
{
primaryStage.setScene(scene);
}
});



while(ite_1.hasNext())
{
temp=ite_1.next().toString();
temp=temp.substring(16,temp.length()-2);
Label l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+10);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 32px;\n" +
"   -fx-font-family: \"Times New Romes\";\n" +
"   -fx-fill: #818181;\n" +
"   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");

temp=ite_2.next().toString();
temp=temp.substring(10,temp.length()-2);
temp=temp.substring(0, 4)+" "+temp.substring(15,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+60);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 12px;\n" +
"   -fx-font-family: \"Times New Romes\";");

temp=ite_3.next().toString();
temp=temp.substring(10,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+80);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 12px;\n" +
"   -fx-font-family: \"Times New Romes\";");

temp=ite_4.next().toString();
temp=temp.substring(10,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+100);
box.getChildren().add(l);
l.setStyle(" -fx-font-size: 12px;\n" +
"   -fx-font-family: \"Times New Romes\";");

temp=ite_5.next().toString();
temp=temp.substring(10,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+120);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 12px;\n" +
"   -fx-font-family: \"Times New Romes\";");

temp=ite_6.next().toString();
temp=temp.substring(10,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+140);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 12px;\n" +
"   -fx-font-family: \"Times New Romes\";");

temp=ite_7.next().toString();
temp=temp.substring(10,temp.length()-2);
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+160);
box.getChildren().add(l);
/*Image poster_image=new Image(temp);
ImageView poster_imageview=new ImageView(poster_image);
poster_imageview.setX(20);
poster_imageview.setY(i*50+40);
poster_imageview.setFitHeight(150);
poster_imageview.setFitWidth(150);
box.getChildren().add(poster_imageview);*/

l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");

temp=ite_8.next().toString();
temp=temp.substring(10,temp.length()-2);
temp=temp.substring(0, 6)+"  ::  "+temp.substring(17, temp.length()-2) ;
l=new Label(temp);
 l.setLayoutX(700);
l.setLayoutY(i*50+180);
box.getChildren().add(l);

l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
i+=5;
}
scene6.setCursor(Cursor.DEFAULT);
//page shifting
Label pages_label=new Label("Page ");
pages_label.setLayoutX(500);
pages_label.setLayoutY(i*50+30);
TextField pages_field=new TextField();
pages_field.setLayoutX(600);
pages_field.setLayoutY(i*50+30);
Button pages_button=new Button("GO");
pages_button.setLayoutX(800);
pages_button.setLayoutY(i*50+30);
box.getChildren().addAll(pages_label,pages_field,pages_button);
pages_button.setOnAction(new EventHandler<ActionEvent>()
{
public void handle(ActionEvent event)
{
scene6.setCursor(Cursor.WAIT);
director_search(a,b, c,Integer.parseInt(pages_field.getText())*10);
}
});
}
public static void  director_scene(int i)
{
    Group director=new Group();
    Scene scene5=new Scene(director,1705,700);
    Group director_header=new Group();
    
    primaryStage.setScene(scene5);
    //Toggle group and RadioButton
    ToggleGroup toggle_group=new ToggleGroup();
    RadioButton Ascending = new RadioButton("Ascending");
    Ascending.setToggleGroup(toggle_group);
    Ascending.setUserData("Ascending");
    
    RadioButton Descending = new RadioButton("Descending");
    Descending.setToggleGroup(toggle_group);
    Descending.setUserData("Descending");
    
    RadioButton Alphabetical = new RadioButton("Alphabetical");
    Alphabetical.setToggleGroup(toggle_group);
    Alphabetical.setUserData("Alphabetical");
    
    //HBox for toggleGroup
    HBox toggle_box=new HBox();
    toggle_box.getChildren().addAll(Ascending,Descending,Alphabetical);
    //ComboBox
    Label rating_label=new Label("Select The Rating");
    ComboBox rating=new ComboBox( );
    rating.getItems().addAll("  9+  ","  8+  ","  7+  ","  6+  ","  5+  ","  4+  ","  3+  ","  2+  ","  1+  ");
    //label and textfield
    Label year_label=new Label("Enter Year");
    TextField year_field=new TextField();
    //HBox for yeargroup
    HBox year_group=new HBox();
    year_group.getChildren().addAll(year_label,year_field);
    //button for searching
    Button search_director=new Button("Search");
    //setting layoutof all
    toggle_box.setLayoutX(500);
    toggle_box.setLayoutY(200);
    rating_label.setLayoutX(500);
    rating_label.setLayoutY(300);
    rating.setLayoutX(600);
    rating.setLayoutY(300);
    year_group.setLayoutX(500);
    year_group.setLayoutY(400);
    search_director.setLayoutX(600);
    search_director.setLayoutY(500);
    director.getChildren().addAll(director_header,toggle_box,rating_label,rating,year_group,search_director);
    
    //Button Action Listener
    search_director.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                scene5.setCursor(Cursor.WAIT);
                String radio_temp=(toggle_group.getSelectedToggle().getUserData().toString());
                String combo_temp=rating.getValue().toString();
                String text_temp=year_field.getText();
                combo_temp=combo_temp.trim();
                combo_temp=combo_temp.substring(0,1);
                director_search(radio_temp,combo_temp,text_temp,0);
            }
        });
}
public static void genre_search(List<String> list,int j,int skip_num)
{
    ScrollPane genre_search=new ScrollPane();
    Scene scene4=new Scene(genre_search,1705,700);
    Group box=new Group();
    
    genre_search.setContent(box);
    genre_search.setFitToHeight(true);
    genre_search.setFitToWidth(true);
    primaryStage.setScene(scene4);
    scene4.setCursor(Cursor.DEFAULT);
    genre_search.setVbarPolicy(ScrollBarPolicy.ALWAYS); 
    
    //connecting with database
    MongoCollection collection =connection_images("video","movieDetails");
    
    FindIterable title;
    FindIterable imdb_rating;
    FindIterable plot;
    FindIterable genres;
    FindIterable runtime;
    FindIterable year;
    FindIterable poster;
    FindIterable awards;
    title = collection.find(all("genres",list.subList(0, j))).projection(fields(include("title"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    imdb_rating = collection.find(all("genres",list.subList(0, j))).projection(fields(include("imdb.rating"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    plot = collection.find(all("genres",list.subList(0, j))).projection(fields(include("plot"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    genres =collection.find(all("genres",list.subList(0, j))).projection(fields(include("genres"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    runtime = collection.find(all("genres",list.subList(0, j))).projection(fields(include("runtime"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    year = collection.find(all("genres",list.subList(0, j))).projection(fields(include("year"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    poster = collection.find(all("genres",list.subList(0, j))).projection(fields(include("poster"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    awards = collection.find(all("genres",list.subList(0, j))).projection(fields(include("awards"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);  
    int i=2;
    String temp="";
    Iterator ite_1=title.iterator();;
    Iterator ite_2=imdb_rating.iterator();
    Iterator ite_3=plot.iterator();
    Iterator ite_4=genres.iterator();
    Iterator ite_5=runtime.iterator();
    Iterator ite_6=year.iterator();
    Iterator ite_7=poster.iterator();
    Iterator ite_8=awards.iterator();
    
    
    
    //HOME SCENE
    Button home=new Button("HOME");
    home.setLayoutX(1700);
    home.setLayoutY(110); 
    home.setStyle(" -fx-font-size: 16px;\n" +
    "   -fx-font-family: \"Times New Romes\";\n" +
            "-fx-color: blue;");
    box.getChildren().add(home);
    home.setOnAction(new EventHandler<ActionEvent>()
    {
    public void handle(ActionEvent event)
    {
    primaryStage.setScene(scene);
    }
    });

    
    
    while(ite_1.hasNext())
    {
        temp=ite_1.next().toString();
        temp=temp.substring(16,temp.length()-2);
        Label l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+10);
        box.getChildren().add(l);
        
        l.setStyle(" -fx-font-size: 32px;\n" +
        "   -fx-font-family: \"Times New Romes\";\n" +
        "   -fx-fill: #818181;\n" +
        "   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");

        temp=ite_2.next().toString();
        temp=temp.substring(10,temp.length()-2);
        temp=temp.substring(0, 4)+" "+temp.substring(15,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+60);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_3.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+80);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_4.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+100);
        box.getChildren().add(l);
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_5.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+120);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_6.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+140);
        box.getChildren().add(l);
        
        l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_7.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+160);
        box.getChildren().add(l);
        /*Image poster_image=new Image(temp);
        ImageView poster_imageview=new ImageView(poster_image);
        poster_imageview.setX(20);
        poster_imageview.setY(i*50+40);
        poster_imageview.setFitHeight(150);
        poster_imageview.setFitWidth(150);
        box.getChildren().add(poster_imageview);*/
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_8.next().toString();
        temp=temp.substring(10,temp.length()-2);
        temp=temp.substring(0, 6)+"  ::  "+temp.substring(17, temp.length()-2) ;
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+180);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        i+=5;
    }
    scene4.setCursor(Cursor.DEFAULT);
    //page shifting 
    Label pages_label=new Label("Page ");
    pages_label.setLayoutX(500);
    pages_label.setLayoutY(i*50+30);
    TextField pages_field=new TextField();
    pages_field.setLayoutX(600);
    pages_field.setLayoutY(i*50+30);
    Button pages_button=new Button("GO");
    pages_button.setLayoutX(800);
    pages_button.setLayoutY(i*50+30);
    box.getChildren().addAll(pages_label,pages_field,pages_button);
    pages_button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                scene4.setCursor(Cursor.WAIT);
                genre_search(list,j,Integer.parseInt(pages_field.getText()));
            }
        });
}
public static void genre_scene(int skip_num)
{
    //close the cursor..
    VBox genre=new VBox(10);
    genre.setAlignment(Pos.CENTER_LEFT);
    Scene scene3=new Scene(genre,1705,700);
    Group genre_header=new Group();
    
    primaryStage.setScene(scene3);
    CheckBox Western=new CheckBox("Western");
    CheckBox Comedy=new CheckBox("Comedy");
    CheckBox Action=new CheckBox("Action");
    CheckBox Musical=new CheckBox("Musical");
    CheckBox Music=new CheckBox("Music");
    CheckBox Drama=new CheckBox("Drama");
    CheckBox Crime=new CheckBox("Crime");
    CheckBox Adventure=new CheckBox("Adventure");
    CheckBox Documentary=new CheckBox("Documentary");
    CheckBox Fantasy=new CheckBox("Fantasy");
    CheckBox Sci_Fi=new CheckBox("Sci-Fi");
    CheckBox Romance=new CheckBox("Romance");
    CheckBox Thriller=new CheckBox("Thriller");
    CheckBox Mystery=new CheckBox("Mystery");
    CheckBox Horror=new CheckBox("Horror");
    CheckBox Animation=new CheckBox("Animation");
    CheckBox Family=new CheckBox("Family");
    CheckBox History=new CheckBox("History");
    CheckBox War=new CheckBox("War");
    CheckBox Biography=new CheckBox("Biography");
    CheckBox Short=new CheckBox("Short");
    Button genre_search=new Button("Search");
    genre.getChildren().addAll(genre_header,Western,Comedy,Action,Musical,Music,Drama,Crime,Adventure,Documentary,Fantasy,Sci_Fi,Romance,Thriller,Mystery,Horror,Animation,Family,History,War,Biography,Short,genre_search);
    genre_search.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                //all the boxed checked
                String checked[]=new String[21];
                int i=0;
                if(Western.isSelected())
                    checked[i++]="Western";
                if(Comedy.isSelected())
                    checked[i++]="Comedy";
                if(Action.isSelected())
                    checked[i++]="Action";
                if(Musical.isSelected())
                    checked[i++]="Musical";
                if(Music.isSelected())
                    checked[i++]="Music";
                if(Drama.isSelected())
                    checked[i++]="Drama";
                if(Crime.isSelected())
                    checked[i++]="Crime";
                if(Adventure.isSelected())
                    checked[i++]="Adventure";
                if(Documentary.isSelected())
                    checked[i++]="Documentary";
                if(Fantasy.isSelected())
                    checked[i++]="Fantasy";
                if(Sci_Fi.isSelected())
                    checked[i++]="Sci_Fi";
                if(Romance.isSelected())
                    checked[i++]="Romance"; 
                if(Thriller.isSelected())
                    checked[i++]="Thriller";
                if(Mystery.isSelected())
                    checked[i++]="Mystery";
                if(Horror.isSelected())
                    checked[i++]="Horror";
                if(Animation.isSelected())
                    checked[i++]="Animation";
                if(Family.isSelected())
                    checked[i++]="Family";
                if(History.isSelected())
                    checked[i++]="History";
                if(War.isSelected())
                    checked[i++]="War";
                if(Biography.isSelected())
                    checked[i++]="Biograph";
                if(Short.isSelected())
                    checked[i++]="Short";
                List<String> list=Arrays.asList(checked);
                scene3.setCursor(Cursor.WAIT);
                genre_search(list,i,0);
            }
        });
    
}
public static void top_movie_scene(int skip_num)
{
    skip_num*=10;
    //scroll bar. root layout
    ScrollPane sc = new ScrollPane();
    Scene scene2=new Scene(sc,1705,700);
    primaryStage.setScene(scene2);
    
    
    
    
    sc.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    //All Usefull data find iterable
    FindIterable title;
    FindIterable imdb_rating;
    FindIterable plot;
    FindIterable genres;
    FindIterable runtime;
    FindIterable year;
    FindIterable poster;
    FindIterable awards;
    
    //databse query of all usefulldatya
    BasicDBObject query_1=new BasicDBObject("title", new BasicDBObject("$ne",null));
    query_1.put("imdb.rating", new BasicDBObject("$ne",null));
    query_1.put("plot", new BasicDBObject("$ne",null));
    query_1.put("genres", new BasicDBObject("$ne",null));
    query_1.put("runtime", new BasicDBObject("$ne",null));
    query_1.put("year", new BasicDBObject("$ne",null));
    query_1.put("poster", new BasicDBObject("$ne",null));
   // BasicDBObject query_8=new BasicDBObject("awards", new BasicDBObject("$ne",null));
    String temp="";
    
    
    MongoCollection collection =connection_images("video","movieDetails");
    int i=2;
    title = collection.find(query_1).projection(fields(include("title"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    imdb_rating = collection.find(query_1).projection(fields(include("imdb.rating"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    plot = collection.find(query_1).projection(fields(include("plot"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    genres =collection.find(query_1).projection(fields(include("genres"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    runtime = collection.find(query_1).projection(fields(include("runtime"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    year = collection.find(query_1).projection(fields(include("year"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);        
    poster = collection.find(query_1).projection(fields(include("poster"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);
    awards = collection.find().projection(fields(include("awards"),excludeId())).limit(10).sort(descending("imdb.rating")).skip(skip_num);    
    Iterator ite_1=title.iterator();;
    Iterator ite_2=imdb_rating.iterator();
    Iterator ite_3=plot.iterator();
    Iterator ite_4=genres.iterator();
    Iterator ite_5=runtime.iterator();
    Iterator ite_6=year.iterator();
    Iterator ite_7=poster.iterator();
    Iterator ite_8=awards.iterator();
    

    Group box=new Group();
    sc.setContent(box);
    sc.setFitToHeight(true);
    sc.setFitToWidth(true);


    //HOME SCENE
    Button home=new Button("HOME");
    home.setLayoutX(1700);
    home.setLayoutY(110); 
    home.setStyle(" -fx-font-size: 16px;\n" +
    "   -fx-font-family: \"Times New Romes\";\n" +
            "-fx-color: blue;");
    box.getChildren().add(home);
    home.setOnAction(new EventHandler<ActionEvent>()
    {
    public void handle(ActionEvent event)
    {
    primaryStage.setScene(scene);
    }
    });
    
    
    
    while(ite_1.hasNext())
    {
        temp=ite_1.next().toString();
        temp=temp.substring(16,temp.length()-2);
        Label l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+10);
        box.getChildren().add(l);
        
        l.setStyle(" -fx-font-size: 32px;\n" +
        "   -fx-font-family: \"Times New Romes\";\n" +
        "   -fx-fill: #818181;\n" +
        "   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");

        temp=ite_2.next().toString();
        temp=temp.substring(10,temp.length()-2);
        temp=temp.substring(0, 4)+" "+temp.substring(15,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+60);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_3.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+80);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_4.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+100);
        box.getChildren().add(l);
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_5.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+120);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_6.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+140);
        box.getChildren().add(l);
        
        l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_7.next().toString();
        temp=temp.substring(10,temp.length()-2);
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+160);
        box.getChildren().add(l);
        /*Image poster_image=new Image(temp);
        ImageView poster_imageview=new ImageView(poster_image);
        poster_imageview.setX(20);
        poster_imageview.setY(i*50+40);
        poster_imageview.setFitHeight(150);
        poster_imageview.setFitWidth(150);
        box.getChildren().add(poster_imageview);*/
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        temp=ite_8.next().toString();
        temp=temp.substring(10,temp.length()-2);
        temp=temp.substring(0, 6)+"  ::  "+temp.substring(17, temp.length()-2) ;
        l=new Label(temp);
        l.setLayoutX(700);
        l.setLayoutY(i*50+180);
        box.getChildren().add(l);
        
         l.setStyle(" -fx-font-size: 12px;\n" +
        "   -fx-font-family: \"Times New Romes\";");
        
        i+=5;
    }  
    
    scene2.setCursor(Cursor.DEFAULT);
    //page shifting 
    Label pages_label=new Label("Page ");
    pages_label.setLayoutX(500);
    pages_label.setLayoutY(i*50+30);
    TextField pages_field=new TextField();
    pages_field.setLayoutX(600);
    pages_field.setLayoutY(i*50+30);
    Button pages_button=new Button("GO");
    pages_button.setLayoutX(800);
    pages_button.setLayoutY(i*50+30);
    box.getChildren().addAll(pages_label,pages_field,pages_button);
    pages_button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                scene2.setCursor(Cursor.WAIT);
                top_movie_scene(Integer.parseInt(pages_field.getText()));
            }
        });
        
}
public static void header(Group root)
{
        FindIterable it;
        Iterator ite;
        //databse query and cursor
        BasicDBObject query;
        String temp="";
    //vbox for logo and black color image
        HBox box_1=new HBox();
        box_1.setAlignment(Pos.TOP_LEFT);
        root.getChildren().add(box_1);
        ObservableList list_1=box_1.getChildren();
        
        //connection of images database
        MongoCollection collection=connection_images("images","img");
        
        //connect with images database and img collection, getting link of logo in result by calling the function
        collection=connection_images("images","img");
        query = new BasicDBObject("info", "imdb logo");
        it = collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        //positioning the retrived link to get the logo
        ite = it.iterator();
        while (ite.hasNext()) 
        {  
            temp=ite.next().toString();
            temp=temp.substring(15,temp.length()-2);
        }
        Image logo=new Image(temp);
        ImageView logoview=new ImageView(logo);
        logoview.setFitHeight(100);
        logoview.setFitWidth(200);
        list_1.add(logoview);
        
        
         // backgroung in upperpart of the window. (the one with the logo)
        query = new BasicDBObject("info", "Black_top_image");
        it = collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
            temp=ite.next().toString();
            temp=temp.substring(15,temp.length()-2);
        }
        Image Black_top_image =new Image(temp);
        ImageView Black_top_imageview=new ImageView(Black_top_image);
        Black_top_imageview.setFitHeight(100);
        Black_top_imageview.setFitWidth(10000);
        list_1.add(Black_top_imageview);
        
        
}
@Override
public void start(Stage primaryStage) 
    {
        
        this.primaryStage= primaryStage;
        root=new Group();
        scene=new Scene(root,1505,700);
        FindIterable it;
        Iterator ite;
        MongoCollection collection =connection_images("images","img"); ;
        //databse query and cursor
        BasicDBObject query;
        String temp="";
        
        //header
        header(root);
       
        //top movies button and customised button
        query= new BasicDBObject("info","top_movies");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
        temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image top_movie_image=new Image(temp);
        ImageView top_movie_imageview=new ImageView(top_movie_image);
        top_movie_imageview.setFitHeight(400);
        top_movie_imageview.setFitWidth(700);
        Button top_movies=new Button("",top_movie_imageview);
        root.getChildren().add(top_movies);
        top_movies.setLayoutX(0);
        top_movies.setLayoutY(100);
        
        //button style
        top_movies.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");

        //top movie action scene
        top_movies.setOnAction(new EventHandler<ActionEvent>()
        {
            
            public void handle(ActionEvent event)
            {
                scene.setCursor(Cursor.WAIT);
                top_movie_scene(0);
            }
        });
        
        
        //genre button
        query=new BasicDBObject("info","genre");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
            temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image genre_image=new Image(temp);
        ImageView genre_imageview=new ImageView(genre_image);
        genre_imageview.setFitHeight(400);
        genre_imageview.setFitWidth(400);
        Button genre=new Button("",genre_imageview);
        root.getChildren().add(genre);
        genre.setLayoutX(950);
        genre.setLayoutY(300);
        
         //button style        
        genre.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");
        
        
        genre.setOnAction(new EventHandler<ActionEvent>()
        {
            
            public void handle(ActionEvent event)
            {
                scene.setCursor(Cursor.WAIT);
                genre_scene(0);
            }
        });
        
        
        //login button
        query=new BasicDBObject("info","login");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
            temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image login_image=new Image(temp);
        ImageView login_imageView=new ImageView(login_image);
        login_imageView.setFitHeight(195);
        login_imageView.setFitWidth(195);
        Button login=new Button("",login_imageView);
        root.getChildren().add(login);
        login.setLayoutX(1150);
        login.setLayoutY(100);
     
         //button style        
        login.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");
        
           //Directors
        query=new BasicDBObject("info","director");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
        temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image director_image=new Image(temp);
        ImageView director_imageView=new ImageView(director_image);
        director_imageView.setFitHeight(200);
        director_imageView.setFitWidth(225);
        Button director=new Button("",director_imageView);
        root.getChildren().add(director);
        director.setLayoutX(715);
        director.setLayoutY(300);
     
         //button style        
        director.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");
        
        director.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                scene.setCursor(Cursor.WAIT);
                director_scene(0);
            }
        });
        
        
        //random
        query=new BasicDBObject("info","random");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
        temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image random_image=new Image(temp);
        ImageView random_imageView=new ImageView(random_image);
        random_imageView.setFitHeight(200);
        random_imageView.setFitWidth(940);
        Button random=new Button("",random_imageView);
        root.getChildren().add(random);
        random.setLayoutX(0);
        random.setLayoutY(510);
        
         //button style        
        random.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");
        
        //rotten tomatoes
        query=new BasicDBObject("info","rotten_tomatoes");
        it=collection.find(query).projection(fields(include("link"),excludeId())).limit(1);
        ite=it.iterator();
        while(ite.hasNext())
        {
        temp=ite.next().toString();
        }
        temp=temp.substring(15,temp.length()-2);
        Image rotten_tomatoes_image=new Image(temp);
        ImageView rotten_tomatoes_imageView=new ImageView(rotten_tomatoes_image);
        rotten_tomatoes_imageView.setFitHeight(195);
        rotten_tomatoes_imageView.setFitWidth(425);
        Button rotten_tomatoes=new Button("",rotten_tomatoes_imageView);
        root.getChildren().add(rotten_tomatoes);
        rotten_tomatoes.setLayoutX(710);
        rotten_tomatoes.setLayoutY(100);
        
         //button style        
        rotten_tomatoes.setStyle("-fx-text-fill: black;\n" +
        "    -fx-base: black;\n" +
        "    -fx-background-color: black;\n" +
        "    -fx-focus-color: black;\n" +
        "     -fx-outer-border:black;\n" +
        "        -fx-inner-border:black; \n" +
        "    -fx-border-color: black;");
        
        
        //setting horizontal and vertical gap and padding for grid
        primaryStage.setScene(scene);
        primaryStage.show();
}

/**
 * @param args the command line arguments
 */
public static void main(String[] args) {
    launch(args);
}

}
